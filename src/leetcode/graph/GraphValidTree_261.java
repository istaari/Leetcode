package leetcode.graph;

public class GraphValidTree_261 {

    private static class unionFind {
        private int[] parent;
        private int[] rank;

        /**
         * Initialize the Union Set
         *
         * @param n number of vertices
         */
        private void initializeUnionSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Find the root of the set
         *
         * @param x vertex
         * @return root of the set
         */
        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Union the two sets
         *
         * @param x vertex
         * @param y vertex
         * @return true if union is successful, false otherwise
         */
        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    /**
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
     * write a function to check whether these edges make up a valid tree.
     *
     * @param n     number of nodes
     * @param edges list of edges
     * @return true if the edges make up a valid tree, false otherwise
     */
    public static boolean validTree(int n, int[][] edges) {
       unionFind uf = new unionFind();
       uf.initializeUnionSet(n);
       for (int[] edge : edges) {
           if (!uf.union(edge[0], edge[1])) {
                return false;
           }
       }
       return true;
    }


    public static void main(String[] args){
        int n = 5;
        int[][] edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(validTree(n, edges)); // true
        edges = new int[][]{ {0, 1},  {1, 2},  {2, 3},  {1, 3},  {1, 4}};
        System.out.println(validTree(n, edges));// false
    }

}
