package leetcode.dp.linear;

/**
 * LeetCode Problem: 213. House Robber II
 * <p>
 * Question:
 * You are a professional robber planning to rob houses along a street. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Otherwise, the robbing rules are the same as House Robber I:
 * if two adjacent houses are broken into on the same night, the police will be contacted.
 * <p>
 * Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money
 * you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [2, 3, 2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent.
 * The best you can do is rob house 2 (money = 3).
 * <p>
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 1000
 */
public class HouseRobber_2 {

    /**
     * This is a helper function that solves the original House Robber I problem for a linear array.
     * It calculates the maximum amount of money that can be robbed from a given list of houses
     * without robbing adjacent ones.
     *
     * @param nums A linear array of houses.
     * @return The maximum loot possible from this linear arrangement.
     */
    public static int helper(int[] nums) {
        // dp[i] will store the maximum money that can be robbed from the first `i` houses.
        int n = nums.length + 1;
        int[] dp = new int[n];

        // Base Cases
        dp[0] = 0;        // Max money from 0 houses is 0.
        dp[1] = nums[0];  // Max money from the first house is its own value.

        // Build the solution from the second house onwards.
        for (int i = 2; i < n; i++) {
            // Recurrence Relation: The max profit for the first `i` houses is the greater of:
            // 1. Robbing the current house (nums[i-1]) + the max profit from non-adjacent previous houses (dp[i-2]).
            // 2. Not robbing the current house, which means taking the max profit from the previous set of houses (dp[i-1]).
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    /**
     * Solves the House Robber II problem.
     * The core idea is to break the circular dependency by solving two separate linear subproblems.
     *
     * @param nums The circular array of money in each house.
     * @return The maximum amount of money that can be robbed.
     */
    public static int rob(int[] nums) {

        // Base case: No houses to rob.
        if (nums.length == 0) {
            return 0;
        }

        // Base case: Only one house, so rob it.
        if (nums.length == 1) {
            return nums[0];
        }

        // --- The Core Logic for Circular Array ---
        // Since the first and last houses are adjacent, you cannot rob both.
        // This means any optimal solution will either:
        // 1. Include the first house but NOT the last house.
        // 2. Include the last house but NOT the first house.
        // We can solve this by running the standard House Robber algorithm on two linear subarrays:
        //   - Subproblem 1: All houses EXCEPT the last one (index 0 to n-2).
        //   - Subproblem 2: All houses EXCEPT the first one (index 1 to n-1).
        // The final answer is the maximum of these two results.

        // This implementation simulates the two subproblems by temporarily modifying the input array.
        // A cleaner approach might use Arrays.copyOfRange to create new subarrays.

        // --- Subproblem 1: Calculate max loot WITHOUT the first house ---
        int val0 = nums[0]; // Store the original value of the first house
        nums[0] = 0;        // Temporarily set it to 0 to effectively "skip" it.
        int max1 = helper(nums); // Run the linear robber algorithm.
        nums[0] = val0;     // Reset the array to its original state.

        // --- Subproblem 2: Calculate max loot WITHOUT the last house ---
        nums[nums.length - 1] = 0; // Temporarily set the last house to 0 to "skip" it.
        int max2 = helper(nums);    // Run the linear robber algorithm again.

        // The overall maximum is the best result from the two scenarios.
        return Math.max(max1, max2);
    }


    static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println("Max profit for [2, 3, 2]: " + rob(nums)); // Expected: 3

        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Max profit for [1, 2, 3, 1]: " + rob(nums2)); // Expected: 4
    }
}