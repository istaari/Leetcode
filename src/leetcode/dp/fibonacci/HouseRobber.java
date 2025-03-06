package leetcode.dp.fibonacci;

public class HouseRobber {

    public static int recursive(int[] nums, int i, int[] memo) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];


        memo[i] = Math.max(nums[i] + recursive(nums, i - 2, memo), recursive(nums, i - 1, memo));
        return memo[i];
    }


    public static int iterative(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length + 1;
        int[] dp = new int[n];

        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(iterative(nums)); // 4
    }
}
