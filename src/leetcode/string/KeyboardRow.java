package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardRow {

    public static String[] findWords(String[] words) {
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";

        List<String> result = new ArrayList<>();


        for (String word : words) {
            String lWord = word.toLowerCase();
            int next = 0;

            while (next < word.length() && row1.contains(String.valueOf(lWord.charAt(next)))) {
                next++;
            }

            if (next == word.length()) {
                result.add(word);
                continue;
            }


            next = 0;
            while (next < word.length() && row2.contains(String.valueOf(lWord.charAt(next)))) {
                next++;
            }


            if (next == word.length()) {
                result.add(word);
                continue;
            }


            next = 0;
            while (next < word.length() && row3.contains(String.valueOf(lWord.charAt(next)))) {
                next++;
            }

            if (next == word.length()) {
                result.add(word);
            }

        }

        return result.toArray(new String[0]);

    }

    public static void main(String[] args) {
        String[] words = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        System.out.println(Arrays.toString(findWords(words)));
    }
}
