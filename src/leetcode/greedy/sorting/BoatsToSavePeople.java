package leetcode.greedy.sorting;

import java.util.Arrays;

/**
 * 881. Boats to Save People
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * You are given an array people where people[i] is the weight of the i-th person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 * Each boat carries at most two people at the same time, provided the sum of their
 * weights is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 * Example 1: people = [1,2], limit = 3 -> 1 (1 boat: [1,2])
 * Example 2: people = [3,2,2,1], limit = 3 -> 3 ([1,2], [2], [3])
 * Example 3: people = [3,5,3,4], limit = 5 -> 4
 *
 * Constraints:
 *   1 <= people.length <= 5 * 10^4
 *   1 <= people[i] <= limit <= 3 * 10^4
 *
 * ---
 * Approach: Sort + Two pointers (greedy pairing)
 *
 * 1. Sort people by weight.
 * 2. Use two pointers: lightest (left) and heaviest (right).
 * 3. If lightest + heaviest <= limit, pair them (move both pointers).
 *    Otherwise, the heaviest rides alone (move right pointer only).
 * 4. Each iteration uses one boat.
 *
 * Greedy choice: always try to pair the heaviest remaining person with the lightest.
 *
 * Time:  O(n log n)
 * Space: O(1)
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int n = people.length;
        int left = 0;
        int right = n - 1;
        int boat = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boat++;
        }

        return boat;
    }

}
