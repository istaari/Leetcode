package leetcode.dp.kandane;

/**
 * 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find a subarray that has the largest product,
 * and return the product.
 *
 * Example 1: Input: nums = [2,3,-2,4] -> Output: 6  (subarray [2,3])
 * Example 2: Input: nums = [-2,0,-1] -> Output: 0   (subarray [0])
 *
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   -10 <= nums[i] <= 10
 *
 * ---
 * Approach: Modified Kadane's (track both max and min product)
 *
 * Because a negative number can flip the max/min, we track both.
 * When we encounter a negative number, we swap maxProd and minProd
 * before updating, since multiplying by a negative flips their roles.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class MaxProductSubarray {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProd = nums[0]; // Maximum product ending at current position
        int minProd = nums[0]; // Minimum product ending at current position (needed for negatives)
        int result = nums[0];  // Global maximum product found so far

        for (int i = 1; i < nums.length; i++) {
            // If current number is negative, swap max and min.
            // A large negative min * negative num = large positive max.
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Either start a new subarray at nums[i], or extend the current one
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);

            // Update global result
            result = Math.max(result, maxProd);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = { 2, 3, -2, 4 };
        System.out.println(maxProduct(nums1));

        // Test case 2
        int[] nums2 = { -2, 0, -1 };
        System.out.println(maxProduct(nums2));
    }
}
