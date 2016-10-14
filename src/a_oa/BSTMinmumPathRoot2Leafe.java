package a_oa;

/**
 * 跟BST没啥关系，不要看到BST就以为是最左边的路径之和（左边路径可以很长，右边路径可以很短），用递归做很简单
 * 
 * 另一题是BST找最小path，这题略坑，他妈居然有null node还能接null left和null
 * right的你敢信？我三行写完发觉居然没过二号test case，仔细一看树的print结构，发现了这个诡异的设计。于是只能null return 0;
 * left!=null&&right==null，走左；反之走右;else 一起走。。。然后就对了
 *
 */
public class BSTMinmumPathRoot2Leafe {

	public int helper1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left != null && root.right == null) {
			return helper1(root.left) + root.val;
		}
		if (root.left == null && root.right != null) {
			return helper1(root.right) + root.val;
		}
		return Math.min(helper1(root.left), helper1(root.right)) + root.val;
	}

	// recommend
	public static int minumumPathSumRoot2Leaf(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root, 0);
		return minSum;
	}

	static int minSum = Integer.MAX_VALUE;

	public static void helper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum = sum + root.val;
		if (root.left == null && root.right == null && sum < minSum) {
			minSum = sum;
		}
		helper(root.left, sum);
		helper(root.right, sum);
	}
}
