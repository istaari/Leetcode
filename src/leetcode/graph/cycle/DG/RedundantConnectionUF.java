package leetcode.graph.cycle.DG;

import java.util.Arrays;

/**
 * 684. Redundant Connection (Union-Find approach)
 * https://leetcode.com/problems/redundant-connection/
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * You are given a graph that started as a tree with n nodes (1 to n), with one
 * additional edge added. Return an edge that can be removed so that the resulting
 * graph is a tree. If multiple answers, return the one occurring last in input.
 *
 * Example 1: edges = [[1,2],[1,3],[2,3]] -> [2,3]
 * Example 2: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]] -> [1,4]
 *
 * ---
 * Approach: Union-Find
 *
 * Process edges one by one. For each edge (u, v):
 *   - If find(u) == find(v), they're already connected -> this edge creates a cycle.
 *   - Otherwise, union them.
 * The first edge where union fails (same root) is the redundant connection.
 *
 * Time:  O(N * α(N)) ≈ O(N)
 * Space: O(N)
 */
@SuppressWarnings("all")
public class RedundantConnectionUF {

    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        /**
         * Initialize the Union Set
         *
         * @param n number of vertices
         */
        private UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }


        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }


        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return true;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return false;
        }
    }


    /**
     * Find the redundant connection
     *
     * @param edges edges of the graph
     * @return redundant connection
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                return new int[]{edge[0], edge[1]};
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] result = findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }


}
