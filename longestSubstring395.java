public class longestSubstring395 {


    /*
    divide and conquer:
    通过计算freq来判断那个char是不是满足k的条件，不满足就要拆分这个str成两部分再继续判断，即divide and conquer
     */
    public int longestSubstring(String s, int k) {

        char[] str = s.toCharArray();
        return helper(str, 0, s.length(), k);
    }

    private int helper(char[] str, int start, int end, int k){
        if(end - start < k){
            return 0;
        }
        int[] count = new int[26];
        for(int i = 0; i < str.length; i++){
            count[str[i] - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(count[i] < k && count[i] > 0){
                for(int j = start; j < end; j++){
                    if(str[j] == (i+'a')){
                        int left = helper(str, start, j, k);
                        int right = helper(str, j+1, end,k);
                        return Math.max(left,right);
                    }
                }
            }
        }
        return end-start;
    }
}
