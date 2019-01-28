import java.util.Arrays;

public class minMeetingRooms253 {

      public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
    /*
    扫描线算法：如果下一个房间的开始时间小于前一个的结束时间那么意味着我们需要更多的房间，如果当前的start大于end了，那么我们就需要
    更新end来看是不是能放进第二个放进
    即starts[i] < end[e]: room++; else e++;
     */


    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        int m = intervals.length;
        int rooms = 0;
        int[] starts = new int[m];
        int[] ends = new int[m];
        for(int i=0; i < m; i++){
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int e =0;
        for(int i = 0; i < m; i++){
            if(starts[i] < ends[e]){
                rooms++;
            }else{
                e++;
            }
        }
        return rooms;
    }
}
