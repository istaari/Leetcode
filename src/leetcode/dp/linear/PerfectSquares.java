package leetcode.dp.linear;

/**
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/
 *
 * Given an integer n, return the least number of perfect square numbers that
 * sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other
 * words, it is the product of some integer with itself. For example, 1, 4, 9,
 * and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1: Input: n = 12 -> Output: 3  (12 = 4 + 4 + 4)
 * Example 2: Input: n = 13 -> Output: 2  (13 = 4 + 9)
 *
 * Constraints: 1 <= n <= 10^4
 *
 * ---
 * Approach: Unbounded Knapsack / BFS
 *
 * Think of it as: we have "coins" of values 1, 4, 9, 16, 25, ...
 * and we want the minimum number of coins to make amount n.
 * This is exactly the Coin Change problem (LC 322) with square numbers as coins.
 *
 * STATE:  dp[i] = minimum number of perfect squares that sum to i
 * BASE:   dp[0] = 0
 * TRANSITION:
 *   dp[i] = min(dp[i - j*j] + 1) for all j where j*j <= i
 *
 * Trace: n = 12
 *   dp[0]  = 0
 *   dp[1]  = dp[0]+1 = 1            (1)
 *   dp[2]  = dp[1]+1 = 2            (1+1)
 *   dp[3]  = dp[2]+1 = 3            (1+1+1)
 *   dp[4]  = min(dp[3]+1, dp[0]+1) = 1   (4)
 *   dp[5]  = min(dp[4]+1, dp[1]+1) = 2   (4+1)
 *   dp[6]  = min(dp[5]+1, dp[2]+1) = 3   (4+1+1)
 *   dp[7]  = min(dp[6]+1, dp[3]+1) = 4   (4+1+1+1)
 *   dp[8]  = min(dp[7]+1, dp[4]+1) = 2   (4+4)
 *   dp[9]  = min(dp[8]+1, dp[5]+1, dp[0]+1) = 1   (9)
 *   dp[10] = min(dp[9]+1, dp[6]+1, dp[1]+1) = 2   (9+1)
 *   dp[11] = min(dp[10]+1, dp[7]+1, dp[2]+1) = 3  (9+1+1)
 *   dp[12] = min(dp[11]+1, dp[8]+1, dp[3]+1) = 3  (4+4+4)  ✓
 *
 * Time:  O(n * sqrt(n))
 * Space: O(n)
 */
public class PerfectSquares {

    // -------------------- Iterative DP (Unbounded Knapsack) --------------------
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // Try every perfect square j*j that fits
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    // -------------------- BFS (Level-order shortest path) --------------------
    /**
     * BFS treats the problem as a shortest path in an unweighted graph:
     *   - Node 0 is the start, node n is the target.
     *   - From node v, edges go to v + j*j for all valid j.
     *   - BFS level = number of perfect squares used.
     */
    public static int numSquaresBFS(int n) {
        boolean[] visited = new boolean[n + 1];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                // Try adding each perfect square
                for (int j = 1; curr + j * j <= n; j++) {
                    int next = curr + j * j;
                    if (next == n) return level;
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        return level; // Should not reach here for valid input
    }

    public static void main(String[] args) {
        System.out.println("DP  - n=12: " + numSquares(12));       // 3
        System.out.println("BFS - n=12: " + numSquaresBFS(12));    // 3
        System.out.println("DP  - n=13: " + numSquares(13));       // 2
        System.out.println("BFS - n=13: " + numSquaresBFS(13));    // 2
    }
}
