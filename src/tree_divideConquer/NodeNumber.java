package tree_divideConquer;

public class NodeNumber {

	/**
	 * all node number
	 * 
	 * 求二叉树中节点个数 递归解法：
	 * 
	 * （1）如果二叉树为空，节点个数为0
	 * 
	 * （2）如果二叉树不为空，二叉树节点个数 = 左子树节点个数 + 右子树节点个数 + 1
	 */
	public static int nodeNumber(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = nodeNumber(root.left);
		int right = nodeNumber(root.right);
		return left + right + 1;
	}

	/**
	 * all node number
	 * 
	 * 求二叉树中节点个数 递归解法：
	 * 
	 * （1）如果二叉树为空，节点个数为0
	 * 
	 * （2）如果二叉树不为空且左右子树为空，返回1
	 * 
	 * （3）如果二叉树不为空，且左右子树不同时为空，返回左子树中叶子节点个数加上右子树中叶子节点个数
	 */
	public static int leafNodeNumber(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int left = leafNodeNumber(root.left);
		int right = leafNodeNumber(root.right);
		return left + right;
	}

	/**
	 * 求二叉树第K层的节点个数 递归解法：
	 * 
	 * （1）如果二叉树为空或者k<1返回0
	 * 
	 * （2）如果二叉树不为空并且k==1，返回1
	 * 
	 * （3）如果二叉树不为空且k>1，返回左子树中k-1层的节点个数与右子树k-1层节点个数之和
	 */
	// root level is 0
	public static int kLevelNodeNumber(TreeNode root, int k) {
		if (root == null) {
			return 0;
		}
		if (k == 0) {
			return 1;
		}
		int left = kLevelNodeNumber(root.left, k - 1);
		int right = kLevelNodeNumber(root.right, k - 1);
		return left + right;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);

		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(7);

		root.left = left;
		root.right = right;

		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;
		System.out.println(nodeNumber(root));
		System.out.println(leafNodeNumber(root));
		System.out.println(kLevelNodeNumber(root, 1));
	}
}
