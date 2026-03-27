package leetcode.greedy.specialized;

/**
 * 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 *
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element represents your maximum jump length
 * at that position. Return true if you can reach the last index.
 *
 * Example 1: nums = [2,3,1,1,4] -> true (jump 1 to index 1, then 3 to last)
 * Example 2: nums = [3,2,1,0,4] -> false (stuck at index 3)
 *
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   0 <= nums[i] <= 10^5
 *
 * ---
 * Approach: Greedy — track farthest reachable index
 *
 * Maintain 'far' = the farthest index reachable so far.
 * At each position i:
 *   - If i > far, we can't reach this position -> return false.
 *   - Update far = max(far, i + nums[i]).
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class CanJump {

    public static boolean canJump(int[] nums) {
        int far = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > far)
                return false;
            far = Math.max(i + nums[i], far);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 1, 4 };
        System.out.println(canJump(nums));
    }

}
