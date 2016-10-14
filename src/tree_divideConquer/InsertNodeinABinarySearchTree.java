package tree_divideConquer;

/**
 * Given a binary search tree and a new tree node, insert the node into the
 * tree. You should keep the tree still be a valid binary search tree.
 * 
 * Notice
 * 
 * You can assume there is no duplicate values in this tree + node.
 *
 */
public class InsertNodeinABinarySearchTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
		TreeNode node = new TreeNode(4);
		insertNodeIterative2(root, node);
	}

	/**
	 * http://www.cnblogs.com/EdwardLiu/p/4314777.html recursive:
	 */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		// write your code here
		if (root == null) {
			return node;
		}
		if (node == null) {
			return root;
		}
		helper(root, node);
		return root;
	}

	public void helper(TreeNode root, TreeNode node) {

		if (root.val < node.val && root.right == null) {
			root.right = node;
		} else if (root.val > node.val && root.left == null) {
			root.left = node;
		} else if (root.val < node.val) {
			helper(root.right, node);
		} else {
			helper(root.left, node);
		}
	}

	/**
	 * http://www.jiuzhang.com/solutions/insert-node-in-binary-search-tree/
	 */

	public TreeNode insertNode1(TreeNode root, TreeNode node) {
		// write your code here
		if (root == null) {
			return node;
		}
		if (node == null) {
			return root;
		}
		if (node.val < root.val) {
			root.left = insertNode(root.left, node);
		} else if (node.val > root.val) {
			root.right = insertNode(root.right, node);
		}
		return root;
	}

	/**
	 * Iteraive http://www.cnblogs.com/EdwardLiu/p/4314777.html Iterative
	 */
	public TreeNode insertNodeIterative(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}
		if (node == null) {
			return root;
		}
		TreeNode rootCopy = root;

		while (root != null) {
			if (root.val < node.val && root.right == null) {
				root.right = node;
				break;
			} else if (root.val > node.val && root.left == null) {
				root.left = node;
				break;
			} else if (root.val < node.val) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return rootCopy;
	}

	/**
	 * Iterative jiuzhang:
	 * http://www.jiuzhang.com/solutions/insert-node-in-binary-search-tree/
	 */

	public static TreeNode insertNodeIterative2(TreeNode root, TreeNode node) {
		if (root == null) {
			root = node;
			return root;
		}
		TreeNode tmp = root;
		TreeNode last = null;
		while (tmp != null) {
			last = tmp;
			if (tmp.val > node.val) {
				tmp = tmp.left;
			} else {
				tmp = tmp.right;
			}
		}
		// Note: last.left = node
		if (last != null) {
			if (last.val > node.val) {
				last.left = node;
			} else {
				last.right = node;
			}
		}
		return root;
	}

}
