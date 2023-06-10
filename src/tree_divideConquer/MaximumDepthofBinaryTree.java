package tree_divideConquer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * also called: longest path from root to leaf in a binary tree
 *
 */
public class MaximumDepthofBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Divide Connquer recrusion
	// http://www.jiuzhang.com/solutions/maximum-depth-of-binary-tree/

	// 求树的最大深度，即从root到叶子节点的最长的路径多长
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		// divide
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		// conquer, 1 means root itself
		return Math.max(left, right) + 1;
	}

	/**
	 * Traverser recursion
	 */
	// we need this variable
	private int maxDepth = 0;

	public int maxDepth2(TreeNode root) {
		// 1 means current depth i.e. for root node
		helper(root, 1);
		return maxDepth;
	}

	private void helper(TreeNode node, int curtDepth) {
		if (node == null) {
			return;
		}

		maxDepth = Math.max(maxDepth, curtDepth);

		helper(node.left, curtDepth + 1);
		helper(node.right, curtDepth + 1);
	}

	// BFS  求树的最大深度，即从root到叶子节点的最长的路径多
	public int maxDepthLevelOrderTraverse(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int maxDepth = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int depth = 0;
		queue.offer(root);
		while (!queue.isEmpty()) {
			depth++;
			maxDepth = Math.max(maxDepth, depth);
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
		return maxDepth;
	}

}
