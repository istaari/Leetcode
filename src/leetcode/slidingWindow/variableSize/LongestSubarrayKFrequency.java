package leetcode.slidingWindow.variableSize;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2958: Length of Longest Subarray With at Most K Frequency
 * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/
 *
 * You are given an integer array nums and an integer k. The frequency of an
 * element x is the number of times it occurs in an array. Return the length of
 * the longest good subarray where the frequency of each element is <= k.
 *
 * Example 1:
 *   Input: nums = [1,2,3,1,2,3,1,2], k = 2
 *   Output: 6 — [1,2,3,1,2,3] has each element appearing at most 2 times.
 *
 * Example 2:
 *   Input: nums = [1,2,1,2,1,2,1,2], k = 1
 *   Output: 2
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - 1 <= nums[i] <= 10^9
 *   - 1 <= k <= nums.length
 *
 * Approach: Variable-size Sliding Window + HashMap
 *   - Maintain a frequency map for the current window.
 *   - Expand right: increment frequency.
 *   - If any element exceeds k, shrink from left until valid.
 *   - Track maximum window size.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LongestSubarrayKFrequency {

    public static int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();

        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {

            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);

            while (count.get(nums[right]) > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3, 1, 2};
        int k = 2;
        System.out.println(maxSubarrayLength(nums, k));
    }
}
