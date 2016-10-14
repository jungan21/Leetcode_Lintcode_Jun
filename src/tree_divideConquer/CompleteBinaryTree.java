package tree_divideConquer;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTree {

	/**
	 * http://www.jiuzhang.com/solutions/complete-binary-tree/
	 */
	// helper class
	class Result {
		public int depth;
		public boolean isFull, isComplete;

		Result(int depth, boolean isFull, boolean isComplete) {
			this.depth = depth;
			this.isFull = isFull;
			this.isComplete = isComplete;
		}
	}

	// entry point
	public boolean isComplete(TreeNode root) {
		Result result = helper(root);
		return result.isComplete;
	}

	public Result helper(TreeNode root) {

		if (root == null) {
			return new Result(0, true, true);
		}

		// divide
		Result left = helper(root.left);
		Result right = helper(root.right);

		// conquer

		// left is is NOT even a complete tree, don't need to do other checks
		// 注意：写成 if (!left.isComplete || !right.isComplete) 也可以
		if (!left.isComplete) {
			return new Result(-1, false, false);
		}

		// depth is the same, left should be full and right should be complete
		if (left.depth == right.depth) {
			if (!left.isFull || !right.isComplete) {
				return new Result(-1, false, false);
			}
			// left.depth + 1,表示加上root node,上面divide conquer,
			// 是分左右子树算的，这里返回总的结果，depth需要加上root
			// 注意：自己写的时候，写错了：要用 right.isFull 而不是false
			return new Result(left.depth + 1, right.isFull, true);
		}

		// left.depth = right.depth + 1, left should at most be complete, and
		// right should be
		// full
		if (left.depth == right.depth + 1) {
			if (!left.isComplete || !right.isFull) {
				return new Result(-1, false, false);
			}
			// left.depth + 1,表示加上root node,上面divide conquer,
			// 是分左右子树算的，这里返回总的结果，depth需要加上root
			return new Result(left.depth + 1, false, true);
		}
		// 注意：写的时候注意
		return new Result(-1, false, false);
	}

	/**
	 * https://github.com/shawnfan/LintCode/blob/master/Java/Complete%20Binary%
	 * 20Tree.java
	 * 
	 * http://www.jianshu.com/p/95d36779d754
	 * 
	 * http://ihuafan.com/%E7%AE%97%E6%B3%95/lintcode-binary-tree-summary#
	 * lintcode
	 * -467-complete-binary-tree-%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91
	 */

	public boolean isCompleteBFS(TreeNode root) {
		// Write your code here

		if (root == null) {
			return true;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);

		int nullFlag = 0;
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();

			if (tmp.left != null) {
				q.offer(tmp.left);
				if (nullFlag == 1) {
					return false;
				}
			} else {
				nullFlag = 1;
			}

			if (tmp.right != null) {
				q.offer(tmp.right);
				if (nullFlag == 1) {
					return false;
				}
			} else {
				nullFlag = 1;
			}
		}
		return true;
	}

	/**
	 * http://www.jianshu.com/p/95d36779d754
	 * 
	 * 检查一棵二叉树是不是完全二叉树。完全二叉树是指一棵树除了最后一层，其它层上节点都有左右孩子，最后一层上，所有节点都必须尽量靠左。
	 * 
	 * 对于一棵树，层层遍历，把每层的节点从左向右依此加入Stack，然后把Stack上层的None弹出，
	 * 最后检查如果Stack中还有None说明不是Complete Tree
	 * 
	 * 比如上面的不完全二叉树生成的数组为[1, 2, 3, None, 4, None, None]，将右侧None弹出后为[1, 2, 3,
	 * None, 4]，循环查找，发现还有None存在，所以是不完全二叉树
	 * 
	 */
	public boolean isCompleteLevelOrder(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		boolean hasNull = false;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				if (node == null) {
					hasNull = true;
				} else if (hasNull) {
					return false;
				} else {
					q.offer(node.left);
					q.offer(node.right);
				}
			}
		}
		return true;
	}
}
