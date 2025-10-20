package leetcode.graph.traversal;

public class MaxAreaOfIsland {

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), find the maximum area of an island in the grid.
     *
     * @param grid 2d grid map
     * @return maximum area of an island
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, DFS(i, j, grid));

                }
            }
        }
        return maxArea;
    }

   /**
     * Depth First Search to mark all connected land cells as visited.
     *
     * @param row row index
     * @param col column index
     * @param grid 2d grid map
     * @return area of the island
     */
    private static int DFS(int row, int col, int[][] grid) {
        // Check if the cell is within the grid and is a land cell
        if (row < grid.length && col < grid[0].length && row >= 0 && col >= 0 && grid[row][col] == 1) {
            grid[row][col] = 0;

            return 1 + DFS(row, col + 1, grid) +
                    DFS(row, col - 1, grid) +
                    DFS(row + 1, col, grid) +
                    DFS(row - 1, col, grid);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0} };
        System.out.println(maxAreaOfIsland(grid));
    }
}
