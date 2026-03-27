package leetcode.greedy.specialized;

/**
 * 135. Candy
 * https://leetcode.com/problems/candy/
 *
 * There are n children standing in a line. Each child is assigned a rating value
 * given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *   1. Each child must have at least one candy.
 *   2. Children with a higher rating than their neighbors get more candies.
 *
 * Return the minimum number of candies you need to have to distribute.
 *
 * Example 1: ratings = [1,0,2] -> 5 (candies = [2,1,2])
 * Example 2: ratings = [1,2,2] -> 4 (candies = [1,2,1])
 *
 * Constraints:
 *   n == ratings.length
 *   1 <= n <= 2 * 10^4
 *   0 <= ratings[i] <= 2 * 10^4
 *
 * ---
 * Approach: Two-pass greedy
 *
 * Pass 1 (left to right): If ratings[i] > ratings[i-1], give one more candy than left neighbor.
 * Pass 2 (right to left): If ratings[i] > ratings[i+1], ensure at least one more than right neighbor.
 *
 * Each pass satisfies one direction of the constraint. Together they satisfy both.
 *
 * Time:  O(n)
 * Space: O(n)
 */
public class Candy {

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        int totalCandy = 0;

        candies[0] = 1;

        // Traverse from left to right, check if left neighbour is greater than current element, If yes then add prev candies plus 1
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // Traverse from right to left, check if right neighbour is greater than current element, If yes then add max of current candy or next candies plus 1
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }


        for (int candy : candies) {
            totalCandy += candy;
        }

        return totalCandy;
    }


    public static void main(String[] args) {
        int[] ratings = {1, 3, 4, 5, 2};
        System.out.println(candy(ratings));
    }

}
