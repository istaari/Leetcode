# **Introduction**

## The Building Blocks of a Graph 🧱

At its heart, a graph is a simple structure used to model relationships between objects.

* **Vertices (or Nodes):** These are the fundamental entities or points in the graph. Think of them as cities on a map, people in a social network, or web pages on the internet.
* **Edges (or Links/Arcs):** These are the connections between pairs of vertices. They represent the relationship between the entities, like roads between cities, friendships between people, or hyperlinks between web pages.

A graph is formally defined as a pair of sets: `G = (V, E)`, where `V` is the set of vertices and `E` is the set of edges.

### Examples

**Social Networks**

Social platforms model human connections and interactions as graphs.

* **Vertices:** User profiles.
* **Edges:** The relationship between users.
    * **Undirected Edge:** On Facebook, a "friendship" is mutual, creating an undirected edge. If you are friends with someone, they are also friends with you.
    * **Directed Edge:** On Twitter or Instagram, a "follow" is a directed edge. You can follow someone without them following you back.


**Transportation and Maps**

Navigation systems like Google Maps or Waze use weighted graphs to find the best routes.

* **Vertices:** Specific locations, such as cities, street intersections, airports, or train stations.
* **Edges:** The paths connecting these locations, like roads, highways, flight paths, or railway tracks. These edges are typically **weighted** by values such as:
    * **Distance** (kilometers or miles)
    * **Travel Time** (which can change based on real-time traffic)
    * **Cost** (tolls or ticket prices)



**Recommendation Engines**

Services like Netflix, Spotify, and Amazon use graphs to suggest content or products you might like.

* **Vertices:** Two types of nodes exist: **Users** and **Items** (e.g., movies, songs, products). This forms a *bipartite graph*.
* **Edges:** An edge connects a User to an Item. The edge can be **weighted** by the user's interaction, such as:
    * The rating a user gave a movie (e.g., 1 to 5 stars).
    * The number of times a user has listened to a song.
    * A simple binary value indicating whether a user purchased a product.

The engine recommends items by finding users who are "close" to you in the graph (i.e., have similar tastes) and then suggesting items they liked that you haven't seen yet.


## Types of Graphs: A Visual Vocabulary

Graphs come in various flavors, each with its own specific characteristics and uses.

### **Based on Edge Direction**

* **Undirected Graph:** Edges have no direction. If an edge connects vertex A to vertex B, it also connects B to A. This is like a two-way street or a friendship on Facebook.

* **Directed Graph (Digraph):** Edges have a direction, usually indicated by an arrow. An edge from A to B doesn't necessarily mean there's an edge from B to A. This is like a one-way street or following someone on Twitter.


### **Based on Edge and Vertex Properties**

* **Weighted Graph:** Each edge is assigned a numerical weight or cost. This weight can represent distance, time, or capacity. For example, a map with distances between cities would be a weighted graph.

* **Unweighted Graph:** Edges have no assigned weights. The focus is purely on the connections themselves.

* **Simple Graph:** A graph with no loops (edges that connect a vertex to itself) and no parallel edges (multiple edges between the same two vertices).

* **Multigraph:** A graph that allows parallel edges between vertices and loops on the same vertex.


* **Complete Graph:** A simple undirected graph where every pair of distinct vertices is connected by a unique edge. A complete graph with 'n' vertices is denoted as `Kn`.


* **Bipartite Graph:** A graph whose vertices can be divided into two disjoint and independent sets, U and V, such that every edge connects a vertex in U to one in V.

* **Tree:** A connected graph with no cycles. Trees are fundamental data structures in computer science.


### Fundamental Properties of Graphs 💡

**Degree of a Vertex:** 

In an undirected graph, the degree of a vertex is the number of edges connected to it. In a directed graph, we have:  
-  **In-degree:** The number of incoming edges.
-  **Out-degree:** The number of outgoing edges.

**Path:**

- **Path in an Undirected Graph**

  In an undirected graph, a path is like a trail you can walk between nodes. The direction doesn't matter.

  Consider this graph:

  ```
        A --------- B --------- E
        |           |
        |           |
        C --------- D
  ```

    * A valid path from **A** to **E** is the sequence of vertices: `A → B → E`.
    * Another valid path from **A** to **E** is: `A → C → D → B → E`.
    * The sequence `A → E` is **not** a path because there is no direct edge connecting them.


- **Path in a Directed Graph**

  In a directed graph, a path is like a one-way street. You must follow the direction of the arrows.

  Consider this graph:

  ```
        A ---------> B
        |            |
        v            v
        C ---------> D <--------- E
  ```

    * A valid path from **A** to **D** is the sequence: `A → C → D`.
    * Another valid path is: `A → B → D`.
    * The sequence `A → C → B` is **not** a valid path because you cannot go from C to B against the arrow's direction.


**Cycle**

A cycle is a path that starts and ends at the same vertex, forming a loop.

  * **In an Undirected Graph:** A cycle is a path where you can travel from a node, visit other nodes, and return to the start without reusing an edge.

    ```
       A --------- B
       |           |
       |           |
       D --------- C
    ```

    The path `A → B → C → D → A` is a cycle.

  * **In a Directed Graph:** A cycle must follow the direction of the arrows.

    ```
       A ---------> B
       ^            |
       |            |
       |            v
       D <--------- C
    ```

    The path `A → B → C → D → A` is a directed cycle. A graph with no cycles is called **acyclic**.   


- **Cycle with One Vertex :** A cycle with just one vertex is only possible if that vertex has an edge that connects back to itself. This is called a **self-loop**.

    * **Undirected Graph:** A self-loop on vertex A creates a cycle of length 1.

      ```
        ---
      /   \
      (     )
      \   /
        -A-
      ```

      The path starts at A, traverses the loop, and ends at A.

    * **Directed Graph:** Similarly, a directed edge starting and ending at the same vertex forms a cycle.

      ```
        -->--
      /     \
      (   A   )
      \     /
        --<--
      ```

  **Without a self-loop, a single vertex cannot form a cycle.**

- **Cycle with Two Vertices**

  * **Undirected Graph:** In a **simple graph** (where there are no parallel edges), a two-vertex setup is **not considered a cycle**.

    ```
       A --------- B
    ```

    The path `A → B → A` is just traversing the same edge back and forth, which doesn't count as a true cycle. To be a cycle, a path typically can't immediately reuse the same edge in reverse.

    However, a cycle *can* exist if you have **parallel edges** (making it a **multigraph**).

    ```
          /-----\
       A           B
          \-----/
    ```

    Here, you can go from `A` to `B` on the top edge and return from `B` to `A` on the bottom edge. This forms a valid cycle of length 2.

  * **Directed Graph:** A cycle with two vertices is very common and straightforward. It happens when there is an edge from A to B **and** an edge from B back to A.

    ```
         ------>
       A         B
         <------
    ```

    The path `A → B → A` is a valid directed cycle because it follows two different directed edges.


**Connected Graph**

This term primarily applies to **undirected graphs**. A graph is connected if there is a path between every pair of vertices. In simple terms, the graph is "all one piece."

  * **Connected Graph:** You can get from any node to any other node.

    ```
       A --- B --- C
             |
             D
    ```

  * **Disconnected Graph:** The graph is made of two or more separate components.

    ```
       A --- B      C --- D
    ```

    You cannot get from node A to node C.

For **directed graphs**, the equivalent concepts are "weakly connected" (if the underlying undirected version is connected) and "strongly connected."

**Strongly Connected Graph**

This term is specifically for **directed graphs**. A directed graph is strongly connected if for every pair of vertices (A, B), there is a path from A to B **and** a path from B back to A.

  * **Strongly Connected:** Every node can reach every other node.

    ```
       A <-------> B
       ^ \       / ^
       |  \     /  |
       |   \   /   |
       |    > v <  |
       +----- C ---+
    ```

    From A, you can get to B and C. From B, you can get to A and C. From C, you can get to A and B.

  * **Weakly Connected:**

    ```
       A ---------> B ---------> C
    ```

    You can get from A to C, but you **cannot** get back from C to A. Therefore, it is not strongly connected.


* **Adjacency:** Two vertices are **adjacent** if they are connected by an edge.


### Minimum Sapnning Tree

A **Minimum Spanning Tree (MST)** is the cheapest possible way to connect all the "dots" (vertices) in a weighted graph into a single tree structure without forming any cycles.

**Analogy: Connecting a New Neighborhood**

Imagine you're a city planner tasked with providing internet to a new neighborhood. You have a map of all the houses (**vertices**) and the potential cable routes between them. Digging along each route has a different cost (**edge weights**).

Your goal is to **connect every single house** to the network, directly or indirectly, using the **least amount of cable** to minimize the total cost.

  * A **Spanning Tree** is any layout that connects all houses without creating redundant loops (cycles). A loop would be wasteful—like running a cable from House A to B, then B to C, and also directly from C back to A.
  * The **Minimum Spanning Tree** is the specific layout that achieves this with the absolute lowest total cost. You're finding the cheapest possible "backbone" for the network.

**Example**

**Original Graph with All Possible Connections:**

```
      (1)
   A ------- B
   | \     / |
   |  \   /  |
(4)|   (5)  |(2)
   |    \ /   |
   |     X    |
   |    / \   |
(3)|   /   \  |(6)
   |  /     \ |
   C ------- D
      (7)
```

There are many ways to connect all four vertices, but we want the cheapest one. An MST algorithm would select the following edges:

1.  **A – B (Cost 1):** The cheapest edge overall.
2.  **B – D (Cost 2):** The next cheapest edge.
3.  **A – C (Cost 3):** The next cheapest. We can add this because it doesn't form a cycle. We don't add A-B-D-A or anything similar.


# **Patterns and Algorithms**

## **DFS and BFS Traversal**

### BFS Variations

### 1. Standard BFS

* **What it does:** Explores a graph layer by layer from a starting point. It's used to find the shortest path in an unweighted graph.
* **LeetCode Problems:**
    * [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/)
    * [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
    * [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

### 2. Multi-Source BFS

* **What it does:** Starts the BFS from multiple source nodes simultaneously. This is perfect for problems where you need to find the shortest distance from any node to the *nearest* of several special nodes.
* **LeetCode Problems:**
    * [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/)
    * [1162. As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible/)
    * [542. 01 Matrix](https://leetcode.com/problems/01-matrix/)

### 3. 0-1 BFS

* **What it does:** Finds the shortest path in a graph where edge weights are restricted to only 0 or 1. It prioritizes traversing 0-cost edges over 1-cost edges.
* **LeetCode Problems:**
    * [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)
    * [934. Shortest Bridge](https://leetcode.com/problems/shortest-bridge/)
    * [2290. Minimum Obstacle Removal to Reach Corner](https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/)

### 4. BFS with Bitmasking (Stateful BFS)

* **What it does:** Finds the shortest path in a graph where your ability to move depends on your current "state" (e.g., keys you've collected). It allows you to revisit a node if you arrive in a different, useful state.
* **LeetCode Problems:**
    * [864. Shortest Path to Get All Keys](https://leetcode.com/problems/shortest-path-to-get-all-keys/)
    * [847. Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes/)
    * [1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix](https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/)

### 5. Bidirectional BFS

* **What it does:** Runs two BFS searches at the same time—one from the source and one from the target. The search stops when the two explorations meet in the middle, which is often much faster than a single search.
* **LeetCode Problems:**
    * [127. Word Ladder](https://leetcode.com/problems/word-ladder/)
    * [433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/)
    * [752. Open the Lock](https://leetcode.com/problems/open-the-lock/)



## **Connected Components**

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


## **Cycle Detection**

| **Topic**                                  | **Description**                                                                                                                                                                                                                                       | **Techniques to Detect Cycles**                                                                                                                       |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Cycle Detection in an Undirected Graph** | A single edge between two vertices (`A <--> B`) **does not form a cycle** unless there is a self-loop (an edge from `A` to `A` or `B` to `B`).<br>A **cycle in an undirected graph must involve at least 3 vertices** (except when self-loops exist). | **DFS (Depth-First Search) with Parent Tracking**<br>**Union-Find (Disjoint Set Union)**                                                              |
| **Cycle Detection in a Directed Graph**    | A cycle in a directed graph can exist with just 2 vertices (`A -> B -> A`).                                                                                                                                                                           | **Cycle Detection using Colors" (Three-State DFS Marking Method)**<br>**Topological Sorting (Kahn's Algorithm - BFS)**                                |

**Examples:**

- [Redundant Connection](https://leetcode.com/problems/redundant-connection/description/) - Find the redundant connection in a graph that results in a cycle.


## **Topological Sorting in Directed Acyclic Graphs (DAGs)**

- Kahn’s Algorithm(Specific Topological Sort Algorithm) 

**Examples:**

- [Course Schedule](https://leetcode.com/problems/course-schedule/) - Determine if it's possible to finish all courses given the prerequisite constraints.

- [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Find the order of courses to take to finish all courses given the prerequisite constraints.

- [Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/) - Reconstruct the original sequence from a set of sequences, ensuring that they are in the correct order.

- [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/solution/) - Determine the order of letters in an alien language based on a list of words.

- [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) - Find the longest increasing path in a matrix.



## **Minimum Spanning Tree (MST)**

- **Kruskal's Algorithm**  

  - Uses edges, sorts them, and adds them one by one to form the MST

- **Prim's Algorithm** 

  - Uses nodes, expanding the MST from a starting node

**Examples:**

- [Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) - Connect all cities with the minimum cost, ensuring no cycles and a minimum spanning tree.

- [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Connect all points with the minimum cost, ensuring all points are part of a minimum spanning tree.


## **Shortest Path Algorithms**

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
