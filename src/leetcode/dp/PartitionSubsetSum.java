package leetcode.dp;


@SuppressWarnings("all")
public class PartitionSubsetSum {

    public static boolean recursive(int[] nums, int index, int target, Boolean[][] dp) {
        if (target == 0) return true;

        if (index > nums.length - 1) return false;
        if (target < 0) return false;

        if (dp[index][target] != null) return dp[index][target];

        dp[index][target] = recursive(nums, index + 1, target - nums[index], dp)
                || recursive(nums, index + 1, target, dp);

        return dp[index][target];
    }


    public static boolean iterative(int[] nums, int targetSum) {
        boolean[][] dp = new boolean[nums.length + 1][targetSum + 1];

        for (int i = 0; i <= nums.length; i++)
            dp[i][0] = true; // For any set of numbers, you can always form a sum of 0 by taking no elements at all

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= targetSum; j++) {

                if (nums[i - 1] <= j) {
                    // dp[i - 1][j] - can we form excluding current item
                    // dp[i - 1][j - nums[i - 1]] - can we form excluding current item and remaining capacity
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][targetSum];
    }


    public static boolean iterativeOptimized(int[] nums, int targetSum) {
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;

        for (int val : nums) {
            for (int j = targetSum; val <= j; j--) {
                dp[j] = dp[j] || dp[j - val];
            }
        }

        return dp[targetSum];
    }


    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int val : nums) {
            totalSum += val;
        }

        if (totalSum % 2 != 0) return false;
        int targetSum = totalSum / 2;
        Boolean dp[][] = new Boolean[nums.length][targetSum + 1];

        return iterative(nums, targetSum);
        //return topDown(nums, 0, targetSum, dp);
    }


    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }


}
