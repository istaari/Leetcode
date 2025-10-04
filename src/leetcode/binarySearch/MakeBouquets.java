package leetcode.binarySearch;

import java.util.Arrays;


/**
 * LeetCode Problem 1482: Minimum Number of Days to Make m Bouquets
 *
 * Given an integer array bloomDay, an integer m and an integer k.
 *
 * We need to make m bouquets. To make a bouquet, you need to use k adjacent
 * flowers from the garden. The garden consists of n flowers, the ith flower will
 * bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 *
 * Return the minimum number of days you need to wait to be able to make m
 * bouquets from the garden. If it is impossible to make m bouquets return -1.
 *
 * This is a classic "Binary Search on the Answer" problem.
 *
 * Example 1:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 *
 * Example 2:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 */
public class MakeBouquets {

    public static int minDays(int[] bloomDay, int m, int k) {
        // If the total flowers needed is more than available, it's impossible.
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        // The search space for the answer (days) is between the earliest bloom day
        // and the latest bloom day.
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        int minDays = -1;

        // Binary search on the answer (the number of days).
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canMake(bloomDay, m, k, mid)) {
                // If we can make bouquets in `mid` days, it's a potential answer.
                // Try for an even smaller number of days.
                minDays = mid;
                right = mid - 1;
            } else {
                // If we can't, we need to wait more days.
                left = mid + 1;
            }
        }
        return minDays;
    }

    private static boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int flowers = 0;
        for (int bloom : bloomDay) {
            if (bloom <= day) {
                // This flower has bloomed.
                flowers++;
                if (flowers == k) {
                    // We have enough adjacent flowers to make a bouquet.
                    bouquets++;
                    flowers = 0; // Reset flower count for the next bouquet.
                }
            } else {
                // This flower has not bloomed, breaking the adjacent streak.
                flowers = 0;
            }
            if (bouquets >= m) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        int[] bloomDay = {1,10,3,10,2};
        int m = 3;
        int k = 1;
        System.out.println("Input: bloomDay = " + Arrays.toString(bloomDay) + ", m = " + m + ", k = " + k);
        System.out.println("Output: " + minDays(bloomDay, m, k)); // Expected: 3

        int[] bloomDay2 = {7,7,7,7,12,7,7};
        int m2 = 2;
        int k2 = 3;
        System.out.println("\nInput: bloomDay = " + Arrays.toString(bloomDay2) + ", m = " + m2 + ", k = " + k2);
        System.out.println("Output: " + minDays(bloomDay2, m2, k2)); // Expected: 12
    }
}
