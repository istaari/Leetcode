package leetcode.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

    public static List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastSeenIndex = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            lastSeenIndex.put(s.charAt(i), i);
        }

        int start = 0;
        int end = Integer.MIN_VALUE;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (lastSeenIndex.get(s.charAt(i)) > end) {
                end = lastSeenIndex.get(s.charAt(i));
            }

            if (end == i) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }

}
