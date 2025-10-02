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