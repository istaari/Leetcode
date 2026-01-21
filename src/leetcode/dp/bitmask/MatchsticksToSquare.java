package leetcode.dp.bitmask;

import java.util.Arrays;

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