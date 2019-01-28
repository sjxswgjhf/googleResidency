public class chessGo {

    static int[][] dirs = {{-1,0}, {0, 1}, {1,0},{0,-1}};

    private static boolean isSurvived(int[][] board){

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 1 && visited[i][j] == false){
                    if(dfs(board, visited, i, j, m, n)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(int[][] board, boolean[][] visited, int r, int c, int m, int n){
        if(board[r][c] == 0){
            return true;
        }
        if(board[r][c] == 2){
            return false;
        }
        visited[r][c] = true;
        for(int i = 0; i < 4; i++){
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && visited[nr][nc] == false){
                boolean cur = dfs(board, visited, nr, nc, m, n);
                if(cur == true){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = {{2,2,2,0},{2,1,2,0},{2,1,1,0},{2,2,2,2}};
        System.out.println(isSurvived(board));
    }
}
