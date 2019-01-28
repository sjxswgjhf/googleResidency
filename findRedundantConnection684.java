import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class findRedundantConnection684 {


    /*
    O(N^2)
     */
    public int[] findRedundantConnectionDFS(int[][] edges) {
        int l = edges.length;
        int[] res = new int[2];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1; i <= l; i++){
            map.put(i, new ArrayList<>());
        }

        for(int[] edge : edges){
            HashSet<Integer> visited = new HashSet<>();
            int s = edge[0];
            int e = edge[1];
            if(dfs(s, e, map, visited)){
                return edge;
            }
            ArrayList<Integer> list1 = map.get(s);
            ArrayList<Integer> list2 = map.get(e);
            list1.add(e);
            list2.add(s);
            map.put(s, list1);
            map.put(e, list2);
        }
        return res;
    }

    private boolean dfs(int cur, int goal, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> visited){
        if(cur == goal){
            return true;
        }
        visited.add(cur);
        if(map.get(cur).size() == 0 || map.get(goal).size() == 0){
            return false;
        }
        for(int next : map.get(cur)){
            if(visited.contains(next))
                continue;
            if(dfs(next, goal, map, visited)){
                return true;
            }
        }
        return false;
    }

    /*
     O(N*alpha(N)) = O(N)  because (N * α(N)) ≈ O(N)
     */
    class UnionFind{
        int[] parents;
        int[] ranks;
        public UnionFind(int n){
            parents = new int[n+1];
            ranks = new int[n+1];
            for(int i = 0; i <= n; i++){
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public boolean union(int u, int v){
            int pu = find(u);
            int pv = find(v);
            if(pu == pv){
                return false;
            }
            if(ranks[pu] >= ranks[pv]){
                parents[pv] = pu;
                ranks[pu]++;
            }else{
                parents[pu] = pv;
            }
            return true;
        }

        public int find(int u){
            while(u != parents[u]){
                parents[u] = parents[parents[u]];
                u = parents[u];
            }
            return u;
        }
    }

    public int[] findRedundantConnectionUF(int[][] edges) {

        int l = edges.length;
        UnionFind uf = new UnionFind(l);
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if(uf.union(u,v) == false){
                return edge;
            }
        }
        return new int[2];
    }
}
