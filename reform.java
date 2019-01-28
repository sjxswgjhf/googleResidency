public class reform {
    /*
    一个已经sort 过的数组。[1,2,3,4,5,6,7] for example.
把它变成一个，偶数位的数字要大于所有这个位置之后的数字。 奇数位的数字要小于所有这个位置之后的所有数字。
上面那个例子变形之后应该是[7, 1, 6, 2, 5, 3, 4]

follow up如果数组没有sort 过呢？

思路：
如果不需要in-place，用一个额外的buffer数组双指针merge

如果需要in-place
[1, 2, 3, 4, 5 , 6, 7]
将原数组分成两半，先reverse后半部分, [1, 2, 3, 7, 6, 5, 4]
然后用shuffle string的方法将数组shuffle

     */

    /*
    inplace
     */
    public static int[] reform(int[] nums){

        int len = nums.length;
        int mid = len / 2;
        reverse(nums, mid, len-1);
        if(len % 2 == 0) {
            shuffle(nums, 0, len-1);
        }
        else{
            shuffle(nums, 0, len -2);
        }

        return nums;
    }

    private static void shuffle(int[] nums, int start, int end){
        for(int i  : nums){
            System.out.print(i+" ");
        }
        System.out.println();
        if(start >= end){
            return;
        }
        if(start + 1 == end){
            swap(nums, start, end);
            return;
        }
        int len = end - start + 1;
        int mid = start+len / 2;
        int lmid = start + len / 4;
        int rmid = mid + len /4;
        reverse(nums, lmid, mid-1);
        reverse(nums, mid, rmid-1);
        reverse(nums, lmid, rmid-1);
        shuffle(nums, start, start + (len/4) * 2 -1);
        shuffle(nums, start + (len / 4) *2, end);
    }


    private static void reverse(int[] nums, int s, int e){
        int l =s;
        int r = e;
        while(l<r){
            swap(nums,l,r);
            l++;
            r--;
        }
    }
    private static void swap(int[] nums, int s, int e){
        int tmp = nums[s];
        nums[s] =nums[e];
        nums[e] = tmp;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        reform(nums);
        for(int i  : nums){
            System.out.print(i+" ");
        }

    }
}
