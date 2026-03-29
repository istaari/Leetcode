package leetcode.graph.algo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bellman-Ford Algorithm — Single-Source Shortest Path
 *
 * PROBLEM IT SOLVES:
 *   Given a weighted directed graph and a source vertex, find the shortest
 *   distance from the source to every other vertex. Unlike Dijkstra, it
 *   handles NEGATIVE edge weights and can DETECT negative-weight cycles.
 *
 * WHEN TO USE:
 *   - Graph has negative edge weights (Dijkstra fails here).
 *   - You need to detect negative-weight cycles.
 *   - Problems like "Cheapest Flights Within K Stops" (LC 787).
 *
 * ALGORITHM STEPS:
 *   Step 1: Initialize dist[] array — set source to 0, everything else to INF.
 *   Step 2: Relax ALL edges (V - 1) times.
 *           - Why V-1? The shortest path between any two vertices has at most V-1 edges.
 *           - In each iteration, at least one vertex gets its correct shortest distance.
 *           - Relaxation: if dist[u] + weight(u,v) < dist[v], update dist[v].
 *   Step 3: Do one MORE pass over all edges (the Vth pass).
 *           - If any distance still decreases, a negative-weight cycle exists.
 *           - Because in a cycle-free graph, V-1 passes are always enough.
 *
 * TIME:  O(V * E) — V-1 passes, each scanning all E edges.
 * SPACE: O(V)     — for the dist[] array.
 *
 * COMPARISON:
 *   Dijkstra:      O((V+E) log V), no negative weights, greedy.
 *   Bellman-Ford:   O(V * E),       handles negatives, detects cycles.
 *   Floyd-Warshall: O(V^3),         all-pairs shortest path.
 */
public class BellmanFord {

    public List<Edge> graph;
    public int V;

    public BellmanFord(int v) {
        graph = new ArrayList<>(v);
        V = v;
    }

    public void addEdge(int s, int d, int w) {
        graph.add(new Edge(s, d, w));
    }

    //1. Directed Weighted Edge
    public static class Edge {
        public int src, dest, weight;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public int[] bellmanFord(int source) {
        // Step 1: Initialize distances — source is 0, everything else is "infinity".
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax ALL edges, repeated (V - 1) times.
        //   Why V-1? In a graph with V vertices, the longest shortest path
        //   (without cycles) can have at most V-1 edges.
        //   Each iteration guarantees at least one more vertex gets its final distance.
        //
        //   Relaxation: if going through edge (u -> v) gives a shorter path
        //   than what we currently know for v, update it.
        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : graph) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Step 3: Negative cycle detection (the Vth pass).
        //   If we can STILL relax an edge after V-1 passes, it means
        //   distances keep decreasing infinitely — a negative cycle exists.
        for (Edge edge : graph) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                throw new IllegalStateException("Negative Weight Cycle Exist");
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        BellmanFord bellmanFord = new BellmanFord(V);

        // Sample Graph
        bellmanFord.addEdge(0, 1, 6);
        bellmanFord.addEdge(0, 4, 7);
        bellmanFord.addEdge(1, 2, 5);
        bellmanFord.addEdge(1, 3, -4);
        bellmanFord.addEdge(1, 4, 8);
        bellmanFord.addEdge(2, 3, -2);
        bellmanFord.addEdge(3, 2, 7);
        bellmanFord.addEdge(4, 2, -3);
        bellmanFord.addEdge(4, 3, 9);

        int[] dist = bellmanFord.bellmanFord(0);
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from 0 to " + i + " is " + dist[i]);
        }
    }


}
