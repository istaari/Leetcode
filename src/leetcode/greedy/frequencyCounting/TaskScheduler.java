package leetcode.greedy.frequencyCounting;

/**
 * 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 *
 * You are given an array of CPU tasks, each represented by a letter (A to Z),
 * and a cooling interval n. Each cycle or interval allows completion of one task.
 * Tasks can be completed in any order, but there's a constraint: identical tasks
 * must be separated by at least n intervals.
 *
 * Return the minimum number of intervals the CPU will take to finish all tasks.
 *
 * Example 1:
 *   Input: tasks = ["A","A","A","B","B","B"], n = 2
 *   Output: 8
 *   Explanation: A -> B -> idle -> A -> B -> idle -> A -> B
 *
 * Example 2:
 *   Input: tasks = ["A","C","A","B","D","B"], n = 1
 *   Output: 6
 *   Explanation: A -> B -> A -> C -> B -> D (no idle needed)
 *
 * Example 3:
 *   Input: tasks = ["A","A","A","B","B","B"], n = 3
 *   Output: 10
 *   Explanation: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B
 *
 * Constraints:
 *   1 <= tasks.length <= 10^4
 *   tasks[i] is an uppercase English letter.
 *   0 <= n <= 100
 *
 * ---
 * Approach: Greedy — math formula based on max frequency
 *
 * Let maxFreq = frequency of the most common task.
 * Let maxCount = number of tasks that have this max frequency.
 *
 * Visualization for tasks=[A,A,A,B,B,B], n=2:
 *   A _ _ | A _ _ | A
 *   Slots between A's: (maxFreq - 1) groups of size (n + 1)
 *   Plus the last group: just the tasks with maxFreq (maxCount)
 *
 * Formula: result = (maxFreq - 1) * (n + 1) + maxCount
 *
 * But if there are many different tasks, we might not need any idle time.
 * In that case, answer = tasks.length.
 *
 * Final answer: max(tasks.length, (maxFreq - 1) * (n + 1) + maxCount)
 *
 * Time:  O(n) — one pass to count frequencies
 * Space: O(1) — at most 26 letters
 */
public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        // Count frequency of each task
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Find the maximum frequency
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        // Count how many tasks share the maximum frequency
        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq) maxCount++;
        }

        // Formula: (maxFreq - 1) chunks of size (n+1) + maxCount
        // Example: A A A B B B, n=2
        //   maxFreq=3, maxCount=2
        //   (3-1) * (2+1) + 2 = 6 + 2 = 8
        //   Layout: [A B _] [A B _] [A B] -> 8 slots
        int formulaResult = (maxFreq - 1) * (n + 1) + maxCount;

        // If tasks fill all slots naturally (no idle needed), answer = tasks.length
        return Math.max(tasks.length, formulaResult);
    }

    public static void main(String[] args) {
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println("Example 1: " + leastInterval(tasks1, 2)); // 8

        char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
        System.out.println("Example 2: " + leastInterval(tasks2, 1)); // 6

        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println("Example 3: " + leastInterval(tasks3, 3)); // 10
    }
}
