public class player {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }


    public static void player1(TreeNode root, TreeNode t){
        int p = dfs(root);
        TreeNode target = find(root, t);
        int cur = dfs(target);
        int l = dfs(target.left);
        int r = dfs(target.right);
        System.out.println(p +" "+ cur +" "+ l +" "+r);
    }

    private static TreeNode find(TreeNode root, TreeNode t){
        if(root == null){
            return null;
        }
        if(root.val == t.val){
            return root;
        }
        TreeNode left = find(root.left, t);
        TreeNode right = find(root.right, t);
        return left == null ? right : left;
    }


    private static int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        return dfs(root.left) + dfs(root.right) + 1;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(6);
        TreeNode t = new TreeNode(7);
        player1(root,t);

    }
}
