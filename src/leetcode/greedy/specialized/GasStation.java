package leetcode.greedy.specialized;

/**
 * 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 *
 * There are n gas stations along a circular route, where the amount of gas at
 * the i-th station is gas[i]. You have a car with an unlimited gas tank and it
 * costs cost[i] of gas to travel from the i-th station to (i+1)-th station.
 *
 * You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index
 * if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If a solution exists, it is guaranteed to be unique.
 *
 * Example 1:
 *   Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *   Output: 3
 *   Explanation: Start at station 3 (gas=4).
 *     Station 3: tank = 0+4-1 = 3
 *     Station 4: tank = 3+5-2 = 6
 *     Station 0: tank = 6+1-3 = 4
 *     Station 1: tank = 4+2-4 = 2
 *     Station 2: tank = 2+3-5 = 0 (made it!)
 *
 * Example 2:
 *   Input: gas = [2,3,4], cost = [3,4,3]
 *   Output: -1 (total gas < total cost)
 *
 * Constraints:
 *   n == gas.length == cost.length
 *   1 <= n <= 10^5
 *   0 <= gas[i], cost[i] <= 10^4
 *
 * ---
 * Approach: Greedy — single pass
 *
 * Key observations:
 * 1. If total gas < total cost, no solution exists.
 * 2. If we start at station s and can't reach station t (tank goes negative),
 *    then NO station between s and t can be the answer either.
 *    -> Jump start to t+1.
 *
 * Algorithm:
 *   - Track totalSurplus (gas - cost for all stations) to check feasibility.
 *   - Track currentTank. If it goes negative, reset start to next station.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;  // Check if solution is possible at all
        int currentTank = 0;   // Track tank for current candidate start
        int start = 0;         // Candidate starting station

        for (int i = 0; i < gas.length; i++) {
            int surplus = gas[i] - cost[i];
            totalSurplus += surplus;
            currentTank += surplus;

            if (currentTank < 0) {
                // Can't reach station i+1 from current start.
                // No station between start and i works either.
                // Try starting from i+1.
                start = i + 1;
                currentTank = 0;
            }
        }

        // If total gas >= total cost, the answer is guaranteed to be 'start'
        return totalSurplus >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Example 1: " + canCompleteCircuit(gas1, cost1)); // 3

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Example 2: " + canCompleteCircuit(gas2, cost2)); // -1
    }
}
