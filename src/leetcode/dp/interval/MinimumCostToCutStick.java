package leetcode.dp.interval;

import java.util.Arrays;

/**
 * 1547. Minimum Cost to Cut a Stick
 * https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
 *
 * Given a wooden stick of length n units. The stick is labelled from 0 to n.
 * Given an integer array cuts where cuts[i] denotes a position you should
 * perform a cut at. The cost of one cut is the length of the stick to be cut.
 * Return the minimum total cost of the cuts. You can change the order of cuts.
 *
 * Example 1: Input: n = 7, cuts = [1,3,4,5] -> Output: 16
 *   Explanation: Order [3,5,1,4] gives cost = 7+4+3+2 = 16
 *
 * Example 2: Input: n = 9, cuts = [5,6,1,4,2] -> Output: 22
 *
 * Constraints:
 *   2 <= n <= 10^6
 *   1 <= cuts.length <= min(n - 1, 100)
 *
 * ---
 * Approach: Interval DP (top-down memoization)
 *
 * Sort cuts and add boundaries [0, n]. Then dp[left][right] = min cost to
 * make all cuts between positions left and right in the sorted cuts array.
 *
 * STATE:      dp[left][right] = min cost to perform all cuts in (left..right)
 * BASE:       dp[left][right] = 0 when left+1 == right (no cuts between them)
 * TRANSITION: For each cut point k between left and right:
 *             dp[left][right] = min(cuts[right]-cuts[left] + dp[left][k] + dp[k][right])
 *
 * Time:  O(m^3) where m = cuts.length
 * Space: O(m^2)
 */
@SuppressWarnings("all")
public class MinimumCostToCutStick {

    // Top-down: find min cost to make all cuts in the segment [left..right]
    public static int helper(int left, int right, int[] cuts, int[][] dp) {
        // Base case: no cuts to make between adjacent positions
        if (left + 1 == right) return 0;

        if (dp[left][right] != -1) return dp[left][right];

        int minCost = Integer.MAX_VALUE;

        // Try every cut point k between left and right
        for (int k = left + 1; k < right; k++) {
            // Cost = length of current segment + cost of left part + cost of right part
            minCost = Math.min(minCost, cuts[right] - cuts[left]
                    + helper(left, k, cuts, dp) // Left Segment
                    + helper(k, right, cuts, dp)); // Right Segment
        }

        dp[left][right] = minCost;

        return dp[left][right];
    }

    public static int recursive(int n, int[] cuts) {
        // Include the boundaries (0 and n) and sort all cut positions
        int m = cuts.length;
        int[] newCuts = new int[m + 2];

        for (int i = 1; i <= m; i++) {
            newCuts[i] = cuts[i - 1];
        }

        newCuts[0] = 0;
        newCuts[m + 1] = n;
        Arrays.sort(newCuts);

        int[][] dp = new int[m + 2][m + 2];

        for (int i = 0; i < m + 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Solve for the full range [0..m+1]
        return helper(0, m + 1, newCuts, dp);
    }

    public static int minCost(int n, int[] cuts) {
        return recursive(n, cuts);
    }

    public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1, 3, 4, 5}; // Output: 16

        System.out.println(recursive(n, cuts));
    }


}
