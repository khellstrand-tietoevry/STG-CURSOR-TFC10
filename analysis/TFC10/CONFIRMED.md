# Confirmed rules — TFC10

Rules settled during review (see [`RULE_REVIEWS.json`](RULE_REVIEWS.json)):

| Rule | Title |
|------|--------|
| RULE-001 | Date plus days (F7919010) — 360-day via port until F7919060 in-tree |
| RULE-003 | Bank-day lookup (F7919090) — tables via Oracle/stub when exercised |
| RULE-008 | K-module initial check calls F115L050 — P0; implement backend port |
| RULE-009 | Return-Error stops K path — Error vs Warning per legacy COBOL |
| RULE-012 | Contract number via F115L020 — in-repo behavior + port if needed |
| RULE-018 | Update-timestamp check disabled on F115L050 main path — match commented COBOL |

All other **P0** rules in [`BUSINESS_RULES.md`](BUSINESS_RULES.md) remain **High** confidence and need no SME gate for the brief.
