package company.robinhoods;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class IntervalsMerge {


    public int[][] merge(int[][] intervals) {
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

//
//    public ArrayList<Interval> mergeintervalsList(ArrayList<Interval> intervals) {
//        if (intervals == null || intervals.size() <= 1) {
//            return intervals;
//        }
//        // 必须排序， 否则结果不对
//        Collections.sort(intervals, new Comparator<Interval>() {
//            // compare 方法的return值得类型必须是int, 不能写成Integer
//            public int compare(Interval a, Interval b) {
//                return a.start - b.start;
//            }
//        });
//
//        ArrayList<Interval> result = new ArrayList<Interval>();
//        Interval pre = intervals.get(0);
//        for (int i = 1; i < intervals.size(); i++) {
//            Interval curt = intervals.get(i);
//            // <= 符号
//            if (curt.start <= pre.end) {
//                pre.end = Math.max(pre.end, curt.end);
//            } else {
//                result.add(pre);
//                pre = curt;
//            }
//        }
//        result.add(pre);
//        return result;
//    }


//    public List<Interval> merge(List<Interval> intervals) {
//        List<Interval> result = new ArrayList<>();
//        if (intervals == null || intervals.size() == 0) {
//            return result;
//        }
//        Collections.sort(intervals, new Comparator<Interval>() {

//            public int compare(Interval a, Interval b) {
//                if (a.start == b.start) {
//                    return a.end - b.end;
//                } else {
//                    return a.start - b.start;
//                }
//            }
//        });
//
//        Interval pre = intervals.get(0);
//        result.add(pre);
//        for (int i = 1; i < intervals.size(); i++) {
//            Interval cur = intervals.get(i);
//            pre = result.get(result.size() - 1);
//            // 下面注释里写法也对，不过java是reference传递，如果改变了pre的end属性，在result里的pre也就自动跟着一起改了
//            if (cur.start <= pre.end) {
//                // Interval newInterval = new Interval(pre.start,
//                // Math.max(pre.end, cur.end));
//                // result.remove(pre);
//                // result.add(newInterval);
//                pre.end = Math.max(pre.end, cur.end);
//            } else {
//                result.add(cur);
//            }
//        }
//        return result;
//    }
//
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        IntervalsMerge system = new IntervalsMerge();
        int[][] results = system.merge(intervals);

         for (int[] interval : results){
             System.out.println("[" + interval[0] + "," + interval[1] + "]");
         }

    }
}
