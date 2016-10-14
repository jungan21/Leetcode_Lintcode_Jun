package TreePaths;

public class LeafNodeWithMinimumDepth {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode left_right_right = new TreeNode(8);

		root.left = left;
		root.right = right;
		//left.left = left_left;
		left.right = left_right;
		right.left = right_left;
		left.right.right = left_right_right;
		System.out.println(minimumDepthLeafNode(root));
	}

	public static TreeNode minimumDepthLeafNode(TreeNode root) {
		// root's depth is 0
		helper(root, 0);
		System.out.println(minimumDepthLeafNodeDepth);
		System.out.println(minimumDepthLeafNode.val);
		return minimumDepthLeafNode;
	}

	static TreeNode minimumDepthLeafNode;
	static int minimumDepthLeafNodeDepth = Integer.MAX_VALUE;

	public static void helper(TreeNode root, int depth) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null
				&& depth < minimumDepthLeafNodeDepth) {
			minimumDepthLeafNode = root;
			minimumDepthLeafNodeDepth = depth;
		}
		helper(root.left, depth + 1);
		helper(root.right, depth + 1);
	}
}
