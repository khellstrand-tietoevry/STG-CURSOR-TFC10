# Preflight — TFC10

**Date:** 2026-09-29  
**Target stack:** Java 21 Spring Boot  
**Legacy path:** `legacy/TFC10/` → `legacy/STG/TFC10/` (directory in workspace)

## Answers (human preflight — headless defaults)

### 1. Scope

**Answer (working default for this repo):** One-hop **slice** of TFC10 (Create Hovedkontrakt H/K/L + helpers). Not the full mainframe estate. External CALL targets (DB programs, `F115L050`, etc.) are **outside** this tree until explicitly imported.

### 2. Build & test locally

**Answer:** COBOL does not build fully locally; Java target stack can run `mvn test` (Spring Boot 3.4.4 smoke verified). No CI timing for this standalone repo.

### 3. Bespoke build infrastructure

**Answer:** None in this repo; legacy assumes mainframe DB2/CICS runtime.

### 4. Prior attempts

**Answer:** Parity/modernization work exists elsewhere; **this repo is self-contained** — do not depend on external checkouts during runs.

### 5. Off limits

**Answer:** Everything under `legacy/**` is frozen. Generated analysis and `modernized/` only.

## Check 6 — Scope boundary

Standalone git repo. **Outbound:** many `CALL` targets have **no `.src` in tree** (16 names) — map and transform must treat them as external boundaries or import missing programs into `legacy/`. **Inbound:** none identified in-repo.

## Summary table

| Check | Status | Finding |
|-------|--------|---------|
| 0 Answers | ⚠️ | Defaults recorded; confirm with SME if needed |
| 1 Stack | ✅ | COBOL fixed-format `.src` + `.copy`; target Java/Maven |
| 2 Analysis tools | ✅ | python3 3.14, no scc/cloc (use wc/find) |
| 3 Build toolchain | ⚠️ | cobc 3.2: partial syntax-only (3/9 clean); H/K abort on EXEC SQL/dialect; **target** Spring Boot smoke OK |
| 4 Source completeness | ⚠️ | **F115L050.src** imported 2026-09-29; **17** other CALL targets still missing `.src` |
| 5 Optional context | ⚠️ | git history partial; no APM |
| 6 Scope | ⚠️ | One-hop slice; not closed call graph |
| 7 Legacy protection | ⚠️ | `.cursor/rules` + AGENTS.md; no Claude `Edit(legacy/**)` deny |

## Verdict

| Command | Verdict |
|---------|---------|
| assess, map, extract-rules | **Ready-with-gaps** (missing sources documented) |
| brief | **Ready** after discovery artifacts |
| transform | **Ready-with-gaps** until missing K-gate (`F115L050`) resolved or scoped out in brief |
| verify | **Ready-with-gaps** (dual COBOL run unlikely) |
| harden | **Ready-with-gaps** |

## Legacy expansion log (approved imports)

| Date | Artifact | Note |
|------|----------|------|
| 2026-09-29 | `legacy/STG/TFC10/src/F115L050.src` | Phase 5; frozen COBOL import from mainframe inventory |

Files under `legacy/` added via this log are **intentional** scope expansion, not accidental edits to the original one-hop snapshot.

**Next:** `modernize-verify TFC10`
