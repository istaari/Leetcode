package leetcode.graph.cycle.directedGraph;

import java.util.Arrays;

@SuppressWarnings("all")
public class RedundantConnectionUnionFind {

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
