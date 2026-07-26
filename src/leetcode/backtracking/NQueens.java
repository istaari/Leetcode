package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * LC 51 - N-Queens
 *
 * Place n queens on an n x n chessboard such that no two queens attack each other
 * (no two queens share the same row, column, or diagonal).
 * Return all distinct solutions. Each solution is a board configuration where
 * 'Q' = queen and '.' = empty cell.
 *
 * Example:
 *   Input:  n=4
 *   Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *
 * Constraints:
 *   1 <= n <= 9
 *
 * Approach: place one queen per row via backtracking.
 *   Track attacked columns (columns[]), left diagonals (d1[row-col+n-1]),
 *   and right diagonals (d2[row+col]) with boolean arrays for O(1) conflict checks.
 *   Backtrack by unmarking when removing a queen.
 * Time: O(n!)   Space: O(n) for tracking arrays + O(n²) per solution
 */
public class NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> board = new ArrayList<>();

        boolean[] columns = new boolean[n];
        boolean[] d1 = new boolean[2 * n - 1];
        boolean[] d2 = new boolean[2 * n - 1];

        helper(n, 0, columns, d1, d2, board, result);

        return result;
    }


    public static void helper(int n, int row, boolean[] columns, boolean[] d1, boolean[] d2, List<String> board, List<List<String>> result) {

        if (n == row) {
            result.add(new ArrayList<>(board));
        }

        for (int col = 0; col < n; col++) {

            if (columns[col] || d1[row - col + n - 1] || d2[row + col]) continue;

            // Mark the column and diagonal visited
            columns[col] = true;
            d1[row - col + n - 1] = true;
            d2[row + col] = true;

            StringBuilder rowConfig = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == col) {
                    rowConfig.append("Q");
                } else {
                    rowConfig.append(".");
                }
            }

            board.add(rowConfig.toString());

            helper(n, row + 1, columns, d1, d2, board, result);

            // Mark the column and diagonal visited
            columns[col] = false;
            d1[row - col + n - 1] = false;
            d2[row + col] = false;

            board.remove(board.size()-1);
        }
    }


    public static void main(String[] args) {
        int n = 4;
        System.out.println(solveNQueens(n));
    }
}
