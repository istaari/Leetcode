package leetcode.graph.traversal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindTheTownJudge {


    public static int findJudge(int n, int[][] trust) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] t : trust) {
            map.putIfAbsent(t[1], new HashSet<>());
            map.get(t[1]).add(t[0]);
        }

        int result = -1;
        for (Integer key : map.keySet()) {
            if (map.get(key).size() == n - 1 && !map.get(key).contains(key)) {
                result = key;
                map.remove(key);
                break;
            }
        }

        for (Integer key : map.keySet()) {
            if (map.get(key).contains(result)) {
                result = -1;
                break;
            }
        }

        return result;
    }


    public static int findJudgeOptimized(int n, int[][] trust) {
        int[] count = new int[n + 1];

        for (int[] t : trust) {
            count[t[0]]--;
            count[t[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(findJudge(4, trust));
    }
}
