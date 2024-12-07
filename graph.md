### 1. **Traversal Problems**
- **Depth First Search (DFS)**: Explore as far as possible along each branch before backtracking.
- **Breadth First Search (BFS)**: Explore all neighbors at the current depth before moving deeper.
- **Shortest Path Problems**: Find the shortest path between nodes.
    - Dijkstra’s Algorithm (single-source shortest path)
    - Bellman-Ford Algorithm (handles negative weights)
    - Floyd-Warshall Algorithm (all-pairs shortest path)
    - A* Search Algorithm (heuristic-based shortest path)
- **Eulerian Path and Circuit**: Determine if a graph has a path or circuit visiting every edge exactly once.
- **Hamiltonian Path and Circuit**: Determine if a graph has a path or circuit visiting every vertex exactly once.

---

### 2. **Connectivity Problems**
- **Connected Components**: Find all connected subgraphs in an undirected graph.
- **Articulation Points**: Find vertices whose removal increases the number of connected components.
- **Bridges (Cut Edges)**: Find edges whose removal increases the number of connected components.
- **Biconnected Components**: Decompose the graph into biconnected components.
- **Strongly Connected Components (SCCs)**: Identify SCCs in a directed graph (Tarjan’s or Kosaraju’s algorithm).

---

### 3. **Cycle Detection**
- **Cycle in Undirected Graph**: Detect the existence of cycles using DFS or union-find.
- **Cycle in Directed Graph**: Use DFS or Kahn’s algorithm for cycle detection.
- **Negative Weight Cycle**: Detect using Bellman-Ford.

---

### 4. **Graph Coloring Problems**
- **Vertex Coloring**: Assign minimum colors such that no two adjacent vertices share the same color.
- **Edge Coloring**: Assign colors to edges so that no two edges sharing a vertex have the same color.
- **Planar Graph Coloring**: Four Color Theorem application.
- **Bipartite Graph Checking**: Test if the graph is bipartite using 2-coloring.

---

### 5. **Tree-Specific Problems**
- **Minimum Spanning Tree (MST)**: Find the subset of edges forming a tree that connects all vertices with minimal total weight.
    - Prim’s Algorithm
    - Kruskal’s Algorithm
- **LCA (Lowest Common Ancestor)**: Find the lowest common ancestor of two nodes in a tree.
- **Diameter of Tree**: Find the longest path between any two nodes.
- **Tree Traversals**: Preorder, Inorder, Postorder, Level-order.

---

### 6. **Flow and Matching Problems**
- **Maximum Flow**: Find the maximum possible flow in a flow network.
    - Ford-Fulkerson Method
    - Edmonds-Karp Algorithm
    - Dinic’s Algorithm
- **Minimum Cut**: Identify the smallest set of edges that, if removed, would disconnect the graph.
- **Bipartite Matching**: Maximum matching in bipartite graphs (Hopcroft–Karp algorithm).
- **Stable Marriage Problem**: Match elements of two sets based on preferences.
- **Vertex Cover and Edge Cover**: Find a set of vertices/edges covering all edges/vertices.

---

### 7. **Planar Graph Problems**
- **Planarity Testing**: Check if a graph can be embedded in the plane without crossing edges.
- **Graph Drawing**: Algorithms for visually embedding graphs (e.g., force-directed layout).
- **Face Traversal**: Traversing the regions (faces) defined by a planar graph.

---

### 8. **Network Analysis**
- **PageRank**: Rank vertices based on their importance (e.g., Google Search algorithm).
- **Community Detection**: Partition the graph into clusters or communities.
- **Clustering Coefficient**: Measure the tendency of nodes to form tightly knit groups.
- **Centrality Measures**: Analyze importance of vertices.
    - Degree Centrality
    - Betweenness Centrality
    - Closeness Centrality
    - Eigenvector Centrality

---

### 9. **Graph Transformations**
- **Line Graph**: Transform edges of a graph into vertices of a new graph.
- **Dual Graph**: Create a dual representation for planar graphs.
- **Graph Isomorphism**: Test if two graphs are structurally identical.

---

### 10. **Other Common Problems**
- **Topological Sorting**: Order vertices in a directed acyclic graph (DAG) such that for every edge \( u \to v \), \( u \) comes before \( v \).
- **Graph Reconstruction**: Reconstruct a graph given partial data like degree sequences.
- **Network Reliability**: Compute reliability of a network under failures.
- **Traveling Salesman Problem (TSP)**: Find the shortest possible route visiting each vertex exactly once.
- **Vertex/Edge Deletion Problems**: Modify graphs by deleting vertices/edges to satisfy properties (e.g., make acyclic).

---


# Graph Algorithms

---

### **Graph Traversal Algorithms**
1. **Breadth-First Search (BFS)**
2. **Depth-First Search (DFS)**

---

### **Shortest Path Algorithms**
1. **Dijkstra's Algorithm** (for graphs with non-negative weights)
2. **Bellman-Ford Algorithm** (handles graphs with negative weights)
3. **Floyd-Warshall Algorithm** (all-pairs shortest paths)
4. **A\* Algorithm** (heuristic-based pathfinding)
5. **Johnson's Algorithm** (all-pairs shortest paths, works well with sparse graphs)
6. **SPFA (Shortest Path Faster Algorithm)** (optimization of Bellman-Ford)

---

### **Minimum Spanning Tree (MST) Algorithms**
1. **Kruskal's Algorithm**
2. **Prim's Algorithm**
3. **Borůvka's Algorithm**

---

### **Topological Sorting Algorithms**
1. **Kahn's Algorithm**
2. **DFS-based Topological Sort**

---

### **Cycle Detection in Directed Graphs**

1. **Depth-First Search (DFS)**:
  - Use a **recursion stack** (or a `visited` state with three values: `unvisited`, `visiting`, and `visited`).
  - If a node is encountered that's already in the recursion stack, a cycle is detected.
  - Time Complexity: \(O(V + E)\), where \(V\) is the number of vertices and \(E\) is the number of edges.

2. **Kahn's Algorithm**:
  - Perform a **topological sort**. If you cannot process all nodes (some nodes have non-zero in-degree remaining), a cycle exists.
  - Time Complexity: \(O(V + E)\).

3. **Tarjan's Algorithm**:
  - This algorithm finds **strongly connected components (SCCs)** in a directed graph.
  - If an SCC contains more than one node or a self-loop, a cycle exists.
  - Time Complexity: \(O(V + E)\).

4. **Disjoint Set (Union-Find)**:
  - Less commonly used for directed graphs. Useful only in certain representations or scenarios, like a graph with edges already sorted.

---

### **Cycle Detection in Undirected Graphs**

1. **Depth-First Search (DFS)**:
  - In an undirected graph, a back edge (an edge pointing to an already visited ancestor that is not the direct parent) indicates a cycle.
  - Time Complexity: \(O(V + E)\).

2. **Disjoint Set (Union-Find)**:
  - Use the **Union-Find algorithm** with path compression and union by rank to detect cycles.
  - If two vertices connected by an edge already belong to the same set, a cycle is detected.
  - Time Complexity: \(O(E \cdot \alpha(V))\), where \(\alpha(V)\) is the inverse Ackermann function (very slow-growing, almost constant).

3. **Breadth-First Search (BFS)**:
  - BFS can be used to detect cycles by keeping track of parent nodes. If a neighbor is visited and it's not the parent, a cycle exists.
  - Time Complexity: \(O(V + E)\).

---

### **Key Differences**
- Directed graphs: Focus on back edges or strongly connected components.
- Undirected graphs: Use parent tracking or union-find to detect cycles efficiently.
---

### **Connectivity Algorithms**
1. **Tarjan's Algorithm** (for finding articulation points and bridges)
2. **Kosaraju's Algorithm** (for strongly connected components)
3. **Fleury's Algorithm** (for Eulerian Path)
4. **Hierholzer's Algorithm** (for Eulerian Circuit)
5. **Hopcroft-Karp Algorithm** (for bipartite matching)

---

### **Flow and Network Algorithms**
1. **Ford-Fulkerson Algorithm** (maximum flow)
2. **Edmonds-Karp Algorithm** (implementation of Ford-Fulkerson using BFS)
3. **Dinic's Algorithm** (efficient maximum flow)
4. **Push-Relabel Algorithm** (for maximum flow)
5. **Min-Cut Max-Flow Theorem**

---

### **Specialized Graph Algorithms**
1. **PageRank Algorithm** (for web graph analysis)
2. **Havel-Hakimi Algorithm** (to check graphical degree sequences)
3. **Bron-Kerbosch Algorithm** (for finding all maximal cliques)
4. **Graph Coloring Algorithms** (e.g., greedy coloring, backtracking)

---

### **Tree-Based Algorithms** (Trees are special cases of graphs)
1. **Lowest Common Ancestor (LCA)** algorithms
  - Binary Lifting
  - Euler Tour and RMQ (Range Minimum Query)
2. **Tree Diameter Algorithms** (using BFS/DFS twice)
3. **Centroid Decomposition** (for divide-and-conquer on trees)

---

### **Heuristic and Approximation Algorithms**
1. **Greedy Algorithms for TSP (Traveling Salesman Problem)**
2. **Christofides Algorithm** (for TSP approximation)
3. **Ant Colony Optimization** (heuristic for pathfinding)

---

### **Graph Matching Algorithms**
1. **Hungarian Algorithm** (for bipartite graph matching)
2. **Blossom Algorithm** (for maximum matching in general graphs)

---