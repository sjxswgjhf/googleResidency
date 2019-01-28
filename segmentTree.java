public class segmentTree {

    /*
    线段树学习：
    首先线段树能高效解决区间的动态查询问题，需要O(N)的空间跟O(logN)的查询时间
    线段树是一个满二叉树，所以他有N个leaf node和N-1个non leaf node，所以total的node是2n-1
    个，这也是为什么space是O(N)因为2n-1的space complexity就是n
     */

    /*
    区间最小值查询:
    如果用naive的查询的话需要O(N)的时间，O(1)space，如果大量的查询量是N的话那么就是O(N^2)

    也是可以预处理各区间的最小值，即i-j，但需要一个二维数组保存，那样查询可以做到O(1)，但是当数据非常大时，所消耗的空间也会很大
    而且当update一个数时，更新这个区间也是非常麻烦的

    所以线段树在这类题目方面就是很好的选择
    消耗O(N)的空间，查询做到了O(logN)
     */

    /*
    首先是构造线段树: 1. leaf node就是array里面的值
                    2. non leafnode就是这个node涵盖的区间的最小值
     */
    class Node{
        int left;
        int right;
        Node lchild;
        Node rchild;
        int val;
        public Node(int l, int r){
            left = l;
            right = r;
            lchild = null;
            rchild = null;
        }
    }

    public class SegmentTree{

        Node root;
        public SegmentTree(int left, int right){
            root = build(left, right);
        }
        public Node build(int left, int right){
            Node root = new Node(left, right);
            if(right - left >= 1){
                int mid = left + (right - left) / 2;
                root.lchild = build(left, mid);
                root.rchild = build(mid+1, right);
            }
            return root;
        }



    }

}
