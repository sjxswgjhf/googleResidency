import java.util.*;

public class bicycleMatch {

    /*
    time complexity: O(m*n + L*W)
     */

    static class Pair{
        int dis;
        int pe;
        int bi;
        public Pair(int d, int p, int b){
            dis = d;
            pe = p;
            bi = b;
        }
    }

    public static List<int[]> matchBicycleHeap(char[][] board){

        List<int[]> people = new ArrayList<>();
        List<int[]> bicycle = new ArrayList<>();
        List<int[]> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'P'){
                    int[] cur = new int[2];
                    cur[0] = i;
                    cur[1] = j;
                    people.add(cur);
                }
                if(board[i][j] == 'B'){
                    int[] cur = new int[2];
                    cur[0] = i;
                    cur[1] = j;
                    bicycle.add(cur);
                }
            }
        }
        int count = people.size();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        for(int i = 0; i < people.size(); i++){
            for(int j = 0; j < bicycle.size(); j++){
                int pr = people.get(i)[0];
                int pc = people.get(i)[1];
                int br = bicycle.get(j)[0];
                int bc = bicycle.get(j)[1];
                int dis = Math.abs(pr - br) + Math.abs(pc-bc);
                Pair pair = new Pair(dis, i, j);
                pq.offer(pair);
            }
        }
        HashSet<Integer> person = new HashSet<>();
        HashSet<Integer> bike = new HashSet<>();
        while(!pq.isEmpty()){

            Pair cur = pq.poll();
            int curP = cur.pe;
            int curB = cur.bi;
            if(person.contains(curP) || bike.contains(curB)){
                continue;
            }
            int[] curPair = new int[2];
            curPair[0] = curP+1;
            curPair[1] = curB+1;
            person.add(curP);
            bike.add(curB);
            res.add(curPair);
            if(res.size() == count){
                break;
            }
        }
        return res;
    }













    /*
    BFS approach: O(m*board.l*board.w)
     */
    static boolean[][] used;
    public static List<int[]> matchBicycle(char[][] board, List<int[]> people, List<int[]> bicycle) {
        int m = board.length;
        int n = board[0].length;
        List<int[]> res = new ArrayList<>();
        used = new boolean[m][n];
        int[][] dirs = { {0,1},{0,-1},{1,0},{-1,0}};
        for (int[] person : people) {
            int r = person[0];
            int c = person[1];
            int[] bike = new int[2];
            boolean[][] visited = new boolean[m][n];
            bike = bfs(board, r, c, visited, m, n, used,dirs);
            res.add(bike);
        }
        return res;
    }


    private static int[] bfs(char[][] board, int r, int c, boolean[][] visited, int m, int n, boolean[][] used,int[][] dirs){
        Queue<int[]> q = new LinkedList<>();
        int[] res = new int[2];
        int[] cur = {r, c};
        q.add(cur);
        visited[r][c] = true;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int[] tmp = q.poll();
                int curR = tmp[0];
                int curC = tmp[1];
                for(int i = 0; i < 4; i++){
                    int nr = dirs[i][0] + curR;
                    int nc = dirs[i][1] + curC;
                    if(nr < 0 || nr == m || nc < 0 || nc == n || visited[nr][nc] == true || used[nr][nc] == true){
                        continue;
                    }
                    if(board[nr][nc] == 'P'){
                        continue;
                    }
                    if(board[nr][nc] == 'B' && used[nr][nc] == false){
                        res[0] = nr;
                        res[1] = nc;
                        used[nr][nc] = true;
                        return res;
                    }
                    if(board[nr][nc] == 'O'){
                        q.offer(new int[]{nr,nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] board = {{'O','P','O','B','O','O','P'}, {'O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O'},
                {'O','O','O','O','O','O','O'},{'B','O','O','B','O','O','B'}};
        List<int[]> p = new ArrayList<>();
        int[] p1 = {0,1};
        int[] p2 = {0,6};
        int[] b1 = {0,3};
        int[] b2 = {4,0};
        int[] b3 = {4,3};
        int[] b4 = {4,6};
        p.add(p1);
        p.add(p2);
        List<int[]> b = new ArrayList<>();
        b.add(b1);
        b.add(b2);
        b.add(b3);
        b.add(b4);
//        List<int[]> res = matchBicycle(board, p, b);
        List<int[]> res = matchBicycleHeap(board);
        for(int[] l : res){
            System.out.println(l[0] + " "+l[1]);
        }
    }
}

/*
OPOBOOP
OOOOOOO
OOOOOOO
OOOOOOO
BOOBOOB
 */




