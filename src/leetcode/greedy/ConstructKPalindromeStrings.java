package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class ConstructKPalindromeStrings {

    public boolean canConstruct(String s, int k) {
        if (s.length() == k) return true;
        if (k > s.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int odd = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) odd++;

            if (odd > k) return false;
        }

        return true;
    }

    public boolean canConstructOptimized(String s, int k) {
        if (s.length() == k) return true;
        if (k > s.length()) return false;

        int odds = 0;
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            odds += counts[s.charAt(i) - 'a'] % 2 == 0 ? -1 : 1;
        }

        return odds <= k;
    }

}
