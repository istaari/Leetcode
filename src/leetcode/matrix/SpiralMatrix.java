package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/*
 * LC 54 - Spiral Matrix
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order
 * (clockwise starting from top-left).
 *
 * Example 1:
 *   Input:  [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 *   Input:  [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *   Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 *   m == matrix.length,  n == matrix[i].length
 *   1 <= m, n <= 10
 *   -100 <= matrix[i][j] <= 100
 *
 * Approach: shrink boundaries (top, bottom, left, right) after each direction.
 *   Right → Down → Left → Up, repeat until boundaries cross.
 * Guards before Left/Up prevent duplicating rows/cols in non-square matrices.
 * Time: O(m*n)   Space: O(1) excluding output
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;

        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;

        while (top <= bottom && left <= right) {
            // traverse right along top row, then shrink top
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // traverse down along right col, then shrink right
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // guard: avoid re-traversing a row already covered by top traversal
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // guard: avoid re-traversing a col already covered by right traversal
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(spiralOrder(matrix));
    }
}
