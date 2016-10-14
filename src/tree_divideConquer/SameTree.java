package tree_divideConquer;

import java.util.LinkedList;

public class SameTree {

	public static void main(String[] args) {
		LinkedList<TreeNode> right = new LinkedList<TreeNode>();
		right.offer(null);
		System.out.println(right.size());
	}

	/**
	 * http://www.jiuzhang.com/solutions/identical-binary-tree/
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {

		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		}
		if (p != null && q != null) {
			return (p.val == q.val) && isSameTree(p.left, q.left)
					&& isSameTree(p.right, q.right);
		}
		return false;
	}

	// 使用了Queue，有点类似BFS。顺便说下Java里面的Queue真难用，连个empty()都没有，要用LinkedList（继承于Queue）
	public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
		LinkedList<TreeNode> left = new LinkedList<TreeNode>();
		LinkedList<TreeNode> right = new LinkedList<TreeNode>();

		left.offer(p);
		right.offer(q);

		while (left.size() != 0 && right.size() != 0) {
			TreeNode ln = left.poll();
			TreeNode rn = right.poll();
			// Note
			if (ln == null && rn == null)
				continue;
			
			if (ln == null || rn == null)
				return false;
			if (ln.val != rn.val)
				return false;

			left.offer(ln.left);
			left.offer(ln.right);
			right.offer(rn.left);
			right.offer(rn.right);
		}

		if (left.size() != 0 || right.size() != 0)
			return false;

		return true;
	}

}
