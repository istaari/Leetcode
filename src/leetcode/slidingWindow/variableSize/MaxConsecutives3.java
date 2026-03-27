package leetcode.slidingWindow.variableSize;

/**
 * LeetCode 1004: Max Consecutive Ones III
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 *   Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 *   Output: 6
 *   Explanation: Flip the two 0's at indices 5,10 -> [1,1,1,0,0,1,1,1,1,1,1]
 *
 * Example 2:
 *   Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 *   Output: 10
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - nums[i] is either 0 or 1
 *   - 0 <= k <= nums.length
 *
 * Approach: Variable-size Sliding Window
 *   - Expand right pointer; when a 0 is encountered, decrement k.
 *   - When k < 0, shrink from left until k >= 0 (restore a flipped 0).
 *   - Track max window size throughout.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaxConsecutives3 {

    public static int longestOnes(int[] nums, int k) {
        int result = 0;

        int left = 0;
        int right = 0;

        while (right < nums.length) {

            if (nums[right] == 0) {
                k--;
            }

            if (k < 0) {
                while (k < 0) {
                    if (nums[left] == 0) {
                        k++;
                    }
                    left++;
                }

            }

            result = Math.max(result, right - left + 1);
            right++;

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;

        System.out.println(longestOnes(nums, k));
    }
}
