public class suffixTree {


    class SuffixTrieNode{
//        int MAX_CHAR = 26;
        SuffixTrieNode[] children;

        public SuffixTrieNode(){
            children = new SuffixTrieNode[26];
        }

        public void insert(String s){
            if(s.length() > 0){
                int idx = s.charAt(0)-'a';
                if(children[idx] == null){
                    children[idx] = new SuffixTrieNode();
                }
                children[idx].insert(s.substring(1));
            }
        }
    }

     class Suffix_trie{
        SuffixTrieNode root;
        public Suffix_trie(String s){
            root = new SuffixTrieNode();
            for(int i = 0; i < s.length(); i++){
                root.insert(s.substring(i));
            }
        }
        public int countNode(SuffixTrieNode node){
            if(node == null){
                return 0;
            }
            int count = 0;
            for(int i = 0; i < 26; i++){
                count += countNode(node.children[i]);
            }
            return count+1;
        }
        public int countInTrie(){
            return countNode(root);
        }
    }
}
