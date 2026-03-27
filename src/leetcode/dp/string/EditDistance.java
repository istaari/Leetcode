package leetcode.dp.string;

import java.util.Arrays;

/**
 * 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 *
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 into word2. You have three operations:
 * Insert a character, Delete a character, Replace a character.
 *
 * Example 1: Input: word1 = "horse", word2 = "ros" -> Output: 3
 *   Explanation: horse -> rorse (replace h with r) -> rose (remove r) -> ros (remove e)
 *
 * Example 2: Input: word1 = "intention", word2 = "execution" -> Output: 5
 *
 * Constraints:
 *   0 <= word1.length, word2.length <= 500
 *   word1 and word2 consist of lowercase English letters.
 *
 * ---
 * Approach: 2D DP (top-down + bottom-up)
 *
 * STATE:      dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
 * BASE:       dp[i][0] = i (delete all), dp[0][j] = j (insert all)
 * TRANSITION: If chars match: dp[i][j] = dp[i-1][j-1]
 *             Else: dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])
 *                                      (insert,     delete,     replace)
 * ANSWER:     dp[m][n]
 *
 * Time:  O(m * n)
 * Space: O(m * n)
 */
public class EditDistance {

    public static int recursive(String word1, String word2, int i, int j, int[][] dp) {
        // Base case: If one string is empty
        if (i == 0) return j; // Insert all characters of word2
        if (j == 0) return i; // Remove all characters of word1

        // Check if the result is already computed
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If the characters are the same, no operation is needed
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = recursive(word1, word2, i - 1, j - 1, dp);
        } else {
            // Calculate the minimum of three operations (Insert, Delete, Replace)
            int insert = recursive(word1, word2, i, j - 1, dp);    // Insert character from word2
            int delete = recursive(word1, word2, i - 1, j, dp);    // Delete character from word1
            int replace = recursive(word1, word2, i - 1, j - 1, dp); // When you replace, it becomes equal

            dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }

        return dp[i][j];
    }

    /**
     * Bottom-up iterative DP
     *
     * Example: word1 = "horse", word2 = "ros" -> 3
     *
     * dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
     *
     *          ""  r  o  s
     *     ""  [ 0, 1, 2, 3 ]   <- base: insert all of word2
     *     h   [ 1, 1, 2, 3 ]   h→r: replace(0+1=1)
     *     o   [ 2, 2, 1, 2 ]   o==o: diagonal(1)
     *     r   [ 3, 2, 2, 2 ]   r==r: diagonal(2)
     *     s   [ 4, 3, 3, 2 ]   s==s: diagonal(2)
     *     e   [ 5, 4, 4, 3 ]   <- answer: dp[5][3] = 3
     *              ↑
     *          base: delete all of word1
     *
     * Trace for dp[5][3] (word1="horse", word2="ros"):
     *   'e' != 's' → 1 + min(dp[4][3], dp[5][2], dp[4][2]) = 1 + min(2, 4, 3) = 3
     *                       (delete)  (insert)  (replace)
     */
    public static int iterative(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base case: if word1 is empty, we need to insert all characters of word2
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Base case: if word2 is empty, we need to remove all characters of word1
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take the minimum of Insert, Delete, and Replace operations
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return recursive(word1, word2, m, n, dp);
    }


    public static void main(String[] args) {
        String word1 = "a";
        String word2 = "x";
        System.out.println(minDistance(word1, word2));
    }

}
