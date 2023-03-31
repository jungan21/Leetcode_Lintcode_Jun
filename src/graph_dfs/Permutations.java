package graph_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * 对于Permutation的话，虽然有for循环，但不是每次都执行n次，所以时间复杂度应该是O(n!)；而因为储存时有n!种排列，而每种排列记录的是n个数
 * ，所以空间复杂度应该是O(n*n!) ==> O(n!)
 * 
 * Time complexity:  O(答案个数 * 构造每个答案的时间) 也就是 O(n! * n)
 * 
 */
public class Permutations {

	public static void main(String[] args) {
		int nums[] = { 1, 2 };
		System.out.println(permute2(nums));
	}

	// http://www.programcreek.com/2013/02/leetcode-permutations-java/
	public static List<List<Integer>> permute(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// start from an empty list, you can't ignore it
		result.add(new ArrayList<Integer>());
		for (int i = 0; i < num.length; i++) {
			// list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
			for (List<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size() + 1; j++) {
					// + add num[i] to different locations (i.e. j)
					l.add(j, num[i]);

					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);

					// System.out.println(temp);
					// - remove newly added num[i], so that next iteration, we
					// will try put num[i]int different position
					l.remove(j);
				}
				System.out.println(current);
			}
			result = new ArrayList<List<Integer>>(current);
			// System.out.println(i + "result: " + result);
		}

		return result;
	}

	// http://www.jiuzhang.com/solutions/permutations/
	// time complexility O(n!)
	public static List<List<Integer>> permute1(int[] num) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		if (num == null || num.length == 0) {
			return rst;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		helper1(rst, list, num);
		return rst;
	}
	//树的每一层就是递归的那一层 树上每个分支就是for循环中的每个选择
	public static void helper1(List<List<Integer>> rst,
			ArrayList<Integer> list, int[] num) {

		// 下面这个if 条件快，是最后一层递归才会运行的
		if (list.size() == num.length) {
			rst.add(new ArrayList<Integer>(list));
			/**
			 * Note: this return is NOT then recursive ending point, if you
			 * remove this "return", it also works 递归有两个出口，那一种岂可。 下面程序运行完整也会结束，
			 * 递归不一定需要return结束， 函数尾巴结束的时候，也会退出来
			 */
			// 及时return避免后面不必要的for循环调用开销,仅仅提高效率
			return;
		}

		// 这个循环也可以写出 for (int number : num), 这里的 i
		// 并不是表示枚举的时候在什么位置，我们这里之所以for循环也就是枚举所有可能的取值，不能误解成为第i位取值，list才是控制位置的
		// 注意与subset不同，
		// permutation是以每个元素为起点，然后扫描整个数组形成一种排列,所以每次起点都是0，subset是以当前元素为起点，往后找以该元素为起点的子集，
		// 以[1,2]为例子比较输出很好理解
		for (int i = 0; i < num.length; i++) {
			// 添加list元素的时候需要注意除重，即在排列的时候，每次从0最开始位置找，如果这个人已经在待排列的list里了，就忽略，取下一个数子来排列
			if (list.contains(num[i])) {
				// you can't ignore this continue and if condition：
				continue;
			}

			list.add(num[i]);
			helper1(rst, list, num);
			// 是因为你递归的时候，加入进去了后，回溯到本层的时候要去掉曾经加的
			list.remove(list.size() - 1);
		}

	}

	/**
	 * method 2: 上面的方法在给排列某位置赋值的时候，用到
	 * list.contains()来保证没有两个位置摆放的同一个数字，下面这个方法用visted数组来确保
	 */

	public static List<List<Integer>> permute2(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (num == null || num.length == 0) {
			return result;
		}
		int[] visited = new int[num.length];
		ArrayList<Integer> list = new ArrayList<Integer>();
		helper2(result, list, num, visited);
		return result;
	}

	public static void helper2(List<List<Integer>> result,
			ArrayList<Integer> list, int[] num, int[] visited) {

		// 下面这个if 条件快，是最后一层递归才会运行的
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
			helper2(result, list, num, visited);
			list.remove(list.size() - 1);
			visited[i] = 0;
		}

	}

	/**
	 * Jun, latest, 与上面的差别是DFS加了个pos参数，其实没必要， 如上面方法，可以用list.size()来控制
	 */

	public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (nums == null || nums.size() == 0) {
			return result;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		DFS(nums, 0, list, result);
		return result;
	}

	public void DFS(ArrayList<Integer> nums, int pos, ArrayList<Integer> list,
			ArrayList<ArrayList<Integer>> result) {
		if (pos == nums.size()) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int num : nums) {
			if (list.contains(num)) {
				continue;
			}
			list.add(num);
			DFS(nums, pos + 1, list, result);
			list.remove(list.size() - 1);
		}
	}
}
