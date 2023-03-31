package tree_divideConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Preorder 与 Inorder 必背，考点
 * http://www.jiuzhang.com/solutions/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Version 0: Non-Recursion (Recommend)
	 */

	public List<Integer> preorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		// result list
		List<Integer> preorder = new ArrayList<Integer>();
		if (root == null) {
			return preorder;
		}
		stack.push(root);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			preorder.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return preorder;
	}

	/**
	 * Version 1: Traverse Recursive - (up to bottom)
	 * 
	 * Result in parameter
	 * 
	 * Top Down
	 */
	public ArrayList<Integer> preorderTraversalRecursive(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		preorderHelper(root, result);
		return result;
	}

	/**
	 * 1. 定义：把root为根的preorder加入result里面
	 * 
	 * 2. 递归的步骤，如何把大事拆为小事
	 */
	private void preorderHelper(TreeNode root, ArrayList<Integer> result) {
		/**
		 * null or leaf
		 * 
		 * you can NOT ignore this condition, otherwise, you will get
		 * NullPointerException
		 */
		if (root == null) {
			return;
		}
		// 这里就不可以多个线程并行进行，顺序就乱了
		result.add(root.val);
		preorderHelper(root.left, result);
		preorderHelper(root.right, result);
	}

	/**
	 * Version 2: Divide & Conquer (bottom->up)
	 * 
	 * （recommend to use this method, better than version1-Traverse Recursive ）
	 * 
	 * Result in return value AND Bottom up
	 */
	public ArrayList<Integer> preorderTraversalRecursive1(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		/**
		 * null condition better than leaf node condition
		 *
		 * can NOT ignore this if condition, otherwise, will get
		 * NullPointerException
		 */
		if (root == null) {
			// you must
			return result;
		}

		// Divide, 这里可以起多个线程并行进行， 这也是better than traverse recursive点之一
		ArrayList<Integer> left = preorderTraversalRecursive1(root.left);
		ArrayList<Integer> right = preorderTraversalRecursive1(root.right);

		// Conquer, preoder sequence: root, left, right
		result.add(root.val);
		// addAll method
		result.addAll(left);
		result.addAll(right);
		return result;
	}
}
