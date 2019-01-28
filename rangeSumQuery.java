public class rangeSumQuery {

    /*
    Approach 1: naive
    update: O(1)
    sum: O(m*n)
     */
    class NumMatrix {

        int[][] nums;

        public NumMatrix(int[][] matrix) {
            nums = matrix;
        }

        public void update(int row, int col, int val) {
            nums[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for(int i = row1; i <= row2; i++){
                for(int j = col1; j <= col2; j++){
                    sum += nums[i][j];
                }
            }
            return sum;
        }
    }

    /*
    Approach: prefix ColSum
    update: O(m)
    sum: O(n)
     */

    class NumMatrix2 {
        int[][] colSums;
        int[][] matrixs;

        public NumMatrix2(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length ==0){
                return;
            }
            matrixs = matrix;
            colSums = new int[matrix.length + 1][matrix[0].length];
            for(int i = 1; i <= matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    colSums[i][j] = colSums[i-1][j] + matrix[i-1][j];
                }
            }
        }

        public void update(int row, int col, int val) {
            for(int i = row + 1; i < colSums.length; i++){
                colSums[i][col] = colSums[i][col] - matrixs[row][col] + val;
            }
            matrixs[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res =0;
            for(int i = col1; i <= col2; i++){
                res += colSums[row2 + 1][i] - colSums[row1][i];
            }
            return res;
        }
    }

    /*
    Binary Index Tree:
    O(log(m*n))
     */
    class NumMatrix3 {
        int[][] tree;
        int[][] nums;
        int m;
        int n;

        public NumMatrix3(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m+1][n+1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            int delta = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i <= m; i += i & (-i)) {
                for (int j = col + 1; j <= n; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }





    }
}
