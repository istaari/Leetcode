package leetcode.dp.bitmask;

import java.util.Arrays;

/**
 * 473. Matchsticks to Square
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length
 * of the i-th matchstick. You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and each matchstick
 * must be used exactly once.
 *
 * Return true if you can make this square and false otherwise.
 *
 * Example 1: Input: matchsticks = [1,1,2,2,2]  -> Output: true
 *   Explanation: You can form a square with side length 2,
 *   one side of the square is formed by two sticks of length 1.
 *
 * Example 2: Input: matchsticks = [3,3,3,3,4]  -> Output: false
 *   Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 * Constraints:
 *   1 <= matchsticks.length <= 15
 *   1 <= matchsticks[i] <= 10^8
 *
 * ---
 * Approach: Bitmask DP
 *
 * Since n <= 15, we can represent every subset of matchsticks as a bitmask (2^15 = 32768 states).
 *
 * Key Idea:
 *   We greedily fill one side of the square at a time. dp[mask] tracks the
 *   "remainder" length on the current (incomplete) side after placing all sticks
 *   in the subset represented by mask.
 *
 *   When dp[mask] reaches exactly `target` (side length), we take modulo to reset
 *   it to 0, meaning that side is complete and we start filling the next side.
 *
 *   If dp[(1<<n) - 1] == 0, all sticks are used and all four sides are exactly filled.
 *
 * Time:  O(2^n * n)  — for each of the 2^n masks, we try n sticks.
 * Space: O(2^n)      — for the dp array.
 */
public class MatchsticksToSquare {

    /**
     * STATE: dp[mask]
     * For a subset of sticks (mask), dp[mask] stores how many units are
     * currently contributing to the "incomplete" side.
     */
    public boolean makeSquare(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4)
            return false;

        long totalSum = 0;
        for (int s : matchsticks)
            totalSum += s;

        // If the total length isn't divisible by 4, we can't form a square
        if (totalSum % 4 != 0)
            return false;

        int target = (int) (totalSum / 4);
        int numStates = 1 << n;
        int[] dp = new int[numStates];

        // BASE CASE:
        // Start with an empty set of sticks.
        // Initialize all other states as unreachable (-1).
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // TRANSITION:
        // For every reachable state (mask), try adding an unused matchstick (j).
        for (int mask = 0; mask < numStates; mask++) {
            if (dp[mask] == -1)
                continue;

            for (int j = 0; j < n; j++) {
                // Check if the j-th matchstick is already used in the current mask
                if ((mask & (1 << j)) == 0) {
                    // Include the current matchstick in the mask
                    int nextMask = mask | (1 << j);

                    // If adding this stick doesn't exceed the target side length...
                    if (dp[mask] + matchsticks[j] <= target) {
                        // Modulo resets side length to 0 once 'target' is reached, starting the next side.
                        // Goal state: all bits set (11...1) indicates all matchsticks have been used.
                        // Goal state: (1 << n) - 1 indicates every stick has been placed.
                        dp[nextMask] = (dp[mask] + matchsticks[j]) % target;
                    }
                }
            }
        }

        return dp[numStates - 1] == 0;
    }

    public static void main(String[] args) {
        MatchsticksToSquare solver = new MatchsticksToSquare();

        // Test Case 1
        int[] matchsticks1 = { 1, 1, 2, 2, 2 };
        System.out.println("Test Case 1: " + Arrays.toString(matchsticks1));
        System.out.println("Can form square? " + solver.makeSquare(matchsticks1)); // Expected: true

        System.out.println("---");

        // Test Case 2
        int[] matchsticks2 = { 3, 3, 3, 3, 4 };
        System.out.println("Test Case 2: " + Arrays.toString(matchsticks2));
        System.out.println("Can form square? " + solver.makeSquare(matchsticks2)); // Expected: false
    }
}