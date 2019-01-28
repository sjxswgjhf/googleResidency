import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class countDistinctSubstring {

    public static int count(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int count = 0;
        for(int l = 1; l <= s.length(); l++){
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i <= s.length() - l ; i++){
                int j = i + l;
                String sub = s.substring(i,j);
                set.add(sub);
            }
            count += set.size();
        }
        return count+1;
    }


    public static void main(String[] args) {
        String s = "ababa";
        System.out.println(count(s));
    }
}
