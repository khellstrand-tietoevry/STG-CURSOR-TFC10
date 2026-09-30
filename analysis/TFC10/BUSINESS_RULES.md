# Business Rules — TFC10

At extraction: 19 confirmed rules (7 P0); later steps may add or correct rules below. Each citation was checked by a second agent that read the cited lines; 1 candidate rules were refuted and left out.

| ID | Name | Category | Priority | Source | Confidence |
|---|---|---|---|---|---|
| RULE-001 | Date plus days (F7919010) | Calculation | P1 | `legacy/STG/TFC10/src/F7919010.src:7` | Medium |
| RULE-002 | Machine timestamp (F7919070) | Calculation | P1 | `legacy/STG/TFC10/src/F7919070.src:181-202` | High |
| RULE-003 | Bank-day lookup (F7919090) | Calculation | P1 | `legacy/STG/TFC10/src/F7919090.src:7` | Medium |
| RULE-004 | Timestamp format conversion (F7919270) | Calculation | P1 | `legacy/STG/TFC10/src/F7919270.src:6` | High |
| RULE-005 | F115L050 requires function and medium | Validation | P0 | `legacy/STG/TFC10/src/F115L050.src:164-173` | High |
| RULE-006 | Invalid status and operation-type combination | Validation | P0 | `legacy/STG/TFC10/src/F115L050.src:335-349` | High |
| RULE-007 | Permanent status blocks in-progress main contract | Validation | P0 | `legacy/STG/TFC10/src/F115L050.src:420-430` | High |
| RULE-008 | K-module initial check calls F115L050 | Validation | P0 | `legacy/STG/TFC10/src/FTFCK100.src:1286-1288` | High |
| RULE-009 | Return-Error stops K path | Validation | P0 | `legacy/STG/TFC10/src/FTFCK100.src:674` | Medium |
| RULE-010 | F115L050 PA-Seq-No zero skips amount reads | Validation | P1 | `legacy/STG/TFC10/src/F115L050.src:106-114` | High |
| RULE-011 | F115L050 linkage version conversion | Validation | P1 | `legacy/STG/TFC10/src/F115L050.src:466-515` | High |
| RULE-012 | Contract number via F115L020 | Lifecycle | P1 | `legacy/STG/TFC10/src/F115L020.src:14` | Medium |
| RULE-013 | F115L050 main-contract table version ACTUAL vs WORK | Lifecycle | P1 | `legacy/STG/TFC10/src/F115L050.src:95-103` | High |
| RULE-014 | Shadow work-charge cleanup (F115L090) | Lifecycle | P1 | `legacy/STG/TFC10/src/F115L090.src:119-209` | High |
| RULE-015 | H invokes K then L on success | Policy | P0 | `legacy/STG/TFC10/src/FTFCH100.src:139-172` | High |
| RULE-016 | Decimal point is comma | Policy | P0 | `legacy/STG/TFC10/src/FTFCH100.src:20` | High |
| RULE-017 | F115L050 skips property check for read and SHOW | Policy | P1 | `legacy/STG/TFC10/src/F115L050.src:137-142` | High |
| RULE-018 | F115L050 update-timestamp check disabled on main path | Policy | P2 | `legacy/STG/TFC10/src/F115L050.src:119-123` | Medium |
| RULE-019 | H-module must not be hand-edited | Policy | P2 | `legacy/STG/TFC10/src/FTFCH100.src:10` | High |

## Calculation

### RULE-001: Date plus days (F7919010)
**Category:** Calculation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F7919010.src:7`
**Plain English:** F7919010 returns a date offset from Gregorian yyyyMMdd inputs.
**Specification:**
  Given Gregorian yyyyMMdd and day/month/year offsets
  When  F7919010 runs
  Then  Output date is returned per calendar mode (360-day path when requested in copybook flags)
**Parameters:** {}
**Confidence:** Medium — Confirm 360-day calendar branch when F7919060 is not in-tree.

### RULE-002: Machine timestamp (F7919070)
**Category:** Calculation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F7919070.src:181-202`
**Plain English:** F7919070 obtains machine timestamp via F7919071 and COBOL date via F7919072.
**Specification:**
  Given F7919070 invoked
  When  Timestamp paragraph runs
  Then  W-DB2-TMS and W-COB-Date are populated from nested calls
**Parameters:** {}
**Edge cases handled:** F7919071/F7919072 not in legacy tree
**Confidence:** High

### RULE-003: Bank-day lookup (F7919090)
**Category:** Calculation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F7919090.src:7`
**Plain English:** F7919090 evaluates bank-day and holiday semantics for a calendar date.
**Specification:**
  Given A calendar date in R7919090 layout
  When  F7919090 runs
  Then  Bank-day flag, weekday, and days-to-next-bank-day are returned
**Parameters:** {}
**Confidence:** Medium — Confirm holiday table source when DB tables are not in one-hop tree.

### RULE-004: Timestamp format conversion (F7919270)
**Category:** Calculation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F7919270.src:6`
**Plain English:** F7919270 converts date and time components to DB2-style and compact timestamp fields.
**Specification:**
  Given Date and time components in R7919270
  When  F7919270 runs
  Then  Equivalent DB2-style and compact timestamp fields are populated
**Parameters:** {}
**Confidence:** High

## Validation

### RULE-005: F115L050 requires function and medium
**Category:** Validation
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/F115L050.src:164-173`
**Plain English:** Initial check abends if Function or Medium is spaces.
**Specification:**
  Given F115L050 C100-Initialize-Output runs
  When  R115L050-Function or R115L050-Medium is spaces
  Then  Message TF-SY-FUNC-AND-MEDIUM is raised, paragraph C102, Z900-Abend
**Parameters:** {"messageCode":"TF-SY-FUNC-AND-MEDIUM","paragraph":"C102"}
**Confidence:** High

### RULE-006: Invalid status and operation-type combination
**Category:** Validation
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/F115L050.src:335-349`
**Plain English:** F115L050 rejects invalid STATUS to OPERATION-TYPE relation from F115ISR0.
**Specification:**
  Given F100-Read-Sys-Code-Rel completed
  When  F115ISR0 returns error for VALID-ST-OP relation
  Then  TF-SY-INVALID-STATUS-OPERTYP is logged with operation type and status variables, paragraph F102, Z700-Error-AE
**Parameters:** {"messageCode":"TF-SY-INVALID-STATUS-OPERTYP","paragraph":"F102"}
**Edge cases handled:** Contract-type split adjusts From/To contract type on R115ISR0
**Confidence:** High

### RULE-007: Permanent status blocks in-progress main contract
**Category:** Validation
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/F115L050.src:420-430`
**Plain English:** Permanent status (PERM in properties) with in-progress main contract is rejected except for function TFD05.
**Specification:**
  Given G000-Check-Properties runs after status properties read
  When  R115ISC0-Property contains PERM, R115IMC0-In-Progress-Yes, and R115L050-Function is not TFD05
  Then  TF-SY-MC-INPROG-Y error, paragraph G001, Z700-Error-AE
**Parameters:** {"messageCode":"TF-SY-MC-INPROG-Y","paragraph":"G001","exceptionFunction":"TFD05"}
**Confidence:** High

### RULE-008: K-module initial check calls F115L050
**Category:** Validation
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/FTFCK100.src:1286-1288`
**Plain English:** Before continuing, K initializes R115L050-Initial-Check and CALLs F115L050.
**Specification:**
  Given K-module reaches initial-check paragraph
  When  Initial-check executes
  Then  F115L050 is called with R115L050-Initial-Check and R7919999-Error-Area
**Parameters:** {}
**Edge cases handled:** F115L050.src is in legacy/STG/TFC10 as of 2026-09-29
**Confidence:** High

### RULE-009: Return-Error stops K path
**Category:** Validation
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/FTFCK100.src:674`
**Plain English:** When R7919999-Return-Error is set after a sub-call, K-module error handling applies.
**Specification:**
  Given A nested CALL sets error area
  When  R7919999-Return-Error is true
  Then  K does not treat the path as success
**Parameters:** {}
**Confidence:** Medium — Confirm exact K behavior when only Return-Warning is set versus Return-Error.

### RULE-010: F115L050 PA-Seq-No zero skips amount reads
**Category:** Validation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L050.src:106-114`
**Plain English:** When PA sequence number is zero, status comes from main contract only; otherwise contract and part amounts are read.
**Specification:**
  Given Main contract read completed
  When  R115L050-PA-Seq-No = 0
  Then  Ws-Status moves from R115IMC0-Status without C250/C300 amount reads
**Parameters:** {}
**Edge cases handled:** Non-zero PA-Seq-No triggers F115ICA0 and F115IPA0 calls
**Confidence:** High

### RULE-011: F115L050 linkage version conversion
**Category:** Validation
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L050.src:466-515`
**Plain English:** Input/output areas must match R115L050 version and length or F115L999 converts via INPUT/OUTPUT.
**Specification:**
  Given F115L050 entry with L-Ext-IO-Area
  When  First 8 bytes or length do not match current R115L050 version layout
  Then  F115L999 is called for INPUT or OUTPUT conversion; abend paragraphs ZI03 or ZO01 on abend
**Parameters:** {}
**Confidence:** High

## Lifecycle

### RULE-012: Contract number via F115L020
**Category:** Lifecycle
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L020.src:14`
**Plain English:** F115L020 generates contract numbers when invoked from L with R115L020-Gen-Contract-No.
**Specification:**
  Given F115L020 invoked
  When  Generation path runs
  Then  Contract number field populated per R115L020 layout
**Parameters:** {}
**Confidence:** Medium — Confirm production contract-number sequence rules beyond in-repo mock paths.

### RULE-013: F115L050 main-contract table version ACTUAL vs WORK
**Category:** Lifecycle
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L050.src:95-103`
**Plain English:** Initial check reads main contract twice when in-progress, switching table version WORK then ACTUAL.
**Specification:**
  Given C000-Interface after initialize
  When  R115IMC0-In-Progress-Yes after first F115IMC0 read
  Then  Ws-Table-Version moves to WORK and C200-Read-Main-Contract runs again; otherwise ACTUAL
**Parameters:** {"versions":["ACTUAL","WORK"]}
**Confidence:** High

### RULE-014: Shadow work-charge cleanup (F115L090)
**Category:** Lifecycle
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L090.src:119-209`
**Plain English:** F115L090 deletes in-work part and main charge shadows for a contract key.
**Specification:**
  Given F115L090 invoked for a contract key
  When  In-work part- and main-charge shadows exist
  Then  Each shadow row is deleted via F115IPC0 and F115ICH0 until no more rows
**Parameters:** {}
**Edge cases handled:** F115L999 on abend paths
**Confidence:** High

## Policy

### RULE-015: H invokes K then L on success
**Category:** Policy
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/FTFCH100.src:139-172`
**Plain English:** H-module calls FTFCK100 with STG header and function input; on success calls FTFCL100 with same linkage.
**Specification:**
  Given FTFCH100 main path after initialization
  When  K-module returns without error
  Then  L-module is invoked before H completes
**Parameters:** {}
**Edge cases handled:** Return-Warning-Goback short-circuits L
**Confidence:** High

### RULE-016: Decimal point is comma
**Category:** Policy
**Priority:** P0
**Source:** `legacy/STG/TFC10/src/FTFCH100.src:20`
**Plain English:** COBOL uses European decimal comma for numeric literals and edited pictures across H/K/L and helpers.
**Specification:**
  Given Any module with Special-Names Decimal-Point Is Comma
  When  Numeric edited output is produced
  Then  Decimal-Point Is Comma applies
**Parameters:** {}
**Confidence:** High

### RULE-017: F115L050 skips property check for read and SHOW
**Category:** Policy
**Priority:** P1
**Source:** `legacy/STG/TFC10/src/F115L050.src:137-142`
**Plain English:** G000-Check-Properties is skipped when function position 3 is R and operation type is SHOW.
**Specification:**
  Given Sys-code reads completed
  When  R115L050-Function(3:1) = R and R115L050-Operation-Type = SHOW
  Then  G000-Check-Properties is not performed
**Parameters:** {}
**Confidence:** High

### RULE-018: F115L050 update-timestamp check disabled on main path
**Category:** Policy
**Priority:** P2
**Source:** `legacy/STG/TFC10/src/F115L050.src:119-123`
**Plain English:** E000-Check-Upd-Timestamp call is commented out in C000-Interface (July test note); E000 section still implements mismatch error E001.
**Specification:**
  Given F115L050 C000-Interface
  When  Production code path as checked in
  Then  Perform E000-Check-Upd-Timestamp is not executed from C000 (commented)
**Parameters:** {"disabledParagraph":"E000","activeSection":"E000-Check-Upd-Timestamp:281-292 when invoked"}
**Edge cases handled:** If re-enabled, mismatch raises TF-SY-UPD-DATED-BY-VAR1 paragraph E001
**Suspected defect:** Comment suggests temporary disable; parity may depend on which branch is live.
**Confidence:** Medium — Was the July disable intentional for all environments or test-only?

### RULE-019: H-module must not be hand-edited
**Category:** Policy
**Priority:** P2
**Source:** `legacy/STG/TFC10/src/FTFCH100.src:10`
**Plain English:** Header comment states FTFCH100 must not be modified by human hands.
**Specification:**
  Given Maintenance policy
  When  Changing H source
  Then  Changes are generator-owned not manual
**Parameters:** {}
**Confidence:** High

## Rules requiring SME confirmation

- **RULE-001** (Medium): Confirm 360-day calendar branch when F7919060 is not in-tree.
- **RULE-003** (Medium): Confirm holiday table source when DB tables are not in one-hop tree.
- **RULE-009** (Medium): Confirm exact K behavior when only Return-Warning is set versus Return-Error.
- **RULE-012** (Medium): Confirm production contract-number sequence rules beyond in-repo mock paths.
- **RULE-018** (Medium): Was the July disable intentional for all environments or test-only?
