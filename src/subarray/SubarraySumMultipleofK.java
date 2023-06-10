package subarray;


import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode: 523 https://leetcode.com/problems/continuous-subarray-sum/
 */
public class SubarraySumMultipleofK {

    /**
     * solution: https://hsiyinl.medium.com/continuous-subarray-sum-leetcode-523-1813f9436d7a
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // prefix sum
        int[] leftSums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            leftSums[i] = sum;
        }

        Map<Integer, Integer> modsToIndices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int mod = leftSums[i] % k;

            // any two prefix sums that are not next to each other with the same mod k or a prefix sum with mod k = 0
            // that is not the first number will yield a valid subarray.
            if (mod == 0 && i != 0) {
                return true;
            }

            if (modsToIndices.containsKey(mod)){
                if (i - modsToIndices.get(mod) > 1){
                    return true;
                }
            } else {
                modsToIndices.put(mod, i);
            }
        }
        return false;
    }
}
