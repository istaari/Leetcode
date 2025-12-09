package leetcode.matrix;

import java.util.Arrays;

public class FlippingImage {

    public static int[][] flipAndInvertImage(int[][] image) {

        int rLen = image.length;
        int cLen = image[0].length;

        // Flip horizontally
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen / 2; j++) {
                int temp = image[i][j];
                image[i][j] = image[i][cLen - 1 - j];
                image[i][cLen - 1 - j] = temp;
            }
        }

        // Invert
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                image[i][j] = image[i][j] ^ 1;
            }
        }

        return image;
    }

    public static void main(String[] args) {
        // Initialize the 2D array with given values
        int[][] image = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
        System.out.println(Arrays.deepToString(flipAndInvertImage(image)));
    }

}
