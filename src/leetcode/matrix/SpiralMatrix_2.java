package leetcode.matrix;

import java.util.Arrays;

/*
 * LC 59 - Spiral Matrix II
 *
 * Given a positive integer n, generate an n x n matrix filled with elements
 * from 1 to n² in spiral order (clockwise starting from top-left).
 *
 * Example 1:
 *   Input:  n=3
 *   Output: [[1,2,3],[8,9,4],[7,6,5]]
 *
 * Example 2:
 *   Input:  n=1
 *   Output: [[1]]
 *
 * Constraints:
 *   1 <= n <= 20
 *
 * Same boundary-shrinking approach as LC 54, but writing values instead of reading.
 * No guards needed — square matrix ensures boundaries prevent overlap naturally.
 * Time: O(n²)   Space: O(1) excluding output
 */
public class SpiralMatrix_2 {

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int counter = 1;

        while (top <= bottom && left <= right) {
            // fill right along top row
            for (int i = left; i <= right; i++) result[top][i] = counter++;
            top++;

            // fill down along right col
            for (int i = top; i <= bottom; i++) result[i][right] = counter++;
            right--;

            // fill left along bottom row
            for (int i = right; i >= left; i--) result[bottom][i] = counter++;
            bottom--;

            // fill up along left col
            for (int i = bottom; i >= top; i--) result[i][left] = counter++;
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(Arrays.deepToString(generateMatrix(n)));
    }
}
