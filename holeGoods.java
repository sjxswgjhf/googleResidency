import java.util.Arrays;

public class holeGoods {


    /*
    从左向右，高度会被前面的洞限制
     */

    public int maxGoods(int[] holes, int[] goods){
        if(holes == null || holes.length == 0){
            return 0;
        }
        if(goods == null || goods.length == 0) {
            return 0;
        }
        int m = holes.length;
        int n = goods.length;
        int[] dp = new int[m];
        dp[0] = holes[0];
        for(int i = 1; i < m; i++){
            dp[i] = Math.min(dp[i-1], holes[i]);
        }
        int idx = 0;
        Arrays.sort(goods);
        for(int i = m - 1; i >=0; i--){
            if(dp[i] >= goods[idx]){
                idx++;
            }
        }
        return idx;
    }
}
