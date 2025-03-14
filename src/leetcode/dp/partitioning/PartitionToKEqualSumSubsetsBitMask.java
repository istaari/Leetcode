package leetcode.dp.partitioning;

import java.util.Arrays;

public class PartitionToKEqualSumSubsetsBitMask {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        int targetSum = sum / k;
        int n = nums.length;
        Arrays.sort(nums);

        boolean[] dp = new boolean[1 << n];
        int[] total = new int[1 << n];
        dp[0] = true;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (!dp[mask]) continue;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {

                    if (total[mask] % targetSum + nums[i] <= targetSum) {
                        int newMask = mask | (1 << i);
                        dp[newMask] = true;
                        total[newMask] = total[mask] + nums[i];
                    }
                }
            }
        }

        return dp[(1 << n) - 1];
    }

}
