
# Algorithms and Data Structures

## String


### 1\. Hashing & Frequency Counting

This is the most frequent pattern. The core idea is to use a hash map (or an array as a frequency map) to store counts of characters or words.

**Key Data Structures:** `HashMap<Character, Integer>`, `int[26]`, `int[128]`, `HashSet`

**Common Problems:**

  * Anagram detection (`s1` and `s2` have the same character counts).
  * Isomorphic strings (character mapping).
  * Finding duplicates or the first unique character.

**[Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)**

This problem is a classic combination of two patterns: **Hashing + Heap**.

1.  **Hashing**: Use a `HashMap<String, Integer>` to store the frequency of each word.
2.  **Heap (`PriorityQueue`)**: Use a `PriorityQueue` to find the top `k` elements efficiently. You need a custom comparator to handle the sorting logic.

*Your code snippet is perfect for this:*

```java
// Custom comparator for the PriorityQueue
// 1. Sort by frequency in descending order.
// 2. If frequencies are equal, sort alphabetically (lexicographically) in ascending order.
Queue<String> queue = new PriorityQueue<>((a, b) -> {
    if (map.get(a).equals(map.get(b))) {
        return a.compareTo(b); 
    } else {
        return map.get(b) - map.get(a);
    }
});
```

### 2\. Two Pointers

This technique uses two pointers to iterate through the string, often leading to optimal $O(N)$ time and $O(1)$ space solutions.

**Common Approaches:**

  * **Converging Pointers**: Pointers start at opposite ends and move toward the center (e.g., palindrome check).
  * **Diverging Pointers**: Pointers start at the same place and move outwards (e.g., expand around center).
  * **Read/Write Pointers**: One pointer reads ahead while the other writes to modify the string/array in place.

**[String Compression](https://leetcode.com/problems/string-compression/)**

This is a perfect example of the **Read/Write Pointers** approach.

  * A `read` pointer scans the array to find groups of consecutive identical characters.
  * A `write` pointer stays at the position where the next compressed character and count should be written.
  * Your description is spot on: "Count the adjacent characters using a forward inner while loop then add the character and count to the result."

### 3\. Palindrome-Specific Techniques

**[Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)**

Your approach is the standard and most efficient one for this problem.

  * **Technique**: **Expand Around Center**.
  * **Logic**: Every palindrome has a center. This center can be a single character (for odd-length palindromes like "racecar") or the space between two characters (for even-length palindromes like "aabbaa"). We iterate through all possible centers and expand outwards as long as the characters match.

*Your code snippet is an excellent implementation:*

```java
int countSubstrings(String s) {
    int n = s.length();
    int ans = 0;
    for (int i = 0; i < n; i++) {
        // Expand around a single character center (odd length)
        ans += expandAndCount(s, i, i);
        // Expand around a two-character center (even length)
        ans += expandAndCount(s, i, i + 1);
    }
    return ans;
}

int expandAndCount(String s, int left, int right) {
    int count = 0;
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        count++; // Found a valid palindrome
        left--;
        right++;
    }
    return count;
}
```

### 4\. Sliding Window

A powerful technique for finding a substring that satisfies a certain condition. A "window" is maintained by two pointers, and it expands and contracts as it moves through the string.

**Common Problems:**

  * Longest Substring Without Repeating Characters.
  * Minimum Window Substring.
  * Finding all anagrams of a pattern string.

### 5\. Advanced Techniques & Unique Patterns

**[Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character/)**

This problem has several clever solutions.

  * **Your Approach (TreeSet)**: This is an interesting solution.

    1.  Store the indices of the target character in a `TreeSet`.
    2.  For each index `i` in the string, use `TreeSet.floor(i)` and `TreeSet.ceiling(i)` to find the nearest target indices on both sides. This works and has a time complexity of $O(N \log K)$, where K is the number of target characters.

  * **Alternative Common Pattern (Two-Pass)**: This is an important $O(N)$ pattern to know.

    1.  **Left-to-Right Pass**: Iterate from left to right. `dist[i]` is the distance from `i` to the *previous* occurrence of the target character. `dist[i] = dist[i-1] + 1`.
    2.  **Right-to-Left Pass**: Iterate from right to left. Update `dist[i]` by comparing with the distance to the *next* occurrence. `dist[i] = min(dist[i], dist[i+1] + 1)`.

### 6\. Dynamic Programming on Strings

Used for optimization problems where the solution is built upon solutions to subproblems. Usually involves a 2D `dp` table.

**Common Problems:**

  * **Longest Common Subsequence**: `dp[i][j]` = LCS of `s1[0..i]` and `s2[0..j]`.
  * **Edit Distance**: `dp[i][j]` = min edits to make `s1[0..i]` equal to `s2[0..j]`.
  * **Word Break**: `dp[i]` = true if `s[0..i]` can be segmented.

### 7\. Backtracking & Recursion

Used for generating all possible combinations or permutations of strings that satisfy a condition.

**Common Problems:**

  * Generate Parentheses.
  * Letter Combinations of a Phone Number.
  * Palindrome Partitioning.

### 8\. Tries (Prefix Trees)

A specialized tree data structure used for problems involving prefixes and dictionaries.

**Common Problems:**

  * Implement an Autocomplete System.
  * Word Search II (finding words from a dictionary in a 2D grid).

---

## Array


---


## Binary Search

### **Pattern 1: Standard Binary Search & Its Variations**

#### Template 1: Exact Match (`while (low <= high)`)

This template is ideal for when you are searching for an exact element and can exit as soon as it's found. The loop terminates when `low > high`.

```java
int low = 0, high = nums.length - 1;
while (low <= high) {
    int mid = low + (high - low) / 2;
    if (nums[mid] == target) {
        return mid; // Found
    } else if (nums[mid] < target) {
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
return -1; // Not found
```


#### Template 2: The "Leftmost" Boundary (Round Down)

This template is designed to find the `lower_bound`—the index of the first element that is greater than or equal to the target.

```java
// Finds the FIRST element >= target
int low = 0, high = nums.length - 1;
while (low < high) {
    int mid = low + (high - low) / 2; // Standard mid, rounds down
    if (nums[mid] >= target) {
        high = mid;
    } else {
        low = mid + 1;
    }
}
// After loop, low == high. Check if this candidate is the target.
return nums.length > 0 && nums[low] == target ? low : -1;
```

**Use Cases for Template 2:**

1.  **The first occurrence of an element.**
2.  **The `ceil` of a number:** Finding the smallest element `>= target`.
3.  **Search Insert Position:** This template directly solves this problem (the final `low` is the answer).
4.  Any problem that requires finding the **leftmost boundary** or the first time a condition becomes true.


#### Template 3: The "Rightmost" Boundary (Round Up)

This template is designed to find the index of the last element that is less than or equal to the target.

```java
// Finds the LAST element <= target
int low = 0, high = nums.length - 1;
while (low < high) {
    int mid = low + (high - low + 1) / 2; // CRITICAL: Rounds UP
    if (nums[mid] <= target) {
        low = mid;
    } else {
        high = mid - 1;
    }
}
// After loop, low == high. Check if this candidate is the target.
return nums.length > 0 && nums[low] == target ? low : -1;
```

**Use Cases for Template 3:**

1.  **The last occurrence of an element.**
2.  **The `floor` of a number:** Finding the largest element `<= target`.
3.  Any problem that requires finding the **rightmost boundary** or the last time a condition is true.

### **Pattern 2: Binary Search on the Answer**

This is a powerful technique for optimization problems that ask for the "minimum possible" or "maximum possible" value that satisfies a certain condition.

**Explanation:**
Instead of searching for an element in an array, you binary search on the *range of possible answers*. For each `mid` value (which is a potential answer), you have a validation function `isPossible(mid)` that checks if it's feasible to achieve the goal with that value. The search space is monotonic: if an answer `X` is possible, all answers "better" than `X` (e.g., `X+1` for maximization, `X-1` for minimization) are also possible.

**Template:**

1.  **Define the Search Space:** Determine the `low` (minimum possible answer) and `high` (maximum possible answer).
2.  **Create a Validation Function:** `boolean isPossible(value)`.
3.  **Binary Search:**
    * If `isPossible(mid)` is true, it means `mid` is a potential answer. We try for a "better" one (e.g., smaller for minimization problems, so `high = mid - 1`).
    * If `isPossible(mid)` is false, `mid` is not a valid answer, so we must consider "worse" answers (e.g., `low = mid + 1`).

**Examples:**

* [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/):
    * **Answer Range:** Speed `k` can be from `1` to `max(piles)`.
    * **`isPossible(speed)`:** Can Koko eat all bananas within `h` hours at the given `speed`?
* [Capacity to Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/):
    * **Answer Range:** Capacity can be from `max(weights)` to `sum(weights)`.
    * **`isPossible(capacity)`:** Can all packages be shipped within `D` days with the given `capacity`?
* [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/):
    * **Answer Range:** Days can be from `1` to `max(bloomDay)`.
    * **`isPossible(days)`:** Can we make `m` bouquets if we wait for the given number of `days`?

### **Pattern 3: Searching in Rotated Sorted Arrays**

This pattern applies to an array that was sorted and then rotated some number of times. The array consists of two sorted subarrays.

**Explanation:**

At each step, perform two checks:

1.  **Find the sorted half:** Is `[low...mid]` or `[mid...high]` sorted?
    * For `nums = [4, 5, 6, 7, 0, 1, 2]`, if `mid` points to `7`, the left half `[4, 5, 6, 7]` is the sorted one.

2.  **Locate the target:** Is the `target` within the range of that sorted half? If yes, search it; if no, search the other half.
    * If `target = 5`, it is within the sorted half's range `[4...7]`, so you search that part.
    * If `target = 1`, it is not in that range, so you must search the other half `[0, 1, 2]`.

**Template:**

```java
while (low <= high) {
    int mid = low + (high - low) / 2;
    if (nums[mid] == target) return mid;

    // Check if the left half (low...mid) is sorted
    if (nums[low] <= nums[mid]) {
        if (target >= nums[low] && target < nums[mid]) {
            high = mid - 1; // Target is in the sorted left half
        } else {
            low = mid + 1;  // Target is in the right half
        }
    } 
    // Otherwise, the right half (mid...high) must be sorted
    else {
        if (target > nums[mid] && target <= nums[high]) {
            low = mid + 1; // Target is in the sorted right half
        } else {
            high = mid - 1; // Target is in the left half
        }
    }
}
```

**Examples:**

* [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
* [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
* [Find Minimum in Rotated Sorted Array (with Duplicates)](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)

### **Pattern 4: Searching on Monotonic(Peaks/Valleys)**

This pattern is used on arrays where values increase and then decrease (a "mountain" or bitonic array), and the goal is to find the peak element.

**Explanation:**

The strategy is to find the peak by checking the "slope" at the midpoint. By comparing `nums[mid]` with its right neighbor `nums[mid+1]`, you can tell if you are on an upward or downward slope, allowing you to discard half the array.

Let's use the example `nums = [0, 2, 4, 6, 3, 1]`. The peak is `6`.

* **Initial Step:** `low = 0`, `high = 5`. Let's say `mid = 2` (`nums[mid] = 4`).

    * We compare `nums[mid]` (4) with `nums[mid+1]` (6).
    * Since `4 < 6`, we are on the **uphill** slope. This means the peak must be to the right of `mid`.
    * **Action:** We discard the left half by setting `low = mid + 1`.

* **Next Step:** The search space is now `[3, 5]`. Let's say `mid = 4` (`nums[mid] = 3`).

    * We compare `nums[mid]` (3) with `nums[mid+1]` (1).
    * Since `3 > 1`, we are on the **downhill** slope. This means `mid` could be the peak, or the peak is to its left.
    * **Action:** We discard the right half by setting `high = mid`.

The loop continues until `low` and `high` converge on the single index of the peak element.

**Template:**

This template uses the `while (low < high)` structure, which is perfect for converging on a single point.

```java
/**
 * Finds the index of a peak element in a mountain array.
 */
int findPeakElement(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low < high) {
        int mid = low + (high - low) / 2;
        
        // Check the slope at mid
        if (nums[mid] < nums[mid + 1]) {
            // Uphill slope: Peak is to the right of mid.
            low = mid + 1;
        } else {
            // Downhill slope: mid could be the peak, or the peak is to the left.
            high = mid;
        }
    }
    
    // The loop terminates when low == high, which is the index of the peak.
    return low;
}
```


### **Pattern 5: Searching in 2D Matrices**

Binary search can be adapted to 2D matrices that have specific sorting properties.

**Explanation:**
There are two main sub-patterns:

1.  **Matrix as a Flattened 1D Array:** If the matrix is sorted such that the last element of row `i` is less than the first element of row `i+1`, you can treat the entire `M x N` matrix as a single sorted array of length `M*N`. An index `mid` in this virtual array maps to `matrix[mid / N][mid % N]`.

    * **Example:** [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)

2.  **Staircase / Saddleback Search:** If each row is sorted and each column is sorted, you can't flatten it. Instead, start at a strategic corner (e.g., top-right or bottom-left).

    * From the **top-right** corner:
        * If `target` is smaller than the current element, it can't be in this column (all elements below are larger), so move left (`col--`).
        * If `target` is larger, it can't be in this row (all elements to the left are smaller), so move down (`row++`).
    * This approach eliminates one row or one column at each step.
    * **Example:** [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)

### **Pattern 6: Binary Search on Real Numbers**

This pattern is for finding a value in a continuous range, like the square root of a number, where absolute precision is needed.

**Explanation:**
The core logic is the same, but the termination condition changes. Instead of the loop ending when `low` and `high` cross, it runs for a fixed number of iterations (e.g., 100) or until the search space `(high - low)` is smaller than a tiny epsilon value (e.g., `1e-7`). This guarantees the answer is found to the desired precision.

**Template (for Square Root):**

```java
double low = 0, high = x;
double epsilon = 1e-7; // Desired precision

while ((high - low) > epsilon) {
    double mid = low + (high - low) / 2;
    if (mid * mid > x) {
        high = mid;
    } else {
        low = mid;
    }
}
// 'low' or 'high' is the answer to the required precision
```

**Example:**

* [Minimize Max Distance to Gas Station](https://leetcode.com/problems/minimize-max-distance-to-gas-station/description/)

---

## Linked List

### **Pattern 1: The Two Pointer Technique (Fast & Slow)**

### **Pattern 2: The Sentinel (Dummy) Node**

### **Pattern 3: Reversing a Linked List**

### **Pattern 4: Using a Hash Map for Visited Nodes**

### **Pattern 5: Merging & Splitting (Divide and Conquer)**

### **Pattern 6: Cycle Analysis (Advanced Two Pointers)**


---

## Stack


### **Pattern 1: LIFO Basics (Reversal and "Undo")**



### **Pattern 2: Complex Simulation & State Tracking**



### **Pattern 3: Parentheses, Paths, and Expression Evaluation**


### Three Main Patterns of Problems

You can generally group these problems into three categories of increasing complexity.

#### 1. Validation Problems (e.g., "Valid Parentheses")

* **The Question:** Is this sequence valid?
* **The Strategy:** Use the stack as a checklist of required closing items.
    * **Push Condition:** When you see an "opening" character (`(`, `{`, `[`), push its required "closing" counterpart onto the stack.
    * **Pop Condition (The Trigger):** When you see a "closing" character.
    * **Pop Logic:** Pop from the stack and check if the popped character matches the current closing character. If it doesn't match, or if the stack was empty, the string is invalid.
    * **Final Check:** After the loop, the stack must be empty. If it's not, there are unfinished tasks (unclosed parentheses), and the string is invalid.

#### 2. Processing & Transformation Problems (Your `RemoveOutermostParentheses` is a perfect example)

* **The Question:** Simplify, process, or remove parts of a sequence.
* **The Strategy:** Use the stack to track the "current valid state" or "depth." Your decision for the current character often depends on the state of the stack.
    * **Push/Pop Conditions:** These are defined by the problem. In your code, you always push `(` and pop `)`.
    * **Core Logic:** The most important part is the condition under which you add to your result. Your code brilliantly uses the stack's size as a proxy for depth.
        * For an opening `(`: You check `if (!stack.isEmpty())`. This asks, "Am I already inside another pair of parentheses?" If yes, this `(` is not an outermost one, so we keep it.
        * For a closing `)`: You `pop()` first, then check `if (!stack.isEmpty())`. This asks, "After closing this pair, am I still inside another pair?" If yes, this `)` was not an outermost one, so we keep it.
    * **Another Example:** For "Simplify Path", you push directory names. If you see `..`, you pop. If you see `.`, you do nothing. The final stack contents are used to build the result.

#### 3. Expression Evaluation Problems (e.g., "Basic Calculator")

* **The Question:** Calculate the result of an arithmetic expression.
* **The Strategy:** This is the most advanced pattern and often requires **two stacks**: one for numbers (operands) and one for operators.
    * **The Challenge:** You have to handle operator precedence (`*` and `/` before `+` and `-`).
    * **The Logic:**
        1.  When you see a number, push it to the `number stack`.
        2.  When you see an operator, look at the top of the `operator stack`.
        3.  If the operator on the stack has higher or equal precedence than your current operator, you must perform that operation first. Pop the operator, pop two numbers, calculate the result, and push the result back onto the `number stack`.
        4.  Repeat this until the operator on the stack has lower precedence, then push your current operator.
        5.  Parentheses are handled by recursively solving the sub-expression or by pushing them onto the operator stack to create a "wall" that ignores precedence until a closing parenthesis is found.

### A Mental Checklist for Any New Problem

When you encounter a new problem of this type, ask yourself these questions:

1.  **What am I putting on the stack?** (Characters, numbers, indices?)
2.  **What is the "Push" condition?** (When do I add to the stack?)
3.  **What is the "Trigger" for a reaction?** (What character makes me look at the stack? A `)`, an operator, etc.?)
4.  **What is the "Pop" logic?** (When the trigger occurs, what do I do? Do I just pop? Do I compare the popped item? Do I use it in a calculation?)
5.  **How is the final result built?** (Is it a boolean? Is it the last number on the stack? Do I build a string from the stack's contents?)


### **Pattern 4: Monotonic Stack (Next/Previous Greater/Smaller)**

---

## Sliding Widow

### **Pattern 1: Fixed-Size Sliding Window**

### **Pattern 2: Variable-Size Sliding Window (Two Pointers)**

### **Pattern 3: Counting Subarrays with the "At Most K" Trick**

### **Pattern 4: Sliding Window with an Auxiliary Data Structure**

---

## Greedy

- [Greedy Template](https://huaguo.substack.com/p/greedy-algorithm)


---
## Prefix Sum

- `prefix[j] - prefix[i - 1] = k` sum of a subarray from index i to j is equal to k
- `prefix[i - 1] = prefix[j] - k` , prefix[i - 1] is valid subarray with sum k
- Subarray sum multuple of k, `prefix[j] % k = prefix[i - 1] % k` 

---
## Trees

### **Tree Representations in array**

- For 1-based indexing:

  - **Left child** of node at index `i`: `2 * i`
  - **Right child** of node at index `i`: `2 * i + 1`
  - **Parent** of node at index `i`: `i // 2` (only if `i > 1`)

- For 0-based indexing:

  - **Left child** of node at index `i`: `2 * i + 1`
  - **Right child** of node at index `i`: `2 * i + 2`
  - **Parent** of node at index `i`: `(i - 1) // 2` (only if `i > 0`)

### **Traversal**

`Note : Visualize with 3 nodes`

  1. **Inorder Iterative(Left-Root-Right)**  

  - Initialize `current variable` with root, push left node until its null
  - Pop last left node process it, then initialize current variable with right node

  2. **Preorder Iterative(Left-Root-Right)**

  - First add root to stack
  - While stack is not empty pop from stack process the element, then push right node and then left node

  3. **Postorder Iterative(Left-Root-Right)**
  
  - Create two stack input and output
  - Push root to a input stack, the pop from stack, then push the element to ouput stack
  - Push left node to input stack and right node to input stack


### 1\. Binary Search Tree (BST)

A Binary Search Tree is a node-based binary tree with a special ordering property that allows for fast lookups, insertions, and deletions.

### **Properties and Principles**

  * **BST Invariant:** For any given node `N`:
      * All values in its **left subtree** are **less than** `N`'s value.
      * All values in its **right subtree** are **greater than** `N`'s value.
      * Both its left and right subtrees must also be binary search trees.
  * **No Duplicate Nodes:** A standard BST does not allow duplicate values.
  * **In-order Traversal:** An in-order traversal of a BST yields its nodes' values in **sorted ascending order**.
  * **Time Complexity:** For a balanced BST, operations like search, insertion, and deletion take $O(\log n)$ time. In the worst case (a skewed or degenerate tree), they take $O(n)$ time.

### **BST Operations**

#### **Insertion**

To insert a value, you traverse the tree from the root. If the new value is less than the current node's value, you go left; otherwise, you go right. You continue until you reach a `null` spot, where you insert the new node.

```java
TreeNode insert(TreeNode root, int key) {
    if (root == null) {
        return new TreeNode(key);
    }
    if (key < root.val) {
        // Recursively insert into the left subtree
        root.left = insert(root.left, key);
    } else if (key > root.val) {
        // Recursively insert into the right subtree
        root.right = insert(root.right, key);
    }
    // Return the (possibly modified) root of the subtree
    return root;
}
```

#### **Deletion**

Deletion is more complex and handles three cases for the node to be deleted:

1.  **No children (leaf node):** Simply remove the node.
2.  **One child:** Replace the node with its child.
3.  **Two children:** Find the node's **in-order successor** (the smallest value in its right subtree), replace the node's value with the successor's value, and then recursively delete the successor node.

<!-- end list -->

```java
TreeNode delete(TreeNode root, int key) {
    if (root == null) return null;

    if (key < root.val) {
        root.left = delete(root.left, key);
    } else if (key > root.val) {
        root.right = delete(root.right, key);
    } else { // Found the node to delete
        // Case 1 & 2: Node with one or no child
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        // Case 3: Node with two children
        // Find the in-order successor (smallest value in the right subtree)
        TreeNode successor = findMin(root.right);
        root.val = successor.val; // Replace node's value with successor's
        root.right = delete(root.right, root.val); // Delete the successor
    }
    return root;
}

// Helper to find the minimum value node in a subtree
TreeNode findMin(TreeNode node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}
```

### **AVL Tree**

  * **Principle:** An AVL Tree is a height-balanced BST. The heights of the two child subtrees of any node can differ by **at most one**. This difference, called the **Balance Factor**, must be in the set `{-1, 0, 1}`.
  * **Properties:** It is the most rigidly balanced type of BST, which means search operations are extremely fast. However, insertions and deletions can be slower because they may require multiple re-balancing operations.
  * **Operations & Balancing:**
      * **Insertion/Deletion:** First, perform a standard BST insertion or deletion. Then, trace the path back up to the root, updating the height of each node.
      * **Rebalancing:** If any node's balance factor becomes `-2` or `+2`, the tree is unbalanced. The tree performs **rotations** (single or double, such as Left-Left, Right-Right, Left-Right, or Right-Left cases) at the unbalanced node to restore the height property.

### **Red-Black Tree**

  * **Principle:** A Red-Black Tree is a color-balanced BST. It uses node "coloring" (red or black) to ensure that the path from the root to any leaf is roughly the same length.
  * **Properties & Rules:**
    1.  Every node is either **red** or **black**.
    2.  The root is always **black**.
    3.  There are no two adjacent red nodes (a red node cannot have a red parent or a red child).
    4.  Every path from a given node to any of its descendant `NULL` nodes contains the same number of **black** nodes (the "black-height").
  * **Operations & Balancing:**
      * **Insertion/Deletion:** After a standard BST operation, a new node is typically colored red. This may violate rules 2 or 3.
      * **Rebalancing:** The tree fixes violations using two main operations: **re-coloring** nodes and performing **rotations**. These operations are designed to be very fast, often resolving the imbalance locally without propagating up the entire tree.
  * **Use Case:** It's slightly less strictly balanced than an AVL tree but requires fewer rotations on average, making it faster for write-heavy applications. It's used in many standard libraries, like `TreeMap` in Java and `std::map` in C++.


### **Segment Tree**

  * **Principle:** A Segment Tree is a binary tree used for storing information about array intervals. Each leaf represents a single element, and each internal node represents a merged property (like sum, min, or max) of its children's intervals.
  * **Properties:** It's a full binary tree built on top of an array. It allows for fast querying of a property over a given range and supports efficient updates to individual elements.
  * **Operations:**
      * **Build ($O(n)$):** A post-order recursive construction. The tree is built from the bottom up, with each internal node's value being calculated from its children.
      * **Range Query ($O(\log n)$):** To query a range `[L, R]`, you traverse the tree. If a node's interval is completely within `[L, R]`, you use its pre-computed value. If it partially overlaps, you recurse on its children.
      * **Point Update ($O(\log n)$):** To update an element at index `i`, you update the corresponding leaf and then recursively update all its ancestors up to the root.

### **B-Tree and B+ Tree**

  * **Principle:** These are self-balancing trees optimized for systems that read and write large blocks of data, such as **databases and filesystems**. Unlike binary trees, nodes in a B-Tree can have many children (a high "fanout").
  * **Properties:**
      * **High Fanout:** Nodes can store many keys and have many children, which keeps the tree's height extremely low. This minimizes the number of disk reads needed to find data.
      * **All Leaves at Same Level:** This ensures that searches are always efficient and predictable.
  * **Operations (B-Tree):**
      * **Insertion:** Find the correct leaf to insert into. If the leaf is full, **split** it into two nodes and promote the median key to the parent. This splitting can propagate up to the root.
      * **Deletion:** May cause a node to have too few keys. This is fixed by **merging** with a sibling or **borrowing** a key from a sibling.
  * **B+ Tree Distinction:**
      * **Data Storage:** All data records are stored **only** in the leaf nodes. Internal nodes only store keys to guide the search.
      * **Linked Leaves:** Leaf nodes are linked together like a **linked list**, allowing for very efficient sequential traversal and range queries (e.g., `SELECT * WHERE age BETWEEN 20 AND 30`). This is the primary data structure used for indexing in most relational databases.

---
## Backtracking


### The Common Locations for Pruning

Let's break down where pruning happens using our examples. There are generally two main places:

#### 1\. While Iterating Through Choices (Inside a `for` loop)

This is the most common place for pruning in **generation-style** problems (like combinations, subsets, permutations). Before you commit to a choice and make a recursive call, you check if that choice is valid or promising.

**Example 1: `Permutations` (Validity Prune)**

```java
for (int num : nums) {
    // PRUNING HAPPENS HERE
    // Checks: "Is this choice valid according to the rules?"
    if (tempList.contains(num)) {
        continue; // Don't explore paths with duplicate numbers.
    }
    
    tempList.add(num);
    helper(...); // Only explore valid choices
    tempList.remove(...);
}
```

Here, you prune **before** making the recursive call to avoid exploring a branch that violates the problem's core rules (e.g., using a number more than once).

**Example 2: `Combinations` (Optimization Prune)**

```java
for (int i = start; i <= n; i++) {
    // PRUNING CAN HAPPEN HERE
    // Asks: "Is it still possible to find a solution from here?"
    if (/* elements needed > elements available */) {
        break; // Stop exploring choices that can't possibly work.
    }

    path.add(i);
    backtrack(...);
    path.remove(...);
}
```

Here, you prune to make the algorithm more efficient by looking ahead and realizing that even if you make a choice, you won't have enough remaining options to complete a valid solution.

#### 2\. At the Start of the Recursive Call (As a Guard Clause)

This is very common in **pathfinding-style** problems on a grid or graph (like a maze). The function is called with a new state (e.g., new coordinates), and the very first thing it does is validate that state.

**Example: `RatInMaze` (Validity Prune)**

```java
public static void helper(int[][] arr, int row, int col, ...) {
    // Base case for success
    if (row == m - 1 && col == n - 1) { ... }

    // PRUNING HAPPENS HERE
    // Checks: "Is the current state (my location) valid?"
    if (row < 0 || col < 0 || row >= m || col >= n || arr[row][col] == 0) {
        return; // This path is invalid (out of bounds or a wall), so stop.
    }

    // Mark visited and explore neighbors
    arr[row][col] = 0; 
    helper(arr, row + 1, col, ...); // Down
    // ... other directions
    arr[row][col] = 1;
}
```

---

## Trie

**1. Basic Trie Implementation**

**Examples:**

- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) - Build a Trie with insert, search, and prefix-check operations.

- [Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) - Implement a Trie that supports adding words and searching words with `.` as a wildcard.


**2. Word Search and Prefix Matching**

**Examples:**

- [Concatenated Words](https://leetcode.com/problems/concatenated-words/) - Find all words that can be formed by concatenating two or more dictionary words.

- [Replace Words](https://leetcode.com/problems/replace-words/) - Replace words in a sentence with the shortest prefix found in a dictionary.


**3. Autocomplete and Suggestions**

**Examples:**

- [Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/) - Build an autocomplete system that suggests hot sentences based on user input.

- [Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/) - Given a list of products, return lexicographically sorted product suggestions based on a search prefix.


**4. Dictionary and Word Manipulation**

**Examples:**

- [Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/) - Find the longest word that can be built one character at a time using a given list of words.

- [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/) - Design a data structure that finds words matching a given prefix and suffix.

- [Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs/) - Implement a Trie-based key-value mapping where keys share prefixes.


**5. Bit Manipulation and Trie**

**Examples:**

Here are the descriptions for all four problems in the requested format:

1. [Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/) - Find the maximum XOR of two numbers in an array by comparing all possible pairs.

2. [Maximum XOR with an Element from Array](https://leetcode.com/problems/maximum-xor-with-an-element-from-array/description/)** - Compute the maximum XOR of a given element with any element from an array.

3. [Maximum Strong Pair XOR I](https://leetcode.com/problems/maximum-strong-pair-xor-i/description/) - Determine the maximum XOR value of a strong pair from an array, where a strong pair is defined by specific conditions.

---


## Design


###  1. Cache & Advanced DS Design (High Value)

* [**Insert Delete GetRandom O(1)**](https://leetcode.com/problems/insert-delete-getrandom-o1/) – Design a data structure that supports insert, delete, and get random element in average O(1) time.

* [**Insert Delete GetRandom O(1) – Duplicates allowed**](https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/) – Extend the above to handle duplicates while keeping O(1) operations.

* [**All O\`one Data Structure**](https://leetcode.com/problems/all-oone-data-structure/) – Implement a structure that supports increment, decrement, and retrieving max/min keys in O(1).

* [**LFU Cache**](https://leetcode.com/problems/lfu-cache/) – Design a Least Frequently Used cache with O(1) operations using hash map + doubly linked list.

* [**Dinner Plate Stacks**](https://leetcode.com/problems/dinner-plate-stacks/) – Simulate stacks with capacity constraints, supporting push/pop efficiently with a priority queue.

* [**Design Skiplist**](https://leetcode.com/problems/design-skiplist/) – Implement a probabilistic data structure (skip list) supporting search, insert, and erase in O(log n).

* [**Snapshot Array**](https://leetcode.com/problems/snapshot-array/) – Create an array that supports snapshots (versioning) with efficient get and set operations.


###  2. System / Real-World Inspired Designs (Medium–High Value)


* [**Design Circular Deque**](https://leetcode.com/problems/design-circular-deque/) – Extend circular queue to allow insert/delete at both ends efficiently.

* [**My Calendar I**](https://leetcode.com/problems/my-calendar-i/) – Book intervals in a calendar ensuring no overlaps using binary search tree.

* [**My Calendar II**](https://leetcode.com/problems/my-calendar-ii/) – Extend calendar booking to allow double bookings but prevent triple overlaps.

* [**My Calendar III**](https://leetcode.com/problems/my-calendar-iii/) – Further extend calendar to return the maximum number of concurrent bookings.

* [**Maximum Frequency Stack**](https://leetcode.com/problems/maximum-frequency-stack/) – Implement a stack-like structure that pops the most frequent element first.

* [**Online Stock Span**](https://leetcode.com/problems/online-stock-span/) – Compute stock spans using a monotonic stack for online queries.


###  3. String / Trie Based Designs (Medium Value)

* [**Implement Magic Dictionary**](https://leetcode.com/problems/implement-magic-dictionary/) – Build a dictionary supporting search with one-character modification.

* [**Map Sum Pairs**](https://leetcode.com/problems/map-sum-pairs/) – Implement a map that allows prefix-sum queries using a Trie.

* [**Stream of Characters**](https://leetcode.com/problems/stream-of-characters/) – Design a system that checks if recent characters form any word in a dictionary.

* [**Encrypt and Decrypt Strings**](https://leetcode.com/problems/encrypt-and-decrypt-strings/) – Create a string encryption/decryption system using mapping rules.

