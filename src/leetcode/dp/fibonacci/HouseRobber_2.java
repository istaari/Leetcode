package leetcode.dp.fibonacci;

public class HouseRobber_2 {

    public static int helper(int[] nums) {
        int n = nums.length + 1;
        int[] dp = new int[n];

        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    public static int rob(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int val0 = nums[0]; // skips the first house
        nums[0] = 0;

        int max1 = helper(nums); // find the max loot of without the first house
        nums[0] = val0; // reset the value of the first house

        nums[nums.length - 1] = 0; // skips the last house
        int max2 = helper(nums); // find the max loot of without the last house

        return Math.max(max1, max2); // calculate the maximum
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(rob(nums)); //3
    }

}
