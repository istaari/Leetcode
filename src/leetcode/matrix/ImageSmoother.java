package leetcode.matrix;

import java.util.Arrays;

public class ImageSmoother {

    // https://leetcode.com/problems/image-smoother/description/
    // from current cell move in all 8 directions
    // Can be optimized using bitwise operations
    public static int[][] imageSmoother(int[][] img) {
        // All 8 directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

        int m = img.length;
        int n = img[0].length;

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int total = img[i][j];
                int count = 1;

                for (int[] dir : directions) {
                    int nRow = dir[0] + i;
                    int nCol = dir[1] + j;
                    // Boundary Conditions
                    if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n) {
                        total += img[nRow][nCol];
                        count++;
                    }
                }

                result[i][j] = total / count;

            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] img = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        System.out.println(Arrays.deepToString(imageSmoother(img))); // [[137,141,137],[141,138,141],[137,141,137]]
    }
}
