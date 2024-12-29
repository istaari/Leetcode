package leetcode.slidingWindow;

public class MaximumPointsFromCards {

    public static int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        for (int point : cardPoints) {
            totalSum += point;
        }

        int windowSize = cardPoints.length - k;
        int windowSum = 0;

        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }

        int result = Math.max(0, totalSum - windowSum);
        for (int i = windowSize; i < cardPoints.length; i++) {

            windowSum += cardPoints[i] - (cardPoints[i - windowSize]);
            result = Math.max(result, totalSum - windowSum);

        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {96, 90, 41, 82, 39, 74, 64, 50, 30};
        int K = 8;

        System.out.println(maxScore(nums, K));
    }


}
