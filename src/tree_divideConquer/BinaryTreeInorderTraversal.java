package tree_divideConquer;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// jiuzhang: NON-Recursive
	// http://www.jiuzhang.com/solutions/binary-tree-inorder-traversal/
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		// currently processing node
		TreeNode curt = root;
		while (curt != null || !stack.empty()) {
			/**
			 * 把root压栈后，向左边遍历，并把左边的node压栈
			 */
			while (curt != null) {
				stack.add(curt);
				curt = curt.left;
			}

			curt = stack.peek();
			// left node pop() first,
			stack.pop();
			result.add(curt.val);
			curt = curt.right;
		}
		return result;
	}

	public ArrayList<Integer> inorderTraversalJun(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (!stack.empty()) {
			TreeNode cur = stack.peek();
			if (cur.left != null) {
				stack.push(cur.left);
				cur.left = null;
			} else {
				result.add(cur.val);
				stack.pop();
				if (cur.right != null) {
					stack.push(cur.right);
				}
			}
		}
		return result;
	}

	/**
	 * Version 1: Traverse Recursive
	 * 
	 * Result in parameter
	 * 
	 * Top Down
	 */

	public ArrayList<Integer> inorderTraversalRecursive1(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		helper(root, result);
		return result;
	}

	public void helper(TreeNode root, ArrayList<Integer> result) {
		/**
		 * can NOT ignore this condition, otherwise, you will get
		 * NullPointerException
		 */
		if (root == null) {
			return;
		}

		helper(root.left, result);
		result.add(root.val);
		helper(root.right, result);
	}

	/**
	 * Version 2: Divide & Conquer
	 * 
	 * Result in return value AND Bottom up
	 */
	public ArrayList<Integer> inorderTraversalRecursive2(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		/**
		 * null or leaf
		 *
		 * you can NOTE ignore this condition, otherwise, you will get
		 * NullPointerException
		 */
		if (root == null) {
			return result;
		}

		// Divide
		ArrayList<Integer> left = inorderTraversalRecursive2(root.left);
		ArrayList<Integer> right = inorderTraversalRecursive2(root.right);

		// Conquer
		result.addAll(left);
		result.add(root.val);
		result.addAll(right);

		return result;
	}
}
