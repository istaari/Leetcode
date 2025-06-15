## **DSA Todos**

###  **Advanced Data Structures**

- [ ] **B and B+ Trees** : Balanced search trees designed for efficient disk-based storage and fast range queries in databases.
- [ ] **AVL Tree** : A self-balancing binary search tree where the height difference between left and right subtrees is at most one.
- [ ] **Red-Black Tree** : A self-balancing binary search tree using color properties to ensure balanced height and \( O(\log n) \) operations.
- [ ] **Treap / Splay Tree** : Self-balancing BSTs using randomized or access-based balancing for efficient dynamic set operations.
- [ ] **Fenwick Tree (Binary Indexed Tree)** : A compact data structure for prefix sums and point updates in \( O(\log n) \) time.
- [x] **Segment Tree** : A tree-based structure for answering range queries and updates (e.g., sum, min, max) in logarithmic time.
- [ ] **Segment Tree with Lazy Propagation** : An optimized segment tree variant that supports fast range updates without full recomputation.
- [ ] **Persistent Segment Tree** : Maintains multiple historical versions of a segment tree, useful for rollback and time-based queries.
- [ ] **Sparse Table** : A preprocessed static structure for answering fast, immutable range queries like min or gcd in \( O(1) \) time.
- [ ] **K-D Tree / 2D Segment Tree** : Tree structures for handling multidimensional range queries, often used in geometric problems.
- [ ] **Link-Cut Tree** : A dynamic tree structure (using splay trees) that supports fast path queries and connectivity updates.
- [ ] **Heavy-Light Decomposition (HLD)** : Breaks a tree into paths to support efficient path queries using segment trees or BIT.
- [ ] **Centroid Decomposition** : A divide-and-conquer method on trees to optimize certain types of queries and dynamic programming.
- [ ] **Euler Tour Technique** : Converts a tree into a linear array to enable fast subtree and Lowest Common Ancestor (LCA) queries.


### ✅ **Advanced Algorithms**


### 🔢 **Number Theory**
- [ ] **Miller-Rabin Primality Test** : A fast probabilistic method to check if a number is prime, widely used in cryptography.
- [ ] **Extended Euclidean Algorithm** : Solves linear Diophantine equations and finds modular inverses.
- [ ] **Chinese Remainder Theorem (CRT)** : Solves systems of modular equations; often paired with modular arithmetic.
- [ ] **Modular Exponentiation / Fast Power** : Computes powers modulo a number efficiently — essential for cryptographic algorithms.
- [x] **Sieve of Eratosthenes + Segmented Sieve** : Fast methods to generate primes and count them over large ranges.
- [ ] **Pollard’s Rho Algorithm (Factorization)** : A randomized algorithm for integer factorization, effective on large semi-primes.


### 📚 **String Algorithms**
- [ ] **Manacher’s Algorithm** : Finds all palindromic substrings in linear time, optimized for problems involving string symmetry.
- [ ] **Aho-Corasick Algorithm** : Builds a finite automaton for matching multiple patterns in a text simultaneously, used in multi-pattern search.
- [ ] **Z-Algorithm** : Finds all pattern occurrences in linear time; simpler alternative to KMP in many cases.
- [x] **KMP Algorithm (Knuth–Morris–Pratt)** : Searches for a pattern in a string using a prefix table — efficient and reliable.
- [ ] **Suffix Array + LCP Array** : Powerful tools for solving substring, pattern matching, and lexicographical problems.
- [ ] **Suffix Automaton** : Generalized structure to solve substring problems in linear time.


### 🌲 **Tree Algorithms**
- [ ] **Tarjan's Offline LCA Algorithm** : Answers multiple Lowest Common Ancestor queries on trees efficiently using Union-Find.
- [ ] **Binary Lifting (for LCA, Kth ancestor, path queries)** : Preprocessing technique for trees to support fast ancestor jumps and queries.
- [ ] **Euler Tour + RMQ (for LCA)** : Converts trees into arrays for answering subtree and LCA queries efficiently.
- [ ] **Heavy-Light Decomposition (if not already in DS section)** : Crucial for tree path queries.
- [ ] **DSU on Tree (a.k.a. Small to Large)** : Advanced technique to combine disjoint set union and tree traversal for complex queries.


### 🧮 **Graph Algorithms**
- [ ] **Convex Hull & Graham's Scan** : Computes the smallest convex polygon enclosing a set of points; used in computational geometry.
- [ ] **Dominator Tree (Graph Theory)** : Captures dominance relations in a control flow graph, useful in compiler design and program analysis.


### 💡 **Optimization Algorithms**
- [ ] **Convex Hull Trick / Li-Chao Tree** : Optimizes certain dynamic programming problems by maintaining a set of lines.
- [ ] **Bitmask DP** : Solves subset or state-based problems where the states can be represented as bitmasks.
- [ ] **Sliding Window Optimization** : Optimizes nested loops to linear time using queues, stacks, or deques.
- [ ] **Meet in the Middle** : Divides the problem into halves for brute-force and combines results efficiently (used in knapsack, etc.).


### 🧩 **Miscellaneous Algorithms**
- [ ] **Mo's Algorithm (Offline Queries)** : Efficiently answers range queries on static arrays by sorting them in a special order using sqrt-decomposition.
- [ ] **Game Theory (Grundy Numbers / Sprague-Grundy)** : For turn-based games — nimbers, XOR-based analysis, etc.
- [ ] **2-SAT and Implication Graph** : Solves logical constraints problems, commonly found in contests involving binary conditions.


### 🛠 **Fast Fourier Transform (FFT) & Related**
- [ ] **FFT (Fast Fourier Transform)** : Efficient algorithm to compute convolutions and polynomial multiplication.
- [ ] **NTT (Number Theoretic Transform)** : The number-theoretic variant of FFT, used for modular arithmetic in large number problems.

---


## **Revisit Problems**

### Tree Problems

- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)

- [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/)

- [Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/)

- [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)

---

## **Company List**

### **Tier I**

- **Google**
- **Amazon**
- **Meta**
- **Microsoft**
- **Uber**
- **Netflix**
- **Apple**

### **Tier II**

- **Angel One**
- **Databricks**
- **Wayfair**
- **Stripe**
- **Coinbase**
- **Nutanix**
- **Walmart**
- **Salesforce**
- **Gojek**
- **CoinDcx**
- **Booking.com**
- **MakeMyTrip**
- **MakeMyTrip**
- **LinkedIn**
- **Godaddy**
- **Razorpay**
- **Groww**
- **ServiceNow**
- **Tesco**


## **Current Todos**

- HLD and LLD and HM
- DP, Graph, Greedy and implementation