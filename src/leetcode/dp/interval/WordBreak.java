package leetcode.dp.interval;

import java.util.*;

/**
 * 139. Word Break
 * https://leetcode.com/problems/word-break/
 *
 * Given a string s and a dictionary of strings wordDict, return true if s
 * can be segmented into a space-separated sequence of one or more dictionary words.
 * The same word in the dictionary may be reused multiple times.
 *
 * Example 1: Input: s = "leetcode", wordDict = ["leet","code"] -> Output: true
 * Example 2: Input: s = "applepenapple", wordDict = ["apple","pen"] -> Output: true
 * Example 3: Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"] -> Output: false
 *
 * Constraints:
 *   1 <= s.length <= 300
 *   1 <= wordDict.length <= 1000
 *   1 <= wordDict[i].length <= 20
 *
 * ---
 * Approach: DP (top-down memoization + bottom-up iterative)
 *
 * STATE:      dp[i] = true if s[0..i-1] can be segmented into dictionary words
 * BASE:       dp[0] = true (empty prefix is trivially valid)
 * TRANSITION: dp[i] = true if there exists j < i such that dp[j] == true AND s[j..i-1] is in dict
 * ANSWER:     dp[n]
 *
 * Time:  O(n^2 * k) where k = average word length for substring comparison
 * Space: O(n)
 */
public class WordBreak {

    // Top-down: can we segment s[start..end] using dictionary words?
    public static boolean helper(String s, int start, Set<String> set, Map<Integer, Boolean> memo) {
        // Base case: consumed the entire string
        if (start >= s.length())
            return true;

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        // Try every possible end point to form a word s[start..end]
        for (int end = start; end < s.length(); end++) {
            String word = s.substring(start, end + 1);

            // If this word is in the dictionary AND the rest can be segmented
            if (set.contains(word) && helper(s, end + 1, set, memo)) {
                memo.put(start, true);
                return memo.get(start);
            }
        }

        memo.put(start, false);
        return memo.get(start);
    }

    public static boolean recursive(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return helper(s, 0, set, new HashMap<>());
    }

    /**
     * dp = {
     * 8: true, // "leetcode" is fully segmented.
     * 4: true, // "code" can be segmented.
     * 0: true // "leet code" can be segmented.
     * }
     * 
     * dp[i] represents: Can the substring s[0...i-1] (the prefix of length i) be successfully segmented into valid dictionary words
     * 
     **/
    public static boolean iterative(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (dp[j] && set.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return iterative(s, wordDict);
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println(wordBreak(s, wordDict)); // Output: true

        String s2 = "applepenapple";
        List<String> wordDict2 = List.of("apple", "pen");
        System.out.println(wordBreak(s2, wordDict2)); // Output: true

        String s3 = "catsandog";
        List<String> wordDict3 = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s3, wordDict3)); // Output: false
    }

}
