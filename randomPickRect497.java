import java.util.Random;
import java.util.TreeMap;

class Solution {

    int[][] arrays;
    TreeMap<Integer, Integer> map;
    int area;
    Random rand;

    /*
    这题解题思路是：先计算总的area然后根据area来random一个数字然后选对应的rect，这里用treemap的ceilingkey method来实现
    通过area找对应的rect，因为题目里有edge case是直线不是rect所以计算面积的时候长宽+1来处理area = 0 的情况，避免重叠，
    在找到对应的rect后从array里面拿出，然后求得左右上下界限值，然后再界限值里面随机取点
     */
    public Solution(int[][] rects) {
        rand = new Random();
        map = new TreeMap<>();
        arrays = rects;
        int i = 0;
        for(int[] rect : rects){
            area += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            map.put(area, i++);
        }
    }

    public int[] pick() {
        int i = map.ceilingKey(rand.nextInt(area + 1));
        int[] rect = arrays[map.get(i)];
        int x_low = rect[0];
        int x_high = rect[2];
        int y_low = rect[1];
        int y_high = rect[3];
        int x_rand = rand.nextInt(x_high-x_low+1)+x_low;
        int y_rand = rand.nextInt(y_high-y_low+1)+y_low;
        return new int[]{x_rand, y_rand};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */