public class NumMatrix308 {

    /*
    解法1: binary index tree
    用二维的二叉索引树去解,原理跟一维的一模一样
    */

    int[][] trees;
    int[][] nums;
    int m;
    int n;
    public NumMatrix308(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        nums = new int[m][n];
        trees = new int[m+1][n+1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }

    private void update(int row, int col, int val){
        if(m == 0 || n == 0){
            return;
        }
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for(int i = row+1; i <= m; i += i & -i){
            for(int j = col + 1; j <= n; j += j&-j){
                trees[i][j] += delta;
            }
        }
    }

    public int sumRange(int row1, int col1, int row2, int col2){
        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
    }

    public int sum(int row, int col){
        int res = 0;
        for(int i = row; i > 0; i -= i & -i){
            for(int j = col; j > 0; j -= j & -j){
                res += trees[i][j];
            }
        }
        return res;
    }

    /*
    解法2：线段树
    因为这个题用线段树感觉好麻烦，所以没记。。以下解法是leetcode的讨论里面的

    Binary Indexed Tree solution is faster and easier compared to this. Just share Segment Tree Solution here.
    The idea is quite similar to 1D solution. The major difference is that each
    TreeNode now has 4 children instead of 2.
     */
    class NumMatrix {
        TreeNode root;

        class TreeNode {
            int sum;
            int row1, col1, row2, col2;
            TreeNode c1, c2, c3, c4;

            public TreeNode(int r1, int c1, int r2, int c2) {
                row1 = r1;
                row2 = r2;
                col1 = c1;
                col2 = c2;
                this.sum = 0;
            }
        }

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                root = null;
            } else {
                root = buildTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
            }
        }

        private TreeNode buildTree(int[][] matrix, int r1, int c1, int r2, int c2) {
            if (r2 < r1 || c2 < c1) {
                return null;
            }
            TreeNode node = new TreeNode(r1, c1, r2, c2);
            if (r1 == r2 && c1 == c2) {
                node.sum = matrix[r1][c1];
                return node;
            }
            int rowMid = (r2 + r1) / 2;
            int colMid = (c2 + c1) / 2;
            node.c1 = buildTree(matrix, r1, c1, rowMid, colMid);
            node.c2 = buildTree(matrix, r1, colMid + 1, rowMid, c2);
            node.c3 = buildTree(matrix, rowMid + 1, c1, r2, colMid);
            node.c4 = buildTree(matrix, rowMid + 1, colMid + 1, r2, c2);
            node.sum += node.c1 == null ? 0 : node.c1.sum;
            node.sum += node.c2 == null ? 0 : node.c2.sum;
            node.sum += node.c3 == null ? 0 : node.c3.sum;
            node.sum += node.c4 == null ? 0 : node.c4.sum;
            return node;
        }

        public void update(int row, int col, int val) {
            update(root, row, col, val);
        }

        private void update(TreeNode root, int r, int c, int val) {
            if (root.row1 == root.row2 && root.row1 == r && root.col1 == root.col2 && root.col1 == c) {
                root.sum = val;
                return;
            }
            int rowMid = (root.row1 + root.row2) / 2;
            int colMid = (root.col1 + root.col2) / 2;
            TreeNode next;
            if (r <= rowMid) {
                if (c <= colMid) {
                    next = root.c1;
                } else {
                    next = root.c2;
                }
            } else {
                if (c <= colMid) {
                    next = root.c3;
                } else {
                    next = root.c4;
                }
            }
            root.sum -= next.sum;
            update(next, r, c, val);
            root.sum += next.sum;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegion(root, row1, col1, row2, col2);
        }

        private int sumRegion(TreeNode root, int r1, int c1, int r2, int c2) {
            if (root.row1 == r1 && root.row2 == r2 && root.col1 == c1 && root.col2 == c2) {
                return root.sum;
            }
            int rowMid = (root.row1 + root.row2) / 2;
            int colMid = (root.col1 + root.col2) / 2;
            if (rowMid >= r2) {
                if (colMid >= c2) {
                    return sumRegion(root.c1, r1, c1, r2, c2);
                } else if (c1 >= colMid + 1) {
                    return sumRegion(root.c2, r1, c1, r2, c2);
                } else {
                    return sumRegion(root.c1, r1, c1, r2, colMid) + sumRegion(root.c2, r1, colMid + 1, r2, c2);
                }
            } else if (r1 >= rowMid + 1) {
                if (colMid >= c2) {
                    return sumRegion(root.c3, r1, c1, r2, c2);
                } else if (c1 >= colMid + 1) {
                    return sumRegion(root.c4, r1, c1, r2, c2);
                } else {
                    return sumRegion(root.c3, r1, c1, r2, colMid) + sumRegion(root.c4, r1, colMid + 1, r2, c2);
                }
            } else {
                if (colMid >= c2) {
                    return sumRegion(root.c1, r1, c1, rowMid, c2) + sumRegion(root.c3, rowMid + 1, c1, r2, c2);
                } else if (c1 >= colMid + 1) {
                    return sumRegion(root.c2, r1, c1, rowMid, c2) + sumRegion(root.c4, rowMid + 1, c1, r2, c2);
                } else {
                    return sumRegion(root.c1, r1, c1, rowMid, colMid) + sumRegion(root.c2, r1, colMid + 1, rowMid, c2) + sumRegion(root.c3, rowMid + 1, c1, r2, colMid) + sumRegion(root.c4, rowMid + 1, colMid + 1, r2, c2);
                }
            }
        }
    }
}
