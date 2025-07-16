package dsa;

class NaiveStringMatch {

    void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        /*
         * why loop condition is n-m, to find the window comparison
         *
         * text: A B A B D
         * index: 0 1 2 3 4
         * pattern: A B A
         * length: n = 5, m = 3
         *
         * Valid i values for comparison:
         *
         * i = 0 → text[0..2]
         * i = 1 → text[1..3]
         * i = 2 → text[2..4]
         * i = 3 → text[3..5] ❌ Invalid! text[5] is out of bounds.
         *
         */
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args) {
        String text = "the quick brown fox jumps over the lazy dog the quick brown fox";
        String pattern = "the quick brown fox";

        // Pattern found at index 0
        // Pattern found at index 44
        new NaiveStringMatch().search(text, pattern);
    }
}
