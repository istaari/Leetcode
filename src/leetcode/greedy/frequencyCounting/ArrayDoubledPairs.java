package leetcode.greedy.frequencyCounting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 954. Array of Doubled Pairs
 * https://leetcode.com/problems/array-of-doubled-pairs/
 *
 * Given an integer array of even length arr, return true if it is possible
 * to reorder arr such that arr[2*i + 1] = 2 * arr[2*i] for every 0 <= i < len(arr) / 2.
 *
 * In other words, can we pair every element x with 2x?
 *
 * Example 1: arr = [3,1,3,6] -> false
 * Example 2: arr = [2,1,2,6] -> false
 * Example 3: arr = [4,-2,2,-4] -> true ([-2,-4],[2,4])
 * Example 4: arr = [1,2,4,16,8,4] -> false
 *
 * Constraints:
 *   2 <= arr.length <= 3 * 10^4
 *   arr.length is even.
 *   -10^5 <= arr[i] <= 10^5
 *
 * ---
 * Approach: Greedy + Sort by absolute value + Frequency map
 *
 * 1. Count frequency of each number.
 * 2. Sort by absolute value (so we process smaller magnitudes first).
 * 3. For each x, try to pair it with 2x. If freq[2x] < freq[x], impossible.
 *
 * Why sort by |x|? For negatives: -4 pairs with -2 (half), not -8 (double).
 * Sorting by |x| ensures we always process x before 2x.
 *
 * Time:  O(n log n)
 * Space: O(n)
 */
public class ArrayDoubledPairs {

    public static boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        // Step 1: Count frequency of each number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //Step 2: Sort by absolute value using boxed stream
        Integer[] s = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .toArray(Integer[]::new);

        // Step 3: Try to pair each number with its double
        for (int x : s) {
            if (map.get(x) == 0)
                continue;

            int doubleX = 2 * x;

            if (map.getOrDefault(doubleX, 0) < map.get(x)) {
                return false;
            }
            map.put(doubleX, map.get(doubleX) - map.get(x));
            map.put(x, 0);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 0, 0, 8, 1 };
        // arr = new int[] {1, 2, 4, 16, 8, 4};
        System.out.println(canReorderDoubled(arr));
    }

}
