package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges_994 {

    final static int FRESH_ORANGE = 1;
    final static int ROTTEN_ORANGE = 2;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private record Pair(int x, int y) { }

    /**
     * In a given grid, each cell can have one of three values: the value 0 representing an empty cell, the value 1 representing a fresh orange, or the value 2 representing a rotten orange.
     *
     * @param grid 2D grid of oranges
     * @return the minimum number of minutes that must elapse until no cell has a fresh orange
     */
    public static int orangesRotting(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int freshOranges = 0;
        int minutes = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == FRESH_ORANGE) freshOranges++;
                if (grid[i][j] == ROTTEN_ORANGE) queue.add(new Pair(i, j));
            }
        }

        // If there are no fresh oranges, return 0
        if (freshOranges == 0) return 0;

        while (!queue.isEmpty() && freshOranges > 0) {
            minutes++;
            int size = queue.size();
            // To mark all the adjacent fresh oranges as rotten level by level
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                for (int[] direction : directions) {
                    assert pair != null;
                    int x = pair.x() + direction[0];
                    int y = pair.y() + direction[1];

                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != FRESH_ORANGE) continue;

                    grid[x][y] = ROTTEN_ORANGE;
                    queue.add(new Pair(x, y));
                    freshOranges--;
                }
            }
        }

        return (freshOranges == 0) ? minutes : -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid)); // 4
        grid = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println(orangesRotting(grid)); // -1
        grid = new int[][]{{1, 2}};
        System.out.println(orangesRotting(grid)); // -1
    }

}
