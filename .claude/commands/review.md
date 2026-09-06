---
description: Post-solve companion — critique code, understand why a solution works, or connect a pattern to a problem
argument-hint: [paste code] | [why: problem name] | [connect: algorithm — problem]
---

## Step 1 — Search project for relevant context
```bash
grep -ril "keyword" /Users/I353174/X01/leetcode/notes/ 2>/dev/null
find /Users/I353174/X01/leetcode/src/ -name "*.java" 2>/dev/null | head -20
```
- For code review: check if a similar problem is already solved in `src/` — compare approaches
- For `why` / `connect`: find the matching pattern section in `notes/` and reference it

## Step 2 — Detect mode and respond

---

### Code block (no prefix) — solution critique:

**Correctness** — list only real edge cases it fails. Show the failing input inline:
```
Input:  nums = []        → IndexOutOfBounds on line 3
Input:  nums = [1]       → returns 0, expected 1
```

**Complexity:**
```
Time:  O(?)   ← your claim vs actual, with the hot loop identified
Space: O(?)   ← aux space only
```
If the hot loop is nested, draw it:
```
for i in n:          ← O(n)
  for j in i..n:     ← O(n) inner  →  total O(n²)
```

**Simplification** — lines or variables that can be removed without changing correctness or complexity. Show before → after for each.

**Optimisation** (only if O(n²) or worse):
- Name the bottleneck (one line)
- Name the data structure or technique that fixes it
- Target complexity after the fix
- Do NOT rewrite the solution

---

### `why: <problem name or brief description>`

**Core insight** (2 sentences max) — the one observation that makes this approach work

**Invariant** — what property is maintained at every step; show with a small ASCII diagram:
```
after each step:  left ptr  ≤  right ptr  and  window is valid
                  [  ....  |  valid window  |  ....  ]
                   ^left                    ^right
```

**Pattern name** — one line

**Trigger signals** — 3 specific phrases or constraints in the problem that should have pointed here

**Practice set** — 3 LeetCode problems using the exact same pattern. Name + number only.

---

### `connect: <algorithm> — <problem>`

**The matching property** — what specific property of the problem makes this algorithm applicable (one sentence)

**The clue** — quote or paraphrase the exact sentence/constraint in the problem that signals it

**Why alternatives fail** — one line per alternative:
```
BFS      → unweighted only, can't handle costs
Greedy   → local optima ≠ global optima here because...
```

**Reference** — if a matching section exists in the project notes, cite the file + section.

---

No recap of the problem. No filler.
