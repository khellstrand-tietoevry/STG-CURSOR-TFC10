# Modernization brief — TFC10

**Target:** Java 21 / Spring Boot 3.x  
**Track:** Transform (strangler-fig)  
**Status:** **Approved** (2026-09-29 — plan execution session)

## Phase plan

| Phase | Scope | Exit criteria |
|-------|--------|---------------|
| 1 | **FTFCK100** + **FTFCH100** (K + H skeleton) | `mvn test` green; P0 H→K→L ordering tested; **F115L050** stubbed or scoped out with DECISIONS |
| 2 | **FTFCL100** (L read path) | Tests for successful L invocation after K |
| 3 | **F115L020**, **F115L090** | Contract number / work-charge helpers |
| 4 | **F7919010/9070/9090/9270** | Date/calendar helpers |
| 5 | Import **F115L050** + DB boundary modules into `legacy/` then K initial-check parity | `modernize-verify` re-run |

## Behavior contract

All **P0** rules in [`BUSINESS_RULES.md`](BUSINESS_RULES.md) must be named by executed tests before **PROVEN**.

## Approval block

- [x] Full plan approved for phased transform (session 2026-09-29)
- [x] SME closed flagged items in [`DECISIONS.md`](DECISIONS.md) / [`RULE_REVIEWS.json`](RULE_REVIEWS.json) (2026-09-29 review)

## Next command

Import `F115L050.src` into `legacy/` (see `PHASE5_LEGACY_IMPORT.md`), then `modernize-verify TFC10` and backend parity for `ftfc-l050`.
