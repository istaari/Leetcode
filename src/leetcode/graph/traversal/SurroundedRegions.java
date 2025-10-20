package leetcode.graph.traversal;

import java.util.Arrays;

public class SurroundedRegions {

    /**
     * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
     *
     * @param board 2D board containing 'X' and 'O'
     */
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

    /**
     * Marks 0's as '*' if they are connected to the border and adjacent
     *
     * @param board 2D board containing 'X' and 'O'
     * @param row   number of cell
     * @param col   number of cell
     */
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

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

}
