package leetcode.backtracking;

public class UniqueBinarySearchTrees {


    public static int numTrees(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int level = 2; level <= n; level++) {
            // j is current node
            for (int j = 1; j <= level; j++) {
                dp[level] += dp[j - 1] * dp[level - j];
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {
        int n = 2;
        System.out.println(numTrees(n));
    }

}
