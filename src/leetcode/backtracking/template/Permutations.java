package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode Problem 46: Permutations
 *
 * Question:
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */
public class Permutations {

    public static List<List<Integer>> helper(int[] nums, List<Integer> tempList, List<List<Integer>> result) {
        // Base case: If the temporary list's size equals the input array's size,
        // a complete permutation has been found.
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // Add a copy of the permutation to the result
            return result;
        }

        // Iterate through all numbers in the input array for each position.
        for (int num : nums) {

            // If the number is already in the current permutation, skip it to avoid duplicates.
            if (tempList.contains(num)) {
                continue;
            }

            // --- Backtracking Pattern ---

            // 1. Choose: Add the number to the current permutation.
            tempList.add(num);
            // 2. Explore: Recurse to find the next number.
            helper(nums, tempList, result);
            // 3. Unchoose: Backtrack to explore other possibilities.
            tempList.remove(tempList.size() - 1);
        }

        return result;
    }


    public static List<List<Integer>> permute(int[] nums) {
        // Calls the helper with an empty temporary list and an empty result list.
        return helper(nums, new ArrayList<>(), new ArrayList<>());
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

}