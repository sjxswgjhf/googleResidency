import java.util.Random;
import java.util.TreeMap;

public class randomPickWeight {


    class Solution {
        TreeMap<Integer, Integer> map;
        int sum;
        int[] array;
        Random rand;
        public Solution(int[] w) {
            map = new TreeMap<>();
            array = w;
            rand = new Random();
            for(int i = 0; i < w.length; i++){
                sum += w[i];
                map.put(sum, i);
            }
        }

        public int pickIndex() {
            int i = rand.nextInt(sum);
            int index = map.ceilingKey(i+1);
            return map.get(index);
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}
