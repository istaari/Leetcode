package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem 560: Subarray Sum Equals K
 *
 * Given an array of integers nums and an integer k, return the total number of
 * continuous subarrays whose sum equals to k.
 *
 * This solution uses a clever approach involving a HashMap and prefix sums.
 * The core idea is that if the cumulative sum up to two indices, say i and j,
 * is sum_i and sum_j, then the sum of the elements between i and j is
 * sum_i - sum_j. We want this difference to be k.
 *
 * So, if we have sum_i - sum_j = k, it means sum_j = sum_i - k.
 *
 * As we iterate through the array, we calculate the current prefix sum (sum_i)
 * and check if we have already seen a prefix sum (sum_j) that equals sum_i - k.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
public class SubarraySumEqualsK {

    /**
     * Calculates the total number of continuous subarrays whose sum equals k.
     *
     * @param nums The input array of integers.
     * @param k The target sum.
     * @return The total count of valid subarrays.
     */
    public static int subarraySum(int[] nums, int k) {
        // A HashMap to store the frequency of each prefix sum encountered so far.
        // Key: Prefix Sum, Value: Frequency (count) of that sum.
        Map<Integer, Integer> map = new HashMap<>();

        // **Crucial Initialization**: We put (0, 1) in the map to handle subarrays
        // that start from index 0. If a prefix sum `sum` itself equals `k`,
        // then `sum - k` will be 0. We need to find a count for this 0.
        map.put(0, 1);

        int sum = 0;    // This will store the running prefix sum.
        int result = 0; // This will store the final count of valid subarrays.

        // Iterate through each number in the input array.
        for (int value : nums) {
            // Update the running prefix sum.
            sum += value;

            // **The Core Logic**:
            // We are looking for a previous prefix sum, let's call it `prevSum`,
            // such that `sum - prevSum = k`.
            // Rearranging this, we get `prevSum = sum - k`.
            // So, we check if our map contains this required `prevSum`.
            if (map.containsKey(sum - k)) {
                // If it does, it means there are subarrays ending at the current
                // position whose sum is k. The number of such subarrays is equal to
                // the frequency of that `prevSum`.
                result += map.get(sum - k);
            }

            // After checking, we must add the current prefix sum to the map.
            // This makes it available for future calculations.
            // We use getOrDefault to handle cases where the sum is seen for the first time.
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }


    static void main(String[] args) {
        // Create a test case: nums = {1, 1, 1}, k = 2
        int[] nums = {1, 1, 1};
        int k = 2;

        System.out.println("Input Array: [1, 1, 1]");
        System.out.println("Target Sum (k): " + k);

        int count = subarraySum(nums, k);

        System.out.println("Total subarrays with sum equal to k: " + count);
        // Expected Output: 2
        // The two subarrays are [1, 1] and [1, 1].
    }
}