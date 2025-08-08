package leetcode.dp.subsequence;

import java.util.Arrays;

public class MaxLengthValidSubsequence_2 {

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k];
        int result = 1;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int m = (nums[i] + nums[j]) % k;
                dp[i][m] = Math.max(dp[i][m], dp[j][m] + 1);

                result = Math.max(dp[i][m], result);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 2;

        System.out.println(new MaxLengthValidSubsequence_2().maximumLength(nums, k));
    }

}
