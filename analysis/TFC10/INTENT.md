# Intent — TFC10 (Create Hovedkontrakt)

Recorded: 2026-09-29 (Cursor + code-modernization plugin)

## Track

**Transform** — rewrite module-by-module to **Java 21 / Spring Boot 3.x** while legacy keeps running conceptually (strangler-fig).

## Parity

**Match legacy behavior exactly, including known quirks.** Equivalence is proven with characterization tests and recorded comparisons where COBOL cannot run locally.

## Target stack

Java 21 (OpenJDK), Spring Boot 3.x, Maven multi-module under `modernized/TFC10/`.

## Legacy

- **Label:** `TFC10`
- **Path:** `legacy/TFC10/` → `legacy/STG/TFC10/` (directory in workspace)
- **Frozen:** no edits under `legacy/**`; analysis and Java under `analysis/TFC10/` and `modernized/`.

## Scope note

This tree is the **one-hop call-graph closure** around H/K/L (`FTFCH100`, `FTFCK100`, `FTFCL100`) plus immediate helpers. **F115L050** is in-tree (imported 2026-09-29). Other **CALL** targets (e.g. `F115IMC0`, `F115ICA0`) remain **without** `.src` — map flags them; expand legacy inside this repo before transform phases that need them.

## Orchestration

```yaml
max_parallel_subagents: 5
```

Honor in every plugin step:

> Cap parallel Task subagents at 5 for this project.

## Entry chain

`FTFCH100` (H) → `FTFCK100` (K) / `FTFCL100` (L)
