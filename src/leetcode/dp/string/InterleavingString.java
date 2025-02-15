package leetcode.dp.string;

public class InterleavingString {

    public static boolean helper(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp) {
        if (i == s1.length() && j == s2.length()) return true;

        if (dp[i][j] != null) return dp[i][j];

        // Take first char from s1
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            dp[i][j] = helper(s1, s2, s3, i + 1, j, k + 1, dp);

            if (dp[i][j]) return true;
        }

        // Take first char from s2
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            dp[i][j] = helper(s1, s2, s3, i, j + 1, k + 1, dp);

            if (dp[i][j]) return true;
        }

        return dp[i][j] = false;
    }

    public static boolean recursive(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        helper(s1, s2, s3, 0, 0, 0, dp);

        return helper(s1, s2, s3, 0, 0, 0, dp);
    }


    public static boolean iterative(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();

        if (n + m != s3.length()) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        // Fill first column (considering only s1)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill first row (considering only s2)
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int k = i + j - 1;
                dp[i][j] = (s1.charAt(i - 1) == s3.charAt(k) && dp[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(k) && dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }


    public static boolean isInterleave(String s1, String s2, String s3) {
        return true;
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac"; // "aa dbbc bc a c";
        System.out.println(recursive(s1, s2, s3));
    }
}
