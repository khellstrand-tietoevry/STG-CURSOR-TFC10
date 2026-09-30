# TRANSFORMATION_NOTES — FTFCL100 (ftfc-l100)

**Equivalence:** trace/spec-based — legacy L-module not executable locally (EXEC SQL / missing F115ISR0 source).

## Mapping (Phase 2 slice)

| Legacy | Java |
|--------|------|
| `FTFCL100.src:131-137` A000-Main | `LModuleLoader.load` |
| `FTFCL100.src:146-153` B000-Initialize | implicit clean state |
| `FTFCL100.src:158-160` D000-Preparations | sys code rel + timestamp ports |
| `FTFCL100.src:168-210` D100-Read-Sys-Code-Rel | `SysCodeRelationPort` |
| `FTFCL100.src:217+` D200-Read-Timestamp | `TimestampPort` |
| E000-Processing / DB CALLs | **not migrated** this phase |

## Deviations

- F115ISR0 / F7919070 replaced with injectable ports (stubs in tests).

## Canary (2026-09-29)

Changed unique-row check from `> 1` to `> 0` → **1** test failed (`rejectsNonUniqueStatusMapping`); restored.

## Executed equivalence cases

**5** L-module unit tests + integration via H orchestrator.

## Follow-ups

- Phase 2b: E000 processing, F115IMC0/ICA0/IEI0 ports.
- Import missing program sources into `legacy/` before full parity.
