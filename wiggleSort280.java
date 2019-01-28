import java.util.Arrays;

public class wiggleSort280 {

    /*
    这题的要求就是even index要小于前后
     */

    public void wiggleSort(int[] nums) {
        for(int i = 0; i < nums.length; i+=2){
            if( i > 0 && nums[i] > nums[i-1] ){
                swap(nums, i-1, i);
            }
            if(i+1 < nums.length && nums[i] > nums[i+1]){
                swap(nums, i, i+1);
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public void wiggleSort2(int[] nums) {
        Arrays.sort(nums);
        for(int i = 2; i < nums.length; i+=2){
            swap(nums,i);
        }
    }

    private void swap(int[] nums, int i){
        int tmp = nums[i];
        nums[i] = nums[i-1];
        nums[i-1]=tmp;
    }
}
