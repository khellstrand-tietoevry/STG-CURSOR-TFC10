# TFC10 — Legacy COBOL (Create Hovedkontrakt)

Standalone copy of the legacy COBOL source for mainframe **TFC10** (create
hovedkontrakt / main contract), extracted for modernization analysis with the
Cursor **code-modernization** plugin.

## Layout

```
legacy/STG/TFC10/
├── src/                 10 COBOL programs (.src) — H/K/L entry and one-hop helpers (incl. F115L050)
├── copybooks/           40 copybooks (.copy) — record layouts and includes
└── corpus-manifest.json Call-graph metadata for the gathered closure
analysis/                Plugin discovery output (assess, map, rules, brief, …)
modernized/              Transformed or uplifted code (empty until a build track runs)
```

This mirrors the `legacy/$system` convention expected by **code-modernization**
commands (`/modernize-preflight`, `/modernize-assess`, `/modernize-map`, …):
the system name is **`STG/TFC10`**, so its code lives at `legacy/STG/TFC10/`.

Nothing under `legacy/` should be edited — it is a frozen copy of the original
mainframe source, not a working copy.

## Workspace boundary

After the initial import, **all further work stays inside this repo.** Agents and
humans should not use other checkouts (parity testing, cob2jav, tfi-workspace
siblings, etc.) as inspiration or as a source of missing files. If something is
needed, copy it in explicitly and record it here. See `AGENTS.md`.

## Provenance

Copied from `cob2jav-tfc90-canonical/STG/TFC10/onehop/{src,copybook}` (commit
`363262d8518239a19a6f0d590a389ec40f6a197d`) in the **tfi-workspace** container.
That folder is the **one-hop call-graph closure** around canonical TFC10 H/K/L
(`FTFCH100`, `FTFCK100`, `FTFCL100`) and its immediate called programs.

The sibling repo `tfi-java-cobol-parity-testing` holds a wider executable lane
(mocks, DB adapters, and additional boundaries for 109 parity scenarios). That
material is intentionally **not** copied here; this repo is legacy COBOL only.

## Run log

See [`RUN_SEQUENCE.md`](RUN_SEQUENCE.md) for the executed modernization pipeline (2026-09-29).

**System label:** `TFC10` · **Legacy:** `legacy/TFC10/` → `legacy/STG/TFC10/`

## Suggested next commands

```
/modernize-transform TFC10
/modernize-verify TFC10
/modernize-status TFC10
```

Open [`analysis/TFC10/REPORT.html`](analysis/TFC10/REPORT.html) for the one-page summary.
