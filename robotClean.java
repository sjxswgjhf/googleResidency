import java.util.HashSet;

public class robotClean {

    interface Robot{
        boolean move();
        void turnLeft();
        void turnRight();
        void clean();
    }

    /*
    这题要注意避免重复计算用set来保存路径Str，然后当robot不能move的时候我们要退回前一格，因为只能call interface，所以
    我们只能转180度，再往前走，然后再转180度（需要保持之前的朝向所以要转回去），然后走一下个方向（这里turnleft或者turnright都可以）
    然后还要注意的是dirs必须是一个circle来保证能回到原位
     */
    int[][] dirs = {{-1,0}, {0, 1}, {1, 0}, {0, -1}};
    public void cleanRoom(Robot robot){
        HashSet<String> visited = new HashSet<>();
        backtrack(robot, visited, 0, 0, 0);
    }

    private void backtrack(Robot robot, HashSet<String> visited, int r, int c, int dir){
        String path = r+"-"+c;
        visited.add(path);
        robot.clean();
        for(int i = 0; i < 4; i++){
            int curDir = (dir+i)%4;
            int nr = dirs[curDir][0];
            int nc = dirs[curDir][1];
            String nextPath = nr+"-"+nc;
            if(!visited.contains(nextPath) && robot.move()){
                backtrack(robot,visited,nr,nc,curDir);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }
}
