package leetcode.graph.connectedComponents.DG;

import java.util.*;

/**
 * 399. Evaluate Division
 * https://leetcode.com/problems/evaluate-division/
 *
 * You are given an array of variable pairs equations and an array of real values
 * values, where equations[i] = [Ai, Bi] and values[i] represent the equation
 * Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the
 * j-th query where you must find the answer for Cj / Dj.
 *
 * Return the answers to all queries. If a single answer cannot be determined,
 * return -1.0.
 *
 * Example 1:
 *   Input: equations = [["a","b"],["b","c"]],
 *          values = [2.0, 3.0],
 *          queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 *   Output: [6.0, 0.5, -1.0, 1.0, -1.0]
 *   Explanation: a/b=2, b/c=3 -> a/c=6, b/a=0.5, a/e=-1 (e unknown)
 *
 * Example 2:
 *   Input: equations = [["a","b"],["b","c"],["bc","cd"]],
 *          values = [1.5, 2.5, 5.0],
 *          queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 *   Output: [3.75, 0.4, 5.0, 0.2]
 *
 * Constraints:
 *   1 <= equations.length <= 20
 *   1 <= queries.length <= 20
 *   All strings are non-empty and consist of lowercase English letters and digits.
 *
 * ---
 * Approach: Directed Weighted Graph + DFS
 *
 * Model as a directed graph with weighted edges:
 *   a/b = 2.0  =>  edge a -> b with weight 2.0
 *                   edge b -> a with weight 0.5 (reciprocal)
 *
 * For query a/c:
 *   Find a path from a to c in the graph. The answer = product of edge weights.
 *   a -> b (2.0) -> c (3.0) => a/c = 2.0 * 3.0 = 6.0
 *
 * This is a connected components problem on a directed weighted graph:
 *   - Variables in the same component can be divided.
 *   - Variables in different components return -1.0.
 *
 * Time:  O(Q * (V + E)) where Q = queries, V = variables, E = equations
 * Space: O(V + E)
 */
public class EvaluateDivision {

    public static double[] calcEquation(List<List<String>> equations,
                                        double[] values,
                                        List<List<String>> queries) {
        // Build directed weighted graph
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            // a/b = val => a -> b (weight=val), b -> a (weight=1/val)
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, val);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / val);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dst)) {
                // Variable doesn't exist in any equation
                result[i] = -1.0;
            } else if (src.equals(dst)) {
                // x / x = 1.0
                result[i] = 1.0;
            } else {
                // DFS to find path from src to dst, multiply edge weights
                Set<String> visited = new HashSet<>();
                result[i] = dfs(graph, src, dst, 1.0, visited);
            }
        }

        return result;
    }

    /**
     * DFS: find path from current to target, accumulating the product of weights.
     *
     * @param graph   adjacency list with weighted edges
     * @param current current node
     * @param target  target node
     * @param product running product of edge weights along the path
     * @param visited set of visited nodes (to avoid cycles)
     * @return the product of weights from current to target, or -1.0 if unreachable
     */
    private static double dfs(Map<String, Map<String, Double>> graph,
                              String current, String target,
                              double product, Set<String> visited) {
        visited.add(current);

        // Check if target is a direct neighbor
        Map<String, Double> neighbors = graph.get(current);
        if (neighbors.containsKey(target)) {
            return product * neighbors.get(target);
        }

        // Explore all unvisited neighbors
        for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
            String neighbor = entry.getKey();
            double weight = entry.getValue();

            if (!visited.contains(neighbor)) {
                double result = dfs(graph, neighbor, target, product * weight, visited);
                if (result != -1.0) return result;
            }
        }

        return -1.0; // Target unreachable from current
    }

    // -------------------- BFS Alternative --------------------
    public static double[] calcEquationBFS(List<List<String>> equations,
                                           double[] values,
                                           List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / values[i]);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);
            result[i] = bfs(graph, src, dst);
        }
        return result;
    }

    private static double bfs(Map<String, Map<String, Double>> graph,
                              String src, String dst) {
        if (!graph.containsKey(src) || !graph.containsKey(dst)) return -1.0;
        if (src.equals(dst)) return 1.0;

        // Queue holds (node, productSoFar)
        Queue<Object[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Object[]{src, 1.0});
        visited.add(src);

        while (!queue.isEmpty()) {
            Object[] curr = queue.poll();
            String node = (String) curr[0];
            double product = (double) curr[1];

            for (Map.Entry<String, Double> entry : graph.get(node).entrySet()) {
                String neighbor = entry.getKey();
                double weight = entry.getValue();

                if (neighbor.equals(dst)) return product * weight;

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(new Object[]{neighbor, product * weight});
                }
            }
        }

        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(
            List.of("a", "c"), List.of("b", "a"),
            List.of("a", "e"), List.of("a", "a"), List.of("x", "x")
        );

        System.out.println("DFS: " + Arrays.toString(calcEquation(equations, values, queries)));
        // [6.0, 0.5, -1.0, 1.0, -1.0]

        System.out.println("BFS: " + Arrays.toString(calcEquationBFS(equations, values, queries)));
        // [6.0, 0.5, -1.0, 1.0, -1.0]
    }
}
