package neetcode150.arrayAndHashing;

import java.util.*;

@SuppressWarnings("all")
public class GroupAnagrams_49 {

    /**
     * sort each string and use it as a key in a map to group anagrams
     *
     * @param strs an array of strings
     * @return a list of lists of strings
     */

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {

            char[] wordChar = word.toCharArray();
            Arrays.sort(wordChar);   // This will take O(nlogn) time complexity
            String key = new String(wordChar);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"aaa", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

}