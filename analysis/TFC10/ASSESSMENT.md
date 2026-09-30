# Assessment — TFC10

**Date:** 2026-09-29  
**Pattern:** **Transform** → Java 21 / Spring Boot  
**Estate size:** Small (10 programs, 40 copybooks, ~12k lines in tree)

## Summary

TFC10 Create Hovedkontrakt is a classic **Servo H/K/L** STG: **FTFCH100** orchestrates, **FTFCK100** validates/controls, **FTFCL100** loads party/data. Helpers (`F115L020`, `F115L090`, `F7919*`) support contract number and dates. Copybooks carry DB2 DCLGEN-style layouts (`R115*`, `R791*`).

## Complexity drivers

- Large K-module (**FTFCK100**, ~1.4k lines) with many validation branches and external CALLs.
- Decimal comma (`Decimal-Point Is Comma`) — Java must preserve numeric formatting semantics in parity tests.
- **Missing local sources** for 17 external CALL targets (F115L050 now in-tree) — highest risk for DB boundary gaps.

## Security / debt (high level)

- Legacy COBOL + implicit DB2 access via called modules; no local SQL in one-hop slice for most helpers.
- Error/trace copybooks (`R7918030`, `R791TRAC`) — ensure no credential leakage in logs when ported.

## Recommendation

Proceed with **transform** in phases: **H + K (without unresolved F115L050 gate)** → **L** → helpers, importing missing CALL targets into `legacy/` before any phase that executes those paths.

## Next command

`modernize-map TFC10`
