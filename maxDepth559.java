import java.util.List;

public class maxDepth559 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    int max = 0;
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        dfs(root, 1);
        return max;
    }
    private void dfs(Node root, int depth){
        if(root == null){
            return;
        }
        max = Math.max(max, depth);
        for(Node n : root.children){
            dfs(n, depth+1);
        }
    }
}
