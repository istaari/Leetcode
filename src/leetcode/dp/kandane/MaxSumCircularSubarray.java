package leetcode.dp.kandane;

/**
 * 918. Maximum Sum Circular Subarray
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 *
 * Given a circular integer array nums, find the maximum possible sum of a
 * non-empty subarray. A circular array means the end connects back to the beginning.
 *
 * Example 1: Input: nums = [1,-2,3,-2] -> Output: 3  (subarray [3])
 * Example 2: Input: nums = [5,-3,5]    -> Output: 10 (subarray [5,5] wrapping around)
 * Example 3: Input: nums = [-3,-2,-3]  -> Output: -2 (subarray [-2])
 *
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 3 * 10^4
 *   -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * ---
 * Approach: Kadane's (max + min variant)
 *
 * Two cases for the maximum subarray:
 *   Case 1: Max subarray does NOT wrap around -> standard Kadane's max
 *   Case 2: Max subarray WRAPS around -> totalSum - minKadane
 *           (removing the minimum middle subarray leaves the max wrapping part)
 *
 * Edge case: if maxAfterWrap == 0, all elements are negative -> use Case 1.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class MaxSumCircularSubarray {

    // Standard Kadane's: find maximum subarray sum
    public static int maxKadane(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    // Inverted Kadane's: find minimum subarray sum
    public static int minKadane(int[] nums) {
        int minSum = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.min(sum + nums[i], nums[i]);
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    public static int maxSubarraySumCircular(int[] nums) {
        // Case 1: max subarray is a normal (non-wrapping) subarray
        int maxKadane = maxKadane(nums);

        // Case 2: max subarray wraps around = totalSum - minSubarray
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int maxAfterWrap = totalSum - minKadane(nums);

        // If maxAfterWrap == 0, all elements are negative; wrapping gives empty array
        // In that case, return the standard Kadane result
        return (maxAfterWrap == 0) ? maxKadane : Math.max(maxKadane, maxAfterWrap);
    }


    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        System.out.println(maxSubarraySumCircular(nums));
    }

}
