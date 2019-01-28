import java.util.HashMap;

public class kIncreasingSequence {


    public static int findKIncSeq(int[] nums,int k){

        HashMap<Integer, Integer> map = new HashMap<>();
        int max=0;
        for(int i = 0; i < nums.length; i++){

            if(map.containsKey(nums[i])){
                map.put(nums[i]+k, map.get(nums[i])+1);
            }else{
                map.put(nums[i]+k, 1);
            }
            if(map.get(nums[i]+k)>max){
                max = map.get(nums[i]+k);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = {2,1,3,5,4,7,8,11};
        int k = 3;
        System.out.println(findKIncSeq(nums, k));
    }
}
