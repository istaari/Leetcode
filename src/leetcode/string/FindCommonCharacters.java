package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommonCharacters {

    public static List<String> commonChars(String[] words) {
        int[] mainCount = new int[26];
        Arrays.fill(mainCount, Integer.MAX_VALUE);

        for (String word : words) {
            int[] count = new int[26];

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                count[index]++;
            }

            for (int i = 0; i < mainCount.length; i++) {
                mainCount[i] = Math.min(mainCount[i], count[i]);
            }
        }


        List<String> result = new ArrayList<>();
        for (int i = 0; i < mainCount.length; i++) {

            while (mainCount[i] > 0) {
                int ch = i + 'a';
                result.add(String.valueOf((char) ch));
                mainCount[i]--;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"bella", "label", "roller"};

        System.out.println(commonChars(words));
    }


}

