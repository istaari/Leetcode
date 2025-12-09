package leetcode.matrix;

import java.util.Arrays;

@SuppressWarnings("all")
public class MatrixReshape {

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;

        if (m * n != r * c) return mat;

        int[][] result = new int[r][c];

        int[] temp = new int[n * m];

        // First convert into 1D array, i * columnLength + j
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i * m + j] = mat[i][j];
            }
        }
        // Now convert 1D array to 2D array, i * columnLength + j, with new column length
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result[i][j] = temp[i * c + j];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3, 4}};
        int r = 2, c = 2;
        System.out.println(Arrays.deepToString(matrixReshape(mat, r, c)));
    }


}
