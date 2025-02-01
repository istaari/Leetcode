package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString {

    public static String reorganizeString(String s) {
        int maxFrequency = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            maxFrequency = Math.max(maxFrequency, map.get(s.charAt(i)));
        }
        // Max Heap on the key of map
        Queue<Character> queue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        // Push all the key in queue
        queue.addAll(map.keySet());

        StringBuilder result = new StringBuilder();
        while (queue.size() >= 2) {
            Character ch1 = queue.poll();
            Character ch2 = queue.poll();
            result.append(ch1);
            result.append(ch2);
            map.put(ch1, map.get(ch1) - 1);
            map.put(ch2, map.get(ch2) - 1);

            if (map.get(ch1) > 0) {
                queue.add(ch1);
            }
            if (map.get(ch2) > 0) {
                queue.add(ch2);
            }
        }

        if (queue.isEmpty())  return result.toString();

        Character ch = queue.poll();
        if (map.get(ch) > 1)  return ""; // If count is greater than 1 than cannot reorganize

        return result.append(ch).toString();
    }


    public static String reorganizeStringSort(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());
        // Solution does not exist,  maxFrequency >  (n + 1) / 2
        if (freqMap.get(maxHeap.peek()) > (s.length() + 1) / 2) {
            return "";
        }

        char[] result = new char[s.length()];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            // First fills the even index
            // Then it fills the odd index
            for (int j = 0; j < freqMap.get(c); j++) {
                if (index >= s.length()) index = 1; // This will only execute once
                result[index] = c;
                index += 2;
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String s = "aaabc";
        System.out.println(reorganizeStringSort(s));
    }

}
