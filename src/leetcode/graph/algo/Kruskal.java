package leetcode.graph.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal's Algorithm — Minimum Spanning Tree (MST)
 *
 * PROBLEM IT SOLVES:
 *   Given a weighted, undirected, connected graph, find the subset of edges
 *   that connects all vertices with the MINIMUM total edge weight and no cycles.
 *   This subset is called the Minimum Spanning Tree.
 *
 * WHEN TO USE:
 *   - Finding MST in sparse graphs (few edges relative to vertices).
 *   - Edge list representation is natural for the input.
 *   - Problems: Min Cost to Connect All Points (LC 1584), etc.
 *
 * ALGORITHM STEPS:
 *   Step 1: Collect all edges into a list.
 *   Step 2: Sort all edges by weight in ascending order.
 *   Step 3: Initialize Union-Find with V components (each vertex is its own set).
 *   Step 4: Iterate through sorted edges, for each edge (u, v, w):
 *       a) Check if u and v are in DIFFERENT components (using Union-Find "find").
 *       b) If YES: add edge to MST and merge their components (using "union").
 *          If NO:  skip the edge (it would create a cycle).
 *       c) Stop once we have V-1 edges (MST of V vertices always has V-1 edges).
 *
 * WHY IT WORKS (Greedy + Cut Property):
 *   The cut property says: for any cut of the graph, the lightest edge crossing
 *   the cut is safe to include in the MST. By processing edges in weight order,
 *   each edge we add is the cheapest that connects two separate components.
 *
 * TIME:  O(E log E) — dominated by sorting. Union-Find ops are ~O(1) amortized.
 * SPACE: O(V + E) — edge list + Union-Find arrays.
 *
 * COMPARISON:
 *   Kruskal: O(E log E), edge-centric, good for sparse graphs.
 *   Prim:    O((V + E) log V) with heap, vertex-centric, good for dense graphs.
 */
public class Kruskal {

    public List<Edge> graph;
    public int V;

    public Kruskal(int v) {
        graph = new ArrayList<>(v);
        V = v;
    }

    public void addEdge(int s, int d, int w) {
        graph.add(new Edge(s, d, w));
    }

    // Step 1: Weighted directed edge representation.
    public static class Edge implements Comparable<Edge> {
        public int src, dest, weight;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        // Step 2: Edges are sorted by weight (ascending) for the greedy approach.
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }

        @Override
        public String toString() {
            return "Edge{" + "src=" + src + ", dest=" + dest + ", weight=" + weight + '}';
        }
    }

    // Step 3: Union-Find (Disjoint Set Union) — used to detect cycles.
    //   find(x): returns the root/representative of x's component. Uses path compression.
    //   union(x, y): merges two components. Uses union by rank for balance.
    //   If find(x) == find(y), adding edge (x,y) would create a cycle.
    public static class UnionFind {

        private final int[] parent;
        private final int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // Find operation with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union (in broad terms, this method adds an edge between two nodes)
        public boolean union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) return false;

            // Attach the smaller rank tree under the root of the larger rank tree
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }

            return true;
        }

    }

    public List<Edge> findMST() {
        List<Edge> result = new ArrayList<>();
        UnionFind unionFind = new UnionFind(V);

        // Step 2: Sort all edges by weight (ascending).
        Collections.sort(graph);

        // Step 4: Iterate through sorted edges and greedily build the MST.
        for (Edge edge : graph) {
            // Step 4a-b: If u and v are in different components, add edge to MST.
            //   union() returns true if they were in different sets (no cycle).
            //   Returns false if they're already connected (would form a cycle).
            if (unionFind.union(edge.src, edge.dest)) {
                result.add(edge);
            }
            // Step 4c: MST is complete when we have exactly V-1 edges.
            if (result.size() == V - 1) break;
        }

        return result;
    }


    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal(4);
        kruskal.addEdge(0, 1, 10);
        kruskal.addEdge(0, 2, 6);
        kruskal.addEdge(0, 3, 5);
        kruskal.addEdge(1, 3, 15);
        kruskal.addEdge(2, 3, 4);

        System.out.println(kruskal.findMST().toString());// Total weight = 4 + 5 + 10 = 19
        
    }


}
