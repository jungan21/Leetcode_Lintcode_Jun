package a_oa;

/**
 * 这题和LintCode上那道是一样的，唯一的区别就是主函数返回1（是subtree）和-1（不是subtree)。楼主忽略了直接按照返回true/
 * false来做，导致后来debug几次
 *
 */
public class SubTree {

	public static void main(String[] args) {

	}

	// 1 means subTree, -1 means not substree
	// determin is T2 the subtree of T1
	public int isSubTree(TreeNode T1, TreeNode T2) {
		if (T2 == null) {
			return 1;
		}
		if (T1 == null) {
			return -1;
		}

		if (isSameTree(T1, T2) == 1) {
			return 1;
		}

		return (isSubTree(T1.left, T2) == 1 || isSubTree(T1.right, T2) == 1) ? 1
				: -1;
	}

	public int isSameTree(TreeNode T1, TreeNode T2) {
		if (T1 == null && T2 == null) {
			return 1;
		} else if (T1 == null || T2 == null) {
			return -1;
		}

		if (T1.val != T2.val) {
			return -1;
		}

		return (isSameTree(T1.left, T2.left) == 1 && isSameTree(T1.right,
				T2.right) == 1) ? 1 : -1;
	}
}
