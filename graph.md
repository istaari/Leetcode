## Graph

### Traversal Algorithms

| Algorithm                      | Description                  |
|--------------------------------|------------------------------|
| **DFS (Depth First Search)**   | Traverses graph depth-wise   |
| **BFS (Breadth First Search)** | Traverses graph breadth-wise |

### Shortest Path Algorithms

| Algorithm                    | Description                                |
|------------------------------|--------------------------------------------|
| **Dijkstra's Algorithm**     | For non-negative weights                   |
| **Bellman-Ford Algorithm**   | Handles negative weights                   |
| **Floyd-Warshall Algorithm** | All-pairs shortest paths                   |
| **Johnson's Algorithm**      | All-pairs shortest paths for sparse graphs |
| **A(Star) Search Algorithm** | Heuristic-based shortest path              |
| **Bidirectional Search**     | Searches from both start and goal          |

### Minimum Spanning Tree (MST) Algorithms

| Algorithm               | Description                         |
|-------------------------|-------------------------------------|
| **Prim's Algorithm**    | Greedy algorithm for MST            |
| **Kruskal's Algorithm** | Greedy algorithm using edge sorting |

### Cycle Detection

| Algorithm                                 | Description                                    |
|-------------------------------------------|------------------------------------------------|
| **Union-Find (Disjoint Set Union – DSU)** | For undirected graphs                          |
| **DFS-based Cycle Detection**             | For directed graphs                            |
| **Kahn's Algorithm**                      | BFS-based topological sort for cycle detection |

### Topological Sorting

| Algorithm                        | Description                            |
|----------------------------------|----------------------------------------|
| **DFS-based Topological Sort**   | Orders vertices in a DAG               |
| **Kahn's Algorithm (BFS-based)** | Alternative topological sorting method |

### Connected Components

| Algorithm                | Description                                     |
|--------------------------|-------------------------------------------------|
| **DFS/BFS**              | Finds connected components in undirected graphs |
| **Kosaraju's Algorithm** | Finds strongly connected components (SCC)       |
| **Tarjan's Algorithm**   | SCC detection using low-link values             |
| **Union-Find**           | Efficient for connectivity queries              |

### **1. DFS and BFS Traversal Problems**

**Examples:**

- [Flood Fill](https://leetcode.com/problems/flood-fill/) - Fill a connected region in a grid starting from a given point.

  - In Flood Fill, we change the color of a group of connected cells starting from a given cell.

- [Number of Islands](https://leetcode.com/problems/number-of-islands/) - Count the number of islands in a 2D grid of water and land.

  - In Number of Islands, we count groups of connected '1's (land) and change them to '0' to mark them as visited.

  - The problem can be represented count connected components in a graph

- [Clone Graph](https://leetcode.com/problems/clone-graph/description/)

- [Get Watched Videos by Your Friends](https://leetcode.com/problems/get-watched-videos-by-your-friends/) - Find the most popular videos watched by your friends in a social network.

- [Evaluate Division](https://leetcode.com/problems/evaluate-division/) - Evaluate the result of division based on a set of equations.

- [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) - Reconstruct a travel itinerary based on given flight tickets.


### **2. Connected Components Problems**

| **Type of Component**                  | **Definition**                                                                                                                       | **Example**                   | **Explanation**                                                                                                                                  | **Algorithm for Solving**                               |
|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| **Connected Component (CC)**           | Maximal subgraph in an **undirected graph** where all nodes are reachable from each other.                                           | **Graph:** `A -- B`, `C -- D` | In an undirected graph, two components: `{A, B}` and `{C, D}` because each pair is connected, but there’s no path between `{A, B}` and `{C, D}`. | **DFS**, **BFS**, **Union-Find (Disjoint Set)**         |
| **Strongly Connected Component (SCC)** | Maximal subgraph in a **directed graph** where every node is reachable from every other node in **both directions**.                 | **Graph:** `A → B → C, C → A` | `{A, B, C}` forms a single SCC since all nodes can reach each other in both directions.                                                          | **Kosaraju’s Algorithm**, **Tarjan’s Algorithm**        |
| **Weakly Connected Component (WCC)**   | Maximal subgraph in a **directed graph** where every node is reachable from every other node if edges are treated as **undirected**. | **Graph:** `A → B, D → E`     | `{A, B}` and `{D, E}` form two WCCs. If we treat the graph as undirected, both components become connected.                                      | **DFS**, **BFS** (after converting to undirected graph) |


**Examples:**

- [Number of Provinces](https://leetcode.com/problems/number-of-provinces/) - Count the number of connected provinces in a graph.

- [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) - Find the minimum number of operations to make the network fully connected.

- [Accounts Merge](https://leetcode.com/problems/accounts-merge/) - Merge accounts with common email addresses into a single account.

- [Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/) - Identify the critical connections in a network that, if removed, would increase the number of connected components.

- [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) - Find the maximum number of stones that can be removed in a grid.


### **3. Cycle Detection in Graph**

| **Topic**                                  | **Description**                                                                                                                                                                                                                                       | **Techniques to Detect Cycles**                                                                                                                       |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Cycle Detection in an Undirected Graph** | A single edge between two vertices (`A <--> B`) **does not form a cycle** unless there is a self-loop (an edge from `A` to `A` or `B` to `B`).<br>A **cycle in an undirected graph must involve at least 3 vertices** (except when self-loops exist). | **DFS (Depth-First Search) with Parent Tracking**<br>**Union-Find (Disjoint Set Union)**                                                              |
| **Cycle Detection in a Directed Graph**    | A cycle in a directed graph can exist with just 2 vertices (`A -> B -> A`).                                                                                                                                                                           | **Cycle Detection using Colors" (Three-State DFS Marking Method)**<br>**Topological Sorting (Kahn's Algorithm - BFS)**                                |

**Examples:**

- [Redundant Connection](https://leetcode.com/problems/redundant-connection/description/) - Find the redundant connection in a graph that results in a cycle.


### **4. Topological Sort & Directed Acyclic Graphs (DAG)**

- Kahn’s Algorithm(Specific Topological Sort Algorithm) 

**Examples:**

- [Course Schedule](https://leetcode.com/problems/course-schedule/) - Determine if it's possible to finish all courses given the prerequisite constraints.

- [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Find the order of courses to take to finish all courses given the prerequisite constraints.

- [Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/) - Reconstruct the original sequence from a set of sequences, ensuring that they are in the correct order.

- [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/solution/) - Determine the order of letters in an alien language based on a list of words.

- [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) - Find the longest increasing path in a matrix.


### **5. Union Find**

**Examples:**

- [Largest Component Size by Common Factor](https://leetcode.com/problems/largest-component-size-by-common-factor/) - Find the largest connected component of nodes that share a common factor.

- [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) - Find the maximum number of stones that can be removed in a grid using Union Find.

### **6. Minimum Spanning Tree (MST)**

- **Kruskal's Algorithm**  

  - Uses edges, sorts them, and adds them one by one to form the MST

- **Prim's Algorithm** 

  - Uses nodes, expanding the MST from a starting node

**Examples:**

- [Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) - Connect all cities with the minimum cost, ensuring no cycles and a minimum spanning tree.

- [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Connect all points with the minimum cost, ensuring all points are part of a minimum spanning tree.


### **7. Shortest Path Algorithms**

| Graph Type                      | Use                             | Notes                                                             |
|---------------------------------|---------------------------------|-------------------------------------------------------------------|
| **Undirected, Unweighted**      | ✅ **BFS**                       | All edges have equal cost                                         |
| **Undirected, Positive Weight** | ✅ **Dijkstra**                  | Add both directions manually                                      |
| **Undirected, Negative Weight** | ✅ **Bellman-Ford**              | Add both directions manually, use with caution if negative cycles |
| **Directed, Unweighted**        | ✅ **BFS**                       | Same as undirected BFS, but follow direction                      |
| **Directed, Positive Weight**   | ✅ **Dijkstra**                  | Handles only non-negative weights                                 |
| **Directed, Negative Weight**   | ✅ **Bellman-Ford**              | Good for sparse graphs, detects negative cycles                   |
| **Directed, Negative Cycle**    | ⚠️ **Bellman-Ford** (to detect) | Cannot find shortest paths, but detects negative cycles           |
| **Directed, All-Pairs**         | ✅ **Floyd-Warshall**            | Especially good for small graphs (V ≤ 100)                        |


**BFS(Unweighted graph)**

**Examples:**

- [Word Ladder I](https://leetcode.com/problems/word-ladder/) - Find the shortest transformation sequence from one word to another using a dictionary.

  - Problem can be represented as `unweighted and directed graph`, Use `BFS` to find the shortest path between two nodes

    ```
    graph = {
        "hit": ["hot"],
        "hot": ["hit", "dot", "lot"],
        "dot": ["hot", "dog"],
        "lot": ["hot", "log"],
        "dog": ["dot", "cog"],
        "log": ["lot", "cog"],
        "cog": ["dog", "log"]
    }
    ```

  - Transform each char from `a-z` and check if it is present in the dictionary to find the to find the shortest path from beginWord to endWord
  - We can use Bidirectional BFS to optimize the solution

- [Word Ladder II](https://leetcode.com/problems/word-ladder-ii/) - Find all the shortest transformation sequences from one word to another.

  - Use BFS from `beginWord` to `endWord` to construct the reverse graph
    ```
      graph = {
        "hot": ["hit"],
        "dot": ["hot"],
        "lot": ["hot"],
        "dog": ["dot"],
        "log": ["lot"],
        "cog": ["dog", "log"]
        }
    ```
  - Use backtracking and DFS to find all the shortest paths from `endWord` to `beginWord`

- [Rotating Oranges](https://leetcode.com/problems/rotting-oranges/description/)  

- [Cut Off Trees for Golf Event](https://leetcode.com/problems/cut-off-trees-for-golf-event/) - Minimize the number of steps required to cut off trees in a golf course.

**Dijkstra's Algorithm(Weighted graph with positive weights)** 

**Examples:**

- [Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/) - Find the path in a graph where the minimum value on the path is maximized.

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node.

- [Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/) - Find the path with the highest probability in a graph.

- [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) - Find the path with the smallest possible maximum effort in a 2D grid.

- [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) - Find the cheapest price for flights within a given number of stops.


**Floyd-Warshall Algorithm(Weighted graph with negative weights)**

**Examples:**

- [Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) - Find the city with the smallest number of neighboring cities at or below a certain distance threshold.

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node, considering all pairwise shortest paths.


**Bellman-Ford Algorithm(Weighted graph with negative weights)**

**Examples:**

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node, considering edge weights in the graph.


### **8. Graph Coloring**

  - Solves the problem of assigning colors to vertices in a graph such that no two adjacent vertices share the same color.

  - **Bipartite Graph** :  A graph \( G = (V, E) \) is **bipartite** if its vertex set \( V \) can be partitioned into two sets \( U \) and \( V \) such that every edge in \( E \) connects a vertex in \( U \) to one in \( V \).

     -  Graph can be colored with 2 colors such that no two adjacent nodes share the same color
     - DFS and BFS can be used to check if a graph is bipartite by coloring the nodes with two colors and checking for conflicts.

### **9 .Connectivity and Bridges** 

**Bridge**

- A bridge (or cut edge) is an edge in an undirected graph whose removal increases the number of connected components.

- **Tarjan’s algorithm** can be modified to find bridges using `low[]` values.


**Articulation Point**

- An articulation point (or cut vertex) is a node whose removal increases the number of connected components in an undirected graph.

- **Tarjan’s algorithm** can be modified to find articulation points using `low[]` values.


### **10. Flow and Matching Problems**

**Flow**

- Flow problems deal with transporting "stuff" (like data, water, traffic, etc.) through a network of nodes and edges, where each edge has a capacity (max flow allowed).

- **Typical Problem** : Find the maximum flow from a source node to a sink node, obeying capacity limits on edges.


| Algorithm             | Description                                                     | Time Complexity                                                |
|-----------------------|-----------------------------------------------------------------|----------------------------------------------------------------|
| **Ford-Fulkerson**    | Basic method using DFS + greedy augmenting paths                | \( O(E \cdot \text{max flow}) \)                               |
| **Edmonds-Karp**      | Ford-Fulkerson with BFS → always finds shortest augmenting path | \( O(VE^2) \)                                                  |
| **Dinic's Algorithm** | Uses BFS for level graph + DFS for blocking flow                | \( O(EV^2) \) worst, \( O(\sqrt{V}E) \) for bipartite matching |


**Matching**

- A matching is a set of edges where no two edges share a vertex. In a bipartite graph, a maximum matching is the largest possible matching between the two sides (e.g., jobs ↔ workers).

- **Typical Problem** : Pair up elements from two sets (e.g., people and tasks) in such a way that no one is matched more than once, and the total number of matches is maximized.

| Algorithm               | Description                                                                      | Time Complexity             |
|-------------------------|----------------------------------------------------------------------------------|-----------------------------|
| **Hungarian Algorithm** | Used for perfect matchings in weighted bipartite graphs (min cost or max profit) | \( O(V^3) \)                |
| **Hopcroft-Karp**       | Efficient method for maximum matching in unweighted bipartite graphs             | \( O(\sqrt{V} \cdot E) \)   |
| **Kuhn's Algorithm**    | DFS-based approach for unweighted bipartite graphs                               | \( O(V \cdot E) \)          |


### **11. DP on Graph**

