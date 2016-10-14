package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * root 2 any node
 * 
 * start from *root*, stop at any node
 * 
 * contain at least one node in it(e.g root itself).
 * 
 */
public class MaximumPathSumII_root2any {

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

		System.out.println(binaryTreeRoot2AnyMaxSumPath(root));
	}

	public static int maxPathSum2(TreeNode root) {
		if (root == null) {
			/**
			 * we can use 0 here, because we always start from root node
			 */
			return 0;
			// you can also use: return Integer.MIN_VALUE;
		}

		int left = maxPathSum2(root.left);
		int right = maxPathSum2(root.right);
		/**
		 * return root.val + Math.max(left, right); 不对，因为题目要求root to anynode,
		 * 至少包含一个node, 如果left, right都是负数的情况，就root.val就是最大值，
		 * 
		 * e.g. root=1, root.left =-5
		 * 
		 * Reason we use: root.val + Math.max(0, Math.max(left, right));
		 * 
		 * if Math.max(left, right) is NEGATIVE number, we can use Math.max(0,
		 * Math.max(left, right)) to eliminate it which means we just return
		 * root.val itself without adding any left or right child value (based
		 * on the requirement, we must start from root, we can stop at any
		 * point)
		 */
		// root -> anynode
		// http://algorithms.tutorialhorizon.com/given-a-binary-tree-find-out-the-maximum-sum-of-value-from-root-to-each-leaf/
		return root.val + Math.max(0, Math.max(left, right));
		// root -> leaf node question (follow up question)
		// we can just revise above last return code to:
		// return root.val + Math.max(left, right);
		// root - leaf: online
		// http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/

	}

	/**
	 * method 2, 好理解
	 */

	static int max = Integer.MIN_VALUE;

	public static int root2AnyMaxSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root, 0);
		return max;
	}

	public static void helper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum = sum + root.val;
		max = Math.max(max, sum);
		helper(root.left, sum);
		helper(root.right, sum);
	}

	/**
	 * best one
	 * 
	 * Jun's 找出max sum最大的 root to any node 的path 以及 max sum
	 */
	static int maxSum = 0;

	public static List<List<Integer>> binaryTreeRoot2AnyMaxSumPath(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<>();
		helperJun(root, list, result, 0);
		// print out max sum result
		System.out.println(maxSum);
		return result;
	}

	public static void helperJun(TreeNode root, List<Integer> list,
			List<List<Integer>> result, int sum) {
		if (root == null) {
			return;
		}
		sum = sum + root.val;
		list.add(root.val);
		if (sum > maxSum) {
			maxSum = sum;
			// clear() fisst, result keep ONLY the max root->leaf path
			result.clear();
			result.add(new ArrayList<Integer>(list));
		}
		helperJun(root.left, list, result, sum);
		helperJun(root.right, list, result, sum);
		list.remove(list.size() - 1);
	}

}
