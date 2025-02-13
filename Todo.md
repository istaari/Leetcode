## **DSA Todos**

### **Advanced Data Structures**

- [ ] **B and B+ Trees**
- [ ] **AVL Tree**
- [ ] **Red Black Trees**
- [ ] **Segment Trees**
- [ ] **KMP, Rabin Karp, Z Function**
- [ ] **Fenwick Tree (Binary Indexed Tree - BIT)**
- [ ] **Persistent Data Structures**
- [ ] **Treap (Tree + Heap)**
- [ ] **Heavy-Light Decomposition (HLD)**
- [ ] **Splay Tree (Self-adjusting BST)**
- [ ] **Skip List**
- [ ] **KD-Tree (K-Dimensional Tree)**
- [ ] **Suffix Array + LCP Array**


| **Algorithm/Data Structure**        | **Problem It Solves**                                                               | **Core Idea**                                                                           |
|-------------------------------------|-------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| **B-Trees & B+ Trees**              | Fast searching, insertion, and deletion in large datasets (databases, filesystems). | Multi-way search tree that minimizes disk accesses by keeping nodes with multiple keys. |
| **AVL Tree**                        | Keeps a BST balanced for efficient lookup, insertion, and deletion.                 | Self-balancing BST using height differences (balance factor).                           |
| **Red-Black Tree**                  | Balances a BST while allowing efficient insertions and deletions.                   | Maintains balance using red/black node properties and rotations.                        |
| **Segment Tree**                    | Efficient range queries (sum, min, max, GCD) with updates.                          | Binary tree structure where each node represents a segment of an array.                 |
| **KMP Algorithm**                   | Fast substring search in a text.                                                    | Precomputes longest prefix-suffix (LPS) array to skip redundant comparisons.            |
| **Rabin-Karp Algorithm**            | Efficient pattern matching using hashing.                                           | Uses rolling hash to compare substring hashes instead of direct character comparison.   |
| **Z-Function**                      | Finds all occurrences of a pattern in a string efficiently.                         | Computes prefix similarities using a Z-array.                                           |
| **Fenwick Tree (BIT)**              | Fast prefix sum and range updates.                                                  | Uses a tree-like structure with cumulative frequency storage.                           |
| **Persistent Data Structures**      | Keeps previous versions of data after modifications (undo/redo, version control).   | Uses path-copying or functional persistence techniques.                                 |
| **Treap (Tree + Heap)**             | Self-balancing BST with priority queue properties.                                  | Combines BST for ordering and heap for balancing using random priorities.               |
| **Heavy-Light Decomposition (HLD)** | Efficient path queries in trees.                                                    | Decomposes tree into heavy and light paths for logarithmic queries.                     |
| **Splay Tree**                      | Self-adjusts based on access frequency for faster lookups.                          | Uses rotations (splaying) to move frequently accessed elements to the root.             |
| **Skip List**                       | Alternative to balanced BST for quick search, insert, and delete operations.        | Uses multiple levels of linked lists with probabilistic balancing.                      |
| **KD-Tree (K-Dimensional Tree)**    | Fast nearest neighbor search in multidimensional space.                             | Recursively partitions space using alternating dimensions.                              |
| **Suffix Array + LCP Array**        | Efficient substring search and pattern matching.                                    | Sorts suffixes of a string and computes longest common prefix (LCP) for fast queries.   |



### **Advanced Algorithms**

- [ ] **Minimax**
- [ ] **Tarjan’s Algorithm (Strongly Connected Components & Bridges)**
- [ ] **Hopcroft-Karp Algorithm (Maximum Bipartite Matching)**
- [ ] **Convex Hull & Graham's Scan (Computational Geometry)**
- [ ] **Mo's Algorithm (Offline Queries on Arrays)**
- [ ] **Floyd-Warshall & Johnson’s Algorithm (All-Pairs Shortest Path)**
- [ ] **Miller-Rabin Primality Test**
- [ ] **Pollard’s Rho Algorithm (Integer Factorization)**
- [ ] **Suffix Automaton**
- [ ] **Dominator Tree (Graph Theory)**
- [ ] **FFT (Fast Fourier Transform) & NTT (Number Theoretic Transform)**


| **Algorithm**                        | **Problem It Solves**                                                                               | **Core Idea**                                                                                                                        |
|--------------------------------------|-----------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| **Minimax**                          | Optimal decision-making in turn-based games.                                                        | Recursively evaluates all possible moves and chooses the best one by minimizing the worst-case loss.                                 |
| **Tarjan’s Algorithm**               | Finds **Strongly Connected Components (SCCs)** and **bridges** in a graph.                          | Uses **DFS with low-link values** to identify SCCs in **O(V+E)** time.                                                               |
| **Hopcroft-Karp Algorithm**          | Finds **maximum bipartite matching** efficiently.                                                   | Uses **augmenting paths** and **DFS/BFS** to improve matching in **O(E√V)** time.                                                    |
| **Convex Hull & Graham’s Scan**      | Finds the smallest convex polygon that encloses a given set of points.                              | Uses **sorting + stack-based approach** to construct the hull in **O(n log n)** time.                                                |
| **Mo’s Algorithm**                   | Answers offline range queries (e.g., sum, frequency) efficiently.                                   | Sorts queries in a specific order to minimize the number of modifications to the dataset, achieving **O(√N log N)** time complexity. |
| **Floyd-Warshall Algorithm**         | Finds the **shortest paths between all pairs of nodes** in a weighted graph.                        | Uses **dynamic programming (DP) on adjacency matrix** in **O(V³)** time.                                                             |
| **Johnson’s Algorithm**              | Finds **all-pairs shortest paths** in a weighted graph with negative edges.                         | Uses **Bellman-Ford to reweight edges**, then applies **Dijkstra’s algorithm** for each node.                                        |
| **Miller-Rabin Primality Test**      | Tests whether a number is prime probabilistically.                                                  | Uses **modular exponentiation & random bases** to check for non-prime witnesses.                                                     |
| **Pollard’s Rho Algorithm**          | Efficient integer factorization for large numbers.                                                  | Uses **randomized cycle detection** to find non-trivial factors of a number.                                                         |
| **Suffix Automaton**                 | Finds all occurrences of a substring efficiently.                                                   | Builds a **state machine** that represents all substrings of a string in **O(n)** time.                                              |
| **Dominator Tree**                   | Finds **which nodes in a directed graph dominate others** (e.g., in program control flow analysis). | Uses **DFS & path compression** to compute dominator relationships efficiently.                                                      |
| **FFT (Fast Fourier Transform)**     | Efficient multiplication of large polynomials, number theory, and signal processing.                | Uses **divide and conquer on complex numbers** to evaluate polynomials in **O(n log n)** time.                                       |
| **NTT (Number Theoretic Transform)** | Similar to FFT but optimized for modular arithmetic.                                                | Uses **modular roots of unity** instead of complex numbers for efficient computations in finite fields.                              |


---

## **Revisit Problems**

### DP Problems

### Graph Problems

### Trie Problems

### Greedy Problems

### Greedy Problems

- [Construct K Palindrome Strings](https://leetcode.com/problems/construct-k-palindrome-strings/description/)

- [String Without AAA or BBB](https://leetcode.com/problems/string-without-aaa-or-bbb/description/)

- [Advantage Shuffle](https://leetcode.com/problems/advantage-shuffle/description/)

- [Previous Permutation with One Swap](https://leetcode.com/problems/previous-permutation-with-one-swap/solutions/)

- [Check If a String Can Break Another String](https://leetcode.com/problems/check-if-a-string-can-break-another-string/description/)

### Array Problems

- [Next Permutation](https://leetcode.com/problems/next-permutation/description/)

- [Insert Interval](https://leetcode.com/problems/insert-interval/description/)

### Prefix Sum Problems

- [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/description/)

- [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/description/)

- [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/description/)

### Tree Problems

- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/) →
  Recursion, Divide & Conquer

- [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/) →
  Recursion, Divide & Conquer

- [Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/)

- [Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/solutions/37811/simple-solution-using-constant-space/) →
  BFS, Constant Space Traversal

- [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)

## Lee 215 List

### Prefix Problems

- [Sum of Total Strength of Wizards](https://leetcode.com/problems/sum-of-total-strength-of-wizards/)

- [Number of Submatrices That Sum to Target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/)

- [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)

### Sliding Window Problems

- [Length of Longest Subarray With at Most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/)

- [Find the Longest Equal Subarray](https://leetcode.com/problems/find-the-longest-equal-subarray/)

- [Count Complete Subarrays in an Array](https://leetcode.com/problems/count-complete-subarrays-in-an-array/)

- [Maximum Beauty of an Array After Applying Operation](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/)

- [Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum/)

- [Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/)

- [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)

- [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)

- [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

- [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

### Stack Problems

- [Collecting Chocolates](https://leetcode.com/problems/collecting-chocolates/)

- [Minimum Time to Repair Cars](https://leetcode.com/problems/minimum-time-to-repair-cars/)

- [Next Greater Element IV](https://leetcode.com/problems/next-greater-element-iv/)

- [Online Stock Span](https://leetcode.com/problems/online-stock-span/)

- [Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/)

- [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)

### Mono Stack Problems

- [Beautiful Towers II](https://leetcode.com/problems/beautiful-towers-ii/)

- [Next Greater Element IV](https://leetcode.com/problems/next-greater-element-iv/)

- [Maximum Number of Robots Within Budget](https://leetcode.com/problems/maximum-number-of-robots-within-budget/)

### Binary Search Problems

- [Minimize the Maximum Difference of Pairs](https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/)

- [House Robber IV](https://leetcode.com/problems/house-robber-iv/)

- [Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/)

- [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)

- [Minimize Max Distance to Gas Station](https://leetcode.com/problems/minimize-max-distance-to-gas-station/)

- [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/)

### DP Problems

- [Maximum Points After Collecting Coins From All Nodes](https://leetcode.com/problems/maximum-points-after-collecting-coins-from-all-nodes/)

- [Minimum Increment Operations to Make Array Beautiful](https://leetcode.com/problems/minimum-increment-operations-to-make-array-beautiful/)

- [Apply Operations to Make Two Strings Equal](https://leetcode.com/problems/apply-operations-to-make-two-strings-equal/)

- [Maximize the Profit as the Salesman](https://leetcode.com/problems/maximize-the-profit-as-the-salesman/)

- [Maximum Earnings From Taxi](https://leetcode.com/problems/maximum-earnings-from-taxi/)

- [Maximum Number of Events That Can Be Attended II](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/)

- [Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) 




