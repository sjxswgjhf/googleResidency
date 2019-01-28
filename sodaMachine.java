import java.util.HashMap;
import java.util.List;

public class sodaMachine {

    class Soda{
        int hi;
        int low;

    }


    public boolean dfs(HashMap<String, Boolean> memo, List<Soda> sodas, int volLower, int volUpper, int targetLower,
                       int targetUpper){
        String str = volLower +"-"+volUpper;
        if(memo.containsKey(str)){
            return memo.get(str);
        }
        if(volLower >= targetLower && volUpper <= targetUpper){
            return true;
        }
        if(volLower < targetLower || volUpper > targetUpper ){
            return false;
        }
        for(Soda soda : sodas){
            int nextLower = volLower + soda.low;
            int nextUpper = volUpper + soda.hi;
            if(dfs(memo, sodas, nextLower, nextUpper, targetLower, targetLower)){
                memo.put(str, true);
                return true;
            }
        }
        memo.put(str, false);
        return false;
    }
}
