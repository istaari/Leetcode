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

# Bitmask

Bitmasking is a powerful technique in Dynamic Programming (DP) used to represent the **state of a set** using a single integer.

### The Core Concept: Mapping Sets to Integers

Imagine you have a set of $N$ items (labeled $0$ to $N-1$). We can represent any subset of these items using an integer where the **$i$-th bit** corresponds to the **$i$-th item**.

* **Bit is 1:** The item is in the subset (Used/Visited/On).
* **Bit is 0:** The item is not in the subset (Available/Unvisited/Off).

#### Example

Suppose we have 3 tasks: $\{A, B, C\}$.

* Task $A$ is index $0$.
* Task $B$ is index $1$.
* Task $C$ is index $2$.

If we have completed Task A and Task C, but not B, our set is $\{A, C\}$.
In binary:

* Index 2 (C): **1**
* Index 1 (B): **0**
* Index 0 (A): **1**

The binary number is $101_2$, which is the decimal integer **5**.
In our DP table, `dp[5]` would store the result for the state where A and C are completed.

-----

### Essential Bitwise Operations

To manipulate these sets, you use bitwise operators. Let's say `mask` is your current integer representing the set, and `i` is the index of the element you are interested in.

| Operation | Code | Explanation |
| :--- | :--- | :--- |
| **Add** element $i$ | `mask | (1 << i)` | Sets the $i$-th bit to 1. |
| **Remove** element $i$ | `mask & ~(1 << i)` | Sets the $i$-th bit to 0. |
| **Check** if $i$ exists | `(mask >> i) & 1` | Returns 1 if present, 0 if not. |
| **Toggle** element $i$ | `mask ^ (1 << i)` | Flips 0 to 1, or 1 to 0. |
| **Set of all $N$ items** | `(1 << N) - 1` | Creates a mask of $N$ ones (e.g., $111_2$). |


# Digit

# Interval

# Knapsack

# State Machines

# String

# Tree