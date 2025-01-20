package neetcode150.dynamicProgramming1D;

import java.util.Arrays;

public class LIS_300 {

    /**
     * Find the length of the longest increasing subsequence.
     *
     * @param number the array of numbers
     * @return the length of the longest increasing subsequence
     */

    public static int LIS_DP(int[] number) {
        int n = number.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (number[i] > number[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

    /**
     * Find the length of the longest increasing subsequence using binary search.
     *
     * @param number the array of numbers
     * @return the length of the longest increasing subsequence
     */
    public static int LIS_BS(int[] number) {
        int[] dp = new int[number.length];
        int len = 0;
        for (int x : number) {
            // Returns insertion point where element can be inserted if element is not found
            int i = Arrays.binarySearch(dp, 0, len, x);
            // Turns insertion point into valid index
            if (i < 0) i = -(i + 1);

            dp[i] = x;
            if (i == len) len++;
        }
        return len;
    }


    public static void main(String[] args) {
        int[] number = {1, 2, 3, 4, 5, 6, 30, 8, 9, 2};
        System.out.println(LIS_BS(number));
    }
}
