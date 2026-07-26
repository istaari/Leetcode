package leetcode.matrix;

import java.util.Arrays;

/*
 * LC 661 - Image Smoother
 *
 * An image smoother is a filter that smooths the grayscale of each pixel by
 * averaging pixels in its 3x3 neighborhood (itself + up to 8 neighbors).
 * For pixels on the border, only existing (in-bounds) neighbors are used.
 * Use floor division for the average.
 *
 * Given an m x n integer matrix img representing the grayscale values of an image,
 * return the image after applying the smoother.
 *
 * Example:
 *   Input:  [[100,200,100],[200,50,200],[100,200,100]]
 *   Output: [[137,141,137],[141,138,141],[137,141,137]]
 *   Corner cell (0,0): avg(100,200,200,50) = floor(550/4) = 137
 *   Center cell (1,1): avg of all 9 cells  = floor(1250/9) = 138
 *
 * Constraints:
 *   m == img.length,  n == img[i].length
 *   1 <= m, n <= 200
 *   0 <= img[i][j] <= 255
 *
 * Time: O(m*n)   Space: O(m*n) for result matrix
 */
public class ImageSmoother {

    public static int[][] imageSmoother(int[][] img) {
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 } };

        int m = img.length;
        int n = img[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int total = img[i][j];
                int count = 1; // include the cell itself

                for (int[] dir : directions) {
                    int nRow = dir[0] + i;
                    int nCol = dir[1] + j;
                    // skip out-of-bounds neighbors
                    if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n) {
                        total += img[nRow][nCol];
                        count++;
                    }
                }

                result[i][j] = total / count; // floor division
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] img = { { 100, 200, 100 }, { 200, 50, 200 }, { 100, 200, 100 } };
        System.out.println(Arrays.deepToString(imageSmoother(img)));
    }
}
