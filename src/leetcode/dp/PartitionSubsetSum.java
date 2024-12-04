package leetcode.dp;


@SuppressWarnings("all")
public class PartitionSubsetSum {

    public static boolean topDown(int[] nums, int index, int targetSum, Boolean[][] dp) {

        if (targetSum == 0) return true;
        if (index > nums.length - 1) return false;
        if (targetSum < 0) return false;

        if (dp[index][targetSum] != null) return dp[index][targetSum];

        boolean canBePartition = topDown(nums, index + 1, targetSum - nums[index], dp)
                || topDown(nums, index + 1, targetSum, dp);

        dp[index][targetSum] = canBePartition;

        return dp[index][targetSum];
    }

    public static boolean bottomUp(int[] nums, int targetSum) {
        boolean[][] dp = new boolean[nums.length + 1][targetSum + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {

            for (int j = 1; j <= targetSum; j++) {

                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }

        return dp[nums.length][targetSum];
    }


    public static boolean bottomUpOptimized(int[] nums, int targetSum) {
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

        return bottomUp(nums, targetSum);

        //return topDown(nums, 0, targetSum, dp);
    }


    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        System.out.println(canPartition(nums));
    }


}
