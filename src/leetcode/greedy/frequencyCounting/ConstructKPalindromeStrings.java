package leetcode.greedy.frequencyCounting;

import java.util.HashMap;
import java.util.Map;

/**
 * 1400. Construct K Palindrome Strings
 * https://leetcode.com/problems/construct-k-palindrome-strings/
 *
 * Given a string s and an integer k, return true if you can use all the
 * characters in s to construct k palindrome strings.
 *
 * Example 1: s = "annabelle", k = 2 -> true ("anna" + "elble")
 * Example 2: s = "leetcode", k = 3 -> false
 * Example 3: s = "true", k = 4 -> true ("t","r","u","e")
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   1 <= k <= 10^5
 *
 * ---
 * Approach: Count odd-frequency characters
 *
 * Key insight: A palindrome can have at most 1 character with odd frequency (the center).
 * So to form k palindromes, we need at most k characters with odd frequency.
 *
 * Conditions:
 *   - k > s.length() -> impossible (not enough chars)
 *   - Count of odd-frequency chars > k -> impossible
 *   - Otherwise -> possible
 *
 * Time:  O(n)
 * Space: O(1) (26 letters)
 */
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
            int index = s.charAt(i) - 'a';
            counts[index]++;
            odds += counts[index] % 2 == 0 ? -1 : 1;
        }

        return odds <= k;
    }

}
