package leetcode.graph.traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WallsGates_286 {

    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * You are given m x n 2D grid initialized with these three possible values, -1 → A wall or an obstacle, Zero → A gate, INF → Infinity means an empty room.
     * We use the value 231-1 = 2,147,483,647 to represent INF as you may assume that the distance to a gate is less than 2,147,483,647.
     * Find the distance to the nearest gate for each room in the grid.
     *
     * @param rooms a 2D grid of rooms
     */

    public static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        /*
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        BFS(rooms, queue);

        **/

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0)
                    DFS(rooms, i, j, 0);
            }
        }
    }

    /**
     * Breadth First Search to find the shortest distance
     *
     * @param rooms a 2D grid of rooms
     * @param queue a queue of rooms
     */
    public static void BFS(int[][] rooms, Queue<int[]> queue) {
        int m = rooms.length;
        int n = rooms[0].length;
        int distance = 0;
        // BFS to find the shortest distance
        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] direction : directions) {
                    assert point != null;
                    int x = direction[0] + point[0];
                    int y = direction[1] + point[1];

                    if (x < 0 || x >= m || y < 0 || y >= n || rooms[x][y] != Integer.MAX_VALUE) continue;
                    rooms[x][y] = distance;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }


    /**
     * Depth First Search to find the shortest distance
     *
     * @param rooms a 2D grid of rooms
     * @param i row index
     * @param j column index
     * @param distance distance from the gate
     *
     */
    private static void DFS(int[][] rooms, int i, int j, int distance) {
        int m = rooms.length;
        int n = rooms[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] < distance) {
            return;
        }

        rooms[i][j] = distance;

        DFS(rooms, i + 1, j, distance + 1); // Down
        DFS(rooms, i - 1, j, distance + 1); // Up
        DFS(rooms, i, j + 1, distance + 1); // Right
        DFS(rooms, i, j - 1, distance + 1); // Left
    }



    public static void main(String[] args) {
        int[][] rooms = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};

        int[][] output = {
                {3, -1, 0, 1 },
                {2,  2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4 }
        };

        wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));
        System.out.println(Arrays.deepEquals(rooms, output));
    }

}
