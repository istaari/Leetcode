package leetcode.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim's Algorithm — Minimum Spanning Tree (MST)
 *
 * PROBLEM IT SOLVES:
 *   Given a weighted, undirected, connected graph, find the MST — the subset
 *   of edges connecting all vertices with minimum total weight and no cycles.
 *
 * WHEN TO USE:
 *   - Finding MST, especially for dense graphs (adjacency list/matrix input).
 *   - When you grow the MST from a starting vertex outward.
 *   - Problems: Min Cost to Connect All Points (LC 1584), etc.
 *
 * ALGORITHM STEPS:
 *   Step 1: Start from an arbitrary vertex (vertex 0). Add it to a min-heap
 *           with weight 0. Mark all vertices as unvisited.
 *   Step 2: While the heap is not empty:
 *       a) Poll the edge with the smallest weight from the heap.
 *       b) If the destination vertex is already visited, skip (prevents cycles).
 *       c) Mark the vertex as visited (it's now part of the MST).
 *       d) Record this edge in the result.
 *       e) For each unvisited neighbour of this vertex, add the edge to the heap.
 *   Step 3: The result contains V edges (including the dummy start edge).
 *           The MST has V-1 real edges with minimum total weight.
 *
 * WHY IT WORKS (Greedy + Cut Property):
 *   At each step, we pick the cheapest edge crossing the cut between
 *   "MST vertices" and "non-MST vertices". The cut property guarantees
 *   this edge is safe to include in the MST.
 *
 * KEY DIFFERENCE FROM KRUSKAL:
 *   - Prim grows the MST from one vertex, expanding outward (vertex-centric).
 *   - Kruskal processes global edges in weight order (edge-centric).
 *
 * TIME:  O((V + E) log V) with binary heap.
 * SPACE: O(V + E) for adjacency list + heap + visited array.
 */
@SuppressWarnings("all")
public class Prims {
    public List<List<Edge>> graph;
    public int V;

    public Prims(int v) {
        this.V = v;
        this.graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static class Edge {
        public int dest;
        public int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public void addEdge(int src, int dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    public List<Edge> findMST() {
        boolean[] visited = new boolean[V];

        // Min-heap: always gives us the cheapest available edge.
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

        // Step 1: Start from vertex 0 with a dummy edge of weight 0.
        pq.add(new Edge(0, 0));
        List<Edge> result = new ArrayList<>();

        // Step 2: Greedily pick the cheapest edge that connects to an unvisited vertex.
        while (!pq.isEmpty()) {
            // Step 2a: Poll the minimum-weight edge from the heap.
            Edge edge = pq.poll();

            // Step 2b: If destination is already in MST, skip (prevents cycles).
            if (visited[edge.dest]) continue;

            // Step 2c: Mark this vertex as part of the MST.
            visited[edge.dest] = true;

            // Step 2d: Record the edge (including the start edge with weight 0).
            result.add(new Edge(edge.dest, edge.weight));

            // Step 2e: Add all edges to unvisited neighbours as candidates.
            for (Edge neighbor : graph.get(edge.dest)) {
                if (!visited[neighbor.dest]) {
                    pq.add(new Edge(neighbor.dest, neighbor.weight));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Prims prim = new Prims(3);
        // A=0, B=1, C=2
        prim.addEdge(0, 1, 2);
        prim.addEdge(1, 0, 2);

        prim.addEdge(1, 2, 1);
        prim.addEdge(2, 1, 1);

        prim.addEdge(0, 2, 3);
        prim.addEdge(2, 0, 3);

        List<Edge> mst = prim.findMST();
        int totalWeight = 0;
        for (Edge e : mst) {
            System.out.println("Connected to: " + e.dest + ", Weight: " + e.weight);
            totalWeight += e.weight;
        }

        System.out.println("Total Weight of MST: " + totalWeight);
    }
}
