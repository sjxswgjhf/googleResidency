public class count {


    public static int count(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < n; i++){
            if(grid[0][i] == 1 && visited[0][i] == false){
                int area = dfs(grid, visited,0, i, m, n, 0);
                count += area;
            }
        }
        return count;
    }
    static int[][] dirs = {{1,0}, {0,1},{-1,0},{0,-1}};

    private static int dfs(int[][] grid, boolean[][] visited, int r, int c, int m, int n, int cur){
        if(r < 0 || r>=m || c < 0 || c>=n || visited[r][c] == true || grid[r][c] == 0){
            return cur;
        }
        cur++;
        visited[r][c] = true;
        for(int i = 0; i < 4; i++){
            cur = dfs(grid,visited, r+dirs[i][0],c+dirs[i][1],m,n,cur);
        }
        return cur;
    }


    public static void main(String[] args) {
        int[][] grid = {{1,1,1,0},{1,0,1,0}};
        System.out.print(count(grid));
    }
}
