package tree_divideConquer;

public class ConstructBinaryTreeFromPreANDInorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Time Complexity: O(n^2). Worst case occurs when tree is left skewed.
	 * Example Preorder and Inorder traversals for worst case are {A, B, C, D}
	 * and {D, C, B, A}.
	 */
	public static TreeNode buildTree(int[] preorder, int[] inorder) {

		int preStart = 0;
		int preEnd = preorder.length - 1;
		int inStart = 0;
		int inEnd = inorder.length - 1;

		return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
	}

	public static TreeNode construct(int[] preorder, int preStart, int preEnd,
			int[] inorder, int inStart, int inEnd) {
		// 不能省略，否则index will be out of bound
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}
		// can NOT use preorder[0], because, this preStart keep changing
		int val = preorder[preStart];
		TreeNode root = new TreeNode(val);

		// find parent element index from inorder
		int k = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (val == inorder[i]) {
				k = i;
				break;
			}
		}
		// Note:下面所有变量必须用 preStart， preEnd， inStart， inEnd不能用常量，因为一直在变
		root.left = construct(preorder, preStart + 1, preStart + (k - inStart),
				inorder, inStart, k - 1);
		root.right = construct(preorder, preStart + (k - inStart) + 1, preEnd,
				inorder, k + 1, inEnd);
		return root;
	}
}