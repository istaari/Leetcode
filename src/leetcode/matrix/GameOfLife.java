package leetcode.matrix;

/*
 * LC 289 - Game of Life
 *
 * Given an m x n grid board of 0s (dead) and 1s (live cells), apply the following
 * rules simultaneously to get the next state:
 *
 *   1. Live cell with < 2 live neighbors → dies (underpopulation)
 *   2. Live cell with 2 or 3 live neighbors → lives
 *   3. Live cell with > 3 live neighbors → dies (overpopulation)
 *   4. Dead cell with exactly 3 live neighbors → becomes live (reproduction)
 *
 * Update must be done in-place. All changes happen simultaneously,
 * so the next state of a cell depends only on the current state of its neighbors.
 *
 * Example:
 *   Input:  [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 *   Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Constraints:
 *   m == board.length,  n == board[i].length
 *   1 <= m, n <= 25
 *   board[i][j] is 0 or 1
 *
 * Key trick: encode transitions in-place → 2 = was live now dead, 3 = was dead now live
 * Time: O(m*n)   Space: O(1) in-place
 */
public class GameOfLife {

    public static void gameOfLife(int[][] board) {
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveNeighbors = 0;

                for (int[] dir : directions) {
                    int row = dir[0] + i;
                    int col = dir[1] + j;
                    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
                        continue;
                    // 2 = originally live (now dying), so still counts as live neighbor
                    if (board[row][col] == 1 || board[row][col] == 2)
                        liveNeighbors++;
                }

                // dead cell with exactly 3 live neighbors → mark as becoming live
                if (board[i][j] == 0 && liveNeighbors == 3)
                    board[i][j] = 3;

                // live cell with < 2 or > 3 neighbors → mark as dying
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3))
                    board[i][j] = 2;
            }
        }

        // decode: finalize encoded states
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 3) board[i][j] = 1;
                if (board[i][j] == 2) board[i][j] = 0;
            }
    }

    public static void main(String[] args) {
        int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
        gameOfLife(board);
    }
}
