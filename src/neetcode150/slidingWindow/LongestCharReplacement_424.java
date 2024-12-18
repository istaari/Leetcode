package neetcode150.slidingWindow;

public class LongestCharReplacement_424 {
    public static int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int mostFreqLetter = 0;
        int left = 0;
        int right = 0;
        int max = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            freq[ch - 'A']++;
            mostFreqLetter = Math.max(mostFreqLetter, freq[ch - 'A']);

            // If my window size - mostFreqLetter > k, then shrink the left pointer
            // right - left + 1 - mostFreqLetter is the number of letters that are not the most frequent letter
            // If this number is greater than k, then a window does not satisfy the condition
            if (right - left + 1 - mostFreqLetter > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k));
    }
}
