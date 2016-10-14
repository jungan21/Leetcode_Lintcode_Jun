package tree_divideConquer;

import java.util.Stack;

public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Double.NEGATIVE_INFINITY);
		System.out.println(Double.MIN_VALUE);
		TreeNode root = new TreeNode(1);

		TreeNode right = new TreeNode(2);

		TreeNode right_left = new TreeNode(3);

		root.right = right;
		root.right.left = right_left;
		System.out.println(isValidBST2(root));

	}

	/**
	 * Version 1: http://www.jiuzhang.com/solutions/validate-binary-search-tree/
	 * 
	 * Traverse - Recursive
	 */
	static private TreeNode preNode = null;

	public static boolean isValidBSTTraverseJun(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (!isValidBSTTraverseJun(root.left)) {
			return false;
		}
		if (preNode != null && preNode.val >= root.val) {
			return false;
		}
		preNode = root;
		if (!isValidBSTTraverseJun(root.right)) {
			return false;
		}
		return true;
	}

	private boolean isFirstNode = true;

	public boolean isValidBSTTraverseJiuzhang(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (!isValidBSTTraverseJiuzhang(root.left)) {
			return false;
		}
		if (!isFirstNode && preNode.val >= root.val) {
			return false;
		}
		isFirstNode = false;
		preNode = root;
		if (!isValidBSTTraverseJiuzhang(root.right)) {
			return false;
		}
		return true;
	}

	/**
	 * Version 2: http://www.jiuzhang.com/solutions/validate-binary-search-tree/
	 * 
	 * Divide Conquer - Recursive (recommend by teacher)
	 */

	public boolean isValidBSTDivideConquer(TreeNode root) {
		Result result = helper(root);
		return result.isBST;
	}

	class Result {
		boolean isBST;
		int min;
		int max;

		Result(boolean isBST, int min, int max) {
			this.isBST = isBST;
			this.min = min;
			this.max = max;
		}
	}

	private Result helper(TreeNode root) {
		if (root == null) {
			// 1. you must use Integer.MIN_VALUE, can NOT use 0 here
			// 2. 必须把 Integer.MAX_VALUE 赋值给 Result的min, Integer.MIN_VALUE
			// 赋值给Rest的max, 反过来 赋值
			return new Result(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}

		Result left = helper(root.left);
		Result right = helper(root.right);

		if (!left.isBST || !right.isBST) {
			// if is_bst is false then minValue and maxValue are useless
			return new Result(false, 0, 0);
		}

		// Note: root.left,  root.right
		if ((root.left != null && left.max >= root.val)
				|| (root.right != null && right.min <= root.val)) {
			return new Result(false, 0, 0);
		}
		// 按result里的的min, max定义顺序赋值
		return new Result(true, Math.min(root.val, left.min), Math.max(
				root.val, right.max));
	}

	/**
	 * jiuzhang Inorder traverse 因为如果是BST的话，中序遍历数一定是单调递增的，如果违反了这个规律，就返回false
	 * 
	 */
	public boolean isValidBSTNonRecursive1(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		TreeNode pre = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (pre != null && cur.val <= pre.val) {
				return false;
			}
			pre = cur;
			cur = cur.right;
		}
		return true;
	}

	/**
	 * Jun's inorder, 因为如果是BST的话，中序遍历数一定是单调递增的，如果违反了这个规律，就返回false
	 * 
	 */
	public boolean isValidBSTNonRecursive2(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode pre = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peek();
			if (cur.left != null) {
				stack.push(cur.left);
				cur.left = null;
			} else {
				// 传统的inorder遍历，需要将cur加入，result list, 这里不需要，只要pre,
				// cur两个变量模拟一下，保证当前遍历的元素一定要比pre大，否则违反BST规则
				if (pre != null && cur.val <= pre.val) {
					return false;
				} else {
					pre = cur;
				}
				stack.pop();
				if (cur.right != null) {
					stack.push(cur.right);
				}
			}
		}
		return true;
	}

	/**
	 * recursive in order
	 */

	TreeNode pre = null;
	boolean result = true;

	public boolean isValidBSTJunRecursive(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		inorderRecurisve(root);
		return result;
	}

	public void inorderRecurisve(TreeNode root) {
		if (root == null)
			return;

		inorderRecurisve(root.left);

		if (pre != null && root.val <= pre.val) {
			result = false;
			return;
		}
		pre = root;
		inorderRecurisve(root.right);
	}

	/**
	 * easy to remember
	 */

	public static boolean isValidBST2(TreeNode root) {
		// you must use double here
		return isValidBST(root, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		// return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean isValidBST(TreeNode p, double min, double max) {
		if (p == null)
			return true;
		// reason for using double: what if tree only have 1 root node with
		// value
		// Integer.MAX_VALUE

		// 不能加上下面的话，不对
		// if (p.left == null && p.right == null){
		// return true;
		// }

		if (p.val <= min || p.val >= max)
			return false;

		return isValidBST(p.left, min, p.val)
				&& isValidBST(p.right, p.val, max);
	}

	/**
	 * wrong method !!!
	 * 
	 * 不能保证 右字树里所有的node都比root 大，也不能保证左子树里所有的都比root小，正能保证局部的小子树符合要求
	 * 
	 */
	public static boolean isValidBSTJun(TreeNode root) {
		if (root == null) {
			return true;
		}

		if (root.left == null && root.right == null) {
			return true;
		} else if (root.left == null) {
			if (root.val >= root.right.val) {
				return false;
			} else {
				return isValidBSTJun(root.right);
			}
		} else if (root.right == null) {
			if (root.val <= root.left.val) {
				return false;
			} else {
				return isValidBSTJun(root.left);
			}
		} else {
			if (root.left.val >= root.val || root.right.val <= root.val) {
				return false;
			}
			return isValidBSTJun(root.left) && isValidBSTJun(root.right);
		}
	}

}
