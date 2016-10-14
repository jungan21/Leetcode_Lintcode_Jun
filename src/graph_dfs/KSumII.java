package graph_dfs;

import java.util.ArrayList;

/**
 * Given n unique integers, number k (1<=k<=n) and target.
 * 
 * Find all possible k integers where their sum is target.
 *
 */
public class KSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Jun
	 */
	public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();

		if (A == null || A.length == 0) {
			return result;
		}
		// subset problem, 0 means starting position
		DFS(A, result, list, k, 0, target);
		return result;

	}

	public void DFS(int[] A, ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, int k, int pos, int target) {

		if (target == 0 && list.size() == k) {
			result.add(new ArrayList<Integer>(list));
		}

		for (int i = pos; i < A.length; i++) {
			list.add(A[i]);
			DFS(A, result, list, k, i + 1, target - A[i]);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Jiuzhang
	 */
	ArrayList<ArrayList<Integer>> ans;

	public void dfs(int A[], int K, int target, int index,
			ArrayList<Integer> list) {

		if (K == 0 && target == 0) {
			ans.add(new ArrayList<Integer>(list));
			return;
		}
		if (K < 0 || target < 0 || index < 0)
			return;
		dfs(A, K, target, index - 1, list);
		list.add(A[index]);
		dfs(A, K - 1, target - A[index], index - 1, list);
		list.remove(list.size() - 1);

	}

	public ArrayList<ArrayList<Integer>> kSumIIJiuzhang(int A[], int K,
			int target) {
		ans = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		dfs(A, K, target, A.length - 1, list);
		return ans;
	}

}
