package leetcode.backtracking;

/*
 * LC 463 - Island Perimeter
 *
 * You are given a row x col grid where grid[i][j] = 1 (land) or 0 (water).
 * Grid cells are connected horizontally/vertically. There is exactly one island
 * (no lakes inside). Find and return the perimeter of the island.
 *
 * Example 1:
 *   Input:  [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 *   Output: 16
 *
 * Example 2:
 *   Input:  [[1]]
 *   Output: 4
 *
 * Constraints:
 *   row == grid.length,  col == grid[i].length
 *   1 <= row, col <= 100
 *   grid[i][j] is 0 or 1. There is exactly one island.
 *
 * Approach: DFS from first land cell. Each step contributes 1 to perimeter if
 *   it hits a boundary or water cell. Mark visited cells as -1 to avoid revisiting.
 * Time: O(m*n)   Space: O(m*n) recursion stack
 */

public class IslandPerimeter {

    @SuppressWarnings("all")
    public static int helper(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;

        if (grid[i][j] == 0) return 1;

        if(grid[i][j] == -1) return 0;

        grid[i][j] = -1;

        int result =  helper(grid, i + 1, j, m, n) +
                helper(grid, i - 1, j, m, n) +
                helper(grid, i, j + 1, m, n) +
                helper(grid, i, j - 1, m, n);

        return result;
    }


    public static int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return helper(grid, i, j, m, n);
                }
            }
        }

        return 0;
    }


    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        System.out.println(islandPerimeter(grid));
    }

}
