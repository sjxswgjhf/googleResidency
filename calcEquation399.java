import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class calcEquation399 {

    class Node{
        String a;
        double d;
        public Node(String s, double r){
            a = s;
            d = r;
        }
    }


    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        HashMap<String, ArrayList<Node>> map = new HashMap<>();
        for(int i = 0; i < values.length; i++){
            String[] equation = equations[i];
            String a = equation[0];
            String b = equation[1];
            double r = values[i];
            ArrayList<Node> listA = map.getOrDefault(a, new ArrayList<>());
            ArrayList<Node> listB = map.getOrDefault(b, new ArrayList<>());
            Node nodeB = new Node(b, r);
            Node nodeA = new Node(a, 1.0/r);
            listA.add(nodeB);
            listB.add(nodeA);
            map.put(a, listA);
            map.put(b, listB);
        }
        double[] res = new double[queries.length];
        for(int i = 0; i < queries.length; i++){
            String[] query = queries[i];
            String s = query[0];
            String t = query[1];
            HashSet<String> visited = new HashSet<>();
            double rate = dfs(s,t, map, visited);
            res[i] = rate;
        }

        return res;
    }

    private double dfs(String s, String t, HashMap<String, ArrayList<Node>> map, HashSet<String> visited){
        if(!map.containsKey(s) || !map.containsKey(t)){
            return -1.0;
        }
        if(s.equals(t)){
            return 1.0;
        }
        if(visited.contains(s)){
            return -1.0;
        }
        visited.add(s);
        for(Node n : map.get(s)){
            double rate = dfs(n.a, t, map, visited);
            if(rate > -1.0){
                return rate * n.d;
            }
        }
        return -1.0;
    }
}
