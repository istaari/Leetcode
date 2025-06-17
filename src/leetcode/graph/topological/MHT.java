package leetcode.graph.topological;

import java.util.*;

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
