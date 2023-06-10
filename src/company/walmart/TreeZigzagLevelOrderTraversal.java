package company.walmart;

import tree_divideConquer.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 *
 */
public class TreeZigzagLevelOrderTraversal {

	public static void main(String[] args) {
		tree_divideConquer.TreeNode root = new tree_divideConquer.TreeNode(1);
		tree_divideConquer.TreeNode left = new tree_divideConquer.TreeNode(2);
		tree_divideConquer.TreeNode right = new tree_divideConquer.TreeNode(3);
		tree_divideConquer.TreeNode left_left = new tree_divideConquer.TreeNode(4);
		tree_divideConquer.TreeNode left_right = new tree_divideConquer.TreeNode(5);
		tree_divideConquer.TreeNode right_left = new tree_divideConquer.TreeNode(6);
		tree_divideConquer.TreeNode right_right = new tree_divideConquer.TreeNode(7);
		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		right.left = right_left;
		right.right = right_right;

		System.out.println(zigzagLevelOrder(root));

	}

	/**
	 * Best
	 */

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(tree_divideConquer.TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return result;
		}

		Stack<tree_divideConquer.TreeNode> currentLevel = new Stack<tree_divideConquer.TreeNode>();
		Stack<tree_divideConquer.TreeNode> nextLevel = new Stack<tree_divideConquer.TreeNode>();

		currentLevel.add(root);

		boolean normalOrder = true;

		while (!currentLevel.isEmpty()) {
			// 每一层的结果
			ArrayList<Integer> levelList = new ArrayList<Integer>();
			int size = currentLevel.size();
			for (int i = 0; i < size; i++) {
				tree_divideConquer.TreeNode node = currentLevel.pop();
				levelList.add(node.val);
				/**
				 * normalOrder表示当前层是不是normal order(i.e. left --> right child),
				 * 如果当前层是 normal order, 则下一层就不是，所以下一层就from right->left
				 */
				if (normalOrder) {
					if (node.left != null) {
						nextLevel.push(node.left);
					}
					if (node.right != null) {
						nextLevel.push(node.right);
					}
				} else {
					if (node.right != null) {
						nextLevel.push(node.right);
					}
					if (node.left != null) {
						nextLevel.push(node.left);
					}

				}
			}
			result.add(levelList);
			currentLevel = nextLevel;
			nextLevel = new Stack<tree_divideConquer.TreeNode>();
			normalOrder = !normalOrder;
		}

		/**
		 * as a follow up, if they ask you to do do level order from left to
		 * root, just do: Collections.reverse(result);
		 */
		return result;
	}

	/**
	 * 
	 * http://www.jiuzhang.com/solutions/binary-tree-zigzag-level-order-
	 * traversal/
	 * 
	 * 
	 */
	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder2(tree_divideConquer.TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> currLevelResult = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}

		Stack<tree_divideConquer.TreeNode> currLevel = new Stack<tree_divideConquer.TreeNode>();
		Stack<tree_divideConquer.TreeNode> nextLevel = new Stack<tree_divideConquer.TreeNode>();
		// Stack<TreeNode> tmp;

		currLevel.push(root);
		boolean normalOrder = true;

		while (!currLevel.isEmpty()) {

			while (!currLevel.isEmpty()) {
				tree_divideConquer.TreeNode node = currLevel.pop();
				currLevelResult.add(node.val);

				if (normalOrder) {
					if (node.left != null) {
						nextLevel.push(node.left);
					}
					if (node.right != null) {
						nextLevel.push(node.right);
					}
				} else {
					if (node.right != null) {
						nextLevel.push(node.right);
					}
					if (node.left != null) {
						nextLevel.push(node.left);
					}
				}
			}

			result.add(currLevelResult);
			// tmp is just used to swap
			// tmp = currLevel;
			currLevelResult = new ArrayList<Integer>();
			currLevel = nextLevel;
			nextLevel = new Stack<TreeNode>();
			normalOrder = !normalOrder;
		}

		return result;

	}

}
