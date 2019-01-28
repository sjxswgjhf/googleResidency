import java.util.Arrays;

public class splitArray410 {

    /*
    把nums分成m组，那么当只有一组的时候就是所有的东西的和，当m>=2的时候
     */

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sums = new int[n];
        sums[0]=nums[0];
        for(int i = 1; i< n; i++){
            sums[i] =sums[i-1]+nums[i];
        }
        int[][] dp = new int[m+1][n];
        for(int[] i : dp){
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        //i is first i num
        for(int i = 0; i < n; i++){
            dp[1][i] = sums[i];
        }
        /*
        dp[i][j] is the min subarray sum
        即i从2开始，组的数量是i
        j是个数，要分成i组，那么我至少需要i个元素，即index至少从i-1开始，
        k is the split index，from idx 0 ~ j
        dp[i][j]是根据我在第k个地方分割，前i-1个组从0～k给我的最大的sum，跟我把k～j组成一个组的最大sum，取大的那个，跟
        已经取得的sum取小的
        Math.max(dp[i-1][k], sums[j]-sums[k]) mean in this split method, the max subarray sum we can get
        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k], sums[j]-sums[k]))
        in all the split ways, we need choose the min sub max.
         */
        for(int i = 2; i <= m; i++){
            for(int j = i - 1; j < n; j++){
                for(int k = 0; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k], sums[j]-sums[k]));
                }
            }
        }
        return dp[m][n-1];


    }

}
