package tree_divideConquer;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

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
		System.out.println(postorderTraversal(root));

	}

	/**
	 * http://www.jiuzhang.com/solutions/binary-tree-postorder-traversal/
	 * Iterative
	 */
	public static ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode prev = null; // previously traversed node
		TreeNode curr = root;

		if (root == null) {
			return result;
		}
		stack.push(root);
		while (!stack.empty()) {
			curr = stack.peek();
			// traverse down the tree
			if (prev == null || prev.left == curr || prev.right == curr) {
				if (curr.left != null) {
					stack.push(curr.left);
				} else if (curr.right != null) {
					stack.push(curr.right);
				}
				// traverse up the tree from the left
			} else if (curr.left == prev) {
				if (curr.right != null) {
					stack.push(curr.right);
				}
			} else { // traverse up the tree from the right
				result.add(curr.val);
				stack.pop();
			}
			prev = curr;
		}

		return result;
	}

	/**
	 * Version 1: Recursive
	 * 
	 * Result in parameter
	 * 
	 * Top Down
	 */
	public ArrayList<Integer> postorderTraversalRecursive1(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}

		result.addAll(postorderTraversalRecursive1(root.left));
		result.addAll(postorderTraversalRecursive1(root.right));
		result.add(root.val);

		return result;

	}

	/**
	 * Version 2: Divide & Conquer Recursive
	 * 
	 * Result in return value AND Bottom up
	 */
	public ArrayList<Integer> postorderTraversalRecursive2(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}

		// Divide
		ArrayList<Integer> left = postorderTraversalRecursive2(root.left);
		ArrayList<Integer> right = postorderTraversalRecursive2(root.right);

		// Conquer
		result.addAll(left);
		result.addAll(right);
		result.add(root.val);

		return result;

	}

}
