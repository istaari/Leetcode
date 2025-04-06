package dsa;

public class KMP {

    // Compute LPS function

    // The LPS (Longest Prefix Suffix) table stores the length of the longest proper prefix and longest proper suffix
    // Proper Prefix: A prefix of a string that is not equal to the full string itself.
    // Proper Suffix: A suffix of a string that is not equal to the full string itself.

    // Interpretation of LPS Table:
    // For a given pattern P[0...i], the value at LPS[i] represents:
    // The length of the longest proper prefix which is also a suffix in the substring P[0...i].

    // Step-by-step Explanation:
    // Index 0 ("A") → No proper prefix & suffix → LPS[0] = 0
    // Index 1 ("AB") → No match → LPS[1] = 0
    // Index 2 ("ABA") → "A" is both prefix & suffix → LPS[2] = 1
    // Index 3 ("ABAB") → "AB" is both prefix & suffix → LPS[3] = 2
    // Index 4 ("ABABA") → "ABA" is both prefix & suffix → LPS[4] = 3
    // Index 5 ("ABABAC") → No match → LPS[5] = 0

    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int i = 1;
        int len = 0; // Stores proper prefix and suffix length

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {

                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // KMP Search Function
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPS(pattern);
        int i = 0, j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "AAAAAAAAAAAAAAAAAB";
        String pattern = "AAAAAB";
        KMPSearch(text, pattern);
    }

}
