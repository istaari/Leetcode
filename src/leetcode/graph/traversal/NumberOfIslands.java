package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode Problem: 200. Number of Islands
 * <p>
 * Question:
 * Given an `m x n` 2D binary grid `grid` which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 300
 * - grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {

    // -----------------------------------------------DFS-----------------------------------------------------------//

    public static int numIslandsDFS(char[][] grid) {
        // Get the dimensions of the grid.
        int row = grid.length;
        if (row == 0) return 0; // Handle empty grid
        int col = grid[0].length;

        int count = 0; // Initialize island count.

        // Iterate through every cell in the grid.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // If we find a '1' (land), it means we've found the start of a new island.
                if (grid[i][j] == '1') {
                    // Start a Depth-First Search (DFS) to find all connected land parts of this island.
                    DFS(i, j, grid);
                    // After the DFS, the entire island is "sunk" (marked as '0'), so we increment the count.
                    count++;
                }
            }
        }
        return count;
    }

    public static void DFS(int row, int col, char[][] grid) {
        // Base case: Stop the recursion if we go out of bounds (off the grid)
        // or if we hit water ('0'), or if we hit an already visited cell (also '0').
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        // Mark the current land cell as visited by changing '1' to '0'.
        // This prevents infinite loops and ensures we don't count it again.
        grid[row][col] = '0';

        // Recursively call DFS on all four adjacent neighbors (right, left, down, up).
        DFS(row, col + 1, grid); // right
        DFS(row, col - 1, grid); // left
        DFS(row + 1, col, grid); // down
        DFS(row - 1, col, grid); // up
    }


    // -----------------------------------------------BFS-----------------------------------------------------------//

    public static int numIslandsBFS(char[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        int count = 0;

        // Iterate through every cell in the grid.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // If we find a '1' (land), it's the start of a new island.
                if (grid[i][j] == '1') {
                    // Increment the island count.
                    count++;
                    // Start a Breadth-First Search (BFS) to find all connected land.
                    BFS(i, j, grid);
                }
            }
        }
        return count;
    }

    public static void BFS(int i, int j, char[][] grid) {
        // Define the four possible directions (right, left, down, up).
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<RowColPair> queue = new LinkedList<>();

        // Add the starting cell to the queue.
        queue.add(new RowColPair(i, j));
        // Mark the starting cell as visited ('0') to avoid re-processing it.
        grid[i][j] = '0';

        // Continue the search as long as there are cells in the queue.
        while (!queue.isEmpty()) {
            // Dequeue the next cell to process.
            RowColPair rowColPair = queue.poll();

            // Check all four neighbors of the current cell.
            for (int[] direction : directions) {
                int row = rowColPair.row() + direction[0];
                int col = rowColPair.col() + direction[1];

                // Check if the neighbor is valid:
                // 1. Is it within the grid bounds (row >= 0, col >= 0, etc.)?
                // 2. Is it a land cell ('1')? (If it's '0', it's water or already visited).
                if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == '1') {
                    // If it's a valid, unvisited land cell:
                    // 1. Mark it as visited.
                    grid[row][col] = '0';
                    // 2. Add it to the queue to process its neighbors later.
                    queue.add(new RowColPair(row, col));
                }
            }
        }
    }

    public record RowColPair(int row, int col) {
    }


    static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        // This grid has only one island.
        System.out.println(numIslandsBFS(grid)); // Expected: 1
    }
}