package leetcode.dp.digit;

/**
 * 902. Numbers At Most N Given Digit Set
 * https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
 *
 * Given an array of digits (sorted strings like ["1","3","5","7"]), return
 * the number of positive integers that can be generated that are less than
 * or equal to n. Each digit in digits[] can be used any number of times.
 *
 * Example 1: Input: digits = ["1","3","5","7"], n = 100 -> Output: 20
 *   Explanation: 1-digit: 1,3,5,7 (4). 2-digit: 11,13,15,17,31,...,77 (16). Total = 20.
 *
 * Example 2: Input: digits = ["1","4","9"], n = 1000000000 -> Output: 29523
 *
 * Example 3: Input: digits = ["7"], n = 8 -> Output: 1
 *
 * Constraints:
 *   1 <= digits.length <= 9
 *   digits[i].length == 1
 *   digits[i] is a digit from '1' to '9'
 *   All values in digits are unique and sorted
 *   1 <= n <= 10^9
 *
 * ---
 * Approach: Digit DP
 *
 * Two parts:
 * 1. Count all numbers with FEWER digits than n (they're all valid).
 *    For k digits: digits.length^k choices.
 *
 * 2. Count numbers with the SAME number of digits as n that are <= n.
 *    Use digit DP: at each position, either place a digit less than n's digit
 *    (then remaining positions are free) or match n's digit exactly (stay tight).
 *
 * Time:  O(log n * |digits|)
 * Space: O(log n)
 */
public class NumbersAtMostNGivenDigitSet {

    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String num = String.valueOf(n);
        int len = num.length();
        int d = digits.length;

        int count = 0;

        // Part 1: Count all numbers with fewer digits than n.
        // For k-digit numbers, each position has 'd' choices: d^k total.
        for (int k = 1; k < len; k++) {
            count += pow(d, k);
        }

        // Part 2: Count numbers with exactly 'len' digits that are <= n.
        // Digit DP: at each position, try all digits.
        count += digitDP(0, true, num, digits);

        return count;
    }

    /**
     * Digit DP: count valid numbers from position pos onward.
     *
     * @param pos    current position in the number
     * @param tight  are we still bounded by n's prefix?
     * @param num    string form of n
     * @param digits available digits
     */
    private static int digitDP(int pos, boolean tight, String num, String[] digits) {
        if (pos == num.length()) {
            return 1; // We've formed a valid number
        }

        int limit = num.charAt(pos) - '0';
        int d = digits.length;
        int count = 0;

        for (String digit : digits) {
            int dig = digit.charAt(0) - '0';

            if (tight) {
                if (dig < limit) {
                    // Placed a digit smaller than n's: remaining positions are free
                    count += pow(d, num.length() - pos - 1);
                } else if (dig == limit) {
                    // Matched n's digit: stay tight for next position
                    count += digitDP(pos + 1, true, num, digits);
                }
                // dig > limit: skip (would exceed n)
            } else {
                // Not tight: any digit is fine, all remaining positions are free
                count += pow(d, num.length() - pos - 1);
            }
        }

        return count;
    }

    private static int pow(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] digits1 = {"1", "3", "5", "7"};
        System.out.println("digits=[1,3,5,7], n=100: " + atMostNGivenDigitSet(digits1, 100)); // 20

        String[] digits2 = {"1", "4", "9"};
        System.out.println("digits=[1,4,9], n=1000000000: " + atMostNGivenDigitSet(digits2, 1000000000)); // 29523

        String[] digits3 = {"7"};
        System.out.println("digits=[7], n=8: " + atMostNGivenDigitSet(digits3, 8)); // 1
    }
}
