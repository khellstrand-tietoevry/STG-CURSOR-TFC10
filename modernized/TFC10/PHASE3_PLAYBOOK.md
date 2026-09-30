# Phase 3 playbook — F115L020 / F115L090

**Modules:** `ftfc-l020` (`ContractNumberGenerator`), `ftfc-l090` (`ShadowChargeCleanup`)

**Scope:** Contract-number range logic and shadow charge purge orchestration with DB/CALL ports (no F115ISC0/F115IPC0/F115ICH0 in tree).

**Verify:**

```bash
cd modernized/TFC10 && mvn test
```

**Notes:** [`ftfc-l020/TRANSFORMATION_NOTES.md`](ftfc-l020/TRANSFORMATION_NOTES.md), [`ftfc-l090/TRANSFORMATION_NOTES.md`](ftfc-l090/TRANSFORMATION_NOTES.md)

**Not in this phase:** wiring into `LModuleLoader` (FTFCL100 E000 paths); import missing CALL targets into `legacy/` for parity.
