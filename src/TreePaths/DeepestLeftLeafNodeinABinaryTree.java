package TreePaths;

import java.util.ArrayList;
import java.util.List;

import TreePaths.DeepestLeafNodeOfBinaryTree.ResultType;

public class DeepestLeftLeafNodeinABinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode left_right_left = new TreeNode(7);

		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		right.left = right_left;
		left.right.left = left_right_left;
		System.out.println(findDeepestLeftLeafNodeWithItsPath(root).val);
		// System.out.println(getDeepestLeftLeafNodeWithItsDepth(root).node.val);
	}

	/**
	 * Best One
	 * 
	 * Deepest left leaf node with its depth
	 */
	public static TreeNode findDeepestLeftLeafNode(TreeNode root) {
		helper(root, false, 0);
		return deepestNode;
	}

	static TreeNode deepestNode;
	static int deepestNodeDepth;

	public static void helper(TreeNode root, boolean isLeftNode, int depth) {
		if (root == null) {
			return;
		}
		if (isLeftNode && depth > deepestNodeDepth) {
			deepestNode = root;
			deepestNodeDepth = depth;
		}
		helper(root.left, true, depth + 1);
		helper(root.right, false, depth + 1);
	}

	/**
	 * Jun's
	 * 
	 * follow up:
	 * 
	 * Deepest left leaf node with its depth and Also its path
	 */
	public static TreeNode findDeepestLeftLeafNodeWithItsPath(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		// 0 means depth, root 的depth 为0
		helper(root, false, 0, list, result);
		System.out.println(result);
		return deepestNode;
	}

	public static void helper(TreeNode root, boolean isLeftNode, int depth,
			List<Integer> list, List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (isLeftNode && depth > deepestNodeDepth && root.left == null
				&& root.right == null) {
			deepestNode = root;
			deepestNodeDepth = depth;
			result.clear();
			result.add(new ArrayList<Integer>(list));
		}
		helper(root.left, true, depth + 1, list, result);
		helper(root.right, false, depth + 1, list, result);
		list.remove(list.size() - 1);
	}

	/**
	 * Another method, can't understand!!!
	 * 
	 * https://www.quora.com/How-can-I-improve-my-Java-solution-for-the-
	 * deepest-left-leaf-node-in-a-binary-tree-problem
	 */
	private static TreeNode getDeepestLeftLeafNodeWithItsDepth1(TreeNode root) {
		ResultType result = FindDeepestLeftChild(root, 0);
		return result != null ? result.node : null;
	}

	private static ResultType FindDeepestLeftChild(TreeNode root, int depth) {
		if (root == null)
			return null;

		ResultType resLeft = FindDeepestLeftChild(root.left, 1 + depth);
		ResultType resRight = FindDeepestLeftChild(root.right, 1 + depth);
		// Check if there is left child and it does not have left children.
		/**
		 * 由于下面的判断， 在最后一层递归调用的时候，resLeft,返回的是整个左子树中，最深的左节点， resRight
		 * 返回的是整个右子树中，最深的左节点，
		 * 
		 */
		if (resLeft == null && root.left != null) {
			// System.out.println(root.left.val);
			resLeft = new ResultType(root.left, depth + 1);
			// System.out.println(root.val);
		}
		if (resRight != null
				&& (resLeft == null || resRight.depth > resLeft.depth)) {
			System.out.println(resRight.node.val);
			return resRight;
		}
		return resLeft;
	}

}
