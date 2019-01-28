import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class minCostToHireWorker {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        /*
        So to minimize the total wage, we want a small ratio.
        So we sort all workers with their expected ratio, and pick up K first worker.
        Now we have a minimum possible ratio for K worker and we their total quality.

        As we pick up next worker with bigger ratio, we increase the ratio for whole group.
        Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
        We calculate the current ratio * total quality = total wage for this group.

        We redo the process and we can find the minimum total wage.
        Because workers are sorted by ratio of wage/quality.
        For every ratio, we find the minimum possible total quality of K workers.

        FAQ:
        Question: "However, it is possible that current worker has the highest quality, so you removed his quality in the last step, which leads to the problem that you are "using his ratio without him".
        Answer: It doesn't matter. The same group will be calculated earlier with smaller ratio.
        And it doesn't obey my logic here: For a given ratio of wage/quality, find minimum total wage of K workers.
        */
        int m = quality.length;
        double[][] workers = new double[m][2];
        for(int i = 0; i < m; i++){
            double ratio = wage[i]*1.0 / quality[i];
            workers[i][0] = ratio;
            workers[i][1] = quality[i];
        }
        Arrays.sort(workers, (a, b)->(Double.compare(a[0],b[0])));
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double res = Integer.MAX_VALUE;
        double curQuality = 0;
        for(double[] worker : workers){
            curQuality += worker[1];
            pq.add(worker[1]);
            if(pq.size() > k){
                double qua = pq.poll();
                curQuality -= qua;
            }
            if(pq.size() == k){
                res = Math.min(res, curQuality*worker[0]);
            }
        }
        return res;
    }
}
