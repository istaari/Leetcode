package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 974: Subarray Sums Divisible by K
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * Given an integer array nums and an integer k, return the number of non-empty
 * subarrays that have a sum divisible by k.
 *
 * Example 1:
 *   Input: nums = [4,5,0,-2,-3,1], k = 5
 *   Output: 7
 *   Explanation: 7 subarrays have a sum divisible by 5:
 *   [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3]
 *
 * Example 2:
 *   Input: nums = [5], k = 9
 *   Output: 0
 *
 * Constraints:
 *   - 1 <= nums.length <= 3 * 10^4
 *   - -10^4 <= nums[i] <= 10^4
 *   - 2 <= k <= 10^4
 *
 * Approach: Prefix Sum + Modulo + HashMap (counting)
 *   - Similar to LC 523, but counts ALL such subarrays instead of just detecting one.
 *   - If prefix_sum[j] % k == prefix_sum[i] % k, then sum(i+1..j) is divisible by k.
 *   - For each remainder, count how many times it appeared before — each is a valid subarray.
 *   - Handle negative remainders: if remainder < 0, add k.
 *   - Initialize map with {0: 1} to count subarrays starting from index 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, k))
 */
public class SubarraySumsDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum += num;
            int remainder = sum % k;

            // Handle negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            result += map.getOrDefault(remainder, 0);

            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return result;
    }


}
