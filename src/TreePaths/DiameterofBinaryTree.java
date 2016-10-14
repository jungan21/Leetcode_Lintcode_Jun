package TreePaths;

/**
 * The diameter of a tree is the number of NODES on the longest path between any
 * two leaves in the tree.
 * 
 * The diameter of a tree T is the largest of the following quantities: the
 * diameter of T’s left subtree
 * 
 * the diameter of T’s right subtree
 * 
 * the longest path between leaves that goes through the root of T (this can be
 * computed from the heights of the subtrees of T)
 * 
 * 
 * D(T) = max{ D(T.left), D(T.right), LongestPath(T.root)}
 * 
 * LongestPath(T.root) = 1+height(T.root..left)+height(T.root.right)
 * 
 * height(T) = 1+max{height(T.left) , height(T.right) }
 *
 */
public class DiameterofBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode left_right_left = new TreeNode(7);
		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		left.right.left = left_right_left;

		System.out.println(diameter(root));

	}
	// 计算的节点的个数，而不是边的个数
	public static int diameter(TreeNode root) {
		// D(T) = max{D(T.left), D(T.right), LongestPathThrough(T.root)}
		if (root == null) {
			return 0;
		}

		int leftHeight = maxDepth(root.left);
		int rightHeight = maxDepth(root.right);

		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);
		// leftHeight + rightHeight + 1; +1的意思是加上root node
		return Math.max(Math.max(leftDiameter, rightDiameter), leftHeight
				+ rightHeight + 1);
	}

	// root 的 depth是1
	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);

		return Math.max(leftDepth, rightDepth) + 1;
	}
}
