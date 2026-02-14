package company.robinhoods;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntervalsInsert {

    // input intervals already sorted by start
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();


        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];

            if (newInterval[1] < cur[0]){
                result.add(newInterval);
                newInterval = cur;

            } else if (newInterval[0] > cur[1]){
                result.add(cur);
            } else {
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
             }
        }

        result.add(newInterval);
        return result.toArray(new int[0][0]);
        //return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};


        IntervalsInsert system = new IntervalsInsert();
        int[][] results = system.insert(intervals, newInterval);

        for (int[] interval : results) {
            System.out.println("[" + interval[0] + "," + interval[1] + "]");
        }

    }
}
