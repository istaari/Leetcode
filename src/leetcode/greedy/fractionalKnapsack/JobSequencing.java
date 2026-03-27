package leetcode.greedy.fractionalKnapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Job Sequencing Problem (Classic Greedy)
 *
 * Given a set of n jobs where each job has a deadline and a profit.
 * Each job takes 1 unit of time. Only one job can be scheduled at a time.
 * A job earns profit only if it is completed by its deadline.
 *
 * Maximize total profit.
 *
 * Example:
 *   Jobs: [(1, 4, 20), (2, 1, 10), (3, 1, 40), (4, 1, 30)]
 *   (id, deadline, profit)
 *   Output: 2 jobs scheduled, profit = 60
 *   Explanation: Schedule Job 3 (profit 40) at time 1, Job 1 (profit 20) at time 4.
 *   Jobs 2 and 4 miss their deadline.
 *
 * ---
 * Approach: Greedy — sort by profit descending, assign latest available slot
 *
 * 1. Sort jobs by profit in descending order.
 * 2. For each job, try to schedule it in the latest available time slot
 *    before its deadline.
 * 3. Use a boolean array to track which time slots are taken.
 *
 * Why latest slot? Scheduling in the latest possible slot keeps earlier
 * slots free for jobs with tighter deadlines.
 *
 * Time:  O(n^2) in worst case (n jobs * searching for slot)
 *        Can be optimized to O(n log n) with Union-Find.
 * Space: O(maxDeadline)
 */
public class JobSequencing {

    public static int[] jobSequencing(int[][] jobs) {
        // Sort by profit descending
        Arrays.sort(jobs, (a, b) -> b[2] - a[2]);

        // Find the maximum deadline to determine the time slot range
        int maxDeadline = 0;
        for (int[] job : jobs) {
            maxDeadline = Math.max(maxDeadline, job[1]);
        }

        // Track which time slots are filled (1-indexed)
        boolean[] slot = new boolean[maxDeadline + 1];
        int totalProfit = 0;
        int jobCount = 0;
        List<Integer> scheduled = new ArrayList<>();

        for (int[] job : jobs) {
            int deadline = job[1];

            // Try to place this job in the latest available slot before its deadline
            for (int t = deadline; t >= 1; t--) {
                if (!slot[t]) {
                    slot[t] = true;
                    totalProfit += job[2];
                    jobCount++;
                    scheduled.add(job[0]);
                    break;
                }
            }
        }

        System.out.println("Scheduled job IDs: " + scheduled);
        return new int[]{jobCount, totalProfit};
    }

    public static void main(String[] args) {
        // {id, deadline, profit}
        int[][] jobs = {
            {1, 4, 20},
            {2, 1, 10},
            {3, 1, 40},
            {4, 1, 30}
        };
        int[] result = jobSequencing(jobs);
        System.out.println("Jobs: " + result[0] + ", Profit: " + result[1]); // 2, 60

        int[][] jobs2 = {
            {1, 2, 100},
            {2, 1, 19},
            {3, 2, 27},
            {4, 1, 25},
            {5, 1, 15}
        };
        int[] result2 = jobSequencing(jobs2);
        System.out.println("Jobs: " + result2[0] + ", Profit: " + result2[1]); // 2, 127
    }
}
