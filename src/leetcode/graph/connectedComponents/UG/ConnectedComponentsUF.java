package leetcode.graph.connectedComponents.UG;

public class ConnectedComponentsUF {

    private static class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        /**
         * Initialize the Union Set
         *
         * @param n number of vertices
         */
        private UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
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
         */
        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            count--;
        }
    }


    /**
     * Count the number of connected components in the graph
     *
     * @param n     number of vertices
     * @param edges edges of the graph
     * @return number of connected components
     */
    public static int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count;
    }


    public static void main(String[] args){
       int  n = 5;
       int[][] edges = new int[][] {{0, 1}, {1, 2}, {3, 4}};
       System.out.println(countComponents(n, edges)); // 2
       edges = new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
       System.out.println(countComponents(n, edges)); // 1
    }

}
