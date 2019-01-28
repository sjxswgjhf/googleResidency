import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class findAndReplacePattern {


    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for(String word : words){
            if(match(word, pattern)){
                res.add(word);
            }
        }
        return res;
    }

    private boolean match(String w1, String w2){

        HashMap<Character, Character> w1map = new HashMap<>();
        HashMap<Character, Character> w2map = new HashMap<>();
        for(int i = 0; i < w1.length(); i++){
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if(!w1map.containsKey(c1) && !w2map.containsKey(c2)){
                w1map.put(c1,c2);
                w2map.put(c2,c1);
            }
            else if(w1map.containsKey(c1) && w2map.containsKey(c2)){
                char curC1 = w1map.get(c1);
                char curC2 = w2map.get(c2);
                if(c1 == curC2 && curC1 == c2){
                    continue;
                }else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    /*
    approach 2: int[] 来记录freq
     */
    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> res= new LinkedList<>();
        for (String w: words){
            int[] p= new int[26], s= new int[26];
            boolean same=true;
            for (int i=0; i<w.length(); i++){
                if (s[w.charAt(i)-'a']!=p[pattern.charAt(i)-'a']){
                    same=false;
                    break;
                }else{
                    s[w.charAt(i)-'a']=p[pattern.charAt(i)-'a']=i+1;
                }
            }
            if (same) res.add(w);
        }
        return res;
    }
}
