package tree_divideConquer;

/**
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * crack code: 4.1
 *
 */

public class BalancedBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(7);
		TreeNode left_left = new TreeNode(1);
		TreeNode left_right = new TreeNode(3);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(9);
		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;

		TreeNode root1 = new TreeNode(1);
		TreeNode right1 = new TreeNode(2);
		TreeNode right1_right = new TreeNode(3);

		root1.right = right1;
		right1.right = right1_right;

	}

	/**
	 * easy to understand !!!
	 * http://blog.csdn.net/fightforyourdream/article/details/18693131
	 */

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		// 如果子树高度差大于1，则不平衡
		if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
			return false;
		}
		// 递归检查左子树和右子树的平衡性
		return isBalanced(root.left) && isBalanced(root.right);
	}

	// 帮助方法，返回树的高度
	private int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(depth(root.left), depth(root.right));
	}

	/**
	 * jiuzhang method: recommend by teacher
	 * http://www.jiuzhang.com/solutions/balanced-binary-tree/
	 */
	// helper result class
	class BalancedBinaryTreeResultType {
		public boolean isBalanced;
		public int maxDepth;

		public BalancedBinaryTreeResultType(boolean isBalanced, int maxDepth) {
			this.isBalanced = isBalanced;
			this.maxDepth = maxDepth;
		}
	}

	public boolean isBalanced1(TreeNode root) {
		return helper(root).isBalanced;
	}

	private BalancedBinaryTreeResultType helper(TreeNode root) {
		if (root == null) {
			// null tree is balanced tree
			return new BalancedBinaryTreeResultType(true, 0);
		}
		// divide
		BalancedBinaryTreeResultType left = helper(root.left);
		BalancedBinaryTreeResultType right = helper(root.right);

		// conquer
		// substree is NOT balanced
		if (!left.isBalanced || !right.isBalanced) {
			// not balanced, we don't care maxDepth property, just set it -1
			return new BalancedBinaryTreeResultType(false, -1);
		}

		// root not balanced
		if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
			return new BalancedBinaryTreeResultType(false, -1);
		}
		return new BalancedBinaryTreeResultType(true, Math.max(left.maxDepth,
				right.maxDepth) + 1);
	}

	/**
	 * NOT Recommend by 老师
	 * http://www.jiuzhang.com/solutions/balanced-binary-tree/
	 */

	public boolean isBalanced2(TreeNode root) {
		return maxDepth(root) != -1;
	}

	// this maxDepth is just for get the height of the tree
	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// the height of left sub tree
		int left = maxDepth(root.left);
		// the height of right sub tree
		int right = maxDepth(root.right);
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

	/**
	 * http://www.programcreek.com/2013/02/leetcode-balanced-binary-tree-java/
	 */

	public boolean isBalanced3(TreeNode root) {
		if (root == null)
			return true;

		if (getHeight(root) == -1)
			return false;

		return true;
	}

	public int getHeight(TreeNode root) {
		if (root == null)
			return 0;

		int left = getHeight(root.left);
		int right = getHeight(root.right);

		if (left == -1 || right == -1)
			return -1;

		if (Math.abs(left - right) > 1) {
			return -1;
		}

		return Math.max(left, right) + 1;

	}

}
