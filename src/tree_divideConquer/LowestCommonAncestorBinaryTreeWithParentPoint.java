package tree_divideConquer;

import java.util.ArrayList;

public class LowestCommonAncestorBinaryTreeWithParentPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParentTreeNode root = new ParentTreeNode(1);

		ParentTreeNode right = new ParentTreeNode(2);

		// ParentTreeNode left_right = new ParentTreeNode(-100);

		// ParentTreeNode right_left = new ParentTreeNode(-100);
		ParentTreeNode right_right = new ParentTreeNode(3);

		ParentTreeNode right_right_right = new ParentTreeNode(4);

		ParentTreeNode right_right_right_right = new ParentTreeNode(5);
		// ParentTreeNode right_right_left_right = new ParentTreeNode(-22);

		// ParentTreeNode right_right_left_right_right = new ParentTreeNode(8);

		root.right = right;
		right.parent = root;
		right.right = right_right;
		right.right.parent = right;

		right.right.right = right_right_right;
		right.right.right.parent = right.right;

		right.right.right.right = right_right_right_right;
		right.right.right.right.parent = right.right.right;
		System.out.println("test");
		System.out.println(lowestCommonAncestor(root, right.right,
				right_right_right_right).val);

	}

	public static ParentTreeNode lowestCommonAncestor(ParentTreeNode root,
			ParentTreeNode node1, ParentTreeNode node2) {
		ArrayList<ParentTreeNode> list1 = getPath2Root(node1);
		ArrayList<ParentTreeNode> list2 = getPath2Root(node2);
		ParentTreeNode lca = null;
		int i = list1.size() - 1;
		int j = list2.size() - 1;
		while (i >= 0 && j >= 0) {
			if (list1.get(i) == list2.get(j)) {
				lca = list1.get(i);
				i--;
				j--;
			} else {
				break;
			}
		}
		return lca;
		/**
		 * you can NOT return null here;
		 * 
		 * e.g {1} 1, 1 means you only have one root node (1), look for 1, and 1
		 * 的LCA, 除了上面的循环i=-1
		 * 
		 * so, below condition is used to deal with situation when node1=node2,
		 * the LCA is just theirself
		 */
		// return list1.get(i + 1);
	}

	private static ArrayList<ParentTreeNode> getPath2Root(ParentTreeNode node) {
		ArrayList<ParentTreeNode> list = new ArrayList<ParentTreeNode>();
		while (node != null) {
			list.add(node);
			node = node.parent;
		}
		return list;
	}

	/**
	 * jiu zhang http://www.jiuzhang.com/solutions/lowest-common-ancestor-ii/
	 */
	public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
			ParentTreeNode A, ParentTreeNode B) {
		// Write your code here

		// Write your code here
		if (root == null) {
			return null;
		}
		if (root == A || root == B) {
			return root;
		}

		ParentTreeNode left = lowestCommonAncestorII(root.left, A, B);
		ParentTreeNode right = lowestCommonAncestorII(root.right, A, B);
		if (left != null && right != null) {
			return root;
		} else if (left != null) {
			return left;
		} else if (right != null) {
			return right;
		} else {
			return null;
		}
	}
}
