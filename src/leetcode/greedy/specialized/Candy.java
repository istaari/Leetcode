package leetcode.greedy.specialized;

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
