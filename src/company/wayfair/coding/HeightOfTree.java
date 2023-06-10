package company.wayfair.coding;

import tree_divideConquer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

		System.out.print(heightOfBinaryTreeIterative(root));

	}

	/**
	 * Get the height of left sub tree, say leftHeight Get the height of right
	 * sub tree, say rightHeight Take the Max(leftHeight, rightHeight) and add 1
	 * for the root and return Call recur­sively.
	 */

	public static int heightOfBinaryTree(TreeNode node) {
		// return 0, is needed as it's the recursive ending condition
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(heightOfBinaryTree(node.left),
					heightOfBinaryTree(node.right));
		}
	}

	// level order traversal
	public static int heightOfBinaryTreeIterative(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int height = 0;
		while (!queue.isEmpty()) {
			height++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return height;
	}
}
