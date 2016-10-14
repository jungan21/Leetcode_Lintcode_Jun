package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个二叉树, 找到最长的path. 如果最长不只一个, 返回字典序第一个最长.
 *
 */
public class LongestPathAny2Any {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(4);
		TreeNode right = new TreeNode(8);

		TreeNode left_left = new TreeNode(11);
		TreeNode left_right = new TreeNode(12);
		TreeNode left_right_right = new TreeNode(10);
		TreeNode right_left = new TreeNode(13);
		TreeNode right_right = new TreeNode(4);

		TreeNode left_left_left = new TreeNode(7);
		TreeNode left_left_right = new TreeNode(2);
		TreeNode right_right_left = new TreeNode(5);
		TreeNode right_right_right = new TreeNode(1);

		root.left = left;
		// root.right = right;
		left.left = left_left;
		left.right = left_right;
		left.right.right = left_right_right;
		right.left = right_left;
		right.right = right_right;
		left.left.left = left_left_left;
		left.left.right = left_left_right;
		right.right.left = right_right_left;
		right.right.right = right_right_right;

		System.out.println(longestPathAny2Any(root));
		longestPath(root);
		for (TreeNode node : path) {
			System.out.print(node.val + ",  ");
		}

	}

	/**
	 * 我们自底向上,对树进行递归, 把path 放到一个stack里, 然后如果找到了一个更长的path(stack的size) 就更新一下.
	 * 因为最长的path不一定在root的同一侧, 所以最后我们还需要比较一下当前节点左右哪边有最长path.
	 * 
	 * 输出： 2, 11, 4, 5, 1, 4, 8,
	 * 
	 * left: 2, 11, 4 root: 5, right: 1, 4, 8
	 * 
	 * 由于 divide conquer, 从底往上， 2, 和1是叶子节点
	 */
	static int max = 0;
	static List<TreeNode> path = null;

	public static List<TreeNode> longestPath(TreeNode root) {
		if (root == null)
			return new ArrayList<TreeNode>();
		List<TreeNode> left = longestPath(root.left);
		List<TreeNode> right = longestPath(root.right);
		if (left.size() + right.size() > max) {
			max = left.size() + right.size();
			List<TreeNode> tmp = new ArrayList<>();
			tmp.addAll(left);
			tmp.add(root);
			tmp.addAll(right);
			path = tmp;
		}
		left.add(root);
		right.add(root);
		return left.size() > right.size() ? left : right;
	}

	static List<TreeNode> leftPath = null;
	static List<TreeNode> rightPath = null;

	// public static List<TreeNode> longestPathJun(TreeNode root) {
	// List<TreeNode> result = new ArrayList<TreeNode>();
	// if (root == null)
	// return result;
	// helperJun(root.left, leftPath);
	// helperJun(root.right, rightPath);
	//
	// }

	public static void helperJun(TreeNode root, List<TreeNode> list) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {

		}

		helperJun(root.left, list);
		helperJun(root.right, list);

	}

	/**
	 * method 2, can only return the length, can't get the path
	 */
	public static int longestPathAny2Any(TreeNode root) {
		Result result = helper(root);
		return result.any2AnyMax;
	}

	private static Result helper(TreeNode root) {
		if (root == null) {
			// can NOT use 0 here, must use MIN_VALUE, we are from any2any node,
			// negative is possible
			// return new Result(0, 0) doesn't work
			return new Result(0, 0);
		}

		// Divide
		Result left = helper(root.left);
		Result right = helper(root.right);

		// Conquer
		int root2AnyMax = 1 + Math.max(left.root2AnyMax, right.root2AnyMax);

		int any2AnyMax = Math.max(left.any2AnyMax, right.any2AnyMax);

		any2AnyMax = Math.max(any2AnyMax, 1 + left.root2AnyMax
				+ right.root2AnyMax);

		return new Result(root2AnyMax, any2AnyMax);
	}
}
