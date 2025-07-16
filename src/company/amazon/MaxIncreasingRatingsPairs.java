package company.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Leetcode :  <a href="https://leetcode.com/problems/maximize-greatness-of-an-array/">maximize-greatness-of-an-array</a>
 */
public class MaxIncreasingRatingsPairs {

    public static int getMaxIncrements(int[] ratings) {
        Arrays.sort(ratings);
        int n = ratings.length;
        int count = 0;

        int left = 0;
        int right = 0;

        while (right < n) {
            if (ratings[left] < ratings[right]) {
                count++;
                left++;
            }
            right++;
        }

        return count;
    }


    public static int getMaxIncrements_2(int[] A) {
        Map<Integer, Integer> count = new HashMap<>();
        int maxFreq = 0;

        for (int a : A) {
            count.put(a, count.getOrDefault(a, 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(a));
        }

        return A.length - maxFreq; // len - total duplicate items
    }


    public static void main(String[] args) {
        int[] ratings1 = {2, 1, 1, 2}; // 2
        System.out.println(getMaxIncrements_2(ratings1));
        int[] ratings2 = {2, 3, 1, 5, 4}; // 4
        System.out.println(getMaxIncrements_2(ratings2));
        int[] ratings3 = {1, 1, 1, 10, 10, 10}; // Output: 3
        System.out.println(getMaxIncrements_2(ratings3));
    }

}
