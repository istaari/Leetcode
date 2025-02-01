package leetcode.backtracking;

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
