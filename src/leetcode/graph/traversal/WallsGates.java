package leetcode.graph.traversal;

import java.util.Arrays;
import java.util.Queue;

public class WallsGates {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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


    public static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) DFS(rooms, i, j, 0);
            }
        }
    }

    static void main(String[] args) {
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
