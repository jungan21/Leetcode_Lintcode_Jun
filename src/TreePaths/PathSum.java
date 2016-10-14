package TreePaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * 
 */
public class PathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(4);
		TreeNode right = new TreeNode(8);

		TreeNode left_left = new TreeNode(11);
		TreeNode right_left = new TreeNode(13);
		TreeNode right_right = new TreeNode(4);

		TreeNode left_left_left = new TreeNode(7);
		TreeNode left_left_right = new TreeNode(2);
		TreeNode right_right_right = new TreeNode(1);

		root.left = left;
		root.right = right;
		left.left = left_left;
		right.left = right_left;
		right.right = right_right;
		left.left.left = left_left_left;
		left.left.right = left_left_right;
		right.right.right = right_right_right;

		System.out.println(hasPathSum2(root, 22));

	}

	// traverse - recursive
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		sum = sum - root.val;
		if (root.left == null && root.right == null) {
			return sum == 0;
		}
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

	// divide-conquer recrusive
	public boolean hasPathSumDivideConquer(TreeNode root, int sum) {
		if (root == null)
			return false;

		// divide
		boolean left = hasPathSumDivideConquer(root.left, sum - root.val);
		boolean right = hasPathSumDivideConquer(root.right, sum - root.val);

		// conquer
		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				return true;
			}
		}

		return left || right;
	}

	public static boolean hasPathSum1(TreeNode root, int sum) {

		if (root == null) {
			return false;
		} else if (root.val == sum && root.left == null && root.right == null) {
			return true;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		HashMap<TreeNode, Integer> hashmap = new HashMap<TreeNode, Integer>();
		hashmap.put(root, 0);

		List<Integer> result = new ArrayList<Integer>();

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			int val = hashmap.get(node);

			if (node != null) {
				if (node.left == null && node.right == null) {
					val = (val + node.val);
					result.add(val);
				}

				if (node.left != null) {
					stack.push(node.left);
					hashmap.put(node.left, val + node.val);
				}

				if (node.right != null) {
					stack.push(node.right);
					hashmap.put(node.right, val + node.val);
				}
			}
		}

		if (result.contains(sum)) {
			return true;
		}
		return false;
	}

	// Recursive
	// http://www.programcreek.com/2013/01/leetcode-path-sum/
	public static boolean hasPathSum2(TreeNode root, int sum) {
		if (root == null)
			return false;
		// (root.left == null && root.left == null) means we hit leaf node
		if (root.left == null && root.right == null)
			return root.val == sum;

		return hasPathSum1(root.left, sum - root.val)
				|| hasPathSum1(root.right, sum - root.val);
	}

	// Iterative easy to understand
	// http://www.programcreek.com/2013/01/leetcode-path-sum/
	public static boolean hasPathSum3(TreeNode root, int sum) {
		if (root == null)
			return false;

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		LinkedList<Integer> values = new LinkedList<Integer>();

		nodes.add(root);
		values.add(root.val);

		while (!nodes.isEmpty()) {
			TreeNode curr = nodes.poll();
			int sumValue = values.poll();
			// // (root.left == null && root.left == null) means curr is the
			// leaf node
			if (curr.left == null && curr.right == null && sumValue == sum) {
				return true;
			}

			if (curr.left != null) {
				nodes.add(curr.left);
				values.add(sumValue + curr.left.val);
			}

			if (curr.right != null) {
				nodes.add(curr.right);
				values.add(sumValue + curr.right.val);
			}
		}

		return false;
	}

}
