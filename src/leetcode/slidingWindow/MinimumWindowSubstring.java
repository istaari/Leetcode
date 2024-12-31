package leetcode.slidingWindow;

public class MinimumWindowSubstring {


    public static String minWindow(String s, String t) {

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }


        int[] count = new int[128];
        // Count the frequency of target
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)]++;
        }

        int startIndex = - 1;
        int minLen = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int counter = 0;

        while (right < s.length()) {

            if (count[s.charAt(right)] > 0) {
                counter++;
            }

            count[s.charAt(right)]--;

            while (counter == t.length()) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) {
                    counter--;
                }

                left++;
            }

            right++;
        }

        return (startIndex == -1) ? "" : s.substring(startIndex, startIndex + minLen);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }


}
