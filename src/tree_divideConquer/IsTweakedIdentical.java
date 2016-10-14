package tree_divideConquer;

/**
 * http://www.lintcode.com/en/problem/tweaked-identical-binary-tree/
 *
 * http://www.jiuzhang.com/solutions/tweaked-identical-binary-tree/
 */
public class IsTweakedIdentical {

	public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		}

		if (a == null || b == null) {
			return false;
		}

		if (a.val != b.val) {
			return false;
		}

		if (isTweakedIdentical(a.left, b.left)
				&& isTweakedIdentical(a.right, b.right)) {
			return true;
		}

		if (isTweakedIdentical(a.left, b.right)
				&& isTweakedIdentical(a.right, b.left)) {
			return true;
		}
		return false;

		// return (isTweakedIdentical(a.left, b.left) || isTweakedIdentical(
		// a.left, b.right))
		// && (isTweakedIdentical(a.right, b.right) || isTweakedIdentical(
		// a.right, b.left));
	}
}
