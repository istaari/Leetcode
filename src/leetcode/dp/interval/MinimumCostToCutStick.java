package leetcode.dp.interval;

import java.util.Arrays;


@SuppressWarnings("all")
public class MinimumCostToCutStick {

    public static int helper(int left, int right, int[] cuts, int[][] dp) {
        if (left + 1 == right) return 0;

        if (dp[left][right] != -1) return dp[left][right];

        int minCost = Integer.MAX_VALUE;

        for (int k = left + 1; k < right; k++) {
            minCost = Math.min(minCost, cuts[right] - cuts[left]
                    + helper(left, k, cuts, dp) // Left Segment
                    + helper(k, right, cuts, dp)); // Right Segment
        }

        dp[left][right] = minCost;

        return dp[left][right];
    }

    public static int recursive(int n, int[] cuts) {
        //Include the boundaries and sort the cut
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
