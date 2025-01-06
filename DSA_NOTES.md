
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

- Find the max value, calculate then length
- Count the occurrences
- Store the cumulative count
- From the last take the elements find the right index and place it in the output array

**8. Bucket Sort**

**9. Radix Sort**

**10. Cyclic**

---

### String

**1. Basic String Manipulation or Processing** 

**Examples:**

- [Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character/description/)

  - Store the index of target character in `Treeset`
  - Compute the distance to both the floor and ceiling indices `Math.min(Treeset.ceiling(i) - i,  i - Treeset.floor(i))`

- [Shortest Distance to a Character](https://leetcode.com/problems/top-k-frequent-words/description/?envType=problem-list-v2&envId=bucket-sort)
  
  - Use a `HashMap` to store the frequency of each word.
  - Use a `PriorityQueue` to store the words based on frequency and lexicographical order with custom comparator.
     ```java
      Queue<String>  queue = new PriorityQueue<>((a, b) -> {
        int frequency =  map.get(b) - map.get(a) ;
        if( frequency == 0 ) {
          return a.compareTo(b); // Sort alphabetically in ascending order
        }else{
          return frequency; // Sort by frequency in descending order
        }
      } );
     ```
  
**2. Palindrome Problems** 


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


**1. Classic Binary Search**

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

**Examples:**

- [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/) - Find the minimum number of days required to make m bouquets

  - Apply binary search on the range of days, then validate if it is possible to make m bouquets in `mid` days.
  - Only adjacent flowers can be used to make a bouquet, so reset the count of flowers when `bloomDay > day`

- [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) - Minimize the speed k at which Koko eats bananas to finish in time.

  - Consider the range like max value in array, then apply `modified binary search on the range(1...N)`
  - Validate the condition, if it is possible to eat all bananas in `mid` speed(k).

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

**Examples:**
- [Find Peak Element](https://leetcode.com/problems/find-peak-element/) – Find a local maximum in the array.
  - `if (arr[mid] < arr[mid+1])`
- [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) – Find the peak in a "mountain array."
  - `if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])`
- [Find a Peak Element II](https://leetcode.com/problems/find-a-peak-element-ii/) – Find a peak in a 2D grid.


**5. Searching in Multi-Dimensional Arrays**
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
### Stack

**1. Stack Simulation**

- [Decode String](https://leetcode.com/problems/decode-string/) -  Use a stack to decode nested encoded strings (e.g., `"3[a2[c]]"` becomes `"accaccacc"`).

  - Use two stacks, one for numbers and one for strings.
  - when digit is encountered, push into number stack
  - When an open bracket is encountered, initialize a string variable
  - When character is encountered, append to the string variable
  - when close bracket is encountered, pop the number and repeat the string that many times.

- [Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) -  Use a stack to remove adjacent duplicates in a string when they occur \( k \) times consecutively.

- [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) - Simulate collisions between asteroids using stack mechanics.
  
  - Push and pop into stack based on conditions
  
- [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) - Simulate the removal of digits to achieve the smallest possible number using a monotonic stack.

  - Push and pop into stack based on conditions

- [Car Fleet](https://leetcode.com/problems/car-fleet/) - Simulate car fleets merging using a stack based on their speeds and positions. 



**2. Valid Parentheses and Expressions Evaluation**

- **Infix Expression**: The operators are written between the operands. Example: `A + B, (A + B) * C`.  
  **How to Solve**: Follow operator precedence and evaluate step by step.

- **Postfix Expression (Reverse Polish Notation)**: The operators are written after the operands. Example: `AB+, AB+C*`.  
  **How to Solve**: Use a stack, push operands, and apply operators in left-to-right order.

- **Prefix Expression (Polish Notation)**: The operators are written before the operands. Example: `+AB, *+ABC`.  
  **How to Solve**: Use a stack, push operands, and apply operators in right-to-left order.

**Examples:**
- [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Check if parentheses are balanced in a string.

- [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) - Count the minimum additions required to make a string of parentheses valid.

- [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Evaluate a postfix expression using a stack.

- [Basic Calculator](https://leetcode.com/problems/basic-calculator/) - Handle parentheses and operators in infix expressions.

- [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) - Evaluate an infix arithmetic expression.
  

**3. Monotonic Stack Problems**

```java

    // Increasing Stack (from bottom to top)
    int[] findPreviousSmallest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    // Increasing Stack (from bottom to top)
    int[] findNextSmallest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    // Decreasing Stack (from bottom to top)
    int[] findPreviousLargest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

   // Decreasing Stack (from bottom to top)
    int[] findNextLargest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

```

**Examples:**
- [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Find the number of days until a warmer temperature.

- [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) - Find the next greater element for each element in an array.

- [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Find the largest rectangular area in a histogram using a monotonic stack.


---

### Sliding Window

### **Examples:**

**1. Variable Window Size**

- [Maximum Points From Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) - Pick cards from either the beginning or the end to maximize the total points.

- [Permutations in String](https://leetcode.com/problems/permutation-in-string/) - Check if `s2` contains any permutation of `s1`.


**1. Longest/Shortest Subarrays**

- [Longest Subarray K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/) - Find the longest subarray with exactly `k` distinct elements.

- [Max Consecutive 1s III](https://leetcode.com/problems/max-consecutive-ones-iii/) - Find the maximum number of consecutive 1's in a binary array after flipping at most `k` 0's to 1's.


**2. Longest/Shortest Substrings**

- [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) - Given a string, replace up to `k` characters to find the longest substring with the same character.

- [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Find the smallest substring in `s` that contains all characters from `t`.

**3. Number of Subarrays**

- [Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/description/) - Find the number of subarrays where the number of odd integers is exactly `k`.

- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Find the maximum value in each sliding window of size `k`.


**4. Number of Substrings**

- [Substrings Containing 3 Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/) - Find the number of substrings that contain exactly 3 distinct characters.

- [Subarrays K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) - Find the number of subarrays with exactly `k` different integers.


---
### Greedy

**1. Interval Scheduling**


**Examples:**

- [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Merge overlapping intervals.

- [Insert Interval](https://leetcode.com/problems/insert-interval/) - Insert a new interval into a list of non-overlapping intervals.

- [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Minimize the number of intervals to remove to make the remaining intervals non-overlapping.

- [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) - Find the minimum arrows needed to burst all balloons based on overlapping intervals.

- [Meeting Rooms I](https://leetcode.com/problems/meeting-rooms/) - Find the minimum number of meeting rooms required.

- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Find the minimum number of meeting rooms required.


**2. Scheduling Problems**

- Scheduling a task optimally with with gap of n intervals.

**Examples:**
- [Task Scheduler](https://leetcode.com/problems/task-scheduler/) - Greedily assign tasks while considering cooldown periods.
  - Formula : `minimumIntervals = (maxFreq − 1 ) × (n + 1 ) + maxCount`, where  maxFreq = max frequency of task, maxCount = number of tasks with max frequency, n = cooldown period. 
  - Can be done with priority queue

**3. Greedy for Arrays**

**Examples:**
- [Partition Labels](https://leetcode.com/problems/partition-labels/) - Partition a string into as many parts as possible such that each letter appears in only one part.

- [Candy](https://leetcode.com/problems/candy/) - Distribute candies to children such that each child has at least one candy and children with higher ratings get more candies.

- [Jump Game II](https://leetcode.com/problems/jump-game-ii/) - Minimize the number of jumps needed to reach the last index.

- [Jump Game](https://leetcode.com/problems/jump-game/) - Determine if you can reach the last index by making greedy jumps.

- [Frog Jump](https://leetcode.com/problems/frog-jump/) - Find if a frog can reach the final stone.

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