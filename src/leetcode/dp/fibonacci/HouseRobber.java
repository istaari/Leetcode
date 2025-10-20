package leetcode.dp.fibonacci;

/**
 * LeetCode Problem: 198. House Robber
 * <p>
 * Question:
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * The only constraint stopping you from robbing each of them is that adjacent houses have security systems connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money
 * you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [1, 2, 3, 1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 400
 */
public class HouseRobber {

    /**
     * Solves the problem using a recursive, top-down DP approach with memoization.
     *
     * @param nums The array of money in each house.
     * @param i    The index of the current house being considered (from the end).
     * @param memo The memoization array to store results of subproblems.
     * @return The maximum amount of money that can be robbed up to house `i`.
     */
    public static int recursive(int[] nums, int i, int[] memo) {
        // Base case: If we are out of bounds (no more houses), the profit is 0.
        if (i < 0) {
            return 0;
        }

        // Memoization check: If we've already calculated the max profit for robbing up to house `i`, return it.
        if (memo[i] != -1) {
            return memo[i];
        }

        // --- The Core Choice ---
        // At house `i`, we have two options:
        // 1. Rob this house: The profit is nums[i] + the max profit from robbing houses up to `i-2` (since we can't rob `i-1`).
        // 2. Don't rob this house: The profit is the max profit from robbing houses up to `i-1`.

        // We choose the option that gives us the maximum profit.
        int robThisHouse = nums[i] + recursive(nums, i - 2, memo);
        int skipThisHouse = recursive(nums, i - 1, memo);

        // Store the result in our memoization table before returning.
        memo[i] = Math.max(robThisHouse, skipThisHouse);
        return memo[i];
    }


    /**
     * Solves the problem using an iterative, bottom-up DP approach.
     *
     * @param nums The array of money in each house.
     * @return The maximum amount of money that can be robbed.
     */
    public static int iterative(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i] will store the maximum money that can be robbed from the first `i` houses.
        // We use n+1 size for easier indexing, where dp[0] is a dummy base case.
        int n = nums.length + 1;
        int[] dp = new int[n];

        // Base Cases:
        // dp[0] = 0: Max money from 0 houses is 0.
        // dp[1] = nums[0]: Max money from the first house is just its own value.
        dp[0] = 0;
        dp[1] = nums[0];

        // Build the solution from the second house onwards.
        // Note: i corresponds to the house at nums[i-1].
        for (int i = 2; i < n; i++) {
            // --- The Core Choice (Recurrence Relation) ---
            // The logic is the same as the recursive approach. To find the max profit for the first `i` houses (dp[i]):
            // 1. Rob house `i-1`: Profit = nums[i-1] + max profit from the first `i-2` houses (dp[i-2]).
            // 2. Don't rob house `i-1`: Profit = max profit from the first `i-1` houses (dp[i-1]).
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        // The final answer is the max profit considering all houses (up to n-1).
        return dp[n - 1];
    }


    static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println("Max profit for [1, 2, 3, 1]: " + iterative(nums)); // Expected: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Max profit for [2, 7, 9, 3, 1]: " + iterative(nums2)); // Expected: 12
    }
}