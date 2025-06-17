package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations_46 {

    public static void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int num : nums) {
            if (list.contains(num)) continue;

            list.add(num);
            backtrack(nums, list, result);
            list.remove(list.size()-1);
        }
    }

    /**
     * Given a collection of distinct integers, return all possible permutations.
     *
     * @param nums array of distinct integers
     * @return List of all possible permutations
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }



    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

}
