# Security findings — TFC10 legacy (scan summary)

**Date:** 2026-09-29  
**Scope:** In-repo one-hop COBOL only; no automated SAST tool run.

| ID | Severity | Finding | Remediation |
|----|----------|---------|-------------|
| SEC-001 | Info | Legacy uses DISPLAY/trace copybooks — avoid logging sensitive contract fields in Java | Redact in adapter layer |
| SEC-002 | Info | No secrets in COBOL source reviewed | N/A |

No patch applied to `legacy/` (frozen).
