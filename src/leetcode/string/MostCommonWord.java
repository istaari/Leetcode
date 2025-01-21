package leetcode.string;

import java.util.*;

public class MostCommonWord {

    // https://leetcode.com/problems/most-common-word/description/
    // https://leetcode.com/problems/most-common-word/solutions/411380/java-summary-of-usages-of-split-and-replace-vs-replaceall/
    // String Preprocessing

    public static String mostCommonWord(String paragraph, String[] banned) {
        // String[] words paragraph.split("[ !?',;.]+");

        paragraph = paragraph.replace("!", " ");
        paragraph = paragraph.replace("?", " ");
        paragraph = paragraph.replace("'", " ");
        paragraph = paragraph.replace(",", " ");
        paragraph = paragraph.replace(";", " ");
        paragraph = paragraph.replace(".", " ");

        String[] words = paragraph.split(" ");
        Set<String> set = new HashSet<>(List.of(banned));

        Map<String, Integer> map = new HashMap<>();


        for (String word : words) {
            String lWord = word.toLowerCase();
            if (!set.contains(lWord) && !lWord.isEmpty()) {
                map.put(lWord, map.getOrDefault(lWord, 0) + 1);
            }
        }

        int count = 0;
        String result = "";

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() > count) {
                result = entry.getKey();
                count = entry.getValue();
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = {"bob", "hit"};
        System.out.println(mostCommonWord(paragraph, banned));
    }
}
