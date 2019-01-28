public class validWordAbbreviation408 {

    public boolean validWordAbbreviation(String word, String abbr) {
        int p1 = 0;
        int p2 = 0;
        while(p1 < word.length ()  && p2 < abbr.length()){
            if(word.charAt(p1) == abbr.charAt(p2)){
                p1++;
                p2++;
                continue;
            }
            if(abbr.charAt(p2) <= '0' || abbr.charAt(p2) > '9'){
                return false;
            }
            int prev = p2;
            while(p2 < abbr.length() && abbr.charAt(p2) >= '0' && abbr.charAt(p2) <= '9'){
                p2++;
            }
            int count = Integer.valueOf(abbr.substring(prev, p2));
            p1+=count;
        }
        return p1==word.length() && p2 == abbr.length();
    }

}
