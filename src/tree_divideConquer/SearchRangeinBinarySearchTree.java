package tree_divideConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary
 * Search Tree. Find all the keys of tree in range k1 to k2. i.e. print all x
 * such that k1<=x<=k2 and x is a key of given BST. Return all the keys in
 * ascending order.
 * 
 * If k1 = 10 and k2 = 22, then your function should return [12, 20, 22]
 * 
 * 如果用inorder traverse一遍，然后在加root.val前判断一下这个val是不是在range里，这种方法的复杂度是O(n)
 *
 * 而本题recursion方法时间复杂度是 O(k2-k1),
 * 因为所有数值不在这个范围区间内的节点都没有访问,只有在极端情况下也有这两个时间复杂度才为O(n) 即：k2为largetst, k1为smallest
 */
public class SearchRangeinBinarySearchTree {

	public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		helper(root, k1, k2, result);
		return result;
	}

	/**
	 * 递归inorder traverse
	 */
	public void helper(TreeNode root, int k1, int k2, List<Integer> result) {
		if (root == null) {
			return;
		}
		/**
		 * root.val > k1: means we have valid number in left tree, so we need to
		 * start from left,
		 * 
		 * 下面的顺序不能改变，相当于 left, root, right 顺序，因为答案要求结果按照 ascending order
		 * 
		 */

		// 只要root.val > k1,就表示 左边有想要的结果
		if (root.val > k1) {
			helper(root.left, k1, k2, result);
		}
		// 接下来考虑 root
		if (k1 <= root.val && root.val <= k2) {
			result.add(root.val);
		}
		// 接下来考虑，右子树的可能
		if (root.val < k2) {
			helper(root.right, k1, k2, result);
		}
	}

	public ArrayList<Integer> searchRange1(TreeNode root, int k1, int k2) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<>();
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (cur.val >= k1 && cur.val <= k2) {
				result.add(cur.val);
			}
			cur = cur.right;
		}
		return result;
	}
}