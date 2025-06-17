package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2_40 {

    /**
     * @param candidates sorted array
     * @param list       current list
     * @param result     result list
     * @param rem        remaining sum
     * @param start      start index
     */
    public static void backtrack(int[] candidates, List<Integer> list, List<List<Integer>> result, int rem, int start) {
        if (rem < 0) {
            return;
        }

        if (rem == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // to skip duplicates

            list.add(candidates[i]);
            backtrack(candidates, list, result, rem - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, new ArrayList<>(), result, target, 0);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(nums, 8));
    }
}
