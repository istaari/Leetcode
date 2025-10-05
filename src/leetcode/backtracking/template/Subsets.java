package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Subsets {


    /**
     * Generates all subsets using a bit manipulation approach.
     *
     * @param nums The input array of distinct integers.
     * @return A list containing all subsets.
     */
    public List<List<Integer>> subsetsBitwise(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsBitwise(nums, result);
        return result;
    }


    /**
     * The idea is to generate all possible subsets using bitwise.
     * For example, for an array [1, 2, 3], the total number of possible subsets is 2^3 = 8 subsets.
     * The subsets are:
     * 000 -> []
     * 001 -> [1]
     * 010 -> [2]
     * 011 -> [1, 2]
     * 100 -> [3]
     * 101 -> [3, 1]
     * 110 -> [3, 2]
     * 111 -> [1, 2, 3]
     *
     * @param nums   input array
     * @param result result list
     */
    private void generateSubsetsBitwise(int[] nums, List<List<Integer>> result) {
        int n = nums.length;
        // There are 2^n possible subsets, so we loop from 0 to 2^n - 1.
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // Check if the i-th bit is set in the current mask.
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
    }

    //-----------------------------------------------------------------------------

    /**
     * Generates all subsets using an iterative construction approach.
     *
     * @param nums The input array of distinct integers.
     * @return A list containing all subsets.
     */
    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsetsIterative(nums, result);
        return result;
    }

    /**
     * Helper method for the iterative approach.
     * <p>
     * <b>Approach:</b> Iterative Construction.
     * <p>
     * <b>Logic:</b> Start with an empty set in the result list. Then, iterate through each number in the input array.
     * For each number, iterate through the subsets already in the result list, create a copy of each,
     * add the current number to the copy, and add the new subset back to the result list.
     * <p>
     * Example: nums = [1, 2, 3]
     * 1. Start: result = [[]]
     * 2. Process 1: result = [[], [1]]
     * 3. Process 2: result = [[], [1], [2], [1, 2]]
     * 4. Process 3: result = [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
     * <p>
     * <b>Time Complexity:</b> O(N * 2^N). The number of subsets doubles at each step. Total steps involve copying and adding elements.
     * <b>Space Complexity:</b> O(N * 2^N). To store the output.
     *
     * @param nums   Input array.
     * @param result The list to populate with subsets.
     */
    private void generateSubsetsIterative(int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>()); // Start with the empty subset
        for (int num : nums) {
            int currentSize = result.size();
            // Create new subsets by adding the current number to all existing subsets.
            for (int i = 0; i < currentSize; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i)); // Create a copy
                newSubset.add(num);
                result.add(newSubset);
            }
        }
    }


    //-----------------------------------------------------------------------------

    /**
     * Generates all subsets using a cascading recursive backtracking approach.
     *
     * @param nums The input array of distinct integers.
     * @return A list containing all subsets.
     */
    public List<List<Integer>> subsetsBacktrackCascade(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCascade(nums, new ArrayList<>(), result, 0);
        return result;
    }


    private void backtrackCascade(int[] nums, List<Integer> temp, List<List<Integer>> result, int start) {
        result.add(new ArrayList<>(temp)); // Add the subset formed so far
        for (int i = start; i < nums.length; i++) {
            // Decision: Include nums[i]
            temp.add(nums[i]);
            // Explore further with the next elements
            backtrackCascade(nums, temp, result, i + 1);
            // Backtrack: Remove nums[i] to explore other possibilities
            temp.remove(temp.size() - 1);
        }
    }


    //-----------------------------------------------------------------------------

    /**
     * Generates all subsets using the "Pick or Not Pick" backtracking approach.
     *
     * @param nums The input array of distinct integers.
     * @return A list containing all subsets.
     */
    public List<List<Integer>> subsetsPickOrNotPick(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPickOrNotPick(nums, new ArrayList<>(), result, 0);
        return result;
    }


    private void backtrackPickOrNotPick(int[] nums, List<Integer> temp, List<List<Integer>> result, int index) {
        // Base Case: If we have considered all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // --- Path 1: Pick the current element ---
        temp.add(nums[index]);
        backtrackPickOrNotPick(nums, temp, result, index + 1);
        // Backtrack to undo the choice for the other path
        temp.remove(temp.size() - 1);
        // --- Path 2: Do NOT pick the current element ---
        backtrackPickOrNotPick(nums, temp, result, index + 1);
    }



    public static void main(String[] args) {
        Subsets solution = new Subsets();
        int[] nums = {1, 2, 3};

        System.out.println("--- Subsets for [1, 2, 3] ---");

        System.out.println("\n1. Bitwise Approach:");
        List<List<Integer>> resultBitwise = solution.subsetsBitwise(nums);
        System.out.println(resultBitwise);

        System.out.println("\n2. Iterative Approach:");
        List<List<Integer>> resultIterative = solution.subsetsIterative(nums);
        System.out.println(resultIterative);

        System.out.println("\n3. Backtracking (Cascade) Approach:");
        List<List<Integer>> resultBacktrack1 = solution.subsetsBacktrackCascade(nums);
        System.out.println(resultBacktrack1);

        System.out.println("\n4. Backtracking (Pick/Not-Pick) Approach:");
        List<List<Integer>> resultBacktrack2 = solution.subsetsPickOrNotPick(nums);
        System.out.println(resultBacktrack2);
    }
}
