package tree_divideConquer;

import java.util.HashSet;

/**
 * check whether all leaves are at same level
 *
 */
public class LeavesAtSameLevel {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(12);
		TreeNode left = new TreeNode(5);
		TreeNode left_left = new TreeNode(3);
		TreeNode right = new TreeNode(7);
		TreeNode right_right = new TreeNode(1);
		root.left = left;
		root.left.left = left_left;
		root.right = right;
		// root.right.right = right_right;
		System.out.println(sameLevel(root));
		System.out.println(sameLevel1(root));

	}

	/**
	 * method 1
	 */
	public static boolean sameLevel(TreeNode root) {
		if (root == null) {
			return false;
		}
		helper(root, 0);
		return set.size() == 1;
	}

	static HashSet<Integer> set = new HashSet<>();

	public static void helper(TreeNode root, int level) {
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			set.add(level);
		}
		helper(root.left, level + 1);
		helper(root.right, level + 1);
	}

	/**
	 * method 2: find the level of the leftmost leaf, then compare all of the
	 * rest with this one
	 */
	static int leftMostLeafLevel = 0;
	static boolean flag = true;

	public static boolean sameLevel1(TreeNode root) {
		if (root == null) {
			return false;
		}
		TreeNode now = root;
		while (now.left != null) {
			leftMostLeafLevel++;
			now = now.left;
		}
		helper1(root, 0);
		return flag;
	}

	public static void helper1(TreeNode root, int level) {
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			if (level != leftMostLeafLevel) {
				flag = false;
			}
		}
		helper1(root.left, level + 1);
		helper1(root.right, level + 1);
	}

}
