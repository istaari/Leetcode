package leetcode.graph.cycle.DG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 684. Redundant Connection
 * https://leetcode.com/problems/redundant-connection/
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * You are given a graph that started as a tree with n nodes (1 to n), with one
 * additional edge added. The added edge connects two vertices and is not a duplicate.
 *
 * Return an edge that can be removed so that the resulting graph is a tree.
 * If there are multiple answers, return the answer that occurs last in the input.
 *
 * Example 1:
 *   Input: edges = [[1,2],[1,3],[2,3]]
 *   Output: [2,3]
 *
 * Example 2:
 *   Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 *   Output: [1,4]
 *
 * Constraints:
 *   n == edges.length
 *   3 <= n <= 1000
 *   There are no repeated edges or self-loops.
 *
 * ---
 * Approach: DFS Cycle Detection
 *
 * Build the graph incrementally. After adding each edge, run DFS to check
 * for a cycle. The first edge that creates a cycle is the redundant one.
 *
 * Time:  O(N^2) — DFS after each edge insertion
 * Space: O(N)
 */
public class RedundantConnectionDFS {

    private static boolean hasCycle(int current, int parent, boolean[] visited, List<List<Integer>> graph) {
        visited[current] = true;

        for (int neighbor : graph.get(current)) {
            // Skip the edge that connects to the parent node
            if (neighbor == parent) {
                continue;
            }
            // If we found a visited node that's not our parent, we have a cycle
            if (visited[neighbor]) {
                return true;
            }
            // Recursively check the unvisited neighbor
            if (hasCycle(neighbor, current, visited, graph)) {
                return true;
            }
        }

        return false;
    }

    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adjacencyList = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);

            boolean[] visited = new boolean[n + 1];

            if (hasCycle(u, -1, visited, adjacencyList)) {
                return edge;
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {
        int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[] result = findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }

}
