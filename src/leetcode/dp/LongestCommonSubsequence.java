package leetcode.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static int topDown(char[] text1, char[] text2, int i, int j, int[][] memo) {

        if (i >= text1.length || j >= text2.length) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        if (text1[i] == text2[j]) {
            memo[i][j] = 1 + topDown(text1, text2, i + 1, j + 1, memo);

        } else {
            int max1 = topDown(text1, text2, i + 1, j, memo);
            int max2 = topDown(text1, text2, i, j + 1, memo);

            memo[i][j] = Math.max(max1, max2);
        }

        return memo[i][j];
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] memo = new int[text1.length()][text2.length()];

        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return topDown(text1.toCharArray(), text2.toCharArray(), 0, 0, memo);
    }


    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        System.out.println(longestCommonSubsequence(text1, text2));
    }

}
