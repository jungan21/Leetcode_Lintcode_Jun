package company.walmart;


// https://leetcode.com/problems/symmetric-tree/

import tree_divideConquer.TreeNode;

public class TreeSymmetric {

	public boolean isSymmetric(tree_divideConquer.TreeNode root) {
		if (root == null) {
			return true;
		}
		return helper(root.left, root.right);
	}

	private boolean helper(tree_divideConquer.TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val != root2.val) {
			return false;
		}
		// compare root1.left with root2.right
		return helper(root1.left, root2.right)
				&& helper(root1.right, root2.left);
	}
}
