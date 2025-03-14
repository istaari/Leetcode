package leetcode.dp.partitioning;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartitionToKEqualSumSubsets {

    private static boolean backtrack(int[] nums, boolean[] used, int currentSum, int startIndex, int remainingSubsets, int targetSum, Map<String, Boolean> memo) {
        if (remainingSubsets == 1) return true; // If k-1 subsets are found, the last one is valid by default

        // Generate a unique key based on the used elements (bitmask-like approach)
        String stateKey = Arrays.toString(used);
        if (memo.containsKey(stateKey)) return memo.get(stateKey);

        if (currentSum == targetSum) {
            // If one subset is complete, move to the next subset
            boolean result = backtrack(nums, used, 0, 0, remainingSubsets - 1, targetSum, memo);
            memo.put(stateKey, result);
            return result;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!used[i] && currentSum + nums[i] <= targetSum) {
                used[i] = true;

                if (backtrack(nums, used, currentSum + nums[i], i + 1, remainingSubsets, targetSum, memo)) {
                    return true;
                }

                used[i] = false;
            }
        }

        memo.put(stateKey, false);
        return false;
    }


    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = Arrays.stream(nums).sum();
        // If totalSum is not divisible by k, partitioning is impossible
        if (totalSum % k != 0) return false;

        int targetSum = totalSum / k;
        Arrays.sort(nums);

        // Start from the largest element to reduce recursion depth
        int n = nums.length;
        // Keeps track of which has been used in a set
        boolean[] used = new boolean[n];

        return backtrack(nums, used, 0, 0, k, targetSum, new HashMap<>());
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        System.out.println(canPartitionKSubsets(nums1, k1)); // Output: true

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 3;
        System.out.println(canPartitionKSubsets(nums2, k2)); // Output: false
    }
}
