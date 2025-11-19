package leetcode.graph.traversal;

import java.util.*;

public class PacificAtlanticWaterFlow {
    private final int[][] Directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public record CellCoordinate(int x, int y) {
    }

    private void BFS(Queue<CellCoordinate> queue, boolean[][] visited, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        while (!queue.isEmpty()) {
            CellCoordinate cell = queue.poll();
            int xOld = cell.x();
            int yOld = cell.y();

            for (int[] direction : Directions) {
                int x = xOld + direction[0];
                int y = yOld + direction[1];
                if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || heights[x][y] < heights[xOld][yOld])
                    continue;
                visited[x][y] = true;
                queue.add(new CellCoordinate(x, y));
            }

        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        // Edge case
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        // Matrix to mark cells water can flow to atlantic
        boolean[][] atlantic = new boolean[m][n];
        // Matrix to mark cells water can flow to pacific
        boolean[][] pacific = new boolean[m][n];
        // Queue to store coordinates of cells of atlantic
        Queue<CellCoordinate> a = new LinkedList<>();
        // Queue to store coordinates of cells of pacific
        Queue<CellCoordinate> p = new LinkedList<>();

        // Add first column coordinates to pacific and last column coordinates to atlantic
        for (int i = 0; i < m; i++) {
            p.add(new CellCoordinate(i, 0));
            a.add(new CellCoordinate(i, n - 1));
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }

        // Add first row coordinates to pacific and last row coordinates to atlantic
        for (int i = 0; i < n; i++) {
            p.add(new CellCoordinate(0, i));
            a.add(new CellCoordinate(m - 1, i));
            pacific[0][i] = true;
            atlantic[m - 1][i] = true;
        }

        BFS(p, pacific, heights);
        BFS(a, atlantic, heights);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> temp = new ArrayList<>(Arrays.asList(i, j));
                    result.add(temp);
                }
            }
        }

        return result;
    }


    static void main(String[] args){
        int[][] heights = { {1, 2, 2, 3, 5},
                            {3, 2, 3, 4, 4},
                            {2, 4, 5, 3, 1},
                            {6, 7, 1, 4, 5},
                            {5, 1, 1, 2, 4} };
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(heights));
    }

}
