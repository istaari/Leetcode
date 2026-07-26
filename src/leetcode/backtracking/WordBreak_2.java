package leetcode.backtracking;

import java.util.*;

/*
 * LC 140 - Word Break II
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to
 * construct all possible sentences where each word is a valid dictionary word.
 * Return all such sentences in any order.
 *
 * Note: the same word in wordDict may be reused multiple times.
 *
 * Example 1:
 *   Input:  s="catsanddog", wordDict=["cat","cats","and","sand","dog"]
 *   Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 *   Input:  s="pineapplepenapple", wordDict=["apple","pen","applepen","pine","pineapple"]
 *   Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 *
 * Example 3:
 *   Input:  s="catsandog", wordDict=["cats","dog","sand","and","cat"]
 *   Output: []
 *
 * Constraints:
 *   1 <= s.length <= 20
 *   1 <= wordDict.length <= 1000
 *   1 <= wordDict[i].length <= 10
 *   s and wordDict[i] consist of lowercase English letters only.
 *
 * Approach: backtracking — at each position try all prefixes that exist in wordSet.
 *   If a prefix matches, recurse on the remaining string. Collect complete paths.
 * Time: O(n * 2^n) worst case   Space: O(n) recursion depth
 */

public class WordBreak_2 {

    private static void backtrack(String s, int start, Set<String> wordSet, List<List<String>> wordList, List<String> temp) {
        if (start >= s.length()) {
            wordList.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (wordSet.contains(word)) {
                temp.add(word);
                backtrack(s, i + 1, wordSet, wordList, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        List<List<String>> wordList = new ArrayList<>();
        backtrack(s, 0, wordSet, wordList, new ArrayList<>());
        List<String> result = new ArrayList<>();

        for (List<String> word : wordList) {
            StringBuilder sb = new StringBuilder();
            for (String w : word) {
                sb.append(w).append(" ");
            }

            result.add(sb.toString().trim());
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreak(s1, wordDict1));

        // Example 2
        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak(s2, wordDict2));
    }

}
