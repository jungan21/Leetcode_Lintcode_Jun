package company.walmart;

import tree_divideConquer.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

// http://www.programcreek.com/2014/06/leetcode-invert-binary-tree-java/
public class TreeMirrorInvertBinary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tree_divideConquer.TreeNode root = new tree_divideConquer.TreeNode(4);
		tree_divideConquer.TreeNode left = new tree_divideConquer.TreeNode(2);
		tree_divideConquer.TreeNode right = new tree_divideConquer.TreeNode(7);
		tree_divideConquer.TreeNode left_left = new tree_divideConquer.TreeNode(1);
		tree_divideConquer.TreeNode left_right = new tree_divideConquer.TreeNode(3);
		tree_divideConquer.TreeNode right_left = new tree_divideConquer.TreeNode(6);
		tree_divideConquer.TreeNode right_right = new tree_divideConquer.TreeNode(9);
		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;

		tree_divideConquer.TreeNode newTreeRoot = new TreeMirrorInvertBinary().invertTreeRecursion(root);
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
	public tree_divideConquer.TreeNode invertTreeRecursion2(tree_divideConquer.TreeNode root) {
		if (root == null)
			return null;
		// divide
		tree_divideConquer.TreeNode newLeft = invertTreeRecursion(root.right);
		tree_divideConquer.TreeNode newRight = invertTreeRecursion(root.left);

		// conquer
		root.left = newLeft;
		root.right = newRight;

		return root;
	}

	/**
	 * Travserse - recursive easy to understand
	 */
	// http://www.jiuzhang.com/solutions/invert-binary-tree/
	public tree_divideConquer.TreeNode invertTreeRecursion(tree_divideConquer.TreeNode root) {

		if (root == null) {
			return null;
		}
		invertTreeRecursion(root.left);
		invertTreeRecursion(root.right);

		tree_divideConquer.TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		return root;
	}

	/**
	 * Lintcode: method 1
	 */
	public void invertBinaryTree(tree_divideConquer.TreeNode root) {
		if (root == null) {
			return;
		}
		helper(root);
	}

	public tree_divideConquer.TreeNode helper(tree_divideConquer.TreeNode root) {
		if (root == null) {
			return null;
		}

		tree_divideConquer.TreeNode newLeft = helper(root.right);
		tree_divideConquer.TreeNode newRight = helper(root.left);

		root.left = newLeft;
		root.right = newRight;
		return root;
	}

	/**
	 * Lintcode: method 2:
	 */

	public void invertBinaryTree2(tree_divideConquer.TreeNode root) {
		if (root == null) {
			return;
		}
		invertBinaryTree(root.left);
		invertBinaryTree(root.right);

		tree_divideConquer.TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

	}

	public tree_divideConquer.TreeNode invertTreeIteration(tree_divideConquer.TreeNode root) {
		// first in, first out
		// Note: you can also you stack here;
		LinkedList<tree_divideConquer.TreeNode> queue = new LinkedList<tree_divideConquer.TreeNode>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			tree_divideConquer.TreeNode p = queue.poll();
			if (p.left != null) {
				queue.add(p.left);
			}
			if (p.right != null) {
				queue.add(p.right);
			}

			tree_divideConquer.TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
		}

		return root;
	}

	public tree_divideConquer.TreeNode invertTreeIteration2(tree_divideConquer.TreeNode root) {
		// first in, first out
		Stack<tree_divideConquer.TreeNode> stack = new Stack<tree_divideConquer.TreeNode>();
		if (root != null) {
			stack.push(root);
		}
		while (!stack.isEmpty()) {
			tree_divideConquer.TreeNode p = stack.pop();
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
