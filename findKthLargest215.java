import java.util.Collections;
import java.util.PriorityQueue;

public class findKthLargest215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < nums.length; i++){
            pq.add(nums[i]);
        }
        while(k-->0){
            pq.poll();
        }
        return pq.peek();
    }
}
