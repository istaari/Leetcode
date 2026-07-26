package leetcode.matrix;

import java.util.Arrays;

/*
 * LC 566 - Reshape the Matrix
 *
 * In MATLAB, there is a reshape function that can reshape an m x n matrix into a
 * new one with a different size r x c, keeping the same data in row-traversal order.
 *
 * Given an m x n matrix mat and two integers r and c representing the number of rows
 * and columns of the wanted reshaped matrix:
 *   - If the reshape is not possible (m*n != r*c), return the original matrix.
 *   - Otherwise return the reshaped r x c matrix.
 *
 * Example 1:
 *   Input:  mat=[[1,2],[3,4]], r=1, c=4
 *   Output: [[1,2,3,4]]
 *
 * Example 2:
 *   Input:  mat=[[1,2],[3,4]], r=2, c=4
 *   Output: [[1,2],[3,4]]  (reshape not possible, return original)
 *
 * Constraints:
 *   m == mat.length,  n == mat[i].length
 *   1 <= m, n <= 100
 *   1 <= r, c <= 300
 *
 * Trick: flatten to 1D using index = i*cols+j, then map back with new col count.
 * Time: O(m*n)   Space: O(r*c)
 */
@SuppressWarnings("all")
public class MatrixReshape {

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;

        // reshape only valid if total elements match
        if (m * n != r * c) return mat;

        int[][] result = new int[r][c];
        int[] temp = new int[n * m];

        // flatten to 1D: index = row * numCols + col
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                temp[i * m + j] = mat[i][j];

        // fill result using new column count c
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                result[i][j] = temp[i * c + j];

        return result;
    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3, 4 } };
        int r = 2, c = 2;
        System.out.println(Arrays.deepToString(matrixReshape(mat, r, c)));
    }
}
