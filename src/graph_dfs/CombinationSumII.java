package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Notice
 * 
 * All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
 * … ≤ ak). The solution set must not contain duplicate combinations
 */
public class CombinationSumII {

	public static void main(String[] args) {
		// int[] nums = { 7, 1, 2, 5, 1, 6, 10 };
		int[] nums = { 7, 1, 2, 5, 1, 6, 10 };
		// int[] nums = { 2, 2, 3 };
		int target = 8;
		System.out.println(new CombinationSumII().combinationSum(nums, target));
	}

	/**
	 * NP问题 算法复杂度因为是NP问题，所以自然是指数量级的
	 * 
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0) {
			return result;
		}
		Arrays.sort(candidates);
		List<Integer> list = new ArrayList<Integer>();
		DFS(candidates, target, 0, list, result);
		return result;
	}

	public void DFS(int[] candidates, int target, int pos, List<Integer> list,
			List<List<Integer>> result) {

		if (target == 0) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		/**
		 * 其本质就是从每个position, 开始，往下枚举排列， 把数放到相应的position上去
		 */
		for (int i = pos; i < candidates.length; i++) {
			if (candidates[i] > target) {
				continue;
			}
			/**
			 * [1,1,5,6], 因为虽然一个元素不可以重复使用，但是如果这个元素重复出现是允许的，但是为了避免出现重复的结果集，
			 * 我们只对于第一次得到这个数进行递归, 譬如[1,1,2]我们只以第一次出现的1 为开始进行递归调用
			 * ，接下来就跳过这个元素了，因为接下来的情况会在上一层的递归函数被考虑到，这样就可以避免重复元素的出现
			 * 
			 * 因为 排序了，所有可以用candidates[i] == candidates[i - 1]判断重复
			 */
			// 只能是 i>pos或者是 i!=pos,
			// 不能用0来判断，注意和CombinationSum.java区别,因为pos就是没个位置第一次得到启示的位置
			// 本质就是subset2.java, i == pos时候没关系，譬如[1,1,2,6,7] k=8, [1,1,6],
			// pos=1时候，i=1，时候，取得是第二个1所有符合题意
			// 另外一种去除重复的方式： http://www.cnblogs.com/springfor/p/3886057.html
			if (i != pos && candidates[i] == candidates[i - 1]) {
				// System.out.println("pos: " + pos + ", " + "i: " + i);
				continue;
			}
			list.add(candidates[i]);
			// 单个元素用过就不可以重复使用了,所有传进去i+1
			DFS(candidates, target - candidates[i], i + 1, list, result);
			list.remove(list.size() - 1);
		}
	}

	public void DFSJun(int[] candidates, int target, int pos,
			List<Integer> list, List<List<Integer>> result) {

		if (target == 0) {
			// 去除重复的集合， 能通过Lintcode
			if (!result.contains(list)) {
				result.add(new ArrayList<Integer>(list));
			}
			return;
		}

		for (int i = pos; i < candidates.length; i++) {
			if (candidates[i] > target) {
				continue;
			}
			list.add(candidates[i]);
			DFS(candidates, target - candidates[i], i + 1, list, result);
			list.remove(list.size() - 1);
		}
	}

}
