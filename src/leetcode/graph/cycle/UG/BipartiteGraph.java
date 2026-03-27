package leetcode.graph.cycle.UG;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 785. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 *
 * There is an undirected graph with n nodes, where each node is numbered
 * between 0 and n-1. You are given a 2D array graph, where graph[u] is an
 * array of nodes that node u is adjacent to.
 *
 * A graph is bipartite if the nodes can be partitioned into two independent
 * sets A and B such that every edge connects a node in A to a node in B.
 *
 * Return true if and only if it is bipartite.
 *
 * Example 1:
 *   Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 *   Output: false
 *   Explanation: No way to partition nodes into two sets so that every edge
 *   crosses sets. (0-1, 0-2, 1-2 form an odd cycle)
 *
 * Example 2:
 *   Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 *   Output: true
 *   Explanation: Partition: A={0,2}, B={1,3}. All edges cross the partition.
 *
 * Constraints:
 *   graph.length == n
 *   1 <= n <= 100
 *   0 <= graph[u].length < n
 *   No self-edges. No parallel edges. If v ∈ graph[u], then u ∈ graph[v].
 *
 * ---
 * Key insight: A graph is bipartite ⟺ it contains no odd-length cycle.
 *
 * Approach: BFS / DFS graph coloring
 *
 * Try to 2-color the graph:
 *   - Pick an uncolored node, color it RED.
 *   - All its neighbors must be BLUE, their neighbors RED, etc.
 *   - If we ever try to color a node that's already the WRONG color -> not bipartite.
 *
 * Must handle disconnected components: iterate over all nodes, run BFS/DFS
 * on each unvisited component.
 *
 * Time:  O(V + E)
 * Space: O(V)
 */
public class BipartiteGraph {

    // -------------------- BFS (2-coloring) --------------------
    public static boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        // color[i]: 0 = unvisited, 1 = color A, -1 = color B
        int[] color = new int[n];

        // Handle disconnected components
        for (int i = 0; i < n; i++) {
            if (color[i] != 0) continue; // Already colored

            // BFS from node i
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            color[i] = 1; // Start with color A

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor : graph[node]) {
                    if (color[neighbor] == 0) {
                        // Unvisited: assign opposite color
                        color[neighbor] = -color[node];
                        queue.offer(neighbor);
                    } else if (color[neighbor] == color[node]) {
                        // Same color as current node -> odd cycle -> not bipartite
                        return false;
                    }
                    // else: already colored with the correct opposite color, skip
                }
            }
        }

        return true;
    }

    // -------------------- DFS (2-coloring) --------------------
    public static boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = unvisited, 1 = color A, -1 = color B

        for (int i = 0; i < n; i++) {
            // If unvisited, try to 2-color the component starting from node i
            if (color[i] == 0 && !dfs(graph, color, i, 1)) {
                return false;
            }
        }

        return true;
    }

    private static boolean dfs(int[][] graph, int[] color, int node, int c) {
        color[node] = c;

        for (int neighbor : graph[node]) {
            if (color[neighbor] == 0) {
                // Unvisited: color with opposite and recurse
                if (!dfs(graph, color, neighbor, -c)) {
                    return false;
                }
            } else if (color[neighbor] == c) {
                // Conflict: neighbor has the same color
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Example 1: Not bipartite (triangle 0-1-2)
        int[][] graph1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println("BFS: " + isBipartiteBFS(graph1)); // false
        System.out.println("DFS: " + isBipartiteDFS(graph1)); // false

        // Example 2: Bipartite (even cycle 0-1-2-3)
        int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println("BFS: " + isBipartiteBFS(graph2)); // true
        System.out.println("DFS: " + isBipartiteDFS(graph2)); // true
    }
}
