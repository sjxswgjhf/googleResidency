import java.util.ArrayList;
import java.util.List;

public class validBST {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }



    public static TreeNode validBST(TreeNode root){

        if(root == null){
            return null;
        }
        TreeNode res = dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return res;
    }

    private static TreeNode dfs(TreeNode root, long left, long right){
        if(root == null){
            return null;
        }
        if(root.val <= left || root.val >= right){
            return null;
        }
        root.left = dfs(root.left,  left, root.val);
        root.right = dfs(root.right, root.val, right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(8);
        TreeNode r = validBST(root);
        List<Integer> list = new ArrayList<>();
        inorder(r,list);
        for(int i : list){
            System.out.print(i+" ");
        }
    }

    private static void inorder(TreeNode res, List<Integer> list){
        if(res == null){
            return;
        }
        inorder(res.left, list);
        list.add(res.val);
        inorder(res.right, list);
    }

}
