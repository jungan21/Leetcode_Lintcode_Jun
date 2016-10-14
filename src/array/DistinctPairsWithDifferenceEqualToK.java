package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array and a positive integer k, count all distinct pairs
 * with difference equal to k.
 * 
 * http://www.geeksforgeeks.org/count-pairs-difference-equal-k/
 * 
 */
public class DistinctPairsWithDifferenceEqualToK {

	public static void main(String[] args) {
		int[] A = { 1, 5, 3, 4, 2 };
		int k = 3;
		System.out.println(countPairsWithDiffK(A, 3));
	}

	public static List<List<Integer>> countPairsWithDiffK(int[] A, int k) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			map.put(A[i], i);
		}
		
		for (int i = 0; i < A.length; i++) {
			int diff = A[i] >= k ? A[i] - k : A[i] + k;
			if (map.containsKey(diff)) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				list.add(map.get(diff));
				result.add(list);
				map.remove(A[i]);
				map.remove(diff);
			}
		}
		return result;
	}
}
