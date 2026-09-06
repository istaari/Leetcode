---
description: Learn an algorithm, pattern, or confusing code — ASCII diagrams, step-by-step walkthroughs, built on your notes
argument-hint: [algorithm/pattern name] | [paste code block]
---

## Step 1 — Search project notes first
```bash
grep -ril "$ARGUMENTS" /Users/I353174/X01/leetcode/notes/ 2>/dev/null
```
Read any matching files. If the topic is already covered, BUILD ON it — skip basics already documented, reference the file and section, only add what's missing.

## Step 2 — Respond based on input type

---

### If given an algorithm or pattern name:

**Analogy** (2 sentences max, real-world)

**Minimal worked example — show state evolution, not prose:**
Pick the smallest input that demonstrates the full behaviour (e.g. 5-element array, 3-node tree).
Draw state at each step using ASCII:

```
Array/pointer style:
  idx:  0    1    2    3    4
  val: [2]  [7]  [9]  [3]  [1]
        ^L             ^R        ← what changed and why

DP table style:
       0    1    2    3    4   ← capacity / index
  0  [ 0    0    0    0    0 ]
  1  [ 0    ?    ?    ?    ? ] ← fill order + recurrence shown inline

Tree style:
        3
       / \
      2   4      ← current node: 3, action: compare key
     /
    1
```

Walk each step: current state → decision → next state. End with the final answer circled or labelled `← answer`.

**Why it works** — one sentence per step explaining the invariant it maintains. What breaks if you skip or reorder a step.

**Recognition checklist** — 4–5 bullet signals in a problem statement that scream this pattern. Be specific ("array is sorted", "asking for min cost", etc.).

**Confusion twin** — one line: how this differs from the pattern it's most confused with.

**5 interview reminders** — ≤8 words each, things that are easy to forget under pressure.

**Starter problem** — the single LeetCode problem where this pattern is most obvious. Name + number only.

---

### If given a code block:

**State diagram** — draw the key data structure (stack, dp array, pointers, etc.) after the first 2–3 meaningful operations to show what it holds and why.

**Line-by-line** — for each non-trivial line:
- what it does
- why it's necessary
- what breaks without it

**Big picture** — 2–3 sentences: the overall strategy, no code.

---

No filler. No restating the question.
