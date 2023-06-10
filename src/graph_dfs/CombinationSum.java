package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 同一个数字可以被用unlimited times Given a set of candidate numbers (C) and a target
 * number (T), find all unique combinations in C where the candidate numbers
 * sums to T.
 * 
 * The same repeated number may be chosen from C *unlimited number* of times.
 * 
 * [7] [2, 2, 3]
 * 
 * Notice
 * 
 * All numbers (including target) will be positive integers. Elements in a
 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
 * … ≤ ak). The solution set must not contain duplicate combinations.
 *
 */
public class CombinationSum {

	public static void main(String[] args) {
		int[] nums = { 2, 2, 3 };
		int target = 7;
		System.out.println(new CombinationSum().combinationSum(nums, target));
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
		// 输出结果要求排序，同时为了去重
		Arrays.sort(candidates);
		List<Integer> list = new ArrayList<Integer>();
		// subset 问题
		DFS(candidates, target, 0, list, result);
		return result;
	}

	public void DFS(int[] candidates, int target, int pos, List<Integer> list,
			List<List<Integer>> result) {
		// 不用care list的size,只要和加在一起是taget, 就是结果要找的
		if (target == 0) {
			// 必须要new一个新的list,
			// 由于是递归,在回朔的过程中，list里的还要被其他递归层用，到时候如果list里元素改变了，result,里的list也会改变，因为是reference
			// 过来
			result.add(new ArrayList<Integer>(list));

			return;
		}
		// 这里i=pos, 而不是每次从0，开始，每次i从pos的往下找，由于数组排过序， 这样能保证结果是有序的
		for (int i = pos; i < candidates.length; i++) {
			// 这里不能省略，否则报错,stack over flow, 也可以去掉这个判断，而在 if(target==0)之前加上
			// if(target <0) return
			if (candidates[i] > target) {
				continue;
			}
			// deal with dupicate,
			// 为了去除重复元素产生重复结果的影响，因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过那层递归,
			// 可以重复用，那么重复就变得毫无意义，直接跳过即可
			// i >0, i!=0, i>pos, i!=pos都可以， 当时在CombinationSumII.java， 只能是
			// i>pos或者是 i!=pos
			if (i != pos && candidates[i] == candidates[i - 1]) {
				// 如果省略掉，结果会重复： [[2, 2, 3], [2, 2, 3], [2, 2, 3]]
				System.out.println("pos: " + pos + ", " + "i: " + i);
				continue;
			}
			list.add(candidates[i]);
			// 注意和subset, 区别， 之所以不传i+1的原因是：The same repeated number may be
			// chosen for unlimited number of times.
			DFS(candidates, target - candidates[i], i, list, result);
			list.remove(list.size() - 1);
		}
	}

	/**
	 *     # HW 候选人 答案，深入理解递归
	 *     def dfs_2(self, candidates, target, start, combination, results):
	 *         # 不能省略
	 *         if start == len(candidates):
	 *             return
	 *
	 *         if target < 0:
	 *             return
	 *
	 *         if target == 0 and combination not in results:
	 *             results.append(list(combination))
	 *             return
	 *
	 *         # 不选当前的数
	 *         self.dfs(candidates, target, start + 1, combination, results)
	 *
	 *         # 选当前的数， 下次还选当前的数
	 *         combination.append(candidates[start])
	 *         self.dfs(candidates, target - candidates[start], start, combination, results)
	 *         combination.pop()
	 *
	 *         # 选当前的数， 下次不选当前的数
	 *         combination.append(candidates[start])
	 *         self.dfs(candidates, target - candidates[start], start + 1, combination, results)
	 *         combination.pop()
	 */
}
