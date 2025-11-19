package leetcode.graph.traversal;

/**
 * LeetCode Problem: 695. Max Area of Island
 *
 * Question:
 * You are given an `m x n` binary grid `grid`. An island is a group of `1`'s (representing land)
 * connected 4-directionally (horizontal or vertical). You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value `1` in the island.
 * Return the maximum area of an island in `grid`. If there is no island, return `0`.
 *
 * Example:
 * Input: grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * The largest island has an area of 6.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 50
 * - grid[i][j] is 0 or 1.
 */
public class MaxAreaOfIsland {

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), find the maximum area of an island in the grid.
     *
     * @param grid 2d grid map
     * @return maximum area of an island
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;

        int maxArea = 0; // Tracks the largest area found so far.

        // Iterate through every cell in the grid.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // If we find a '1', it's the start of a new, unvisited island.
                if (grid[i][j] == 1) {
                    // Start a DFS to find the area of this new island.
                    // The DFS will "sink" the island by setting its cells to 0.
                    int currentArea = DFS(i, j, grid);
                    // Update the maxArea if this island is the largest we've seen.
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        return maxArea;
    }


    private static int DFS(int row, int col, int[][] grid) {
        // --- Base Case (Stop Recursion) ---
        // Stop if we are:
        // 1. Out of bounds (top, bottom, left, or right)
        // 2. On a water cell ('0') or an already visited cell (also '0')
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
            return 0; // This cell contributes 0 to the area.
        }

        // --- Recursive Step ---

        // 1. Mark the current cell as visited by "sinking" it (set to 0).
        //    This is crucial to prevent infinite loops.
        grid[row][col] = 0;

        // 2. The area of the current island is:
        //    1 (for the current cell)
        //    + the area of the island part to the right
        //    + the area of the island part to the left
        //    + the area of the island part below
        //    + the area of the island part above
        return 1 + DFS(row, col + 1, grid) +  // right
                DFS(row, col - 1, grid) +  // left
                DFS(row + 1, col, grid) +  // down
                DFS(row - 1, col, grid);   // up
    }


    static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        // The DFS will be called on (0,0) and will find the one large island.
        // Let's trace a small part:
        // DFS(0,0) returns 1 + DFS(0,1) + DFS(-1,0) + DFS(1,0) + DFS(0,-1)
        // DFS(0,0) returns 1 + (1 + DFS(0,2) + ...) + 0 + (1 + DFS(1,1) + ...) + 0
        // This continues, summing all '1's in the connected component.
        System.out.println(maxAreaOfIsland(grid)); // Expected: 9
    }
}