package tree_divideConquer;

import java.util.Stack;

/**
 * 
 Design an iterator over a binary search tree with the following rules:
 * 
 * Elements are visited in ascending order (i.e. an in-order traversal) next()
 * and hasNext() queries run in O(1) time in average.
 * 
 *
 */
public class BinarySearchTreeIterator {

	private Stack<TreeNode> stack = new Stack<>();
	private TreeNode curt;

	// @param root: The root of binary tree.
	public BinarySearchTreeIterator(TreeNode root) {
		curt = root;
	}

	// @return: True if there has next node, or false
	// please refer to the inorder traverser code, it's just the while loop
	// condition
	public boolean hasNext() {
		return (curt != null || !stack.isEmpty());
	}

	// @return: return next node
	public TreeNode next() {
		while (curt != null) {
			stack.push(curt);
			curt = curt.left;
		}

		curt = stack.pop();
		TreeNode node = curt;
		curt = curt.right;

		return node;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		TreeNode left = new TreeNode(6);
		TreeNode right = new TreeNode(10);
		TreeNode left_right = new TreeNode(7);
		TreeNode left_left = new TreeNode(5);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		root.right = right;

		BinarySearchTreeIterator i = new BinarySearchTreeIterator(root);
		while (i.hasNext()) {
			System.out.println(i.next().val);

		}
	}

}
