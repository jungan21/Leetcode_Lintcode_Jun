package TreePaths;

import java.util.ArrayList;

public class SumRoottoLeafNumbers {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		root.left = n1;
		root.right = n2;

		System.out.println(sumNumbers(root));

	}

	// method 1: http://www.jiuzhang.com/solutions/sum-root-to-leaf-numbers/
	public static int sumNumbers(TreeNode root) {
		return dfs(root, 0);
	}

	/**
	 * 由根节点往叶节点方向走，就是从高位往地位加和的方向。也就是说，当遍历的节点往叶节点方向走一层的时候，该节点的值应为父节点的值*10+
	 * 当前节点的值。
	 */
	public static int dfs(TreeNode root, int prev) {
		// this is not ending point of recursion, just a condition to make sure
		// root is NOT null
		if (root == null) {
			return 0;
		}
		int sum = root.val + prev * 10;
		if (root.left == null && root.right == null) {
			// sum is root
			return sum;
		}
		return dfs(root.left, sum) + dfs(root.right, sum);
	}

	/**
	 * method 2 Jun
	 */

	public int sumNumbersJun(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return helper(root, root.val);
	}

	public static int helper(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return sum;
		}
		if (root.left != null && root.right != null) {
			return dfs(root.left, sum * 10 + root.left.val)
					+ dfs(root.right, sum * 10 + root.right.val);
		} else if (root.left != null) {
			return dfs(root.left, sum * 10 + root.left.val);
		} else if (root.right != null) {
			return dfs(root.right, sum * 10 + root.right.val);
		}
		return 0;
	}

	public static int sumNumbersTest(TreeNode root) {
		int result = 0;
		if (root == null)
			return result;

		ArrayList<ArrayList<TreeNode>> all = new ArrayList<ArrayList<TreeNode>>();
		ArrayList<TreeNode> l = new ArrayList<TreeNode>();
		// l.add(root);
		dfs3(root, l, all);

		for (ArrayList<TreeNode> a : all) {
			StringBuilder sb = new StringBuilder();
			for (TreeNode n : a) {
				sb.append(String.valueOf(n.val));
			}
			int currValue = Integer.valueOf(sb.toString());
			result = result + currValue;
		}

		return result;

	}

	public static void dfs3(TreeNode n, ArrayList<TreeNode> l,
			ArrayList<ArrayList<TreeNode>> all) {
		if (n == null) {
			return;
		}
		if (n.left == null && n.right == null) {
			ArrayList<TreeNode> t = new ArrayList<TreeNode>();
			l.add(n);
			t.addAll(l);
			all.add(t);
			l.remove(l.size() - 1);
		}

		l.add(n);
		dfs3(n.left, l, all);
		dfs3(n.right, l, all);
		l.remove(l.size() - 1);

	}

}
