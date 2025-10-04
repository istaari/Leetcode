package dsa.graph;

import java.util.Arrays;

//Purpose: Finds the shortest paths between all pairs of nodes.
//Use Case: You want to know the shortest distance from every node to every other node.
//Works with: Positive and negative weights (but no negative cycles).
//Time Complexity: O(V³)
public class FloydWarshall {

    final static int INF = 99999; // Represents infinity
    final static int V = 4;       // Number of vertices

    public int[][] floydWarshall(int[][] graph) {
        int[][] dist = new int[V][V];

        // Initialize the solution matrix same as input graph matrix
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, V);
        }

        // Update dist[][] considering each vertex as an intermediate
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Check if the path through vertex k is shorter
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
