package leetcode.graph.algo;

import java.util.Arrays;

/**
 * Floyd-Warshall Algorithm — All-Pairs Shortest Path
 *
 * PROBLEM IT SOLVES:
 *   Given a weighted graph, find the shortest distance between EVERY pair
 *   of vertices. Works with negative weights (but NOT negative cycles).
 *
 * WHEN TO USE:
 *   - You need shortest paths between ALL pairs, not just from one source.
 *   - Graph is dense or small (V is manageable since O(V^3)).
 *   - Problems: detecting negative cycles, transitive closure, etc.
 *
 * ALGORITHM STEPS:
 *   Step 1: Initialize a V x V distance matrix dist[][] from the input graph.
 *           - dist[i][j] = weight of edge i->j if it exists, INF otherwise.
 *           - dist[i][i] = 0 for all vertices.
 *
 *   Step 2: For each INTERMEDIATE vertex k (0 to V-1):
 *       For each source i:
 *         For each destination j:
 *           if dist[i][k] + dist[k][j] < dist[i][j]:
 *             dist[i][j] = dist[i][k] + dist[k][j]
 *
 *   INTUITION: In each iteration of k, we ask:
 *     "Is it shorter to go from i to j through vertex k?"
 *     After considering all V vertices as intermediates, we've found
 *     all shortest paths.
 *
 *   Step 3 (optional): Check the diagonal — if dist[i][i] < 0 for any i,
 *           a negative-weight cycle exists.
 *
 * TIME:  O(V^3) — three nested loops.
 * SPACE: O(V^2) — for the distance matrix.
 *
 * COMPARISON:
 *   Dijkstra (per source): O((V+E) log V) — run V times for all-pairs = O(V(V+E) log V)
 *   Floyd-Warshall:         O(V^3) — simpler, better when graph is dense.
 */
public class FloydWarshall {

    final static int INF = 99999; // Represents infinity
    final static int V = 4;       // Number of vertices

    public int[][] floydWarshall(int[][] graph) {
        // Step 1: Initialize dist[][] as a copy of the input adjacency matrix.
        //   dist[i][j] = direct edge weight from i to j (or INF if no edge).
        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, V);
        }

        // Step 2: Try every vertex k as an INTERMEDIATE node.
        //   For each pair (i, j), check: is the path i -> k -> j shorter
        //   than the current best path i -> j?
        //
        //   After k=0: all shortest paths that may go through vertex 0 are found.
        //   After k=1: paths through vertices {0,1} are found.
        //   ...after k=V-1: all shortest paths are found.
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Relaxation: is going through k cheaper?
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        FloydWarshall floydWarshall = new FloydWarshall();
        int graph[][] = {
                { 0,   5,  INF, 10 },
                { INF, 0,   3,  INF },
                { INF, INF, 0,   1 },
                { INF, INF, INF, 0 }
        };

        floydWarshall.floydWarshall(graph);
        System.out.println(Arrays.deepToString(floydWarshall.floydWarshall(graph)));
    }
}
