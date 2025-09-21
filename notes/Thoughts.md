# Data Structure and Algorithms

## Array

## String

## Linked List

## Stack

- Use `two stack` when you need to keep track of two things, like characters with cumulative counts

## Recursion

- `Recursive Calls Represent Unfinished Work`, When a recursive function calls itself, it doesn’t immediately solve the
  current problem but instead defers it to be solved later by breaking it down into smaller sub-problems

- Think of the `bases cases and smallest input`

- Use `Nested Recursion`, Solution to one recursive call depends on the solution of another recursive call, or nested
  structure

## Tree

## Graph

## Backtracking

## Greedy

### 1. Sorting-Based Greedy

This is the most common pattern. The greedy choice is made possible by first sorting the data. The sorting criteria is the **key insight** that reveals the greedy strategy.

* **Core Idea:** Sort the input based on a specific property (e.g., end time, cost, length) that helps make the optimal local decision at each step.
* **When to Use:** When the problem seems to involve picking the "best" item, but the "best" isn't obvious until you order the options.

* **Examples:**
    * **Interval Problems:** Sort by end time to maximize the number of non-overlapping intervals. (e.g., `Non-overlapping Intervals`, `Meeting Rooms II`).
    * **Cost/Benefit Problems:** Sort by cost or a cost-to-benefit ratio to make the most efficient choices. (e.g., `Maximum Bags With Full Capacity of Rocks`, `Minimum Cost to Hire K Workers`, `Assign Cookies`).
    * **Monotonic Arrays:** Sort to create a consistent order that allows for a greedy sweep. (e.g., `Maximum Element After Decreasing and Rearranging`, `Minimum Replacements to Sort the Array`).
    * **Two-Pointer/Paired Problems:** Sorting allows you to use two pointers to find optimal pairs. (e.g., `Boats to Save People`, `Maximum Number of Coins You Can Get`).


### 2. Heap-Based Greedy

This is a specific kind of sorting-based greedy approach. Instead of a full sort, you use a heap to continuously get the next "best" item (the min or max) as you go.

* **Core Idea:** Use a **min-heap** or **max-heap** to always have immediate access to the element that should be processed next based on a greedy criteria.
* **When to Use:** When the "best" item changes dynamically as you make choices, and you need to repeatedly find the minimum or maximum from a changing set of values.

* **Examples:**
    * **Merging/Combining:** Repeatedly merge the two smallest elements. (e.g., `Minimum Cost to Connect Sticks`).
    * **Constraint-based:** Always process the most frequent or most constrained item first. (e.g., `Task Scheduler`, `Reorganize String`).
    * **Running Min/Max:** Maintain the k-th largest/smallest element or a median. (e.g., `Top K Frequent Elements`, `Last Stone Weight`).
    * **Graph Algorithms:** Prioritize nodes with the shortest distance or lowest cost. (e.g., `Dijkstra's Algorithm`, `Prim's Algorithm`).


### 3. Local Maxima/Farthest Jump

This pattern is about making the most aggressive move at each step to reach a destination as quickly as possible.

* **Core Idea:** At your current position, make a choice that gets you the farthest possible, with the goal of reaching the end in the minimum number of steps.
* **When to Use:** Problems that involve movement, such as jumps, covering a range, or moving along a path.

* **Examples:**
    * **Jump Games:** Find the farthest reachable point from your current position. (e.g., `Jump Game`, `Jump Game II`).
    * **Coverage Problems:** At each step, choose the interval or tap that extends your coverage the farthest. (e.g., `Minimum Number of Taps to Open to Water a Garden`, `Video Stitching`).
    * **Pathfinding:** Find a path that maximizes or minimizes a value by greedily choosing the best next step. (e.g., `Gas Station`, `Wiggle Subsequence`).

### 4. Reverse/Digit Greedy

This is a clever pattern where it's easier to find the solution by working backward from the goal or by targeting the most significant parts of the input first.

* **Core Idea:** Instead of building the solution forward, work backward from the target state or make changes to the most impactful digits first.
* **When to Use:** When the forward path is complex, but the reverse path is clear, or when you need to make changes that have the greatest effect on the final value.

* **Examples:**
    * **Reverse Operations:** Work backward from the target to the start state, which is often a simpler path. (e.g., `Broken Calculator`).
    * **Digit Manipulation:** Modify the most significant (leftmost) digits first, as they have the greatest impact on the number's value. (e.g., `Maximum 69 Number`, `Max Difference You Can Get From Changing an Integer`).


## Dynamic Programming

- Always find what is subproblem and what is state
- Think solution to the smallest subproblems, `smallest problem is almost always a problem of size 0 or 1 or empty string`
- Combine Smallest subproblems to solve bigger problems
- Think of smallest input like size is empty or 1, or string is empty
- Some DP problems are about maximizing/minimizing a value subject to some constraints
- In Recursive dp think from the last

## Math

- Normalizing modulo to be always positive `mod = (mod + k) % k`

## Bit Manipulation

- A `bitmask` is typically an integer where each bit (from right to left) represents a state or an element

- `Bitwise manipulations` refer to operations that directly manipulate the individual bits of an integer using logical
  operators like AND, OR, XOR, NOT, and bit shifts.

## Technical words


| Word      | Meaning                                                                              | Example Sentence                                                 |
|-----------|--------------------------------------------------------------------------------------|------------------------------------------------------------------|
| Resilient | Able to recover quickly from difficulties                                            | "Despite the challenges, she remained resilient and kept going." |
| Symmetry  | A property of a mathematical object that remains unchanged under some transformation | "The butterfly’s wings showed perfect symmetry."                 |


---

## How to approach a problem

- Always read constraints
- Break Down the Problem
- Think in Terms of Patterns
- Explore Brute Force First
- Think from different angle, generalize or simplify the problem

### **Rule of Thumb for Common Constraints**

| **Constraint**            | **Recommended Time Complexity**            | **Examples**                           |
|---------------------------|--------------------------------------------|----------------------------------------|
| <code>n &leq; 10</code>   | <code>O(n!)</code>, <code>O(2^n)</code>    | Backtracking, combinatorics            |
| <code>n &leq; 100</code>  | <code>O(n^2)</code>, <code>O(n^3)</code>   | Dynamic programming, matrix algorithms |
| <code>n &leq; 10^4</code> | <code>O(n log n)</code>, <code>O(n)</code> | Sorting, linear scans                  |
| <code>n &leq; 10^6</code> | <code>O(n)</code>, <code>O(n log n)</code> | Sliding window, prefix sums            |
| <code>n &leq; 10^9</code> | <code>O(log n)</code>, <code>O(1)</code>   | Binary search, modular arithmetic      |


## Three Methods of Amortized Analysis

### 1. Aggregate Method
Calculate the total cost of n operations and divide by n.

**Example: Dynamic Array (ArrayList/Vector)**
```
Operations: Insert at end
- Most insertions: O(1) - just add to available space
- Occasional resize: O(n) - when array is full, create new array of double size

Analysis:
- Start with size 1, double each time: 1 → 2 → 4 → 8 → 16 → ... → n
- Total copies during all resizes: 1 + 2 + 4 + 8 + ... + n/2 = n - 1
- Total cost for n insertions: n (normal insertions) + (n-1) (copies) = 2n - 1
- Amortized cost per insertion: (2n - 1)/n ≈ O(1)
```

## Total Subarray Problem

### Sliding Window

Used when you're scanning subarrays that satisfy a specific condition (like count of elements, distinct values, etc.).

- Find number of subarrays where no. of odd integers is exactly k
- Find the no. subarrays with exactly k different integers


### Monotonic Stack

Used for subarray problems where you're calculating the contribution of each element as min or max across subarrays.

- Calculate the sum of the range difference between (max and min) of all subarray
- Calculate the sum of the min elements of all subarrays


### Prefix Sum

Used when you're checking for subarray sums equal to or divisible by something.

- Total no. of subarray whose sum is equals to k
- The sum of the elements of the subarray is multiple of k
- Total subarrays that have a sum divisible by k


### Kandane DP

Used when you're trying to find the max/min total sum or product of a contiguous subarray.

- Find the largest sum of a contiguous subarray.
- Find the maximum sum of a circular subarray.
- Find the largest product of a contiguous subarray.


## Coding Tricks


### Reverse Loop with Post-Decrement

```java
int i = 3;
while (i-- > 0)
    a[i] = in.nextInt();
```

Let’s walk through it with `n = 3`:

| Loop | i (before `--`) | `i-- > 0`? | `i` after `--` | `a[i]` gets value |
| ---- | --------------- | ---------- | -------------- | ----------------- |
| 1    | 3               | Yes        | 2              | a\[2] = ...       |
| 2    | 2               | Yes        | 1              | a\[1] = ...       |
| 3    | 1               | Yes        | 0              | a\[0] = ...       |
| 4    | 0               | No         | -1             | stops             |


### Fast I/O Template

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
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
}
```

### Lambda Sorting

```java
// Ascending
Arrays.sort(arr, (x, y) -> Integer.compare(x[0], y[0]));
// Descending
Arrays.sort(arr, (x, y) -> Integer.compare(y[0], x[0]));
```

### Greedy Index Sorting

```java
Integer[] idx = new Integer[n];
for (int i = 0; i < n; i++) idx[i] = i;
Arrays.sort(idx, (i, j) -> Integer.compare(arr[i], arr[j]));
```

### prefix sum technique

```java
int[] a = {3, 5, 2, 7}; // n = 4

int[] prefix = new int[5]; // size n + 1
for (int i = 0; i < 4; i++)
    prefix[i + 1] = prefix[i] + a[i];
```

## State Machines

- **1. Deterministic Nature**

  A **Deterministic Finite State Machine (DFSM)** means that **given a specific state and an input (decision), the next state is uniquely determined**. Let's analyze this:

    - If you **own a stock (`Hold`)**, you have two choices:
        - Do nothing → Stay in `Hold`.
        - Sell → Move to `Sold`.

    - If you **just sold a stock (`Sold`)**, there is **only one possible transition**:
        - Move to `Rest` (cooldown applies).

    - If you are **not holding a stock and not in cooldown (`Rest`)**, you also have two choices:
        - Do nothing → Stay in `Rest`.
        - Buy → Move to `Hold`.

- **2. Difference Between DFSM and NDFSM**

  A **Non-Deterministic FSM (NDFSM)** allows **multiple possible next states for the same input, without a clear rule**. That is, for the same state and input, different transitions could happen at the same time.

    - In this problem, at any given moment, **the transitions follow strict rules**.
    - Given a state and a choice, you always move to **one specific next state** (no parallel paths).

    - The key difference is **in an NDFSM, the machine can "magically" pick different paths without clear rules**.
    - Here, once a choice is made, the transition is **fixed**, which makes it **deterministic**.
