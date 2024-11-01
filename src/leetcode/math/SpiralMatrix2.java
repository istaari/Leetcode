package leetcode.math;

import java.util.Arrays;

public class SpiralMatrix2 {

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int counter = 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++) {
                result[top][i] = counter++;
            }
            top++;


            for (int i = top; i <= bottom; i++) {
                result[i][right] = counter++;
            }
            right--;


            for (int i = right; i >= left; i--) {
                result[bottom][i] = counter++;
            }

            bottom--;

            for (int i = bottom; i >= top; i--) {
                result[i][left] = counter++;
            }
            left++;

        }

        return result;
    }


    public static void main(String[] args) {
        int n = 3;
        System.out.println(Arrays.deepToString(generateMatrix(n)));
    }
}
