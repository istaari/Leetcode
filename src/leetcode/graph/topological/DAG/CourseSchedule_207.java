package leetcode.graph.topological.DAG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where
 * prerequisites[i] = [ai, bi] indicates that you must take course bi first
 * if you want to take course ai.
 *
 * Return true if you can finish all courses, i.e., there is no cyclic dependency.
 *
 * Example 1:
 *   Input: numCourses = 2, prerequisites = [[1,0]]
 *   Output: true  (Take 0 first, then 1)
 *
 * Example 2:
 *   Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *   Output: false (Cycle: 0 -> 1 -> 0)
 *
 * Constraints:
 *   1 <= numCourses <= 2000
 *   0 <= prerequisites.length <= 5000
 *
 * ---
 * Approach: Topological Sort (Kahn's Algorithm / BFS)
 *
 * 1. Build adjacency list and compute in-degree for each node.
 * 2. Add all nodes with in-degree 0 to queue (no prerequisites).
 * 3. Process queue: for each node, decrement in-degree of its neighbors.
 *    If a neighbor's in-degree becomes 0, add it to the queue.
 * 4. If all nodes are processed (count == numCourses), no cycle exists.
 *
 * Key insight: If a cycle exists, the nodes in the cycle will never have
 * in-degree 0, so they'll never enter the queue.
 *
 * Time:  O(V + E)
 * Space: O(V + E)
 */
// Only Applicable in DAG
public class CourseSchedule_207 {

    private static List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // [1, 0] means 0 is prerequisite for 1, There is edge from 0 to 1
        for (int[] pre : prerequisites) {
            adjacencyList.get(pre[1]).add(pre[0]);
        }

        return adjacencyList;
    }

    // ---------------------------------------------BFS-----------------------------------------------------

    // Topological Sort with Kahn’s Algorithm
    private static boolean hashCycle(int numCourses, List<List<Integer>> adjacencyList) {
        // 1. Stores the in-degree of each course
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int course : adjacencyList.get(i)) {
                inDegree[course]++;
            }
        }

        // 2. Add vertex whose in-degree is 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int processedCount = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            processedCount++;

            for (int nextCourse : adjacencyList.get(course)) {
                inDegree[nextCourse]--; // Remove in-degree of each course
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }

        }

        return processedCount == numCourses; // If all the courses are processed then course can be finished
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = buildGraph(numCourses, prerequisites);
        return hashCycle(numCourses, adjacencyList);
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        System.out.println(canFinish(numCourses, prerequisites));
    }
}
