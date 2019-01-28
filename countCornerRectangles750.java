public class countCornerRectangles750 {


    /*
    O((m*n)^2)
    这个就是在一个是1的点遍历往下找到1，然后从改点再往左找有没有1，然后再找到1之后看对应的下方那个点是不是1，这里可以直接用
    直接的左边的长度来比较
    */

    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                //go down
                for(int p = i + 1; p < m; p++){
                    if(grid[p][j] == 0){
                        continue;
                    }
                    //go left
                    for(int q = j + 1; q < n; q++){
                        if(grid[i][q]==0)
                            continue;
                        if(grid[i][q] == 1){

                            if(grid[p][q] == 1){
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /*
    O(m^2*n):
    这个就是用到了一个思想，当前array有k个1，那么有多少种组合，k*(k-1)
    通过这个思想我们只要遍历一次col就可以，只要符合两条row里在同一个col的时候有1就好，然后计算数量，用k*(k-1)来算当前两个row的
    rectangle个数，然后累积
     */

    public int countCornerRectangles2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int p = i+1; p < m; p++){
                int c = 0;
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == 1 && grid[p][j] == 1){
                        c++;
                    }
                }
                res += (c*(c-1))/2;
            }
        }
        return res;
    }
}
