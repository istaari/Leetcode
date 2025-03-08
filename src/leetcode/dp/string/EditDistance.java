package leetcode.dp.string;

import java.util.Arrays;

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

    public static int iterative(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // DP table where dp[i][j] represents the minimum number of operations to convert word1[0..i-1] to word2[0..j-1]
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
