# Phase 5 playbook — F115L050 / K initial-check

**Module:** `ftfc-l050` (`InitialCheckService`), integrated in `ftfc-k100`

**Scope:** R115L050 layout + FTFCK100 E100 mapping; backend port until `F115L050.src` is imported.

**Legacy gap:** [`analysis/TFC10/PHASE5_LEGACY_IMPORT.md`](../../analysis/TFC10/PHASE5_LEGACY_IMPORT.md)

**Verify:**

```bash
cd modernized/TFC10 && mvn test
```

**Notes:** [`ftfc-l050/TRANSFORMATION_NOTES.md`](ftfc-l050/TRANSFORMATION_NOTES.md)
