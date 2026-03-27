package leetcode.graph.topological.DAG;

import java.util.*;

/**
 * 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where
 * prerequisites[i] = [ai, bi] indicates that you must take course bi first
 * if you want to take course ai.
 *
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *   Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *   Output: [0,1,2,3] or [0,2,1,3] (both valid topological orders)
 *
 * Example 2:
 *   Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *   Output: [] (cycle exists, impossible)
 *
 * Constraints:
 *   1 <= numCourses <= 2000
 *   0 <= prerequisites.length <= numCourses * (numCourses - 1)
 *
 * ---
 * Approach: Topological Sort (Kahn's Algorithm / BFS)
 *
 * Same as CourseSchedule_207, but instead of just checking feasibility,
 * we record the processing order. The BFS dequeue order IS the topological order.
 *
 * Time:  O(V + E)
 * Space: O(V + E)
 */
public class CourseSchedule_210 {

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

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = buildGraph(numCourses, prerequisites);
        int[] result = new int[numCourses];
        int index = 0;

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

        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            for (int nextCourse : adjacencyList.get(course)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        return result;
    }

    
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }
}
