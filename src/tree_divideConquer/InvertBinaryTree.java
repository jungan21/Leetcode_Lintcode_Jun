package tree_divideConquer;

import java.util.LinkedList;
import java.util.Stack;

// http://www.programcreek.com/2014/06/leetcode-invert-binary-tree-java/
public class InvertBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(4);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(7);
		TreeNode left_left = new TreeNode(1);
		TreeNode left_right = new TreeNode(3);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(9);
		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;

		TreeNode newTreeRoot = new InvertBinaryTree().invertTreeRecursion(root);
		System.out.print(newTreeRoot.left.val + "   ");
		System.out.print(newTreeRoot.right.val);
		System.out.println();
		System.out.print(newTreeRoot.left.left.val);
		System.out.print(newTreeRoot.left.right.val);
		System.out.print("\t");
		System.out.print(newTreeRoot.right.left.val);
		System.out.print(newTreeRoot.right.right.val);

	}

	/**
	 * divide conquer - recursive
	 */
	public TreeNode invertTreeRecursion2(TreeNode root) {
		if (root == null)
			return null;
		// divide
		TreeNode newLeft = invertTreeRecursion(root.right);
		TreeNode newRight = invertTreeRecursion(root.left);

		// conquer
		root.left = newLeft;
		root.right = newRight;

		return root;
	}

	/**
	 * Travserse - recursive easy to understand
	 */
	// http://www.jiuzhang.com/solutions/invert-binary-tree/
	public TreeNode invertTreeRecursion(TreeNode root) {

		if (root == null) {
			return null;
		}
		invertTreeRecursion(root.left);
		invertTreeRecursion(root.right);

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		return root;
	}

	/**
	 * Lintcode: method 1
	 */
	public void invertBinaryTree(TreeNode root) {
		if (root == null) {
			return;
		}
		helper(root);
	}

	public TreeNode helper(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode newLeft = helper(root.right);
		TreeNode newRight = helper(root.left);

		root.left = newLeft;
		root.right = newRight;
		return root;
	}

	/**
	 * Lintcode: method 2:
	 */

	public void invertBinaryTree2(TreeNode root) {
		if (root == null) {
			return;
		}
		invertBinaryTree(root.left);
		invertBinaryTree(root.right);

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

	}

	public TreeNode invertTreeIteration(TreeNode root) {
		// first in, first out
		// Note: you can also you stack here;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			TreeNode p = queue.poll();
			if (p.left != null) {
				queue.add(p.left);
			}
			if (p.right != null) {
				queue.add(p.right);
			}

			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
		}

		return root;
	}

	public TreeNode invertTreeIteration2(TreeNode root) {
		// first in, first out
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root != null) {
			stack.push(root);
		}
		while (!stack.isEmpty()) {
			TreeNode p = stack.pop();
			if (p.left != null) {
				stack.push(p.left);
			}
			if (p.right != null) {
				stack.push(p.right);
			}

			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
		}

		return root;
	}
}
