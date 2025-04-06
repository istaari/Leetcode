package dsa;


public class RabinKarp {

    public static void search(String text, String pattern) {
        int PRIME = 101;
        int m = pattern.length();
        int n = text.length();
        int d = 256; // Total number of possible characters
        int patternHash = 0; // Hash value for pattern
        int textHash = 0; // Hash value for text substring
        int h = 1;

        // Compute (d^(m-1)) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % PRIME;
        }

        // Compute initial hash values for a pattern and first window of a text
        for (int i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (d * textHash + text.charAt(i)) % PRIME;
        }

        for (int i = 0; i <= n - m; i++) {
            // If hashes match, do a character-by-character check
            if (patternHash == textHash) {
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Compute next hash value (rolling hash)
            if (i < n - m) {
                textHash = (d * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                if (textHash < 0) textHash += PRIME; // Handles Negative Number
            }
        }
    }

    public static void main(String[] args) {
        String text = "ababdabcabababd";
        String pattern = "ababd";
        search(text, pattern);
    }
}

