package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Rat in a Maze (GFG Classic / Backtracking)
 *
 * Given an n x n binary matrix where 1 = open path and 0 = blocked, a rat starts
 * at (0,0) and must reach (n-1, n-1). Find all possible paths the rat can take.
 * The rat can move in 4 directions: Down (D), Up (U), Left (L), Right (R).
 * A cell cannot be visited more than once in a single path.
 *
 * Return all paths in lexicographic order as strings of direction characters.
 *
 * Example:
 *   Input:  [[1,0,0,0],[1,1,0,1],[1,1,0,0],[0,1,1,1]]
 *   Output: ["DDRDRR","DRDDRR"]
 *
 * Constraints:
 *   2 <= n <= 5
 *   grid[i][j] is 0 or 1
 *   grid[0][0] == 1 (start is always open)
 *
 * Approach: DFS backtracking — mark cell as 0 (visited) before recursing,
 *   restore to 1 after returning to allow other paths to reuse the cell.
 * Time: O(4^(n²))   Space: O(n²) recursion stack
 */

public class RatInMaze {

    public static ArrayList<String> findSum(int[][] arr, int n) {
        ArrayList<String> result = new ArrayList<>();
        if (arr.length == 0 || arr[0][0] == 0)
            return result;

        helper(arr, n, n, 0, 0, "", result);
        return result;
    }

    public static void helper(int[][] arr, int m, int n, int row, int col, String path, List<String> result) {
        if (row == m - 1 && col == n - 1) {
            result.add(path);
        }

        if (row < 0 || row >= m || col < 0 || col >= n || arr[row][col] == 0)
            return;

        arr[row][col] = 0; // Mark as visited

        helper(arr, m, n, row + 1, col, path + "D", result); // Down
        helper(arr, m, n, row - 1, col, path + "U", result); // Up
        helper(arr, m, n, row, col - 1, path + "L", result); // Left
        helper(arr, m, n, row, col + 1, path + "R", result); // Right

        arr[row][col] = 1; // Reset the cell to its original value
    }

    public static void main(String[] args) {
        // Initialize the matrix as a 2D array
        int[][] arr = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 }
        };

        System.out.println(findSum(arr, arr.length));
    }

}
