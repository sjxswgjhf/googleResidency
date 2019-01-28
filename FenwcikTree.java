public class FenwcikTree {


    /*
    FenwickTree通过储存部分解来实现时间的优化，运用到了lowbit的方法，
    什么是lowbit，lowbit(x) = x & -x; -x = ~x + 1
    即如果x = 6 = 0110，那么-x = ～x + 1 = 1001 + 1 = 1010, 那么lowbit = 0110 & 1010 = 0010
    然后parent跟child的关系就是根据这个lowbit来定义的，update的时候parent=child+lowbit(child)
    query的时候是child = parent - lowbit(parent)
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
}
