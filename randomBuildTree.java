import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomBuildTree {


    private class TreeNode{
        int val;
        List<TreeNode> children;
        public TreeNode(int x) {
            val = x;
            children = new ArrayList<>();
        }
    }

    public TreeNode getTree(int N) {
        if(N == 0) return null;
        List<TreeNode> pool = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            pool.add(new TreeNode(i));
        }
        List<TreeNode> tree = new ArrayList<>();
        Random rand = new Random();
        while (pool.size() > 0) {
            int idx = rand.nextInt(pool.size());
            TreeNode curr = pool.get(idx);
            if(tree.size() == 0) {
                tree.add(curr);
                pool.remove(idx);
            } else {
                int parent = rand.nextInt(tree.size());
                tree.get(parent).children.add(curr);
                tree.add(curr);
                pool.remove(idx);
            }
        }
        return tree.get(0);
    }


}
