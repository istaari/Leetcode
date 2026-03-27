package leetcode.dp.kandane;

/**
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Given an integer array nums, find the subarray with the largest sum,
 * and return its sum.
 *
 * Example 1: Input: nums = [-2,1,-3,4,-1,2,1,-5,4] -> Output: 6
 *   Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2: Input: nums = [1] -> Output: 1
 * Example 3: Input: nums = [5,4,-1,7,8] -> Output: 23
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 *
 * ---
 * Approach: Kadane's Algorithm
 *
 * Maintain a running sum. At each element, decide: extend the current
 * subarray or start fresh from the current element.
 *
 * STATE:      sum = max subarray sum ending at current index
 * TRANSITION: sum = max(sum + nums[i], nums[i])
 * ANSWER:     max of all sum values
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class MaxSubarray {

    public static int maxSubArray(int[] nums) {
        int max = nums[0];  // Global maximum subarray sum
        int sum = nums[0];  // Current subarray sum ending at position i

        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray or start a new one at nums[i]
            sum = Math.max(sum + nums[i], nums[i]);
            // Update global maximum
            max = Math.max(sum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, -5, 10};
        System.out.println(maxSubArray(nums));
    }

}
