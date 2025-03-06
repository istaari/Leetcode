package leetcode.dp.fibonacci;

public class ClimbingStairs {

    public static int iterative(int n) {
        if (n <= 2) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Recurrence relation
        }

        return dp[n];
    }


    public static int recursive(int n, int[] dp) {
        if (n <= 2) return n;

        if (dp[n] != 0) return dp[n];

        dp[n] = recursive(n - 1, dp) + recursive(n - 2, dp);

        return dp[n];
    }


    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return recursive(n, dp);
    }


    public static void main(String[] args) {
        System.out.println(climbStairs(2)); // 2
        System.out.println(climbStairs(3)); // 3
        System.out.println(climbStairs(4)); // 5
        System.out.println(climbStairs(5)); // 8
    }
}
