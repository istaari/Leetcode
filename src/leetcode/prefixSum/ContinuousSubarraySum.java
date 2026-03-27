package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 523: Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 *
 * Given an integer array nums and an integer k, return true if nums has a good subarray.
 * A good subarray is a subarray where:
 *   - its length is at least two, and
 *   - the sum of the elements is a multiple of k.
 *
 * Example 1:
 *   Input: nums = [23,2,4,6,7], k = 6
 *   Output: true
 *   Explanation: [2,4] is a continuous subarray of size 2 whose sum = 6.
 *
 * Example 2:
 *   Input: nums = [23,2,6,4,7], k = 6
 *   Output: true
 *   Explanation: [23,2,6,4,7] sums to 42 which is a multiple of 6.
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - 0 <= nums[i] <= 10^9
 *   - 0 <= sum(nums[i]) <= 2^31 - 1
 *   - 1 <= k <= 2^31 - 1
 *
 * Approach: Prefix Sum + Modulo + HashMap
 *   - If prefix_sum[j] % k == prefix_sum[i] % k, then sum(i+1..j) is divisible by k.
 *   - Store the first index where each remainder appears.
 *   - If the same remainder appears again with index gap >= 2, return true.
 *   - Initialize map with {0: -1} to handle subarrays starting from index 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, k))
 */
public class ContinuousSubarraySum {


    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // To calculate proper length
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;

            if (map.containsKey(mod)) {

                if (i - map.get(mod) >= 2) {
                    return true;
                }

            } else {
                map.put(mod, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
    }
}
