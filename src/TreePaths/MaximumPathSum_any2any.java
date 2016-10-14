package TreePaths;


/**
 * any node ==> any node
 * 
 * The path may start and end at any node in the tree.
 * 
 * jiuzhang: http://www.jiuzhang.com/solutions/binary-tree-maximum-path-sum/
 */

// help result type class
class Result {
	// root2Any: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
	// any2Any: 从树中任意到任意点的最大路径，这条路径至少包含一个点
	int root2AnyMax;
	int any2AnyMax;

	Result(int root2AnyMax, int any2AnyMax) {
		this.root2AnyMax = root2AnyMax;
		this.any2AnyMax = any2AnyMax;
	}
}
//http://www.zrzahid.com/binary-tree-all-paths-max-sum-path-diameter-longest-path-shortest-path-closest-leaf/
public class MaximumPathSum_any2any {

	/**
	 * jiuzhang (from teacher in class)
	 * 
	 */

	private Result helper(TreeNode root) {
		// illegal
		if (root == null) {
			// can NOT use 0 here, must use MIN_VALUE, we are from any2any node,
			// negative is possible
			// return new Result(0, 0) doesn't work
			return new Result(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}

		// Divide
		Result left = helper(root.left);
		Result right = helper(root.right);

		// Conquer
		/**
		 * 计算root2AnyMax不可以写成下面的方式， 逻辑上是对的，不过由于我们定义int类型，容易over flow,
		 * 假设如果root.val是个负数，再加上Integer.MIN_VALUE，就会overflow,
		 * 如果把定义改成long类型，就可以这样写了，下面的处理方式就很好，如果是负数，就和0比较，这样就防止了over flow
		 * 
		 * Math.max(root.val,root.val+Math.max(left.root2AnyMax,right.
		 * root2AnyMax));
		 */

		int root2AnyMax = root.val
				+ Math.max(0, Math.max(left.root2AnyMax, right.root2AnyMax));

		// left.any2AnyMax means the maxPath in left subtree;
		// right.any2AnyMax means the maxPath in the right subtree;
		// 这种情况，don't cross root node
		int any2AnyMax = Math.max(left.any2AnyMax, right.any2AnyMax);

		// 这种情况 any2AnyMax cross root node
		// 注意： 不能写成 Math.max(any2Any, root.val + Math.max(0,
		// Math.max(left.root2Any, right.root2Any)));
		any2AnyMax = Math.max(
				any2AnyMax,
				root.val + Math.max(0, left.root2AnyMax)
						+ Math.max(0, right.root2AnyMax));

		return new Result(root2AnyMax, any2AnyMax);
	}

	public int maxPathSum(TreeNode root) {
		Result result = helper(root);
		return result.any2AnyMax;
	}

	/**
	 * http://www.programcreek.com/2013/02/leetcode-binary-tree-maximum-path-sum
	 * -java/
	 * 
	 * 1) Recursively solve this problem
	 * 
	 * 2) Get largest left sum and right sum
	 * 
	 * 2) Compare to the stored maximum
	 */
	public int maxPathSum2(TreeNode root) {
		int max[] = new int[1];
		max[0] = Integer.MIN_VALUE;
		calculateSum(root, max);
		return max[0];
	}

	public int calculateSum(TreeNode root, int[] max) {
		if (root == null)
			return 0;

		int left = calculateSum(root.left, max);
		int right = calculateSum(root.right, max);

		int current = Math.max(root.val,
				Math.max(root.val + left, root.val + right));

		max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

		return current;
	}

}
