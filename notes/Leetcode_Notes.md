
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

### **Pattern 4: Merging & Splitting (Divide and Conquer)**

### **Pattern 4: Cycle Analysis (Advanced Two Pointers)**

---
## Stack

### **Pattern 1: LIFO Basics (Reversal and "Undo")**

This is the most fundamental use of a stack. The LIFO property is perfect for problems that require reversing a sequence or simulating an "undo" or "backspace" operation.

**Explanation:**
Items are pushed onto the stack in one order and popped in the reverse order. This provides a simple way to process sequences backwards or to manage a list where only the most recently added item can be removed.

**Examples:**
* **Reverse a String:** Push each character onto a stack, then pop them off to build the reversed string.
* [Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/): Use a stack to simulate typing. A non-'#' character is pushed, and a '#' character pops the stack, effectively handling the backspace.


### **Pattern 2: Complex Simulation & State Tracking**

For problems with complex, stateful interactions (like collisions or decoding), a stack is perfect for managing the "currently active" set of items and their properties (like counts, directions, or nested results).

**Explanation:**
The top of the stack always represents the most current state or object. When a new element arrives, it only interacts with the element at the top of the stack. This simplifies problems that would otherwise require complex, nested logic.

**Examples:**
* [Decode String](https://leetcode.com/problems/decode-string/): Use two stacks (one for counts, one for partial strings) to handle nested decoding from the inside out. When you see `]`, you pop a count and a string, process them, and append the result to the new "current" string at the top of the string stack.
* [Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/): Use a stack of pairs `(character, count)`. When a new character arrives, if it matches the top, increment the count. If the count reaches `k`, pop from the stack.
* [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/): A new asteroid only collides with the last one standing (the stack's top). This simplifies the collision logic to a pairwise check.
* [Car Fleet](https://leetcode.com/problems/car-fleet/): After sorting cars by position, use a stack to merge fleets. A car becomes a fleet. If the car behind it is faster and would catch up, it gets absorbed into the fleet (do nothing). If it's slower (or can't catch up), it forms a new, distinct fleet (push its arrival time to the stack).


### **Pattern 3: Parentheses, Paths, and Expression Evaluation**

The stack's ability to handle matching pairs and precedence makes it the go-to data structure for parsing file paths, validating paired tokens (like parentheses), and evaluating arithmetic expressions.

**Explanation:**
When you encounter an "opening" token (like `(`, a directory name, or a number), you push it. When you encounter a "closing" token (`(`, `..`, or an operator), you process by popping from the stack.

* **Postfix (Reverse Polish Notation)**: `2 3 +`
    * **How to Solve**: Iterate through tokens. If a number, push it onto the stack. If an operator, pop the top two numbers, perform the operation, and push the result back.
* **Infix Notation**: `2 + 3`
    * **How to Solve**: Use two stacks (one for numbers, one for operators). Handle operator precedence carefully. When you see an operator, process any operators on the stack that have higher or equal precedence before pushing the new one. This is the basis of the Shunting-yard algorithm.
* **Prefix Notation (Polish Notation)**: `+ 2 3`
    * **How to Solve**: Same as postfix, but you iterate through the tokens in reverse (from right to left).

**Examples:**
* [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/): The classic example. Push opening brackets. When a closing bracket appears, check if it matches the top of the stack.
* [Simplify Path](https://leetcode.com/problems/simplify-path/): Use a stack to manage directory names. `.` is ignored, `..` pops from the stack (goes up one level), and a name is pushed.
* [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/): A direct implementation of the postfix evaluation algorithm.
* [Basic Calculator](https://leetcode.com/problems/basic-calculator/) series: These problems involve evaluating infix expressions with varying levels of complexity (parentheses, precedence).


### **Pattern 4: Monotonic Stack (Next/Previous Greater/Smaller)**

A monotonic stack (either always increasing or always decreasing) is a powerful tool for efficiently finding the **Next Greater/Smaller Element** or **Previous Greater/Smaller Element** for all items in a sequence.

**Explanation:**
You maintain a stack that is always sorted. When considering a new element, you pop from the stack any elements that would violate the monotonic property (e.g., in an increasing stack, pop all elements larger than the current one). The crucial insight is that for each element you pop, the current element is its **"Next Smaller Element"**.

**Examples:**
* [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/): The canonical problem to learn this pattern.
* [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/): A variation of "Next Greater Element," where you store indices and calculate the distance.
* [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/): A hard but classic application. The stack is used to find the `previous smaller` and `next smaller` bar for each bar `i`. These boundaries define the width of the largest possible rectangle that has bar `i` as its height.
* [Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/): An advanced use case. For each element `A[i]`, a monotonic stack helps find its `previous smaller` and `next smaller` element. This tells you the number of subarrays for which `A[i]` is the minimum, allowing you to calculate its total contribution to the sum.
* [Remove K Digits](https://leetcode.com/problems/remove-k-digits/): To get the smallest number, you want a monotonically increasing sequence of digits. You can use a stack to build this. If the current digit is smaller than the top of the stack, pop the top (simulating a removal) to maintain the increasing order.
* [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/): A clever variation. It builds a lexicographically smallest string using an increasing monotonic stack. You can pop a larger character `c1` to add a smaller one `c2`, but only if `c1` appears again later in the string.

---

## Sliding Widow

The sliding window technique is a powerful method for solving problems involving subarrays or substrings. It optimizes solutions that would otherwise be O(N²) down to an efficient O(N) by avoiding re-computation.

### **Pattern 1: Fixed-Size Sliding Window**

This is the simplest pattern, used when the problem specifies a fixed window size `k`. The goal is to find a property (like the max/min sum, average, etc.) of all windows of that size.

**Explanation:**
You first create an initial window of size `k`. Then, you slide the window one element at a time to the right. In each step, you efficiently update your calculation by **adding the new element** that enters the window and **subtracting the leftmost element** that leaves it. This O(1) update is the key to its efficiency.

**Examples:**
* **Maximum Sum Subarray of Size K:** The canonical example. Find the sum of the first `k` elements. Then, slide the window, at each step adding the new element and subtracting the one that fell off, updating the max sum.
* [Permutation in String](https://leetcode.com/problems/permutation-in-string/): This is conceptually a fixed-window problem. You are looking for a window in `s2` of size `s1.length()` that is a permutation of `s1`. You maintain a character count map and slide the window, updating the counts until you find a match.
* [Maximum Points From Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/): A clever twist on the pattern. Taking `k` cards from the ends is the same as leaving a contiguous block of `n-k` cards in the middle. The problem becomes: "Find the minimum sum subarray of fixed size `n-k`." The answer is `total_sum - min_subarray_sum`.


### **Pattern 2: Variable-Size Sliding Window (Two Pointers)**

This is the most common pattern, used to find the **longest or shortest** subarray/substring that satisfies a given condition.

**Explanation:**
You use two pointers, `left` and `right`, to define the current window.
1.  **Expand:** The `right` pointer always moves forward, expanding the window and adding new elements.
2.  **Shrink:** When the window no longer satisfies the condition, the `left` pointer moves forward, shrinking the window from the left until the condition is met again.
3.  **Update:** The answer (max/min length) is updated after each valid expansion.

**Examples:**
* [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/): **Condition:** `window_length - count_of_most_frequent_char <= k`. Expand the window with `right`. If the condition is violated, shrink with `left`.
* [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/): **Condition:** The window must contain all characters from string `t`. Expand with `right` until the condition is met. Then, shrink with `left` as much as possible while keeping the condition valid, updating the minimum length at each step.
* [Longest Subarray with at most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/): **Condition:** The frequency of every element in the window must be `<= k`. Expand with `right`. If the frequency of the newly added element exceeds `k`, shrink with `left` until its frequency is back to `k`.


### **Pattern 3: Counting Subarrays with the "At Most K" Trick**

This is a specific but powerful pattern for problems that ask for the **number of subarrays** that satisfy a condition with **exactly `k`** of something (e.g., odd numbers, distinct characters).

**Explanation:**
Counting subarrays with *exactly* `k` is difficult. However, counting subarrays with *at most* `k` is much easier and can be solved with a standard variable-size window. The magic formula is:
`count(exactly k) = count(at most k) - count(at most k - 1)`
You write a helper function to solve the "at most k" version and call it twice.

**Examples:**
* [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/): The canonical example of this pattern. You implement a helper `countAtMostKDistinct(nums, k)` and return `helper(nums, k) - helper(nums, k - 1)`.
* [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/): Same logic. The "something" to count is the number of odd integers in the subarray.
* [Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/): This can be solved with a variation. Once the window `[left...right]` is valid (contains 'a', 'b', and 'c'), you know that this window, plus any window that extends further to the right (e.g., `[left...right+1]`, `[left...n-1]`), is also valid. The number of such windows is `n - right`. You add this to the total and then shrink from the left.


### **Pattern 4: Sliding Window with an Auxiliary Data Structure**

This pattern applies when simple variables or a hash map are not enough to track the window's property efficiently. A specialized data structure is used to maintain the property in O(1) or O(log N) time as the window slides.

**Explanation:**
The core sliding window logic remains, but checking and maintaining the window's state requires a more complex tool. The most common is a **monotonic deque** (a double-ended queue that is always sorted).

**Examples:**
* [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/): The canonical example. A monotonic decreasing deque is used to store indices of elements in the window. The front of the deque always holds the index of the maximum element.
    1.  **Slide:** Before adding a new element's index, remove any indices from the back of the deque that correspond to smaller elements.
    2.  **Maintain:** Remove the index from the front if it falls out of the current window's bounds.
    3.  **Query:** The maximum element is always at `nums[deque.peekFirst()]`.
* **Sliding Window Median (Hard):** This requires two heaps or a balanced binary search tree to keep track of the median of the elements within the window as it slides.

---

## Greedy

- [Greedy Template](https://huaguo.substack.com/p/greedy-algorithm)


---

## Matrix


---
## Prefix Sum

- `prefix[j] - prefix[i - 1] = k` sum of a subarray from index i to j is equal to k
- `prefix[i - 1] = prefix[j] - k` , prefix[i - 1] is valid subarray with sum k
- Subarray sum multuple of k, `prefix[j] % k = prefix[i - 1] % k` 


**1. Subarray Problems with Prefix Sum**

**Examples:**

- [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)

- [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

- [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

- [Longest Subarray with Sum Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)


---
## Trees

- `Inorder successor(smallest element in left subtree from right node)` is next node element in inorder traversal(Sorted Element in BST)


**0. Tree Representations in array**

- For 1-based indexing:

  - **Left child** of node at index `i`: `2 * i`
  - **Right child** of node at index `i`: `2 * i + 1`
  - **Parent** of node at index `i`: `i // 2` (only if `i > 1`)

- For 0-based indexing:

  - **Left child** of node at index `i`: `2 * i + 1`
  - **Right child** of node at index `i`: `2 * i + 2`
  - **Parent** of node at index `i`: `(i - 1) // 2` (only if `i > 0`)

**1. Traversal**

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

**2. BST Operations**  

- Insertions

  ```java
      TreeNode insert(TreeNode root, int key) {
          if (root == null) {
              return new TreeNode(key);
          }
          if (key < root.val) {
              root.left = insertHelper(root.left, key); // Fill the new node
          } else if (key > root.val) {
              root.right = insertHelper(root.right, key); // // Fill the new node
          }
          return root; // return or propagate the root, means fill the left and right child of parent node
      }
  ```

- Deletions 

  ```java
    TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val)
            root.left = deleteHelper(root.left, key); // If no child,  null is filled, if one node filled either one left or right node
        else if (key > root.val)
            root.right = deleteHelper(root.right, key);
        else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null)  return root.left;

            root.val = inorderSuccessor(root.right); // Replace with Inorder Successor
            root.right = deleteHelper(root.right, root.val); // Delete the inorder successor
        }

        return root;
    }
  ```

**3. Depth/Height** 

- [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/description/)

  ```java
    int diameter = 0;

    int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }
  ```

- [Max Depth of binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)

  ```java 
    int maxDepth(TreeNode root) {
       if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left , right) + 1;   
    }
  ```

- [Maximum Depth of N-ary Tree](https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/)

  ```java
    int maxDepth(Node root) {
        if (root == null) return 0;

        if (root.children.isEmpty()) return 1;

        int depth = 0;
        for (Node child : root.children) {
            depth = Math.max(depth, maxDepth(child)); // This find max depth for each children
        }

        return depth + 1; // max depth of a child and including root  
    }
  ```  


**4. Path problem binary tree** 

- Path from root to leaf for target sum

  ```java
    boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        if ( root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);  
    }
  ```

**5. Comparison on Two Trees**

- In [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/description/) Compare two subtree parallely.
   
  ```java
    boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        return (p.val == q.val) && helper(p.left, q.right) && helper(p.right, q.left);
    }
  ```

**6. Counting nodes in Tree**


**Examples**

- [Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/)
   
   - In function call keep one variable contains max value in tree path, then compare max value with root value, count good node and update the max value.

   ```java
      int good;
      void DFS(TreeNode root, int max) {
          if (root == null)
              return;

          if (root.val >= max)
              good++;

          max = Math.max(max, root.val);
          DFS(root.left, max);
          DFS(root.right, max);
      }
   ```  


**7. Ancestor**

- [Lowest Common Ancestor](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) in BST

  ```Java 
      TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          int small = Math.min(p.val, q.val);
          int large = Math.max(p.val, q.val);
          while (root != null) {
              if (root.val > large) // p, q belong to the left subtree
                  root = root.left;
              else if (root.val < small) // p, q belong to the right subtree
                  root = root.right;
              else // Now, small <= root.val <= large -> This root is the LCA between p and q
                  return root;
          }
          return null;
      }
  ```

**8. Different view of tree**

**Examples:**

- [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)
  
  - Process one node(right) at each level, maintain level parameter in function call

  ```java
     void helper(List<Integer> result, TreeNode root, int level) {
          if (root == null) return;

          if (level == result.size()) result.add(root.val);

          helper(result, root.right, level + 1);
          helper(result, root.left, level + 1);
    }
  ```
- [Binary Tree Left Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Process one node(left) at each level, maintain level parameter in function call

- [Binary Tree Top Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Use `level order traversal`, create pair of node and horizontal distance.
  - Assign horizontal distance to each node, like root is 0, left child is -1, right child is +1
  ```
          1(0)
        /     \
      2(-1)    3(+1)
    /  \     /   \
  4(-2) 5(0) 6(0)  7(+2)

  ```
  - If distance does not exist in TreeMap, then add the node to TreeMap, Top view node will have unique distance

- [Binary Tree Bottom Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Similar to top view, the only difference is that we need to replace the node in TreeMap with the same distance

- [Binary Tree Diagonal View]()  

  - start with root 0, for left node assign same distance and for right node assign distance + 1


**9. Tree construction** 

**Examples:**

- [Construct Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees-ii/description/)

- [Number of Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees/description/)

```java

   long binomialCoefficient(int n, int k) {
        long res = 1;

        if (k > n - k)
            k = n - k; // Using the property: C(n, k) = C(n, n-k)

        for (int i = 0; i < k; i++) {
            res *= (n - i); // Multiply by decreasing numerator
            res /= (i + 1); // Divide by increasing denominator
            // Using the property of the associativity of multiplication and division:
            // (a / b) × (c / d) = (a × c) / (b × d)
        }

        return res;
    }

   int numTrees(int n) {
        return (int) (binomialCoefficient(2 * n, n) / (n + 1));
    }

```

- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)

- [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/)
   

**10 Serialize and Deserialize**

**Examples:**

- [Verify Preorder Serialization of a Binary Tree](https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/) - Serialized tree "9,3,4,#,#,1,#,#,2,#,6,#,#"

   - Use stack to collapse the nodes if prev 3 nodes are `4,#,#` pattern into single hash `#`
   - If stack size is 1 and its `#` value then return true else false

- [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/) 

   - Use `preorder traversal` to serialize the tree
   - Use `preorder traversal` to deserialize the tree 

**11. B and B+ Tree**  


**12. AVL Tree**   


**13. Red-Black Tree**  


**14. Segment Tree**  


---
## Backtracking

- **Pruning the search** : We can often optimize backtracking by pruning the search tree.
- **Meet in the middle** : Meet in the middle is a technique where the search space is divided into two parts of about equal size. A separate search is performed for both of the parts, and finally the results of the searches are combined. Example `subset sum` can be optimize using this technique


### **1. Subsets (Power Set)**  

**Examples:**  

- [Subsets](https://leetcode.com/problems/subsets/) - Generate all possible subsets of a given set.

  - Always Select the next element, then backtrack and remove the element
  
  ```java
    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, list, result, i + 1);
            list.removeLast();
        }
    }
  ```

- [Subsets II](https://leetcode.com/problems/subsets-ii/) - Generate all possible subsets of a given set, handling duplicates.  

  - Sort the elements and check if there is duplicates by comparing with previous element, then skip the duplicates


### **2. Permutations**  

**Examples:**  

- [Permutations](https://leetcode.com/problems/permutations/) - Generate all possible permutations of a given set of numbers. 

  - Recursive functions always starts with `i = 0` or first element and check duplicates in the list

  ```java
    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int num : nums) {
            if (list.contains(num)) continue;

            list.add(num);
            backtrack(nums, list, result);
            list.removeLast();
        }
    }
  ```


- [Permutations II](https://leetcode.com/problems/permutations-ii/) - Generate all unique permutations of a given set, handling duplicates.  

  - Can be used visited array to check duplicates
    ```java
      if (visited[i]) continue;
      if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
    ```


### **3. Combinations**  

**Examples:**  

- [Combinations](https://leetcode.com/problems/combinations/) - Generate all possible combinations of `k` numbers from a given set. 

  - Recursive Subset pattern can be used here, call recursive function with next starting index

  - **Meet in the Middle Optimizations** : 
    
    - For example, suppose that the list is [2,4,5,9] and x = 15. First, we divide the list into A= [2,4] and B= [5,9]. After this, we create lists SA = [0,2,4,6]
  and SB = [0,5,9,14]. In this case, the sum x = 15 is possible to form, because SA contains the sum 6, SB contains the sum 9, and 6 + 9= 15. This corresponds to the solution [2,4,9].


- [Combination Sum](https://leetcode.com/problems/combination-sum/) - Find all unique combinations of numbers that sum up to a target.  


- [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) - Similar to Combination Sum but with each number used at most once.  


- [Combination Sum III](https://leetcode.com/problems/combination-sum-iii/) - Find all valid combinations of `k` numbers that sum to `n`.  


### **4. Word Search**  

**Examples:**  

- [Word Search](https://leetcode.com/problems/word-search/) - Check if a word exists in a grid using backtracking.  

- [Word Search II](https://leetcode.com/problems/word-search-ii/) - Find all words from a dictionary that exist in a grid.  



### **5. Sudoku Solver**  

**Examples:**  

- [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) - Solve a Sudoku puzzle by filling empty cells with valid numbers.  

- [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) - Check if a given Sudoku board configuration is valid.  


### **6. N-Queens**  

**Examples:**  

- [N-Queens](https://leetcode.com/problems/n-queens/) - Place `N` queens on an `N×N` board without attacking each other.  

- [N-Queens II](https://leetcode.com/problems/n-queens-ii/) - Count the number of distinct solutions to the N-Queens problem.  


### **7. Backtracking with String**  

**Examples:**  

- [Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/) - Generate all possible case variations of a string containing letters.  

- [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/) - Generate all possible valid IP addresses from a given string.  


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


---

## Number Theory





---

## Bit Manipulation




---


## Matrix

