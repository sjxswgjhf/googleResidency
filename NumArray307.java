public class NumArray307 {

    /*
    解法1：binary index tree
     */

    class FenwickTree{
        int[] sums;
        public FenwickTree(int n){
            sums = new int[n+1];
        }

        public void update(int i, int delate){
            while(i < sums.length){
                sums[i] += delate;
                i += i & -i;
            }
        }

        public int query(int i){
            int res = 0;
            while(i>0){
                res += sums[i];
                i -= i & -i;
            }
            return res;
        }
    }

    FenwickTree ft;
    int[] nums;

    public NumArray307(int[] nums){
        this.nums = nums;
        ft = new FenwickTree(nums.length);
        for(int i = 0; i < nums.length; i++) {
            ft.update(i+1, nums[i]);
        }
    }

    public void update(int i, int value){
        ft.update(i+1, value - nums[i]);
        nums[i] = value;
    }

    public int query(int i, int j){
        return ft.query(j+1) - ft.query(i);
    }


    /*
    解法2:线段树
     */

    class NumArray{
        /*
        线段树构造: sum代表区间start~end的sum，left，right是左右孩子，start，end代表这个node所包括的区间
         */
        class SegmentTree{
            int start, end;
            SegmentTree left, right;
            int sum;
            public SegmentTree(int s, int e) {
                this.start = s;
                this.end = e;
                left = null;
                right = null;
                sum = 0;
            }
        }

        SegmentTree root;
        /*
        题目一开始先根据input来构建segment tree
         */
        public NumArray(int[] nums){
            root = buildTree(nums, 0, nums.length - 1);
        }
        /*
        当到leaf的时候root的sum就是nums[i]的值了，然后的话recursion先构造左子树跟右子树，然后再根据左右子树的sum来更新当前
        node的sum，最后返回当前node
         */
        private SegmentTree buildTree(int[] nums, int start, int end){
            if(start > end){
                return null;
            }else{
                SegmentTree root = new SegmentTree(start, end);
                if(start == end){
                    root.sum = nums[start];
                }else {
                    int mid = start + (end - start) / 2;
                    root.left = buildTree(nums, start, mid);
                    root.right = buildTree(nums, mid + 1, end);
                    root.sum = root.left.sum + root.right.sum;
                }
            }
            return root;
        }

        public void update(int i, int val){
            update(root, i, val);
        }

        /*
        update的时候先要去找到是在左边还是右边，停止条件当然是leaf的时候即s=e，更新val，然后还要更新所有跟这个pos有关系的区间
        即找root的mid跟pos比较，如果mid较大，那么就说明要更新左边那块，如果mid较大，就更新右边那块，原理跟buildtree一样，
        利于recursion的特性去更新左右子树然后更新root的sum
         */
        public void update(SegmentTree root, int pos, int val){
            if(root.start == root.end){
                root.sum = val;
            }
            else {

                int mid = root.start + (root.end - root.start) / 2;
                if (mid >= pos) {
                    update(root.left, pos, val);
                } else {
                    update(root.right, pos, val);
                }
                root.sum = root.left.sum + root.right.sum;
            }
        }

        public int sumRange(int i, int j){
            return sumRange(root, i, j);
        }
        /*
        找sum的时候要看所需要的区间是在哪，如果在root的左边，那么就往左边找，如果在root的右边那边往右边找，也可能是一些
        在root的左边区间，一些在右边区间，那么就需要两边找了之后加起来，即一共三种情况
         */
        public int sumRange(SegmentTree root, int start, int end){

            if(root.start == start && root.end == end){
                return root.sum;
            }else{

                int mid = root.start + (root.end - root.start) / 2;
                if(mid >= end){
                    return sumRange(root.left, start, end);
                }
                else if(start > mid){
                    return sumRange(root.right, start, end);
                }else{
                    return sumRange(root.left, start, mid) + sumRange(root.right, mid+1, end);
                }
            }
        }

    }


}
