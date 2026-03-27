package leetcode.slidingWindow.atmostk;

/**
 * LeetCode 930: Binary Subarrays With Sum
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 *
 * Given a binary array nums and an integer goal, return the number of non-empty
 * subarrays with a sum equal to goal.
 *
 * Example 1:
 *   Input: nums = [1,0,1,0,1], goal = 2
 *   Output: 4
 *   Explanation: [1,0,1], [1,0,1,0], [0,1,0,1], [1,0,1]
 *
 * Example 2:
 *   Input: nums = [0,0,0,0,0], goal = 0
 *   Output: 15
 *
 * Constraints:
 *   - 1 <= nums.length <= 3 * 10^4
 *   - nums[i] is either 0 or 1
 *   - 0 <= goal <= nums.length
 *
 * Approach: atMost(K) - atMost(K-1) pattern
 *   - exactlyK(goal) = atMost(goal) - atMost(goal - 1)
 *   - atMost(K): sliding window counting subarrays with sum <= K.
 *     For each right, shrink left while sum > K, then add (right - left + 1).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
@SuppressWarnings("all")
public class BinarySubarraysWithSum {

    public static int subarrayAtMostK(int[] nums, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];

            while (left <= right && sum > K) {
                sum -= nums[left];
                left++;
            }

            result += right - left + 1;
            right++;
        }

        return result;
    }


    public static int numSubarraysWithSum(int[] nums, int goal) {
        return subarrayAtMostK(nums, goal) - subarrayAtMostK(nums, goal - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        System.out.println(numSubarraysWithSum(nums, goal));
    }
}
