package tree_divideConquer;

public class ConvertSortedArraytoBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * O(n) 每个数据被访问一遍， O(1)的时间就能找到mid
	 * 
	 * T(n) = 2T(n/2) + C T(n) --> Time taken for an array of size n C -->
	 * Constant (Finding middle of array and linking root to left and right
	 * subtrees take constant time)
	 * 
	 * Note:不过这种方法对于 conver list to BST, 就是O(nlogn)因为，对于List,每次找到mid就需要花费O(N)的时间
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		int length = nums.length;
		return helper(nums, 0, length - 1);
	}

	public TreeNode helper(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		TreeNode left = helper(nums, start, mid - 1);
		TreeNode right = helper(nums, mid + 1, end);
		root.left = left;
		root.right = right;
		return root;
	}
}
