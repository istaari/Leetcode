package leetcode.graph.cycle.dp;


// The problem can be modeled as a DAG (Directed Acyclic Graph).
// Each cell in the grid is a node.
// A directed edge exists from a cell to any adjacent cell with a strictly greater value.
// Since movement only goes to strictly larger values, cycles are not possible.
// Each valid move corresponds to a directed edge in the graph.
class IncreasingPathsGrid {

    private int[][] dirs = new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    private static int MOD = 1_000_000_007;

    private int dfs(int i, int j, int m, int n, int[][] grid, int[][] memo) {
        if (memo[i][j] != 0)
            return memo[i][j];

        int count = 1;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];

            if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] > grid[i][j]) {
                count = (count + dfs(r, c, m, n, grid, memo)) % MOD;
            }
        }

        memo[i][j] = count;
        return count;
    }

    // Count a path for each cell
    public int countPaths(int[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                result = (result + dfs(i, j, m, n,grid, memo)) % MOD;
            }
        }

        return result;
    }


    public static void main(String[] args){
        int[][] grid = new int[][]{ {1,1}, {3,4} };
        IncreasingPathsGrid inc = new IncreasingPathsGrid();
        System.out.println(inc.countPaths(grid));
    }

}