package TreePaths;

/**
 * shortest Distance between two nodes is the minimum number of edges to be
 * traversed to reach one node from other. Note that, any two node in the tree
 * must have a common ancestor. So, we can compute Lowest Common Ancestor (LCA)
 * for the given two nodes and then we can find distance between two nodes as
 * follows –
 * 
 *
 */
public class ShortestDistanceBetweenTwoNodes {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);

		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		System.out.println(shortestDistance(root, root.left.left,
				root.left.right));

	}

	// 节点A 到 节点B经过的边的个数
	public static int shortestDistance(TreeNode root, TreeNode A, TreeNode B) {
		if (root == null) {
			return 0;
		}

		TreeNode lca = LCA(root, A, B);
		// d(A,B) = d(root,A) + d(root, B) - 2*d(root, lca)
		// 0 means level, root的level是0
		int A_Level = getLevel(root, 1, A);
		int B_Level = getLevel(root, 1, B);
		int lca_Level = getLevel(root, 1, lca);
		getLevelJun(root, 1, A, B, lca);
		System.out.println(A_level);
		System.out.println(B_level);
		System.out.println(C_level);

		return A_Level + B_Level - 2 * lca_Level;
	}

	static int A_level = 0;
	static int B_level = 0;
	static int C_level = 0;

	public static void getLevelJun(TreeNode root, int level, TreeNode A,
			TreeNode B, TreeNode LCA) {
		// root的level 是0
		if (root == null) {
			return;
		}

		if (root == A) {
			A_level = level;
		} else if (root == B) {
			B_level = level;

		} else if (root == LCA) {
			C_level = level;
		}

		getLevelJun(root.left, level + 1, A, B, LCA);
		getLevelJun(root.right, level + 1, A, B, LCA);
	}

	// http://www.geeksforgeeks.org/get-level-of-a-node-in-a-binary-tree/
	public static int getLevel(TreeNode root, int count, TreeNode node) {
		// root的level 是0
		if (root == null) {
			return 0;
		}

		if (root == node) {
			return count;
		}

		int leftLevel = getLevel(root.left, count + 1, node);
		if (leftLevel != 0) {
			return leftLevel;
		}
		int rightLevel = getLevel(root.right, count + 1, node);
		return rightLevel;
	}

	public static TreeNode LCA(TreeNode root, TreeNode A, TreeNode B) {
		if (root == null)
			return null;
		if (root == A || root == B)
			return root;

		TreeNode left = LCA(root.left, A, B);
		TreeNode right = LCA(root.right, A, B);

		// A is in one subtree and and B is on other subtree of root
		if (left != null && right != null) {
			return root;
		}
		// either A or B is present in one of the subtrees of root or none
		// present in either side of the root
		// means we found LCA in left side
		if (left != null) {
			return left;
		}
		// means we found LCA in right side
		if (right != null) {
			return right;
		}
		// A, B nodes don't exist in this trees
		return null;
	}

}
