## Table of Contents
- [How to Approach a Problem](#how-to-approach-a-problem)
- [Subarray Patterns](#subarray-patterns)
- [Two Pointer Patterns](#two-pointer-patterns)
- [Binary Search Mental Model](#binary-search-mental-model)
- [Recursion vs Iteration](#recursion-vs-iteration)
- [Which Data Structure?](#which-data-structure)
- [Amortized Analysis](#amortized-analysis)
- [Problem Types](#problem-types)
- [Edge Case Checklist](#edge-case-checklist)
- [Design by Contract](#design-by-contract)
- [Coding Tricks](#coding-tricks)
- [Number System Conversion](#number-system-conversion)

---

## How to Approach a Problem

1. Read **constraints** first — they reveal the expected time complexity
2. Identify the **pattern** from the constraint table below
3. Start with **brute force**, then optimize
4. **Generalize or simplify** — a complex problem is often a simpler one in disguise

### Constraint → Time Complexity Cheatsheet

| Constraint | Target Complexity | Typical Approaches |
|---|---|---|
| n ≤ 10 | O(n!) or O(2ⁿ) | Backtracking, combinatorics |
| n ≤ 100 | O(n²) or O(n³) | DP, matrix algorithms |
| n ≤ 10⁴ | O(n log n) | Sorting, binary search |
| n ≤ 10⁶ | O(n) | Sliding window, prefix sums |
| n ≤ 10⁹ | O(log n) or O(1) | Binary search, math |

### Problem-Solving Decision Flowchart

```
Start
  │
  ├─ Shortest path / min steps?
  │     ├─ Unweighted graph          → BFS
  │     ├─ Weighted, no neg edges    → Dijkstra
  │     └─ Negative edges            → Bellman-Ford
  │
  ├─ All paths / combinations / permutations?
  │     └─ Backtracking (+ pruning)
  │
  ├─ Overlapping subproblems?
  │     └─ Dynamic Programming
  │           ├─ 1D array state      → Linear DP
  │           └─ 2D / interval       → Grid / Interval DP
  │
  ├─ Sorted array / search for value?
  │     └─ Binary Search
  │
  ├─ Contiguous subarray problem?
  │     ├─ Max/min sum               → Kadane's
  │     ├─ Fixed window size         → Sliding Window
  │     ├─ Sum equals k              → Prefix Sum + HashMap
  │     └─ Min/max contribution      → Monotonic Stack
  │
  ├─ Two elements satisfying condition?
  │     ├─ Sorted array              → Two Pointers
  │     └─ Unsorted                  → HashMap
  │
  ├─ Top-K / frequent elements?
  │     └─ Heap (PriorityQueue)
  │
  └─ Connectivity / grouping?
        ├─ Grid / graph              → BFS / DFS
        └─ Dynamic merging           → Union-Find
```

---

## Subarray Patterns

> Pick the right tool based on *what* you're measuring.

| Pattern | Use When | Analogy | Example |
|---|---|---|---|
| **Sliding Window** | Condition on count/distinct elements in a window | Camera panning across a scene — fixed or flexible frame | Subarrays with exactly k odd numbers |
| **Monotonic Stack** | Each element's contribution as min/max across subarrays | Mountain peaks — each element "dominates" until a taller one arrives | Sum of (max − min) across all subarrays |
| **Prefix Sum** | Subarray sum equals or divisible by k | Odometer — range distance = end − start reading | Count subarrays with sum = k |
| **Kadane's DP** | Max/min contiguous sum or product | Running balance — reset when it goes negative | Max subarray sum, max circular subarray |

---

## Two Pointer Patterns

> Two pointers eliminate the need for nested loops when the array has monotonic structure.

### The 3 Setups

| Setup | Start | Move When | Use For |
|---|---|---|---|
| **Opposite ends** | `left=0, right=n-1` | Shrink toward center based on comparison | Two Sum (sorted), container with most water |
| **Same direction (fast/slow)** | Both at 0 | Fast advances every step, slow conditionally | Remove duplicates, find cycle in linked list |
| **Sliding window** | Both at 0 | Right expands, left contracts when condition breaks | Longest substring without repeat |

### Opposite Ends Template
```java
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) return new int[]{left, right};
    else if (sum < target) left++;
    else right--;
}
```

### Fast / Slow Template
```java
int slow = 0;
for (int fast = 0; fast < arr.length; fast++) {
    if (condition(arr[fast])) {
        arr[slow++] = arr[fast]; // compact valid elements
    }
}
// slow = new length
```

---

## Binary Search Mental Model

> **Analogy:** You're looking for a word in a dictionary. You never start from page 1 — you open the middle, decide "too early" or "too late", and halve the remaining search space.

Binary search applies whenever the search space is **monotonic** — a predicate flips from `false` to `true` (or vice versa) at exactly one point.

```
false false false [TRUE TRUE TRUE TRUE]
                  ↑
              Find this boundary
```

### The 3 Variants

| Goal | Condition | Template |
|---|---|---|
| Exact match | `arr[mid] == target` | Standard |
| First `true` (left boundary) | `arr[mid] >= target` | `ans = mid; right = mid - 1` |
| Last `false` (right boundary) | `arr[mid] <= target` | `ans = mid; left = mid + 1` |

### Universal Template
```java
int left = 0, right = n - 1, ans = -1;
while (left <= right) {
    int mid = left + (right - left) / 2; // avoids overflow
    if (condition(mid)) {
        ans = mid;
        right = mid - 1; // search left for first true
        // left = mid + 1; // search right for last true
    } else {
        left = mid + 1;
        // right = mid - 1;
    }
}
```

### When Is It Binary Search?
- "Find minimum X such that condition holds" → binary search on answer
- "Search in rotated/sorted array" → modified binary search
- `O(n)` brute force, but the space is monotonic → try O(log n) binary search

---

## Recursion vs Iteration

| | Recursion | Iteration |
|---|---|---|
| **Mental model** | Trust the function to solve subproblems | Explicit state management |
| **Risk** | StackOverflow on deep inputs (~10k frames) | None |
| **When to use** | Tree/graph traversal, divide & conquer, backtracking | Linear scans, BFS, when stack depth matters |
| **Convert to iteration** | Use an explicit `Stack<>` | N/A |

### Thinking Recursively — 3 Questions
```
1. What is the base case?        (when to stop)
2. What does one step do?        (trust recursion for the rest)
3. What do I return / accumulate?
```

**Example — Max depth of binary tree:**
```java
// Q1: null node → depth 0
// Q2: depth = 1 + max(left depth, right depth)
// Q3: return the integer depth
int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

---

## Which Data Structure?

| Need | Best DS | Time |
|---|---|---|
| Fast lookup by key | `HashMap` | O(1) avg |
| Sorted key lookup / range queries | `TreeMap` | O(log n) |
| Min or max element fast | `PriorityQueue` (heap) | O(log n) push/pop |
| Top-K elements | Min-heap of size K | O(n log k) |
| LIFO — undo / DFS | `Stack` / `Deque` | O(1) |
| FIFO — BFS / task queue | `Queue` / `ArrayDeque` | O(1) |
| Unique elements, fast contains | `HashSet` | O(1) avg |
| Sorted unique elements | `TreeSet` | O(log n) |
| Prefix queries / range sum | Prefix sum array | O(1) query |
| Dynamic connectivity | Union-Find | O(α(n)) ≈ O(1) |
| Substring / prefix matching | Trie | O(L) per op |

### Common Complexity Reference

| Operation | Array | LinkedList | HashMap | TreeMap | Heap |
|---|---|---|---|---|---|
| Access by index | O(1) | O(n) | — | — | — |
| Search | O(n) | O(n) | O(1) | O(log n) | O(n) |
| Insert | O(n) | O(1) | O(1) | O(log n) | O(log n) |
| Delete | O(n) | O(1) | O(1) | O(log n) | O(log n) |
| Min/Max | O(n) | O(n) | — | O(log n) | O(1) |

---

## Amortized Analysis

> "What's the *average* cost per operation over a long sequence — not the worst case of a single one?"

**Analogy:** Rent is paid once a month (expensive), daily coffee is cheap. Your *average daily cost* is low — rent is amortized across 30 days.

### The 3 Methods

| Method | How | Best For |
|---|---|---|
| **Aggregate** | Total cost ÷ n operations | Dynamic array resizing |
| **Accounting** | Charge extra "credit" on cheap ops, bank it for expensive ones | Stack with multi-pop |
| **Potential** | Assign a "potential energy" value to data structure state | Splay trees, Fibonacci heap |

### Example: Dynamic Array Resizing (Aggregate)

Inserting 9 elements into an ArrayList that doubles on overflow:

| Insert # | Insert Cost | Copy Cost (on resize) |
|---|---|---|
| 1 | 1 | 0 |
| 2 | 1 | 1 |
| 3 | 1 | 2 |
| 5 | 1 | 4 |
| 9 | 1 | 8 |

Total = 9 (inserts) + 15 (copies) = **24 operations for 9 inserts → O(1) amortized**

---

## Problem Types

| Type | What It Is | Key Signals | Strategy |
|---|---|---|---|
| **Constructive** | Build *any* valid solution | "Find any...", "Construct..." | Greedy or math insight |
| **Implementation** | Faithfully simulate given rules | Detailed spec, many edge cases | Careful, methodical coding |
| **Brute Force** | Try every possibility | Small n ≤ 20, "all pairs/subsets" | Nested loops, recursion |
| **Greedy** | Locally optimal choice = globally optimal | Prove exchange argument | Sort + scan |
| **Divide & Conquer** | Split → solve → merge | Overlapping subproblems absent | Merge sort, binary search |

### Problem Examples

| Platform | Constructive | Implementation | Brute Force |
|---|---|---|---|
| **Codeforces** | [1352C](https://codeforces.com/problemset/problem/1352/C), [1360B](https://codeforces.com/problemset/problem/1360/B) | [96A](https://codeforces.com/problemset/problem/96/A), [236A](https://codeforces.com/problemset/problem/236/A) | [231A](https://codeforces.com/problemset/problem/231/A), [69A](https://codeforces.com/problemset/problem/69/A) |
| **LeetCode** | [484](https://leetcode.com/problems/find-permutation/), [526](https://leetcode.com/problems/beautiful-arrangement/) | [54](https://leetcode.com/problems/spiral-matrix/), [68](https://leetcode.com/problems/text-justification/) | [15](https://leetcode.com/problems/3sum/), [78](https://leetcode.com/problems/subsets/) |

---

## Edge Case Checklist

Before submitting, run through:

```
Input validity
  □ Empty array / string / tree
  □ Single element
  □ All elements identical

Numbers
  □ Negative numbers
  □ Integer overflow (use long, or Math.addExact)
  □ Zero as input or divisor

Array / String
  □ Length 1 or 2
  □ Already sorted / reverse sorted
  □ Duplicates (does the problem allow them?)

Graph
  □ Disconnected components
  □ Self-loops
  □ No path exists (return -1 / false)

Boundaries
  □ Left/right pointer crossing
  □ Off-by-one in loops (< vs <=)
  □ Index out of bounds on grid edges
```

---

## Design by Contract

**Analogy:** ATM transaction —
- **Precondition:** Must insert valid card *(caller's job)*
- **Invariant:** Balance stays consistent during transaction *(system's job)*
- **Postcondition:** Cash dispensed, balance updated *(system's guarantee)*

| Concept | When | Owner | Example |
|---|---|---|---|
| **Precondition** | Before function runs | Caller | `sqrt(n)` requires `n ≥ 0` |
| **Invariant** | During execution | Code itself | Loop: "first i elements are sorted" |
| **Postcondition** | After function returns | Code itself | `sqrt(n)² ≈ n` |

### Loop Invariant

A property that holds **before and after every iteration** — use it to prove correctness.

```
Insertion Sort invariant:
  "After i iterations, A[0..i-1] is sorted relative to itself."
  When i = n → A[0..n-1] is fully sorted ✓

If the invariant breaks mid-loop → the bug is in that iteration.
```

---

## Coding Tricks

**Reverse Loop with Post-Decrement**
```java
int i = 3;
while (i-- > 0)
    a[i] = in.nextInt(); // fills a[2], a[1], a[0]
```

**Fast I/O**
```java
static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try { st = new StringTokenizer(br.readLine()); }
            catch (IOException e) { e.printStackTrace(); }
        }
        return st.nextToken();
    }
    int nextInt()  { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
}
```

**Lambda Sorting**
```java
Arrays.sort(arr, (x, y) -> Integer.compare(x[0], y[0])); // ascending by first element
Arrays.sort(arr, (x, y) -> Integer.compare(y[0], x[0])); // descending
// Multi-key: sort by first asc, break ties by second desc
Arrays.sort(arr, (x, y) -> x[0] != y[0] ? x[0] - y[0] : y[1] - x[1]);
```

**Greedy Index Sorting** (sort indices by value, not values themselves)
```java
Integer[] idx = new Integer[n];
for (int i = 0; i < n; i++) idx[i] = i;
Arrays.sort(idx, (i, j) -> Integer.compare(arr[i], arr[j]));
```

**Prefix Sum**
```java
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++)
    prefix[i + 1] = prefix[i] + a[i];
// Range sum [l, r] = prefix[r+1] - prefix[l]
```

**2D Prefix Sum**
```java
int[][] psum = new int[m+1][n+1];
for (int i = 1; i <= m; i++)
    for (int j = 1; j <= n; j++)
        psum[i][j] = grid[i-1][j-1] + psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1];
// Rectangle sum (r1,c1) to (r2,c2):
// psum[r2+1][c2+1] - psum[r1][c2+1] - psum[r2+1][c1] + psum[r1][c1]
```

**Frequency Map (one-liner)**
```java
Map<Integer, Integer> freq = new HashMap<>();
for (int x : arr) freq.merge(x, 1, Integer::sum);
```

**Swap without temp**
```java
a ^= b; b ^= a; a ^= b;
```

**Check power of 2**
```java
boolean isPow2 = n > 0 && (n & (n - 1)) == 0;
```

**Integer ceiling division**
```java
int ceil = (a + b - 1) / b; // same as Math.ceil(a / b) without float
```

---

## Number System Conversion

### Quick Reference

| From → To | Rule | Example |
|---|---|---|
| Binary → Decimal | Σ bit × 2^pos | `1011` = 8+2+1 = **11** |
| Octal → Decimal | Σ digit × 8^pos | `342` = 192+32+2 = **226** |
| Hex → Decimal | Σ digit × 16^pos | `3F` = 48+15 = **63** |
| Decimal → Binary | Divide by 2, remainders bottom→top | `23` → `10111` |
| Decimal → Octal | Divide by 8, remainders bottom→top | `83` → `123` |
| Decimal → Hex | Divide by 16, remainders bottom→top | `255` → `FF` |

### Decimal → Any Base (Visual)
```
23 ÷ 2 = 11 r 1  ↑
11 ÷ 2 = 5  r 1  │  read
 5 ÷ 2 = 2  r 1  │  bottom
 2 ÷ 2 = 1  r 0  │  to top
 1 ÷ 2 = 0  r 1  │
                 → 10111
```

### Java Conversions (Built-in)
```java
Integer.toBinaryString(23);   // "10111"
Integer.toOctalString(83);    // "123"
Integer.toHexString(255);     // "ff"
Integer.parseInt("10111", 2); // 23  (binary → decimal)
Integer.parseInt("FF", 16);   // 255 (hex → decimal)
```
