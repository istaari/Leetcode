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


## Amortized Analysis

### 1. Aggregate Method
Calculate the total cost of n operations and divide by n.

**Example: Dynamic Array (ArrayList/Vector)**

Imagine you're trying to figure out your average daily expenses for the month.

- Most days, you just spend a little on lunch and maybe a coffee. These are your cheap operations.

- But once a month, you have to pay your rent, which is a very large, expensive operation.

If you only looked at the day you pay rent, you'd think your daily spending is incredibly high. The Aggregate Method says this is misleading. Instead, it tells you to add up all your expenses for the entire month (the total cost) and then divide by the number of days (the number of operations).

When you do this, the huge, one-time cost of rent is spread out over all the cheap days. Your calculated average or "amortized" daily cost ends up being very reasonable and a much more realistic picture of your finances.

**Analyzing the Results**

Now, let's look at the totals after inserting **9 elements (N = 9)**.

1.  **Total Insertion Cost:** We did 9 insertions, and each one had an insertion cost of 1.
    * `Total Insertion Cost = 9` (This will always be `N`).

2.  **Total Copy Cost:** Let's add up only the `Copy Cost` column.
    * `Total Copy Cost = 0 + 1 + 2 + 4 + 8 = 15`

3.  **Grand Total Cost:**
    * `Grand Total Cost = (Total Insertion Cost) + (Total Copy Cost) = 9 + 15 = 24`

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


## Problems Types


### **Constructive Algorithms**

**What they are:** Constructive algorithms are a type of problem where you are asked to **build or construct a solution** that satisfies a given set of constraints. Instead of just determining if a solution exists, you need to provide a concrete example. These problems often require a bit of creativity and logical thinking to come up with a valid construction.

**Key characteristics:**
* **Directly building a solution:** You're not searching for a pre-existing answer; you're creating one.
* **Ad-hoc or greedy approaches:** Many constructive problems can be solved by making locally optimal choices at each step.
* **Mathematical insights:** Sometimes, a mathematical property or pattern is the key to constructing the solution.

---

### **Implementation Algorithms**

**What they are:** Implementation-heavy problems are less about discovering a clever algorithm and more about **carefully and accurately translating a given set of rules or a well-known algorithm into code**. These problems test your attention to detail, your ability to handle edge cases, and your coding proficiency.

**Key characteristics:**
* **Clear instructions:** The problem statement usually describes a process or a set of rules to follow.
* **Focus on details:** The main challenge is to handle all the specific conditions and constraints correctly.
* **Data structures:** You might need to use specific data structures to manage the information efficiently.

---

### **Brute Force Algorithms**

**What they are:** A brute force approach involves **systematically checking every possible solution** to a problem until you find the correct one. It's a straightforward, "try everything" method. While often inefficient, a brute force solution can be a good starting point and may be sufficient for problems with small constraints.

**Key characteristics:**
* **Exhaustive search:** It explores the entire search space of possible solutions.
* **Simple to implement:** The logic is usually straightforward, involving loops to iterate through all possibilities.
* **Time complexity:** Brute force solutions often have a high time complexity and may be too slow for larger inputs.

---

### **Examples on Codeforces and LeetCode**

Sure, here is the same information reformatted with the problem types arranged column-wise.

### **Problem Examples by Type**

| Platform | Constructive 🧠 | Implementation 💻 | Brute Force 🐢 |
| :--- | :--- | :--- | :--- |
| **Codeforces** | [K-th Not Divisible by n (1352C)](https://codeforces.com/problemset/problem/1352/C)<br>[Honest Coach (1360B)](https://codeforces.com/problemset/problem/1360/B)<br>[Required Remainder (1374A)](https://codeforces.com/problemset/problem/1374/A) | [Football (96A)](https://codeforces.com/problemset/problem/96/A)<br>[Boy or Girl (236A)](https://codeforces.com/problemset/problem/236/A)<br>[Petya and Strings (112A)](https://codeforces.com/problemset/problem/112/A) | [Team (231A)](https://codeforces.com/problemset/problem/231/A)<br>[Young Physicist (69A)](https://codeforces.com/problemset/problem/69/A)<br>[Presents (136A)](https://codeforces.com/problemset/problem/136/A) |
| **LeetCode** | [Find Permutation (484)](https://leetcode.com/problems/find-permutation/)<br>[Beautiful Arrangement (526)](https://leetcode.com/problems/beautiful-arrangement/)<br>[Construct Binary Tree (105)](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | [Spiral Matrix (54)](https://leetcode.com/problems/spiral-matrix/)<br>[Text Justification (68)](https://leetcode.com/problems/text-justification/)<br>[Rotate Image (48)](https://leetcode.com/problems/rotate-image/) | [3Sum (15)](https://leetcode.com/problems/3sum/)<br>[Subsets (78)](https://leetcode.com/problems/subsets/)<br>[Permutations (46)](https://leetcode.com/problems/permutations/) |


## Design by Contract


###  Preconditions

A **precondition** is a condition that **must be true *before* a function or method is called**. It's the "contract" that the calling code must fulfill to use the function correctly. The function itself *assumes* the precondition is met and doesn't bother checking for it.

* **Analogy:** The precondition for using an ATM is that you **must insert a valid debit card**. The ATM doesn't try to function with a library card; it *assumes* you've met the requirement to start the process.
* **Code Example:** For a function `calculateSquareRoot(number)`, a precondition is that `number >= 0`. The function relies on the caller to provide a non-negative number.


###  Postconditions

A **postcondition** is a condition that the function **guarantees will be true *after* it finishes executing**, provided the preconditions were met. It's the promise the function makes about its result.

* **Analogy:** The postcondition of a successful ATM withdrawal is that **you have received cash and your account balance has been updated correctly**. The machine guarantees this outcome.
* **Code Example:** For the `calculateSquareRoot(number)` function, a postcondition is that the `(return_value * return_value)` will be very close to the original `number`.


### **How They Relate to Invariants**

This trio forms a powerful logical framework:

| Concept | When it Must Be True | Whose Responsibility? |
| :--- | :--- | :--- |
| **Precondition** | **Before** the code runs. | The **Caller** |
| **Invariant** | **During** the code's execution. | The **Code Itself** |
| **Postcondition** | **After** the code finishes. | The **Code Itself** |

Think of it like a journey:
* **Precondition:** You must have a full tank of gas *before* you start your road trip.
* **Invariant:** Your car's engine temperature must remain within a safe range *during* the entire trip.
* **Postcondition:** You will have arrived at your destination *after* the trip is complete.

---

### What are Invariants?

In computer science, an **invariant** is a condition or a property that remains true throughout the execution of a program or a part of it, like a loop or the lifetime of an object. Think of it as a rule that is never broken.

Here's a simple analogy: Imagine you have a bag of marbles that only contains red and blue marbles. An invariant of this system could be: "The total number of marbles in the bag is always a non-negative integer." No matter how many marbles you add or remove, as long as you follow the rules of the system (don't add half a marble, for instance), this statement will always be true.

### Types of Invariants in Programming

Invariants are a powerful tool for reasoning about the correctness of your code. Here are some common types:

* **Loop Invariants:** This is a condition that is true before a loop starts, and it remains true before and after each iteration of the loop. This is extremely useful for proving that a loop behaves as expected.

    * **Example:** In an algorithm to find the maximum element in an array, a loop invariant could be: "At the end of each iteration `i`, the `max_so_far` variable holds the maximum value in the subarray from index 0 to `i`."

* **Class Invariants:** In object-oriented programming, a class invariant is a condition that must be true for any object of that class whenever it is not in the middle of executing one of its methods. This ensures that the object is always in a valid state.

    * **Example:** If you have a `Date` class with `day`, `month`, and `year` properties, a class invariant would be that the `day` is always between 1 and 31, the `month` is between 1 and 12, and so on.

### Why are Invariants Important?

Invariants are a fundamental concept in programming for several reasons:

* **Correctness:** They help you prove that your algorithms are correct. If you can establish a loop invariant that, upon the loop's termination, implies the desired outcome, you have a strong argument for the correctness of your code.

* **Debugging:** When a program fails, checking if an invariant has been violated can quickly lead you to the source of the bug. If an invariant is broken, you know that the error must have occurred in the code that was supposed to maintain it.

* **Design:** Thinking about invariants helps you design better, more robust code. By defining the rules that your data structures and algorithms must follow, you can create more predictable and reliable systems.

### Simple Analogy: Sorting an Array

Let's consider sorting an array in ascending order. A common approach is to iterate through the array and place elements in their correct positions. A loop invariant for many sorting algorithms (like insertion sort) could be:

> "After `i` iterations of the loop, the first `i` elements of the array are sorted relative to each other."

This doesn't mean they are in their final, globally sorted positions, but that the subarray `A[0...i-1]` is sorted. By maintaining this invariant throughout the loop, you can be confident that when the loop finishes, the entire array will be sorted.

In essence, invariants are a way of making formal, provable statements about the behavior of your code, which is a cornerstone of writing correct and reliable software.

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