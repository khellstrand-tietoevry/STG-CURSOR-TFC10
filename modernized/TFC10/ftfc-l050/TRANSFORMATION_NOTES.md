# F115L050 — transformation notes (Phase 5)

| Legacy | Java |
|--------|------|
| `FTFCK100` E100-Check-Status | `KInitialCheckMapper` + `KModuleValidator` |
| `F115L050` C100 / C000 | `InitialCheckService` + `TracedInitialCheckBackend` |
| `F115IMC0`, `F115ISR0`, `F115ISC0`, … | `InitialCheckDependencies` (ports) |
| `R115L050.copy` | `InitialCheckCommand` |

**Source:** `legacy/STG/TFC10/src/F115L050.src` (imported 2026-09-29).

**Equivalence:** trace-based; legacy COBOL not executed locally. Orchestration matches in-repo paragraphs; DB CALL behavior is injected via `InitialCheckDependencies`.

| Rule | Java |
|------|------|
| RULE-005 | `InitialCheckService.validateFunctionAndMedium` |
| RULE-006 | `TracedInitialCheckBackend` + F102 |
| RULE-007 | `TracedInitialCheckBackend` G001 / TFD05 exception |
| RULE-008 | `InitialCheckService.run` / K E100 path |
| RULE-010 | `TracedInitialCheckBackend` PA-Seq-No branch |
| RULE-017 | `TracedInitialCheckBackend.shouldSkipPropertyCheck` |
| RULE-018 | E000 not invoked on main path (no Java equivalent call) |

**Canary:** break `TracedInitialCheckBackend` PERM/in-progress guard or `validateFunctionAndMedium` → initial-check tests fail.

**Verify:** `cd modernized/TFC10 && mvn -q clean test`
