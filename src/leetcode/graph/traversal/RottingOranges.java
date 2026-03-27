package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 *
 * You are given an m x n grid where each cell can have one of three values:
 *   0 — empty cell
 *   1 — fresh orange
 *   2 — rotten orange
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten
 * orange becomes rotten. Return the minimum number of minutes that must elapse
 * until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Example 1:
 *   Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 *   Output: 4
 *
 * Example 2:
 *   Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 *   Output: -1 (bottom-left orange is unreachable)
 *
 * Constraints:
 *   m == grid.length, n == grid[i].length
 *   1 <= m, n <= 10
 *
 * ---
 * Approach: Multi-source BFS
 *
 * 1. Enqueue ALL rotten oranges at once (multi-source).
 * 2. BFS level by level. Each level = 1 minute.
 * 3. Each rotten orange spreads to adjacent fresh oranges.
 * 4. Track freshOranges count. If 0 at end -> return minutes. Else -> -1.
 *
 * This is the classic "simultaneous BFS from multiple sources" pattern.
 *
 * Time:  O(m * n)
 * Space: O(m * n)
 */
public class RottingOranges {

    final static int FRESH_ORANGE = 1;
    final static int ROTTEN_ORANGE = 2;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private record Pair(int x, int y) { }

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


    static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid)); // 4
        grid = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println(orangesRotting(grid)); // -1
        grid = new int[][]{{1, 2}};
        System.out.println(orangesRotting(grid)); // -1
    }

}
