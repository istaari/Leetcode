package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode Problem 40: Combination Sum II
 *
 * Question:
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example:
 * Input: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
 * Output:
 * [
 * [1, 1, 6],
 * [1, 2, 5],
 * [1, 7],
 * [2, 6]
 * ]
 */
public class CombinationSum {
    static List<List<Integer>> result = new ArrayList<>();

    public static void helper(int[] candidates, int target, List<Integer> combination, int start) {
        // If the target is less than 0, this path is not a solution.
        if (target < 0) return;

        // Base case: A valid combination is found when the target reaches 0.
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates to avoid creating duplicate combinations.
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // --- Backtracking Pattern ---

            // 1. Choose the current number
            combination.add(candidates[i]);
            // 2. Explore further, passing i + 1 as the new start to ensure each number is used only once.
            helper(candidates, target - candidates[i], combination, i + 1);
            // 3. Unchoose (backtrack)
            combination.remove(combination.size() - 1);
        }
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        result.clear(); // Clear previous results as 'result' is a static field.
        Arrays.sort(candidates); // Sort candidates to handle duplicates effectively.
        helper(candidates, target, new ArrayList<>(), 0);
        return result;
    }


    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSum(candidates, target));
    }
}