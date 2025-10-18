# **Introduction**

🧩 1. **State**

A **DP state** defines what subproblem you're solving.

**In other words:**

> It is a representation of the problem with a subset of inputs that leads to the full solution.

**Examples:**

* `dp[i]` = the optimal solution (e.g., max/min/count/etc.) considering the first `i` elements.
* `dp[i][j]` = the solution when considering the first `i` items and a total capacity of `j`.
* `dp[mask]` = the result when a subset of elements (represented by bitmask `mask`) has been processed.
* `F(n)` =the result (e.g., number of ways, max value, min cost, etc.) for input size or parameter n.

**You choose a state by answering:**

> What parameters do I need to uniquely define a subproblem?


🔁 2. **Transition**

A **transition** tells you how to compute the value of the current state using previously computed states.

**In other words:**

> It defines how to move from smaller subproblems to bigger ones.

**Example:**

If `dp[i]` is the number of ways to reach step `i`, and you can take 1 or 2 steps at a time:

```
dp[i] = dp[i-1] + dp[i-2]
```

Transitions are based on:

* Choices you can make
* Constraints of the problem
* Recurrence relations
  - Recurrence Relation is an equation that defines the solution to a larger problem in terms of the solutions to its smaller, overlapping subproblems.
  - It defines the relationship between the problem and its subproblems

Notes
- A **transition rule** (or transition function) defines how a system moves from one state to another in response to an input or event.

- An **induction rule** is a method of mathematical proof used to establish that a given statement is true for all natural numbers (or any well-ordered set).

   - **Key Idea:** It establishes a chain of reasoning. If the first case is true, and there's a rule that shows if any case is true then the next one must also be true, it follows that all subsequent cases are true.

🧱 3. **Base Case**

A **base case** is where your DP starts — the smallest or simplest subproblem that can be solved directly.

**In other words:**

> It provides the initial value(s) to build the rest of the DP table.

**Example:**

If you are counting the number of ways to climb stairs:

```
dp[0] = 1   # 1 way to stay at ground level (do nothing)
dp[1] = 1   # 1 way to take the first step
```

You **must always define** the base case correctly — it anchors your solution.


### **Optimization Techniques**

- Space Optimization (Reducing DP Array Dimensions, rolling arrays or swap between two arrays)
- State Compression (Bitmask DP)
- Convex Hull Trick / Li Chao Tree (for certain DP recurrences)
- Divide and Conquer Optimization
- Monotonic Queue Optimization
- Knuth’s Optimization

### **Misc.**

- DP on Trees for hierarchical structures
- DP with Binary Search for range-based decisions


# Patterns


## Linear Sequences (Fibonacci-Style)

This is the most fundamental 1D DP pattern. The solution for the current state `i` is a direct function of a fixed number of preceding states (usually `i-1` and `i-2`).

**Core Idea:** Build the solution step-by-step along a line or sequence.


### A General Approach for 1D DP

1.  **Identify the State:** First, figure out what `dp[i]` will represent. Ask yourself: "What information do I need to decide what to do at step `i`?" Common states include:

      * The number of ways to reach index `i`.
      * The minimum cost to get to index `i`.
      * The maximum value/profit ending at index `i`.

2.  **Find the Recurrence Relation:** This is the core formula. How can you calculate `dp[i]` using previous values like `dp[i-1]` or `dp[i-2]`? This relation models the choices you can make at each step.

3.  **Determine the Base Cases:** What are the solutions for the smallest subproblems (e.g., `dp[0]` or `dp[1]`)? These are the starting points that don't depend on any other subproblems.


### Example Analysis


#### 1\. **Climbing Stairs**

  * **State `dp[i]`:** The number of distinct ways to reach the `i`-th stair.
  * **Recurrence Relation:** To reach stair `i`, you could have come from stair `i-1` (by taking 1 step) or stair `i-2` (by taking 2 steps). So, the total ways are the sum of the ways to reach those previous stairs.
      * `dp[i] = dp[i-1] + dp[i-2]`
  * **Base Cases:** `dp[0] = 1` (one way to be at the start), `dp[1] = 1` (one way to reach the first step).

#### 2\. **Min Cost Climbing Stairs**

  * **State `dp[i]`:** The minimum cost to reach the `i`-th stair.
  * **Recurrence Relation:** To reach stair `i`, you can jump from `i-1` or `i-2`. You choose the path with the minimum cost so far and add the cost of the current stair.
      * `dp[i] = cost[i] + min(dp[i-1], dp[i-2])`
  * **Base Cases:** `dp[0] = cost[0]`, `dp[1] = cost[1]`.

#### 3\. **House Robber**

  * **State `dp[i]`:** The maximum amount of money that can be robbed up to and including house `i`.
  * **Recurrence Relation:** At house `i`, you have two choices:
    1.  **Rob house `i`:** You get `nums[i]` plus the max money robbed up to house `i-2` (since you can't rob `i-1`).
    2.  **Don't rob house `i`:** You get the max money robbed up to house `i-1`.
    <!-- end list -->
      * `dp[i] = max(nums[i] + dp[i-2], dp[i-1])`
  * **Base Cases:** `dp[0] = nums[0]`, `dp[1] = max(nums[0], nums[1])`.

#### 4\. **House Robber II**

  * **Key Twist:** The houses are in a circle, meaning the first and last houses are adjacent. You can't rob both.
  * **Approach:** This breaks the problem into two separate, standard "House Robber" problems:
    1.  Rob houses from `0` to `n-2` (excluding the last house).
    2.  Rob houses from `1` to `n-1` (excluding the first house).
  * **Solution:** The final answer is the maximum of the results from these two subproblems.

Got it. Here's a refined version of your notes for the Grid/Pathfinding DP pattern.

---

## Grid Traversal (Path Problems)

This pattern involves finding an optimal path (e.g., counting paths, finding a minimum/maximum sum path) in a 2D matrix. The solution for a cell `(i, j)` typically depends on the solutions from adjacent cells, most commonly the ones above (`i-1, j`) and to the left (`i, j-1`).

### A General Approach for Grid DP

1.  **Define the State:** `dp[i][j]` almost always represents the answer to the subproblem that ends at cell `(i, j)`. For example, it could be "the number of ways to reach `(i, j)`" or "the minimum path sum to `(i, j)`".

2.  **Establish the Recurrence Relation:** Determine how to compute `dp[i][j]` from its neighbors. Since movement is usually restricted (e.g., only right and down), the value at `dp[i][j]` is a function of `dp[i-1][j]` and `dp[i][j-1]`.

3.  **Set the Base Cases:** The starting points need to be initialized. This is often the top-left cell `dp[0][0]`. For many grid problems, the entire first row and first column serve as the initial base cases, as there's only one way to reach them.


### **Example Analysis**

#### 1. **Unique Paths**
* **State `dp[i][j]`:** The number of unique paths from `(0, 0)` to `(i, j)`.
* **Recurrence Relation:** To get to `(i, j)`, you must have come from either `(i-1, j)` (above) or `(i, j-1)` (left). The total paths are the sum of paths to those cells.
    * `dp[i][j] = dp[i-1][j] + dp[i][j-1]`
* **Base Cases:** Every cell in the first row and first column can be reached in only one way. So, `dp[i][0] = 1` and `dp[0][j] = 1` for all `i, j`.

#### 2. **Unique Paths II**
* **Key Twist:** Same problem, but some cells are obstacles you cannot pass through.
* **State `dp[i][j]`:** Same as before.
* **Recurrence Relation:** The logic is the same, but with a crucial condition.
    * If `grid[i][j]` is an obstacle: `dp[i][j] = 0`
    * Otherwise: `dp[i][j] = dp[i-1][j] + dp[i][j-1]`
* **Base Cases:** The first row and column are initialized to 1 until an obstacle is hit. After an obstacle, all subsequent cells in that row/column are unreachable (`dp` value is 0).

#### 3. **Minimum Path Sum**
* **State `dp[i][j]`:** The minimum sum of a path from `(0, 0)` to `(i, j)`.
* **Recurrence Relation:** To reach `(i, j)` with a minimum sum, you must have come from the neighbor (`(i-1, j)` or `(i, j-1)`) that had the smaller path sum.
    * `dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])`
* **Base Cases:** The first row and column are cumulative sums of the grid values. `dp[i][0] = dp[i-1][0] + grid[i][0]` and `dp[0][j] = dp[0][j-1] + grid[0][j]`.



#### 4. **Minimum Falling Path Sum**
* **Key Twist:** You can start at any cell in the top row and move down, down-left, or down-right.
* **State `dp[i][j]`:** The minimum sum of a falling path *ending* at cell `(i, j)`.
* **Recurrence Relation:** The minimum path to `(i, j)` comes from the minimum of the three possible cells in the row above it.
    * `dp[i][j] = matrix[i][j] + min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])`
    * (You must handle boundary conditions carefully for the first and last columns).
* **Base Cases:** The first row of the `dp` table is simply the first row of the input matrix.
* **Final Answer:** The minimum value in the *last row* of the `dp` table.

#### 5. **Triangle**
* **Key Twist:** A grid that's shaped like a triangle. A cell `(i, j)` can only be reached from `(i-1, j)` or `(i-1, j-1)`.
* **State `dp[i][j]`:** The minimum path sum from the top to cell `(i, j)`.
* **Recurrence Relation:** Similar to other min path problems, but with different neighbors.
    * `dp[i][j] = triangle[i][j] + min(dp[i-1][j-1], dp[i-1][j])`
    * (Handle the edges of the triangle where `j=0` or `j=i`).
* **Final Answer:** The minimum value in the last row of the `dp` table.



### Key Techniques & Optimizations

* **2D DP Table:** The standard approach is to create a `dp` grid of size `M x N`.
* **Space Optimization (to 1D array):** For problems where `dp[i][j]` only depends on the previous row (`i-1`), you can optimize space from $O(M \times N)$ to $O(N)$. You only need one array to store the results of the previous row while you compute the current row.
* **In-Place Modification:** Sometimes, you can use the input grid itself as your DP table to save space, modifying it as you go. This works if you don't need the original values anymore.

---


## Decision-Making at Each Step (Subsequence & Knapsack Problems) 🎒

This powerful pattern solves problems where for each item in a collection, you must make a choice: either **include** it in your solution or **exclude** it. The goal is to optimize a certain value (e.g., reach a target sum, maximize profit, minimize items) based on a sequence of these choices.


### A General Approach for Knapsack-Style DP

1.  **Identify the State:** `dp[i][j]` is the most common state representation.
    * `i` typically refers to considering the **first `i` items** from the input set.
    * `j` typically refers to the **target** you are trying to achieve (e.g., a specific sum, capacity, or weight).
    * The value `dp[i][j]` represents the answer to the subproblem: "Can we achieve target `j` using only the first `i` items?" or "What is the best outcome for target `j` using the first `i` items?".

2.  **Formulate the Recurrence Relation (The Core Choice):** For each item `i`, the recurrence relation is built around the two fundamental choices:
    * **Exclude the current item `i`:** If you don't use item `i`, the solution is simply the best you could do with the first `i-1` items for the same target `j`. The state transitions to `dp[i-1][j]`.
    * **Include the current item `i`:** This is only possible if the item's value/weight is less than or equal to the current target `j`. The solution then depends on the best you could do for the remaining target `j - value[i]`. The state transition depends on whether you can reuse items (see below).

3.  **Establish Base Cases:** Define the starting conditions. This usually involves `dp[0][j]` (using no items) or `dp[i][0]` (for a target of 0).


### Sub-Pattern A: 0/1 Knapsack (Use Each Item At Most Once)

In this variant, once you use an item, you cannot use it again. The "include" choice always looks back at the solution from the *previous row* (`i-1`).

#### **Example Analysis**

**1. Partition Equal Subset Sum**
* **Problem:** Can an array be partitioned into two subsets with an equal sum? This is equivalent to finding if a subset exists that sums to `totalSum / 2`.
* **State `dp[i][j]`:** A boolean value - `true` if a sum of `j` can be formed using the first `i` numbers, `false` otherwise.
* **Recurrence Relation:** For each number `nums[i-1]` and target `j`:
    * **Exclude `nums[i-1]`:** The result is `dp[i-1][j]`.
    * **Include `nums[i-1]`:** The result is `dp[i-1][j - nums[i-1]]`.
    * Since we want to know if *either* choice works, we use OR:
        `dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]]`
* **Base Cases:** `dp[i][0] = true` for all `i` (you can always make a sum of 0 by picking no elements).


### Sub-Pattern B: Unbounded Knapsack (Use Items Infinitely)

In this variant, you can use the same item multiple times. The "include" choice looks at the solution from the *current row* (`i`), signifying that the same item can be used again to solve the smaller subproblem.

#### **Example Analysis**

**1. Coin Change (Fewest Coins)**
* **State `dp[i][j]`:** The minimum number of coins to make change for amount `j`, using the first `i` coin types.
* **Recurrence Relation:** For each coin `c = coins[i-1]` and amount `j`:
    * **Exclude coin `c`:** `dp[i-1][j]`
    * **Include coin `c`:** `1 + dp[i][j - c]` (The `+1` is for the current coin, and we look at `dp[i]` because we can reuse the same coin).
    * We want the minimum, so:
        `dp[i][j] = min(dp[i-1][j], 1 + dp[i][j - c])`
* **Base Cases:** `dp[i][0] = 0` (zero coins for amount 0). Initialize other states to infinity.

**2. Coin Change 2 (Number of Combinations)**
* **State `dp[i][j]`:** The number of combinations to make amount `j` using the first `i` coin types.
* **Recurrence Relation:**
    * **Exclude coin `c`:** `dp[i-1][j]`
    * **Include coin `c`:** `dp[i][j - c]` (We look at `dp[i]` to allow reuse).
    * We want the total number of ways, so we add them:
        `dp[i][j] = dp[i-1][j] + dp[i][j - c]`
* **Base Cases:** `dp[i][0] = 1` (there is one way to make amount 0: by picking nothing).


### Sub-Pattern C: Longest Increasing Subsequence (LIS)

This is a different but related 1D DP pattern. The decision is still about inclusion, but the state is defined differently.

#### **Example Analysis**

**1. Longest Increasing Subsequence**
* **State `dp[i]`:** The length of the longest increasing subsequence that **ends at index `i`**. This is a crucial distinction.
* **Recurrence Relation:** To calculate `dp[i]`, we look at all previous elements `j < i`. If `nums[i]` can extend the subsequence ending at `j` (i.e., `nums[i] > nums[j]`), then we have a potential new LIS of length `dp[j] + 1`. We take the maximum over all such valid `j`.
    * `dp[i] = 1 + max(dp[j])` for all `0 <= j < i` where `nums[i] > nums[j]`.
* **Base Cases:** `dp[i] = 1` for all `i` initially (each element is an LIS of length 1 by itself).
* **Final Answer:** The final answer is the maximum value in the entire `dp` array, not necessarily `dp[n-1]`.


### Key Techniques & Optimizations

* **Space Optimization (to 1D array):** Both 0/1 and Unbounded Knapsack problems can be optimized from $O(N \times \text{Target})$ to $O(\text{Target})$ space.
    * **For 0/1 Knapsack (Partition Subset Sum):** The inner loop must iterate **backwards** (from `target` down to `num`) to prevent using the same item multiple times in the same subsequence.
    * **For Unbounded Knapsack (Coin Change):** The inner loop must iterate **forwards** (from `coin` up to `amount`) to allow the same item to be reused.
* **LIS $O(N \log N)$ Solution:** The standard LIS problem can be further optimized from $O(N^2)$ to $O(N \log N)$ using a clever approach with binary search, which is a common follow-up.

 
 ---

## String Manipulation (2D Comparisons)

This family of problems typically involves comparing two strings or analyzing properties of a single string's substrings. The standard tool is a **2D DP table**, where `dp[i][j]` represents a solution for the prefixes `string1[0...i-1]` and `string2[0...j-1]`, or for the substring `string[i...j]`.

### A General Approach for String DP

1.  **Define the State:**
    * **Two Strings:** `dp[i][j]` usually stores the answer (e.g., length, count, boolean) for the subproblem involving the first `i` characters of `s1` and the first `j` characters of `s2`.
    * **One String:** `dp[i][j]` usually stores the answer for the substring starting at index `i` and ending at index `j`.

2.  **Establish the Recurrence Relation:** The logic almost always hinges on comparing the characters at the current pointers (`s1[i-1]` and `s2[j-1]`, or `s[i]` and `s[j]`).
    * **If characters match:** The solution typically builds upon the solution for the smaller subproblem, `dp[i-1][j-1]`.
    * **If characters don't match:** You must make a choice, which often involves taking the `max`, `min`, or `sum` of the solutions from related subproblems, like `dp[i-1][j]` and `dp[i][j-1]`.

3.  **Set the Base Cases:** Initialize the first row and column of the DP table. These represent the solution when one of the strings is empty.


### Sub-Pattern A: Comparing Two Strings

#### **1. Longest Common Subsequence (LCS)**
* **State `dp[i][j]`:** The length of the LCS between `s1[0...i-1]` and `s2[0...j-1]`.
* **Recurrence Relation:**
    * If `s1[i-1] == s2[j-1]`: The characters match, so they extend the common subsequence.
        `dp[i][j] = 1 + dp[i-1][j-1]`
    * If `s1[i-1] != s2[j-1]`: The characters don't match. The LCS is the best we can do by either ignoring the character from `s1` or from `s2`.
        `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`

#### **2. Distinct Subsequences**
* **State `dp[i][j]`:** The number of distinct subsequences of `s[0...j-1]` that are equal to `t[0...i-1]`.
* **Recurrence Relation:**
    * If `s[j-1] != t[i-1]`: The last character of `s` can't be used to match the last character of `t`. So, the number of ways is the same as if we ignored this last character of `s`.
        `dp[i][j] = dp[i][j-1]`
    * If `s[j-1] == t[i-1]`: The last characters match. The total number of ways comes from two possibilities:
        1.  Ways to form `t` in `s` *without* using the current character `s[j-1]`: `dp[i][j-1]`.
        2.  Ways to form the rest of `t` (`t[0...i-2]`) in the rest of `s` (`s[0...j-2]`), using the current character as the match: `dp[i-1][j-1]`.
        `dp[i][j] = dp[i][j-1] + dp[i-1][j-1]`

#### **3. Interleaving String**
* **State `dp[i][j]`:** A boolean that is `true` if `s3`'s prefix of length `i+j` can be formed by interleaving `s1`'s prefix of length `i` and `s2`'s prefix of length `j`.
* **Recurrence Relation:** The last character of the target prefix `s3[i+j-1]` must match either the last character of `s1`'s prefix (`s1[i-1]`) or `s2`'s prefix (`s2[j-1]`).
    * Match with `s1`: `s1[i-1] == s3[i+j-1]` AND the rest of `s3` can be formed (`dp[i-1][j]`).
    * Match with `s2`: `s2[j-1] == s3[i+j-1]` AND the rest of `s3` can be formed (`dp[i][j-1]`).
    * Since either can be true, we use an OR:
        `dp[i][j] = (match_s1 && dp[i-1][j]) || (match_s2 && dp[i][j-1])`

#### **4. Edit Distance**
* **State `dp[i][j]`:** The minimum number of operations (insert, delete, replace) to convert `word1[0...i-1]` to `word2[0...j-1]`.
* **Recurrence Relation:**
    * If `word1[i-1] == word2[j-1]`: No operation needed.
        `dp[i][j] = dp[i-1][j-1]`
    * If `word1[i-1] != word2[j-1]`: We must perform one operation. We choose the one that results from the minimum cost subproblem.
        `dp[i][j] = 1 + min(dp[i-1][j-1],   // Replace`
                         `dp[i-1][j],       // Delete`
                         `dp[i][j-1])       // Insert`


### Sub-Pattern B: Analyzing Substrings of a Single String

#### **1. Longest Palindromic Substring**
* **State `dp[i][j]`:** A boolean that is `true` if the substring `s[i...j]` is a palindrome.
* **Recurrence Relation:** For a string to be a palindrome, the outer characters must match, AND the inner part must also be a palindrome.
    * `dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]`
* **Base Cases:** `dp[i][i] = true` (single characters). `dp[i][i+1]` is `true` if `s[i] == s[i+1]`.

#### **2. Longest Palindromic Subsequence**
* **State `dp[i][j]`:** The length of the LPS within the substring `s[i...j]`.
* **Recurrence Relation:**
    * If `s[i] == s[j]`: The two outer characters contribute to the palindrome.
        `dp[i][j] = 2 + dp[i+1][j-1]`
    * If `s[i] != s[j]`: We can't use both outer characters. Find the LPS by either excluding `s[i]` or `s[j]`.
        `dp[i][j] = max(dp[i+1][j], dp[i][j-1])`


### Sub-Pattern C: Advanced Matching (Wildcards)

#### **1. Wildcard & Regular Expression Matching**

* **State `dp[i][j]`:** `true` if the first `i` characters of the string `s` match the first `j` characters of the pattern `p`.
* **Recurrence (Wildcard Matching - `?`, `*`):**
    * If `p[j-1] == '?'` or `s[i-1] == p[j-1]`: The current characters match. The result depends on the prefixes. `dp[i][j] = dp[i-1][j-1]`.
    * If `p[j-1] == '*'`: The `*` gives two choices:
        1.  `*` matches an empty sequence: `dp[i][j-1]`
        2.  `*` matches the current character `s[i-1]`: `dp[i-1][j]`
        `dp[i][j] = dp[i][j-1] || dp[i-1][j]`
* **Recurrence (Regex Matching - `.`, `*`):**
    * If `p[j-1] == '.'` or `s[i-1] == p[j-1]`: `dp[i][j] = dp[i-1][j-1]`.
    * If `p[j-1] == '*'`: The `*` modifies the *previous* pattern character `p[j-2]`.
        1.  `*` matches zero times (we ignore `p[j-2]*`): `dp[i][j-2]`.
        2.  `*` matches one or more times (if `s[i-1]` matches `p[j-2]`): `dp[i-1][j]`.

---

## Local Optimizations Toward a Global Solution

This pattern focuses on problems where the optimal solution can be built by making a locally optimal choice at each step. Instead of a full DP table, the solution often only depends on the results from the immediately preceding step (`i-1`), allowing for significant space optimization, often down to a few variables.

### A General Approach

1.  **Define the State:** Identify what needs to be tracked at each step `i`. This is often "the best solution ending at index `i`" or a set of states representing possible actions (like `buy`, `sell`, `rest`).
2.  **Find the Recurrence:** Establish the relationship for the current step `i` based *only* on the outcomes of step `i-1`.
3.  **Track Global Maximum:** While calculating the local optimum at each step, maintain a separate global variable to store the overall best solution found so far.


### Sub-Pattern A: Maximum/Minimum Subarray (Kadane's Algorithm)

This pattern is a highly efficient form of 1D DP. The core idea is that for each element, you decide whether it's better to extend the previous subarray or to start a new subarray with the current element.

#### **1. Maximum Subarray**
* **State `dp[i]`:** The maximum sum of a contiguous subarray that **ends** at index `i`.
* **Recurrence:** At index `i`, you have two choices:
    1.  Start a new subarray: The sum is just `nums[i]`.
    2.  Extend the previous subarray: The sum is `nums[i] + dp[i-1]`.
    * `dp[i] = max(nums[i], nums[i] + dp[i-1])`
* **Kadane's Optimization:** You don't need a `dp` array. You only need the `local_max` from the previous step and a `global_max` to track the overall answer.

#### **2. Maximum Product Subarray**
* **Key Twist:** A negative number can flip the largest product to the smallest, and vice-versa.
* **Approach:** You must track both the **maximum product** and the **minimum product** ending at the current position.
    * When you encounter a negative number, the roles of max and min product swap.
    * The new max product at `i` is the maximum of `nums[i]`, `nums[i] * old_max`, and `nums[i] * old_min`.

#### **3. Maximum Sum Circular Subarray**
* **Key Twist:** The subarray can wrap around the end of the array to the beginning.
* **Approach:** This leaves two possibilities for the maximum sum subarray:
    1.  **Case 1: The subarray does not wrap around.** This is the standard Maximum Subarray problem, solvable with Kadane's.
    2.  **Case 2: The subarray wraps around.** A wrapping subarray is equivalent to taking the **total sum** and subtracting the **minimum sum subarray** from the middle. The minimum subarray can also be found using a variation of Kadane's.
* **Solution:** `max(kadane_max, total_sum - kadane_min)` (with an edge case for when all numbers are negative).


### Sub-Pattern B: State Machines (Stock Problems) 📈

For problems where decisions are constrained by previous actions (e.g., you can't sell if you haven't bought), a state machine is a perfect model. We define states based on the actions allowed and calculate the max profit for each state at every step.


#### **1. Best Time to Buy and Sell Stock I**
* **States:** Implicitly, you are either looking for the cheapest day to buy (`min_price`) or you have bought and are looking for the max profit (`max_profit`).
* **Approach:** Iterate through prices, keeping track of the minimum price seen so far and updating the maximum profit possible if you were to sell on the current day.

#### **2. Best Time to Buy and Sell Stock II**
* **Constraint:** You can transact as many times as you like.
* **Approach (Greedy):** This simplifies the problem. You don't need complex states. If today's price is higher than yesterday's, you can make a profit. The total profit is the sum of all such positive price changes.

#### **3. Best Time to Buy and Sell Stock with Cooldown**
* **States:**
    * `hold`: Max profit if you are **holding** a stock today.
    * `sold`: Max profit if you **sold** a stock today.
    * `rest` (or `cooldown`): Max profit if you are **not holding** and are free to buy.
* **Recurrence Relations:**
    * `hold[i] = max(hold[i-1], rest[i-1] - price)`  (Continue holding or buy from rest)
    * `sold[i] = hold[i-1] + price`                    (Must have held yesterday to sell today)
    * `rest[i] = max(rest[i-1], sold[i-1])`        (Continue resting or enter rest after selling)

#### **4. Best Time to Buy and Sell Stock III & IV (K Transactions)**
* **Key Idea:** Generalize the states to include the transaction number.
* **State:** We need to track the max profit after the `j`-th buy and `j`-th sell.
    * `buy[j]`: Max profit after at most `j` buys.
    * `sell[j]`: Max profit after at most `j` sells.
* **Recurrence for K transactions:** For each price, iterate from `j = 1 to k`:
    * To make the `j`-th buy, you must have completed the `(j-1)`-th sell.
        `buy[j] = max(buy[j], sell[j-1] - price)`
    * To make the `j`-th sell, you must have completed the `j`-th buy.
        `sell[j] = max(sell[j], buy[j] + price)`
* The final answer is `sell[k]`.
 
---


## DP on Partitions and Subsets 🧩

This pattern covers two related but distinct techniques. The first involves **partitioning** a sequence (like a string or array) into valid segments. The second, **bitmasking**, is a powerful technique for solving problems involving subsets, especially when the number of elements is small.

### Sub-Pattern A: Partitioning DP

The goal is to break a sequence into segments that satisfy certain properties. The DP state `dp[i]` typically stores the answer for the prefix of length `i`.

#### **A General Approach**
1.  **Define State:** `dp[i]` is the answer (e.g., min cuts, number of ways, boolean possibility) for the prefix of length `i`.
2.  **Recurrence Relation:** To calculate `dp[i]`, you iterate backwards with a pointer `j` from `i-1` down to `0`. For each `j`, you check if the segment from `j` to `i-1` is "valid". If it is, you use the pre-computed answer for the prefix ending at `j` (`dp[j]`) to update `dp[i]`.

#### **Example Analysis**

**1. Word Break**
* **State `dp[i]`:** A boolean, `true` if the prefix `s[0...i-1]` can be successfully segmented into dictionary words.
* **Recurrence Relation:** We look at all possible last words. `dp[i]` is `true` if we can find some split point `j` such that `dp[j]` is `true` AND the substring `s[j...i-1]` is a word in the dictionary.
    * `dp[i] = OR( dp[j] )` for all `0 <= j < i` where `s.substring(j, i)` is in the word dictionary.
* **Base Case:** `dp[0] = true` (an empty string can always be formed).

**2. Palindrome Partitioning II**
* **State `dp[i]`:** The **minimum number of cuts** required to partition the prefix `s[0...i-1]` into palindromes.
* **Recurrence Relation:** To find the minimum cuts for `s[0...i-1]`, we try all possible last segments. If the substring `s[j...i-1]` is a palindrome, it means we can make one cut after index `j-1` and then use the solution for the prefix `s[0...j-1]`.
    * `dp[i] = min( 1 + dp[j] )` for all `0 <= j < i` where `s.substring(j, i)` is a palindrome.
* **Optimization:** Pre-calculating all palindromic substrings in $O(N^2)$ is crucial to avoid re-computation inside the main DP loop.

**3. Word Break II**
* **State `dp[i]`:** A **list of strings**, where each string is a valid sentence formed from the prefix `s[0...i-1]`.
* **Recurrence Relation:** This is a constructive version of Word Break I. For each split point `j` where `s[j...i-1]` is a valid word, we retrieve all sentences from `dp[j]`. We then form new sentences by appending `s[j...i-1]` to each of them.
* **Note:** This is often implemented with memoized recursion (top-down DP) as it can be more intuitive for building the resulting strings.


### Sub-Pattern B: Bitmask DP

This technique is used when the state depends on a **subset of items** being used, picked, or visited. A bitmask (an integer) acts as a compact map to represent this subset, which is very efficient for a small number of items (usually `N <= 20`).

#### **A General Approach**
1.  **Define State:** `dp[mask]` stores the optimal value or property for the subset of items represented by `mask`. The `i`-th bit of the mask is `1` if the `i`-th item is included, and `0` otherwise.
2.  **Recurrence Relation:** The transitions involve moving from a smaller subset (a mask with fewer `1`s) to a larger one by adding a single, unused item. `dp[new_mask]` is calculated based on `dp[old_mask]` and the item being added.

#### **Example Analysis**

**1. Partition to K Equal Sum Subsets**
* **The Challenge:** We need to keep track of which numbers have already been placed into a subset to avoid reusing them. A bitmask is perfect for this.
* **Approach:** This is best solved with a top-down DP (memoized recursion) approach.
* **State `memo[mask]`:** A boolean, `true` if the subset of numbers represented by `mask` can be successfully partitioned.
* **Recursive Function `can_partition(mask, current_sum)`:**
    * **Base Case:** If `mask` has all bits set to `1`, all numbers have been used, so we return `true`.
    * **Logic:** Iterate through each number `nums[i]`. If the `i`-th bit in `mask` is `0` (meaning `nums[i]` is unused):
        1. Try adding `nums[i]` to the current subset.
        2. Recursively call `can_partition(new_mask, new_sum)`, where `new_mask` has the `i`-th bit set and `new_sum` is `(current_sum + nums[i]) % target_subset_sum`.
        3. If the recursive call returns `true`, it means a valid partition was found, so we can propagate `true` up.
* **Memoization:** Store the result for each `mask` in `memo[mask]` to avoid re-solving the same subproblem.

---

## DP on Intervals (Matrix Chain Multiplication Style) 🔨

This advanced pattern solves problems where you need to find an optimal solution for an interval or sequence by making a series of choices. The key idea is that the optimal solution for an interval `[i, j]` can be found by iterating through all possible "split points" `k` and combining the optimal solutions of the resulting sub-intervals (e.g., `[i, k]` and `[k, j]`).

This pattern often feels like you're working "inside-out" or thinking backwards: instead of deciding the *first* move, you often reframe the problem by deciding the **last action** to be performed on an interval.

### A General Approach for Interval DP

1.  **Define the State:** `dp[i][j]` represents the optimal value (e.g., maximum coins, minimum cost) for the subproblem defined by the interval from index `i` to `j`.

2.  **Find the Recurrence (The Splitting Logic):** The core of the problem is to find the best way to split the interval `[i, j]`. You iterate through every possible index `k` between `i` and `j` that could serve as the point of the final action. The recurrence generally looks like this:
    * `dp[i][j] = max/min ( dp[i][k] + dp[k][j] + cost_of_split(k) )`
    * The `cost_of_split(k)` is the value gained or cost incurred by making the last move at `k`.

3.  **Determine the Base Cases:** The base cases are the smallest possible intervals. Usually, this is an interval of length 1 or 2 (`dp[i][i]` or `dp[i][i+1]`), where the cost or value is zero or a known initial value.

4.  **Set the Iteration Order:** You must solve for smaller intervals before you can solve for larger ones. The standard way is to loop by the **length of the interval**, from 2 up to `n`, and then loop through the start (`i`) of the interval.


### **Example Analysis**

#### 1. **Burst Balloons** 🎈
* **The Challenge:** Bursting a balloon affects its neighbors, which changes the subproblems. A simple left-to-right approach doesn't work.
* **The Reframe:** Instead of thinking about the *first* balloon to burst, think about the **last balloon to burst** in an interval `(i, j)`.
* **State `dp[i][j]`:** The maximum coins obtainable by bursting all balloons in the open interval `(i, j)`. The balloons at `i` and `j` are the fixed boundaries that will be adjacent to the last balloon burst.
* **Recurrence:** If `k` is the last balloon to burst in `(i, j)`, then by the time we burst it, all other balloons between `i` and `j` are already gone. Its neighbors will be `i` and `j`. The coins from this final burst are `nums[i] * nums[k] * nums[j]`. The total is this value plus the optimal solutions for the sub-intervals that were cleared to make this happen.
    * `dp[i][j] = max(nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j])` for all `k` from `i+1` to `j-1`.
* **Base Cases:** `dp[i][i+1] = 0`. An interval with no balloons inside yields 0 coins.

#### 2. **Minimum Cost to Cut a Stick**
* **The Challenge:** The cost of a cut depends on the length of the current stick segment, which depends on prior cuts.
* **The Reframe:** Instead of the *next* cut, think about the **first cut** you make within a given stick segment defined by existing cut points.
* **Preprocessing:** Add `0` and the stick length `n` to the cuts array and sort it. These points now define all possible stick segments. Let this new sorted array be `cuts`.
* **State `dp[i][j]`:** The minimum cost to make all necessary cuts on the stick segment that lies between `cuts[i]` and `cuts[j]`.
* **Recurrence:** If the first cut you make within the segment `(cuts[i], cuts[j])` is at position `cuts[k]`, the cost of this single cut is `cuts[j] - cuts[i]`. After this cut, the problem is divided into two independent subproblems: cutting the stick from `cuts[i]` to `cuts[k]` and from `cuts[k]` to `cuts[j]`.
    * `dp[i][j] = min( (cuts[j] - cuts[i]) + dp[i][k] + dp[k][j] )` for all `k` from `i+1` to `j-1`.
* **Base Cases:** `dp[i][i+1] = 0`. If there are no cut points between two boundaries, no cuts are needed, and the cost is 0.
