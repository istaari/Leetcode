package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands_200 {

    // -----------------------------------------------DFS-----------------------------------------------------------//

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     *
     * @param grid 2d grid map
     * @return number of islands
     */
    public static int numIslandsDFS(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    DFS(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Depth-First Search to mark all connected land cells as visited.
     *
     * @param row row index
     * @param col column index
     * @param grid 2d grid map
     */
    public static void DFS(int row, int col, char[][] grid) {
        // Check if the cell is within the grid and is a land cell
        if (row < grid.length && col < grid[0].length && row >= 0 && col >= 0 && grid[row][col] == '1') {
            grid[row][col] = '0';
            DFS(row, col + 1, grid);
            DFS(row, col - 1, grid);
            DFS(row + 1, col, grid);
            DFS(row - 1, col, grid);
        }
    }


    // -----------------------------------------------BFS-----------------------------------------------------------//

    public record RowColPair(int row, int col) { }

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     *
     * @param grid 2d grid map
     * @return number of islands
     */

    public static int numIslandsBFS(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        Queue<RowColPair> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    BFS(i, j, grid, queue);
                }
            }
        }
        return count;
    }

    public static void BFS(int i, int j, char[][] grid,  Queue<RowColPair> queue) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // Creates a pair of row and column, it represents a cell in the grid
        queue.add(new RowColPair(i, j));
        // Mark the cell as visited
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            RowColPair rowColPair = queue.poll();
            for (int[] direction : directions) {
                int row = rowColPair.row() + direction[0];
                int col = rowColPair.col() + direction[1];
                // Check if the cell is within the grid and is a land cell
                if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == '1') {
                   // Mark the cell as visited
                    grid[row][col] = '0';
                    queue.add(new RowColPair(row, col));
                }
            }
        }

    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslandsBFS(grid));
    }
}
