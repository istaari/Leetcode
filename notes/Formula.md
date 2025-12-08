### **1. Core Combinatorics & Counting**

These formulas are fundamental for calculating the number of possible combinations.

| Concept                    | Formula           | Explanation                                                                           |
|:---------------------------|:------------------|:--------------------------------------------------------------------------------------|
| **Subsets**                | `2^n`             | For each element, you have 2 choices (include it or not). With n elements, it is 2^n. |
| **Subsequences**           | `2^n - 1`         | Same as subsets, but typically excludes the empty subsequence.                        |
| **Substrings / Subarrays** | `n * (n + 1) / 2` | The count of all contiguous segments.                                                 |
| **Subarrays of size k**    | `n - k + 1`       | A window of size k can start at index 0 and slide up to index n-k.                    |

<br>

### **2. Array Manipulation & Indexing**

Essential tricks for handling array indices correctly.

| Technique                         | Formula                                                                                                                                                                               | Use Case                                                                                                                                                     |
|:----------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Circular Array**                | `i % n`                                                                                                                                                                               | To wrap around an array. Often used in loops that go past the array length.                                                                                  |
| **Negative Index Modulo**         | `(i % n + n) % n`                                                                                                                                                                     | Safely handles negative results from a modulo operation in languages like C++/Java.                                                                          |
| **Right Rotation**                | `(i + k) % n`                                                                                                                                                                         | Finds the new index of element `i` after a right rotation by `k` positions.                                                                                  |
| **Left Rotation**                 | `(i - k + n) % n`                                                                                                                                                                     | Finds the new index of element `i` after a left rotation by `k` positions.                                                                                   |
| **Middle Index (0-indexed)**      | `mid = n / 2`                                                                                                                                                                         | For an odd length array (e.g., n=5, mid=2). This relies on integer division.                                                                                 |
| **Middle Indices (0-indexed)**    | `low = n / 2 - 1`, `high = n / 2`                                                                                                                                                     | For an even length array (e.g., n=6, low=2, high=3). Lower Mid and Upper Mid                                                                                 |
| **Converging Pointers**           | `i = 0`, `j = n - 1`<br>Access `arr[i]` and its symmetric partner `arr[j]` or `arr[n - 1 - i]`.                                                                                       | Checking for palindromes, reversing an array in-place, Two Sum on a sorted array.                                                                            |
| **Fast & Slow Pointers**          | `slow = arr[slow]`, `fast = arr[arr[fast]]`                                                                                                                                           | Primarily for linked lists, but also for finding cycles in arrays where `arr[i]` points to another index. (e.g., LeetCode 287: "Find the Duplicate Number"). |
| **Cyclic Sort**                   | For arrays with numbers in a range (e.g., 1 to n).<br>Logic: `while (nums[i] != i + 1)`, swap `nums[i]` with the element at its correct position: `swap(nums[i], nums[nums[i] - 1])`. | Finding missing or duplicate numbers in a constrained range in O(n) time. (e.g., "Find the Missing Number", "Find All Duplicates in an Array").              |
| **Dutch National Flag Partition** | Three pointers: `low`, `mid`, `high`.<br>Partition an array into 3 sections (`< pivot`, `== pivot`, `> pivot`).                                                                       | The core of the "Sort Colors" problem (LeetCode 75). Also the partitioning step in QuickSort.                                                                |

<br>

### **3. Algorithmic Patterns**

#### **a. Subarray Contribution for Element `arr[i]`**

Let's focus on an element at a generic index `i`.

Okay, let's visualize this with a diagram for `arr = [10, 20, 30, 40]` where `n = 4`.

Now, let's pick `arr[2]` (`30` in our example). So, `i = 2`.

```
Array:    [ 10,  20,  30,  40 ]
Index:      0    1    2    3    (n = 4)
                  ^
                  |
                  i = 2 (Focus on element '30')
```

- **1. Possible Start Indices (from the Left)**

    These are all the indices `j` such that `j <= i`.
    The number of such indices is `i + 1`.
    
    ```
    Array:    [ 10,  20,  30,  40 ]
    Index:      0    1    2    3
                <----------^--------->
                | Possible start points (j <= i)
                |
                Start at index 0
                Start at index 1
                Start at index 2 (This is 'i')
    
    Number of possible start indices = i + 1
                                     = 2 + 1
                                     = 3
    ```

    *Visual Representation of Possible Starting Points:* The `arr[i]` element (our `30`) can be part of a subarray that starts at:
    
    * `arr[0]` (10)
    * `arr[1]` (20)
    * `arr[2]` (30 itself)


- **2. Possible End Indices (to the Right)**

    These are all the indices `k` such that `k >= i`.
    The number of such indices is `n - i`.
    
    ```
    Array:    [ 10,  20,  30,  40 ]
    Index:      0    1    2    3
                <----------^--------->
                           | Possible end points (k >= i)
                           |
                           End at index 2 (This is 'i')
                           End at index 3
    
    Number of possible end indices = n - i
                                   = 4 - 2
                                   = 2
    ```
    
    *Visual Representation of Possible Ending Points:* The `arr[i]` element (our `30`) can be part of a subarray that ends at:

    * `arr[2]` (30 itself)
    * `arr[3]` (40)


- **3. Total Contribution of `arr[i]`**

    To form a subarray that *includes* `arr[i]`, we need to pick **one** of the possible start indices AND **one** of the possible end indices.
    
    Since these choices are independent:
    
    **Total subarrays containing `arr[i]` = (Number of Possible Start Indices) \* (Number of Possible End Indices)**
    
     * For `arr[2]` (element `30`):
          * `= (i + 1) * (n - i)`
          * `= (2 + 1) * (4 - 2)`
          * `= 3 * 2`
          * `= 6`
    
    This confirms that the element `30` contributes to 6 subarrays.

**An O(n) technique to calculate sums over all subarrays without generating them.**

  * **Logic**: Iterate through each element `arr[i]` and calculate how many subarrays it contributes to.
      * Number of possible start indices: `i + 1`
      * Number of possible end indices: `n - i`
  * **Total Sum of All Subarrays**:
      * Contribution of `arr[i]` = `arr[i] * (i + 1) * (n - i)`
      * Total Sum = The sum of these contributions for every element `i`.
  * **Sum of All Odd-Length Subarrays**:
      * Contribution of `arr[i]` = `arr[i] * (((i + 1) * (n - i) + 1) / 2)` (using integer division).

#### **B. Binary Search**

Used for searching in a sorted array or a monotonic search space.

  * **Standard Middle (avoids overflow)**: `mid = low + (high - low) / 2`
      * Select the **lower-middle** element in an even-sized range.
  * **Upper Middle**: `mid = low + (high - low + 1) / 2`
      * Select the **upper-middle** element. Useful for avoiding infinite loops in certain search conditions.

#### **C. Sliding Window**

Efficiently processes contiguous blocks of data.

  * **Window Size**: `window_size = right_pointer - left_pointer + 1`
  * **Frequency Maps/Arrays**: Use a hash map or an array to track element frequencies in the window.
      * Digits `0-9`: `new int[10]`
      * Lowercase English letters: `new int[26]`
      * All ASCII characters: `new int[128]` or `new int[256]`

#### **D. Prefix Sum**

Pre-calculates sums to answer range sum queries in O(1) time.

  * `prefix[i] = arr[0] + ... + arr[i]`
  * **Sum of Subarray `[i, j]`**: `prefix[j] - prefix[i-1]`
  * **Problem: Find subarray sum equal to k**
      * Logic: `prefix[j] - prefix[i-1] = k`, which means `prefix[i-1] = prefix[j] - k`.
      * Use a hash map to check if the value `prefix[j] - k` has been seen before.
  * **Problem: Find subarray sum divisible by k**
      * Logic: `(prefix[j] - prefix[i-1]) % k == 0`, which means `prefix[j] % k == prefix[i-1] % k`.
      * Use a hash map to count the frequencies of remainders (`prefix_sum % k`).



#### **E. Greedy**

1. Core Concept & Philosophy

| Concept                  | The "Formula" / Proof Strategy                                                                                                                                                                                                                    | When to be Cautious                                                                                                                                             |
|:-------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Greedy Choice**        | At each step, choose the best possible option *right now* without considering future consequences.                                                                                                                                                | If a local choice could negatively impact the range of future optimal choices, greedy will likely fail. This often indicates a **Dynamic Programming** problem. |
| **Proof of Correctness** | **1. Greedy Choice Property:** Prove that a locally optimal choice will be part of *some* globally optimal solution.<br>**2. Optimal Substructure:** Prove that an optimal solution to the problem contains optimal solutions to its subproblems. | You don't need to formally prove this in an interview, but you must have a strong intuition for *why* your greedy choice works.                                 |


2. The Sorting Heuristic (The Practical "Formula")


| Sort By...                                   | Greedy Choice & Rationale                                                                                                        | Common LeetCode Application                                                                                                                                          |
|:---------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **End Time** (Ascending)                     | Choose the interval that **finishes earliest**. This frees up resources for other potential choices as quickly as possible.      | **Activity Selection / Interval Scheduling.** Finding the maximum number of non-overlapping intervals. (e.g., "Non-overlapping Intervals").                          |
| **Start Time** (Ascending)                   | Process items in a chronological or sequential order. This is for problems where the start order matters.                        | **Interval Merging.** (e.g., "Merge Intervals", "Meeting Rooms").                                                                                                    |
| **Value / Weight Ratio** (Descending)        | Choose the item with the most "bang for your buck". Maximizes value for a given capacity.                                        | **Fractional Knapsack.**                                                                                                                                             |
| **Value / Size** (Largest or Smallest First) | Handle the most constrained or most impactful items first. <br>e.g., Give the smallest cookie to the child with the least greed. | **Assignment Problems.** (e.g., "Assign Cookies").<br>e.g., Match the people with the lightest weights with the heaviest to fit in a boat. ("Boats to Save People"). |
| **A Custom Heuristic**                       | Sort by a problem-specific property. For example, `start_i + length_i` to find the farthest reachable point.                     | **Jump Game II.** The greedy choice is to make the jump that offers the maximum reach for the *next* jump.                                                           |


<br>

### **4. Data Structures**

#### **Array Representation Complete Binary Tree**

Used in heaps and segment trees. Relies on integer division.

  * **For 1-based indexing:** (Array index `1...n`)
      * Left child of node `i`: `2 * i`
      * Right child of node `i`: `2 * i + 1`
      * Parent of node `i`: `i / 2`
  * **For 0-based indexing:** (Array index `0...n-1`)
      * Left child of node `i`: `2 * i + 1`
      * Right child of node `i`: `2 * i + 2`
      * Parent of node `i`: `(i - 1) / 2`


####  General Binary Tree Properties

These formulas apply to any binary tree, where $N$ is the total number of nodes.

| Property | Formula | Description |
| :--- | :--- | :--- |
| **Total Edges** | $E = N - 1$ | In a non-empty binary tree with $N$ nodes, there are always $N-1$ edges (connections). |
| **Max Nodes at Level $l$** | $2^l$ | The maximum number of nodes possible at level $l$ (root is $l=0$). |
| **Max Nodes for Height $h$** | $N_{max} = 2^{h+1} - 1$ | The maximum total number of nodes in a binary tree of height $h$ (occurs in a perfect binary tree). |
| **Min Height for $N$ Nodes** | $h_{min} = \lfloor \log_2 N \rfloor$ | The minimum possible height for a binary tree with $N$ nodes (occurs in a complete binary tree). |


#### Full Binary Tree Properties

A **Full Binary Tree** is a tree where every node has either 0 or 2 children.

| Property | Formula | Description |
| :--- | :--- | :--- |
| **Leaves vs. Internal Nodes** | $L = T + 1$ | The number of leaf nodes ($L$) is always one more than the number of internal nodes with two children ($T$). |
| **Total Nodes** | $N = 2T + 1$ | The total number of nodes ($N$) in terms of internal nodes with two children ($T$). |
| **Total Nodes** | $N = 2L - 1$ | The total number of nodes ($N$) in terms of leaf nodes ($L$). |


### **Bit Manipulation**


#### **XOR Properties**

| Property                 | Meaning                       |
|--------------------------|-------------------------------|
| `a ^ b = c  ⇒ b ^ c = a` | You can reverse XOR           |
| `x ^ 0 = x`              | XOR with 0 returns same value |
| `x ^ x = 0`              | XOR with itself is 0          |


#### **Bit Shift Tricks**

| Operation | Meaning       |
|-----------|---------------|
| `1 << n`  | Equals `2^n`  |
| `a >> 1`  | Divide by 2   |
| `a << 1`  | Multiply by 2 |


#### **Set Operations Using Bitmask**


The key idea is that **an integer can represent a set**. Each bit position (0, 1, 2, 3, etc.) corresponds to an element. If the bit is `1` ("on"), the element is **in** the set. If the bit is `0` ("off"), the element is **not** in the set.

Let's use two simple sets for all our examples:

  * **Set A = 5**

      * Binary: `...00000101`
      * Bits 0 and 2 are `1`.
      * This represents the set **`{0, 2}`**.

  * **Set B = 6**

      * Binary: `...00000110`
      * Bits 1 and 2 are `1`.
      * This represents the set **`{1, 2}`**.

Here is the refined table, explaining each operation using these two sets.

-----

### **Simplified Set Operations (Bitmask)**

| Operation | Code | Simplified Explanation (Using A = `{0, 2}` and B = `{1, 2}`) |
| :--- | :--- | :--- |
| **Union** ($A \cup B$) | `A \| B` | **Goal:** Get all elements from A *or* B.<br>**Set:** `{0, 2}` $\cup$ `{1, 2}` = `{0, 1, 2}`<br>**Bitmask:** `...0101 \| ...0110` = `...0111` (which is integer `7`) |
| **Intersection** ($A \cap B$) | `A & B` | **Goal:** Get elements in *both* A and B.<br>**Set:** `{0, 2}` $\cap$ `{1, 2}` = `{2}`<br>**Bitmask:** `...0101 & ...0110` = `...0100` (which is integer `4`) |
| **Subtraction** ($A - B$) | `A & ~B` | **Goal:** Get elements in A *but not* in B.<br>**Set:** `{0, 2}` - `{1, 2}` = `{0}`<br>**Bitmask:** `...0101 & ~...0110` = `...0101 & ...1001` = `...0001` (integer `1`) |
| **Negation** (Complement) | `~A` | **Goal:** Get all elements *not* in A.<br>**Set:** Complement of `{0, 2}` is `{1, 3, 4, 5, ...}`<br>**Bitmask:** `~...00000101` = `...11111010` |
| **Set bit** (Add) | `A \|= (1 << n)` | **Goal:** Add element **`n`** to the set.<br>**Example:** Add element **3** to A (`{0, 2}`).<br>**Bitmask:** `...0101 \| (1 << 3)` = `...0101 \| ...1000` = `...1101` (integer `13`).<br>**Result:** New set is `{0, 2, 3}`. |
| **Clear bit** (Remove) | `A &= ~(1 << n)` | **Goal:** Remove element **`n`** from the set.<br>**Example:** Remove element **2** from A (`{0, 2}`).<br>**Bitmask:** `...0101 & ~(1 << 2)` = `...0101 & ~...0100` = `...0101 & ...1011` = `...0001` (integer `1`).<br>**Result:** New set is `{0}`. |
| **Test bit** (Check) | `(A & (1 << n)) != 0` | **Goal:** Check if element **`n`** is in the set.<br>**Example:** Is element **2** in A (`{0, 2}`)?<br>**Bitmask:** `...0101 & (1 << 2)` = `...0101 & ...0100` = `...0100` (which is `4`).<br>**Result:** `4 != 0`, so **True**. |
| **Extract last bit** | `A & -A` | **Goal:** Get just the *smallest* element from the set.<br>**Example:** Use B (`{1, 2}`), which is `...0110`.<br>**Bitmask:** `...0110 & -...0110` = `...0010` (integer `2`).<br>**Result:** The set `{1}`. This isolates the smallest element, **1**. |
| **Remove last bit** | `A & (A - 1)` | **Goal:** Remove the *smallest* element from the set.<br>**Example:** Use B (`{1, 2}`), which is `...0110`.<br>**Bitmask:** `...0110 & (...0110 - 1)` = `...0110 & ...0101` = `...0100` (integer `4`).<br>**Result:** The new set is `{2}`. The smallest element, **1**, was removed. |
| **All 1-bits** (Universal) | `~0` | **Goal:** Get a set containing *all* possible elements.<br>**Bitmask:** `~0` = `...11111111`<br>**Result:** The set `{0, 1, 2, 3, 4, ...}` |





### **Matrix / Grid Cheat Sheet**

#### **Directional Vectors**

| Directions              | Use Case                  | Vectors (row, col)                                               |
|-------------------------|---------------------------|------------------------------------------------------------------|
| **4 Directions** (NSEW) | BFS, DFS, Flood Fill      | `{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}` (Up, Down, Left, Right)     |
| **8 Directions** (All)  | Knight Moves, Word Search | `{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}}` |

#### **Matrix ↔ Array Conversion**

For an `N x M` matrix (N rows, M columns).

| Operation         | Formula                              |
|-------------------|--------------------------------------|
| **2D → 1D Index** | `index = row * M + col`              |
| **1D Index → 2D** | `row = index / M`, `col = index % M` |

#### **Common Operations & Tricks**

| Operation                        | Key Logic / Formula                             |
|----------------------------------|-------------------------------------------------|
| **Transpose** (In-place, Square) | `swap(matrix[i][j], matrix[j][i])` for `j > i`  |
| **Rotate 90° Clockwise**         | 1. Transpose matrix <br> 2. Reverse each row    |
| **Rotate 90° Anti-Clockwise**    | 1. Transpose matrix <br> 2. Reverse each column |
| **Sudoku 3x3 Box ID**            | `box_id = (row / 3) * 3 + (col / 3)`            |