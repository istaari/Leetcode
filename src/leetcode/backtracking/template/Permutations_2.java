package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode Problem 47: Permutations II
 *
 * Question:
 * Given a collection of numbers, nums, that might contain duplicates, return all
 * possible unique permutations in any order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permutations_2 {


    public static void helper(int[] nums, List<Integer> list, List<List<Integer>> result, boolean[] visited) {
        // Base case: If a full permutation is formed, add it to the results.
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list)); // Add a copy of the current permutation
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // If the element is already used in the current path, skip it.
            if (visited[i]) continue;

            // --- Key logic for handling duplicates ---
            // If the current element is the same as the previous one, and the previous
            // one has NOT been used yet in this path (i.e., it was backtracked from),
            // skip the current element. This enforces a fixed picking order for duplicates
            // to prevent generating the same permutation multiple times.
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;


            // --- Backtracking Pattern ---
            // 1. Choose: Mark the element as visited and add it.
            visited[i] = true;
            list.add(nums[i]);
            // 2. Explore: Recurse to build the rest of the permutation.
            helper(nums, list, result, visited);
            // 3. Unchoose: Backtrack by removing the element and
            list.remove(list.size() - 1);
            //    unmarking it as visited.
            visited[i] = false;
        }
    }


    public static List<List<Integer>> permute(int[] nums) {
        // A boolean array to keep track of which elements have been used in the current path.
        boolean[] visited = new boolean[nums.length];
        // The list to store all unique permutations.
        List<List<Integer>> result = new ArrayList<>();

        // Sort the array to handle duplicates effectively.
        Arrays.sort(nums);
        // Start the backtracking process.
        helper(nums, new ArrayList<>(), result, visited);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permute(nums));
    }
}