package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations_2 {

    public static void helper(int[] nums, List<Integer> list, List<List<Integer>> result, boolean[] visited) {

        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) continue;

            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            list.add(nums[i]);

            helper(nums, list, result, visited);

            list.removeLast();
            visited[i] = false;

        }

    }


    public static List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        helper(nums, new ArrayList<>(), result, visited);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};

        System.out.println(permute(nums));
    }
}

