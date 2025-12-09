package leetcode.prefixSum;

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
