package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22,
 *
 */
public class LongestRoot2LeafPath {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(4);
		TreeNode right = new TreeNode(8);

		TreeNode left_left = new TreeNode(11);
		TreeNode right_left = new TreeNode(13);
		TreeNode right_right = new TreeNode(4);

		TreeNode left_left_left = new TreeNode(7);
		TreeNode left_left_right = new TreeNode(2);
		TreeNode right_right_left = new TreeNode(5);
		TreeNode right_right_right = new TreeNode(1);

		root.left = left;
		root.right = right;
		left.left = left_left;
		right.left = right_left;
		right.right = right_right;
		left.left.left = left_left_left;
		left.left.right = left_left_right;
		right.right.left = right_right_left;
		right.right.right = right_right_right;

		System.out.println(longestRoot2LeafPath(root));
		// System.out.println(pathSum(root, 22));
	}

	public static int longestRoot2LeafPath(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		pathSumHelper(root, list, result);
		System.out.println(result);
		int max = 0;
		for (List<Integer> l : result) {
			max = Math.max(max, l.size());
		}
		return max;
	}

	public static void pathSumHelper(TreeNode root, List<Integer> list,
			List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(list));
		}
		pathSumHelper(root.left, list, result);
		pathSumHelper(root.right, list, result);
		list.remove(list.size() - 1);
	}

	/**
	 * method 2: http://www.cnblogs.com/grandyang/p/4042156.html
	 */
	public List<List<Integer>> pathSum1(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		List<Integer> list = new ArrayList<Integer>();
		list.add(root.val);
		helper(root, sum, list, result);
		return result;
	}

	public void helper(TreeNode root, int sum, List<Integer> list,
			List<List<Integer>> result) {
		if (root.left == null && root.right == null && root.val == sum) {
			result.add(new ArrayList<Integer>(list));
			// 这个return可以省略，因为运行完了自动推到上一次的递归调用
			return;
		}

		if (root.left != null) {
			list.add(root.left.val);
			// Note sum-root.val, 而不是sum- root.left.val
			helper(root.left, sum - root.val, list, result);
			list.remove(list.size() - 1);
		}

		if (root.right != null) {
			list.add(root.right.val);
			helper(root.right, sum - root.val, list, result);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * method 3: http://www.cnblogs.com/yuzhangcmu/p/4377146.html Solution 1
	 */
	public static ArrayList<ArrayList<Integer>> pathSumRecursive2(
			TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return rst;

		list.add(root.val);
		sum = sum - root.val;

		dfs1(rst, list, root, sum);
		return rst;
	}

	private static void dfs1(ArrayList<ArrayList<Integer>> result,
			ArrayList<Integer> list, TreeNode root, int sum) {
		if (root == null) {
			return;
		}

		if (sum == 0 && root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(list));
			return;
		}
		if (root.left != null) {
			list.add(root.left.val);
			// note: 因为原始方法里sum-root.val已经减过了，
			dfs1(result, list, root.left, sum - root.left.val);
			list.remove(list.size() - 1);
		}
		if (root.right != null) {
			list.add(root.right.val);
			dfs1(result, list, root.right, sum - root.right.val);
			list.remove(list.size() - 1);
		}

	}

	/**
	 * method 4: jiuzhang template
	 */
	public List<List<Integer>> pathSum3(TreeNode root, int sum) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (root == null)
			return rst;

		DFS(rst, path, root, sum);
		return rst;
	}

	private void DFS(List<List<Integer>> result, ArrayList<Integer> path,
			TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum = sum - root.val;
		if (root.left == null && root.right == null) {
			if (sum == 0) {
				path.add(root.val);
				result.add(new ArrayList<Integer>(path));
				path.remove(path.size() - 1);
			}

		}
		path.add(root.val);
		DFS(result, path, root.left, sum);
		DFS(result, path, root.right, sum);
		path.remove(path.size() - 1);

	}

}
