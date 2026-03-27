package leetcode.slidingWindow.fixedSize;

/**
 * LeetCode 1423: Maximum Points You Can Obtain from Cards
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 *
 * There are several cards arranged in a row, and each card has an associated
 * number of points. You are given the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the
 * row. You have to take exactly k cards. Your score is the sum of the points of
 * the cards you have taken.
 *
 * Return the maximum score you can obtain.
 *
 * Example 1:
 *   Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 *   Output: 12 (Take the last three cards: 4 + 5 + 6 + 1 vs right combos)
 *
 * Example 2:
 *   Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 *   Output: 55
 *
 * Constraints:
 *   - 1 <= cardPoints.length <= 10^5
 *   - 1 <= cardPoints[i] <= 10^4
 *   - 1 <= k <= cardPoints.length
 *
 * Approach: Fixed-size Sliding Window (complement)
 *   - Instead of choosing k cards from ends, find the minimum sum subarray
 *     of size (n - k) in the middle.
 *   - Answer = totalSum - minWindowSum.
 *   - Slide a window of size (n - k) across the array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaximumPointsFromCards {

    public static int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        for (int point : cardPoints) {
            totalSum += point;
        }

        int windowSize = cardPoints.length - k;
        int windowSum = 0;

        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }

        int result = Math.max(0, totalSum - windowSum);
        for (int i = windowSize; i < cardPoints.length; i++) {

            windowSum += cardPoints[i] - (cardPoints[i - windowSize]);
            result = Math.max(result, totalSum - windowSum);

        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {96, 90, 41, 82, 39, 74, 64, 50, 30};
        int K = 8;

        System.out.println(maxScore(nums, K));
    }


}
