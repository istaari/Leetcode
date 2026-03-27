package leetcode.greedy.specialized;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 406. Queue Reconstruction by Height
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 *
 * You are given an array of people, people, which are the attributes of some
 * people in a queue (not necessarily in order). Each people[i] = [hi, ki]
 * represents the i-th person of height hi with exactly ki other people in front
 * who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue.
 *
 * Example:
 *   Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 *   Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 *
 * Constraints:
 *   1 <= people.length <= 2000
 *   0 <= hi <= 10^6
 *   0 <= ki < people.length
 *
 * ---
 * Approach: Sort descending by height, then insert by k-value
 *
 * 1. Sort by height descending (tallest first). Break ties by k ascending.
 * 2. Insert each person at index k in the result list.
 *
 * Why this works: When we insert a shorter person, all previously-inserted
 * people are taller or equal, so inserting at index k guarantees exactly k
 * taller people before them.
 *
 * Time:  O(n^2) due to list insertions
 * Space: O(n)
 */
public class QueueReconstruction {

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0]; // Sort by height descending
            return a[1] - b[1]; // Sort by k ascending
        });

        List<int[]> list = new LinkedList<>();
        for (int[] current : people) {
            list.add(current[1], new int[]{current[0], current[1]});
        }

        return list.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

}
