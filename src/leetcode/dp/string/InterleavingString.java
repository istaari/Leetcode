package leetcode.dp.string;

public class InterleavingString {

    public static boolean isInterleave(String s1, String s2, String s3) {
        // Length check
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                char c3 = s3.charAt(i + j - 1);

                if (c1 == c3 && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (c2 == c3 && dp[i][j - 1]) {
                    dp[i][j] = true;
                }

            }
        }

        return dp[s1.length()][s2.length()];

    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        System.out.println(isInterleave(s1, s2, s3));
    }
}
