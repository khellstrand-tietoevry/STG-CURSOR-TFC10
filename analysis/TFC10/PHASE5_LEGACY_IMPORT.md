# Phase 5 — legacy import status

**Date:** 2026-09-29

## Required for full F115L050 parity

| Artifact | Status |
|----------|--------|
| `legacy/STG/TFC10/src/F115L050.src` | **Imported** 2026-09-29 from `mainframe-prod-inventory/src/F115L050.src` (SHA-256 `c460ff7b…`) |
| `R115L050.copy` | Present under `legacy/STG/TFC10/copybooks/` |
| DB/CICS callees of F115L050 | Out of one-hop tree (see `topology.json`) |

## Next steps

1. ~~Add **`F115L050.src`**~~ — done (563 lines, `Program-ID. F115L050.`).
2. ~~Re-run **`modernize-map TFC10`**~~ — done 2026-09-29 (step 19).
3. ~~Replace `F115L050BackendPort` stub~~ — `TracedInitialCheckBackend` + `InitialCheckDependencies` (2026-09-29 transform).

## Java delivered without `.src`

- `ftfc-l050` — `InitialCheckService` maps **E100-Check-Status** fields and delegates to `F115L050BackendPort`.
- `ftfc-k100` — `KModuleValidator` uses `InitialCheckService` (RULE-003).
