import java.util.HashMap;

public class longestConsecutive128 {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        //key is target and val is lenth
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for(int i = 0; i < nums.length; i++){

            int cur = nums[i];
            if(map.containsKey(cur)){
                continue;
            }
            // no neighbours
            else if(!map.containsKey(cur-1) && !map.containsKey(cur+1)){
                map.put(cur, 1);
            }
            //one neighbour
            else if(!map.containsKey(cur-1) && map.containsKey(cur+1)){
                //right neighbout, and cur become new left side of the consecutive sequence
                int len = map.get(cur+1);
                // update left side and right side
                map.put(cur, len+1);
                map.put(cur + len, len+1);
                //update max
                max = Math.max(len+1, max);
            }
            else if(map.containsKey(cur-1) && !map.containsKey(cur+1)){
                //left neighbour and cur become new right side of the consecutive sequence
                int len = map.get(cur-1);
                map.put(cur, len+1);
                map.put(cur-len, len+1);
                max = Math.max(len+1, max);
            }
            //has left and right neighbour
            else{
                int leftLen = map.get(cur-1);
                int rightLen =map.get(cur+1);
                map.put(cur-leftLen, leftLen+rightLen+1);
                map.put(cur+rightLen, leftLen+rightLen+1);
                map.put(cur, leftLen+rightLen+1);
                max = Math.max(max, leftLen + rightLen + 1);
            }
        }
        return max;
    }
}
