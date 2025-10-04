package leetcode.binarySearch;

import java.util.Arrays;


/**
 * LeetCode Problem 875: Koko Eating Bananas
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has
 * piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she
 * chooses some pile of bananas and eats k bananas from that pile. If the pile
 * has less than k bananas, she eats all of them instead and will not eat any
 * more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas
 * before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * This is a classic "Binary Search on the Answer" problem.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 */
public class KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {
        // The search space for the answer (speed k) is between 1 and the largest pile.
        // The minimum possible speed is 1.
        int left = 1;
        // The maximum possible speed is the size of the largest pile, as any higher
        // speed
        // offers no benefit (you can only clear one pile per hour).
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // We use the "Leftmost Boundary" template to find the first speed k
        // for which canEatAll returns true.
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEatAll(piles, mid, h)) {
                // This speed works. It might be the answer, or an even smaller
                // speed might also work. Search in the left half.
                right = mid;
            } else {
                // This speed is too slow. We must increase the speed.
                left = mid + 1;
            }
        }

        // When the loop terminates, left == right, which is the minimum possible speed.
        return left;
    }

    public static boolean canEatAll(int[] piles, int speed, int h) {
        long hours = 0;
        for (int pile : piles) {
            // To calculate the hours for a pile, we need to round up.
            // (pile / speed) gives the integer division.
            // If there's a remainder, we need one more hour.
            hours += pile / speed;
            if (pile % speed != 0) {
                hours++;
            }
            // A more concise way to do this is using Math.ceil:
            // hours += (long) Math.ceil((double) pile / speed);
        }
        return hours <= h;
    }

    public static void main(String[] args) {
        int[] piles1 = { 3, 6, 7, 11 };
        int h1 = 8;
        System.out.println("Input: piles = " + Arrays.toString(piles1) + ", h = " + h1);
        System.out.println("Output: " + minEatingSpeed(piles1, h1)); // Expected: 4

        int[] piles2 = { 30, 11, 23, 4, 20 };
        int h2 = 5;
        System.out.println("\nInput: piles = " + Arrays.toString(piles2) + ", h = " + h2);
        System.out.println("Output: " + minEatingSpeed(piles2, h2)); // Expected: 30

        int[] piles3 = { 30, 11, 23, 4, 20 };
        int h3 = 6;
        System.out.println("\nInput: piles = " + Arrays.toString(piles3) + ", h = " + h3);
        System.out.println("Output: " + minEatingSpeed(piles3, h3)); // Expected: 23
    }
}
