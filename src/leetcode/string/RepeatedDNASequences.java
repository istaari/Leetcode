package leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {

    public static List<String> findRepeatedDnaSequences(String s) {

        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();

        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append(s.charAt(i));

            if (stringBuilder.length() > 10) {
                stringBuilder.deleteCharAt(0);
            }

            if (stringBuilder.length() == 10) {

                String seq = stringBuilder.toString();
                if (visited.contains(seq)) {
                    result.add(seq);
                } else {
                    visited.add(seq);
                }

            }
        }

        return result.stream().toList();
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s));
    }


}
