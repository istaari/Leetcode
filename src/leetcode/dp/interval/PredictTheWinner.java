package leetcode.dp.interval;


public class PredictTheWinner {

    public static int recursive(int[] nums, int i, int j, Integer[][] dp) {
        if (i == j) return nums[i];

        if (dp[i][j] != null) return dp[i][j];

        int pickLeft = nums[i] - recursive(nums, i + 1, j, dp);
        int pickRight = nums[j] - recursive(nums, i, j - 1, dp);

        dp[i][j] = Math.max(pickLeft, pickRight);

        return dp[i][j];
    }

    public static boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];

        return recursive(nums, 0, n - 1, dp) >= 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2};
        System.out.println(predictTheWinner(nums));
    }

}