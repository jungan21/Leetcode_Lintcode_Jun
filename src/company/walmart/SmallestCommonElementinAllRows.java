package company.walmart;


// https://leetcode.com/problems/find-smallest-common-element-in-all-rows/

import java.util.HashMap;
import java.util.Map;

/**
 * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
 * Output: 2
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 */

public class SmallestCommonElementinAllRows {
    public int smallestCommonElement(int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                map.merge(mat[i][j], 1, Integer::sum);
            }
        }

        int result = Integer.MAX_VALUE;

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            // because each row is strictly increasing ===> no duplicate in each row
            if (entry.getValue() == mat.length){
                if (entry.getKey() < result){
                    result = entry.getKey();
                }
            }
        }
        return result;

    }
}
