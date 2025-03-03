package leetcode.graph.cycle.undirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedundantConnectionDFS {

    // Cycle Detection in an Undirected Graph using DFS (Depth-First Search) with Parent Tracking.
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
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] result = findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }

}
