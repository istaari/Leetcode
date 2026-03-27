package leetcode.dp.lis;

import java.util.Arrays;

/**
 * 673. Number of Longest Increasing Subsequence
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * Given an integer array nums, return the number of longest increasing subsequences.
 * Notice that the sequence has to be strictly increasing.
 *
 * Example 1: Input: nums = [1,3,5,4,7] -> Output: 2
 *   Explanation: The two longest increasing subsequences are [1,3,5,7] and [1,3,4,7].
 *
 * Example 2: Input: nums = [2,2,2,2,2] -> Output: 5
 *   Explanation: Length of LIS is 1, and there are 5 such subsequences.
 *
 * Constraints:
 *   1 <= nums.length <= 2000
 *   -10^6 <= nums[i] <= 10^6
 *
 * ---
 * Approach: DP with two arrays (length + count)
 *
 * Extend the classic LIS DP with a second array to track HOW MANY
 * subsequences achieve each length.
 *
 * STATE:
 *   dp[i]    = length of the longest increasing subsequence ending at index i
 *   count[i] = number of LIS of that length ending at index i
 *
 * BASE:       dp[i] = 1, count[i] = 1 (each element alone)
 *
 * TRANSITION: For each j < i where nums[j] < nums[i]:
 *   If dp[j] + 1 > dp[i]:  found a longer subsequence → dp[i] = dp[j]+1, count[i] = count[j]
 *   If dp[j] + 1 == dp[i]: found another way to reach same length → count[i] += count[j]
 *
 * ANSWER:     Sum of count[i] for all i where dp[i] == maxLength
 *
 * Example trace: nums = [1, 3, 5, 4, 7]
 *
 *   dp:    [1, 2, 3, 3, 4]
 *   count: [1, 1, 1, 1, 2]
 *                         ^-- 2 because both dp[2]=3 and dp[3]=3 extend to dp[4]=4
 *
 * Time:  O(n^2)
 * Space: O(n)
 */
public class NumberOfLIS {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];    // Length of LIS ending at i
        int[] count = new int[n]; // Number of LIS of length dp[i] ending at i

        // Base case: each element alone forms a subsequence of length 1
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        // Found a strictly longer subsequence ending at i
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // Reset count: inherit j's count
                    } else if (dp[j] + 1 == dp[i]) {
                        // Found another way to reach the same length
                        count[i] += count[j]; // Accumulate count
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        // Sum up counts of all positions whose LIS length equals maxLen
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                result += count[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 4, 7};
        System.out.println("[1,3,5,4,7]: " + findNumberOfLIS(nums1)); // 2

        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println("[2,2,2,2,2]: " + findNumberOfLIS(nums2)); // 5

        int[] nums3 = {1, 2, 4, 3, 5, 4, 7, 2};
        System.out.println("[1,2,4,3,5,4,7,2]: " + findNumberOfLIS(nums3)); // 3
    }
}
