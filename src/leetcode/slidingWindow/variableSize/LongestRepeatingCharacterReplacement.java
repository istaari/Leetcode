package leetcode.slidingWindow.variableSize;

public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];

        int left = 0;
        int right = 0;
        int result = 0;
        int mostFrequent = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            count[rightChar - 'A']++;
            mostFrequent = Math.max(mostFrequent, count[rightChar - 'A']);

            while (left < right && (right - left + 1) - mostFrequent > k) {
                char leftChar = s.charAt(left);
                count[leftChar - 'A']--;

                left++;
            }

            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }


    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        System.out.println(characterReplacement(s, k));
    }


}
