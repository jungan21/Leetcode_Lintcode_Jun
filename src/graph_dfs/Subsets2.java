package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets. *
 */
public class Subsets2 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 2 };
		System.out.println(subsets(nums));
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/subsets-ii/
	 * 
	 * 其他两种去重的方法： http://www.cnblogs.com/springfor/p/3879853.html
	 * 在把list添加result时候,
	 * 判断是否result中已经存过该list了。(!result.contains(list))没存过的才存保证子集唯一性。
	 * 
	 * http://www.cnblogs.com/grandyang/p/4310964.html
	 * 
	 */

	public static ArrayList<ArrayList<Integer>> subsets(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (num == null || num.length == 0) {
			return result;
		}
		Arrays.sort(num);
		subsetsHelper(result, list, num, 0);

		return result;
	}

	private static void subsetsHelper(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int[] num, int pos) {

		result.add(new ArrayList<Integer>(list));

		/**
		 * for 循环表示枚举可能的取值，pos =
		 * 0,的时候表示可能的取值是是数组里所有的元素，pos=1,的时候，表示可取的元素少了一个，因为只能往下去
		 * ，也就是可取的元素是从1的位置到最后
		 */
		for (int i = pos; i < num.length; i++) {

			// if condition means, 只要不是该轮循环起点， index i == index i-1
			// i > pos 或 i!=pos都可以充当条件， i == pos时候，不用考虑去重， 譬如[1,2,2] pos
			// =2, i=2时，（即填充第二个pos位置的时候）， 取的是第二个2（因为i==2），所以没关系
			/**
			 * 能代表「重复元素的第一个」即为 for 循环中的pos变量，i ==
			 * pos时，i处所代表的变量即为某一层遍历中得「第一个元素」，因此去重时只需判断i != pos && s[i] == s[i -
			 * 1].
			 * 
			 * Subsets1里面没有重复元素，所有不用如下的if condition考虑
			 */
			if (i != pos && num[i] == num[i - 1]) {
				//画出树状递归图，以及打印出i, pos的值，就懂了System.out.println("pos: " + pos + ", " + "i: " + i);
				continue;
			}

			list.add(num[i]);
			subsetsHelper(result, list, num, i + 1);
			list.remove(list.size() - 1);
			/**
			 * 上面的那个if, continue can be replaced by below condition, but you
			 * must put while loop at the end,因为，上面三句叫一个完整的DFS， 下一次DFS，才去重
			 * 对于[1,2,2]以i =0,DFS完后，i变为1，num[1]=num[2],所以i++,然后用第二个2再做DFS
			 */
			// DFS过程中 当有重复元素出现就只对当前这个元素走一起，其他重复元素跳过
			// while (i < num.length - 1 && num[i] == num[i + 1]) {
			// i++;
			// }
		}
	}
	
	
	
	
	
	
	

	// non-recursive
	// http://www.programcreek.com/2013/01/leetcode-subsets-java/
	public static List<List<Integer>> subsets1(int[] num) {
		if (num == null)
			return null;

		// you must sort it, so that we can ignore duplicate
		Arrays.sort(num);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<List<Integer>> prev = new ArrayList<List<Integer>>();

		for (int i = 0; i < num.length; i++) {
			// Note: without prev.size() == 0 condition, it also works with
			// Leetcode
			// get existing sets
			// if num[i] == num [i-1], we can NOT createa new prev and copy all
			// elements from result, because, num[i-1] element, already did
			// combination with num[i-2], then we would have duplicate list
			if (i == 0 || num[i] != num[i - 1] || prev.size() == 0) {
				prev = new ArrayList<List<Integer>>();
				for (List<Integer> a : result) {
					prev.add(new ArrayList<Integer>(a));
				}
			}

			// add current number to each element of the set
			for (List<Integer> b : prev) {
				b.add(num[i]);
			}

			// add each single number as a set, only if current element is
			// different with previous
			if (i == 0 || num[i] != num[i - 1]) {
				ArrayList<Integer> single = new ArrayList<Integer>();
				single.add(num[i]);
				prev.add(single);
			}

			// add all set created in this iteration
			// here we can NOT do result.addAll(prev), because, perv is NOT
			// re-intilized every iteration
			for (List<Integer> list : prev) {
				result.add(new ArrayList<Integer>(list));
			}
		}

		// add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}

}
