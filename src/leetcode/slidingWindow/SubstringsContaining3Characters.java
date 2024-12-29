package leetcode.slidingWindow;

public class SubstringsContaining3Characters {

    public static int numberOfSubstrings(String s) {
        int[] charCount = new int[3];
        int result = 0;
        int left = 0;
        int n = s.length();

        for (int right = 0; right < n; ++right) {
            charCount[s.charAt(right) - 'a']++;

            while (charCount[0] > 0 && charCount[1] > 0 && charCount[2] > 0) {
                charCount[s.charAt(left) - 'a']--;
                left++;
            }

            // Add the number of valid substrings ending at the current position
            result += left;
        }

        return result;
    }

    public static int numberOfSubstrings0(String s) {
        int result = 0;
        int left = 0;
        int right = 0;

        int[] count = new int[3];

        while (right < s.length()) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(left) - 'a']--;

                // Add the number of valid substrings from left - right to end
                result += s.length() - right;
                left++;
            }

            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(numberOfSubstrings(s));
    }

}
