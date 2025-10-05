package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a concrete and runnable backtracking template.
 * It solves the classic LeetCode problem: "Combinations".
 * (Find all combinations of size k from numbers 1 to n).
 */
public class Combinations {


    private static void backtrack(List<List<Integer>> results, List<Integer> path, int n, int k, int start) {
        // 1. Base Case: Have we reached a valid, complete solution?
        // If the current path has k numbers, we've found a valid combination.
        if (path.size() == k) {
            results.add(new ArrayList<>(path)); // Add a *copy* of the path.
            return;
        }

        // Optional Pruning: If it's impossible to form a combination of size k from the remaining numbers, stop.
        // k - path.size() is the number of elements we still need.
        // n - i + 1 is the number of elements available to choose from.
        // if (k - path.size() > n - i + 1) { continue; or break; }

        // 2. Iterate through all possible choices for the current step.
        // The choices are numbers from 'start' up to 'n'.
        for (int i = start; i <= n; i++) {
            // 3. Choose: Add the current number to our path.
            path.add(i);

            // 4. Explore: Recurse with the updated path.
            // The next choice must be greater than the current one (i + 1) to avoid duplicates.
            backtrack(results, path, n, k, i + 1);

            // 5. Unchoose (Backtrack): Remove the choice to explore other branches.
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        List<List<Integer>> results = new ArrayList<>();
        // Start the backtracking process from the number 1.
        backtrack(results, new ArrayList<>(), n, k, 1);

        System.out.println("All combinations of " + k + " numbers from 1 to " + n + ":");
        // Expected output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
        System.out.println(results);
    }

}