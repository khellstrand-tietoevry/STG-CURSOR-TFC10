# RUN_SEQUENCE — TFC10 modernization log

Chronological record of **commands run**, **decisions**, **prompts**, and **outcomes** for **STG-cursor-TFC10**. Plugin artifacts live under `analysis/TFC10/`; this file is the human-oriented journal.

### Specialist decisions (review settled)

**Review handoff:** [**`CONFIRMED.md`**](analysis/TFC10/CONFIRMED.md) (**6** settled rules) · [**`DECISIONS.md`**](analysis/TFC10/DECISIONS.md) (closed RULE-008/018; open DB boundary imports). Pop-up verdicts: [`RULE_REVIEWS.json`](analysis/TFC10/RULE_REVIEWS.json). Rule cards: [`BUSINESS_RULES.md`](analysis/TFC10/BUSINESS_RULES.md).

**Readable chat export:** [`analysis/TFC10/CURSOR_SESSION_EXPORT.md`](analysis/TFC10/CURSOR_SESSION_EXPORT.md) (conversation `dbb69360-61a9-4f0f-ace3-6e5e9d3b221c`).

**Policy:** Cap parallel Task subagents at **5** (see [`analysis/TFC10/INTENT.md`](analysis/TFC10/INTENT.md)). Repeat in chat when starting each step:

```text
Cap parallel Task subagents at 5 for this project.
```

---

## Session index

| # | Date (UTC+2) | Step | Status | Artifacts |
|---|--------------|------|--------|-----------|
| 0 | 2026-09-29 ~14:44 | Repo scaffold + `legacy/TFC10` symlink | Done | `INTENT.md`, `RUN_SEQUENCE.md`, `.cursor/rules/tfc10-modernization.mdc` |
| 1 | 2026-09-29 ~15:31 | Intent / transform Java 21 | Done | [`analysis/TFC10/INTENT.md`](analysis/TFC10/INTENT.md) |
| 2 | 2026-09-29 ~15:31 | `modernize-preflight TFC10` | Done | [`PREFLIGHT.md`](analysis/TFC10/PREFLIGHT.md) |
| 3 | 2026-09-29 ~15:31 | `modernize-assess TFC10` | Done | [`ASSESSMENT.md`](analysis/TFC10/ASSESSMENT.md) |
| 4 | 2026-09-29 ~15:31 | `modernize-map TFC10` | Done | [`topology.json`](analysis/TFC10/topology.json), [`ARCHITECTURE.mmd`](analysis/TFC10/ARCHITECTURE.mmd) |
| 5 | 2026-09-29 ~15:31 | `modernize-extract-rules TFC10` | Done (first pass) | [`rules_result.json`](analysis/TFC10/rules_result.json), [`BUSINESS_RULES.md`](analysis/TFC10/BUSINESS_RULES.md) |
| 6 | 2026-09-29 ~15:31 | `modernize-review TFC10` | Done | [`RULE_REVIEWS.json`](analysis/TFC10/RULE_REVIEWS.json), [`CONFIRMED.md`](analysis/TFC10/CONFIRMED.md), [`DECISIONS.md`](analysis/TFC10/DECISIONS.md) |
| 7 | 2026-09-29 ~15:31 | `modernize-brief TFC10` | Approved | [`MODERNIZATION_BRIEF.md`](analysis/TFC10/MODERNIZATION_BRIEF.md) |
| 8 | 2026-09-29 ~15:39 | `modernize-transform TFC10` Phase 1 | Done | [`modernized/TFC10/`](modernized/TFC10/), [`PHASE1_PLAYBOOK.md`](modernized/TFC10/PHASE1_PLAYBOOK.md) |
| 9 | 2026-09-29 ~16:38 | `modernize-verify TFC10` | Done | [`VERIFICATION.md`](analysis/TFC10/VERIFICATION.md) — **NOT PROVEN** |
| 10 | 2026-09-29 | `modernize-harden TFC10` | Done | [`SECURITY_FINDINGS.md`](analysis/TFC10/SECURITY_FINDINGS.md) |
| 11 | 2026-09-29 ~21:38 | `modernize-transform TFC10` Phase 2 | Done | [`ftfc-l100/`](modernized/TFC10/ftfc-l100/), [`PHASE2_PLAYBOOK.md`](modernized/TFC10/PHASE2_PLAYBOOK.md) |
| 12 | 2026-09-29 | `modernize-verify TFC10` (post Phase 2) | Done | [`VERIFICATION.json`](analysis/TFC10/VERIFICATION.json) — **PARTLY PROVEN** |
| 13 | 2026-09-29 ~22:00 | `modernize-transform TFC10` Phase 3 | Done | [`ftfc-l020/`](modernized/TFC10/ftfc-l020/), [`ftfc-l090/`](modernized/TFC10/ftfc-l090/), [`PHASE3_PLAYBOOK.md`](modernized/TFC10/PHASE3_PLAYBOOK.md) |
| 14 | 2026-09-29 | `modernize-verify TFC10` (post Phase 3) | Done | **PARTLY PROVEN** (5 modules) |
| 15 | 2026-09-29 ~22:00 | `modernize-transform TFC10` Phase 4 | Done | [`ftfc-f7919/`](modernized/TFC10/ftfc-f7919/), [`PHASE4_PLAYBOOK.md`](modernized/TFC10/PHASE4_PLAYBOOK.md) |
| 16 | 2026-09-29 | `modernize-verify TFC10` (post Phase 4) | Done | **PARTLY PROVEN** (6 modules) |
| 17 | 2026-09-29 ~22:00 | `modernize-transform TFC10` Phase 5 | Partial (pre-import) | [`ftfc-l050/`](modernized/TFC10/ftfc-l050/), [`PHASE5_PLAYBOOK.md`](modernized/TFC10/PHASE5_PLAYBOOK.md) |
| 18 | 2026-09-29 ~22:08 | Import **`F115L050.src`** | Done | [`PHASE5_LEGACY_IMPORT.md`](analysis/TFC10/PHASE5_LEGACY_IMPORT.md) |
| 19 | 2026-09-29 ~22:11 | `modernize-map TFC10` (re-map) | Done | [`topology.json`](analysis/TFC10/topology.json), [`TOPOLOGY.html`](analysis/TFC10/TOPOLOGY.html) |
| 20 | 2026-09-29 ~22:13 | `modernize-extract-rules TFC10` (re-pass) | Done | **19** rules, **7** P0 — [`DATA_OBJECTS.md`](analysis/TFC10/DATA_OBJECTS.md) |
| 21 | 2026-09-29 ~22:14 | `modernize-review TFC10` (re-pass) | Done | **6** confirmed, **0** open discuss |
| 22 | 2026-09-29 ~22:16 | `modernize-verify TFC10` (post Phase 5) | Done | **7× PARTLY PROVEN**, **30** tests |
| 23 | 2026-09-29 ~22:21 | `modernize-transform TFC10 ftfc-l050` | Done | `TracedInitialCheckBackend`, RULE-005/006/007/008 tests |
| 24 | 2026-09-29 ~22:32 | `modernize-verify TFC10` (post l050) | Done | **38** tests; **ftfc-l050** P0 **4/4** — [`mvn-test-20260929-verify-post-l050.log`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-verify-post-l050.log) |
| 25 | 2026-09-30 ~09:12 | Session export + RUN_SEQUENCE (TFR11-style) | Done | [`CURSOR_SESSION_EXPORT.md`](analysis/TFC10/CURSOR_SESSION_EXPORT.md), this file §25 |

---

## 0 — Repo scaffold and self-contained boundary (2026-09-29)

**Trigger:** New git repo **STG-cursor-TFC10** with legacy COBOL from inventory; no out-of-repo runtime authority.

**Chat prompts (setup):**

```text
Can you move from the 'git'-folder into a new git repo named 'STG-cursor-TFC10', can you fetch the legacy cobol code relevant for TFC10 into it?
```

```text
Now that we've fetched the code from the legacy version into this repo we should not use anything outside this repo as inspiration or source of truth.
```

```text
A colleauge posted his cursor session and the run sequence. Could you analyze it and make a plan for how to run this on TFC10?
```

```text
Execute plan step by step without pause and report back at the end with the result
```

| Time (CEST) | Key result |
|-------------|------------|
| ~14:44 | Repo created; `legacy/STG/TFC10/` snapshot; symlink `legacy/TFC10` |
| ~15:17 | Colleague **TFR11** `RUN_SEQUENCE` / session pattern analyzed; TFC10 plan |
| ~15:31 | Preflight → brief pipeline executed (steps 1–7) |

**Action:** Added [`analysis/TFC10/INTENT.md`](analysis/TFC10/INTENT.md), [`.cursor/rules/tfc10-modernization.mdc`](.cursor/rules/tfc10-modernization.mdc), repository-boundary rules in `AGENTS.md` / README.

---

## 1 — Intent: Java 21 transform, strict parity (2026-09-29)

**Command:** `/code-modernization:modernize` (system **TFC10**).

**Outcome:** Transform track, **exact legacy behavior including quirks**, target **Java 21 / Spring Boot** — see [`INTENT.md`](analysis/TFC10/INTENT.md).

---

## 2 — Preflight (2026-09-29)

**Chat prompt (command):**

```text
/code-modernization:modernize-preflight TFC10
```

**Outcome:** [`PREFLIGHT.md`](analysis/TFC10/PREFLIGHT.md) — one-hop closure under `legacy/STG/TFC10/`; **F115L050** flagged missing until step 18.

---

## 3 — Assess (2026-09-29)

**Command:** `modernize-assess TFC10`

**Outcome:** [`ASSESSMENT.md`](analysis/TFC10/ASSESSMENT.md), architecture sketch [`ARCHITECTURE.mmd`](analysis/TFC10/ARCHITECTURE.mmd).

---

## 4 — Map (2026-09-29)

**Command:** `modernize-map TFC10`

**Outcome:** [`topology.json`](analysis/TFC10/topology.json), [`TOPOLOGY.html`](analysis/TFC10/TOPOLOGY.html), call graph for H/K/L modules.

---

## 5 — Extract rules (first pass) (2026-09-29)

**Command:** `modernize-extract-rules TFC10`

**Outcome:** [`rules_result.json`](analysis/TFC10/rules_result.json), [`BUSINESS_RULES.md`](analysis/TFC10/BUSINESS_RULES.md).

---

## 6 — Review + decisions (2026-09-29)

**Command:** `modernize-review TFC10`

**Outcome:** [`RULE_REVIEWS.json`](analysis/TFC10/RULE_REVIEWS.json), [`CONFIRMED.md`](analysis/TFC10/CONFIRMED.md), [`DECISIONS.md`](analysis/TFC10/DECISIONS.md).

**Themes (see DECISIONS):**

| Topic | Decision |
|-------|----------|
| RULE-008 / F115L050 | P0 — trace COBOL via backend port, not permanent stub |
| RULE-018 | E000 update-timestamp **not** on main path (match commented COBOL) |
| F115I* DB programs | **ports/adapters** until `.src` imported into `legacy/` |

---

## 7 — Brief approved (2026-09-29)

**Command:** `modernize-brief TFC10`

**Outcome:** [`MODERNIZATION_BRIEF.md`](analysis/TFC10/MODERNIZATION_BRIEF.md) — phased transform plan (Phases 1–5).

---

## 8 — Transform Phase 1 (2026-09-29)

**Chat prompt (command):**

```text
/code-modernization:modernize-transform TFC10
```

**Outcome:** `ftfc-h100`, `ftfc-k100` under [`modernized/TFC10/`](modernized/TFC10/); [`PHASE1_PLAYBOOK.md`](modernized/TFC10/PHASE1_PLAYBOOK.md).

**Build proof:**

```bash
cd modernized/TFC10 && mvn test
```

Log: [`equivalence/mvn-logs/mvn-test-20260929-phase1.log`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-phase1.log) — **5** tests, **0** failures.

---

## 9 — Verify (post Phase 1) (2026-09-29)

**Chat prompt (command):**

```text
/code-modernization:modernize-verify TFC10
```

**Outcome:** [`VERIFICATION.md`](analysis/TFC10/VERIFICATION.md) — overall **NOT PROVEN**; legacy `ran: false`; no `equivalence/cases.json`.

---

## 10 — Harden (2026-09-29)

**Command:** `modernize-harden TFC10`

**Outcome:** [`SECURITY_FINDINGS.md`](analysis/TFC10/SECURITY_FINDINGS.md).

---

## 11–16 — Transform Phases 2–4 + verify cycles (2026-09-29 evening)

**Chat prompts (representative):**

```text
/code-modernization:modernize-transform TFC10
```

```text
/code-modernization:modernize-verify TFC10
```

| Phase | Modules | Tests (reactor) | Verify status |
|-------|---------|-----------------|---------------|
| 2 | `ftfc-l100` | 12 → 13 in unit pack | **PARTLY PROVEN** |
| 3 | `ftfc-l020`, `ftfc-l090` | 21 | **PARTLY PROVEN** (5 modules) |
| 4 | `ftfc-f7919` | 27 | **PARTLY PROVEN** (6 modules) |

Logs: [`mvn-test-20260929-phase2.log`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-phase2.log), [`phase3`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-phase3.log), [`phase4`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-phase4.log), verify logs under [`equivalence/mvn-logs/`](analysis/TFC10/equivalence/mvn-logs/).

Evidence: [`equivalence/test-runs.json`](analysis/TFC10/equivalence/test-runs.json), canaries [`equivalence/canary/`](analysis/TFC10/equivalence/canary/). `proof_pack.py` exit **1** (expected until legacy cases exist).

---

## 17 — Transform Phase 5 (initial slice) (2026-09-29)

**Outcome:** [`ftfc-l050/`](modernized/TFC10/ftfc-l050/), [`PHASE5_PLAYBOOK.md`](modernized/TFC10/PHASE5_PLAYBOOK.md) — K-gate wiring before full F115L050 source in tree.

Log: [`mvn-test-20260929-phase5.log`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-phase5.log) — **30** tests.

---

## 18 — Legacy import F115L050 (2026-09-29)

**Chat prompt:**

```text
Do this: Import F115L050.src into legacy/STG/TFC10/src/
```

**Outcome:** [`PHASE5_LEGACY_IMPORT.md`](analysis/TFC10/PHASE5_LEGACY_IMPORT.md); source under `legacy/STG/TFC10/src/F115L050.src` (read-only legacy).

---

## 19–21 — Re-map, re-rules, re-review (2026-09-29)

**Chat prompts (commands):**

```text
/code-modernization:modernize-map TFC10
```

```text
/code-modernization:modernize-extract-rules TFC10
```

```text
/code-modernization:modernize-review TFC10
```

| Step | Key result |
|------|------------|
| Re-map | Closure includes **F115L050**; updated [`topology.json`](analysis/TFC10/topology.json) |
| Re-rules | **19** rules, **7** P0; [`DATA_OBJECTS.md`](analysis/TFC10/DATA_OBJECTS.md) |
| Re-review | **6** confirmed in [`CONFIRMED.md`](analysis/TFC10/CONFIRMED.md); **0** open `discuss` |

---

## 22 — Verify (post Phase 5 import) (2026-09-29)

**Chat prompt:**

```text
/code-modernization:modernize-verify TFC10
```

**Outcome:** **7** modules **PARTLY PROVEN**; **30** tests; canary **ftfc-l050** in proof pack.

---

## 23 — Transform `ftfc-l050` (traced backend) (2026-09-29)

**Chat prompt:**

```text
/code-modernization:modernize-transform TFC10 ftfc-l050
```

**Outcome:** `TracedInitialCheckBackend`, `InitialCheckDependencies`, `InitialCheckService` (RULE-005); tests for **RULE-005/006/007/008**; K-module tests renamed for rule008/rule009.

---

## 24 — Verify (post l050 transform) (2026-09-29)

**Chat prompt:**

```text
/code-modernization:modernize-verify TFC10
```

| Metric | Value |
|--------|--------|
| Reactor tests | **38**, **0** failures |
| **ftfc-l050** P0 | **4/4** |
| Overall | **PARTLY PROVEN** (7 modules) |
| Legacy COBOL run | `ran: false` |
| Human sign-off | [`VERIFICATION.md`](analysis/TFC10/VERIFICATION.md) sign-off **blank** |

Log: [`mvn-test-20260929-verify-post-l050.log`](analysis/TFC10/equivalence/mvn-logs/mvn-test-20260929-verify-post-l050.log).

**Chat follow-ups (2026-09-29 / 2026-09-30):**

```text
is the pipeline ready?
```

```text
Can you give me a time estimate for each step in the pipeline in a table
```

**Answer (summary):** Discovery/build largely complete; verification **PARTLY PROVEN**, not full **PROVEN** — needs `equivalence/cases.json` and/or legacy run evidence, production `InitialCheckDependencies`, optional import of remaining F115I* sources, human sign-off.

---

## 25 — Session export + journal alignment (2026-09-30)

**Chat prompt:**

```text
Could you create coresponding files for TFC10 as my colleague did for TFR11 like this:
https://github.com/tietoevryfs/STG-CURSOR-TFR11/blob/main/analysis/TFR11/CURSOR_SESSION_EXPORT.md
https://github.com/tietoevryfs/STG-CURSOR-TFR11/blob/main/RUN_SEQUENCE.md
```

**Outcome:** This file expanded to TFR11-style index + per-step prompts; [`analysis/TFC10/CURSOR_SESSION_EXPORT.md`](analysis/TFC10/CURSOR_SESSION_EXPORT.md) generated from conversation `dbb69360-61a9-4f0f-ace3-6e5e9d3b221c`.

---

## Next commands

1. Wire production `InitialCheckDependencies` (Oracle/stubs) when scenarios need F115I* parity.
2. Optional: add [`equivalence/cases.json`](analysis/TFC10/equivalence/cases.json) + legacy fixtures, or sign off trace-based proof in [`VERIFICATION.md`](analysis/TFC10/VERIFICATION.md).
3. Import additional F115I* `.src` into `legacy/STG/TFC10/` if brief closure requires live DB-boundary parity (see [`DECISIONS.md`](analysis/TFC10/DECISIONS.md)).

Report: [`analysis/TFC10/REPORT.html`](analysis/TFC10/REPORT.html)
