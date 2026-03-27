package leetcode.greedy.specialized;

/**
 * 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii/
 *
 * You are given a 0-indexed array of integers nums of length n. You are
 * initially positioned at nums[0]. Each element represents the max length
 * of a forward jump from that position.
 *
 * Return the minimum number of jumps to reach nums[n-1].
 * The test cases are generated such that you can always reach the last index.
 *
 * Example 1: nums = [2,3,1,1,4] -> 2 (jump 1 to index 1, then 3 to index 4)
 * Example 2: nums = [2,3,0,1,4] -> 2
 *
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   0 <= nums[i] <= 1000
 *
 * ---
 * Approach: Greedy — BFS-like level expansion
 *
 * Think of it as BFS where each "level" is one jump:
 *   - 'reached' = the farthest index reachable with current number of jumps.
 *   - 'far' = the farthest index reachable with one more jump.
 *   - When i == reached, we must take a jump: count++ and reached = far.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class CanJump_2 {

    public static int canJump(int[] nums) {
        int far = 0;
        int reached = 0;
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            far = Math.max(i + nums[i], far);

            if (i == reached) {
                count++;
                reached = far;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(canJump(nums));
    }

}
