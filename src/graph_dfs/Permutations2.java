package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * 
 * Time complexity:  O(答案个数 * 构造每个答案的时间) 也就是 O(n! * n)
 * 
 */
public class Permutations2 {

	public static void main(String[] args) {
		int nums[] = { 3, 3, 0, 3 };
		System.out.println(permuteUnique(nums));
	}

	// Method: Use set to maintain uniqueness:
	// http://www.programcreek.com/2013/02/leetcode-permutations-ii-java/
	public static List<List<Integer>> permuteUnique2(int[] num) {

		List<List<Integer>> returnList = new ArrayList<List<Integer>>();
		// permutation , you must add empty set at the begaining, for subset,
		// you must add at the end
		returnList.add(new ArrayList<Integer>());

		for (int i = 0; i < num.length; i++) {
			Set<List<Integer>> currentSet = new HashSet<List<Integer>>();
			for (List<Integer> l : returnList) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, num[i]);
					List<Integer> T = new ArrayList<Integer>(l);
					l.remove(j);
					currentSet.add(T);
				}
			}
			returnList = new ArrayList<List<Integer>>(currentSet);
		}

		return returnList;
	}

	// recursive:
	/**
	 * http://www.jiuzhang.com/solutions/permutations-ii/
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4141085.html
	 */

	public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length == 0)
			return result;
		ArrayList<Integer> list = new ArrayList<Integer>();
		int[] visited = new int[num.length];

		Arrays.sort(num);
		helper(result, list, visited, num);
		return result;
	}

	public static void helper(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] visited, int[] num) {
		if (list.size() == num.length) {
			result.add(new ArrayList<Integer>(list));
			return;
		}
		// for 循环仅仅是枚举每一位置所有可能的取值，并不是实际控制位置的
		for (int i = 0; i < num.length; i++) {
			// http://www.jiuzhang.com/solutions/permutations-ii/
			/**
			 * below if condition can't be ignored
			 * 
			 * http://blog.csdn.net/linhuanmars/article/details/21570835
			 */
			if (visited[i] == 1) {
				continue;
				/**
				 * visited[i - 1] == 0 OR visited[i - 1] != 0 都行，但是visited[i -
				 * 1] == 0更方便理解程序的运行
				 * 
				 * 以{ 1, 3, 3 }数组为例，下标分别为， 0， 1， 2， 在第一次加入下标为2的那个 ​3
				 * 时，相对应的visited[1]为true(对应下标为1的那个3，已经标志为访问过了)，而在递归回来，
				 * （即处理完remove 和set visited[i]=0后），第二次加入下标为2的那个3​​
				 * 时，相对应的visited[1]为false。
				 * 
				 * 一句话总结即为：在遇到当前元素和前一个元素相等时，如果前一个元素visited[i - 1] == false,
				 * 那么我们就跳过当前元素并进入下一次循环
				 * ，这就是剪枝的关键所在。另一点需要特别注意的是这种剪枝的方法能使用的前提是提供的nums是有序数组，否则无效。
				 * 
				 */
			} else if (i != 0 && num[i] == num[i - 1] && visited[i - 1] == 0) {
				// System.out.println("i: " + i);
				continue;
			}

			visited[i] = 1;
			list.add(num[i]);
			helper(result, list, visited, num);
			list.remove(list.size() - 1);
			visited[i] = 0;
		}
	}

	// https://www.youtube.com/watch?v=Ws1NFUvA42s, we can also write this way
	public static void helper1(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] visited, int[] num) {
		if (list.size() == num.length) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < num.length; i++) {
			if (visited[i] == 1) {
				continue;
			}

			visited[i] = 1;
			list.add(num[i]);
			helper(result, list, visited, num);
			list.remove(list.size() - 1);
			visited[i] = 0;
			// remove duplicate
			while (i < num.length - 1 && num[i] == num[i + 1]) {
				i++;
			}
		}
	}

	/**
	 * Jun, latest, 与上面的差别是DFS加了个pos参数，其实没必要， 如体面方法，可以用list.size()来控制
	 */
	public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (nums == null || nums.size() == 0) {
			return result;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		Collections.sort(nums);
		int[] visited = new int[nums.size()];
		helper(nums, 0, list, result, visited);
		return result;
	}

	public void helper(ArrayList<Integer> nums, int pos,
			ArrayList<Integer> list, ArrayList<ArrayList<Integer>> result,
			int[] visited) {

		if (pos == nums.size()) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.size(); i++) {
			if (visited[i] == 1) {
				continue;
			}

			if (i != 0 && nums.get(i) == nums.get(i - 1) && visited[i - 1] == 0) {
				continue;
			}

			visited[i] = 1;
			list.add(nums.get(i));
			helper(nums, pos + 1, list, result, visited);
			list.remove(list.size() - 1);
			visited[i] = 0;
		}
	}
}
