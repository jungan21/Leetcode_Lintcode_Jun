package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 无重复数字的set Given a set of distinct integers, return all possible
 * subsets.
 * 
 * Notice
 * 
 * 1. Elements in a subset must be in non-descending order. 2.The solution set
 * must not contain duplicate subsets.
 * 
 * 
 * Best Explanation:
 * http://www.code123.cc/docs/leetcode-notes/backtracking/subsets.html
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 对的，我记得老师课上有个经典的计算复杂度的公式 O(构造解的复杂度 * 解的个数)，我觉得这样分析会更加直观一些。 比如subset，解的个数是2^n,
 * 每次构造出一个解列出n个数，所以构造解的复杂度是n，所以时间复杂度就是O(n * 2^n)
 * 空间复杂度就是和解的个数是一致的。应该和你的分析是一样的。空间复杂度：应该跟调用递归次数一样O(2^n)
 *
 */
public class Subsets {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(subsets2(nums));
	}

	/**
	 * non-recursive http://www.programcreek.com/2013/01/leetcode-subsets-java/
	 * 
	 * 该方法出来的结果，即，result里的每个list，不一定是排序的，Leetcode里提交可以过
	 * 
	 * 不过lintcode里要求是每个list要排好序,w
	 * 为了满足lintcode要求，只需要，在最开始对输入排序就好，Arrays.sort(nums);
	 * 
	 */
	//
	public static List<List<Integer>> subsets(int[] nums) {
		if (nums == null)
			return null;
		// no need for sort, without below sentence, you can sitll submit to
		// leetcode
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> temp = new ArrayList<List<Integer>>();

			// get sets that are already in result

			/**
			 * Note: you can't use "temp.addAll(result)", if you do so, all
			 * sublist within result will be copied to temp, then in the next
			 * step, when you modify the sublist of temp, you will also make
			 * changes to sublist of original result. Finally the result is
			 * different from what we want
			 */

			// each iteratin, make a copy of previous result, and work on the
			// copy, Do NOT change the previous result
			for (List<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}

			// add nums[i] to existing sets
			for (List<Integer> a : temp) {
				a.add(nums[i]);
			}

			/**
			 * single.add[nums[i]] return boolean, so you can NOT simply do
			 * temp.add(new ArrayList<Integer>().add[nums[i]]);
			 */

			// add nums[i] only as a set
			// list.add(num[i]) method return a boolean, so you must
			List<Integer> single = new ArrayList<Integer>();
			single.add(nums[i]);
			temp.add(single);

			result.addAll(temp);
		}

		// empty 必须最后加，否则结果不对
		result.add(new ArrayList<Integer>());

		return result;
	}

	/**
	 * jiuzhang 2: recursive
	 * 
	 * Best
	 * 解释：http://www.code123.cc/docs/leetcode-notes/backtracking/subsets.html
	 * 
	 * best explanation: http://blog.csdn.net/u011095253/article/details/9158397
	 * 
	 * http://www.jiuzhang.com/solutions/subsets/
	 * 
	 * 1. 结果要求生成升序排列，所以最开始的时候我们需要Sort一下
	 * 
	 * 2. 往res里面添加的时候要 new ArrayList(tmp);
	 * 
	 * 3. 别忘了空集也是子集
	 */
	public static ArrayList<ArrayList<Integer>> subsets2(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0) {
			return result;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		Arrays.sort(num); //如果结果要求排序
		// 0 means index of the array, i.e position
		DFS(result, list, num, 0);

		return result;
	}

	private static void DFS(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] num, int pos) {

		result.add(new ArrayList<Integer>(list));
		/**
		 * 简单理解就是以每个元素为起点，做DFS，为了方便理解假设输入为{1, 2},逐步debug,并参考下面的文章理解
		 * 
		 * 这里要注意的是递归调用完后，由于for循环的最后又i++, 所以要把握好i的当前值
		 * 
		 * http://www.code123.cc/docs/leetcode-notes/backtracking/subsets.html
		 * 
		 * http://www.cnblogs.com/TenosDoIt/p/3451902.html
		 * 
		 * 解释的很好
		 * https://soulmachine.gitbooks.io/algorithm-essentials/content/java
		 * /brute-force/subsets.html
		 */
		// 这里for循环依然仅仅是表示某一位置所有可能的取值范围， 当某位取值了，i+1表示下一位取值的范围缩小了
		for (int i = pos; i < num.length; i++) {

			list.add(num[i]);
			/**
			 * 注意，这里是i+1, 而不是pos + 1;
			 * 因为这里i是变量，表示i从第pos个位置开始，然后往前递归，subset递归是从当前元素往后看
			 * ，而permutation是每次从0位置开始排列整个数组
			 */
			// 第i个位置摆好了，接下来轮到i+1的位置，即下一个位置
			DFS(result, list, num, i + 1);
			// 譬如[1, 2, 3,] DFS了一遍，以1开头的subset都找到了，接下来找以2开头的
			list.remove(list.size() - 1);
		}
	}

	/**
	 * method 2: 思路就是套用Combination.java的方法，只需要在combination的外面加个循环即可。
	 * 
	 * http://www.cnblogs.com/springfor/p/3879830.html
	 * 
	 * 例如k=3，n=1，用combination那道题的方法求得集合是[[1], [2], [3]]；
	 * 
	 * k=3, n=2,用combination那道题的方法求得集合是[[1, 2], [1, 3], [2, 3]]
	 * 
	 * k=3, n=3,用combination那道题的方法求得集合是[[1,2,3]]
	 */

	public static ArrayList<ArrayList<Integer>> subsets1(int[] S) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (S.length == 0 || S == null)
			return res;

		Arrays.sort(S);
		// len从0，开始，因为空集[],也是结果
		for (int len = 0; len <= S.length; len++)
			dfs(S, 0, len, item, res);

		res.add(new ArrayList<Integer>());

		return res;
	}

	public static void dfs(int[] S, int start, int len,
			ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
		if (item.size() == len) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		for (int i = start; i < S.length; i++) {
			item.add(S[i]);
			dfs(S, i + 1, len, item, res);
			item.remove(item.size() - 1);
		}

	}

	/**
	 * another version of DFS
	 * 
	 * http://www.cnblogs.com/springfor/p/3879830.html
	 */

	public static ArrayList<ArrayList<Integer>> subsetsDFS(int[] S) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		if (S.length == 0 || S == null)
			return res;

		Arrays.sort(S);
		dfs(S, 0, item, res);
		// add empty set first
		res.add(new ArrayList<Integer>());

		return res;
	}

	public static void dfs(int[] S, int start, ArrayList<Integer> item,
			ArrayList<ArrayList<Integer>> res) {
		for (int i = start; i < S.length; i++) {
			item.add(S[i]);
			res.add(new ArrayList<Integer>(item));
			dfs(S, i + 1, item, res);
			item.remove(item.size() - 1);
		}

	}

}
