# 🎯 Contents

---

- [Recursion Patterns](#recursion-patterns)
- [String Problems](#string-problems)
- [Array Problems](#array-problems)
- [Greedy Problems](#greedy-problems)
- [Map Usage](#map-usage)
- [Deque Usage](#deque-usage)
- [Two-Pointer Techniques](#two-pointer-techniques)
- [Sliding Window Techniques](#sliding-window-techniques)
- [Stack Techniques](#stack-techniques)
- [Binary Search Techniques](#binary-search-techniques)
- [Linked List Techniques](#linked-list-techniques)
- [Tree Techniques](#tree-techniques)
- [Backtracking Techniques](#backtracking-techniques)
- [Graph Techniques](#graph-techniques)
- [Dynamic Programming](#dynamic-programming)
- [Bit Manipulation](#bit-manipulation)
- [Matrix](#matrix)
- [Permutations](#permutations)
- [Combinations](#combinations)
- [Number System conversion](#Number-System-conversion)

---

# Recursion Patterns

**1. Direct Recursion or Linear Recursion**

- A function calls itself once with a subset of the original input.
  > **subset of the original input** in the context of linear recursion means that each recursive call to the function
  works with a part of the data derived from the original input, typically a smaller or reduced version.

**2. Tail Recursion**

- A special kind of recursion where the recursive call is the last operation in the function. This allows for
  optimizations by the compiler or interpreter, making the recursion more efficient in terms of memory usage.
- Only one stack frame is created.

**3. Tree Recursion**

- A type of recursion where a function makes multiple recursive calls. This creates a branching effect, like the
  branches of a tree.

**4. Divide and Conquer**

- The divide and conquer pattern is a powerful algorithmic technique where a problem is divided into smaller subproblems
  that are solved recursively.

**5. Backtracking**

- Explore all potential solutions and backtrack when a solution fails.
    - Example: Solving puzzles like Sudoku, N-Queens problem.

**6. Dynamic Programming (with Recursion)**

- Recursion combined with memoization to avoid redundant calculations.
    - Example: Fibonacci sequence with memoization, Knapsack problem.

**7. Mutual Recursion**

- Mutual recursion is when two or more functions recursively call each other. A classic example involves checking
  whether a number is even or odd using mutual recursion.

**8. Generative Recursion**

- Generative recursion involves generating and solving new subproblems based on the current state, often used in
  scenarios like generating permutations.

**9. Indirect Recursion**

- A function calls another function, eventually leading to the original function being called.
    - Example: Function A calls Function B, and Function B calls Function A.

**10. Nested Recursion**

- Nested recursion occurs when a recursive function calls itself with a recursive call as one of its arguments.

</br>

# String Problems

[Important string questions pattern](https://leetcode.com/discuss/interview-question/2001789/collections-of-important-string-questions-pattern)

### 1. String Manipulation

#### Problem Types:

a) **Reverse String**

- Basic: Reverse an entire string
- Variants:
    - Reverse words in a string while preserving word order
    - Reverse only certain parts of a string
    - Reverse string without affecting special characters

b) **Palindrome Problems**

- Basic: Check if a string is a palindrome
- Variants:
    - Find the longest palindromic substring
    - Determine minimum characters to add to make a palindrome
    - Check if a string can be rearranged to form a palindrome

c) **Remove/Replace Characters**

- Basic: Remove all occurrences of a given character
- Variants:
    - Remove duplicates from a string
    - Replace certain characters or patterns
    - Remove characters to make two strings equal

#### How to Solve:

- Use two pointers (start and end) and swap characters
- For word reversal, reverse entire string, then reverse each word
- Use two-pointer technique for palindromes
- Apply dynamic programming for longest palindromic substring
- Use hash tables for character frequency counting

### 2. String Searching

#### Problem Types:

a) **Substring Search**

- Basic: Find if a substring exists in a string (implement strStr())
- Variants:
    - Find all occurrences of a pattern in a string
    - Search for a pattern with wildcards
    - Find the longest common substring between two strings

b) **Pattern Matching**

- Basic: Check if a string matches a given pattern
- Variants:
    - Implement regular expression matching
    - Match strings with custom wildcard characters
    - Find strings that match a pattern in an array of strings

#### How to Solve:

- Naive approach: Use nested loops for comparison
- KMP: Build failure function, then search
- Rabin-Karp: Use rolling hash for quick comparisons
- Use state machines for regular expressions
- Apply dynamic programming for complex patterns
- Use backtracking for wildcard matching

### 3. Anagrams and Permutations

#### Problem Types:

a) **Anagram Check**

- Basic: Determine if two strings are anagrams
- Variants:
    - Group anagrams from an array of strings
    - Find all anagrams of a pattern in a string
    - Minimum number of character deletions to make two strings anagrams

b) **Permutations**

- Basic: Generate all permutations of a string
- Variants:
    - Next lexicographical permutation
    - Permutation in string (check if any permutation of a string is present in another)
    - Permutations with duplicates

#### How to Solve:

- Sort both strings and compare
- Count character frequencies and compare
- Use a single integer for XOR of all characters (limited alphabet)
- Apply recursive backtracking for generating permutations
- Use Heap's algorithm for generating all permutations
- Implement next permutation algorithm for lexicographical order

### 4. String Compression

#### Problem Types:

a) **Run-Length Encoding**

- Basic: Compress string using count of repeated characters
- Variants:
    - Decompress a run-length encoded string
    - Optimize compression for different types of strings
    - Compress with limitations (e.g., at most k consecutive repeats)

b) **Dictionary Coding**

- Basic: Replace common substrings with shorter codes
- Variants:
    - Adaptive dictionary coding
    - Compress with a given dictionary
    - Optimal dictionary generation for compression

#### Common Techniques:

- Two-pointer method
- Counting
- Hash tables/dictionaries

### 5. Longest Substring Problems

#### Problem Types:

a) **Longest Substring Without Repeating Characters**

- Basic: Find the length of the longest substring without repeating characters
- Variants:
    - Find the actual substring, not just length
    - Allow k repeating characters
    - Longest substring with at most two distinct characters

b) **Longest Common Substring/Subsequence**

- Basic: Find the longest common substring between two strings
- Variants:
    - Longest common subsequence
    - Longest palindromic subsequence
    - Shortest common supersequence

#### How to Solve:

- Use sliding window with two pointers
- Employ hash table to store last seen position of each character
- Apply dynamic programming (2D table)
- Use suffix trees for advanced implementations

### 6. String Parsing

#### Problem Types:

- Expression Evaluation
- Nested Structures Parsing

#### How to Solve:

- Use a stack for operators and operands in expression evaluation
- Apply Shunting yard algorithm for infix to postfix conversion
- Implement recursive functions for nested elements
- Use stack to keep track of opening/closing elements

### 7. String Transformation

#### Problem Types:

- Edit Distance
- Word Break

#### How to Solve:

- Use dynamic programming (2D table) for edit distance
- Implement recursive approach with memoization
- Apply dynamic programming for word break problems
- Use trie for efficient word lookup

### 8. Regular Expression and Wildcard Matching

#### Problem Types:

- Regex Matching
- Wildcard Matching

#### How to Solve:

- Build NFA (Nondeterministic Finite Automaton)
- Use dynamic programming for matching
- Apply greedy algorithms for simple wildcard cases
- Implement dynamic programming for complex wildcard patterns

### 9. String Encoding/Decoding

#### Problem Types:

- Decode String
- Serialize/Deserialize Data Structures

#### How to Solve:

- Use a stack to handle nested structures
- Implement a recursive approach for nested encodings
- Apply depth-first traversal for serialization
- Use stack or queue for deserialization

### 10. Trie-based Problems

#### Problem Types:

- Implement Trie
- Word Search II

#### How to Solve:

- Create TrieNode class with children and isEnd flag
- Implement insert, search, and startsWith methods
- Build trie with given words
- Perform DFS on board with trie traversal for word search problems

</br>

# Array Problems

### 1. Two Pointer Technique

#### Problem Types:

a) **Two Sum**

- Basic: Find two numbers in an array that add up to a target
- Variants:
    - Three Sum, Four Sum
    - Two Sum II (input array is sorted)
    - Closest Sum

b) **Sliding Window**

- Basic: Find subarray with given sum
- Variants:
    - Longest substring with k distinct characters
    - Minimum size subarray sum
    - Maximum sum subarray of size k

c) **Palindrome, Reverse**

- Basic: Check if array is palindrome
- Variants:
    - Reverse array in-place
    - Rotate array

#### How to Solve:

- Use two pointers moving towards each other for Two Sum
- Maintain a window and slide it over the array
- Use left and right pointers for palindrome check or reversal

#### Common Techniques:

- Two pointers (start and end)
- Sliding window
- In-place manipulation

### 2. Sorting and Searching

#### Problem Types:

a) **Binary Search**

- Basic: Search in sorted array
- Variants:
    - Search in rotated sorted array
    - Find first and last position of element
    - Search insert position

b) **Merge Intervals**

- Basic: Merge overlapping intervals
- Variants:
    - Insert interval
    - Non-overlapping intervals

c) **Sorting Algorithms**

- Implementation of various sorting algorithms
- Custom sort based on specific criteria

#### How to Solve:

- Implement binary search for sorted arrays
- Sort intervals based on start time for merging
- Choose appropriate sorting algorithm based on input size and constraints

#### Common Techniques:

- Binary search
- Sorting (QuickSort, MergeSort)
- Interval manipulation

### 3. Dynamic Programming

#### Problem Types:

a) **Maximum Subarray**

- Basic: Find contiguous subarray with largest sum
- Variants:
    - Maximum product subarray
    - Circular subarray

b) **Longest Increasing Subsequence**

- Basic: Find length of longest increasing subsequence
- Variants:
    - Longest bitonic subsequence
    - Increasing triplet subsequence

c) **Coin Change**

- Basic: Minimum number of coins to make amount
- Variants:
    - Number of ways to make amount
    - Coin change with limited supply

#### How to Solve:

- Use Kadane's algorithm for maximum subarray
- Maintain dp array for longest increasing subsequence
- Use bottom-up dp approach for coin change problems

#### Common Techniques:

- Tabulation (bottom-up)
- Memoization (top-down)
- State transition

### 4. Prefix Sum

#### Problem Types:

a) **Range Sum Query**

- Basic: Sum of elements between two indices
- Variants:
    - 2D range sum query
    - Range sum query with updates

b) **Subarray Sum Equals K**

- Basic: Count of subarrays with sum equal to k
- Variants:
    - Maximum size subarray sum equals k
    - Continuous subarray sum

#### How to Solve:

- Precompute cumulative sum array
- Use hash map to store running sum and count
- Optimize space using constant extra space techniques

#### Common Techniques:

- Prefix sum array
- Hash map for sum frequency
- Difference array for range updates

### 5. Greedy Algorithms

#### Problem Types:

a) **Jump Game**

- Basic: Determine if you can reach the last index
- Variants:
    - Jump Game II (minimum number of jumps)
    - Jump Game III (can reach zero)

b) **Gas Station**

- Basic: Find starting gas station to complete circuit
- Variants:
    - Minimum refueling stops

c) **Candy Distribution**

- Basic: Distribute candies based on ratings
- Variants:
    - Distribute candies with additional constraints

#### How to Solve:

- Iterate from end to start for Jump Game
- Use circular array traversal for Gas Station
- Two-pass approach for Candy Distribution

#### Common Techniques:

- Iterative greedy choice
- Backward iteration
- Two-pass algorithm

### 6. Matrix Operations

#### Problem Types:

a) **Matrix Rotation**

- Basic: Rotate image by 90 degrees
- Variants:
    - Spiral matrix
    - Anti-diagonal traverse

b) **Matrix Search**

- Basic: Search in sorted 2D matrix
- Variants:
    - Kth smallest element in sorted matrix

c) **Island Problems**

- Basic: Number of islands
- Variants:
    - Max area of island
    - Island perimeter

#### How to Solve:

- Use layer-by-layer approach for rotation
- Treat 2D matrix as 1D sorted array for search
- Use DFS or BFS for island problems

#### Common Techniques:

- In-place rotation
- Binary search on 2D array
- Depth-First Search (DFS)
- Breadth-First Search (BFS)

### 7. Stack and Queue

#### Problem Types:

a) **Next Greater Element**

- Basic: Find next greater element for each element
- Variants:
    - Daily temperatures
    - Stock span problem

b) **Histogram**

- Basic: Largest rectangle in histogram
- Variants:
    - Maximal rectangle (in binary matrix)

c) **Sliding Window Maximum**

- Basic: Maximum of all subarrays of size k
- Variants:
    - Sliding window median

#### How to Solve:

- Use stack to keep track of next greater elements
- Maintain monotonic stack for histogram problems
- Use deque for sliding window maximum

#### Common Techniques:

- Monotonic stack
- Deque (double-ended queue)
- Two stacks to simulate queue

### 8. Bit Manipulation

#### Problem Types:

a) **Single Number**

- Basic: Find single number in array where every element appears twice
- Variants:
    - Single number II (every element appears thrice except for one)

b) **Counting Bits**

- Basic: Count number of 1's in binary representation of each number
- Variants:
    - Number of 1 Bits
    - Power of Two

c) **Bitwise AND of Numbers Range**

- Basic: Find bitwise AND of all numbers in a range
- Variants:
    - Missing Number using XOR

#### How to Solve:

- Use XOR operation for Single Number
- Use dynamic programming or bit manipulation for Counting Bits
- Find common prefix for Bitwise AND of Numbers Range

#### Common Techniques:

- XOR operation
- Bit shifting
- Masking

Certainly! Here's a comprehensive overview of greedy problem types and solving techniques in markdown format:

</br>

# Greedy Problems

### 1. Scheduling Problems

#### Problem Types:

a) **Activity Selection**

- Basic: Select maximum number of non-overlapping activities
- Variants:
    - Minimum number of meeting rooms required
    - Maximum profit in scheduling weighted activities

b) **Job Sequencing**

- Basic: Schedule jobs to maximize profit with deadlines
- Variants:
    - Job scheduling with cooldown periods
    - Minimum difficulty of a job schedule

c) **Interval Partitioning**

- Basic: Assign rooms to lectures with minimum number of rooms
- Variants:
    - Classroom allocation problem
    - Minimum platforms needed for train schedule

#### How to Solve:

- Sort activities by end time for activity selection
- Sort jobs by profit and use disjoint set for job sequencing
- Use priority queue for interval partitioning

#### Common Techniques:

- Sorting based on end time or profit
- Priority queue for managing conflicts
- Disjoint set data structure

### 2. Huffman Coding

#### Problem Types:

a) **Huffman Encoding**

- Basic: Construct Huffman tree for optimal prefix codes
- Variants:
    - Optimal file merge pattern
    - Minimum cost of merging files

b) **Data Compression**

- Basic: Compress data using Huffman coding
- Variants:
    - Implement run-length encoding
    - Design LZW compression algorithm

#### How to Solve:

- Use priority queue to build Huffman tree
- Traverse Huffman tree to generate codes
- Implement encoding and decoding functions

#### Common Techniques:

- Priority queue (min-heap)
- Tree traversal
- Bit manipulation for encoding

c) **Decode Huffman Encoded String**

- Basic: Decode a string encoded using Huffman coding
- Variants:
    - Decode with incomplete Huffman tree
    - Adaptive Huffman decoding

d) **Optimal Merge Pattern**

- Basic: Find optimal way to merge files
- Variants:
    - Merge with constraints on merge order

#### How to Solve:

- Traverse Huffman tree based on encoded bits
- Use stack or recursion for decoding
- Apply greedy choice in merging files

#### Common Techniques:

- Tree traversal
- Bit manipulation
- Priority queue for merging

### 3. Fractional Knapsack

#### Problem Types:

a) **Classic Fractional Knapsack**

- Basic: Maximize value in knapsack allowing fractional items
- Variants:
    - Minimize cost to reach a certain value
    - Multiple knapsack problem with fractional items

b) **Load Balancing**

- Basic: Distribute tasks among machines to minimize maximum load
- Variants:
    - Load balancing with different machine capacities
    - Online load balancing

#### How to Solve:

- Sort items by value-to-weight ratio
- Greedily select items or fractions of items
- Use priority queue for load balancing

#### Common Techniques:

- Sorting based on value-to-weight ratio
- Fractional selection
- Priority queue for machine loads

### 4. Coin Change (Greedy Approach)

#### Problem Types:

a) **Minimum Coin Change**

- Basic: Find minimum number of coins to make a given amount
- Variants:
    - Coin change with limited supply of each coin
    - Coin change with removal allowed

b) **Coin Change Combinations**

- Basic: Find number of ways to make change for an amount
- Variants:
    - Coin change with order consideration

#### How to Solve:

- Sort coins in descending order
- Greedily select largest coin possible at each step
- Use dynamic programming for variants with limitations

#### Common Techniques:

- Sorting coins
- Iterative greedy selection
- Backtracking for complex variants

### 5. Egyptian Fraction

#### Problem Types:

a) **Represent Fraction as Sum of Unit Fractions**

- Basic: Represent a fraction as sum of unique unit fractions
- Variants:
    - Minimize number of unit fractions
    - Egyptian fraction with constraints on denominators

b) **Greedy Approximation Problems**

- Basic: Approximate real numbers with rational numbers
- Variants:
    - Continued fraction representation

#### How to Solve:

- Use greedy algorithm to find largest possible unit fraction
- Recursively apply the process for the remaining fraction
- Implement continued fraction algorithm for approximation

#### Common Techniques:

- Recursive greedy approach
- Arithmetic operations on fractions
- Continued fraction expansion

### 6. Minimum Spanning Tree

#### Problem Types:

a) **Kruskal's Algorithm**

- Basic: Find minimum spanning tree in a weighted graph
- Variants:
    - Maximum spanning tree
    - Minimum spanning forest

b) **Prim's Algorithm**

- Basic: Find minimum spanning tree starting from a vertex
- Variants:
    - Minimum spanning tree with constraints
    - Minimum product spanning tree

#### How to Solve:

- Sort edges by weight for Kruskal's algorithm
- Use priority queue for Prim's algorithm
- Implement union-find data structure for Kruskal's

#### Common Techniques:

- Sorting edges
- Priority queue for selecting edges
- Union-find data structure

### 7. Dijkstra's Algorithm

#### Problem Types:

a) **Single Source Shortest Path**

- Basic: Find shortest path from source to all vertices
- Variants:
    - Shortest path with exactly k edges
    - Shortest path with alternating colors

b) **Network Routing**

- Basic: Find optimal route in a network
- Variants:
    - Route with minimum number of hops
    - Route with maximum bandwidth

#### How to Solve:

- Use priority queue to select minimum distance vertex
- Maintain distance array and update distances
- Implement path reconstruction for actual path

#### Common Techniques:

- Priority queue for vertex selection
- Relaxation of edges
- Path reconstruction

</br>

# Map Usage

1. **Frequency Counting**

    - Approach: Count occurrences of elements
    - Examples:
        - Valid Anagram
        - Top K Frequent Elements
    - Usage: `Map<element, frequency>`

2. **Two Sum and Variants**

    - Approach: Store complement values
    - Examples:
        - Two Sum
        - 3Sum
        - 4Sum
    - Usage: `Map<complement, index>`

3. **Caching/Memoization**

    - Approach: Store computed results
    - Examples:
        - Fibonacci Number
        - LRU Cache
    - Usage: `Map<input, computedResult>`

4. **String Manipulation**

    - Approach: Character mapping or counting
    - Examples:
        - Isomorphic Strings
        - Group Anagrams
    - Usage: `Map<char, char> or Map<string, list>`

5. **Graph Representation**

    - Approach: Adjacency list
    - Examples:
        - Clone Graph
        - Course Schedule
    - Usage: `Map<node, List<neighbors>>`

6. **Prefix Sum**

    - Approach: Store cumulative sum
    - Examples:
        - Subarray Sum Equals K
        - Continuous Subarray Sum
    - Usage: `Map<prefixSum, count> or Map<prefixSum, index>`

7. **Design Problems**

    - Approach: Implement data structure
    - Examples:
        - Design HashMap
        - Implement Trie (Prefix Tree)
    - Usage: `Map as core data structure`

8. **Longest Substring/Subarray**

    - Approach: Track last seen index
    - Examples:
        - Longest Substring Without Repeating Characters
        - Longest Consecutive Sequence
    - Usage: `Map<char/num, lastSeenIndex>`

9. **Array Transformation**

    - Approach: Map old to new values
    - Examples:
        - Find and Replace Pattern
        - Word Pattern
    - Usage: `Map<oldValue, newValue>`

10. **Intersection and Union**

    - Approach: Track common elements
    - Examples:
        - Intersection of Two Arrays
        - Union of Two Arrays
    - Usage: `Map<element, occurrence>`

11. **Time-Based Data Structures**

    - Approach: Store values with timestamps
    - Examples:
        - Time Based Key-Value Store
    - Usage: `Map<key, List<{timestamp, value}>>`

12. **Sliding Window**

    - Approach: Track window contents
    - Examples:
        - Longest Substring with At Most K Distinct Characters
    - Usage: `Map<char, frequency>`

13. **Path Sum Problems**

    - Approach: Store path sums
    - Examples:
        - Path Sum III
    - Usage: `Map<prefixSum, count>`

14. **Parentheses Problems**

    - Approach: Match opening and closing
    - Examples:
        - Valid Parentheses
    - Usage: `Map<closingParen, openingParen>`

15. **Bit Manipulation**
    - Approach: Store bit patterns
    - Examples:
        - Repeated DNA Sequences
    - Usage: `Map<bitPattern, count>`

</br>

# Deque Usage

1. **Implementing Both Stack and Queue Operations**

    - As a Stack: You can use methods like addFirst() and removeFirst() to add and remove elements from the front,
      mimicking stack behavior.
    - As a Queue: You can use methods like addLast() and removeFirst() to enqueue and dequeue elements, mimicking queue
      behavior.

3. **Sliding Window Problems**

    - Deque is particularly useful in algorithms that require a sliding window over a sequence of data. For example, in
      finding the maximum or minimum in a sliding window over an array, a Deque can efficiently maintain the window's
      elements, allowing constant-time access to the window's maximum or minimum.

5. **Palindrome Checking**

    - You can use a Deque to check if a string is a palindrome. By pushing characters onto the Deque and then popping
      from both ends, you can compare characters from the front and back to determine if they are the same.

7. **Undo and Redo Operations**
    - In applications with undo and redo functionalities, a Deque can store the history of actions. The front of the
      Deque can store actions for undo, while the back can store actions for redo.

8. **Expression Parsing**

    - In parsing expressions, especially for balancing parentheses or evaluating postfix expressions, a Deque can be
      used to temporarily hold operands and operators.

10. **BFS (Breadth-First Search) and DFS (Depth-First Search)**

    - In graph traversal algorithms, a Deque can be used to manage the frontier. For BFS, you can use it as a queue, and
      for DFS, you can use it as a stack.

11. **Handling a Fixed Number of Recent Elements**

    - When you need to maintain a fixed number of recent elements, a Deque can be used to implement a circular buffer.
      By adding elements to the back and removing elements from the front, you can keep the buffer up to date with the
      most recent data.

13. **Job Scheduling**

    - In systems that require scheduling tasks or jobs, a Deque can help manage tasks that can be added and removed from
      either end, providing flexibility in task management.

</br>

# Two-Pointer Techniques

1. **Merge Two Sorted Arrays**

    - First pointer: `0th index of first array`
    - Second pointer: `0th index of second array`
    - Movement: Compare elements, place smaller in result, move corresponding pointer

2. **Two Sum (Sorted Array)**

    - First pointer: `0th index (left)`
    - Second pointer: `Last index (right)`
    - Movement: Move left if sum too small, right if sum too large

3. **Reverse Array/String**

    - First pointer: `0th index (left)`
    - Second pointer: `Last index (right)`
    - Movement: Swap elements, move left pointer right and right pointer left

4. **Remove Duplicates from Sorted Array**

    - First pointer: `0th index (unique elements)`
    - Second pointer: `1st index (iterator)`
    - Movement: Second pointer moves, first only when new unique element found

5. **Container With Most Water**

    - First pointer: `0th index (left)`
    - Second pointer: `Last index (right)`
    - Movement: Move the pointer with shorter height inward

6. **3Sum**

    - First pointer: `Fixed, iterates through array`
    - Second and Third: `Two-sum on remaining elements (like #2)`

7. **Sliding Window**

    - First pointer: `Start of window`
    - Second pointer: `End of window`
    - Movement: `Expand/contract window based on condition`

8. **Palindrome Check**

    - First pointer: `0th index (left)`
    - Second pointer: `Last index (right)`
    - Movement: Move towards center, comparing characters

9. **Linked List Cycle Detection**

    - First pointer: `Slow (moves one step)`
    - Second pointer: `Fast (moves two steps)`
    - Movement: If cycle exists, they'll meet

10. **Dutch National Flag**
    - First pointer: `0th index (left, for 0s)`
    - Second pointer: `0th index (iterator)`
    - Third pointer: `Last index (right, for 2s)`
    - Movement: Swap elements based on value, adjust pointers

</br>

# Sliding Window Techniques

- [Sliding Window](https://leetcode.com/discuss/interview-question/3722472/mastering-sliding-window-technique-a-comprehensive-guide)

1. **Fixed-size window:**

    - Slide a window of fixed size across the array/string
    - Useful for problems where the subarray/substring size is given
    - Example: `Finding the maximum sum subarray of a fixed size`

   **How it works:**

    - Initialize the window sum with the first k elements
    - Slide the window by one position at a time:
        - Add the next element to the sum
        - Subtract the first element of the previous window
    - Keep track of the maximum sum encountered

2. **Variable-size window:**

    - Use two pointers (start and end) to define the window
    - Expand or contract the window based on certain conditions
    - Useful for problems where you need to find a subarray/substring that satisfies certain criteria
    - Example: `Finding the longest substring without repeating characters`

   **How it works:**

    - Start with both pointers at the beginning
    - Expand the window (move end pointer) until a condition is violated
    - Contract the window (move start pointer) until the condition is satisfied again
    - Update the result if necessary
    - Repeat until the end pointer reaches the end of the array/string

3. **Window with Auxiliary Data Structure:**

    - Use a hash map or set to keep track of elements in the current window
    - Useful for problems involving unique elements or frequency counts
    - Example: `Finding the longest substring with at most K distinct characters`

   **How it works:**

    - Use a hash map to store character frequencies
    - Expand the window and update the hash map
    - If the number of distinct characters exceeds K, contract the window
    - Update the hash map as you contract
    - Keep track of the longest valid substring

4. **Sliding Window with a Counter:**

    - Maintain a counter for a specific condition
    - Update the counter as you slide the window
    - Useful for problems involving counting or finding occurrences
    - Example: `Finding the number of subarrays with K different integers`

   **How it works:**

    - Use a counter to keep track of a specific condition (e.g., number of unique elements)
    - Expand the window and update the counter
    - If the condition is met, update the result
    - Contract the window if necessary and update the counter
    - Continue this process until the end of the array/string is reached

#### LeetCode Problems

1. **Fixed Window Size:**

    - LeetCode 643: Maximum Average Subarray I
    - LeetCode 1343: Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

2. **Variable Window Size:**

    - LeetCode 3: Longest Substring Without Repeating Characters
    - LeetCode 76: Minimum Window Substring
    - LeetCode 209: Minimum Size Subarray Sum
    - LeetCode 424: Longest Repeating Character Replacement

3. **Window with Auxiliary Data Structure:**

    - LeetCode 159: Longest Substring with At Most Two Distinct Characters
    - LeetCode 340: Longest Substring with At Most K Distinct Characters
    - LeetCode 438: Find All Anagrams in a String

4. **Sliding Window with a Counter:**
    - LeetCode 239: Sliding Window Maximum
    - LeetCode 567: Permutation in String
    - LeetCode 1004: Max Consecutive Ones III

</br>

# Stack Techniques

1. **Monotonic Stack**

    - A stack that maintains elements in a strictly increasing or decreasing order
    - Used for problems involving finding the next greater/smaller element or calculating spans

   **How it works:**

    - Iterate through the elements
    - For each element, pop elements from the stack that violate the monotonic property
    - Push the current element onto the stack
    - The elements remaining in the stack after processing all elements often represent the solution

2. **Paired Brackets Matching**

    - Used for validating or processing strings with paired brackets or parentheses

   **How it works:**

    - Push opening brackets onto the stack
    - When encountering a closing bracket, check if it matches the top of the stack
    - If it matches, pop the top element; if not, the string is invalid
    - After processing all characters, the stack should be empty for a valid string

3. **Expression Evaluation**

    - Used for evaluating arithmetic expressions, especially those in Reverse Polish Notation (RPN)

   **How it works:**

    - For RPN: Push operands onto the stack
    - When an operator is encountered, pop the required number of operands, apply the operator, and push the result back
    - The final result is the top of the stack after processing all tokens

4. **Backtracking with Stack**

    - Used for problems that require exploring paths or combinations with the ability to backtrack

   **How it works:**

    - Push states or choices onto the stack as you explore
    - When backtracking, pop the stack to return to the previous state
    - Continue until all possibilities are explored

5. **Stack for Tree Traversal**

    - Used for implementing iterative versions of tree traversal algorithms

   **How it works:**

    - Push nodes onto the stack as you traverse
    - Pop nodes to process them
    - The order of pushing and processing determines the type of traversal (in-order, pre-order, post-order)

</br>

# Binary Search Techniques

1. **Basic Binary Search**

   **Technique**

   Search a sorted array by repeatedly dividing the search interval in half.

   **How it works**

    - [Binary Search Patterns](<https://leetcode.com/discuss/interview-question/1322500/5-variations-of-Binary-search-(A-Self-Note)>)

   **Common Applications**

    - Searching in sorted arrays
    - Implementing lower_bound and upper_bound

2. **Binary Search on Answer**

   **Technique**
   Use binary search to find an answer that satisfies certain conditions.

   **How it works**

    - Define a range where the answer must lie
    - Use binary search to narrow down the correct answer
    - Check if the mid value satisfies the problem conditions

   **Common Applications**

    - Minimizing maximum or maximizing minimum problems
    - Finding the Kth element in sorted arrays

3. **Binary Search on Floating Point Numbers**

   **Technique**
   Apply binary search to a continuous range of real numbers.

   **How it works**

    - Define a precision (epsilon)
    - Continue binary search until the range is smaller than the precision

   **Common Applications**

    - Finding square roots
    - Optimization problems with continuous domains

4. **Binary Search in Rotated Sorted Array**

   **Technique**
   Modify binary search to work on a sorted array that has been rotated.

   **How it works**

    - Find the pivot point where rotation occurs
    - Perform binary search on the appropriate half of the array

   **Common Applications**

    - Searching in rotated sorted arrays
    - Finding minimum in rotated sorted array

5. **Binary Search on Monotonic Functions**

   **Technique**
   Apply binary search to find roots or extrema of monotonic functions.

   **How it works**

    - Define the function and its monotonic property
    - Use binary search to narrow down the point where the function changes sign or reaches extremum

   **Common Applications**

    - Finding peaks in bitonic arrays
    - Optimization problems

6. **Multi-dimensional Binary Search**

   **Technique**
   Apply binary search on multiple dimensions or parameters simultaneously.

   **How it works**

    - Perform binary search on one dimension
    - For each step, solve a subproblem in other dimensions

   **Common Applications**

    - Median of two sorted arrays
    - Searching in sorted 2D matrices

7. **Binary Search with Prefix Sums**

   **Technique**
   Combine binary search with prefix sum array for range queries.

   **How it works**

    - Precompute prefix sum array
    - Use binary search to find appropriate ranges in the prefix sum array

   **Common Applications**

    - Finding subarrays with sum closest to a target
    - Optimizing subarray-related problems

### **1. Modified Binary Search**

**Definition**: A variation of binary search that modifies the standard search logic to solve problems beyond just
finding an exact match. It’s typically used to find the **best candidate** (like the largest element smaller than a
target, or smallest element greater than a target) in a sorted array.

### **Examples and Scenarios for Modified Binary Search**:

### 1. **Finding the Floor or Ceiling of a Target**:

- **Problem**: Given a sorted array, find the **largest element smaller than or equal** to a target (`floor`) or the *
  *smallest element greater than or equal** to a target (`ceiling`).
- **Example**:
    - Array: `[1, 2, 8, 10, 10, 12, 19]`, Target: `5` → Floor: `2`
    - Array: `[1, 2, 8, 10, 10, 12, 19]`, Target: `11` → Ceiling: `12`

### 2. **Searching for the First or Last Occurrence of a Target**:

- **Problem**: In a sorted array with duplicate elements, find the **first** or **last occurrence** of a target.
- **Example**:
    - Array: `[2, 4, 10, 10, 10, 18, 20]`, Target: `10` → First occurrence: index `2`, Last occurrence: index `4`

### 3. **Counting Occurrences of a Target**:

- **Problem**: Count how many times a target appears in a sorted array.
- **Approach**: Use two binary searches to find the first and last occurrences, then calculate the difference between
  their indices.
- **Example**:
    - Array: `[1, 2, 2, 2, 3, 4]`, Target: `2` → Count: `3`

### 4. **Finding the Peak Element in an Unimodal Array**:

- **Problem**: In an array where elements first increase and then decrease (unimodal array), find the **peak element**.
- **Example**:
    - Array: `[1, 3, 5, 7, 6, 4, 2]` → Peak: `7`
- **Approach**: Compare the middle element with its neighbors and adjust the search range accordingly.

### 5. **Finding the Rotation Point in a Rotated Sorted Array**:

- **Problem**: A sorted array is rotated at some pivot. Find the **index of the smallest element**, which is the
  rotation point.
- **Example**:
    - Array: `[6, 7, 9, 15, 19, 2, 3]` → Rotation point (smallest element): index `5`
- **Approach**: Use binary search to find where the rotation occurred.

### 6. **Search in a Nearly Sorted Array**:

- **Problem**: Search for an element in a nearly sorted array where each element is at most one position away from its
  correct position.
- **Example**:
    - Array: `[10, 3, 40, 20, 50, 80, 70]`, Target: `40` → Index: `2`
- **Approach**: Check the middle element, and also its immediate neighbors (since the array is almost sorted).

### 7. **Finding the Smallest Missing Positive Integer**:

- **Problem**: In a sorted array of distinct positive integers, find the smallest missing positive integer.
- **Example**:
    - Array: `[1, 2, 3, 5, 6]` → Smallest missing positive integer: `4`
- **Approach**: Use binary search to find the first index where the difference between the value and its index is more
  than 1.

### **2. Binary Search on the Answer**

**Definition**: In **binary search on the answer**, the goal is to find the **minimum or maximum value** that satisfies
a given condition. The search space is not the indices of an array, but the **range of possible answers**.

### **Examples and Scenarios for Binary Search on the Answer**:

### 1. **Minimum Number of Days to Make Bouquets**:

- **Problem**: You have an array of bloom days, and you need to find the **minimum number of days** required to make `m`
  bouquets, with each bouquet consisting of `k` adjacent flowers.
- **Example**:
    - Array: `[1, 10, 3, 10, 2]`, `m = 3`, `k = 1` → Minimum days: `3`
- **Approach**: Use binary search on the number of days to find the smallest day where it’s possible to make the
  required bouquets.

### 2. **Finding the Minimum Capacity to Ship Packages within D Days**:

- **Problem**: Given an array where each element represents the weight of a package, find the **minimum capacity** of a
  ship that can deliver all packages within `D` days.
- **Example**:
    - Array: `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`, `D = 5` → Minimum capacity: `15`
- **Approach**: Use binary search on the ship's capacity to find the minimum value that satisfies the constraint.

### 3. **Minimizing the Maximum Distance Between Gas Stations**:

- **Problem**: Given an array of positions of gas stations, you need to add `k` more stations and minimize the **maximum
  distance** between any two adjacent gas stations.
- **Example**:
    - Positions: `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]`, `k = 9` → Minimum max distance: `1.0`
- **Approach**: Use binary search on the maximum possible distance between gas stations.

### 4. **Finding the Maximum Number of Tasks You Can Complete**:

- **Problem**: You have `n` tasks, each with a certain deadline and a time to complete. Find the **maximum number of
  tasks** you can complete within the deadline.
- **Approach**: Use binary search on the number of tasks and check whether it’s possible to complete that many tasks by
  the deadline.

### 5. **Allocating Resources to Minimize Maximum Load**:

- **Problem**: You have `k` workers and `n` jobs. Each job has a different workload. Find the minimum maximum workload
  that any single worker would have.
- **Approach**: Use binary search on the maximum workload and check whether it’s possible to allocate the jobs so that
  no worker exceeds that workload.

### 6. **Kth Smallest Element in Multiplication Table**:

- **Problem**: Given an `m x n` multiplication table, find the **k-th smallest number** in the table.
- **Approach**: Use binary search on the possible values in the table, and for each mid-point, count how many numbers in
  the table are less than or equal to `mid`.

### 7. **Minimizing Maximum Page Allocation**:

- **Problem**: You have an array representing the number of pages in books, and you need to divide these books among `k`
  students so that the **maximum pages** assigned to any student is minimized.
- **Example**:
    - Pages: `[12, 34, 67, 90]`, `k = 2` → Minimum maximum pages: `113`
- **Approach**: Use binary search on the possible maximum page count and determine if a given max page count is
  feasible.

### 8. **Maximum Side Length of a Square with Sum <= Threshold**:

- **Problem**: Given a matrix of integers, find the **maximum side length** of a square sub-matrix where the sum of the
  elements in the square is less than or equal to a given threshold.
- **Approach**: Use binary search on the side length to find the maximum feasible square.

---

</br>

# Linked List Techniques

1. **Two-Pointer Technique**

    - Uses two pointers moving at different speeds or starting at different positions
    - Useful for detecting cycles, finding middle elements, or identifying intersections

   **How it works:**

    - Initialize two pointers, often called "slow" and "fast"
    - Move the pointers through the list at different rates
    - The relative position or meeting point of the pointers provides the solution

2. **Dummy Node**

    - Uses a dummy node as the head of the list to simplify edge cases
    - Particularly useful in problems involving list manipulation or merging

   **How it works:**

    - Create a dummy node and set it as the head of the new list
    - Perform operations on the list, starting from the dummy node
    - Return the next node of the dummy node as the head of the resulting list

3. **Reverse Linked List**

    - A fundamental operation used in many linked list problems
    - Can be done iteratively or recursively

   **How it works:**

    - _Iterative:_ Use three pointers (prev, current, next) to reverse links as you traverse
    - _Recursive:_ Recursively reverse the rest of the list and fix the head

4. **Merge Lists**

    - Combines two or more sorted linked lists into a single sorted list
    - Often used as a subproblem in more complex list manipulations

   **How it works:**

    - Compare the heads of the lists
    - Choose the smaller head and move it to the result list
    - Repeat until all lists are exhausted

5. **Fast and Slow Pointers**

    - A specific application of the two-pointer technique
    - Used for finding the middle of the list, detecting cycles, or finding the nth node from the end

   **How it works:**

    - Initialize two pointers, with the fast pointer moving twice as fast as the slow pointer
    - When the fast pointer reaches the end, the slow pointer will be at the middle

6. **Sentinel Node**

    - Similar to the dummy node, used to simplify edge cases
    - Particularly useful in problems involving deletion or insertion

   **How it works:**

    - Create a sentinel node and link it to the head of the list
    - Perform operations on the list, treating the sentinel as the start
    - Remove the sentinel node at the end if necessary

</br>

# Tree Techniques

```
       1
     /   \
    2     3
   / \   / \
  4   5 6   7
  
```

## Traditional Traversals

#### Inorder Traversal

- **Order**: `Visit left subtree → Node → Right subtree`
- **Output**: 4 2 5 1 6 3 7

#### Preorder Traversal

- **Order**: `Visit node → Left subtree → Right subtree`
- **Output**: 1 2 4 5 3 6 7

#### Postorder Traversal

- **Order**: `Visit left subtree → Right subtree → Node`
- **Output**: 4 5 2 6 7 3 1

#### Level Order Traversal

- **Order**: `Visit nodes level by level from root to leaves`
- **Output**: 1 2 3 4 5 6 7

## Reversed Traversals

#### Reversed Inorder Traversal

- **Order**: `Visit right subtree → Node → Left subtree`
- **Output**: 7 3 6 1 5 2 4

#### Reversed Preorder Traversal

- **Order**: `Visit node → Right subtree → Left subtree`
- **Output**: 1 3 7 6 2 5 4

#### Reversed Postorder Traversal

- **Order**: `Visit right subtree → Left subtree → Node`
- **Output**: 7 6 3 5 4 2 1

#### Reversed Level Order Traversal

- **Order**: `Visit nodes level by level from leaves to root, left to right at each level`
- **Output**: 4 5 6 7 2 3 1

### Tree Problem Categories

1. **Tree Traversal**
    - **Preorder Traversal**: Visit the root, then left subtree, then right subtree.
    - **Inorder Traversal**: Visit the left subtree, then root, then right subtree.
    - **Postorder Traversal**: Visit the left subtree, then right subtree, then root.
    - **Level Order Traversal**: Visit nodes level by level from top to bottom.

2. **Binary Search Tree (BST) Problems**
    - **Insertion and Deletion**: Adding or removing nodes while maintaining the BST property.
    - **Searching**: Finding a specific value or node.
    - **Validation**: Checking if a tree is a valid BST.
    - **Finding the Lowest Common Ancestor (LCA)**: Finding the common ancestor of two nodes in a BST.
    - **Converting Sorted Array to BST**: Creating a balanced BST from a sorted array.

3. **Binary Tree Properties**
    - **Height and Depth**: Calculating the height or depth of the tree.
    - **Diameter**: Finding the longest path between any two nodes.
    - **Symmetry**: Checking if a tree is symmetric (mirror image of itself).
    - **Balanced Tree**: Checking if the tree is height-balanced.

4. **Tree Construction**
    - **Constructing from Traversal Orders**: Building a tree from preorder and inorder, or postorder and inorder
      traversals.
    - **Constructing from Serialization**: Reconstructing a tree from a serialized format (like in a string).

5. **Tree Algorithms**
    - **Depth-First Search (DFS)**: Using recursion or stack-based approach.
    - **Breadth-First Search (BFS)**: Using queue-based approach.
    - **Lowest Common Ancestor (LCA)**: Finding the LCA in different types of trees (BST, general trees).

6. **Tree Modifications**
    - **Flattening**: Converting a binary tree to a linked list.
    - **Invert/Reverse**: Flipping the tree upside down.
    - **Merge Trees**: Combining two trees into one.

7. **Special Trees**
    - **N-ary Trees**: Handling trees where each node can have more than two children.
    - **Trie (Prefix Tree)**: Insertion, search, and deletion operations.

8. **Advanced Tree Problems**
    - **Segment Tree**: For range queries and updates.
    - **Fenwick Tree (Binary Indexed Tree)**: For cumulative frequency tables.
    - **Suffix Tree and Suffix Array**: For string processing problems.

</br>

# Heap and Priority Queue Techniques

1. **Basic Heap Operations**

   **Concept**

   A heap is a specialized tree-based data structure that satisfies the heap property. In a max heap, for any given
   node, the node's value is greater than or equal to the values of its children. In a min heap, the node's value is
   less than or equal to the values of its children.

   **Key Operations**

    - Insertion: O(log n)
    - Deletion of top element: O(log n)
    - Peek top element: O(1)
    - Heapify (building a heap from an array): O(n)

   **How it works**

    - Heaps are typically implemented as arrays, where for any element at index i:
    - Its left child is at index 2i + 1
    - Its right child is at index 2i + 2
    - Its parent is at index (i - 1) / 2
    - When inserting or deleting, the heap property is maintained by "bubbling up" or "sinking down" elements.

2. **K-th Largest/Smallest Element**

   **Technique**

   Use a min heap for K-th largest, or a max heap for K-th smallest.

   **How it works**

    - For K-th largest:

    1. Maintain a min heap of size K.
    2. Iterate through all elements:
        - If heap size < K, add the element.
        - If current element > heap top, remove top and add current element.
    3. The top of the heap is the K-th largest element.

3. **Merge K Sorted Lists/Arrays**

   **Technique**

   Use a min heap to efficiently merge multiple sorted sequences.

   **How it works**

    1. Create a min heap and insert the first element from each list along with its list identifier.
    2. While the heap is not empty:

    - Extract the minimum element (this is the next element in the merged sequence).
    - If the list this element came from is not exhausted, insert its next element into the heap.

    3. Time complexity: O(N log K), where N is the total number of elements and K is the number of lists.

4. **Sliding Window Problems**

   **Technique**
   Use a heap to maintain the maximum or minimum within a sliding window.

   **How it works**

    1. Use a heap to store elements within the current window.
    2. As the window slides:

    - Remove elements that are no longer in the window.
    - Add the new element entering the window.
    - The top of the heap represents the max/min of the current window.

    3. To handle element removal efficiently, you might need to pair each value with its index in the heap.

5. **Top K Frequent Elements**

   **Technique**

   Use a hash map for counting and a heap for selecting top K.

   **How it works**

    1. Use a hash map to count the frequency of each element.
    2. Use a min heap of size K to keep track of the K most frequent elements:
        - If heap size < K, add the element.
        - If current element's frequency > heap top's frequency, remove top and add current element.
    3. The heap will contain the K most frequent elements.
    4. Time complexity: O(N log K), where N is the number of elements.

6. **Median in a Stream of Integers**

   **Technique**

   Use two heaps: a max heap for the lower half and a min heap for the upper half.

   **How it works**

    1. Maintain two heaps:

    - A max heap for the lower half of the numbers.
    - A min heap for the upper half of the numbers.

    2. Balance the heaps so their sizes differ by at most 1.
    3. When a new number comes in:

    - Add to the appropriate heap.
    - Rebalance if necessary by moving the top element from one heap to the other.

    4. The median is either the top of the max heap (if it's larger) or the average of both tops.

7. **Dijkstra's Algorithm (Shortest Path)**

   **Technique**
   Use a min heap (priority queue) to always process the vertex with the smallest known distance.

   **How it works**

    1. Initialize distances to all vertices as infinite, except the start vertex (distance = 0).
    2. Push the start vertex into the min heap.
    3. While the heap is not empty:

    - Extract the vertex with minimum distance.
    - For each neighbor of this vertex:
        - Calculate the distance through the current vertex.
        - If this distance is less than the previously known distance, update it and push the neighbor onto the heap.

    4. Time complexity: O((V + E) log V), where V is the number of vertices and E is the number of edges.

### General Approach to Heap/Priority Queue Problems

1. **Identify if a heap is suitable:**

    - Problems involving finding top K elements, sorting, or maintaining a dynamic set of elements where you frequently
      need the max/min.

2. **Choose between min heap and max heap:**

    - Min heap for problems where you need to repeatedly find and remove the smallest element.
    - Max heap for problems where you need to repeatedly find and remove the largest element.

3. **Consider using two heaps:**

    - Useful for problems like finding the median in a stream of numbers.

4. **Pair values with additional information:**

    - Often useful to store pairs (value, index) or (value, frequency) in the heap.

5. **Be mindful of time complexity:**

    - Insertion and deletion operations are O(log n), where n is the size of the heap.
    - Building a heap from an array is O(n).

6. **Use with other data structures:**

    - Heaps are often used in combination with hash maps, especially for frequency-based problems.

7. **Custom comparators:**

    - For complex objects, define custom comparison logic to determine heap ordering.

8. **Lazy deletion:**

    - In some cases, it's more efficient to mark elements as deleted rather than removing them immediately.

9. **Space-time trade-off:**

    - Heaps can often provide a good balance between time efficiency and space usage.

10. **Practice and learn patterns:**
    - Many heap problems share similar structures. Recognizing these patterns can speed up problem-solving.

</br>

# Backtracking Techniques

1. **Basic Backtracking Structure**

   **How it works:**

    - Start with an initial state or partial solution.
    - Recursively explore possible candidates to build the solution.
    - If a candidate leads to a valid solution, add it to the result.
    - If a candidate doesn't lead to a valid solution, backtrack (undo the last choice) and try the next candidate.
    - Continue this process until all possibilities have been explored.

   The key components are:

    1. A method to check if the current state is a valid solution.
    2. A way to generate candidate choices for the next step.
    3. A mechanism to apply and undo changes to the current state.
    4. A recursive function that ties all these components together.

2. **Pruning in Backtracking**

   **How it works**

    - Before fully exploring a branch, check if it can potentially lead to a valid solution.
    - If the branch cannot possibly lead to a valid solution, skip it entirely.
    - This is typically implemented by adding conditions before the recursive call.
    - Pruning can significantly reduce the number of explored states, improving efficiency.

3. **State Space Tree**

   **How it works**

    - Conceptualize the problem as a tree where:
    - Each node represents a partial solution or state.
    - Edges represent choices or decisions.
    - Leaf nodes are either complete solutions or dead ends.
    - Traverse this tree using depth-first search.
    - Backtracking occurs when you reach a leaf node and need to explore other branches.

4. **Backtracking with Memoization**

   **How it works:**

    - Combine backtracking with dynamic programming techniques.
    - Store the results of subproblems in a data structure (often a hash map).
    - Before solving a subproblem, check if its result is already stored.
    - If found, use the stored result instead of recomputing.
    - This optimization is particularly useful when the same subproblems are encountered multiple times during
      backtracking.

5. **Iterative Backtracking**

   **How it works:**

    - Instead of using recursion, use a stack to manage the states.
    - Push initial state onto the stack.
    - While the stack is not empty:
    - Pop a state from the stack.
    - If it's a solution, process it.
    - If not, generate new candidate states and push them onto the stack.
    - This approach avoids potential stack overflow issues in deep recursions.
    - It can be more complex to implement but may be necessary for very deep search trees.

### General Approach to Backtracking Problems

1. **Identify the problem structure:**

    - Determine if the problem requires finding all solutions or just one
    - Identify the constraints and validity conditions

2. **Define the state:**

    - Determine what information needs to be maintained in each state
    - Design a suitable data structure to represent the state

3. **Implement the backtracking function:**

    - Define base cases (solution found or dead end)
    - Implement the recursive exploration of possibilities

4. **Optimize with pruning:**

    - Identify conditions that allow early termination of a branch
    - Implement checks to skip invalid paths early

5. **Consider time and space complexity:**

    - Analyze the branching factor and depth of the search tree
    - Look for opportunities to reduce redundant computations

6. **Test with small examples:**

    - Verify the algorithm with simple, known cases
    - Gradually increase complexity to test robustness

7. **Debug systematically:**

    - Use print statements or debuggers to visualize the backtracking process
    - Verify that all valid solutions are being generated and no invalid ones are included

8. **Consider iterative implementation:**

    - If stack overflow is a concern, convert the recursive solution to an iterative one

9. **Optimize for specific problem requirements:**

    - If only one solution is needed, consider adding early termination
    - If counting solutions is sufficient, avoid storing all solutions explicitly

10. **Practice and learn patterns:**
    - Many backtracking problems share similar structures
    - Recognizing these patterns can speed up problem-solving in the future

</br>

# Graph Techniques

1. **Graph Representation**

   **Techniques**

    - Adjacency Matrix
    - Adjacency List

   **How it works**

    - Adjacency Matrix: 2D array where matrix[i][j] indicates an edge from i to j
    - Adjacency List: Array of lists where list[i] contains nodes adjacent to node i

   **Common Applications**

    - Dense graphs (Adjacency Matrix)
    - Sparse graphs (Adjacency List)

2. **Depth-First Search (DFS)**

   **Technique**
   Explore as far as possible along each branch before backtracking.

   **How it works**

    - Use recursion to visit all neighbors of a node
    - Keep track of visited nodes to avoid cycles
    - Process each node once

   **Common Applications**

    - Topological sorting
    - Cycle detection
    - Path finding

3. **Breadth-First Search (BFS)**

   **Technique**
   Explore all neighbor nodes at the present depth before moving to nodes at the next depth level.

   **How it works**

    - Use a queue to store nodes at each level
    - Process nodes in the order they were added to the queue
    - Keep track of visited nodes to avoid revisiting

   **Common Applications**

    - Shortest path in unweighted graphs
    - Level-order traversal
    - Finding connected components

4. **Dijkstra's Algorithm**

   **Technique**
   Find the shortest path between nodes in a weighted graph.

   **How it works**

    - Use a priority queue to select the node with the smallest tentative distance
    - Update distances to all adjacent nodes
    - Repeat until all nodes are visited

   **Common Applications**

    - Shortest path problems
    - Network routing protocols

5. **Union-Find (Disjoint Set)**

   **Technique:**
   Efficiently keep track of a partition of a set into disjoint subsets.

   **How it works**

    - Implement `find` and `union` operations
    - Use path compression and union by rank for optimization

   **Common Applications**

    - Kruskal's algorithm for Minimum Spanning Tree
    - Detecting cycles in undirected graphs

6. **Topological Sort**

   **Technique**
   Linear ordering of vertices such that for every directed edge (u, v), vertex u comes before v in the ordering.

   **How it works**

    - Use DFS and stack to store nodes in reverse finishing time
    - Alternatively, use Kahn's algorithm with in-degree tracking

   **Common Applications**

    - Dependency resolution
    - Task scheduling

7. **Strongly Connected Components**

   **Technique**
   Find maximal strongly connected subgraphs.

   **How it works**

    - Kosaraju's algorithm: Perform DFS, reverse graph, perform DFS again
    - Tarjan's algorithm: Single DFS with lowlink values

   **Common Applications**

    - Social network analysis
    - Compiler optimization

8. **Minimum Spanning Tree**

   **Techniques**

    - Kruskal's Algorithm
    - Prim's Algorithm

   **How it works**

    - Kruskal's: Sort edges, add if no cycle formed
    - Prim's: Grow tree from a starting vertex, always adding the cheapest edge

   **Common Applications**

    - Network design
    - Cluster analysis

### General Approach to Graph Problems

1. **Choose appropriate representation:**

    - Adjacency list for sparse graphs
    - Adjacency matrix for dense graphs or when quick edge lookup is needed

2. **Identify the problem type:**

    - Traversal, shortest path, connectivity, etc.
    - This helps in choosing the right algorithm

3. **Consider graph properties:**

    - Directed vs undirected
    - Weighted vs unweighted
    - Cyclic vs acyclic

4. **Implement basic graph operations:**

    - Adding edges, checking for adjacency, etc.

5. **Choose the right algorithm:**

    - DFS for exploring all paths or topological sorting
    - BFS for shortest path in unweighted graphs
    - Dijkstra's for shortest path in weighted graphs

6. **Handle edge cases:**

    - Empty graph
    - Graph with one node
    - Disconnected graphs

7. **Optimize for space and time:**

    - Consider iterative vs recursive implementations
    - Use appropriate data structures (e.g., priority queues for Dijkstra's)

8. **Debug systematically:**

    - Use small, simple graphs to test your algorithm
    - Visualize the graph and algorithm steps

9. **Consider problem-specific optimizations:**

    - Can you preprocess the graph?
    - Are there any special properties you can exploit?

10. **Practice and learn patterns:**
    - Many graph problems share similar structures
    - Recognizing these patterns can speed up problem-solving

# Dynamic Programming

### 1. **0/1 Knapsack**

- **Scenario**: Imagine you are a backpacker going on a hiking trip. You have a backpack with a weight limit of **10 kg
  **. You also have several items to choose from:
    - **Item A**: Weight = 4 kg, Value = $40
    - **Item B**: Weight = 5 kg, Value = $50
    - **Item C**: Weight = 6 kg, Value = $60

  The **0/1 Knapsack** problem asks: which items should you pack to maximize the total value without exceeding the
  weight limit of 10 kg?

- **Key Decision**: For each item, you must make a binary decision: either take it or leave it. You can't take half of
  an item or multiple units of the same item. For example, you can either take **Item A** (which weighs 4 kg and is
  worth $40) or leave it behind — there's no in-between.

- **Outcome**: After evaluating all the options (Item A, B, and C), you would find the combination that gives the
  maximum value without exceeding the 10 kg limit. You might take Item B (5 kg, $50) and Item A (4 kg, $40) because
  their combined weight is 9 kg (within the limit) and their total value is $90, which is optimal.

### 2. **Fractional Knapsack (Greedy Approach)**

- **Scenario**: You're again packing for a trip, but this time, you can **take fractions of the items**. Let’s say your
  weight limit is **15 kg**:
    - **Item A**: Weight = 10 kg, Value = $100
    - **Item B**: Weight = 5 kg, Value = $60
    - **Item C**: Weight = 8 kg, Value = $80

  The **Fractional Knapsack** problem asks: how much of each item should you take to maximize value?

- **Key Feature**: You can take fractions of items. For example, you can take **half of Item A** (5 kg of a 10 kg item)
  and get **$50** worth of value from it, instead of having to take the whole item.

- **Greedy Approach**: To maximize value, you calculate the **value-to-weight ratio** for each item:
    - **Item A**: $100 / 10 kg = $10 per kg
    - **Item B**: $60 / 5 kg = $12 per kg
    - **Item C**: $80 / 8 kg = $10 per kg

  You start by taking all of **Item B** (5 kg, $60, best value per kg), then take **Item C** (8 kg, $80), and if there's
  still room, take a **fraction of Item A** until the weight limit is reached.

- **Outcome**: With this approach, you maximize the value by prioritizing items with the highest value-to-weight ratio
  first. This problem is solved using a greedy algorithm.

### 3. **Bounded Knapsack**

- **Scenario**: Let’s say you're running a **store** and want to maximize the value of the items you display in a
  limited space. However, this time, you have a **fixed quantity** of each item available:
    - **Item A**: Weight = 2 kg, Value = $30, Quantity available = 2
    - **Item B**: Weight = 3 kg, Value = $50, Quantity available = 3
    - **Item C**: Weight = 5 kg, Value = $70, Quantity available = 1

  The **Bounded Knapsack** problem asks: given these limited quantities, how can you maximize the value while staying
  within the weight limit?

- **Key Feature**: You can take **multiple units** of an item, but only up to the given limit. For example, if you want
  to take **Item A**, you can take **1 or 2** units, but not 3, because only 2 are available.

- **Outcome**: You would consider all the items and their quantities to find the best combination that maximizes value.
  For instance, you might take 2 units of **Item A** (4 kg, $60) and 1 unit of **Item C** (5 kg, $70) to maximize value
  while staying under a weight limit of 9 kg.

### 4. **Unbounded Knapsack (Infinite Knapsack)**

- **Scenario**: You are a **merchant** with an unlimited supply of items to sell, but you have a limited display space
  in your shop (say, 20 kg capacity). You can display any number of these items:
    - **Item A**: Weight = 3 kg, Value = $20
    - **Item B**: Weight = 4 kg, Value = $30
    - **Item C**: Weight = 5 kg, Value = $50

  The **Unbounded Knapsack** problem asks: how many units of each item should you take to maximize the total value
  within the weight limit?

- **Key Feature**: You can take **unlimited quantities** of each item. For instance, if **Item A** (3 kg, $20) seems
  valuable to you, you can take as many of them as your weight limit allows.

- **Outcome**: In this case, you'd consider how many times you can take each item to maximize value while staying within
  the weight limit. For example, you might realize that taking **4 units of Item B** (16
  kg, $120) gives you more value than taking **6 units of Item A** (18 kg, $120), and you'd have room for **1 unit of
  Item C** (5 kg, $50) to further maximize the value.

### Summary of Key Differences:

1. **0/1 Knapsack**: You must decide for each item whether to take it or not; you can't take fractions or multiple
   units.
2. **Fractional Knapsack**: You can take fractions of items to maximize the total value, solved using a greedy approach.
3. **Bounded Knapsack**: You can take multiple units of each item, but each has a limit on how many you can take.
4. **Unbounded Knapsack**: You can take an unlimited number of units of any item, constrained only by the weight limit.

## 5. **Longest Common Subsequence (LCS)**

- **Scenario**: Suppose you have two sequences of characters:
    - **Sequence 1**: `ABCBDAB`
    - **Sequence 2**: `BDCAB`

  The **Longest Common Subsequence (LCS)** problem is to find the longest subsequence that is common to both sequences.

- **Key Concept**: A subsequence is a sequence that can be derived from another sequence by deleting some or none of the
  elements without changing the order of the remaining elements.

- **Example**: For the sequences above, the LCS is `BCAB` (length 4). The subsequences `BCAB`, `BDAB`, and `BCAB` are
  all common, but `BCAB` is the longest.

- **Approach**: Use dynamic programming to build a table where each cell `dp[i][j]` represents the length of the LCS of
  the first `i` characters of Sequence 1 and the first `j` characters of Sequence 2.

- **Outcome**: The table is filled based on comparing characters from both sequences and using previously computed
  solutions to build up the length of the LCS.

## 6. **Longest Increasing Subsequence (LIS)**

- **Scenario**: Given an array of integers:
    - **Array**: `[10, 22, 9, 33, 21, 50, 41, 60, 80]`

  The **Longest Increasing Subsequence (LIS)** problem is to find the length of the longest subsequence where the
  elements are in increasing order.

- **Key Concept**: Unlike the LCS, the subsequence does not need to be contiguous but must be in increasing order.

- **Example**: For the array above, the LIS could be `10, 22, 33, 50, 60, 80` (length 6).

- **Approach**: Use dynamic programming where `dp[i]` represents the length of the longest increasing subsequence ending
  at index `i`. Update `dp[i]` by checking all previous elements and extending the increasing subsequences.

- **Outcome**: By examining all potential subsequences and updating the lengths dynamically, you find the length of the
  LIS for the array.

## 7. **Dynamic Programming on Grid**

- **Scenario**: Consider a grid where each cell contains a number, and you want to find the maximum path sum from the
  top-left to the bottom-right corner. You can only move right or down.

  **Grid Example**:
  ```
  2  3  1
  1  5  2
  4  3  6
  ```

- **Key Concept**: The **Dynamic Programming on Grid** problem involves filling a table based on previous computations
  to find an optimal path or value.

- **Approach**: Use a 2D DP table where each cell `dp[i][j]` represents the maximum sum path to reach cell `(i, j)`.
  Update each cell based on values from the top and left cells:  `dp[i][j] = grid[i][j] + max(dp[i-1][j], dp[i][j-1])`

- **Outcome**: By processing each cell in the grid and combining the results from neighboring cells, you determine the
  maximum value that can be achieved for the given path constraints.

## 8. **Dynamic Programming on Trees**

- **Scenario**: Suppose you have a tree structure and want to find the maximum sum of values from the root to any leaf
  node, with constraints on what paths are allowed.

- **Key Concept**: **Dynamic Programming on Trees** involves recursively solving problems by processing nodes and their
  subtrees to gather information and compute optimal solutions.

- **Approach**:
    1. **Define State**: Use DP where `dp[node]` represents the optimal solution for the subtree rooted at `node`.
    2. **Recurrence Relation**: Solve for each node by combining results from its children. For example, if you want the
       maximum sum path, you might use:
       `dp[node] = node_value + max(dp[child1], dp[child2], ...) `

    3. **Process Tree**: Traverse the tree (e.g., using DFS) to compute DP values for each node based on its children.

- **Outcome**: By solving the DP problem at each node and using results from subtrees, you compute the optimal solution
  for the entire tree structure.

## Recurrence Relations

### 1. **Fibonacci Sequence**

- **Problem**: Find the \( n \)-th Fibonacci number.
- **Description**: Calculate Fibonacci numbers using previously computed values.
- **Recurrence Relation**:
  ```html
  F(n) = F(n-1) + F(n-2)
  ```

### 2. **0/1 Knapsack Problem**

- **Problem**: Given a set of items with weight and value, maximize the value without exceeding a weight capacity.
- **Description**: You can either take or leave each item.
- **Recurrence Relation**:
  ```html
  dp[i][w] = max(dp[i-1][w], dp[i-1][w-w_i] + v_i)
  ```
  where `w_i` and `v_i` are the weight and value of the \( i \)-th item.

### 3. **Fractional Knapsack Problem**

- **Problem**: Maximize the value by taking fractions of items, given a weight limit.
- **Description**: Use a greedy approach based on value-to-weight ratio.
- **Approach**: Solve using a greedy algorithm by selecting items with the highest value-to-weight ratio first.

### 4. **Longest Common Subsequence (LCS)**

- **Problem**: Find the longest subsequence common to two sequences.
- **Description**: Build a DP table to track the length of the common subsequence for all prefixes.
- **Recurrence Relation**:
  ```html
  dp[i][j] = 
  <span style="color:blue;">1 + dp[i-1][j-1],</span> if X[i] = Y[j]
  <span style="color:red;">max(dp[i-1][j], dp[i][j-1]),</span> otherwise
  ```

### 5. **Longest Increasing Subsequence (LIS)**

- **Problem**: Find the longest increasing subsequence in an array.
- **Description**: Track the length of the longest increasing subsequence up to each element.
- **Recurrence Relation**:
  ```html
  dp[i] = max(dp[j] + 1) for j < i and arr[j] < arr[i]
  ```

### 6. **Edit Distance (Levenshtein Distance)**

- **Problem**: Find the minimum number of operations (insertions, deletions, substitutions) required to convert one
  string into another.
- **Description**: Use DP to compute the cost of transforming prefixes of the two strings.
- **Recurrence Relation**:
  ```html
  dp[i][j] = 
  <span style="color:blue;">dp[i-1][j-1],</span> if X[i] = Y[j]
  <span style="color:red;">1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]),</span> otherwise
  ```

### 7. **Coin Change Problem**

- **Problem**: Find the minimum number of coins needed to make a given amount.
- **Description**: Use DP to track the minimum coins required for each amount.
- **Recurrence Relation**:
  ```html
  dp[amount] = min(dp[amount - coin] + 1)
  ```

### 8. **Matrix Chain Multiplication**

- **Problem**: Determine the most efficient way to multiply a sequence of matrices.
- **Description**: Compute the minimum number of scalar multiplications needed.
- **Recurrence Relation**:
  ```html
  dp[i][j] = min(dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j])
  ```

### 9. **0/1 Knapsack with Bounded Items**

- **Problem**: Each item has a limited quantity available.
- **Description**: Modify the 0/1 Knapsack approach to account for item limits.

### 10. **Unbounded Knapsack Problem**

- **Problem**: Each item can be selected an unlimited number of times.
- **Description**: Use DP to find the maximum value with unlimited items.
- **Recurrence Relation**:
  ```html
  dp[i] = max(dp[i], dp[i - weight_j] + value_j)
  ```

### 11. **Subset Sum Problem**

- **Problem**: Determine if there is a subset of numbers that sums up to a given value.
- **Description**: Use DP to check possible sums that can be formed.
- **Recurrence Relation**:
  ```html
  dp[i][j] = dp[i-1][j] or dp[i-1][j - nums[i]]
  ```

### 12. **Longest Palindromic Subsequence**

- **Problem**: Find the longest subsequence in a string that is a palindrome.
- **Description**: Build a DP table to track the longest palindromic subsequence.
- **Recurrence Relation**:
  ```html
  dp[i][j] = 
  <span style="color:blue;">2 + dp[i+1][j-1],</span> if X[i] = X[j]
  <span style="color:red;">max(dp[i+1][j], dp[i][j-1]),</span> otherwise
  ```

### 13. **Partition Problem**

- **Problem**: Determine if a given set can be partitioned into two subsets with equal sum.
- **Description**: Use DP to check if the sum can be split into two equal subsets.

### 14. **Rod Cutting Problem**

- **Problem**: Given a rod of length \( n \) and a price table, find the maximum revenue obtainable by cutting up the
  rod and selling the pieces.
- **Description**: Use DP to compute maximum revenue for different lengths of rod.
- **Recurrence Relation**:
  ```html
  dp[i] = max(price[j] + dp[i - j - 1]) for j < i
  ```

---

Let me know if you need any more information!



</br>

# Bit Manipulation

### Bitwise effects on numbers

```java

/*
 * Left Shift (<<) :
 * The left shift operator (<<) shifts the bits of a number to the left by a specified number of positions.
 * Each bit is shifted to the left by the specified amount, and zeros are added to the right.
 */
int x = 5; // Binary representation: 000...0000101
int y = x << 2; // Shift left by 2 positions
// After shifting left by 2 positions, the binary representation becomes: 000...0010100

// Multiplication by powers of 2
/*  5 * 2^2 = 20 **/
    System.out.

println(y); // Output: 20

/*
 * Right Shift (>>):
 * The right shift operator (>>) shifts the bits of a number to the right by a specified number of positions.
 * Each bit is shifted to the right by the specified amount.
 */

int a = 20; // Binary representation: 000...0010100
int b = a >> 2; // Shift right by 2 positions
// After shifting right by 2 positions, the binary representation becomes: 000...0000101

// Division by powers of 2
/*  20 / 2^2 = 5 **/
    System.out.

println(b); // Output: 5

```

### Check if a Number is Even or Odd

```java

// Input Examples:
// isEven(4):
// 4 in binary: 0100
// 4 & 1: 0100 & 0001 = 0000 (result is 0, so 4 is even)

// isEven(7):
// 7 in binary: 0111
// 7 & 1: 0111 & 0001 = 0001 (result is 1, so 7 is odd)

public boolean isEven(int number) {
    return (number & 1) == 0;
}

```

### Toggle a Specific Bit

`It means doing NOT operation at a specific bit position(using XOR ^)`

```java

// Input Examples:
// toggleBit(5, 1):
// 5 in binary: 0101
// 1 << 1 (shift left by 1): 0010
// 5 ^ 2: 0101 ^ 0010 = 0111 (result is 7)

// toggleBit(8, 3):
// 8 in binary: 1000
// 1 << 3 (shift left by 3): 1000
// 8 ^ 8: 1000 ^ 1000 = 0000 (result is 0)

public int toggleBit(int number, int bitPosition) {
    return number ^ (1 << bitPosition);
}


```

### Set a Specific Bit

`It means setting 1 at a specific bit position (using OR | )`

```java

// Input Examples:
// setBit(5, 2):
// 5 in binary: 0101
// 1 << 2 (shift left by 2): 0100
// 5 | 4: 0101 | 0100 = 0101 (result is 7)

// setBit(10, 1):
// 10 in binary: 1010
// 1 << 1 (shift left by 1): 0010
// 10 | 2: 1010 | 0010 = 1010 (result is 10)

public int setBit(int number, int bitPosition) {
    return number | (1 << bitPosition);
}

```

### clear a Specific Bit

`It means setting 0 at a specific bit position (using AND and Complement )`

```java

// Input Examples:
// clearBit(7, 1):
// 7 in binary: 0111
// ~(1 << 1) (shift left by 1 and complement): ~0010 = 1101
// 7 & 1101: 0111 & 1101 = 0101 (result is 5)

// clearBit(15, 3):
// 15 in binary: 1111
// ~(1 << 3) (shift left by 3 and complement): ~1000 = 0111
// 15 & 0111: 1111 & 0111 = 0111 (result is 7)

public int clearBit(int number, int bitPosition) {
    return number & ~(1 << bitPosition);
}

```

### Count the Number of Set Bits (Hamming Weight)

```java

// Input Examples:
// countSetBits(7):
// 7 in binary: 0111
// Number of 1's: 3

// countSetBits(10):
// 10 in binary: 1010
// Number of 1's: 2

public int countSetBits(int number) {
    int count = 0;
    while (number != 0) {
        count += (number & 1);
        number >>= 1;
    }
    return count;
}

```

### Find the Position of the Rightmost Set Bit

```java

// Input Examples:
// findRightmostSetBit(12):
// 12 in binary: 1100
// -12 in binary (two's complement): 0011 (inverting 1100) + 1 = 0100
// 12 & -12: 1100 & 0100 = 0100 (result is 4)

// findRightmostSetBit(18):
// 18 in binary: 10010
// -18 in binary: 01101 (inverting 10010) + 1 = 01110
// 18 & -18: 10010 & 01110 = 00110 (result is 2)

public int findRightmostSetBit(int number) {
    return number & -number;
}

```

### Check if a Number is a Power of Two

```java

// Input Examples:
// isPowerOfTwo(4):
// 4 in binary: 0100
// 4 - 1 = 3 (binary: 0011)
// 4 & 3: 0100 & 0011 = 0000 (result is 0, so 4 is a power of two)

// isPowerOfTwo(6):
// 6 in binary: 0110
// 6 - 1 = 5 (binary: 0101)
// 6 & 5: 0110 & 0101 = 0100 (result is not 0, so 6 is not a power of two)

public boolean isPowerOfTwo(int number) {
    return (number > 0) && ((number & (number - 1)) == 0);
}

```

### Swap Two Numbers Without Using a Temporary Variable

```java

// Input Examples:
// arr = [3, 5]; swap(arr, 0, 1);
// Before swap: [3, 5]
// 3 ^ 5 = 6 (0110)
// 5 ^ 6 = 3
// 6 ^ 3 = 5
// After swap: [5, 3]

// arr = [7, 2]; swap(arr, 0, 1);
// Before swap: [7, 2]
// 7 ^ 2 = 5 (0101)
// 2 ^ 5 = 7
// 5 ^ 7 = 2
// After swap: [2, 7]

public void swap(int[] arr, int i, int j) {
    if (i != j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}

```

### Reverse Bits of an Integer

```java

// Input Examples:
// reverseBits(5):
// 5 in binary: 00000101
// Reversed: 10100000 (result is 160)

// reverseBits(15):
// 15 in binary: 00001111
// Reversed: 11110000 (result is 240)

public int reverseBits(int number) {
    int result = 0;
    while (number > 0) {
        result = (result << 1) | (number & 1);
        number >>= 1;
    }
    return result;
}


```

### Determine if Two Integers Have Opposite Signs

```java

// Input Examples:
// haveOppositeSigns(5, -3):
// 5 in binary: 00000000000000000000000000000101
// -3 in binary (two's complement): 11111111111111111111111111111101
// 5 ^ -3: 00000000000000000000000000000101 ^ 11111111111111111111111111111101 = 11111111111111111111111111111000 (negative result)
// Result: true (opposite signs)

// haveOppositeSigns(-7, -8):
// -7 in binary: 11111111111111111111111111111001
// -8 in binary: 11111111111111111111111111111000
// -7 ^ -8: 11111111111111111111111111111001 ^ 11111111111111111111111111111000 = 00000000000000000000000000000001 (positive result)
// Result: false (same signs)


public boolean haveOppositeSigns(int a, int b) {
    return (a ^ b) < 0;
}

```


</br>

# Matrix

### Basic Directions (left, right, top, down)

- `{0, 1}` : Represents movement to the right
- `{0, -1}`: Represents movement to the left
- `{1, 0}` : Represents movement downwards
- `{-1, 0}`: Represents movement upwards

### Additional diagonal movements

- `{1, 1}` : Represents movement diagonally down and to the right
- `{1, -1}` : Represents movement diagonally down and to the left
- `{-1, 1}` : Represents movement diagonally up and to the right
- `{-1, -1}`: Represents movement diagonally up and to the left

### Matrix Conversion

Convert `n * m` matrix to an array : `a[row * m + col] = matrix[row][col]`

```java
    public static int[] matrixToArray(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[] array = new int[m * n];

    for (int row = 0; row < m; row++) {
        System.arraycopy(matrix[row], 0, array, row * n + 0, n);
    }
    return array;
}
```

Convert array to `n * m` matrix : `matrix[x / m][x % m] = a[x]`;

```java
public static int[][] arrayToMatrix(int[] array, int n, int m) {
    int[][] matrix = new int[n][m];

    for (int x = 0; x < array.length; x++) {
        matrix[x / m][x % m] = array[x];
    }
    return matrix;
}
```

### Grid Number in matrix

gridNumber = `(row / 3) * 3 + (col / 3)`

# Hashing Techniques

## Polynomial Rolling Hash

Polynomial Rolling Hash is a simple yet effective hashing technique, especially used in string hashing. The idea is to
represent a string as a polynomial and then compute the hash value by evaluating this polynomial.

<h3>Formula</h3>
<p>For a string <code>s</code> of length <code>n</code>, the hash value <code>H</code> is calculated as:</p>
<p>
  <code>
    H(s) = (s<sub>0</sub> &times; p<sup>n-1</sup> + s<sub>1</sub> &times; p<sup>n-2</sup> + &hellip; + s<sub>n-1</sub> &times; p<sup>0</sup>) % m
  </code>
</p>
<p>Where:</p>
<ul>
  <li><code>s<sub>i</sub></code> is the ith character of the string (converted to an integer, often using ASCII values).</li>
  <li><code>p</code> is a prime number (typically a small prime, e.g., 31).</li>
  <li><code>m</code> is a large prime number used to reduce hash collisions.</li>
</ul>

### Example

<p>For the string <code>"abc"</code> with <code>p = 31</code> and <code>m = 101</code>:</p>

- Convert characters: 'a' = 1, 'b' = 2, 'c' = 3
- Compute hash:
  <br>
  <code>
  H("abc") = (1 &times; 31<sup>2</sup> + 2 &times; 31<sup>1</sup> + 3 &times; 31<sup>0</sup>) mod 101
  <br>
  = (961 + 62 + 3) mod 101
  <br>
  = 1026 mod 101
  <br>
  = 17
  </code>

## Division Method

The Division Method is one of the simplest hashing techniques where the hash value is computed by taking the modulus of
the key with a prime number.

<h3>Formula</h3>
<p>For a key <code>k</code> and a hash table size <code>m</code>, the hash value <code>H</code> is calculated as:</p>
<p>
  <code>
    H(k) = k % m
  </code>
</p>
<p>Where:</p>
<ul>
  <li><code>k</code> is the key (or integer value).</li>
  <li><code>m</code> is the size of the hash table (preferably a prime number to reduce collisions).</li>
</ul>

### Example

<p>For a key <code>k = 1234</code> and a hash table size <code>m = 1009</code> (a prime number):</p>

- Compute hash:
  <br>
  <code>
  H(1234) = 1234 mod 1009
  <br>
  = 225
  </code>

## Multiplicative Hashing

Multiplicative Hashing uses a multiplicative factor to compute the hash value. It is based on multiplying the key by a
constant and then taking the modulus of the result.

<h3>Formula</h3>
<p>For a key <code>k</code> and a hash table size <code>m</code>, the hash value <code>H</code> is calculated as:</p>
<p>
  <code>
    H(k) = &lfloor; m &times; (k &times; A % 1) &rfloor;
  </code>
</p>
<p>Where:</p>
<ul>
  <li><code>A</code> is a constant factor (0 &lt; A &lt; 1). A common choice is <code>(√5 - 1) / 2 ≈ 0.6180339887</code>.</li>
  <li><code>&lfloor; x &rfloor;</code> denotes the floor function (rounding down to the nearest integer).</li>
</ul>

### Example

<p>For a key <code>k = 1234</code>, hash table size <code>m = 1009</code>, and <code>A = 0.6180339887</code>:</p>

- Compute hash:
  <br>
  <code>
  H(1234) = &lfloor; 1009 &times; (1234 &times; 0.6180339887 mod 1) &rfloor;
  <br>
  = &lfloor; 1009 &times; 0.780300596 &rfloor;
  <br>
  = &lfloor; 787.8 &rfloor;
  <br>
  = 787
  </code>

# Number System conversion

### 1. **Convert from Binary to Decimal, Octal, and Hexadecimal**

#### (a) Binary to Decimal

- **Explanation**: To convert binary to decimal, multiply each bit by 2 raised to the power of its position, starting
  from 0 on the right.

- **Example**: Convert binary `1011` to decimal.

  ```
  1011 (binary) = 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
               = 8 + 0 + 2 + 1
               = 11 (decimal)
  ```

#### (b) Binary to Octal

- **Explanation**: Group the binary digits into sets of 3 bits from the right, then convert each group into its octal
  equivalent.

- **Example**: Convert binary `101110` to octal.

  ```
  Grouping: 101 110
  101 (binary) = 5 (octal)
  110 (binary) = 6 (octal)
  
  So, 101110 (binary) = 56 (octal)
  ```

#### (c) Binary to Hexadecimal

- **Explanation**: Group the binary digits into sets of 4 bits from the right, then convert each group into its
  hexadecimal equivalent.

- **Example**: Convert binary `10111101` to hexadecimal.

  ```
  Grouping: 1011 1101
  1011 (binary) = B (hex)
  1101 (binary) = D (hex)
  
  So, 10111101 (binary) = BD (hex)
  ```

---

### 2. **Convert from Decimal to Binary, Octal, and Hexadecimal**

#### (a) Decimal to Binary

- **Explanation**: Divide the decimal number by 2, record the remainder, and repeat until the quotient is 0. The binary
  result is the remainders read from bottom to top.

- **Example**: Convert decimal `23` to binary.

  ```
  23 ÷ 2 = 11 remainder 1
  11 ÷ 2 = 5 remainder 1
  5 ÷ 2 = 2 remainder 1
  2 ÷ 2 = 1 remainder 0
  1 ÷ 2 = 0 remainder 1

  So, 23 (decimal) = 10111 (binary)
  ```

#### (b) Decimal to Octal

- **Explanation**: Divide the decimal number by 8, record the remainder, and repeat until the quotient is 0.

- **Example**: Convert decimal `83` to octal.

  ```
  83 ÷ 8 = 10 remainder 3
  10 ÷ 8 = 1 remainder 2
  1 ÷ 8 = 0 remainder 1
  
  So, 83 (decimal) = 123 (octal)
  ```

#### (c) Decimal to Hexadecimal

- **Explanation**: Divide the decimal number by 16, record the remainder, and repeat until the quotient is 0.

- **Example**: Convert decimal `255` to hexadecimal.

  ```
  255 ÷ 16 = 15 remainder 15
  15 ÷ 16 = 0 remainder 15
  
  Since remainder 15 = F in hexadecimal,
  So, 255 (decimal) = FF (hex)
  ```

---

### 3. **Convert from Octal to Binary, Decimal, and Hexadecimal**

#### (a) Octal to Binary

- **Explanation**: Convert each octal digit into its 3-bit binary equivalent.

- **Example**: Convert octal `75` to binary.

  ```
  7 (octal) = 111 (binary)
  5 (octal) = 101 (binary)
  
  So, 75 (octal) = 111101 (binary)
  ```

#### (b) Octal to Decimal

- **Explanation**: Multiply each digit by 8 raised to the power of its position from the right (starting from 0).

- **Example**: Convert octal `342` to decimal.

  ```
  342 (octal) = 3*8^2 + 4*8^1 + 2*8^0
              = 3*64 + 4*8 + 2*1
              = 192 + 32 + 2
              = 226 (decimal)
  ```

#### (c) Octal to Hexadecimal

- **Explanation**: First convert the octal number to binary, then group the binary digits into sets of 4 to convert to
  hexadecimal.

- **Example**: Convert octal `27` to hexadecimal.

  ```
  2 (octal) = 010 (binary)
  7 (octal) = 111 (binary)
  So, 27 (octal) = 010 111 (binary)
  
  Grouping as 4 bits: 0010 1111
  0010 (binary) = 2 (hex)
  1111 (binary) = F (hex)
  
  So, 27 (octal) = 2F (hex)
  ```

---

### 4. **Convert from Hexadecimal to Binary, Decimal, and Octal**

#### (a) Hexadecimal to Binary

- **Explanation**: Convert each hexadecimal digit to its 4-bit binary equivalent.

- **Example**: Convert hexadecimal `2A7` to binary.

  ```
  2 (hex) = 0010 (binary)
  A (hex) = 1010 (binary)
  7 (hex) = 0111 (binary)
  
  So, 2A7 (hex) = 001010100111 (binary)
  ```

#### (b) Hexadecimal to Decimal

- **Explanation**: Multiply each hex digit by 16 raised to the power of its position (starting from 0 from the right).

- **Example**: Convert hexadecimal `3F` to decimal.

  ```
  3F (hex) = 3*16^1 + 15*16^0
           = 3*16 + 15*1
           = 48 + 15
           = 63 (decimal)
  ```

#### (c) Hexadecimal to Octal

- **Explanation**: First convert hexadecimal to binary, then group binary digits in sets of 3 to convert to octal.

- **Example**: Convert hexadecimal `4B` to octal.

  ```
  4 (hex) = 0100 (binary)
  B (hex) = 1011 (binary)
  
  So, 4B (hex) = 0100 1011 (binary)
  
  Group into 3 bits: 000 100 101 011
  000 (binary) = 0 (octal)
  100 (binary) = 4 (octal)
  101 (binary) = 5 (octal)
  011 (binary) = 3 (octal)
  
  So, 4B (hex) = 0453 (octal)
  ```

---