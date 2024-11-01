package neetcode150.maths;

public class SetMatrixZeroes_73 {

    public void setZeroes(int[][] matrix) {

        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int firstRow = -1;
        int firstCol = -1;

        // Calculate the first row and first column to be set to 0
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < columnLength; col++) {
                if (matrix[row][col] == 0) {
                    if (row == 0) firstRow = 0;
                    if (col == 0) firstCol = 0;

                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // Mark the rows and columns to be set to 0
        for (int row = 1; row < rowLength; row++) {
            for (int col = 1; col < columnLength; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (firstCol == 0) {
            for (int row = 0; row < rowLength; row++) {
                matrix[row][0] = 0;
            }
        }

        if (firstRow == 0) {
            for (int col = 0; col < columnLength; col++) {
                matrix[0][col] = 0;
            }
        }

    }
}
