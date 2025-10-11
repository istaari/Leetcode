package leetcode.string;

import java.util.*;

/*
 * LeetCode Problem: 821. Shortest Distance to a Character
 *
 * Question:
 * Given a string s and a character c that occurs in s, return an array of integers
 * answer where answer.length == s.length and answer[i] is the shortest distance
 * from s[i] to the character c in s.
 *
 * The distance between two indices i and j is abs(i - j).
 *
 * Example 1:
 * Input: s = "loveleetcode", c = 'e'
 * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
 * The distance from index 0 to 'e' is min(abs(0-3), abs(0-5), abs(0-6), abs(0-11)) = 3.
 * The distance from index 1 to 'e' is min(abs(1-3), abs(1-5), abs(1-6), abs(1-11)) = 2.
 * For index 4, min(abs(4-3), abs(4-5), abs(4-6), abs(4-11)) = 1.
 * For index 8, min(abs(8-3), abs(8-5), abs(8-6), abs(8-11)) = 3.
 *
 */

public class ShortestDistanceCharacter {


    // Uses treeSet to store indices of character c
    @SuppressWarnings("all")
    public static int[] shortestToChar(String s, char c) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        // put all indices of character c in the TreeSet
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                treeSet.add(i);
            }
        }

        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int min;

            int ceilMin = Integer.MAX_VALUE;
            int floorMin = Integer.MAX_VALUE;

            if (treeSet.ceiling(i) != null) {
                ceilMin = treeSet.ceiling(i) - i;
            }

            if (treeSet.floor(i) != null) {
                floorMin = i - treeSet.floor(i);
            }

            min = Math.min(ceilMin, floorMin);
            result[i] = min;
        }

        return result;
    }

    /**
     * Solves the same problem using a pre-computed list of character indices and binary search.
     * This approach follows the user's suggestion.
     *
     * @param s The input string.
     * @param c The target character.
     * @return An array of integers representing the shortest distances.
     */
    public static int[] shortestToCharBinarySearch(String s, char c) {
        // Step 1: Find all indices of the character 'c' and store them in a sorted list.
        List<Integer> cIndices = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                cIndices.add(i);
            }
        }

        int n = s.length();
        int[] result = new int[n];

        // Step 2: For each character in the string, find the shortest distance to 'c'.
        for (int i = 0; i < n; i++) {
            // Use binary search to find the insertion point of the current index 'i'
            // in our list of 'c' indices. This helps us quickly find the nearest neighbors.
            int insertionPoint = Collections.binarySearch(cIndices, i);

            // Case 1: 'i' is an index where 'c' is present. The distance is 0.
            if (insertionPoint >= 0) {
                result[i] = 0;
            } else {
                // Case 2: 'i' is not an index of 'c'.
                // The binarySearch method returns (-(insertion point) - 1).
                // We convert it back to the actual insertion point.
                insertionPoint = -(insertionPoint + 1);

                // Calculate distance to the 'c' on the left.
                int distToLeft = Integer.MAX_VALUE;
                // The neighbor to the left is at `insertionPoint - 1`.
                if (insertionPoint > 0) {
                    distToLeft = i - cIndices.get(insertionPoint - 1);
                }

                // Calculate distance to the 'c' on the right.
                int distToRight = Integer.MAX_VALUE;
                // The neighbor to the right is at the `insertionPoint`.
                if (insertionPoint < cIndices.size()) {
                    distToRight = cIndices.get(insertionPoint) - i;
                }

                // The shortest distance is the minimum of the two.
                result[i] = Math.min(distToLeft, distToRight);
            }
        }

        return result;
    }

    /**
     * Solves the problem using a two-pass (left-to-right and right-to-left) approach.
     * This corresponds to the "peaks and valleys" visualization.
     *
     * @param s The input string.
     * @param c The target character.
     * @return An array of integers representing the shortest distances.
     */
    public static int[] shortestToCharTwoPass(String s, char c) {
        int n = s.length();
        int[] result = new int[n];
        int pos = -n; // Random number should be less than -n

        // Pass 1: Left to Right
        // For each index, we find the distance to the nearest 'c' on its left.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                pos = i; // Update the position of the last seen 'c'.
            }
            result[i] = i - pos;
        }

        // Pass 2: Right to Left
        // For each index, we find the distance to the nearest 'c' on its right.
        // Then we take the minimum of the left-distance and the right-distance.
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                pos = i; // Update the position of the last seen 'c'.
            }
            // result[i] currently holds the distance to the left 'c'.
            // pos - i is the distance to the right 'c'. We need the minimum.
            result[i] = Math.min(result[i], Math.abs(i - pos));
        }

        return result;
    }




    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        System.out.println("Input String: " + s);
        System.out.println("Target Character: " + c);
        System.out.println("Result: " + Arrays.toString(shortestToChar(s, c)));
    }
}
