import java.util.ArrayList;
import java.util.List;

public class pathMax {

    /*
    Given a N*N matrix with random amount of money in each cell, you start from top-left, and can only move from left to right, or top to bottom one step at a time until you hit the bottom right cell. Find the path with max amount of money on its way.
    Sample data:
    start
    |
    v
     5,   15,20,  ...
    10, 15,  5,   ...
    30,  5,  5,    ...
    …
                     ^end here.
     */


    public static int maxPath(int[][] matrix){

        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        /*
        [1  1   1]
       -1 0
        -|
        [1

         */
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j==0){
                    dp[i][j] = matrix[i][j];
                }
                else if(i == 0 && j != 0){
                    dp[i][j] = dp[i][j-1] + matrix[i][j];
                }
                else if(j == 0 && i != 0){
                    dp[i][j] = dp[i-1][j] + matrix[i][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]) + matrix[i][j];
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m-1][n-1];
    }
    /*
    O(N^2) -> O(N) space
     */
    public static int maxPath2(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(c == 0){
                    dp[c] +=  matrix[r][c];
                }
                else {
                    dp[c] = Math.max(dp[c - 1], dp[c]) + matrix[r][c];
                }
            }
        }
        return dp[n-1];
    }

    /*
    Follow up 1：要求重建从end 到 start的路径
    思路：用另一个额外数组记录每一步选择的parent，dp结束后，从end依次访问它的parent重建路径
     */
    static class Pair{
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static List<Pair> maxPath3(int[][] matrix){
        List<Pair> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return res;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(r == 0 && c == 0){
                    dp[r][c] = matrix[r][c];
                }
                else if(r != 0 && c == 0){
                    dp[r][c] = dp[r-1][c] + matrix[r][c];
                }
                else if(r == 0 && c != 0){
                    dp[r][c] = dp[r][c-1] + matrix[r][c];
                }else{
                    dp[r][c] = Math.max(dp[r][c-1], dp[r-1][c]) + matrix[r][c];
                }
            }
        }
        int a = m-1;
        int b = n-1;
        res.add(new Pair(a,b));
        while(true){
            if(a == 0 && b == 0){
                break;
            }
            else if(a == 0 && b != 0){
                b--;
            }
            else if(a!=0 && b == 0){
                a--;
            }else{
                if(dp[a-1][b] > dp[a][b-1]){
                    a--;
                }else{
                    b--;
                }
            }
            res.add(new Pair(a,b));
        }
        return res;
    }

    /*
    Follow up 2: 现在要求空间复杂度为O（1），dp且重建路径
    修改原数组
    */
    public static List<int[]> maxPath4(int[][] matrix){
        List<int[]> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return res;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0 && j != 0) {
                    matrix[i][j] = Math.abs(matrix[i][j - 1]) + matrix[i][j];
                    matrix[i][j] *= -1;
                } else if (i != 0 && j == 0) {
                    matrix[i][j] += Math.abs(matrix[i - 1][j]);
                } else {
                    int left = Math.abs(matrix[i][j - 1]);
                    int top = Math.abs(matrix[i - 1][j]);
                    if (left >= top) {
                        matrix[i][j] = (left + matrix[i][j]) * -1;
                    } else {
                        matrix[i][j] = top + matrix[i][j];
                    }
                }
            }
        }
        int a = m-1;
        int b = n-1;
        res.add(new int[]{a,b});
        while(true){
            int[] cur = new int[2];
            if(a==0 && b == 0){
                break;
            }
            else if(a == 0 && b > 0){
                b--;
            }
            else if(a > 0 && b == 0){
                a--;
            }
            else{
                if(matrix[a][b] < 0){
                    b--;
                }else{
                    a--;
                }
            }
            cur[0] = a;
            cur[1] = b;
            res.add(cur);
        }
        return res;
    }


    public static void main(String[] args) {

        int[][] matrix = {{5,15,10},{10,15,5},{10,5,5}};
        for(int[] p : maxPath4(matrix)){
            System.out.println(p[0]+ " "+p[1]);
        }
        for(Pair p : maxPath3(matrix)){
            System.out.println(p.x+ " "+p.y);
        }
    }
}