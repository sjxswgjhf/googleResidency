import java.util.HashMap;

public class predictWinner {

    public boolean PredictTheWinner(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if(nums == null || nums.length == 0){
            return true;
        }
        int dif = getDif(nums, 0, nums.length - 1, map);
        return dif >= 0;
    }

    private int getDif(int[] nums, int l, int r, HashMap<Integer, Integer> map){
        if(l == r){
            return nums[l];
        }
        int k = l * nums.length + r;
        if(map.containsKey(k)){
            return map.get(k);
        }
        int pickLeft = nums[l] - getDif(nums, l + 1, r, map);
        int pickRight = nums[r] - getDif(nums, l, r-1, map);
        int tmp = Math.max(pickLeft, pickRight);
        map.put(k, tmp);
        return tmp;
    }

}
