package leetcode.matrix;

import java.util.Arrays;

/*
 * LC 832 - Flipping an Image
 *
 * Given an n x n binary matrix image, flip the image horizontally, then invert it,
 * and return the resulting image.
 *
 * To flip an image horizontally means each row is reversed.
 *   e.g. [1,1,0] → [0,1,1]
 * To invert an image means each 0 is replaced by 1 and each 1 by 0.
 *   e.g. [0,1,1] → [1,0,0]
 *
 * Example 1:
 *   Input:  [[1,1,0],[1,0,1],[0,0,0]]
 *   Output: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * Example 2:
 *   Input:  [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 *   Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * Constraints:
 *   n == image.length == image[i].length
 *   1 <= n <= 20
 *   image[i][j] is either 0 or 1
 *
 * Time: O(n²)   Space: O(1) in-place
 */
public class FlippingImage {

    public static int[][] flipAndInvertImage(int[][] image) {
        int rLen = image.length;
        int cLen = image[0].length;

        // flip each row: swap from both ends toward center
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen / 2; j++) {
                int temp = image[i][j];
                image[i][j] = image[i][cLen - 1 - j];
                image[i][cLen - 1 - j] = temp;
            }
        }

        // invert: XOR each cell with 1 flips 0↔1
        for (int i = 0; i < rLen; i++)
            for (int j = 0; j < cLen; j++)
                image[i][j] ^= 1;

        return image;
    }

    public static void main(String[] args) {
        int[][] image = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
        System.out.println(Arrays.deepToString(flipAndInvertImage(image)));
    }
}
