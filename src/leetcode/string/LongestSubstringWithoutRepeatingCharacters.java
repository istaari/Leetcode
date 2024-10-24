package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int window = 0;
        int left = 0;
        int right = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            while (map.get(s.charAt(right)) > 1) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);

                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }

                left++;
            }

            window = Math.max(window, right - left + 1);
            right++;
        }

        return window;
    }


    // Comparison using map and window size function
    public static int lengthOfLongestSubstringOptimized(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            if (map.size() == right - left + 1) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                continue;
            }

            while (map.size() < right - left + 1) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                left++;
            }

            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstringOptimized(s));
    }
}
