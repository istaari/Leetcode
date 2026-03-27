package leetcode.dp.digit;

/**
 * 357. Count Numbers with Unique Digits
 * https://leetcode.com/problems/count-numbers-with-unique-digits/
 *
 * Given an integer n, return the count of all numbers with unique digits, x,
 * where 0 <= x < 10^n.
 *
 * Example 1: Input: n = 2 -> Output: 91
 *   Explanation: All numbers from 0 to 99 except [11,22,33,44,55,66,77,88,99]
 *   have unique digits. 100 - 9 = 91.
 *
 * Example 2: Input: n = 0 -> Output: 1 (only 0)
 * Example 3: Input: n = 1 -> Output: 10 (0 through 9)
 *
 * Constraints:
 *   0 <= n <= 8
 *
 * ---
 * Approach: Combinatorics / Math DP
 *
 * Count k-digit numbers with unique digits for each k from 1 to n.
 * For a k-digit number:
 *   - First digit: 9 choices (1-9, can't be 0)
 *   - Second digit: 9 choices (0-9 minus the first)
 *   - Third digit: 8 choices, and so on.
 *
 * uniqueK = 9 * 9 * 8 * 7 * ... * (11 - k)
 * answer = 1 + sum(uniqueK) for k = 1..n
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class CountNumbersWithUniqueDigits {

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;

        int total = 10; // For n=1, we have 0..9 = 10 numbers
        int uniqueK = 9; // Count of 1-digit unique-digit numbers (1-9)

        for (int k = 2; k <= n && k <= 10; k++) {
            // For k-th digit, we have (11 - k) remaining choices
            // k=2: 9 choices (0-9 minus 1 used), k=3: 8 choices, etc.
            uniqueK *= (11 - k);
            total += uniqueK;
        }

        return total;
    }

    /**
     * Alternative: Digit DP with bitmask
     *
     * This approach generalizes better to harder digit DP problems.
     * dp(pos, mask, started) = count of valid numbers from position pos onward.
     *
     * - pos:     current digit position (0 to n-1)
     * - mask:    bitmask of digits already used (10 bits for 0-9)
     * - started: whether we've placed a non-zero digit yet (handles leading zeros)
     */
    public static int digitDP(int n) {
        if (n == 0) return 1;
        // memo[pos][mask][started] — mask has 1024 states, started has 2
        Integer[][][] memo = new Integer[n][1 << 10][2];
        return 1 + solve(0, 0, false, n, memo); // +1 for zero itself
    }

    private static int solve(int pos, int mask, boolean started, int n, Integer[][][] memo) {
        // All positions filled: we've formed one valid number
        if (pos == n) return started ? 1 : 0;

        int s = started ? 1 : 0;
        if (memo[pos][mask][s] != null) return memo[pos][mask][s];

        int count = 0;

        for (int d = 0; d <= 9; d++) {
            // Skip if digit already used
            if ((mask & (1 << d)) != 0) continue;

            if (!started && d == 0) {
                // Leading zero: don't mark it in the mask, keep started=false
                count += solve(pos + 1, mask, false, n, memo);
            } else {
                // Place digit d: mark it in the mask, started=true
                count += solve(pos + 1, mask | (1 << d), true, n, memo);
            }
        }

        memo[pos][mask][s] = count;
        return count;
    }

    public static void main(String[] args) {
        System.out.println("n=0: " + countNumbersWithUniqueDigits(0)); // 1
        System.out.println("n=1: " + countNumbersWithUniqueDigits(1)); // 10
        System.out.println("n=2: " + countNumbersWithUniqueDigits(2)); // 91
        System.out.println("n=3: " + countNumbersWithUniqueDigits(3)); // 739

        System.out.println("--- Digit DP ---");
        System.out.println("n=0: " + digitDP(0)); // 1
        System.out.println("n=2: " + digitDP(2)); // 91
        System.out.println("n=3: " + digitDP(3)); // 739
    }
}
