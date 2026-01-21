package leetcode.graph.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {

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
     * Manhattan Distance between two points
     *
     * @param p1 point 1
     * @param p2 point 2
     * @return manhattan distance
     */
    private static int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    //---------------------------------------------kruskal's Algorithm---------------------------------------------//

    /**
     * Kruskal's Algorithm
     *
     * @param points points
     * @return minimum cost to connect all points
     */
    private static int kruskal(int[][] points) {
        int vertex = points.length;  // Number of vertices
        List<int[]> edges = new ArrayList<>();

        // Step 1: Generate all edges with their Manhattan distances
        for (int i = 0; i < vertex; i++) {
            for (int j = i + 1; j < vertex; j++) {
                int dist = manhattanDistance(points[i], points[j]);
                edges.add(new int[]{i, j, dist});
            }
        }

        /*  Step 2: Sort edges by distance */
        edges.sort((a, b) -> Integer.compare(a[2], b[2]));  // a[2] and b[2] are distances

        /* Step 3. Create a Union Find data structure to detect cycle */
        UnionFind uf = new UnionFind(vertex);
        int minCost = 0;

        /*Step 4. Spanning tree always will have total vertex - 1 edges */
        int minimumSpanningTree = vertex - 1;

        /*Step 5. Traverse through the edges and add the cost of edge if it doesn't form a cycle */
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            // Add the edge if there is no cycle
            if (uf.union(u, v)) {
                minCost += cost;
                minimumSpanningTree--;

                if (minimumSpanningTree == 0) break;
            }
        }

        return minCost;
    }

    //---------------------------------------------Prims Algorithm---------------------------------------------//

    private static int prims(int[][] points) {
        int n = points.length;

        // 1. Create a priority queue that will store the minimum cost edge for each point.
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // push the first vertex with cost 0 (the source vertex)
        priorityQueue.add(new int[]{0, 0}); // {cost, vertex index}

        // 2. Create an array to store the minimum cost to connect each vertex.
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        minCost[0] = 0;

        // 3. Create a visited array to check whether a vertex has been added to the MST.
        boolean[] visited = new boolean[n];
        int totalCost = 0;

        // 4. Iterate through the priority queue to process all vertices.
        while (!priorityQueue.isEmpty()) {

            // Get the vertex with the smallest edge weight.
            int[] current = priorityQueue.poll();
            int cost = current[0];
            int u = current[1];

            // If this vertex is already visited, continue.
            if (visited[u]) continue;
            visited[u] = true;

            // Add the current cost to the total cost (this is part of the MST).
            totalCost += cost;

            // 5. For each neighboring vertex, calculate the Manhattan distance.
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int newCost = manhattanDistance(points[u], points[v]);

                    // If this edge offers a smaller cost, update the priority queue and the minCost array.
                    if (newCost < minCost[v]) {
                        minCost[v] = newCost;
                        priorityQueue.add(new int[]{newCost, v});
                    }
                }
            }
        }

        return totalCost;
    }


    public static int minCostConnectPoints(int[][] points) {
        return prims(points);
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(kruskal(points));
        System.out.println(prims(points));
    }

}
