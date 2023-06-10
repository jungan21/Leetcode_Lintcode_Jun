package subarray;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个整数数组，请找出一个连续子数组，使得该子数组的和最大。输出答案时，请分别返回第一个数字和最后一个数字的下标。（如果存在多个答案，请返回字典序最小的）
 * Lintcode 402: https://www.lintcode.com/problem/402/
 */
public class MaximumSubarrayPrintIndex {
    public List<Integer> continuousSubarraySum(int[] A) {
        List<Integer> result = new ArrayList<Integer>();
        // Note: must have result.add(0); otherwise result.set(0, start); throw IndexOutOfBoundsException
        result.add(0);
        result.add(0);
        if (A == null || A.length == 0) {
            return result;
        }

        int globalMax = A[0];
        int localMax = A[0];
        //Note: logic is exactly same as MaximumSubarrayPrint.java just need use start, end points to track the index;
        int start  = 0, end = 0;
        for (int i = 1 ; i < A.length; i++){
            // MaximumSubarrayPrint.java 里 localMax = Math.max(A[i], localMax + A[i]);
            if (localMax + A[i] > A[i]){
                localMax = localMax + A[i];
                end = i;
            } else {
                localMax = A[i];
                start = end = i;
            }
            // MaximumSubarrayPrint.java 里 globalMax = Math.max(globalMax, localMax);
            if (localMax > globalMax){
                globalMax = localMax;
                result.set(0, start);
                result.set(1, end);
            }
        }
        return result;

    }
}
