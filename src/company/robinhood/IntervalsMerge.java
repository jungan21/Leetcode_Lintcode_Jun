package company.robinhoods;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class IntervalsMerge {

    // 这种写法与Insert Interval代码一模一样了，这里假设intervals[0] 为待insert的interval
    public int[][] mergeJun(int[][] intervals) {
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, (a, b) -> {   // 必须先排序
            return a[0] - b[0];
        });
        List<int[]> result = new LinkedList<>();
        int[] newInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];

            if(newInterval[1] < cur[0]){
                result.add(newInterval);
                newInterval = cur;
            } else if( newInterval[0] > cur[1]){
                result.add(cur);
            } else {
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
            }
        }
        result.add(newInterval);
        return result.toArray(new int[0][0]); // Important!!!
    }
    
    public int[][] mergeMethod2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new LinkedList<>();

        int[] pre = intervals[0];
        result.add(pre);

        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            pre = result.get(result.size()-1);
            if (cur[0] > pre[1]){
                result.add(cur);
            } else {
                pre[1] = Math.max(pre[1], cur[1]);
            }
        }
        return result.toArray(new int[0][0]);
        //return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        IntervalsMerge system = new IntervalsMerge();
        int[][] results = system.mergeJun(intervals);

         for (int[] interval : results){
             System.out.println("[" + interval[0] + "," + interval[1] + "]");
         }

    }
}
