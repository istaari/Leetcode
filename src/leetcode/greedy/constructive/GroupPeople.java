package leetcode.greedy.constructive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1282. Group the People Given the Group Size They Belong To
 * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 *
 * There are n people that are split into some unknown number of groups.
 * Each person is labeled with a unique ID from 0 to n-1.
 *
 * You are given an integer array groupSizes, where groupSizes[i] is the size
 * of the group that person i is in.
 *
 * Return a list of groups such that each person i is in a group of size groupSizes[i].
 * Each person should appear in exactly one group, and every person must be in a group.
 * If there are multiple answers, return any of them.
 *
 * Example:
 *   Input: groupSizes = [3,3,3,3,3,1,3]
 *   Output: [[5],[0,1,2],[3,4,6]]
 *
 * Constraints:
 *   groupSizes.length == n
 *   1 <= n <= 500
 *   1 <= groupSizes[i] <= n
 *
 * ---
 * Approach: Greedy grouping by size
 *
 * Use a map: groupSize -> list of person IDs.
 * When a list reaches its target size, flush it to the result and start a new list.
 *
 * Greedy choice: assign each person to the first available group of their size.
 *
 * Time:  O(n)
 * Space: O(n)
 */
public class GroupPeople {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> tempList = map.computeIfAbsent(groupSizes[i], (key) -> new ArrayList<>());
            tempList.add(i);

            if (tempList.size() == groupSizes[i]) {
                result.add(map.get(groupSizes[i]));
                map.put(groupSizes[i], new ArrayList<>());
            }
        }

        return result;
    }


}
