# F115L090 — transformation notes

| Legacy | Java |
|--------|------|
| `F115L090.src` | `ShadowChargeCleanup` |
| `F115IPC0` part-charge cursor | `PartChargePort` |
| `F115ICH0` main-charge cursor | `MainChargePort` |
| Version I/O (`F115L999`) | Not migrated |

Loop shape matches C000: find-first then delete until iterator exhausted (COBOL `Return-Error` on next find).

| Rule | Java |
| RULE-007 | `ShadowChargeCleanup.java` |
