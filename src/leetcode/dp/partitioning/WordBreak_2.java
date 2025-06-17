package leetcode.dp.partitioning;

import java.util.*;

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
