package leetcode.string;


/**
 * LeetCode Problem 299: Bulls and Cows
 *
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is.
 * When your friend makes a guess, you provide a hint with the following format:
 *
 * The hint tells your friend how many "bulls" and "cows" they got. A "bull" is a
 * digit in the guess that is in the correct position. A "cow" is a digit in the
 * guess that is in the secret number but in the wrong position.
 *
 * Specifically, the non-bull digits in the guess that could be rearranged to match
 * the non-bull digits in the secret number are counted as cows.
 *
 * Given the secret number secret and your friend's guess guess, return the hint.
 *
 * Example 1:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are at index 1 (8). Cows are 0, 1, and 7.
 *
 * Example 2:
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bull is at index 1 (1). The guess has three 1s but the secret has only
 * two. One 1 from the guess can be matched with a 1 from the secret to form a cow.
 */
public class BullsCows {

    // Uses net difference counting to track unmatched digits.
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;

        // `count` array acts as a frequency map for digits 0-9.
        // A positive value count[i] means we've seen digit `i` more times in `secret`.
        // A negative value count[i] means we've seen digit `i` more times in `guess`.
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));

            if (s == g) {
                bulls++;
            } else {

                // If count[s] is negative, it means the `guess` string has an unmatched `s`
                // from a previous iteration. The current `s` from `secret` can now form a cow.
                if (count[s] < 0) {
                    cows++;
                }
                // If count[g] is positive, it means the `secret` string has an unmatched `g`
                // from a previous iteration. The current `g` from `guess` can now form a cow.
                if (count[g] > 0) {
                    cows++;
                }

                // Increment for the digit seen in `secret`.
                count[s]++;
                // Decrement for the digit seen in `guess`.
                count[g]--;
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        String secret1 = "1807";
        String guess1 = "7810";
        System.out.println("Secret: " + secret1 + ", Guess: " + guess1);
        System.out.println("Hint: " + getHint(secret1, guess1)); // Expected: "1A3B"

        String secret2 = "1123";
        String guess2 = "0111";
        System.out.println("\nSecret: " + secret2 + ", Guess: " + guess2);
        System.out.println("Hint: " + getHint(secret2, guess2)); // Expected: "1A1B"

        String secret3 = "11";
        String guess3 = "10";
        System.out.println("\nSecret: " + secret3 + ", Guess: " + guess3);
        System.out.println("Hint: " + getHint(secret3, guess3)); // Expected: "1A0B"
    }
}
