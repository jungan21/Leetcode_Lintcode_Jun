package a_oa;

public class TreeAmplitude {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(6);
		TreeNode left = new TreeNode(8);
		TreeNode right = new TreeNode(9);
		TreeNode left_left = new TreeNode(12);
		TreeNode left_right = new TreeNode(2);
		TreeNode right_left = new TreeNode(1);
		TreeNode right_right = new TreeNode(4);
		TreeNode right_left_left = new TreeNode(2);
		TreeNode right_right_left = new TreeNode(5);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;

		root.right.left = right_left;
		root.right.right = right_right;
		root.right.left.left = right_left_left;
		root.right.right.left = right_right_left;
		System.out.println(Solution(root));
		System.out.println(Solution(root));
	}

	/**
	 * Given a tree of N nodes, return the amplitude of the tree
	 * 
	 * The amplitude of path P is the maximum difference among values of nodes
	 * on path P
	 * 
	 * 其实就是在所有root 到leaf的path中，最大值的点 - 最小值的点
	 * 
	 * 譬如一条root -> leaf的path: 5, 9, 1, 2, 答案是 9 -1 = 8
	 * 
	 */
	// http://siyang2notleetcode.blogspot.ca/2015/02/amplitude-of-tree.html

	/**
	 * http://wdxtub.com/interview/14520850399861.html
	 * 
	 * http://codingmelon.com/2015/12/11/amplitude-of-tree/
	 * 
	 * http://siyang2notleetcode.blogspot.ca/2015/02/amplitude-of-tree.html
	 */
	public static int Solution(TreeNode root) {
		if (root == null)
			return 0;
		return helper(root, root.val, root.val);
	}

	private static int helper(TreeNode root, int min, int max) {
		if (root == null)
			return max - min;

		min = Math.min(min, root.val);
		max = Math.max(max, root.val);

		return Math.max(helper(root.left, min, max),
				helper(root.right, min, max));
	}

}
