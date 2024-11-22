package leetcode.matrix;

public class GameOfLife {


    public static void gameOfLife(int[][] board) {
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                int countOne = 0;

                for (int[] dir : direction) {
                    int row = dir[0] + i;
                    int col = dir[1] + j;

                    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) continue;

                    if (board[row][col] == 1 || board[row][col] == 2) countOne++;
                }

                // make dead to live
                if (board[i][j] == 0 && countOne == 3) {
                    board[i][j] = 3;
                }

                if (board[i][j] == 1) {

                    // make live cell to dead
                    if (countOne < 2 || countOne > 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }

                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }


    }


    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};

        gameOfLife(board);
    }

}
