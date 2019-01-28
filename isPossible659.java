import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class isPossible659 {

    public boolean isPossible(int[] nums) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, Integer> tailfreq = new HashMap<>();
        for(int n : nums){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        for(int i : nums){
            if(freq.get(i) == 0){
                continue;
            }
            else if(tailfreq.getOrDefault(i, 0)>0){
                tailfreq.put(i, tailfreq.get(i) - 1);
                tailfreq.put(i+1, tailfreq.getOrDefault(i+1,0)+1);
            }
            else if(freq.getOrDefault(i+1, 0) > 0 && freq.getOrDefault(i+2, 0)>0){
                freq.put(i+1, freq.get(i+1)-1);
                freq.put(i+2, freq.get(i+2)-1);
                tailfreq.put(i+3, tailfreq.getOrDefault(i+3, 0)+1);
            }
            else{
                return false;
            }
            freq.put(i, freq.get(i)-1);
        }
        return true;
    }
}
