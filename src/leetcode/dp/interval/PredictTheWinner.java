package leetcode.dp.interval;

/**
 * 486. Predict the Winner
 * https://leetcode.com/problems/predict-the-winner/
 *
 * You are given an integer array nums. Two players take turns picking numbers
 * from either end of the array. Player 1 picks first. Each player adds the
 * picked number to their score. Return true if Player 1 can win or tie.
 *
 * Example 1: Input: nums = [1,5,2] -> Output: false
 *   Explanation: Player 1 picks 1, Player 2 picks 5, Player 1 picks 2.
 *   Score: Player 1 = 3, Player 2 = 5. Player 1 cannot win.
 *
 * Example 2: Input: nums = [1,5,233,7] -> Output: true
 *
 * Constraints:
 *   1 <= nums.length <= 20
 *   0 <= nums[i] <= 10^7
 *
 * ---
 * Approach: Interval DP (minimax with relative score)
 *
 * dp[i][j] = the maximum score difference (current player - opponent)
 * the current player can achieve from nums[i..j].
 *
 * STATE:      dp[i][j] = max relative advantage for current player in [i..j]
 * BASE:       dp[i][i] = nums[i]  (only one element to pick)
 * TRANSITION: dp[i][j] = max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])
 *             (pick left or right, then subtract opponent's best result)
 * ANSWER:     dp[0][n-1] >= 0 means Player 1 wins or ties
 *
 * Time:  O(n^2)
 * Space: O(n^2)
 */
public class PredictTheWinner {

    // Top-down: returns the max score difference the current player achieves in nums[i..j]
    public static int recursive(int[] nums, int i, int j, Integer[][] dp) {
        // Base case: only one element, current player takes it
        if (i == j) return nums[i];

        if (dp[i][j] != null) return dp[i][j];

        // Pick left:  gain nums[i], then opponent plays optimally on [i+1..j]
        int pickLeft = nums[i] - recursive(nums, i + 1, j, dp);
        // Pick right: gain nums[j], then opponent plays optimally on [i..j-1]
        int pickRight = nums[j] - recursive(nums, i, j - 1, dp);

        // Choose the action that maximizes our relative advantage
        dp[i][j] = Math.max(pickLeft, pickRight);

        return dp[i][j];
    }

    public static boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];

        // Player 1 wins if their relative score advantage >= 0
        return recursive(nums, 0, n - 1, dp) >= 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2};
        System.out.println(predictTheWinner(nums));
    }

}