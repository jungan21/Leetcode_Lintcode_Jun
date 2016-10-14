package tree_divideConquer;

public class OddMinusEvenLevelSum {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);

		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(7);

		root.left = left;
		root.right = right;

		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;
		System.out.println(sumDiffOddEvenLevel(root));
	}

	static int sumOdd = 0;
	static int sumEven = 0;

	public static int sumDiffOddEvenLevel(TreeNode root) {
		helper(root, 0);
		return sumOdd - sumEven;
	}

	public static void helper(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (level % 2 == 0) {
			sumEven += root.val;
		} else {
			sumOdd += root.val;
		}
		helper(root.left, level + 1);
		helper(root.right, level + 1);
	}
}
