package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    static List<List<Integer>> result = new ArrayList<>();

    public static void helper(int[] candidates, int target, List<Integer> combination, int start) {

        if (target < 0) return;

        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            if (i > start && candidates[i] == candidates[i - 1]) continue;

            combination.add(candidates[i]);
            helper(candidates, target - candidates[i], combination, i + 1);
            combination.remove(combination.size()-1);
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        result.clear();
        Arrays.sort(candidates);
        helper(candidates, target, new ArrayList<>(), 0);
        return result;
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        System.out.println(combinationSum(candidates, target));
    }
}
