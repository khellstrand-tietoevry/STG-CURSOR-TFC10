# Verification: TFC10

Written by `scripts/proof_pack.py` on 2026-09-29 20:33 UTC. Each module's verdict is computed from the evidence files by the fixed rules at the end of this page. No model's opinion is part of it.

**Overall: PARTLY PROVEN** (7 partly proven)


Legacy source: `legacy/TFC10` (a link to `/Users/kristofferhellstrand/git/tfi-workspace/STG-cursor-TFC10/legacy/STG/TFC10`). Could it run here: no. The source was checked by modification time, and no version-control tool was run.

| Module | Track | Verdict | Tests executed | Failed | P0 rules tested | Same behavior | Fresh inputs |
|---|---|---|---|---|---|---|---|
| ftfc-f7919 | rewrite | **PARTLY PROVEN** | 6 | 0 | 2 of 2 | not proven | not proven |
| ftfc-h100 | rewrite | **PARTLY PROVEN** | 4 | 0 | 0 of 0 | not proven | not proven |
| ftfc-k100 | rewrite | **PARTLY PROVEN** | 4 | 0 | 2 of 2 | not proven | not proven |
| ftfc-l020 | rewrite | **PARTLY PROVEN** | 4 | 0 | 1 of 1 | not proven | not proven |
| ftfc-l050 | rewrite | **PARTLY PROVEN** | 11 | 0 | 4 of 4 | not proven | not proven |
| ftfc-l090 | rewrite | **PARTLY PROVEN** | 4 | 0 | 1 of 1 | not proven | not proven |
| ftfc-l100 | rewrite | **PARTLY PROVEN** | 5 | 0 | 0 of 0 | not proven | not proven |

## ftfc-f7919: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-f7919`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 6 test(s) executed, 0 failed, 0 skipped.
- Rules traced: All 2 P0 rule(s) this module answers for are backed by a test that ran and passed.
- Canary: 1 canary run(s) shown by a result file or log; the first (BankDayCalculator: always treat weekday as bank day (ignore holidays)) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-f7919/target/surefire-reports/TEST-no.tieto.tfc10.f7919.F7919HelpersTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 6 test(s) executed, 0 failed, 0 skipped. |
| Rules traced | pass | All 2 P0 rule(s) this module answers for are backed by a test that ran and passed. |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (BankDayCalculator: always treat weekday as bank day (ignore holidays)) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-f7919/target/surefire-reports/TEST-no.tieto.tfc10.f7919.F7919HelpersTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 6, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 6 executed, 0 failed, 0 skipped, 1 result files written 2026-09-29 20:33 UTC

### P0 business rules

| Rule | Name | Confidence | Result | Tests | Code | A test that names it |
|---|---|---|---|---|---|---|
| RULE-008 | K-module initial check calls F115L050 | High | tested | 2 | 1 | `modernized/TFC10/ftfc-f7919/src/test/java/no/tieto/tfc10/f7919/F7919HelpersTest.java:15` |
| RULE-009 | Return-Error stops K path | Medium | tested | 1 | 1 | `modernized/TFC10/ftfc-f7919/src/test/java/no/tieto/tfc10/f7919/F7919HelpersTest.java:31` |

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-h100: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-h100`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 4 test(s) executed, 0 failed, 0 skipped. Note: Post-L050 verify; full reactor clean test 2026-09-29.
- Rules traced: No rule this module answers for is rated P0 (1 rule(s) counted).
- Canary: 1 canary run(s) shown by a result file or log; the first (HModuleOrchestrator: skip real L load (always return LResult.ok)) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-h100/target/surefire-reports/TEST-no.tieto.tfc10.h.HModuleOrchestratorIntegrationTest.xml, analysis/TFC10/equivalence/canary/ftfc-h100/target/surefire-reports/TEST-no.tieto.tfc10.h.HM…
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 4 test(s) executed, 0 failed, 0 skipped. Note: Post-L050 verify; full reactor clean test 2026-09-29. |
| Rules traced | pass | No rule this module answers for is rated P0 (1 rule(s) counted). |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (HModuleOrchestrator: skip real L load (always return LResult.ok)) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-h100/target/surefire-reports/TEST-no.tieto.tfc10.h.HModuleOrchestratorIntegrationTest.xml, analysis/TFC10/equivalence/canary/ftfc-h100/target/surefire-reports/TEST-no.tieto.tfc10.h.HModuleOrc… |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 4, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 4 executed, 0 failed, 0 skipped, 2 result files written 2026-09-29 20:33 UTC. Note: Post-L050 verify; full reactor clean test 2026-09-29.

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-k100: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-k100`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 4 test(s) executed, 0 failed, 0 skipped.
- Rules traced: All 2 P0 rule(s) this module answers for are backed by a test that ran and passed.
- Canary: 1 canary run(s) shown by a result file or log; the first (KModuleValidator: ignore initial-check error flag) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-k100/target/surefire-reports/TEST-no.tieto.tfc10.k.KModuleValidatorTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 4 test(s) executed, 0 failed, 0 skipped. |
| Rules traced | pass | All 2 P0 rule(s) this module answers for are backed by a test that ran and passed. |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (KModuleValidator: ignore initial-check error flag) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-k100/target/surefire-reports/TEST-no.tieto.tfc10.k.KModuleValidatorTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 4, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 4 executed, 0 failed, 0 skipped, 1 result files written 2026-09-29 20:32 UTC

### P0 business rules

| Rule | Name | Confidence | Result | Tests | Code | A test that names it |
|---|---|---|---|---|---|---|
| RULE-008 | K-module initial check calls F115L050 | High | tested | 1 | 0 | `modernized/TFC10/ftfc-k100/src/test/java/no/tieto/tfc10/k/KModuleValidatorTest.java:32` |
| RULE-009 | Return-Error stops K path | Medium | tested | 1 | 0 | `modernized/TFC10/ftfc-k100/src/test/java/no/tieto/tfc10/k/KModuleValidatorTest.java:23` |

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-l020: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-l020`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 4 test(s) executed, 0 failed, 0 skipped.
- Rules traced: All 1 P0 rule(s) this module answers for are backed by a test that ran and passed.
- Canary: 1 canary run(s) shown by a result file or log; the first (ContractRangeRules: disable primary range exhaustion check) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l020/target/surefire-reports/TEST-no.tieto.tfc10.l020.ContractNumberGeneratorTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 4 test(s) executed, 0 failed, 0 skipped. |
| Rules traced | pass | All 1 P0 rule(s) this module answers for are backed by a test that ran and passed. |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (ContractRangeRules: disable primary range exhaustion check) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l020/target/surefire-reports/TEST-no.tieto.tfc10.l020.ContractNumberGeneratorTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 4, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 4 executed, 0 failed, 0 skipped, 1 result files written 2026-09-29 20:33 UTC

### P0 business rules

| Rule | Name | Confidence | Result | Tests | Code | A test that names it |
|---|---|---|---|---|---|---|
| RULE-006 | Invalid status and operation-type combination | High | tested | 2 | 1 | `modernized/TFC10/ftfc-l020/src/test/java/no/tieto/tfc10/l020/ContractNumberGeneratorTest.java:33` |

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-l050: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-l050`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 11 test(s) executed, 0 failed, 0 skipped. Note: TracedInitialCheckBackend + RULE-005/006/007 tests
- Rules traced: All 4 P0 rule(s) this module answers for are backed by a test that ran and passed.
- Canary: 1 canary run(s) shown by a result file or log; the first (InitialCheckService: ignore backend.error() return path) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l050/target/surefire-reports/TEST-no.tieto.tfc10.l050.InitialCheckServiceTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 11 test(s) executed, 0 failed, 0 skipped. Note: TracedInitialCheckBackend + RULE-005/006/007 tests |
| Rules traced | pass | All 4 P0 rule(s) this module answers for are backed by a test that ran and passed. |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (InitialCheckService: ignore backend.error() return path) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l050/target/surefire-reports/TEST-no.tieto.tfc10.l050.InitialCheckServiceTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 11, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 11 executed, 0 failed, 0 skipped, 2 result files written 2026-09-29 20:32 UTC. Note: TracedInitialCheckBackend + RULE-005/006/007 tests

### P0 business rules

| Rule | Name | Confidence | Result | Tests | Code | A test that names it |
|---|---|---|---|---|---|---|
| RULE-005 | F115L050 requires function and medium | High | tested | 2 | 4 | `modernized/TFC10/ftfc-l050/src/test/java/no/tieto/tfc10/l050/InitialCheckServiceTest.java:40` |
| RULE-006 | Invalid status and operation-type combination | High | tested | 1 | 0 | `modernized/TFC10/ftfc-l050/src/test/java/no/tieto/tfc10/l050/TracedInitialCheckBackendTest.java:15` |
| RULE-007 | Permanent status blocks in-progress main contract | High | tested | 2 | 0 | `modernized/TFC10/ftfc-l050/src/test/java/no/tieto/tfc10/l050/TracedInitialCheckBackendTest.java:24` |
| RULE-008 | K-module initial check calls F115L050 | High | tested | 2 | 1 | `modernized/TFC10/ftfc-l050/src/test/java/no/tieto/tfc10/l050/InitialCheckServiceTest.java:17` |

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-l090: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-l090`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 4 test(s) executed, 0 failed, 0 skipped.
- Rules traced: All 1 P0 rule(s) this module answers for are backed by a test that ran and passed.
- Canary: 1 canary run(s) shown by a result file or log; the first (ShadowChargeCleanup: ignore part-charge delete errors) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l090/target/surefire-reports/TEST-no.tieto.tfc10.l090.ShadowChargeCleanupTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 4 test(s) executed, 0 failed, 0 skipped. |
| Rules traced | pass | All 1 P0 rule(s) this module answers for are backed by a test that ran and passed. |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (ShadowChargeCleanup: ignore part-charge delete errors) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l090/target/surefire-reports/TEST-no.tieto.tfc10.l090.ShadowChargeCleanupTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 4, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 4 executed, 0 failed, 0 skipped, 1 result files written 2026-09-29 20:33 UTC

### P0 business rules

| Rule | Name | Confidence | Result | Tests | Code | A test that names it |
|---|---|---|---|---|---|---|
| RULE-007 | Permanent status blocks in-progress main contract | High | tested | 1 | 1 | `modernized/TFC10/ftfc-l090/src/test/java/no/tieto/tfc10/l090/ShadowChargeCleanupTest.java:47` |

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## ftfc-l100: PARTLY PROVEN

Track: rewrite. Folder: `modernized/TFC10/ftfc-l100`. Checked 2026-09-29 20:33 UTC.

**What is missing or wrong**

- Same behavior: No equivalence cases were recorded: the new code was never compared with the legacy output.
- Fresh inputs: The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN.

**What passed**

- Tests ran: 5 test(s) executed, 0 failed, 0 skipped.
- Rules traced: No rule this module answers for is rated P0 (1 rule(s) counted).
- Canary: 1 canary run(s) shown by a result file or log; the first (LModuleLoader unique-row check: rel.totalRows() > 1 → >= 1) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l100/target/surefire-reports/TEST-no.tieto.tfc10.l.LModuleLoaderTest.xml).
- Source untouched: legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run.

| Check | Result | Detail |
|---|---|---|
| Tests ran | pass | 5 test(s) executed, 0 failed, 0 skipped. |
| Rules traced | pass | No rule this module answers for is rated P0 (1 rule(s) counted). |
| Same behavior | not proven | No equivalence cases were recorded: the new code was never compared with the legacy output. |
| Fresh inputs | not proven | The legacy could not run here (H/K/L cluster requires mainframe DB2 and external F115I* modules; COBOL not executed for equivalence here), so the proof is trace-based: no fresh-input comparison was possible and the verdict cannot be PROVEN. |
| Canary | pass | 1 canary run(s) shown by a result file or log; the first (LModuleLoader unique-row check: rel.totalRows() > 1 → >= 1) made 1 more test(s) fail than the clean run (analysis/TFC10/equivalence/canary/ftfc-l100/target/surefire-reports/TEST-no.tieto.tfc10.l.LModuleLoaderTest.xml). |
| Source untouched | pass | legacy/TFC10 is untouched: no file changed after the analysis started (51 files compared). Checked by modification time, and no version-control tool was run. |

tests executed: 5, failed 0, skipped 0

- unit tests: `cd modernized/TFC10 && mvn -q clean test` (from result files): 5 executed, 0 failed, 0 skipped, 1 result files written 2026-09-29 20:32 UTC

Inputs left out of the fresh check: End-to-end COBOL execution for FTFCH100/FTFCK100/FTFCL100/F115L050; Fresh-input legacy vs Java comparison (legacy.ran false); equivalence/cases.json not present; F115IMC0/F115ICA0/F115IPA0 and other DB boundary CALL targets without .src; RULE-016 decimal-comma characterization beyond COBOL Special-Names declaration

### Waiting for a person

Claude never ticks these. A person decides them.

- The brief lists no unticked criterion for this module.

### What this does not prove

- It does not prove behavior on inputs nobody tried: the equivalence cases and the fresh inputs are samples, not the whole input space.
- The legacy could not run here, so the comparison rests on recorded outputs and traces. It is only as good as that recording.
- It does not cover speed, capacity, security, concurrency, or anything the tests and cases do not exercise.
- It traces only the rules that were extracted: behavior nobody wrote down as a rule has no rule to trace.
- A test that names a rule shows the rule is mentioned, not that the test is a good one. The canary shows only that some test can fail.
- It does not replace the review and sign-off of the people who own the system.

## Sign-off

A person fills this in. Claude leaves it blank.

| | |
|---|---|
| Name | ________________ |
| Role | ________________ |
| Date | __________ |
| Decision | accept / accept with conditions / reject |

## How each verdict is computed

PROVEN needs every check to pass: the six below, and for an uplift three more (7 to 9). NOT PROVEN when any check fails. PARTLY PROVEN when nothing failed but a check could not pass.

1. Tests ran: at least one test executed in a fresh run, none failed, none was skipped without a reason, and every result file is newer than the code. The counts must be read by this script from result files or a saved raw runner log; counts only typed in cannot reach PROVEN.
2. Rules traced (rewrite and reimagine): every P0 rule the module answers for is backed by a test that ran and passed: its id is in the test or class name of a result this script parsed, or a test file names it on a line not marked skipped or pending and that file's class ran and passed. A rule named only by skipped, pending or failing tests is named, not run; one only the notes name is claimed. Neither is tested.
3. Same behavior: the development cases, judged again by compare.py, executed at least one case and none differs or is missing (a difference a person approved is allowed and listed); for an uplift, no regression, no new failure, no missing test and no drop in executed tests against BASELINE.md.
4. Fresh inputs: at least 10 new inputs, none with the same output as a development case, ran on the legacy and the new code with no difference. Needed whenever the legacy could run here; when it could not, the proof is trace-based and the best verdict is PARTLY PROVEN.
5. Canary: a deliberate one-line break whose own result file or saved runner log shows failing tests. A line in the notes is a claim.
6. Source untouched: no file under legacy/<system> is newer than the analysis (PREFLIGHT.md), by a plain file walk; no version-control tool is run.
7. Baseline measured (uplift): the old version's numbers in BASELINE.md come from per-test result files, a per-test results file or a raw runner log that this script parsed (in analysis/<system>/baseline/, or on a Recorded: or Machine-readable: line of BASELINE.md), and the typed table agrees with them. A table typed by hand, or sources that disagree, cannot reach PROVEN.
8. Tests kept (uplift): no test file of the untouched legacy tree is missing from the working copy, no more than 25% of them changed, and the legacy tree has test files to compare against (walked by file, no process is run). The list is for a person to review; weakened assertions cannot be detected, only that files changed.
9. Deltas covered (uplift): every Behavioral-silent delta in DELTA_CATALOG.md has its site's file named, as a whole word, by some test file of the working copy. A name is not proof that the test exercises the change.
Open questions, unticked criteria and the sign-off are for a person. They never change the verdict.
A folder that holds only test code and the files that build it is test tooling: it is listed, not judged, and not counted.
