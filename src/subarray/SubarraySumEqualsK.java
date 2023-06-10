package subarray;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {


    // Leetcode 560 https://leetcode.com/problems/subarray-sum-equals-k/
    // Solution: https://www.youtube.com/watch?v=AmlVSNBHzJg
    public int subarraySumEqualsK_ReturnTotalNumber_JiuzhangVersion(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>(); // Key: sum , Value: 该sum出现的次数
        map.put(0, 1);  // sum为0， 出现1次
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum  += nums[i];
            // map.containsKey(sum - k) 表示从之前某个点，到现在的点之间出现过和为K的subarray
            if (map.containsKey(sum - k)){
                result += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);

        }
        return result;
    }

    // 推荐！Lintcode 911  https://us.jiuzhang.com/problems/info/911
    // 用hashmap维护前缀和对应的最大终点位置，然后枚举子数组开始位置，在hashmap中进行查找
    // Solution: https://www.cnblogs.com/foldn/p/15969873.html
    public int longestSubarraySumEqualsK_ReturnLength_JiuzhangVersion(int[] nums, int k) {
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>(); // Key: sum , Value: 该sum出现时候i的下标值
        map.put(0, -1);// Note:  必须设置为（0， -1）否则计算结果不对，为了处理前面加起来和我0的情况，即，当subarry从下标为0开始
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum  += nums[i];
            if (map.containsKey(sum - k)){
                maxLen = Math.max(maxLen, i - map.get(sum-k));
            }
            //出现过就不用存入了，以先出现sum时候的下标为准，这样计算的长度会更长一点
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    // 不推荐！ Leetcode 560  https://leetcode.com/problems/subarray-sum-equals-k/

    public int longestSubarraySumEqualsK_ReturnLength_JunVersion(int[] nums, int k) {
        int maxLength = 0;
        int j = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            while (j < nums.length){
                sum += nums[j];
                j++;
                if (sum == k){
                    maxLength = Math.max(maxLength, j - i);
                    // 滑动左边窗口
                    sum = sum - nums[i];
                    break;
                }
            }
        }
        return maxLength;
    }


}
