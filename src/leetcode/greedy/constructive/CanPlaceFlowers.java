package leetcode.greedy.constructive;

public class CanPlaceFlowers {


    /**
     * LeetCode Problem 605: Can Place Flowers
     * <p>
     * You have a long flowerbed in which some of the plots are planted, and some
     * are not. However, flowers cannot be planted in adjacent plots.
     * <p>
     * Given an integer array flowerbed containing 0s and 1s, where 0 means empty
     * and 1 means not empty, and an integer n, return if n new flowers can be
     * planted in the flowerbed without violating the no-adjacent-flowers rule.
     * <p>
     * Greedy Choice Property: iterate through the flowerbed and plant a flower in the first available spot you find.
     * <p>
     * Example 1:
     * Input: flowerbed = [1,0,0,0,1], n = 1
     * Output: true
     * <p>
     * Example 2:
     * Input: flowerbed = [1,0,0,0,1], n = 2
     * Output: false
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // We must loop through the entire flowerbed.
        for (int i = 0; i < flowerbed.length; i++) {
            // Only consider empty plots.
            if (flowerbed[i] == 0) {
                // Check if the left plot is empty (or it's the edge).
                boolean prevEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                // Check if the right plot is empty (or it's the edge).
                boolean nextEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // If both sides are clear, plant a flower.
                if (prevEmpty && nextEmpty) {
                    flowerbed[i] = 1; // Mark the spot as filled.
                    n--;              // Decrement the count of flowers to plant.
                }
            }
            // If we've planted all the flowers, we can stop early.
            if (n <= 0) {
                return true;
            }
        }

        // If we finish the loop and n is 0 or less, we succeeded.
        return n <= 0;
    }

    static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        int n = 2;

        System.out.println(canPlaceFlowers(flowerbed, n));
    }
}
