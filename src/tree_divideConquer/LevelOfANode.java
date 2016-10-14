package tree_divideConquer;

/**
 * Implement an algorithm to get the level of a node in a Binary Tree assuming
 * root node to be at level 1.
 *
 */
public class LevelOfANode {

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

		System.out.println(getLevelofNode(root, 2, 1));
		getLevelofNode1(root, 2, 1);
		System.out.println(levelOfNode);
	}

	public static int getLevelofNode(TreeNode root, int target, int level) {
		if (root == null) {
			return 0;
		}

		if (root.val == target) {
			return level;
		}
		int left = getLevelofNode(root.left, target, level + 1);
		int right = getLevelofNode(root.right, target, level + 1);

		if (left == 0) {
			return right;
		} else {
			return left;
		}
	}

	static int levelOfNode = 0;

	public static void getLevelofNode1(TreeNode root, int target, int level) {
		if (root == null) {
			return;
		}

		if (root.val == target) {
			levelOfNode = level;
		}
		getLevelofNode1(root.left, target, level + 1);
		getLevelofNode1(root.right, target, level + 1);

	}
	
	/**
	 * level order traersal
	 */
}
