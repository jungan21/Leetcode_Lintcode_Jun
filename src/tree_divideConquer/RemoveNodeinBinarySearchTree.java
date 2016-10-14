package tree_divideConquer;

public class RemoveNodeinBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode removeNode(TreeNode root, int value) {
		TreeNode dummy = new TreeNode(0);
		dummy.left = root;

		TreeNode parent = findNode(dummy, root, value);
		TreeNode node;
		if (parent.left != null && parent.left.val == value) {
			node = parent.left;
		} else if (parent.right != null && parent.right.val == value) {
			node = parent.right;
		} else {
			return dummy.left;
		}

		deleteNode(parent, node);

		return dummy.left;
	}

	private TreeNode findNode(TreeNode parent, TreeNode node, int value) {
		if (node == null) {
			return parent;
		}

		if (node.val == value) {
			return parent;
		}
		if (value < node.val) {
			return findNode(node, node.left, value);
		} else {
			return findNode(node, node.right, value);
		}
	}

	private void deleteNode(TreeNode parent, TreeNode node) {
		if (node.right == null) {
			if (parent.left == node) {
				parent.left = node.left;
			} else {
				parent.right = node.left;
			}
		} else {
			TreeNode temp = node.right;
			TreeNode father = node;

			while (temp.left != null) {
				father = temp;
				temp = temp.left;
			}

			if (father.left == temp) {
				father.left = temp.right;
			} else {
				father.right = temp.right;
			}

			if (parent.left == node) {
				parent.left = temp;
			} else {
				parent.right = temp;
			}

			temp.left = node.left;
			temp.right = node.right;
		}
	}

	/**
	 * better method, easy to understand
	 * 
	 * https://www.youtube.com/watch?v=gcULXE7ViZw
	 * 
	 * http://hehejun.blogspot.ca/2015/01/lintcoderemove-node-in-binary-search.
	 */
	public TreeNode removeNodeRecusive(TreeNode root, int value) {
		if (root == null)
			return null;
		if (value < root.val)
			root.left = removeNodeRecusive(root.left, value);
		else if (value > root.val)
			root.right = removeNodeRecusive(root.right, value);
		// root.val = value
		else {
			// case 1: no child
			if (root.left == null && root.right == null) {
				// 或者直接return null;
				root = null;
				return root;
				// case 2: one child{
			} else if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
				// case 3: two children
			} else {
				TreeNode temp = root;
				// find the min value in right subtree, and make is as root
				root = findMin(root.right);
				// remember to use x
				// right is the original right tree with min node removed
				root.right = deleteMin(temp.right);
				// left is original left tree
				root.left = temp.left;
				// root.rigth, root.left赋值顺序不能改变
			}
		}
		return root;
	}

	public TreeNode findMin(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left == null) {
			return root;
		}

		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	public TreeNode deleteMin(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left == null) {
			root = root.right;
			return root;
		}

		while (root.left.left != null) {
			root = root.left;
		}
		root.left = null;
		return root;
	}
}
