package leetcode.greedy.intervals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 763. Partition Labels
 * https://leetcode.com/problems/partition-labels/
 *
 * You are given a string s. We want to partition the string into as many parts
 * as possible so that each letter appears in at most one part.
 *
 * Note that the partition is done so that after concatenating all the parts in
 * order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 * Example 1:
 *   Input: s = "ababcbacadefegdehijhklij"
 *   Output: [9,7,8]
 *   Explanation: "ababcbaca", "defegde", "hijhklij"
 *   Each letter appears in at most one part.
 *
 * Example 2: s = "eccbbbbdec" -> [10]
 *
 * Constraints:
 *   1 <= s.length <= 500
 *   s consists of lowercase English letters.
 *
 * ---
 * Approach: Greedy — extend partition to last occurrence
 *
 * 1. Record the last index of each character.
 * 2. Iterate through s, tracking the farthest last occurrence ("end") of any
 *    character seen so far in the current partition.
 * 3. When i == end, we've found a valid partition boundary. Record its size.
 *
 * Think of each character as an interval [first, last]. The problem becomes
 * merging overlapping intervals and reporting their sizes.
 *
 * Time:  O(n)
 * Space: O(1) (26 letters)
 */
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
