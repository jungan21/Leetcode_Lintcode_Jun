package TreePaths;

import java.util.ArrayList;
import java.util.List;

/**
 * get all paths which sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * in a straight line down.
 * 
 * 复杂度为O(nlgn)，空间复杂度为O(lgn)
 */
public class PathSumIII {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode right_left = new TreeNode(2);

		root.left = left;
		root.right = right;
		left.left = left_left;
		right.left = right_left;

		System.out.println(binaryTreePathSum2(root, 6));
	}

	/**
	 * http://www.lintcode.com/en/problem/binary-tree-path-sum-ii/
	 * http://www.jiuzhang.com/solutions/binary-tree-path-sum-ii/
	 * http://www.cnblogs.com/grandyang/p/4714640.html
	 * 
	 */
	public static List<List<Integer>> binaryTreePathSum2(TreeNode root,
			int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<Integer>();
		// 0 means level
		dfs(root, target, list, result, 0);
		return result;
	}

	public static void dfs(TreeNode root, int target, List<Integer> list,
			List<List<Integer>> result, int level) {
		if (root == null) {
			return;
		}

		int tmp = target;
		list.add(root.val);
		/**
		 * level from 0 to height of the tree， root node 的level = 0
		 * 
		 * level越来越深时， 从下往上找，譬如main方法里的树， 当，level到节点node 4时， List 里面[1, 2, 4],
		 * target 6 减4， 再减2，等于零
		 * 
		 */
		for (int i = level; i >= 0; i--) {
			tmp -= list.get(i);
			if (tmp == 0) {
				List<Integer> temp = new ArrayList<Integer>();
				for (int j = i; j <= level; ++j)
					temp.add(list.get(j));
				result.add(temp);
				//不能return 
			}
		}
		// level +1, target不变，和 BinaryTreePathSum 的区别在于每一层，每一个点都有可能是起点
		dfs(root.left, target, list, result, level + 1);
		dfs(root.right, target, list, result, level + 1);
		list.remove(list.size() - 1);
	}
}
