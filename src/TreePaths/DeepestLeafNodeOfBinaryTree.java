package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * 求二叉树中最深的叶子节点，如果该深度有多个叶子节点，则返回最左边的那个。
 *
 */
public class DeepestLeafNodeOfBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode left_right_right = new TreeNode(8);

		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		right.left = right_left;
		left.right.right = left_right_right;
		System.out.println(findDeepestLeafNodeWithItsPath(root));
	}

	public static TreeNode findDeepestNode(TreeNode root) {
		// root's depth is 0
		helper(root, 0);
		System.out.println(deepestNodeDepth);
		return deepestNode;
	}

	static TreeNode deepestNode;
	static int deepestNodeDepth;

	public static void helper(TreeNode root, int depth) {
		if (root == null) {
			return;
		}
		if (depth > deepestNodeDepth) {
			deepestNode = root;
			deepestNodeDepth = depth;
		}
		helper(root.left, depth + 1);
		helper(root.right, depth + 1);
	}

	/**
	 * Jun's
	 * 
	 * follow up:
	 * 
	 * Deepest leaf node with its depth and Also its path
	 */
	public static TreeNode findDeepestLeafNodeWithItsPath(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		// 0 means depth, root 的depth 为0
		helper(root, 0, list, result);
		System.out.println(result);
		System.out.println(deepestNodeDepth);
		return deepestNode;
	}

	public static void helper(TreeNode root, int depth, List<Integer> list,
			List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (depth > deepestNodeDepth && root.left == null && root.right == null) {
			deepestNode = root;
			deepestNodeDepth = depth;
			result.clear();
			result.add(new ArrayList<Integer>(list));
		}
		helper(root.left, depth + 1, list, result);
		helper(root.right, depth + 1, list, result);
		list.remove(list.size() - 1);
	}

	/**
	 * Jun's NOT recommended!!!!
	 */

	static class ResultType {
		TreeNode node;
		int depth;

		public ResultType(TreeNode node, int depth) {
			this.depth = depth;
			this.node = node;
		}
	}

	public static ResultType getDeepestNodeWithItsDepth(TreeNode root) {
		ResultType result = helper(root);
		return result;
	}

	public static ResultType helper(TreeNode root) {
		if (root == null) {
			// 必须是0， 因为null node的高度是0
			return new ResultType(null, 0);
		}

		ResultType leftResult = helper(root.left);
		ResultType rightResult = helper(root.right);

		ResultType result = null;
		// +1 表示加上 root node的高度, >=, 表示，如果有多nodes with 都在depth那层, 返回最左边的那个node,
		// (对于本体，5， 6 depth相同，不过返回5)
		if (leftResult.depth >= rightResult.depth) {
			result = new ResultType(leftResult.node, leftResult.depth + 1);
		} else {
			result = new ResultType(rightResult.node, rightResult.depth + 1);
		}
		// 不能省略，只有这样，才能找到那个node, 为空，表示left, right,没有找到，那么node就是root;
		if (result.node == null) {
			result.node = root;
		}
		return result;
	}

	/**
	 * Find deepest node(s) of a binary tree (multipe nodes),上面都是放回一个node就好
	 * 
	 * http://www.dsalgo.com/2013/03/find-deepest-nodes-of-binary-tree.html
	 * 
	 * We do a preorder traversal of the tree. While calling the recursive
	 * function we pass an array containing level and list of nodes. During the
	 * preorder traversal, this same array is always passed. So the elements of
	 * this array work as global variables. Whenever we call the recursive
	 * function to a node's children we increase the level by 1. At each node if
	 * the level value in the array is less than the current level value then we
	 * clear the previous node list, as we found a deeper level. If the level
	 * value is equal then we keep on adding the current node in the list.
	 */

	private static List<TreeNode> findDeepestNodes(TreeNode root) {
		Object[] levelNodes = new Object[2];
		levelNodes[0] = 0;
		levelNodes[1] = new ArrayList<TreeNode>();
		findDeepestNodes(root, 1, levelNodes);
		return (List<TreeNode>) levelNodes[1];
	}

	private static void findDeepestNodes(TreeNode root, int level,
			Object[] levelNodes) {
		if (root == null)
			return;
		if ((Integer) levelNodes[0] <= level) {
			if ((Integer) levelNodes[0] < level)
				((List) levelNodes[1]).clear();
			levelNodes[0] = level;
			((List) levelNodes[1]).add(root);
		}
		findDeepestNodes(root.left, level + 1, levelNodes);
		findDeepestNodes(root.right, level + 1, levelNodes);
	}

}
