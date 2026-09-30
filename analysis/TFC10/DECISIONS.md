# Decisions — TFC10

## F115L050 initial-check gate (RULE-008)

**Status:** Closed (review 2026-09-29). K-module **CALLs F115L050**; `F115L050.src` is in `legacy/STG/TFC10/src/`. **Decision:** P0 parity — `ftfc-l050` / `F115L050BackendPort` must trace imported COBOL (not a permanent stub).

## F115L050 update-timestamp (RULE-018)

**Status:** Closed (review 2026-09-29). **Decision:** Java matches legacy **main path** — do **not** invoke E000 from C000 (Perform remains commented in source). Re-enable only if legacy source is changed.

## Missing DB boundary programs

**Status:** Open for transform. CALL targets `F115ICA0`, `F115IMC0`, `F115ISC0`, `F115IPA0`, etc. have no `.src` in this repo. **Decision:** **ports/adapters** in Java until sources are imported into `legacy/`.

## Calendar helpers (RULE-001, RULE-003)

**Status:** Closed (review 2026-09-29). **Decision:** Implement in-repo F7919 logic; 360-day (F7919060) and holiday tables via **ports/stubs** aligned with Oracle when scenarios exercise them.

## Contract number (RULE-012)

**Status:** Closed (review 2026-09-29). **Decision:** Parity from in-repo **F115L020**; production numbering semantics via port if scenarios require live sequence data.
