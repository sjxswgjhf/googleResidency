public class getMoneyAmount375 {


    /*
    这题本意是从一堆消费中取一个最小的，但是这个消费都是对我坏的
    从i~j，我选k作为我猜的数字，那么这个钱就是k，但是这个k不一定是最小的错误的钱总和，所以要遍历i~j从中找到最小的总和，
    For each number x in range[i~j]
    we do: result_when_pick_x = x + max{DP([i~x-1]), DP([x+1, j])}
    --> // the max means whenever you choose a number, the feedback is always bad and therefore leads you to a worse branch.
    then we get DP([i~j]) = min{xi, ... ,xj}
    --> // this min makes sure that you are minimizing your cost.
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int j = 2; j <= n; j++){
            for(int i = j -1; i >= 0; i--){
                int min = Integer.MAX_VALUE;
                for(int k = i+1; k < j;k++) {
                    int max = Math.max(dp[i][k - 1], dp[k + 1][j]) + k;
                    min = Math.min(min, max);
                }
                dp[i][j] = j-i == 1 ? i : min;
            }
        }
        return dp[1][n];
    }
}
