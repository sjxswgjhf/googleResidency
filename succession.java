import java.util.*;

public class succession {

    static HashMap<String, ArrayList<String>> map = new HashMap<>();
    static HashSet<String> deathTable = new HashSet<>();
    static String root = "king";
    public static void birth(String father, String son){
        ArrayList<String> sons = map.getOrDefault(father, new ArrayList<>());
        sons.add(son);
        map.put(father, sons);
    }

    public static void death(String name){
        deathTable.add(name);
    }

    public static List<String> getOrder(){

        List<String> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private static void dfs(List<String> res, String root){
        if(!deathTable.contains(root)){
            res.add(root);
        }
        if(map.get(root) == null){
            return;
        }
        List<String> sons = map.get(root);
        for(int i = 0; i < sons.size(); i++){
            dfs(res,sons.get(i));
        }
    }

    public static void main(String[] args) {
        String k = "king";
        String a = "a";
        String a1 = "a1";
        String a2 = "a2";
        String a3 = "a3";
        String b = "b";
        String c = "c";
        birth(k,a);
        birth(a,a1);
        birth(a,a2);
        birth(a,a3);
        birth(k,b);
        birth(k,c);
        death(k);
        death(a2);
        List<String> res = getOrder();
        System.out.println(res);
        LinkedList<Integer> s = new LinkedList<>();
        s.removeFirst();

    }

}
