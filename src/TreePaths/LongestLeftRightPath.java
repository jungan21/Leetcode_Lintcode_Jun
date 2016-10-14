package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.jianshu.com/p/168b6a1d8959
 * 给一个二叉树，找到一直向左或者一直向右的最长路径的长度，路径可以从任何树内任何一个节点开始。（递归找所有左路径和所有右路径，把最大长度输出）
 * Ex：#代表空节点，最长路径为4-5-6， 长度为2（这种情况要求就是2的
 *
 */
public class LongestLeftRightPath {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(7);
		TreeNode right_left_right = new TreeNode(8);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;

		root.right.left = right_left;
		root.right.right = right_right;
		root.right.left.right = right_left_right;
		longestLeftRightPath(root);
	}

	/**
	 * Wrong method
	 */
	static List<List<Integer>> result = new ArrayList<List<Integer>>();

	public static List<Integer> longestLeftRightPath(TreeNode root) {

		List<Integer> leftList = new ArrayList<Integer>();
		List<Integer> rightList = new ArrayList<Integer>();
		// 0 means depth, root 的depth 为0
		helper(root, false, false, leftList, rightList);
		System.out.println(result);
		return null;
	}

	public static void helper(TreeNode root, boolean isLeftNode,
			boolean isRightNode, List<Integer> leftList, List<Integer> rightList) {
		if (root == null) {
			return;
		}
		leftList.add(root.val);
		// rightList.add(root.val);
		if (isLeftNode && root.left == null) {
			result.add(new ArrayList<Integer>(leftList));
		}

		// if (isRightNode && root.right == null) {
		// result.add(new ArrayList<Integer>(rightList));
		// return;
		// }
		helper(root.left, true, false, leftList, rightList);
		helper(root.right, false, true, leftList, rightList);
		leftList.remove(leftList.size() - 1);

		// if (isRightNode) {
		// rightList.add(root.val);
		// helper(root.right, false, true, leftList, rightList);
		// rightList.remove(rightList.size() - 1);
		// }
	}
}
