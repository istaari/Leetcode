package leetcode.dp.knapsack;

import java.util.Arrays;

/**
 * 494. Target Sum
 * https://leetcode.com/problems/target-sum/
 *
 * You are given an integer array nums and an integer target. You want to build
 * an expression out of nums by adding one of the symbols '+' or '-' before each
 * integer in nums and then concatenate all the integers.
 *
 * Return the number of different expressions that evaluate to target.
 *
 * Example 1: Input: nums = [1,1,1,1,1], target = 3 -> Output: 5
 *   Explanation: -1 + 1 + 1 + 1 + 1 = 3 (5 ways to pick which one is negative)
 *
 * Example 2: Input: nums = [1], target = 1 -> Output: 1
 *
 * Constraints:
 *   1 <= nums.length <= 20
 *   0 <= nums[i] <= 1000
 *   0 <= sum(nums[i]) <= 1000
 *   -1000 <= target <= 1000
 *
 * ---
 * Approach: 0/1 Knapsack (Subset Sum reduction)
 *
 * Key insight: Partition nums into two subsets P (positive) and N (negative).
 *   P - N = target
 *   P + N = totalSum
 *   => P = (target + totalSum) / 2
 *
 * So the problem reduces to: count subsets of nums that sum to P.
 * This is a classic 0/1 knapsack / subset sum count problem.
 *
 * Edge cases:
 *   - If (target + totalSum) is odd, no valid partition exists → return 0.
 *   - If |target| > totalSum, impossible → return 0.
 *
 * Time:  O(n * P)
 * Space: O(P) with 1D optimization
 */
public class TargetSum {

    /**
     * Top-down recursive with memoization (the direct +/- approach).
     *
     * dp(index, currentSum) = number of ways to reach target from index onward.
     */
    public static int recursive(int[] nums, int target) {
        // Offset currentSum by 1000 to handle negative indices (sum range: -1000 to 1000)
        int offset = Arrays.stream(nums).sum();
        Integer[][] memo = new Integer[nums.length][2 * offset + 1];
        return helper(nums, target, 0, 0, offset, memo);
    }

    private static int helper(int[] nums, int target, int index, int currentSum, int offset, Integer[][] memo) {
        // All numbers used: check if we hit the target
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        if (memo[index][currentSum + offset] != null) {
            return memo[index][currentSum + offset];
        }

        // Two choices: add or subtract the current number
        int add = helper(nums, target, index + 1, currentSum + nums[index], offset, memo);
        int sub = helper(nums, target, index + 1, currentSum - nums[index], offset, memo);

        memo[index][currentSum + offset] = add + sub;
        return add + sub;
    }

    /**
     * Bottom-up 0/1 Knapsack (Subset Sum Count)
     *
     * Subproblem: dp[j] = number of subsets that sum to j
     *
     * Example trace: nums = [1,1,1,1,1], target = 3
     *   totalSum = 5, P = (3+5)/2 = 4
     *   Count subsets summing to 4:
     *
     *   dp after init:  [1, 0, 0, 0, 0]
     *   after num=1:    [1, 1, 0, 0, 0]
     *   after num=1:    [1, 2, 1, 0, 0]
     *   after num=1:    [1, 3, 3, 1, 0]
     *   after num=1:    [1, 4, 6, 4, 1]
     *   after num=1:    [1, 5,10,10, 5]  → dp[4] = 5  ✓
     */
    public static int iterative(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Edge cases: impossible to partition
        if (Math.abs(target) > totalSum) return 0;
        if ((target + totalSum) % 2 != 0) return 0;

        int subsetSum = (target + totalSum) / 2;

        // 1D DP: dp[j] = number of subsets summing to j
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1; // Base: empty subset sums to 0

        for (int num : nums) {
            // Traverse RIGHT to LEFT to avoid using the same element twice (0/1 knapsack)
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num]; // Include num: ways to make (j - num) carry over
            }
        }

        return dp[subsetSum];
    }

    /**
     * 2D Knapsack (for clarity, same logic as iterative but unoptimized)
     *
     * dp[i][j] = number of subsets using first i numbers that sum to j
     *
     * Transition:
     *   dp[i][j] = dp[i-1][j]                    (exclude nums[i-1])
     *            + dp[i-1][j - nums[i-1]]         (include nums[i-1], if j >= nums[i-1])
     */
    public static int iterative2D(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (Math.abs(target) > totalSum) return 0;
        if ((target + totalSum) % 2 != 0) return 0;

        int subsetSum = (target + totalSum) / 2;
        int n = nums.length;

        int[][] dp = new int[n + 1][subsetSum + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= subsetSum; j++) {
                // Exclude current number
                dp[i][j] = dp[i - 1][j];
                // Include current number (if it fits)
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }

        return dp[n][subsetSum];
    }

    public static int findTargetSumWays(int[] nums, int target) {
        return iterative(nums, target);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        System.out.println("Recursive:  " + recursive(nums1, 3));    // 5
        System.out.println("Iterative:  " + iterative(nums1, 3));    // 5
        System.out.println("2D:         " + iterative2D(nums1, 3));  // 5

        int[] nums2 = {1};
        System.out.println("Recursive:  " + recursive(nums2, 1));    // 1
        System.out.println("Iterative:  " + iterative(nums2, 1));    // 1
    }
}
