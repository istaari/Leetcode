package leetcode.graph.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra's Algorithm — Single-Source Shortest Path (Non-Negative Weights)
 *
 * PROBLEM IT SOLVES:
 *   Given a weighted graph with NON-NEGATIVE edge weights and a source vertex,
 *   find the shortest distance from the source to every other vertex.
 *
 * WHEN TO USE:
 *   - Shortest path from a single source.
 *   - All edge weights >= 0 (use Bellman-Ford if negatives exist).
 *   - Problems: Network Delay Time (LC 743), Cheapest Flights (LC 787), etc.
 *
 * ALGORITHM STEPS:
 *   Step 1: Initialize dist[] array — source = 0, all others = INF.
 *           Add (source, 0) to a min-heap (priority queue sorted by distance).
 *   Step 2: While the min-heap is not empty:
 *       a) Poll the vertex u with the smallest distance.
 *       b) If u is already visited, skip it (we already found its shortest path).
 *       c) Mark u as visited.
 *       d) For each neighbour v of u, try to RELAX the edge:
 *          if dist[u] + weight(u,v) < dist[v], update dist[v] and push (v, newDist) to heap.
 *   Step 3: dist[] now contains shortest distances from source to all vertices.
 *
 * WHY GREEDY WORKS:
 *   With non-negative weights, the vertex with the smallest current distance
 *   is guaranteed to have its final shortest distance. No future path through
 *   other vertices can be shorter (because all remaining edges add >= 0).
 *
 * TIME:  O((V + E) log V) with min-heap (binary heap).
 * SPACE: O(V + E) for adjacency list + dist[] + heap.
 */
public class Dijkstra {
    public List<List<Edge>> graph;
    public static int V;

    public Dijkstra(int v) {
        this.V = v;
        this.graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
    }


    public void addEdge(int src, int dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    public static class Edge {
        public int dest;
        public int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public int[] dijkstra(int source) {
        boolean[] visited = new boolean[V];

        // Step 1: Initialize — source distance = 0, all others = infinity.
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Min-heap stores {vertex, distance}. Sorted by distance (ascending).
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{source, 0});

        // Step 2: Greedily process the closest unvisited vertex.
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            // Step 2b: If already visited, skip (stale entry in heap).
            if (visited[u]) continue;

            // Step 2c: Mark as visited — u's shortest distance is now finalized.
            visited[u] = true;

            // Step 2d: Relax all outgoing edges from u.
            //   If going through u provides a shorter path to v, update dist[v].
            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // Relaxation: dist[v] = min(dist[v], dist[u] + weight)
                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // Step 3: dist[] now holds the shortest distances from source.
        return dist;
    }


    public static void main(String[] args) {
        Dijkstra graph1 = new Dijkstra(6);
        graph1.addEdge(0, 1, 2);
        graph1.addEdge(0, 2, 4);
        graph1.addEdge(1, 2, 1);
        graph1.addEdge(1, 3, 7);
        graph1.addEdge(2, 4, 3);
        graph1.addEdge(3, 5, 1);
        graph1.addEdge(4, 3, 2);
        graph1.addEdge(4, 5, 5);

        int source1 = 0;
        int[] distances1 = graph1.dijkstra(source1);

        System.out.println("Shortest distances from vertex " + source1 + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + distances1[i]);
        }

        
    }
}




