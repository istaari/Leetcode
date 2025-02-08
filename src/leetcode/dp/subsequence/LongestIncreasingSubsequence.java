package leetcode.dp.subsequence;


import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static Integer[][] dp;

    public static int helper(int[] nums, int i, int prevIndex) {
        if (i >= nums.length) return 0;

        if (dp[i][prevIndex + 1] != null) {
            return dp[i][prevIndex + 1];
        }

        int exclude = helper(nums, i + 1, prevIndex);

        int include = 0;
        if (prevIndex == -1 || nums[i] > nums[prevIndex]) {
            include = 1 + helper(nums, i + 1, i);
        }

        dp[i][prevIndex + 1] = Math.max(exclude, include);
        return dp[i][prevIndex + 1];
    }


    public static int recursive(int[] nums) {
        dp = new Integer[nums.length][nums.length + 1];
        return helper(nums, 0, -1);
    }


    public static int iterative(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

    public static int lengthOfLIS(int[] nums) {
        return iterative(nums);
    }


    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(recursive(nums));
    }


}
