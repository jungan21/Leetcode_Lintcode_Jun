package company.walmart;

import java.util.HashMap;

public class ArrayDegree {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            count.merge(nums[i], 1, Integer::sum);
//            if(count.containsKey(nums[i])){
//                count.put(nums[i], count.get(nums[i])+1);
//            }else{
//                count.put(nums[i],1);
//            }
        }

        for(int i=nums.length-1;i>=0;i--){
            left.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            right.put(nums[i],i);
        }
        int ans = nums.length;
        int curr_degree = 0;
        for(int i=0;i<nums.length;i++){
            int n = nums[i];
            if(count.get(n)>=curr_degree){
                int dis = (right.get(n)-left.get(n))+1;
                if(count.get(n)>curr_degree) {
                    ans = dis;
                } else if(dis<ans) {
                    ans = dis;
                }
                curr_degree = count.get(n);
            }
        }
        return ans;
    }
}
