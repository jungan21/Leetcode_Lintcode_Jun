package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * root node ==> leaf
 * 
 * The path may start at root and end at leaf in the tree.
 * 
 * http://algorithms.tutorialhorizon.com/given-a-binary-tree-find-out-the-
 * maximum-sum-of-value-from-root-to-each-leaf/
 * 
 * http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
 * 
 */

public class MaximumPathSum_root2leaf {

	public static void main(String[] args) {
		// TreeNode root = new TreeNode(1);
		// TreeNode left = new TreeNode(-2);
		// TreeNode right = new TreeNode(-3);
		// root.left = left;
		// root.right = right;
		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(4);
		TreeNode right = new TreeNode(8);

		TreeNode left_left = new TreeNode(11);
		TreeNode right_left = new TreeNode(-13);
		TreeNode right_right = new TreeNode(4);

		TreeNode left_left_left = new TreeNode(-7);
		TreeNode left_left_right = new TreeNode(-2);
		TreeNode right_right_left = new TreeNode(-5);
		TreeNode right_right_right = new TreeNode(-1);

		root.left = left;
		root.right = right;
		left.left = left_left;
		right.left = right_left;
		right.right = right_right;
		left.left.left = left_left_left;
		left.left.right = left_left_right;
		right.right.left = right_right_left;
		right.right.right = right_right_right;

		System.out.println(root2LeafMaxSum(root));
	}

	/**
	 * best one
	 * 
	 * Jun's 找出max sum最大的 root to leaf 的path 以及 max sum
	 */
	static int max = 0;

	public static List<List<Integer>> binaryTreeRoot2LeafMaxSum(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<>();
		helperJun(root, list, result, 0);
		// print out max sum result
		System.out.println(max);
		return result;
	}

	public static void helperJun(TreeNode root, List<Integer> list,
			List<List<Integer>> result, int sum) {
		if (root == null) {
			return;
		}
		sum = sum + root.val;
		list.add(root.val);
		if (root.left == null && root.right == null && sum > max) {
			max = sum;
			// clear() fisst, result keep ONLY the max root->leaf path
			result.clear();
			result.add(new ArrayList<Integer>(list));
		}
		helperJun(root.left, list, result, sum);
		helperJun(root.right, list, result, sum);
		list.remove(list.size() - 1);
	}

	// http://algorithms.tutorialhorizon.com/given-a-binary-tree-find-out-the-maximum-sum-of-value-from-root-to-each-leaf/
	// http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
	static int maxSum = Integer.MIN_VALUE;
	static TreeNode maxSumLeaf = null;

	public static int root2LeafMaxSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root, 0);
		return maxSum;
	}

	// pre­order traversal
	public static void helper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum = sum + root.val;
		if (root.left == null && root.right == null && sum > maxSum) {
			maxSum = sum;
			maxSumLeaf = root;
		}
		helper(root.left, sum);
		helper(root.right, sum);
	}

	// 网上找path的方法，不好理解
	public static boolean path(TreeNode root, TreeNode leaf) {
		// base case
		if (root == null) {
			return false;
		}
		// return true if this node is the target_leaf OR
		// target leaf is present in one of its descendants
		if ((root == leaf) || path(root.left, leaf) || path(root.right, leaf)) {
			System.out.print(" " + root.val);
			return true;
		}
		return false;
	}

}
