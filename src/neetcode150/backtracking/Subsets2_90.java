package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2_90 {


    /**
     * Backtracking approach.
     * The idea is to generate all possible subsets using backtracking.
     * The difference between this problem and Subsets_78 is that this problem contains duplicates.
     * To avoid duplicates, we need to sort the input array and skip duplicates.
     *
     * @param nums input array
     * @return result list
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), result, 0);
        return result;
    }

    public static void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates

            list.add(nums[i]);
            backtrack(nums, list, result, i + 1);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3};
        System.out.println(subsetsWithDup(nums));
    }

}
