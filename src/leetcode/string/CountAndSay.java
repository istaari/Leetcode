package leetcode.string;


/**
 * LeetCode Problem 38: Count and Say
 *
 * The count-and-say sequence is a sequence of digit strings defined by the
 * recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from
 * countAndSay(n-1), which is then converted into a different digit string.
 *
 * To determine how you "say" a digit string, split it into the minimal number
 * of substrings such that each substring contains exactly one unique digit. Then
 * for each substring, say the number of digits, then say the digit. Finally,
 * concatenate every said digit.
 *
 * For example, the saying of "3322251" is "two 3s, three 2s, one 5, one 1",
 * which becomes "23321511".
 *
 * Sequence Generation:
 * n=1: "1"
 * n=2: say "1" -> "one 1" -> "11"
 * n=3: say "11" -> "two 1s" -> "21"
 * n=4: say "21" -> "one 2, one 1" -> "1211"
 * n=5: say "1211" -> "one 1, one 2, two 1s" -> "111221"
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 */
public class CountAndSay {

    private static String generateNextTerm(String term) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < term.length(); i++) {
            int count = 1;
            char currentChar = term.charAt(i);

            // Look ahead to count consecutive occurrences of the same character.
            while (i + 1 < term.length() && term.charAt(i + 1) == currentChar) {
                count++;
                i++; // IMPORTANT: Increment i here to skip the counted characters.
            }

            // Append the count and the character to the result.
            result.append(count).append(currentChar);
        }

        return result.toString();
    }


    public static String countAndSayIterative(int n) {
        String result = "1";
        // Start with the first term "1" and apply the "saying" process
        // n-1 times to build up to the n-th term.
        for (int i = 1; i < n; i++) {
            result = generateNextTerm(result);
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 5;

        System.out.println("\nUsing Iterative approach for n = " + n + ":");
        System.out.println(countAndSayIterative(n));  // Expected Output: "111221"
    }

}
