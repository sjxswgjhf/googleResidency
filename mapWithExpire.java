import java.util.HashMap;

public class mapWithExpire {

    class MyMap{

        HashMap<Integer, Integer> map1;
        HashMap<Integer, Long> map2;

        public MyMap(){
            map1 = new HashMap<>();
            map2 = new HashMap<>();
        }

        public String get(int k){
            long cur = System.currentTimeMillis();
            long expired = map2.containsKey(k) ? map2.get(k) : 0;


            if(expired >= cur){
                return String.valueOf(map1.get(k));
            }else{
                return null;
            }
        }

        public void put(int k, int val, long duration){
            long cur = System.currentTimeMillis();
            long expired = duration + cur;
            map2.put(k, expired);
            map1.put(k, val);
        }
    }
}
