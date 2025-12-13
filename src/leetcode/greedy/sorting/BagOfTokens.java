package leetcode.greedy.sorting;

import java.util.Arrays;

public class BagOfTokens {

    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0;
        int right = tokens.length - 1;
        int score = 0;

        while (left <= right) {
            // Power up
            if (power < tokens[left] && score > 0) {
                score--;
                power += tokens[right];
                right--;
            }

            // Power down
            if (power >= tokens[left]) {
                power -= tokens[left];
                score++;
            }

            left++;
        }

        return score;
    }

    public static void main(String[] args) {
        int[] tokens = { 100, 200, 300, 400 };
        int power = 200;
        System.out.println(bagOfTokensScore(tokens, power));
    }

}
