package leetcode.dp.subsequenceAndSets;

import java.util.Arrays;

public class NumberOfWaysToStay {

    private static int MOD = 1_000_000_007;

    private static int recursive(int steps, int arrLen, int i, int j, int[][] dp){
       if (j < 0 || j >= arrLen) return 0;

       if (i == steps) return j == 0 ? 1 : 0;

       if(dp[i][j] != -1) return dp[i][j];

       int stay = recursive(steps, arrLen, i + 1, j, dp);
       int right = recursive(steps, arrLen, i + 1, j + 1, dp);
       int left = recursive(steps, arrLen, i + 1, j - 1, dp);
      
       dp[i][j] = ((stay + right) % MOD + left) % MOD;
       return  dp[i][j];
    }

    public static int numWays(int steps, int arrLen) {
        int maxPos = Math.min(steps, arrLen); // max useful positions
        int[][] dp = new int[steps + 1][maxPos];
        for(int[] a : dp){
            Arrays.fill(a, -1);
        }
        return recursive(steps, maxPos, 0, 0, dp);
    }

    public static void main(String[] args){
       int steps = 3;
       int arrLen = 2;
       System.out.println(numWays(steps, arrLen));  
    }
}

