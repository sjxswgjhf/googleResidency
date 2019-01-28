public class longestline562 {


    public int longestLine(int[][] M) {
        if(M == null || M.length == 0 || M[0] == null || M[0].length == 0){
            return 0;
        }
        int m = M.length;
        int n = M[0].length;
        int max = 0;
        int[][][] dp = new int[m][n][4];
        for(int i = 0; i < m; i++){
            for(int j = 0; j <n; j++){
                if(M[i][j] == 0){
                    continue;
                }
                for(int z = 0; z < 4; z++){
                    dp[i][j][z] = 1;
                }
                if(j>0) dp[i][j][0] += dp[i][j-1][0];
                if(i>0) dp[i][j][1] += dp[i-1][j][1];
                if(i>0 && j>0) dp[i][j][2] += dp[i-1][j-1][2];
                if(j<n-1 && i>0) dp[i][j][3] += dp[i-1][j+1][3];
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            }
        }
        return max;
    }

}
