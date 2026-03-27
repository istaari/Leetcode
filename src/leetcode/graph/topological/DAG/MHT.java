package leetcode.graph.topological.DAG;

import java.util.*;

/**
 * 310. Minimum Height Trees
 * https://leetcode.com/problems/minimum-height-trees/
 *
 * A tree is an undirected graph in which any two vertices are connected by
 * exactly one path. Any connected graph without cycles is a tree.
 *
 * Given a tree of n nodes labeled from 0 to n-1, and an array of n-1 edges,
 * you can choose any node as the root. The height of the rooted tree is the
 * number of edges on the longest downward path from root to a leaf.
 *
 * Return a list of all MHTs' root labels. The answer can be returned in any order.
 *
 * Example 1:
 *   Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 *   Output: [1]
 *
 * Example 2:
 *   Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 *   Output: [3,4]
 *
 * Constraints:
 *   1 <= n <= 2 * 10^4
 *   edges.length == n - 1
 *
 * ---
 * Approach: Topological peeling (leaf-removal BFS)
 *
 * Idea: The root of an MHT is the "center" of the tree — the node(s) farthest
 * from all leaves. Repeatedly peel off leaf nodes (degree 1) layer by layer.
 * The last 1 or 2 remaining nodes are the MHT roots.
 *
 * Why at most 2? A tree has at most 2 centers (on the diameter path).
 *
 * 1. Build adjacency list, find all leaves (degree == 1).
 * 2. Remove leaves, update neighbors' degrees. New leaves go into next round.
 * 3. Stop when remainingNodes <= 2.
 *
 * Time:  O(N)
 * Space: O(N)
 */
public class MHT {

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n == 1) return Collections.singletonList(0);

        // 1. Build Graph
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 2. Initialize queue with 1 degree or connection
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) queue.add(i);
        }

        // 3. Remove the leaf from node
        int remainingNodes = n;
        while (remainingNodes > 2) {
            int size = queue.size();
            remainingNodes = remainingNodes - size;

            for (int i = 0; i < size; i++) {

                assert queue.peek() != null;

                int leaf = queue.poll();
                int neighbor = graph.get(leaf).get(0);

                graph.get(neighbor).remove(Integer.valueOf(leaf));

                if (graph.get(neighbor).size() == 1) {
                    queue.add(neighbor);
                }
            }
        }

        return queue.stream().toList();
    }


    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(findMinHeightTrees(n, edges));
    }


}
