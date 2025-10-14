package leetcode.graph.topological.directedAcyclicGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


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

    //---------------------------------------------BFS-----------------------------------------------------

    // Topological Sort with Kahn’s Algorithm
    private static boolean hashCycle(int numCourses, List<List<Integer>> adjacencyList) {
        //  1. Stores the in-degree of each course
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

    //---------------------------------------------DFS-----------------------------------------------------

    /**
     * Cycle Detection using Colors (Three-State DFS Marking Method)
     *
     * 0 (Unvisited) - This indicates that the vertex (course) has not been visited
     * 1 (Visiting/In Progress) - This indicates that the vertex is currently being visited
     * 2 (Visited/Completed) - This indicates that the vertex and all its adjacent vertices have been fully explored
     */
    private static boolean hashCycle(int course, List<List<Integer>> adjacencyList, int[] visited) {
        if (visited[course] == 1) {
            return true;
        }
        if (visited[course] == 2) {
            return false;
        }

        visited[course] = 1;

        for (int nextCourse : adjacencyList.get(course)) {
            if (hashCycle(nextCourse, adjacencyList, visited)) return true;
        }

        visited[course] = 2;

        return false;
    }


    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = buildGraph(numCourses, prerequisites);

        // Initialize to 0 which is
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && hashCycle(i, adjacencyList, visited)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(canFinish(numCourses, prerequisites));
    }
}
