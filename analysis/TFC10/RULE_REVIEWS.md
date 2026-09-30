# Rule reviews — TFC10

Recorded via `/code-modernization:modernize-review TFC10` (flagged scope). Answers merge into the brief behavior contract; `BUSINESS_RULES.md` is not edited.

| Rule | Verdict | When | Title | Note |
|------|---------|------|-------|------|
| RULE-001 | confirmed | 2026-09-29T22:20:00+02:00 | Date plus days (F7919010) | Implement 360-day path via port/stub until F7919060 imported. |
| RULE-003 | confirmed | 2026-09-29T22:20:00+02:00 | Bank-day lookup (F7919090) | Parity via Oracle/stub matching legacy tables when exercised. |
| RULE-008 | confirmed | 2026-09-29T22:21:00+02:00 | K-module initial check calls F115L050 | P0 contract; F115L050.src in legacy; trace/implement in ftfc-l050 backend port. |
| RULE-009 | confirmed | 2026-09-29T22:20:00+02:00 | Return-Error stops K path | Match legacy: Return-Error stops K; Return-Warning handled separately per COBOL. |
| RULE-012 | confirmed | 2026-09-29T22:21:00+02:00 | Contract number via F115L020 | Parity from in-repo F115L020; production sequence via port if needed. |
| RULE-018 | confirmed | 2026-09-29T22:20:00+02:00 | F115L050 update-timestamp check disabled on main path | Java parity must keep E000 disabled on main path (match commented COBOL). |

**Superseded (pre–rule renumber):** old `RULE-003` (discuss: missing F115L050) → resolved by import + **RULE-008** confirmed; old `RULE-006` confirm → **RULE-012**.
