package leetcode.backtracking;

import java.util.Arrays;

public class FloodFill {

    public static void helper(int[][] image, int sr, int sc, int color, int target) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) return;

        if (image[sr][sc] == color) return;

        if (image[sr][sc] != target) return;

        image[sr][sc] = color;

        helper(image, sr + 1, sc, color, target);
        helper(image, sr - 1, sc, color, target);
        helper(image, sr, sc + 1, color, target);
        helper(image, sr, sc - 1, color, target);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int target = image[sr][sc];
        helper(image, sr, sc, color, target);
        return image;
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1;    // Starting row
        int sc = 1;    // Starting column
        int color = 2; // New color value

        System.out.println(Arrays.deepToString(floodFill(image, sr, sc, color)));
    }
}
