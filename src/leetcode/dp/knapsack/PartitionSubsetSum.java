package leetcode.dp.knapsack;

import java.util.Arrays;

/**
 * LeetCode Problem: 416. Partition Equal Subset Sum
 *
 * Question:
 * Given a non-empty array `nums` containing only positive integers, find if the array can be partitioned
 * into two subsets such that the sum of elements in both subsets is equal.
 *
 * Example 1:
 * Input: nums = [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11]. Both have a sum of 11.
 *
 * Example 2:
 * Input: nums = [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into two subsets with equal sum.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 100
 */
@SuppressWarnings("all")
public class PartitionSubsetSum {

    public static boolean recursive(int[] nums, int index, int target, Boolean[][] dp) {
        // Base case: We've reached the target sum exactly.
        if (target == 0) {
            return true;
        }

        // Base cases: We've run out of numbers or overshot the target.
        if (index > nums.length - 1 || target < 0) {
            return false;
        }

        if (dp[index][target] != null) {
            return dp[index][target];
        }

        boolean include = recursive(nums, index + 1, target - nums[index], dp);
        boolean exclude = recursive(nums, index + 1, target, dp);

        dp[index][target] = include || exclude;

        return dp[index][target];
    }

    /**
     * Subproblem: dp[i][j] = True if sum j can be formed using a subset of the first i numbers.
     *
     * Example: If nums = {1, 5, 11, 5} and target = 11:
     * dp[2][6] asks: "Can we make a sum of 6, using a subset of the first two numbers {1, 5}?"
     * The answer would be true (from {1, 5}).
     *
     * dp[2][7] asks: "Can we make a sum of 7, using a subset of {1, 5}?"
     * The answer would be false.
     */
    public static boolean iterative(int[] nums, int targetSum) {
        // dp[i][j] = can we make a sum of `j` using only the first `i` numbers?
        boolean[][] dp = new boolean[nums.length + 1][targetSum + 1];

        // Base case: We can always make a sum of 0 (by taking no elements).
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            int currentNum = nums[i - 1]; // The current number we are considering
            for (int j = 1; j <= targetSum; j++) {

                if (currentNum <= j) {
                    // We have two choices:
                    // 1. Exclude the current number: dp[i - 1][j] (result using numbers before this one)
                    // 2. Include the current number: dp[i - 1][j - currentNum] (result for the remaining sum)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - currentNum];
                } else {
                    // If the number is larger than the target `j`, we can't include it.
                    // So, the result is the same as not having this number.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // The final answer is whether we can make the `targetSum` using all `nums.length` numbers.
        return dp[nums.length][targetSum];
    }


    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int val : nums) {
            totalSum += val;
        }

        // If the total sum is odd, it's impossible to split into two equal halves.
        if (totalSum % 2 != 0) return false;

        // The target sum for our subset is half of the total.
        int targetSum = totalSum / 2;

        // Setup for recursive solution
        Boolean dp[][] = new Boolean[nums.length][targetSum + 1];

        // Call any of the implementations. The iterative one is shown here.
        return iterative(nums, targetSum);
        // return recursive(nums, 0, targetSum, dp);
        // return iterativeOptimized(nums, targetSum);
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // Expected: true
    }
}