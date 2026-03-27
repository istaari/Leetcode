package leetcode.greedy.frequencyCounting;

import java.util.*;

/**
 * 1338. Reduce Array Size to The Half
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 *
 * You are given an integer array arr. You can choose a set of integers and
 * remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers
 * of the array are removed.
 *
 * Example 1: arr = [3,3,3,3,5,5,5,2,2,7] -> 2 (remove {3,7} -> 6 removed >= 5)
 * Example 2: arr = [7,7,7,7,7,7] -> 1
 *
 * Constraints:
 *   2 <= arr.length <= 10^5
 *   arr.length is even.
 *   1 <= arr[i] <= 10^5
 *
 * ---
 * Approach: Greedy — remove most frequent first
 *
 * 1. Count frequency of each number.
 * 2. Sort frequencies in descending order.
 * 3. Greedily remove the most frequent numbers until we've removed >= half.
 *
 * Time:  O(n log n)
 * Space: O(n)
 */
public class ReduceArraySizeHalf {

    // The goal is to minimize the number of unique numbers removed.
    public  int minSetSize(int[] arr) {
        // Step 1: Count frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Store frequencies in a list and sort in descending order
        List<Integer> freqList = new ArrayList<>(map.values());
        freqList.sort(Collections.reverseOrder());

        // Step 3: Remove elements greedily from the highest frequency
        int halfSize = arr.length / 2;
        int removed = 0, setSize = 0;

        for (int freq : freqList) {
            removed += freq;
            setSize++;
            if (removed >= halfSize) {
                return setSize;
            }
        }

        return setSize;
    }

    public static void main(String[] args) {
        ReduceArraySizeHalf sol = new ReduceArraySizeHalf();

        int[] arr1 = {3,3,3,3,5,5,5,2,2,7};
        System.out.println(sol.minSetSize(arr1)); // Output: 2

        int[] arr2 = {7,7,7,7,7,7};
        System.out.println(sol.minSetSize(arr2)); // Output: 1

        int[] arr3 = {1,9,9,2,2,2,3,3,3,3,4,4,4,4,4};
        System.out.println(sol.minSetSize(arr3)); // Output: 2
    }


}
