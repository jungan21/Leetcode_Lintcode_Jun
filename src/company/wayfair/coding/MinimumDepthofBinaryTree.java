package company.wayfair.coding;

import tree_divideConquer.TreeNode;

import java.util.LinkedList;

/**
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node. (must reach out to leaf node)
 */
public class MinimumDepthofBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int minDepth(TreeNode root) {
		// ending condition
		if (root == null) {
			return 0;
		}

		//Note:
		int left = minDepth(root.left) + 1;
		int right = minDepth(root.right) + 1;

		/**
		 * [1,2] tree(i.e. root is 1, left is 2), the minDepth should be 2,
		 * instead of 1, the path means from root to leaf, root itself can't be
		 * considered as a path
		 */
		// left == 1, means left is just the root node
		if (left == 1) {
			return right;
		} else if (right == 1) {
			return left;
		} else {
			return Math.min(left, right);
		}

	}

	/**
	 * jiuzhang
	 * 
	 * http://www.jiuzhang.com/solutions/minimum-depth-of-binary-tree/
	 */

	public int minDepth2(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getMin(root);
	}

	public int getMin(TreeNode root) {
		/**
		 * 不是非法不非法的问题，root ==
		 * null只可能是一个节点的左子树或者右子树有一个是空的，此时这个节点并非叶节点，深度应该是另外一颗子树的深度加一
		 * ，而我们要求出最小深度，故返回整型的最大值以保证答案的正确
		 */
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		/**
		 * you can NOT ignore below if and return,
		 * 
		 * it means root is the only node in the tree, root node is both root
		 * and leaf node
		 */
		if (root.left == null && root.right == null) {
			return 1;
		}

		return Math.min(getMin(root.left), getMin(root.right)) + 1;
	}

	/**
	 * http://www.programcreek.com/2013/02/leetcode-minimum-depth-of-binary-tree
	 * -java/
	 */
	public int minDepth3(TreeNode root) {
		if (root == null) {
			return 0;
		}

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		LinkedList<Integer> counts = new LinkedList<Integer>();

		nodes.add(root);
		counts.add(1);

		while (!nodes.isEmpty()) {
			TreeNode curr = nodes.remove();
			int count = counts.remove();

			if (curr.left == null && curr.right == null) {
				return count;
			}

			if (curr.left != null) {
				nodes.add(curr.left);
				counts.add(count + 1);
			}

			if (curr.right != null) {
				nodes.add(curr.right);
				counts.add(count + 1);
			}
		}

		return 0;
	}

	

}
