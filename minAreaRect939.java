import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class minAreaRect939 {


    /*
    用map跟set来储存x对应的所有的y值
    然后loop points，比较所有的case，判断两个点是不是在同一高度或者垂直线上，是的话就继续，因为只能构成直线，不能构成rectangle
    然后如果不在一直线上，那么判断point1的y左边里面有没有point2，point2的y左边里有没有point1的，有的话就能构成rectangle，然后
    计算area
     */

    public int minAreaRect(int[][] points) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] point : points){
            Set<Integer> set = map.getOrDefault(point[0], new HashSet<>());
            set.add(point[1]);
            map.put(point[0], set);
        }
        int area = Integer.MAX_VALUE;
        for(int[] point1 : points){
            for(int[] point2 : points){
                if(point1[0] == point2[0] || point1[1] == point2[1]){
                    continue;
                }
                if(map.get(point1[0]).contains(point2[1]) && map.get(point2[0]).contains(point1[1])){
                    int width = Math.abs(point1[0] - point2[0]);
                    int heigth = Math.abs(point1[1] - point2[1]);
                    area = Math.min(area, width*heigth);
                }
            }
        }
        return area == Integer.MAX_VALUE ? 0 : area;
    }

}
