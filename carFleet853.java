import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

public class carFleet853 {

    /*
    这题就是看前车到终点的时间是不是比我要久，如果比我久，那么我可以赶上他，不会生成新的fleet，如果我赶不上前车，那么我就是
    单独的fleet
     */

    public int carFleet(int target, int[] position, int[] speed) {
        if(position == null || position.length == 0 || speed == null || speed.length == 0){
            return 0;
        }
        TreeMap<Integer, Double> map = new TreeMap<>(Collections.reverseOrder());
        int n = position.length;
        for(int i = 0; i < n; i++){
            map.put(position[i], (target-position[i])*1.0/speed[i]);
        }
        int fleet = 0;
        double cur = 0;
        for(double val : map.values()){
            if(val > cur){
                fleet++;
                cur = val;
            }
        }
        return fleet;
    }
}
