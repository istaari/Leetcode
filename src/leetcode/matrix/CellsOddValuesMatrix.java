package leetcode.matrix;

/*
 * LC 1252 - Cells with Odd Values in a Matrix
 *
 * There is an m x n matrix that is initialized to all 0s. You are given an array
 * indices where indices[i] = [ri, ci] represents an operation where you must
 * increment all cells in row ri and column ci by 1.
 *
 * Perform all the operations, then return the number of cells with odd values.
 *
 * Example 1:
 *   Input:  m=2, n=3, indices=[[0,1],[1,1]]
 *   Start:  [[0,0,0],[0,0,0]]
 *   After [0,1]: increment row 0 and col 1 → [[1,2,1],[0,1,0]]
 *   After [1,1]: increment row 1 and col 1 → [[1,3,1],[1,2,1]]
 *   Output: 4 (cells with odd values: 1,3,1,1)
 *
 * Example 2:
 *   Input:  m=2, n=2, indices=[[1,1],[0,0]]
 *   Output: 0
 *
 * Constraints:
 *   1 <= m, n <= 50
 *   1 <= indices.length <= 100
 *   0 <= ri < m, 0 <= ci < n
 *
 * Time: O(indices + m + n)   Space: O(m + n)
 */
public class CellsOddValuesMatrix {

    public static int oddCells(int m, int n, int[][] indices) {
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // just tally increments per row and col — no need to build the matrix
        for (int[] index : indices) {
            rowCount[index[0]]++;
            colCount[index[1]]++;
        }

        // cell (i,j) = rowCount[i] + colCount[j], odd iff exactly one is odd
        int oddRows = 0, oddCols = 0;
        for (int x : rowCount) if (x % 2 != 0) oddRows++;
        for (int x : colCount) if (x % 2 != 0) oddCols++;

        int evenRows = m - oddRows;
        int evenCols = n - oddCols;

        // odd+even=odd, even+odd=odd, odd+odd=even, even+even=even
        return oddRows * evenCols + evenRows * oddCols;
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int[][] indices = { { 0, 1 }, { 1, 1 } };
        System.out.println(oddCells(m, n, indices));
    }
}
