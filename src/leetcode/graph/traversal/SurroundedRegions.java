package leetcode.graph.traversal;

import java.util.Arrays;

/**
 * 130. Surrounded Regions
 * https://leetcode.com/problems/surrounded-regions/
 *
 * Given an m x n matrix board containing 'X' and 'O', capture all regions
 * that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * An 'O' on the border (or connected to a border 'O') is NOT surrounded.
 *
 * Example:
 *   Input:  [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *   Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *   Explanation: The bottom 'O' is on the border, so it is not flipped.
 *   The middle 'O's are surrounded, so they are flipped to 'X'.
 *
 * Constraints:
 *   m == board.length, n == board[i].length
 *   1 <= m, n <= 200
 *   board[i][j] is 'X' or 'O'.
 *
 * ---
 * Approach: Border DFS (reverse thinking)
 *
 * Instead of finding surrounded 'O's, find UN-surrounded ones:
 * 1. DFS/BFS from every border 'O', mark connected 'O's as '*' (safe).
 * 2. Sweep the board:
 *    - Remaining 'O' -> 'X' (they are surrounded)
 *    - '*' -> 'O' (restore the safe ones)
 *
 * Time:  O(m * n)
 * Space: O(m * n) recursion stack in worst case
 */
public class SurroundedRegions {

    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        // Mark all 'O' on the border and adjacent as '*'
        for (int i = 0; i < m; i++) {
            flip(board, i, 0);
            flip(board, i, n - 1);
        }

        // Mark all 'O' on the border and adjacent as '*'
        for (int i = 0; i < n; i++) {
            flip(board, 0, i);
            flip(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }


    private static void flip(char[][] board, int row, int col) {

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;

        if (board[row][col] == 'X') return;

        if (board[row][col] == 'O') {
            board[row][col] = '*';
            flip(board, row + 1, col);
            flip(board, row - 1, col);
            flip(board, row, col + 1);
            flip(board, row, col - 1);
        }
    }

    static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

}
