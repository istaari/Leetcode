package leetcode.slidingWindow.fixedSize;

public class PermutationsInString {

    public static boolean checkInclusion(String s1, String s2) {
        int left = 0;
        int[] count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }

        for (int right = 0; right < s2.length(); right++) {
            char current = s2.charAt(right);
            count[current - 'a']--;

            while (count[current - 'a'] < 0) {
                count[s2.charAt(left) - 'a']++;
                left++;
            }

            if (right - left + 1 == s1.length()) return true;
        }


        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidboaooo";

        System.out.println(checkInclusion(s1, s2));
    }
}
