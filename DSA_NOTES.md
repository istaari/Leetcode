
# Algorithms and Data Structures

## Sorting

**1. Bubble Sort**

- Compare adjacent elements and swap them if they are in the wrong order.
-  In a single pass, the smallest or largest element reaches at the end of the array, its in sorted order.

**2. Selection Sort**

- Find the smallest or largest element in array and swap it with the first element. Which becomes sorted.

**3. Insertion Sort**

- Pick an element(2nd element) and compare it with the previous element, if it is smaller than the previous element, 
- then move the prev element to the next position, at last insert the picked element at the correct position.

**Examples:**

- [Leetcode Insertion Sort Linked List](https://leetcode.com/problems/insertion-sort-list/description/)

**4. Merge Sort**

- Recursively Divide the array until it has only one element.
- Then in the merge step, first create two array from left to mid and mid+1 to right.
- Copy from original array to these two arrays.
- Then merge these two arrays in sorted order.

**Examples:**

- [Leetcode Sort Linked List](https://leetcode.com/problems/sort-list/description/)

**5. Quick Sort**

- Select a pivot element, either first, last or random element.
- then partition the array such that all elements less than pivot are on left and greater are on right.
- The pivot element is at its sorted position.
- Recursively apply the same steps to the left and right subarrays.

**6. Heap Sort**


**7. Counting Sort**


**8. Bucket Sort**


**9. Radix Sort**

---

### String

**1. Basic String Manipulation or Processing** 

**Examples:**

- [Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character/description/)

  - Store the index of target character in `Treeset`
  - Compute the distance to both the floor and ceiling indices `Math.min(Treeset.ceiling(i) - i,  i - Treeset.floor(i))`
  
**2.Substring and Subsequence Problems** 

**3. Frequency Analysis** 

**4. Palindrome Problems** 

**5. Two-Pointer/String Traversal Problems** 

**6. String Compression and Encoding** 

**7. Greedy/String Optimization** 

**8. Character Rearrangement** 

**9. Nested String Parsing** 

**10. Pattern Matching and Regular Expressions** 


---

### Array

**1. Subarrays** 

- Total Subarrays = `n * (n + 1) / 2`

- ```java
    int sumOfAllSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int contribution = (i + 1) * (n - i); // How many times arr[i] will appear in subarrays
            sum += arr[i] * contribution; // Add arr[i] * contribution to sum
        }

        return sum;
    }
   ```

 - ```java
    int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int contribution = ((i + 1) * (n - i) + 1) / 2;
            sum += arr[i] * contribution;
        }
        
        return sum;  
    }
   ``` 



---
### Binary Search

 - `low + (high - low) / 2`  - Selects lower middle, if there are even elments 
 - `low + high / 2`  - Selects lower middle, if there are even elments 
 - `low + (high - low + 1) / 2`  - Selects upper middle, if there are even elments 
 -  In Modified Binary Bearch, `The loop exits when low = high`

**1. Classic Binary Search**
   - **Key Idea:** Check the middle element, adjust search bounds (left or right), repeat.
  
   **Examples:**

   - [Find First and Last Occurences of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
   
      - When target is found for first occurrence, move `right = mid-1`
      - When target is found for last occurrence, move `left = mid+1`


   - Floor and Ceil of a number in a sorted array.

      - If `target > mid`, then ` floor = mid and low = mid + 1`
      - If `target < mid`, then ` ceil = mid and high = mid - 1`
  
   - [Search Insert Position](https://leetcode.com/problems/search-insert-position/)

      - ```java 
          int searchInsert(int[] nums, int target) {
              int low = 0;
              int high = nums.length; // Full Length

              while (low < high) {
                  int mid = low + (high - low) / 2;
                  if (target <= nums[mid]) {
                      high = mid;
                  } else {
                      low = mid + 1;
                  }
              }
              
              return low;
          }
        ```


**2. Binary Search on the Result**
   - **Key Idea:** Apply binary search to a range of possible answers (not the array), then validate a condition for each midpoint.

   **Examples:**

   - [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/split-array-largest-sum/) - Find the minimum number of days required to make m bouquets

   - [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) - Minimize the speed at which Koko eats bananas to finish in time.

   - [Capacity to Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) - Minimize the capacity needed to ship packages in a given time.


**3. Rotated Sorted Arrays**
   - **Key Idea:** Identify the rotated section, then apply binary search in the appropriate part.
   - Use Modified Binary Search, find smallest or pivot `if(nums[mid] <= nums[high])`, then apply Binary Search on left or right part.
   - Normal Binary Search `mid = (low + high) / 2 , realMidValue = (mid + rotationPoint) % n`

   **Examples:**

   - [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) 

   - [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) 
   
   - [Find Minimum in Rotated Sorted Array Contains Duplicates ](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)

      - ```java 
          int findMin(int[] nums) {
              int low = 0;
              int high = nums.length - 1;

              while (low < high) {
                  int mid = low + (high - low) / 2;
                  if (nums[mid] < nums[high]) {
                      high = mid;
                  } else if (nums[mid] > nums[high]) {
                      low = mid + 1;
                  } else { // nums[mid] == nums[hi])

                      if (nums[high - 1] > nums[high]) {
                          low = high; // low is Pivot index or min element
                          break;
                      }
                      high--;
                  }
              }
              return nums[low];
          } 
        ```


**4. Searching in Monotonic Functions**
   - **What:** Use binary search on monotonic functions to find peaks, valleys, or thresholds.
   - **Key Idea:** Exploit the monotonic property of the function (increasing, decreasing, or peaks).
   
   **Examples:**
   - [Find Peak Element](https://leetcode.com/problems/find-peak-element/) – Find a local maximum in the array.

      - `if (arr[mid] < arr[mid+1])`

   - [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) – Find the peak in a "mountain array."

      - `if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])`

   - [Find a Peak Element II](https://leetcode.com/problems/find-a-peak-element-ii/) – Find a peak in a 2D grid.


**5. Searching in Multi-Dimensional Arrays**
   - **What:** Search for a target in a matrix (2D array).
   - **Key Idea:** Treat the matrix as a sorted structure and adapt binary search.
   - **Variants:**
     - Treat the matrix as a flat array `matrix[mid / n][mid % n]`, where n is column length
     - Exploit row/column properties.

   **Examples:**
   - [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) – Search in a matrix where rows and columns are sorted.

   - [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) – Search in a matrix with sorted rows and columns.
  
---
### Linked List



---
### Stack and Queue


### **1. Valid Parentheses and Expressions (Stack)**
   - **Key Idea:** Use a stack to match opening and closing characters or to evaluate expressions.

   **Examples:**
   - [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Check if parentheses are balanced in a string.
   - [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) - Count the minimum additions required to make a string of parentheses valid.
   - [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Evaluate a postfix expression using a stack.

---

### **2. Monotonic Stack Problems**
   - **Key Idea:** Use a stack to maintain a monotonic increasing or decreasing order for elements to solve range-based problems.

   **Examples:**
   - [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Find the number of days until a warmer temperature.
   - [Next Greater Element I/II](https://leetcode.com/problems/next-greater-element-i/) - Find the next greater element for each element in an array.
   - [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Find the largest rectangular area in a histogram using a monotonic stack.

---

### **3. Backtracking with Stack**
   - **Key Idea:** Use a stack to manage backtracking steps when solving recursive problems.

   **Examples:**
   - [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) - Generate all combinations of valid parentheses.
   - [Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/) - Perform a preorder traversal using a stack.
   - [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) - Generate all possible letter combinations for a phone number using backtracking.

---

### **4. Queue for BFS (Breadth-First Search)**
   - **Key Idea:** Use a queue to implement BFS for tree, graph, or grid traversal.

   **Examples:**
   - [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) - Traverse a binary tree level by level.
   - [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) - Use BFS to find the minimum time for all oranges to rot.
   - [Word Ladder](https://leetcode.com/problems/word-ladder/) - Use BFS to find the shortest transformation sequence between words.

---

### **5. Sliding Window Maximum (Deque/Queue)**
   - **Key Idea:** Use a deque to efficiently find the maximum in every sliding window of size \( k \).

   **Examples:**
   - [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Use a deque to maintain the maximum in a sliding window.
   - [First Negative Integer in Every Window of Size K](https://leetcode.com/problems/first-negative-integer-in-every-window-of-size-k/) - Find the first negative number in every sliding window of size \( k \).
   - [Sum of Minimums of All Subarrays](https://leetcode.com/problems/sum-of-subarray-minimums/) - Use a deque to find the sum of minimums in all subarrays.

---

### **6. Implementing Data Structures (Stack/Queue)**
   - **Key Idea:** Build or simulate stacks and queues using arrays or other data structures.

   **Examples:**
   - [Implement Stack Using Queues](https://leetcode.com/problems/implement-stack-using-queues/) - Simulate a stack using two queues.
   - [Implement Queue Using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) - Simulate a queue using two stacks.
   - [Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Design a deque with circular array implementation.

---

### **7. Topological Sorting (Queue/Stack)**
   - **Key Idea:** Use a stack or queue to perform topological sorting in a Directed Acyclic Graph (DAG).

   **Examples:**
   - [Course Schedule](https://leetcode.com/problems/course-schedule/) - Determine if all courses can be completed using topological sorting.
   - [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) - Find the order of characters in an alien language using topological sorting.
   - [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Return the order of courses to be taken using BFS or DFS.

---

### **8. Two Stacks for Min/Max Tracking**
   - **Key Idea:** Use an auxiliary stack to track the minimum or maximum element in constant time.

   **Examples:**
   - [Min Stack](https://leetcode.com/problems/min-stack/) - Implement a stack that supports retrieving the minimum element in constant time.
   - [Max Stack](https://leetcode.com/problems/max-stack/) - Implement a stack that supports retrieving the maximum element in constant time.
   - [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Use a stack to calculate the largest rectangle in a histogram.

---

### **9. Priority Queue for Greedy Problems**
   - **Key Idea:** Use a priority queue (min-heap or max-heap) to efficiently retrieve the smallest or largest element when needed.

   **Examples:**
   - [Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Use a min-heap to merge \( k \) sorted linked lists.
   - [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) - Use two heaps to maintain the median in a dynamic data stream.
   - [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Use a priority queue to retrieve the \( k \) most frequent elements.

---

### **10. Stack for DFS (Depth-First Search)**
   - **Key Idea:** Use a stack to implement an iterative depth-first search.

   **Examples:**
   - [Path Sum II](https://leetcode.com/problems/path-sum-ii/) - Use DFS to find all root-to-leaf paths with a given sum.
   - [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/) - Perform an inorder traversal using a stack.
   - [Number of Islands](https://leetcode.com/problems/number-of-islands/) - Use a stack to perform DFS on a grid to count islands.

---

### **11. Queue for Simulation Problems**
   - **Key Idea:** Use a queue to simulate real-world processes like task scheduling or round-robin processing.

   **Examples:**
   - [Task Scheduler](https://leetcode.com/problems/task-scheduler/) - Use a queue to simulate task scheduling with cooldowns.
   - [Design Hit Counter](https://leetcode.com/problems/design-hit-counter/) - Use a queue to count hits within a rolling time window.
   - [The Maze](https://leetcode.com/problems/the-maze/) - Simulate movements in a grid using BFS with a queue.

---

### **12. Stack for Expression Evaluation**
   - **Key Idea:** Use a stack to evaluate postfix, prefix, or infix expressions.

   **Examples:**
   - [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) - Evaluate an infix arithmetic expression.
   - [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Evaluate a postfix expression using a stack.
   - [Basic Calculator](https://leetcode.com/problems/basic-calculator/) - Handle parentheses and operators in infix expressions.

---
### Sliding Window

### **1. Maximum/Minimum of Subarrays (Fixed Size)**
   - **Key Idea:** Use a sliding window of fixed size \( k \) to calculate the maximum, minimum, or sum for every subarray.

   **Examples:**
   - [Maximum Sum of Subarray of Size K](https://leetcode.com/problems/maximum-sum-of-subarray-of-size-k/) - Find the maximum sum of a subarray of size \( k \).
   - [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Find the maximum value in each subarray of size \( k \).
   - [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Find the smallest substring covering all characters of another string.

---

### **2. Dynamic Sliding Window (Variable Size)**
   - **Key Idea:** Dynamically adjust the size of the window based on problem constraints, expanding when conditions are not met and contracting when they are.

   **Examples:**
   - [Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) - Find the longest substring containing at most \( k \) distinct characters.
   - [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - Find the smallest subarray with a sum greater than or equal to \( s \).
   - [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) - Count the subarrays containing exactly \( k \) distinct integers.

---

### **3. Substring/Pattern Matching**
   - **Key Idea:** Use a sliding window to match substrings based on specific character frequencies or patterns.

   **Examples:**
   - [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) - Find the starting indices of all anagrams of a given string.
   - [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - Find the length of the longest substring without repeating characters.
   - [Permutations in String](https://leetcode.com/problems/permutation-in-string/) - Check if a string contains a permutation of another string.

---

### **4. Longest/Shortest Subarrays with Conditions**
   - **Key Idea:** Adjust the window size dynamically to find the longest or shortest subarray that satisfies a condition.

   **Examples:**
   - [Longest Subarray of Ones After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/) - Find the longest subarray of 1's after deleting at most one element.
   - [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) - Find the longest subarray with at most \( k \) 0's.
   - [Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/) - Find the longest substring containing at most two distinct characters.

---

### **5. Sliding Window with Two Pointers**
   - **Key Idea:** Maintain two pointers (left and right) to define the window boundaries and expand/contract them to meet conditions.

   **Examples:**
   - [Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) - Find the longest subarray satisfying an absolute difference condition.
   - [Maximum Size Subarray Sum Equals K](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/) - Find the longest subarray with a sum equal to \( k \).
   - [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Find the longest subarray containing at most two types of fruits.

---

### **6. Prefix Sum with Sliding Window**
   - **Key Idea:** Maintain a prefix sum or running sum to efficiently calculate the sum of elements in the current window.

   **Examples:**
   - [Maximum Sum of Subarray of Size K](https://leetcode.com/problems/maximum-sum-of-subarray-of-size-k/) - Use prefix sums to efficiently calculate subarray sums of size \( k \).
   - [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) - Count the number of subarrays with a sum equal to \( k \).
   - [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/) - Check if the array contains a subarray whose sum is a multiple of \( k \).

---

### **7. Counting and Frequency Problems**
   - **Key Idea:** Use a hashmap or frequency counter to track the count of elements or characters within the current window.

   **Examples:**
   - [Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) - Use a hashmap to count distinct characters in the window.
   - [Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) - Find the length of the longest substring that can be made uniform with at most \( k \) replacements.
   - [Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/) - Count substrings that contain all three characters (a, b, c).

---

### **8. Subarray/Substring Matching**
   - **Key Idea:** Adjust the window size to find subarrays or substrings that match specific conditions or patterns.

   **Examples:**
   - [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Find the smallest substring containing all characters of a given string.
   - [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) - Find the shortest subarray whose sum is at least \( k \).
   - [Longest Subarray With Sum Divisible by K](https://leetcode.com/problems/longest-subarray-with-sum-divisible-by-k/) - Find the longest subarray whose sum is divisible by \( k \).


---
### Greedy

Here’s a structured categorization of **LeetCode problems involving Greedy algorithms**:

---

### **1. Interval Scheduling**
   - **Key Idea:** Sort intervals by their start or end times, then iteratively select non-overlapping intervals based on a greedy strategy.

   **Examples:**
   - [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Minimize the number of intervals to remove to make the remaining intervals non-overlapping.
   - [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Find the minimum number of meeting rooms required.
   - [Erase Overlap Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Same as Non-overlapping Intervals but focuses on removal count.

---

### **2. Scheduling Problems**
   - **Key Idea:** Use a greedy strategy to maximize or minimize results while considering constraints like deadlines or weights.

   **Examples:**
   - [Task Scheduler](https://leetcode.com/problems/task-scheduler/) - Greedily assign tasks while considering cooldown periods.
   - [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) - Find the minimum arrows needed to burst all balloons based on overlapping intervals.
   - [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) - Maximize profit from non-overlapping jobs using greedy and binary search.

---

### **3. Greedy for Coins and Change**
   - **Key Idea:** Iteratively select the largest denominations or largest values to achieve the target.

   **Examples:**
   - [Minimum Number of Coins](https://leetcode.com/problems/coin-change/) - Find the minimum number of coins to make up a given amount.
   - [Lemonade Change](https://leetcode.com/problems/lemonade-change/) - Determine if you can provide the correct change for each lemonade sold.
   - [Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/) - Find the minimum number of operations to reduce \( X \) by removing elements.

---

### **4. Greedy for Arrays**
   - **Key Idea:** Make greedy choices based on array properties like sorting or element values.

   **Examples:**
   - [Partition Labels](https://leetcode.com/problems/partition-labels/) - Partition a string into as many parts as possible such that each letter appears in only one part.
   - [Candy](https://leetcode.com/problems/candy/) - Distribute candies to children such that each child has at least one candy and children with higher ratings get more candies.
   - [Jump Game II](https://leetcode.com/problems/jump-game-ii/) - Minimize the number of jumps needed to reach the last index.

---

### **5. Greedy for Strings**
   - **Key Idea:** Make greedy choices to construct substrings or reduce operations.

   **Examples:**
   - [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) - Remove \( k \) digits to form the smallest possible number.
   - [Greedy Algorithm for Lexicographical Order](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) - Find the smallest subsequence of distinct characters in lexicographical order.
   - [Reorganize String](https://leetcode.com/problems/reorganize-string/) - Rearrange characters so that no two adjacent characters are the same.

---

### **6. Greedy for Subsequence Problems**
   - **Key Idea:** Select subsequences to maximize or minimize values or satisfy conditions.

   **Examples:**
   - [Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/) - Determine if a triplet exists in increasing order.
   - [Is Subsequence](https://leetcode.com/problems/is-subsequence/) - Check if a string is a subsequence of another string.
   - [Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/) - Find the longest subsequence that forms a palindrome.

---

### **7. Divide and Conquer + Greedy**
   - **Key Idea:** Combine greedy strategies with divide-and-conquer approaches for optimized solutions.

   **Examples:**
   - [Divide Chocolate](https://leetcode.com/problems/divide-chocolate/) - Maximize the minimum sweetness of chocolate pieces divided among friends.
   - [Split Array into Consecutive Subsequences](https://leetcode.com/problems/split-array-into-consecutive-subsequences/) - Split an array into consecutive subsequences of length \( \geq 3 \).
   - [Find the Minimum Number of Fibonacci Numbers](https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/) - Use Fibonacci numbers greedily to sum up to \( k \).

---

### **8. Greedy for Graph Problems**
   - **Key Idea:** Use greedy choices to optimize paths or matchings in graphs.

   **Examples:**
   - [Prim's Algorithm](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Find the minimum cost to connect all points in a graph.
   - [Kruskal's Algorithm](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) - Connect cities with the minimum cost using a greedy MST approach.
   - [Dijkstra's Algorithm](https://leetcode.com/problems/path-with-maximum-probability/) - Use a greedy approach to find the shortest or most probable path.

---

### **9. Greedy for Sorting and Rearrangement**
   - **Key Idea:** Rearrange elements in a greedy manner to maximize or minimize results.

   **Examples:**
   - [Largest Number](https://leetcode.com/problems/largest-number/) - Arrange numbers to form the largest possible number.
   - [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/) - Determine if a person can attend all meetings by sorting intervals.
   - [Rearrange Array Elements by Sign](https://leetcode.com/problems/rearrange-array-elements-by-sign/) - Rearrange positive and negative elements alternately.

---

### **10. Greedy for Path Problems**
   - **Key Idea:** Choose optimal steps to minimize or maximize the result.

   **Examples:**
   - [Jump Game](https://leetcode.com/problems/jump-game/) - Determine if you can reach the last index by making greedy jumps.
   - [Frog Jump](https://leetcode.com/problems/frog-jump/) - Find if a frog can reach the final stone.
   - [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) - Use a greedy strategy to minimize the effort required to traverse a path.

---
### Trees

**Binary Tree Traversal (In-order, Pre-order, Post-order, Level Order)**  


**BST Operations**  


**Depth/Height** 


**Path problems** 


**Comparison on Two Trees**


**Counting nodes in Tree**


**Ancestor**


**Top, Bottom, Right, Left, Vertical & Diagonal view of tree**


**Tree construction** 


**Serialize and Deserialize**


**B and B+ Tree**  


**AVL Tree**  


**Red-Black Tree**  


**Segment Tree**  


---
### Heap




---
### Backtracking





---
### Dynamic Programming

 **1D DP** 


 **Grids(Path Problem)**
 

 **Subsequences(Kanpsack, Subset, Coin Change, Partition)** 


 **String(Subsequence, Substring, Edit Distance)** 


 **Longest Increasing Subsequence** 


 **Stock Optimizations**  


 **Matrix Chain Multiplication**  


 **Graph DP**   


 **Bitmasking + DP**   


---
### Graph


### **Traversal & Basic Operations**

**Depth-First Search (DFS)**  

**Breadth-First Search (BFS)** 

**Number of Connected Components** 

**Shortest Path - BFS**  

**Grid-Based Problems** (e.g., shortest path in a grid, number of islands).  


### **Cycle Detection**

**Detect Cycle in an Undirected Graph**  

**Detect Cycle in a Directed Graph**  

**Detect Negative Weight Cycle** (using Bellman-Ford).  


### **Topological Sort & Directed Acyclic Graphs (DAG)**

**Topological Sort**  

**Kahn’s Algorithm** 

**Longest Path in a DAG**  


### **Minimum Spanning Tree (MST)**

**Kruskal's Algorithm**  

**Prim's Algorithm** 

**Borůvka's Algorithm** (another MST algorithm).  


### **Shortest Path Algorithms**

**Dijkstra's Algorithm** 

**Bellman-Ford Algorithm** 

**Floyd-Warshall Algorithm** 

**Shortest Path in Weighted DAG** 


###  **Graph Coloring**

**M-Coloring Problem** (Can the graph be colored with M colors?). 

**Check Bipartite Graph** (using graph coloring).  

**Chromatic Number** (Minimum number of colors to color the graph).  


### **Connectivity and Bridges**

**Articulation Points** (Nodes whose removal increases connected components).  

**Bridges in a Graph** (Edges whose removal increases connected components).  

**Strongly Connected Components (SCC)** (Kosaraju, Tarjan’s algorithms).  

**2-Edge Connected Components**.  


### **Flow and Matching Problems**

**Ford-Fulkerson Algorithm** (Maximum Flow).  

**Edmonds-Karp Algorithm** (Optimized Maximum Flow). 

**Dinic’s Algorithm** (Efficient Maximum Flow).  

**Bipartite Graph Check** (using DFS/BFS).  

**Maximum Bipartite Matching** (Hungarian Algorithm).  

---
### Trie




---
### Maths

**1. Sieve of Eratosthenes**

**2. Euclidean Algorithm for GCD**

**3. Fast Exponentiation**

**4. Prime Factorization**

**5. Modular Arithmetic**

---

### Bit Manipulation

**Bitwise effects on numbers**

```java

/*
 * Left Shift (<<) :
 * The left shift operator (<<) shifts the bits of a number to the left by a specified number of positions.
 * Each bit is shifted to the left by the specified amount, and zeros are added to the right.
 */
int x = 5; // Binary representation: 000...0000101
int y = x << 2; // Shift left by 2 positions : 000...0010100

// Multiplication by powers of 2
/*  5 * 2^2 = 20 **/
System.out.println(y); // Output: 20

/*
 * Right Shift (>>):
 * The right shift operator (>>) shifts the bits of a number to the right by a specified number of positions.
 * Each bit is shifted to the right by the specified amount.
 */

int a = 20; // Binary representation: 000...0010100
int b = a >> 2; // Shift right by 2 positions : 000...0000101

// Division by powers of 2
/*  20 / 2^2 = 5 **/
System.out.println(b); // Output: 5

```

**Toggle a Specific Bit**

`It means doing NOT operation at a specific bit position(using XOR ^)`

```java

// toggleBit(5, 1):
// 5 in binary: 0101
// 1 << 1 (shift left by 1): 0010
// 5 ^ 2: 0101 ^ 0010 = 0111 (result is 7)

public int toggleBit(int number, int bitPosition) {
    return number ^ (1 << bitPosition);
}


```

**Set a Specific Bit**

`It means setting 1 at a specific bit position (using OR | )`

```java

// setBit(5, 2):
// 5 in binary: 0101
// 1 << 2 (shift left by 2): 0100
// 5 | 4: 0101 | 0100 = 0101 (result is 7)

public int setBit(int number, int bitPosition) {
    return number | (1 << bitPosition);
}

```

**clear a Specific Bit**

`It means setting 0 at a specific bit position (using AND and Complement )`

```java

// clearBit(7, 1):
// 7 in binary: 0111
// ~(1 << 1) (shift left by 1 and complement): ~0010 = 1101
// 7 & 1101: 0111 & 1101 = 0101 (result is 5)

public int clearBit(int number, int bitPosition) {
    return number & ~(1 << bitPosition);
}

```

**Count the Number of Set Bits (Hamming Weight)**

```java

// countSetBits(7):
// 7 in binary: 0111
// Number of 1's: 3

public int countSetBits(int number) {
    int count = 0;
    while (number != 0) {
        count += (number & 1);
        number >>= 1;
    }
    return count;
}

```

**Find the Position of the Rightmost Set Bit**

```java

// findRightmostSetBit(12):
// 12 in binary: 1100
// -12 in binary (two's complement): 0011 (inverting 1100) + 1 = 0100
// 12 & -12: 1100 & 0100 = 0100 (result is 4)

public int findRightmostSetBit(int number) {
    return number & -number;
}

```

**Check if a Number is a Power of Two**

```java

// isPowerOfTwo(4):
// 4 in binary: 0100
// 4 - 1 = 3 (binary: 0011)
// 4 & 3: 0100 & 0011 = 0000 (result is 0, so 4 is a power of two)

public boolean isPowerOfTwo(int number) {
    return (number > 0) && ((number & (number - 1)) == 0);
}

```

**Swap Two Numbers Without Using a Temporary Variable**

```java

// arr = [3, 5]; swap(arr, 0, 1);
// Before swap: [3, 5]
// 3 ^ 5 = 6 (0110)
// 5 ^ 6 = 3
// 6 ^ 3 = 5
// After swap: [5, 3]

public void swap(int[] arr, int i, int j) {
    if (i != j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}

```

**Reverse Bits of an Integer**

```java

// reverseBits(5):
// 5 in binary: 00000101
// Reversed: 10100000 (result is 160)

public int reverseBits(int number) {
    int result = 0;
    while (number > 0) {
        result = (result << 1) | (number & 1);
        number >>= 1;
    }
    return result;
}


```

**Determine if Two Integers Have Opposite Signs**

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

### Matrix

**Basic Directions (left, right, top, down)**

- `{0, 1}` : Represents movement to the right
- `{0, -1}`: Represents movement to the left
- `{1, 0}` : Represents movement downwards
- `{-1, 0}`: Represents movement upwards

**Additional diagonal movements**

- `{1, 1}` : Represents movement diagonally down and to the right
- `{1, -1}` : Represents movement diagonally down and to the left
- `{-1, 1}` : Represents movement diagonally up and to the right
- `{-1, -1}`: Represents movement diagonally up and to the left

**Matrix Conversion**

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

**Grid Number in matrix**

gridNumber = `(row / 3) * 3 + (col / 3)`


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