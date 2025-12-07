package leetcode.backtracking.template;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Count of Subsets with a Given Sum (using Meet-in-the-Middle)
 *
 * Question:
 * Given an array of N integers (which can be positive, negative, or zero) and a
 * target
 * sum S, find the total number of subsets of the array whose elements sum up to
 * S.
 *
 * Why Meet-in-the-Middle?
 * A standard recursive or backtracking approach to generate all 2^N subsets
 * would be too
 * slow for N > 25. This technique optimizes the solution from O(2^N) to
 * O(2^(N/2)),
 * making it feasible for N up to around 40-50 by trading space for time.
 *
 * Example:
 * Input: nums = {1, 2, 3, 4, 5}, S = 6
 * Output: 3
 * Explanation: The subsets that sum to 6 are {1, 5}, {2, 4}, and {1, 2, 3}.
 *
 */
public class MeetInTheMiddleSubsetSum {
    static long totalSubsets = 0;

    public static void generateSubsetSums(int[] arr, int n, Map<Long, Integer> sumMap) {
        // There are 2^n possible subsets.
        for (int i = 0; i < (1 << n); i++) {
            long currentSum = 0;
            for (int j = 0; j < n; j++) {
                // Check if the j-th element is in the current subset.
                if ((i & (1 << j)) != 0) {
                    currentSum += arr[j];
                }
            }
            sumMap.put(currentSum, sumMap.getOrDefault(currentSum, 0) + 1);
        }
    }

    public static long countSubsets(int[] nums, int S) {
        int n = nums.length;
        if (n == 0)
            return 0;

        // --- Step 1: Split the array into two halves ---
        int mid = n / 2;
        int[] part1 = new int[mid];
        int[] part2 = new int[n - mid];

        System.arraycopy(nums, 0, part1, 0, mid);
        System.arraycopy(nums, mid, part2, 0, n - mid);

        // --- Step 2: Generate all subset sums for the first half and store them ---
        Map<Long, Integer> sums1 = new HashMap<>();
        generateSubsetSums(part1, part1.length, sums1);

        // --- Step 3 & 4: Generate subset sums for the second half and meet in the
        // middle ---
        long count = 0;
        Map<Long, Integer> sums2 = new HashMap<>();
        generateSubsetSums(part2, part2.length, sums2);

        // For each subset sum from the second half, find a matching sum from the first
        // half.
        for (Map.Entry<Long, Integer> entry : sums2.entrySet()) {
            long sumB = entry.getKey();
            int freqB = entry.getValue();
            long requiredSumA = S - sumB;

            if (sums1.containsKey(requiredSumA)) {
                int freqA = sums1.get(requiredSumA);
                count += (long) freqA * freqB;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, -1, -2, -3 }; // N = 9
        int S = 5;
        // Example valid subsets: [2, 3], [1, 4], [5], [6, -1], [1, 2, -1, 3], etc.

        long result = countSubsets(nums, S);
        System.out.println("Number of subsets with sum " + S + ": " + result);

        int[] largerNums = new int[40]; // Example for a large N
        for (int i = 0; i < 40; i++)
            largerNums[i] = i + 1;
        // A brute-force 2^40 would be impossible.
        // A 2^20 + 2^20 meet-in-the-middle is feasible.
    }
}