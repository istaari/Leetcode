package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

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
