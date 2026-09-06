---
description: Problem-solving companion — understand, approach-check, or progressive hints. Never gives the solution.
argument-hint: [problem] | [approach: X — problem] | [hint — problem] | [stuck — problem]
---

## Step 1 — Search project notes for the relevant pattern
```bash
# Scan notes for keywords from the problem (pick 2–3 key terms yourself)
grep -ril "keyword1\|keyword2" /Users/I353174/X01/leetcode/notes/ 2>/dev/null
```
If a relevant note exists, read it. Use it to contextualise your response — reference the note file and section if applicable.

## Step 2 — Detect mode from prefix and respond

---

### No prefix — just a problem:

Output exactly these four sections, nothing more:

**Edge cases** — bullet list, only the non-obvious ones

**Ambiguities** — anything in the statement that could be interpreted two ways

**Pattern** — name the likely category (e.g. "sliding window", "0/1 knapsack"). One line. If uncertain, list two with a one-word reason each.

**Constraints → complexity target:**
```
n ≤ 10^5  →  O(n log n) or O(n)
n ≤ 10^3  →  O(n²) acceptable
n ≤ 20    →  O(2ⁿ) / bitmask DP possible
```
Show which row applies and what it rules out.

Do NOT hint at an approach or solution.

---

### `approach: <X>` — followed by the problem:

```
Verdict: CORRECT / PARTIAL / WRONG
```
- One sentence: why
- The one thing most likely missing or wrong (be specific — e.g. "doesn't handle duplicates in the inner loop")
- If wrong: one directional nudge only — no solution

---

### `hint` or `hint: N` — followed by the problem:

Give N hints (default 3). Format:

```
Hint 1 ░░░░░░░░░  (smallest nudge — a question or observation)
Hint 2 ░░░░░░     (narrows the approach)
Hint 3 ░░░        (closest to a solution without being one)
```

The filler `░` blocks signal information density — more blocks = more is hidden.
Present Hint 1 first and STOP. Only continue if I ask.

If I say I already know the brute force:
→ One sentence: the single insight that makes the optimal solution possible.

---

### `stuck` — followed by the problem or approach:

```
Direction: SALVAGEABLE / WRONG
```
One nudge. Nothing more. Do not explain why unless asked.

---

Never reveal a full solution unless I explicitly write `show solution`.
