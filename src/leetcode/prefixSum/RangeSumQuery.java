package leetcode.prefixSum;

/**
 * LeetCode 304: Range Sum Query 2D - Immutable
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Given a 2D matrix, handle multiple queries of the following type:
 *   - Calculate the sum of the elements inside the rectangle defined by its
 *     upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Example:
 *   Input:
 *     matrix = [[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]
 *     sumRegion(2,1,4,3) -> 8
 *     sumRegion(1,1,2,2) -> 11
 *     sumRegion(1,2,2,4) -> 12
 *
 * Constraints:
 *   - m == matrix.length, n == matrix[i].length
 *   - 1 <= m, n <= 200
 *   - -10^4 <= matrix[i][j] <= 10^4
 *   - At most 10^4 calls to sumRegion
 *
 * Approach: 2D Prefix Sum Matrix
 *   - Build a prefix sum matrix where prefixSum[i][j] = sum of all elements
 *     in the rectangle from (0,0) to (i-1,j-1).
 *   - Uses inclusion-exclusion principle:
 *     prefixSum[i][j] = matrix[i-1][j-1] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1]
 *   - Query answer using:
 *     sumRegion = prefixSum[r2+1][c2+1] - prefixSum[r1][c2+1] - prefixSum[r2+1][c1] + prefixSum[r1][c1]
 *
 * Time Complexity: O(m*n) for init, O(1) per query
 * Space Complexity: O(m*n)
 */
public class RangeSumQuery {

    int[][] prefixSum;

    public RangeSumQuery(int[][] matrix) {
        prefixSum = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i < prefixSum.length; i++) {
            for (int j = 1; j < prefixSum[0].length; j++) {

                prefixSum[i][j] = matrix[i - 1][j - 1] +
                        prefixSum[i - 1][j] +
                        prefixSum[i][j - 1] -
                        prefixSum[i - 1][j - 1];

            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1] -
                prefixSum[row1][col2 + 1] -
                prefixSum[row2 + 1][col1] +
                prefixSum[row1][col1];
    }

    public static void main(String[] args) {
        // Input 2D array
        int[][] matrix = {
                { 3, 0, 1, 4, 2 },
                { 5, 6, 3, 2, 1 },
                { 1, 2, 0, 1, 5 },
                { 4, 1, 0, 1, 7 },
                { 1, 0, 3, 0, 5 }
        };

        RangeSumQuery rangeSumQuery = new RangeSumQuery(matrix);
        System.out.println(rangeSumQuery.sumRegion(2, 1, 4, 3));
        System.out.println(rangeSumQuery.sumRegion(1, 1, 2, 2));
        System.out.println(rangeSumQuery.sumRegion(1, 2, 2, 4));
    }
}
