package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum_39 {
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
            list.add(candidates[i]);
            backtrack(candidates, list, result, rem - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }


    /**
     * Picking the current element or not picking the current element
     *
     * @param candidates sorted list
     * @param result     result list
     * @param temp       temporary list
     * @param index      current index
     * @param sum        current sum
     * @param target     target sum
     */
    public static void picking(List<Integer> candidates, List<List<Integer>> result, List<Integer> temp, int index, int sum, int target) {
        if (index >= candidates.size()) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if (sum > target) {
            return;
        }

        temp.add(candidates.get(index));
        picking(candidates, result, temp, index, sum + candidates.get(index), target);
        temp.remove(temp.size() - 1);
        picking(candidates, result, temp, index + 1, sum, target);
    }


    /**
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     *
     * @param candidates the candidates array
     * @param target     the target sum
     * @return a list of all unique combinations
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        //backtrack(candidates, new ArrayList<>(), result, target, 0);
        picking(Arrays.stream(candidates).boxed().toList(), result, new ArrayList<>(), 0, 0, target);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        System.out.println(combinationSum(nums, 7));
    }
}
