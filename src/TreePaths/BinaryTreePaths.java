package TreePaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * root to leaf paths
 * Leetcode 257 https://leetcode.com/problems/binary-tree-paths/
 */
// https://leetcode.com/problems/binary-tree-paths/
public class BinaryTreePaths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(7);

		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		root.right.left = right_left;
		root.right.right = right_right;

		System.out.println(binaryTreeRoot2AnyNodePaths(root, right_right));
	}

	public static List<String> binaryTreePathsIterative(TreeNode root) {

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		HashMap<TreeNode, String> hashmap = new HashMap<TreeNode, String>();
		hashmap.put(root, "");

		List<String> result = new ArrayList<>();

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			String path = hashmap.get(node);

			if (node != null) {
				if (node.left == null && node.right == null) {
					path = path + node.val;
					result.add(path);
				}

				if (node.left != null) {
					stack.push(node.left);
					hashmap.put(node.left, path + node.val + "->");
				}

				if (node.right != null) {
					stack.push(node.right);
					hashmap.put(node.right, path + node.val + "->");
				}
			}
		}
		return result;
	}

	/**
	 * 这道题， 不能用StringBuilder 和 StringBuffer
	 */
	public static List<String> binaryTreePathsRecursive(TreeNode root) {
		List<String> result = new ArrayList<String>();
		if (root == null) {
			return result;
		}
		// remember to convert int to String so that we can user "+" to append
		// next value
		helper(root, String.valueOf(root.val), result);
		return result;
	}

	// parameter "path" is used to keep track of the path
	private static void helper(TreeNode root, String path, List<String> result) {
		/**
		 * this return is NOT ending condition for this recursive method: the
		 * reaonse, why this recursive method worn't run forever: we make
		 * recursive within if condition, cone if contions is false, it won't
		 * call itself
		 * 
		 */
		if (root == null) {
			return;
		}
		/**
		 * this return is NOT ending condition for this recursive method it also
		 * works without "return" keywords
		 * 
		 * for recursive method, when this method finishs running, recursive
		 * will also terminate
		 */
		if (root.left == null && root.right == null) {
			result.add(path);
			return;
		}
		// Note: String + int 可以直接返回 String
		if (root.left != null) {
			helper(root.left, path + "->" + root.left.val, result);
		}

		if (root.right != null) {
			helper(root.right, path + "->" + root.right.val, result);
		}
	}

	/**
	 * recommended
	 */
	public static List<String> binaryTreePathsRecursive2(TreeNode root) {
		List<String> result = new LinkedList<String>();
		if (root == null) {
			return result;
		}
		helper1(root, "", result);
		return result;
	}

	public static void helper1(TreeNode root, String path, List<String> result) {
		if (root == null) {
			return;
		}
		path += root.val;
		if (root.left == null && root.right == null) {
			result.add(path);
			return;
		}
		helper1(root.left, path + "->", result);
		helper1(root.right, path + "->", result);
	}

	/**
	 * Jun's root -> all leaf node paths
	 */
	public static List<List<Integer>> binaryTreeRoot2LeafPaths(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<>();
		helperJun(root, list, result);
		return result;
	}

	public static void helperJun(TreeNode root, List<Integer> list,
			List<List<Integer>> result) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(list));
		} else {
			helperJun(root.left, list, result);
			helperJun(root.right, list, result);
		}
		list.remove(list.size() - 1);
	}

	/**
	 * Jun's root -> any node path
	 */
	public static List<List<Integer>> binaryTreeRoot2AnyNodePaths(
			TreeNode root, TreeNode target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<>();
		helperJun(root, list, result, target);
		return result;
	}

	public static void helperJun(TreeNode root, List<Integer> list,
			List<List<Integer>> result, TreeNode target) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root == target) {
			result.add(new ArrayList<Integer>(list));
		} else {
			helperJun(root.left, list, result, target);
			helperJun(root.right, list, result, target);
		}
		list.remove(list.size() - 1);
	}

}
