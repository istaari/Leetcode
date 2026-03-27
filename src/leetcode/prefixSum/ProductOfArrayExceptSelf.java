package leetcode.prefixSum;

/**
 * LeetCode 238: Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all elements of nums except nums[i].
 * You must solve it without using division and in O(n) time.
 *
 * Example 1:
 *   Input: nums = [1,2,3,4]
 *   Output: [24,12,8,6]
 *
 * Example 2:
 *   Input: nums = [-1,1,0,-3,3]
 *   Output: [0,0,9,0,0]
 *
 * Constraints:
 *   - 2 <= nums.length <= 10^5
 *   - -30 <= nums[i] <= 30
 *   - The product of any prefix or suffix fits in a 32-bit integer
 *
 * Approach: Prefix and Suffix Products
 *   - First pass (left to right): build prefix product in result array.
 *     result[i] = product of all elements before i.
 *   - Second pass (right to left): multiply each result[i] by suffix product.
 *     This gives product of all elements except self.
 *   - No extra arrays needed — use result array for prefix + a running variable for suffix.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) — output array not counted
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Left pass: result[i] = product of all elements to the left of i
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Right pass: multiply by product of all elements to the right of i
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = new ProductOfArrayExceptSelf().productExceptSelf(nums);

        for (int val : result) {
            System.out.print(val + " "); // 24 12 8 6
        }
    }
}
