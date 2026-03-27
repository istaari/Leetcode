package leetcode.dp.digit;

/**
 * 233. Number of Digit One
 * https://leetcode.com/problems/number-of-digit-one/
 *
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 *
 * Example 1: Input: n = 13 -> Output: 6
 *   Explanation: Digit 1 appears in: 1, 10, 11 (twice), 12, 13 → total 6.
 *
 * Example 2: Input: n = 0 -> Output: 0
 *
 * Constraints:
 *   0 <= n <= 10^9
 *
 * ---
 * Approach: Digit DP
 *
 * Process the number digit by digit (as a string). At each position, decide
 * what digit to place and track state:
 *
 * dp(pos, count, tight) = total count of digit '1' across all valid numbers
 *   - pos:   current digit position being filled
 *   - count: how many 1s placed so far in this number
 *   - tight: whether we're still bounded by n's prefix (can't exceed n)
 *
 * If tight=true, digit can go from 0 to digits[pos].
 * If tight=false, digit can be 0 to 9 freely.
 *
 * We count 1s contributed by ALL numbers from 0 to n collectively.
 *
 * Time:  O(len * len * 2) where len = number of digits in n (~10)
 * Space: O(len * len * 2)
 */
public class NumberOfDigitOne {

    /**
     * Digit DP Solution
     */
    public static int countDigitOne(int n) {
        if (n <= 0) return 0;

        String num = String.valueOf(n);
        int len = num.length();
        // memo[pos][count][tight]
        // count can be at most len (all digits are 1), tight is 0 or 1
        Integer[][][] memo = new Integer[len][len + 1][2];

        return solve(0, 0, true, num, memo);
    }

    /**
     * @param pos   current digit position (0-indexed from left)
     * @param count number of 1s placed so far
     * @param tight true if previous digits exactly matched n's prefix
     * @param num   string representation of n
     * @param memo  memoization table
     * @return total count of digit 1 in all numbers formed from pos onward
     */
    private static int solve(int pos, int count, boolean tight, String num, Integer[][][] memo) {
        // All positions filled: return the count of 1s in this number
        if (pos == num.length()) return count;

        int t = tight ? 1 : 0;
        if (memo[pos][count][t] != null) return memo[pos][count][t];

        // Upper limit for this digit position
        int limit = tight ? (num.charAt(pos) - '0') : 9;

        int result = 0;
        for (int d = 0; d <= limit; d++) {
            // If we place digit 1, count increases by 1
            int newCount = count + (d == 1 ? 1 : 0);
            // tight remains true only if we're still matching n's digits
            boolean newTight = tight && (d == limit);
            result += solve(pos + 1, newCount, newTight, num, memo);
        }

        memo[pos][count][t] = result;
        return result;
    }

    /**
     * Mathematical approach (O(log n) per digit position)
     *
     * For each position, count how many times digit 1 appears there
     * across all numbers from 0 to n.
     *
     * For position i (from right, 0-indexed), let:
     *   factor = 10^i
     *   higher = n / (factor * 10)
     *   curr   = (n / factor) % 10
     *   lower  = n % factor
     *
     * Count of 1s at position i:
     *   if curr == 0: higher * factor
     *   if curr == 1: higher * factor + lower + 1
     *   if curr >= 2: (higher + 1) * factor
     */
    public static int countDigitOneMath(int n) {
        int count = 0;
        // factor represents 10^i (the place value: 1, 10, 100, ...)
        for (long factor = 1; factor <= n; factor *= 10) {
            long higher = n / (factor * 10);  // Digits above current position
            long curr = (n / factor) % 10;    // Current digit
            long lower = n % factor;           // Digits below current position

            if (curr == 0) {
                count += higher * factor;
            } else if (curr == 1) {
                count += higher * factor + lower + 1;
            } else {
                count += (higher + 1) * factor;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("n=13 (DP):   " + countDigitOne(13));       // 6
        System.out.println("n=13 (Math): " + countDigitOneMath(13));   // 6
        System.out.println("n=100 (DP):  " + countDigitOne(100));      // 21
        System.out.println("n=100 (Math):" + countDigitOneMath(100));  // 21
    }
}
