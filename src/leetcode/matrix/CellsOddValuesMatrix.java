package leetcode.matrix;

public class CellsOddValuesMatrix {

    public static int oddCells(int m, int n, int[][] indices) {
        int[][] result = new int[m][n];

        for (int[] index : indices) {
            int row = index[0];
            int col = index[1];

            for (int i = 0; i < n; i++) {
                result[row][i]++;
            }

            for (int i = 0; i < m; i++) {
                result[i][col]++;
            }
        }

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] % 2 != 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int[][] indices = { { 0, 1 }, { 1, 1 } };
        System.out.println(oddCells(m, n, indices));
    }
}
