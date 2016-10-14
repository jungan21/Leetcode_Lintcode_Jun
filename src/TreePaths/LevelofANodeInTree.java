package TreePaths;

public class LevelofANodeInTree {

	public static void main(String[] args) {

	}

	// Time O(n)
	private static int getLevel(TreeNode root, TreeNode node) {
		return helper(node, node, 1);
	}

	public static int helper(TreeNode root, TreeNode node, int level) {
		// root的level 是0
		if (root == null) {
			return 0;
		}

		if (root == node) {
			return level;
		}

		int leftLevel = helper(root.left, node, level + 1);
		if (leftLevel != 0) {
			return leftLevel;
		}
		int rightLevel = helper(root.right, node, level + 1);
		if (rightLevel != 0) {
			return rightLevel;
		}
		// node doesn't exist in the tree
		return -1;
	}

}
