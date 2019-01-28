import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class guessWord {

    interface Master{
        public int guess(String word);

    }

    /*
    首先这个secret在wordlist里面，我们要通过10次guess去找到这个word.
    */

    /*
    approach 1：随机选择一个word去reduce wordlist的范围，根据match的次数来reduce，先求得match的freq，然后用随机选择的词跟
    wordlist去做match，把所有match = freq的word拿出来，把 wordlist 重新 生成， 循环这样，这个方法的可能会wrong。因为
    我们选择的word可能会0match，那么我们没法做到reduce，所以有approach 2，建立在这个方法的idea上，先预计算哪个word我们需要去
    选择不是随机，而是通过比较所有的word，找到最小的那个mismatch freq的word
    For the random approach, time complexity O(N), average 6.5 guess, worst case 14 guess.
    */
    public void findSecretWord(String[] wordlist, Master master) {
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            String word = wordlist[rand.nextInt(wordlist.length)];
            int x = master.guess(word);
            if(x == 6){
                break;
            }else{
                List<String> words2 = new ArrayList<>();
                for(String str : wordlist){
                    if(match(str, word) == x){
                        words2.add(str);
                    }
                }
                wordlist = words2.toArray(new String[words2.size()]);

            }
        }
    }

    /*
    这个方法是O（N^2）但是worst guess是10，所以一定能guess到正确的解
    idea是先计算每个word在wordlist里面能mismatch == 0 的freq，即跟别的word完全不同，然后取一个freq最小的那个，这里要注意
    有可能有的word跟所有word都有match，那么再取guess的时候要注意nullpointerexception，通过比较freq来做到选哪个word，然后拿到
    wordlist里面去比较，这样就最大效益的reduce wordlist。
     */

    public void findSecretWord2(String[] wordlist, Master master){
        for(int i = 0; i < 10; i++){
            HashMap<String, Integer> map = new HashMap<>();
            String guessWord = new String();
            int freq = Integer.MAX_VALUE;
            for(String word : wordlist){
                for(String word2 : wordlist){
                    if(match(word, word2) == 0){
                        map.put(word, map.getOrDefault(word, 0)+1);
                    }
                }
            }
            for(String w : wordlist){
                if(map.getOrDefault(w, 0) < freq){
                    freq = map.getOrDefault(w, 0);
                    guessWord = w;
                }
            }

            int x = master.guess(guessWord);
            if(x == 6){
                break;
            }
            List<String> wordList2 = new ArrayList<>();
            for(String word : wordlist){
                if(match(guessWord, word) == x){
                    wordList2.add(word);
                }
            }
            wordlist = wordList2.toArray(new String[wordList2.size()]);
        }
    }

    private int match(String w1, String w2){
        int count = 0;
        for(int i = 0; i < w1.length(); i++){
            if(w1.charAt(i) == w2.charAt(i)){
                count++;
            }
        }
        return count;
    }
}
