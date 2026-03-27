package leetcode.dp.lis;

import java.util.Arrays;

/**
 * 354. Russian Doll Envelopes
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi]
 * represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height
 * of one envelope are strictly greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (put one inside the other).
 * You cannot rotate an envelope.
 *
 * Example 1: Input: envelopes = [[5,4],[6,4],[6,7],[2,3]] -> Output: 3
 *   Explanation: [2,3] => [5,4] => [6,7]
 *
 * Example 2: Input: envelopes = [[1,1],[1,1],[1,1]] -> Output: 1
 *
 * Constraints:
 *   1 <= envelopes.length <= 10^5
 *   envelopes[i].length == 2
 *   1 <= wi, hi <= 10^5
 *
 * ---
 * Approach: Sort + LIS with Binary Search (O(n log n))
 *
 * Key insight: reduce to 1D LIS problem.
 *
 * 1. Sort envelopes by width ascending. If widths are equal, sort by height
 *    DESCENDING. This ensures that for the same width, we never pick two
 *    envelopes (since heights are decreasing, LIS won't select both).
 *
 * 2. Extract the heights array and find the LIS length using patience-sorting
 *    (binary search approach).
 *
 * Example: [[5,4],[6,4],[6,7],[2,3]]
 *   After sort: [[2,3],[5,4],[6,7],[6,4]]  (width asc, height desc for same width)
 *   Heights: [3, 4, 7, 4]
 *   LIS of heights: [3, 4, 7] → length 3
 *
 * Time:  O(n log n)
 * Space: O(n)
 */
public class RussianDollEnvelopes {

    /**
     * O(n log n) approach: Sort + LIS via binary search (patience sorting)
     */
    public static int maxEnvelopes(int[][] envelopes) {
        // Sort: width ascending, height DESCENDING for same width
        // Descending height for same width prevents picking two envelopes
        // with same width in the LIS
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // width ascending
            return b[1] - a[1];                     // height descending
        });

        // Now find LIS on heights using patience sorting
        // tails[i] = smallest possible tail element of an increasing subsequence of length i+1
        int[] tails = new int[envelopes.length];
        int size = 0; // Length of the longest subsequence found

        for (int[] env : envelopes) {
            int h = env[1];

            // Binary search: find the first element in tails >= h
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < h) {
                    lo = mid + 1; // Look for a larger tail
                } else {
                    hi = mid;     // tails[mid] >= h, try to go smaller
                }
            }

            // lo is the insertion point
            tails[lo] = h;

            // If lo == size, we extended the longest subsequence
            if (lo == size) size++;
        }

        return size;
    }

    /**
     * O(n^2) approach: Sort + classic LIS DP
     * (TLE for large inputs but easier to understand)
     */
    public static int maxEnvelopesDP(int[][] envelopes) {
        // Sort by width ascending, then height ascending
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int n = envelopes.length;
        int[] dp = new int[n]; // dp[i] = max envelopes in chain ending at i
        Arrays.fill(dp, 1);

        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Both width and height must be strictly greater
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[][] env1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println("O(n log n): " + maxEnvelopes(env1));   // 3
        System.out.println("O(n^2):     " + maxEnvelopesDP(env1)); // 3

        int[][] env2 = {{1, 1}, {1, 1}, {1, 1}};
        System.out.println("O(n log n): " + maxEnvelopes(env2));   // 1
        System.out.println("O(n^2):     " + maxEnvelopesDP(env2)); // 1
    }
}
