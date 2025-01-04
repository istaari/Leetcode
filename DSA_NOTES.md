
# Algorithms and Data Structures

### Sorting

**1. Bubble Sort**

> Compare adjacent elements and swap them if they are in the wrong order.
> In a single pass, the smallest or largest element reaches at the end of the array, its in sorted order.

**2. Selection Sort**

> Find the smallest or largest element in array and swap it with the first element. Which becomes sorted.

**3. Insertion Sort**

> Pick an element(2nd element) and compare it with the previous element, if it is smaller than the previous element, 
> then move the prev element to the next position, at last insert the picked element at the correct position.

[Leetcode Insertion Sort Linked List](https://leetcode.com/problems/insertion-sort-list/description/)

**4. Merge Sort**

> - Recursively Divide the array until it has only one element.
> - Then in the merge step, first create two array from left to mid and mid+1 to right.
> - Copy from original array to these two arrays.
> - Then merge these two arrays in sorted order.

[Leetcode Sort Linked List](https://leetcode.com/problems/sort-list/description/)

**5. Quick Sort**

> - Select a pivot element, either first, last or random element.
> - then partition the array such that all elements less than pivot are on left and greater are on right.
> - The pivot element is at its sorted position.
> - Recursively apply the same steps to the left and right subarrays.

**6. Heap Sort**


**7. Counting Sort**


**8. Bucket Sort**


**9. Radix Sort**

---

### String

**1. Basic String Manipulation or Processing** : Problems involve operations such as reversing, concatenating, or modifying strings.

[Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character/description/)

> Store the index of target character in `Treeset`, then for each character find the floor and ceil value of the current index.
> Calculate min distace of current character till target  `Math.min(treeSet.ceiling(i) - i,  i - treeSet.floor(i))`


**2. Substring and Subsequence Problems** : These involve finding or working with substrings or subsequences, `can be solved using sliding window or DP`.


**3. Frequency Analysis** : These problems involve analyzing character frequencies, like Anagrams, Anagrams Groups, character count, etc. `can be solved using Hashmap`.


**4. Palindrome Problems** : These involve checking or forming palindromes. can be solved using two-pointer technique.


**5. Two-Pointer/String Traversal Problems** : These involve using two pointers or iterating over the string efficiently.


**6. String Compression and Encoding** : Problems involve compressing or encoding strings.


**7. Greedy/String Optimization** : Problems solved by making optimal local decisions.


**8. Character Rearrangement** : These involve rearranging characters to meet certain conditions.


**9. Nested String Parsing** : Problems involving parsing nested strings or brackets.


**10 Pattern Matching and Regular Expressions** : These problems often involve checking or generating patterns.

---

### Array




---
### Binary Search




---
### Linked List




---
### Stack and Queue



---
### Sliding Window



---
### Greedy


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


**AVL Tree**  


**Red-Black Tree**  



**Segment Tree**  


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

</br>

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