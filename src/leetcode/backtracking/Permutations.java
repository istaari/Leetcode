package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> helper(int[] nums, List<Integer> tempList, List<List<Integer>> result) {

        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return result;
        }

        for (int num : nums) {
            if (tempList.contains(num)) continue;

            tempList.add(num);
            helper(nums, tempList, result);
            tempList.removeLast();
        }

        return result;
    }


    public static List<List<Integer>> permute(int[] nums) {
        return helper(nums, new ArrayList<>(), new ArrayList<>());
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println(permute(nums));
    }
}
