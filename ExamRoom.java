import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ExamRoom {

    int N;
    PriorityQueue<Interval> pq;

    class Interval{
        int x;
        int y;
        int gap;
        public Interval(int x, int y){
            this.x = x;
            this.y = y;
            if(x == -1){
                gap = y;
            }
            else if(y == N-1){
                gap = N - 1 - x;
            }
            else{
                gap = Math.abs(y - x) / 2;
            }
        }
    }

    public ExamRoom(int N){
        this.N = N;
        Interval dummy = new Interval(-1, N);
        pq = new PriorityQueue<>((a,b) -> a.gap == b.gap ? a.x-b.x : b.gap - a.gap);
        pq.add(dummy);
    }

    public int seat(){
        int seat = -1;
        Interval interval = pq.poll();
        if(interval.x == -1){
            seat = 0;
        }
        else if(interval.y == N){
            seat = N - 1;
        }else{
            seat = (interval.x + interval.y) / 2;
        }
        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));
        return seat;
    }

    public void leave(int p){
        Interval head = null, tail = null;
        List<Interval> list = new ArrayList<>(pq);
        for(Interval cur : list){
            if(cur.x == p){
                tail = cur;
            }
            if(cur.y == p){
                head = cur;
            }
            if(head != null && tail !=null){
                break;
            }
        }
        pq.remove(tail);
        pq.remove(head);
        pq.offer(new Interval(head.x, tail.y));
    }
}
