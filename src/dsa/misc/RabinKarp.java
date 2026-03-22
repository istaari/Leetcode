package dsa.misc;


public class RabinKarp {

    void search(String text, String pattern) {
        int PRIME = 1000_000_000 + 7; // 10 ^ 9 + 7
        int m = pattern.length();
        int n = text.length();

        int base = 256; // Rabin-Karp uses 256 as the base because it's designed for ASCII-based string hashing
        int patternHash = 0; // Hash value for pattern
        int textHash = 0; // Hash value for text substring

        // Compute initial window hash values for a pattern and text
        // This loop builds the hash left to right
        // This is called Horner’s method, a faster way to evaluate polynomials without repeated exponentiation
        for (int i = 0; i < m; i++) {
            patternHash = (base * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (base * textHash + text.charAt(i)) % PRIME;
        }


        int h = 1;  // Compute (base^(m-1)) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % PRIME;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                boolean matched = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        matched = false;
                        break;
                    }
                }
                if (matched) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Compute next hash value including next character
            if (i < n - m) {
                textHash = (base * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                if (textHash < 0) textHash += PRIME; // Handles Negative Number
            }
        }
    }

    public static void main(String[] args) {
        String text = "abcdxyz";
        String pattern = "xyz";
        new RabinKarp().search(text, pattern);
    }
}

