package leetcode.dp.knapsack;

/**
 * 474. Ones and Zeroes
 * https://leetcode.com/problems/ones-and-zeroes/
 *
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most
 * m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 *   Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 *   Output: 4
 *   Explanation: The largest subset with at most 5 zeroes and 3 ones is
 *   {"10", "0001", "1", "0"}, so the answer is 4.
 *   Other valid but smaller subsets: {"111001"} has 4 zeros and 2 ones.
 *
 * Example 2:
 *   Input: strs = ["10","0","1"], m = 1, n = 1
 *   Output: 2
 *   Explanation: The largest subset is {"0","1"} -> 1 zero, 1 one.
 *
 * Constraints:
 *   1 <= strs.length <= 600
 *   1 <= strs[i].length <= 100
 *   strs[i] consists only of digits '0' and '1'.
 *   1 <= m, n <= 100
 *
 * ---
 * Approach: Multi-dimensional 0/1 Knapsack
 *
 * This is a classic 0/1 knapsack with TWO capacity dimensions:
 *   - Capacity of 0's (m) and capacity of 1's (n).
 *   - Each string is an "item" with cost (zeros, ones) and value 1.
 *
 * STATE:  dp[i][j] = max subset size using at most i zeros and j ones
 * BASE:   dp[0][0] = 0
 * TRANSITION: For each string with (zeros, ones) count:
 *   dp[i][j] = max(dp[i][j], dp[i - zeros][j - ones] + 1)
 *
 * We iterate strings in outer loop, and iterate dp in reverse (like 0/1 knapsack)
 * to ensure each string is used at most once.
 *
 * Time:  O(L * m * n) where L = strs.length
 * Space: O(m * n)
 */
public class OnesAndZeroes {

    // -------------------- Iterative 2D Knapsack --------------------
    public static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] = max strings we can pick with at most i zeros and j ones
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            // Count zeros and ones in current string
            int zeros = 0, ones = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }

            // Iterate in REVERSE to avoid using the same string twice (0/1 knapsack)
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    // Either skip this string or take it
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }

    // -------------------- Recursive + Memoization --------------------
    public static int findMaxFormRecursive(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        // Initialize with -1 to distinguish "not computed" from 0
        for (int[][] layer : memo)
            for (int[] row : layer)
                java.util.Arrays.fill(row, -1);
        return solve(strs, m, n, 0, memo);
    }

    private static int solve(String[] strs, int m, int n, int idx, int[][][] memo) {
        if (idx == strs.length) return 0;
        if (memo[idx][m][n] != -1) return memo[idx][m][n];

        // Count zeros and ones
        int zeros = 0, ones = 0;
        for (char c : strs[idx].toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }

        // Option 1: Skip current string
        int skip = solve(strs, m, n, idx + 1, memo);

        // Option 2: Take current string (if capacity allows)
        int take = 0;
        if (m >= zeros && n >= ones) {
            take = 1 + solve(strs, m - zeros, n - ones, idx + 1, memo);
        }

        memo[idx][m][n] = Math.max(skip, take);
        return memo[idx][m][n];
    }

    public static void main(String[] args) {
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        System.out.println("Example 1: " + findMaxForm(strs1, 5, 3));            // 4
        System.out.println("Recursive: " + findMaxFormRecursive(strs1, 5, 3));   // 4

        String[] strs2 = {"10", "0", "1"};
        System.out.println("Example 2: " + findMaxForm(strs2, 1, 1));            // 2
    }
}
