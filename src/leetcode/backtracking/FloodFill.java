package leetcode.backtracking;

import java.util.Arrays;

/*
 * LC 733 - Flood Fill
 *
 * An image is represented as an m x n integer grid where image[i][j] represents
 * the pixel value. You are given the location (sr, sc) of a starting pixel and
 * a new color. Perform a flood fill starting from that pixel.
 *
 * To flood fill: color the starting pixel with the new color, then color all
 * 4-directionally connected pixels of the same original color, and so on recursively.
 *
 * Example 1:
 *   Input:  image=[[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, color=2
 *   Output: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * Example 2:
 *   Input:  image=[[0,0,0],[0,0,0]], sr=0, sc=0, color=0
 *   Output: [[0,0,0],[0,0,0]]  (color same as original, no change)
 *
 * Constraints:
 *   m == image.length,  n == image[i].length
 *   1 <= m, n <= 50
 *   0 <= image[i][j], color <= 65535
 *   0 <= sr < m,  0 <= sc < n
 *
 * Approach: DFS — stop if out of bounds, already new color, or not original target color.
 * Time: O(m*n)   Space: O(m*n) recursion stack
 */

public class FloodFill {

    public static void helper(int[][] image, int sr, int sc, int color, int target) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length)
            return;

        if (image[sr][sc] == color)
            return;

        if (image[sr][sc] != target)
            return;

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
                { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 0, 1 }
        };

        int sr = 1; // Starting row
        int sc = 1; // Starting column
        int color = 2; // New color value

        System.out.println(Arrays.deepToString(floodFill(image, sr, sc, color)));
    }
}
