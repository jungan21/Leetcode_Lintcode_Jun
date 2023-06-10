package company.walmart;

import tree_divideConquer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tree_divideConquer.TreeNode root = new tree_divideConquer.TreeNode(1);
		tree_divideConquer.TreeNode left = new tree_divideConquer.TreeNode(2);
		tree_divideConquer.TreeNode right = new tree_divideConquer.TreeNode(3);
		tree_divideConquer.TreeNode left_left = new tree_divideConquer.TreeNode(4);
		tree_divideConquer.TreeNode left_right = new tree_divideConquer.TreeNode(5);
		tree_divideConquer.TreeNode right_left = new tree_divideConquer.TreeNode(6);
		tree_divideConquer.TreeNode right_right = new tree_divideConquer.TreeNode(7);
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

	public static int heightOfBinaryTree(tree_divideConquer.TreeNode node) {
		// return 0, is needed as it's the recursive ending condition
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(heightOfBinaryTree(node.left),
					heightOfBinaryTree(node.right));
		}
	}

	// level order traversal
	public static int heightOfBinaryTreeIterative(tree_divideConquer.TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<tree_divideConquer.TreeNode> queue = new LinkedList<tree_divideConquer.TreeNode>();
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
