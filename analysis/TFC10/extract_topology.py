#!/usr/bin/env python3
"""Extract call/data topology from legacy/STG/TFC10 COBOL sources.

Writes analysis/TFC10/topology.json (plugin schema). Rerun after legacy imports.
"""
from __future__ import annotations

import json
import re
from collections import defaultdict
from datetime import date
from pathlib import Path

REPO = Path(__file__).resolve().parents[2]
LEGACY = REPO / "legacy" / "STG" / "TFC10"
SRC = LEGACY / "src"
OUT = Path(__file__).resolve().parent / "topology.json"

CALL_RE = re.compile(r"Call\s+'([^']+)'", re.IGNORECASE)


def cobol_code_line(raw: str) -> str:
    """Area B (cols 8–72) when fixed format; otherwise strip trailing seq."""
    if len(raw) >= 7 and raw[6] == " ":
        return raw[6:72].rstrip()
    return raw.rstrip()


def loc_of(path: Path) -> int:
    return sum(1 for _ in path.open(encoding="utf-8", errors="replace"))


def extract_calls(path: Path) -> list[tuple[int, str, str | None]]:
    prog = path.stem.upper()
    hits: list[tuple[int, str, str | None]] = []
    for i, raw in enumerate(path.read_text(encoding="utf-8", errors="replace").splitlines(), 1):
        line = cobol_code_line(raw)
        if line.lstrip().startswith("*"):
            continue
        m = CALL_RE.search(line)
        if not m:
            continue
        using = None
        um = re.search(r"Using\s+(.+)$", line, re.IGNORECASE)
        if um:
            using = um.group(1).strip()
        hits.append((i, m.group(1).upper(), using))
    return hits


def module_node(prog: str, rel_file: str | None, language: str = "cobol", description: str | None = None) -> dict:
    node: dict = {
        "id": prog,
        "name": prog,
        "kind": "module",
        "language": language,
    }
    if rel_file:
        p = LEGACY / rel_file
        node["file"] = rel_file
        node["loc"] = loc_of(p) if p.is_file() else 0
    else:
        node["loc"] = 0
    if description:
        node["description"] = description
    return node


DESCRIPTIONS: dict[str, str] = {
    "FTFCH100": (
        "FTFCH100 is the TFC10 H-module orchestrator for create hovedkontrakt. It receives the STG "
        "header and payload, invokes FTFCK100 for control validation, then FTFCL100 for load and "
        "persistence logic when K succeeds. It calls F791TRAC for trace and F7918030 for structured "
        "errors. Trade-finance operators experience this as the server entry that sequences K then L."
    ),
    "FTFCK100": (
        "FTFCK100 is the K-module validator for TFC10. It enforces function, medium, dates via "
        "F7919040, system codes via F115ISC0 and F115ISR0, and the initial-check gate via F115L050 "
        "using R115L050-Initial-Check. Failures route through R7918030 and R7919999. It is called "
        "only from FTFCH100 and is on the critical path before L runs."
    ),
    "FTFCL100": (
        "FTFCL100 is the L-module loader for contract create. It reads and writes main contract and "
        "amount data through F115IMC0 and F115ICA0, events via F115IEI0, contract numbers via F115L020, "
        "charges via F115L090, timestamps via F7919070 and F7919270, and calendar helpers F7919010 and "
        "F7919090. It is invoked from FTFCH100 after K returns success."
    ),
    "F115L050": (
        "F115L050 performs initial checks before downstream business logic, as described in its header "
        "by Rune Mesel. It reads main contract via F115IMC0, contract and part amounts via F115ICA0 and "
        "F115IPA0, system code relation via F115ISR0, status via F115ISC0, and system type via F115IST0. "
        "FTFCK100 calls it with R115L050-Initial-Check; errors use F7918030 and F115L999 abend."
    ),
    "F115L020": (
        "F115L020 generates contract numbers for the create flow. FTFCL100 calls it with "
        "R115L020-Gen-Contract-No. It uses F115ISC0 for system code reads and F7918030 on errors; "
        "F115L999 handles abend paths. It is a helper in the L-module contract-number step."
    ),
    "F115L090": (
        "F115L090 deletes or adjusts shadow charges during create. FTFCL100 invokes it with "
        "R115L090-Del-Charges. It calls F115IPC0 for part charges and F115ICH0 for main charges, "
        "with F7918030 and F115L999 on failure paths."
    ),
    "F7919010": (
        "F7919010 adds days to dates (R7919010-Dato-Pluss-Dager). FTFCL100 uses it when computing "
        "dates in the create flow alongside F7919090 holiday checks. No outbound CALL targets appear "
        "in the in-repo source; it is a leaf helper in the F7919 family."
    ),
    "F7919070": (
        "F7919070 builds DB2-style timestamps (R7919070-Timestamp). FTFCL100 calls it during load; "
        "it delegates to F7919071 and F7919072 for TMS and COBOL date conversion. Those callees are "
        "not present as .src in this repository."
    ),
    "F7919090": (
        "F7919090 evaluates holidays (R7919090-Helligdager). FTFCL100 calls it when adjusting dates "
        "with F7919010. The in-repo program has no outbound CALL statements in executable code."
    ),
    "F7919270": (
        "F7919270 converts timestamps (R7919270-Konv-Timestamp). FTFCL100 calls it after F7919070 "
        "in the timestamp path. No further CALL targets appear in the in-repo source."
    ),
}


def main() -> None:
    programs = sorted(p.stem.upper() for p in SRC.glob("*.src"))
    in_repo = set(programs)

    edges_raw: list[dict] = []
    callers: defaultdict[str, set[str]] = defaultdict(set)
    callees: defaultdict[str, set[str]] = defaultdict(set)

    for path in sorted(SRC.glob("*.src")):
        prog = path.stem.upper()
        rel = f"src/{path.name}"
        for line, target, using in extract_calls(path):
            boundary = "internal" if target in in_repo else "external"
            edges_raw.append(
                {
                    "source": prog,
                    "target": target,
                    "kind": "call",
                    "boundary": boundary,
                    "line": line,
                    "using": using,
                    "source_path": rel,
                }
            )
            callers[prog].add(target)
            callees[target].add(prog)

    external = sorted({e["target"] for e in edges_raw if e["target"] not in in_repo})

    stg = ["FTFCH100", "FTFCK100", "FTFCL100"]
    helpers = [p for p in programs if p not in stg]

    children_stg = [
        module_node(p, f"src/{p}.src", description=DESCRIPTIONS.get(p))
        for p in stg
        if p in in_repo
    ]
    children_helpers = [
        module_node(p, f"src/{p}.src", description=DESCRIPTIONS.get(p))
        for p in helpers
    ]
    children_boundary = [
        module_node(
            t,
            None,
            description=(
                f"{t} is called from in-repo TFC10 programs but has no .src in legacy/STG/TFC10. "
                f"Callers: {', '.join(sorted(callees.get(t, [])))}."
            ),
        )
        for t in external
    ]

    datastores = [
        {"id": "ds:TFCE100", "name": "TFCE100 STG payload", "kind": "datastore"},
        {"id": "ds:DB2-CONTRACT", "name": "Contract tables (via F115I* modules)", "kind": "datastore"},
        {"id": "ds:SYS-CODES", "name": "System code tables (F115ISC0/F115ISR0)", "kind": "datastore"},
    ]

    graph_edges = [{"source": e["source"], "target": e["target"], "kind": e["kind"]} for e in edges_raw]
    # Dedupe graph edges
    seen: set[tuple[str, str, str]] = set()
    unique_edges: list[dict] = []
    for e in graph_edges:
        key = (e["source"], e["target"], e["kind"])
        if key in seen:
            continue
        seen.add(key)
        unique_edges.append(e)

    entry_points = ["FTFCH100"]
    dead_ends = sorted(
        p
        for p in programs
        if p not in entry_points and not callees.get(p)
    )

    observations = [
        "FTFCK100 → F115L050 is now an in-repo edge after importing F115L050.src; F115L050 fans out to "
        "six F115I* DB boundary programs still missing locally.",
        "FTFCL100 concentrates the widest CALL surface (F115IMC0, F115ICA0, F115IEI0, helpers F115L020/L090).",
        "F7919070 depends on F7919071/F7919072 outside the tree — timestamp parity needs those imports or stubs.",
        "Decimal-Point Is Comma appears on H/K/L and F115L050 — numeric formatting must stay in parity tests.",
        "F115L050 has commented-out update-timestamp checks (Nina, July); Java must preserve legacy quirk if still disabled in source.",
    ]

    flows = [
        {
            "name": "Create hovedkontrakt (TFC10)",
            "persona": "Trade finance user creating a main contract",
            "description": "User submits create contract; STG runs H then K then L with validation and load.",
            "steps": [
                {"label": "STG invokes H-module", "nodes": ["FTFCH100"]},
                {"label": "H calls K validation", "nodes": ["FTFCH100", "FTFCK100"]},
                {"label": "K runs initial check (F115L050)", "nodes": ["FTFCK100", "F115L050"]},
                {"label": "K passes control to L on success", "nodes": ["FTFCH100", "FTFCL100"]},
                {"label": "L loads contract, amounts, charges, timestamps", "nodes": ["FTFCL100", "F115L020", "F115L090", "F7919070"]},
            ],
        }
    ]

    topology = {
        "system": "TFC10",
        "generated": date.today().isoformat(),
        "root": {
            "id": "sys",
            "name": "TFC10",
            "kind": "system",
            "children": [
                {"id": "dom:stg", "name": "STG H/K/L", "kind": "domain", "children": children_stg},
                {"id": "dom:helpers", "name": "In-repo helpers", "kind": "domain", "children": children_helpers},
                {
                    "id": "dom:boundary",
                    "name": "External CALL targets",
                    "kind": "domain",
                    "children": children_boundary,
                },
                {"id": "dom:data", "name": "Data stores", "kind": "domain", "children": datastores},
            ],
        },
        "edges": unique_edges,
        "entryPoints": entry_points,
        "deadEnds": dead_ends,
        "observations": observations,
        "flows": flows,
        "in_repo_modules": sorted(in_repo),
        "external_call_targets_missing_source": external,
        "call_edge_count": len(edges_raw),
        "edges_detail_sample": edges_raw[:40],
    }

    OUT.write_text(json.dumps(topology, indent=2) + "\n", encoding="utf-8")

    print(f"Wrote {OUT}")
    print(f"Programs in repo: {len(in_repo)} ({', '.join(sorted(in_repo))})")
    print(f"External CALL targets: {len(external)}")
    print(f"Unique call edges: {len(unique_edges)} (raw {len(edges_raw)})")
    print(f"Entry: {entry_points}; dead-ends in repo: {dead_ends}")
    if "F115L050" in in_repo:
        print("F115L050: in-repo; FTFCK100 -> F115L050 edge present.")


if __name__ == "__main__":
    main()
