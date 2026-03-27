package leetcode.graph.shortestPath.DG;

import java.util.*;

/**
 * 743. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 *
 * You are given a network of n nodes, labeled from 1 to n. You are also given
 * times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node, and wi is the time it
 * takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes
 * for all n nodes to receive the signal. If it is impossible for all nodes to
 * receive the signal, return -1.
 *
 * Example 1:
 *   Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 *   Output: 2
 *   Explanation: Node 2 sends signal:
 *     t=0: node 2
 *     t=1: node 1, node 3
 *     t=2: node 4 (from node 3)
 *     All nodes reached by t=2.
 *
 * Example 2:
 *   Input: times = [[1,2,1]], n = 2, k = 2
 *   Output: -1  (node 1 can't reach node 2 but signal starts at 2, node 1 unreachable)
 *
 * Constraints:
 *   1 <= k <= n <= 100
 *   1 <= times.length <= 6000
 *   1 <= ui, vi <= n, 0 <= wi <= 100
 *
 * ---
 * Approach: Dijkstra's Algorithm (single-source shortest path)
 *
 * This is the textbook Dijkstra problem:
 * 1. Build adjacency list from directed edges with weights.
 * 2. Use a min-heap (PriorityQueue) keyed by distance.
 * 3. Start from node k with distance 0.
 * 4. Greedily expand the closest unvisited node, relax its neighbors.
 * 5. Answer = max distance among all nodes (time for the last node to be reached).
 *
 * Why Dijkstra works: All edge weights are non-negative.
 *
 * Time:  O(E log V) with binary heap
 * Space: O(V + E)
 */
public class NetworkDelayTime {

    // -------------------- Dijkstra's Algorithm --------------------
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list: node -> [(neighbor, weight), ...]
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        // dist[i] = shortest distance from k to i
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min-heap: (distance, node). Sorted by distance.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];   // Current distance
            int u = curr[1];   // Current node

            // Skip if we already found a shorter path to u
            // (lazy deletion: stale entries remain in the heap)
            if (d > dist[u]) continue;

            // Relax all neighbors of u
            for (int[] edge : graph.get(u)) {
                int v = edge[0];      // Neighbor
                int w = edge[1];      // Edge weight
                int newDist = d + w;

                // If we found a shorter path to v through u
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{newDist, v});
                }
            }
        }

        // The answer = max distance to any node (time for last node to be reached)
        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1; // Node unreachable
            maxDist = Math.max(maxDist, dist[i]);
        }

        return maxDist;
    }

    // -------------------- Bellman-Ford (alternative) --------------------
    /**
     * Bellman-Ford: Relax all edges (V-1) times.
     * Simpler but slower than Dijkstra. Handles negative weights (not needed here).
     *
     * Time:  O(V * E)
     * Space: O(V)
     */
    public static int networkDelayTimeBF(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Relax all edges (n-1) times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : times) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist[i]);
        }
        return maxDist;
    }

    public static void main(String[] args) {
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println("Dijkstra:     " + networkDelayTime(times1, 4, 2));   // 2
        System.out.println("Bellman-Ford: " + networkDelayTimeBF(times1, 4, 2)); // 2

        int[][] times2 = {{1, 2, 1}};
        System.out.println("Dijkstra:     " + networkDelayTime(times2, 2, 2));   // -1
    }
}
