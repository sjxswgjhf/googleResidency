import java.util.*;

public class uniquePath {

    /*
    机器人只能走右上右下右边
     */

    public static int uniquePath(int m, int n){

        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0){
                    dp[j][i] = 1;
                }
                else if(i == 0 && j != 0){
                    dp[j][i] = 0;
                }
                else if(j== 0 && i != 0){
                    dp[j][i] = dp[j+1][i-1]+dp[j][i-1];
                }
                else if(j == m-1){
                    dp[j][i] = dp[j][i-1]+dp[j-1][i-1];
                }
                else{
                    dp[j][i] = dp[j-1][i-1] + dp[j][i-1] + dp[j+1][i-1];
                }
            }
        }
        return dp[0][n-1];
    }

    /*
    followup1: 优化空间复杂度至 O(n)
     */
    public static int uniquePath2(int m, int n){
        int[] dp = new int[m];
        int[] tmp = new int[m];
        dp[0] = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                int val1 = j == 0 ? 0 : dp[j-1];
                int val2 = dp[j];
                int val3 = j == m - 1? 0 : dp[j+1];
                tmp[j] = val1+val2+val3;
            }
            dp = Arrays.copyOfRange(tmp, 0, m);

        }
        return dp[0];
    }

    /*
    followup2: 给定矩形里的三个点，判断是否存在遍历这三个点的路经
     */
    public static boolean uniquePath3(int[][] points){
        List<int[]> list = new ArrayList<>();
        for(int [] point : points){
            list.add(point);
        }
        Collections.sort(list, (a, b) -> a[0]-b[0]);
        for(int i = 1; i < list.size(); i++){
            int[] cur = list.get(i);
            int[] prev = list.get(i-1);
            if(cur[1] == prev[1]){
                return false;
            }
            int len = cur[0]-prev[0];
            if(prev[1]-len <= cur[1] && prev[1]+len >= cur[1]){
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /*
    followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
    1：还是按照follow up 1的思路用滚动数组dp，但是如果当前列有需要到达的点时，只对该点进行dp，
    其他格子全部置零，表示我们只care这一列上经过目标点的路径
    2：如果一列上有多个需要达到的点，直接返回0；

    */
    public int uniquePaths4(int rows, int cols, int[][] points) {
        int[] dp = new int[rows];
        int[] tmp = new int[rows];
        dp[0] = 1;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] point : points){
            if(map.containsKey(point[1])){
                return 0;
            }else{
                map.put(point[1], point[0]);
            }
        }
        for(int c = 1; c < cols; c++){
            for(int r = 0; r < rows; r++){
                int val1 = r == 0 ? 0 : dp[r-1];
                int val2 = dp[r];
                int val3 = r == rows - 1 ? 0 : dp[r+1];
                tmp[r] = val1+val2+val3;
            }
            dp = Arrays.copyOfRange(tmp, 0, rows);
            if(map.containsKey(c)){
                int rowIndex = map.get(c);
                for(int i = 0; i < rows; i++){
                    if(i == rowIndex){
                        res = dp[i];
                    }else{
                        dp[i] = 0;
                    }
                }
            }
        }
        return res;
    }

    /*
    followup4: 给定一个下界 (x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)
     */
    public int uniquePaths5(int rows, int cols, int H) {
        return uniquePath(rows, cols) - uniquePath(H, cols);
    }


    /*
    followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量

    [1, 0, 0]
    idx : 0~2
idx -1 0 1  0 1 2   1 2 3
     \ | /  \ | /   \ | /
    [  1 ,   1,      0]

     */

    public int uniquePaths6(int rows, int cols){
        int[] dp = new int[cols];
        int[] tmp = new int[cols];
        dp[0] = 1;
        for(int r = 1; r < rows; r++){
            for(int c = 0; c < cols; c++){
                int val1 = c == 0 ? 0 : dp[c - 1];
                int val2 = dp[c];
                int val3 = c == cols-1 ? 0 : dp[c+1];
                tmp[c] = val1+val2+val3;
            }
            dp = Arrays.copyOfRange(tmp, 0, cols);
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        System.out.print(uniquePath(m,n));
        System.out.print(uniquePath2(m,n));
    }
}
