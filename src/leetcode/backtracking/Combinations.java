package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static void helper(int n, int k, int start, List<List<Integer>> result, List<Integer> temp) {

        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {

            if (temp.contains(i)) continue;

            temp.add(i);
            helper(n, k, i + 1, result, temp);
            temp.remove(temp.size() - 1);

        }

    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        helper(n, k, 1, result, new ArrayList<>());

        return result;
    }


    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        System.out.println(combine(n, k)); // [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    }
}
