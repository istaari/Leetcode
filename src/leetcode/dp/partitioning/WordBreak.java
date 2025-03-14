package leetcode.dp.partitioning;

import java.util.*;

public class WordBreak {

    public static boolean helper(String s, int start, Set<String> set, Map<Integer, Boolean> memo) {
        if (start >= s.length()) return true;

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        for (int end = start; end < s.length(); end++) {
            String word = s.substring(start, end + 1);

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
     * 8: true,  // "leetcode" is fully segmented.
     * 4: true,  // "code" can be segmented.
     * 0: true   // "leet code" can be segmented.
     * }
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
