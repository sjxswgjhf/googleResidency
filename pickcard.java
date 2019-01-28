import java.util.HashMap;

public class pickcard {


    public int pickcard(int[] nums){

        int n = nums.length;
        int[] dp = new int[n];
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i-1]+nums[i];
        }
        dp[0] = nums[0];

        return 0;
    }
}