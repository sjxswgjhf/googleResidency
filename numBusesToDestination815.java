import java.util.*;

public class numBusesToDestination815 {

    /*
    解题思路:遍历routes保存所有到这个route的bus，然后通过从S开始遍历bus，看当前的bus是不是有包含这个T，有的话return，
    没有就走到一层
     */


    public int numBusesToDestination(int[][] routes, int S, int T) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int res = 0;
        if(S == T){
            return res;
        }
        for(int i = 0; i < routes.length; i++){
            int[] route = routes[i];
            for(int j = 0; j < route.length; j++){
                ArrayList<Integer> busList = map.getOrDefault(route[j], new ArrayList<>());
                busList.add(i);
                map.put(route[j],busList);
            }
        }
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        while(!q.isEmpty()){
            int size = q.size();
            res++;
            while(size-->0){
                int routeIdx = q.poll();
                ArrayList<Integer> busList = map.get(routeIdx);
                for(int busIdx : busList){
                    if(visited.contains(busIdx)){
                        continue;
                    }
                    visited.add(busIdx);
                    int[] r = routes[busIdx];
                    for(int rIdx : r){
                        if(rIdx == T){
                            return res;
                        }else{
                            q.offer(rIdx);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
