package graph_dfs;

import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 *
 */
public class Combinations {

	public static void main(String[] args) {
		int n = 4, k = 2;
		System.out.println(combine(4, 2));
	}

	public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		// because it need to begin from 1, 可取值范围[1, 2, 3...n]
		helper(result, list, n, k, 1);
		return result;
	}

	private static void helper(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int n, int k, int start) {
		// list.size()来控制位置，position
		if (list.size() == k) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = start; i <= n; i++) {
			list.add(i);

			// the new start should be after the next number after i
			helper(result, list, n, k, i + 1);
			list.remove(list.size() - 1);
		}
	}

}
