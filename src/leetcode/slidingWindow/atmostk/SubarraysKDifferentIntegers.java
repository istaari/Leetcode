package leetcode.slidingWindow.atmostk;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 992: Subarrays with K Different Integers
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 *
 * Given an integer array nums and an integer k, return the number of good
 * subarrays of nums. A good subarray has exactly k different integers.
 *
 * Example 1:
 *   Input: nums = [1,2,1,2,3], k = 2
 *   Output: 7
 *   Explanation: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *
 * Example 2:
 *   Input: nums = [1,2,1,3,4], k = 3
 *   Output: 3
 *
 * Constraints:
 *   - 1 <= nums.length <= 2 * 10^4
 *   - 1 <= nums[i], k <= nums.length
 *
 * Approach: atMost(K) - atMost(K-1) pattern
 *   - exactlyK(k) = atMost(k) - atMost(k - 1)
 *   - atMost(K): sliding window with a HashMap tracking distinct element counts.
 *     Shrink left when distinct count exceeds K. Add (right - left + 1) subarrays.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */
public class SubarraysKDifferentIntegers {


    public static int countAtMostKSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++;
            }

            // (right - left + 1) - It calculates the count of all possible subarrays ending at the current 'right' position.
            result += (right - left + 1);
        }

        return result;
    }


    public static int subarraysWithKDistinct(int[] nums, int k) {
        return countAtMostKSubarrays(nums, k) - countAtMostKSubarrays(nums, k - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;

        System.out.println(subarraysWithKDistinct(nums, k));
    }


}
