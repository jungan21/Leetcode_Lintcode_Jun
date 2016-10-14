package tree_divideConquer;

public class MinimalHeightTreeFromSortedArray {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		createMinimalHeightTree(arr);
	}

	public static TreeNode createMinimalHeightTree(int arr[]) {
		return addToTree(arr, 0, arr.length - 1);
	}

	public static TreeNode addToTree(int arr[], int start, int end) {

		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = addToTree(arr, start, mid - 1);
		root.right = addToTree(arr, mid + 1, end);
		return root;
	}

}
