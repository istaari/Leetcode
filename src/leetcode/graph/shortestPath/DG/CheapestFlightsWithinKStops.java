package leetcode.graph.shortestPath.DG;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * There are n cities connected by some number of flights. You are given an array
 * flights where flights[i] = [fromi, toi, pricei] indicates a flight from city
 * fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k. Return the cheapest price
 * from src to dst with at most k stops. If there is no such route, return -1.
 *
 * Example 1:
 *   Input: n=4, flights=[[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]],
 *          src=0, dst=3, k=1
 *   Output: 700
 *   Explanation: 0 -> 1 -> 3 (cost=100+600=700, 1 stop)
 *                0 -> 1 -> 2 -> 3 would cost 400 but has 2 stops.
 *
 * Example 2:
 *   Input: n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=1
 *   Output: 200  (0 -> 1 -> 2, 1 stop)
 *
 * Example 3:
 *   Input: n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=0
 *   Output: 500  (0 -> 2 directly, 0 stops)
 *
 * Constraints:
 *   1 <= n <= 100
 *   0 <= flights.length <= n * (n - 1) / 2
 *   0 <= src, dst, k < n
 *
 * ---
 * Approach 1: Modified Bellman-Ford
 *
 * Standard Bellman-Ford relaxes all edges (V-1) times.
 * Here, "at most k stops" means at most (k+1) edges.
 * So relax all edges exactly (k+1) times.
 *
 * IMPORTANT: Use a copy of the distance array for each round to prevent
 * "chaining" updates within the same round (which could use more edges).
 *
 * Approach 2: BFS with pruning
 *
 * BFS level by level where each level = one flight (one edge).
 * Track best cost to reach each node. Prune if current cost > best known.
 * Stop after (k+1) levels.
 *
 * Time:  O(K * E) for Bellman-Ford, O(K * E) for BFS
 * Space: O(V) for Bellman-Ford, O(V) for BFS
 */
public class CheapestFlightsWithinKStops {

    // -------------------- Bellman-Ford (K+1 rounds) --------------------
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // dist[i] = cheapest cost to reach city i from src
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges (k+1) times (at most k+1 edges = k stops)
        for (int i = 0; i <= k; i++) {
            // CRITICAL: copy the array to avoid chaining within the same round.
            // Without this, a path could use more than (k+1) edges.
            int[] prev = dist.clone();

            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                // Only relax from the PREVIOUS round's distances
                if (prev[from] != Integer.MAX_VALUE) {
                    dist[to] = Math.min(dist[to], prev[from] + price);
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    // -------------------- BFS (level-by-level) --------------------
    public static int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] f : flights) graph.get(f[0]).add(new int[]{f[1], f[2]});

        // best[i] = cheapest cost to reach city i found so far
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        best[src] = 0;

        // BFS: queue holds (node, costSoFar)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        int stops = 0;

        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0], cost = curr[1];

                for (int[] edge : graph.get(node)) {
                    int next = edge[0], price = edge[1];
                    int newCost = cost + price;

                    // Only explore if this path is cheaper than what we've seen
                    if (newCost < best[next]) {
                        best[next] = newCost;
                        queue.offer(new int[]{next, newCost});
                    }
                }
            }
            stops++;
        }

        return best[dst] == Integer.MAX_VALUE ? -1 : best[dst];
    }

    public static void main(String[] args) {
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("BF:  " + findCheapestPrice(4, flights1, 0, 3, 1));     // 700
        System.out.println("BFS: " + findCheapestPriceBFS(4, flights1, 0, 3, 1));  // 700

        int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println("BF:  " + findCheapestPrice(3, flights2, 0, 2, 1));     // 200
        System.out.println("BFS: " + findCheapestPriceBFS(3, flights2, 0, 2, 1));  // 200

        System.out.println("BF:  " + findCheapestPrice(3, flights2, 0, 2, 0));     // 500
        System.out.println("BFS: " + findCheapestPriceBFS(3, flights2, 0, 2, 0));  // 500
    }
}
