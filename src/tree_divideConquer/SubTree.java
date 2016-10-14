package tree_divideConquer;

import java.util.Arrays;

/**
 * 
 * Given two binary trees, check if the first tree is subtree of the second one.
 * A subtree of a tree T is a tree S consisting of a node in T and all of its
 * descendants in T.
 * 
 * The subtree corresponding to the root node is the entire tree; the subtree
 * corresponding to any other node is called a proper subtree.
 * 
 * 
 */
public class SubTree {
	/**
	 * Given two binary trees, check if the first tree is subtree of the second
	 * one. A subtree of a tree T is a tree S consisting of a node in T and all
	 * of its descendants in T.
	 * 
	 * The subtree corresponding to the root node is the entire tree; the
	 * subtree corresponding to any other node is called a proper subtree.
	 */
	public boolean isSubtree(TreeNode T1, TreeNode T2) {

		if (T2 == null) {
			return true;
		}
		// which mean T2 is not null, and T1 is null,
		if (T1 == null) {
			return false;
		}

		if (isEqual(T1, T2)) {
			return true;
		}
		return isSubtree(T1.left, T2) || isSubtree(T1.right, T2);

	}

	public boolean isEqual(TreeNode T1, TreeNode T2) {
		if (T1 == null && T2 == null) {
			return true;
		}
		if (T1 == null || T2 == null) {
			return false;
		}

		if (T1.val != T2.val) {
			return false;
		}

		return isEqual(T1.left, T2.left) && isEqual(T1.right, T2.right);
	}

	/**
	 * method 2: on the fact that inorder and preorder/postorder uniquely
	 * identify a binary tree.
	 * 
	 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-
	 * tree-set-2/
	 * 
	 * We can solve the above problem in O(n) time. Please refer Check if a
	 * binary tree is subtree of another binary tree | Set 2 for O(n) solution.
	 * 
	 * 思路就是求出两个遍历序列（前中或者中后），然后用模式匹配算法判断包含关系，这样就可以做到线性复杂度了
	 */

	/* This function returns true if S is a subtree of T, otherwise false */
	boolean isSubtree(Node T, Node S) {
		/* base cases */
		if (S == null) {
			return true;
		}
		if (T == null) {
			return false;
		}

		// Store Inorder traversals of T and S in inT[0..m-1]
		// and inS[0..n-1] respectively
		char inT[] = new char[100];
		String op1 = String.valueOf(inT);
		char inS[] = new char[100];
		String op2 = String.valueOf(inS);
		storeInorder(T, inT, p);
		storeInorder(S, inS, p);
		inT[p.m] = '\0';
		inS[p.m] = '\0';
		System.out.println(Arrays.toString(inT));
		System.out.println(Arrays.toString(inS));
		// If inS[] is not a substring of preS[], return false
		if (strstr(op1, op2) != null) {
			return false;
		}

		// Store Preorder traversals of T and S in inT[0..m-1]
		// and inS[0..n-1] respectively
		p.m = 0;
		p.n = 0;
		char preT[] = new char[15];
		char preS[] = new char[15];
		String op3 = String.valueOf(preT);
		String op4 = String.valueOf(preS);
		storePreOrder(T, preT, p);
		storePreOrder(S, preS, p);
		preT[p.m] = '\0';
		preS[p.n] = '\0';

		// If inS[] is not a substring of preS[], return false
		// Else return true
		return (strstr(op3, op4) != null);
	}

	// A utility function to store inorder traversal of tree rooted
	// with root in an array arr[]. Note that i is passed as reference
	void storeInorder(Node node, char arr[], Passing i) {
		if (node == null) {
			arr[i.i++] = '$';
			return;
		}
		storeInorder(node.left, arr, i);
		arr[i.i++] = node.data;
		storeInorder(node.right, arr, i);
	}

	// A utility function to store preorder traversal of tree rooted
	// with root in an array arr[]. Note that i is passed as reference
	void storePreOrder(Node node, char arr[], Passing i) {
		if (node == null) {
			arr[i.i++] = '$';
			return;
		}
		arr[i.i++] = node.data;
		storePreOrder(node.left, arr, i);
		storePreOrder(node.right, arr, i);
	}

	static Node root;
	Passing p = new Passing();
	String strstr(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return null;
		}
		int hLength = haystack.length();
		int nLength = needle.length();
		if (hLength < nLength) {
			return null;
		}
		if (nLength == 0) {
			return haystack;
		}
		for (int i = 0; i <= hLength - nLength; i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				int j = 0;
				for (; j < nLength; j++) {
					if (haystack.charAt(i + j) != needle.charAt(j)) {
						break;
					}
				}
				if (j == nLength) {
					System.out.println("j: " + j);
					return haystack.substring(i);
				}
			}
		}
		return null;
	}

	// Driver program to test above functions
	public static void main(String args[]) {
		SubTree tree = new SubTree();
		Node T = new Node('a');
		T.left = new Node('b');
		T.right = new Node('d');
		T.left.left = new Node('c');
		//T.right.right = new Node('e');

		Node S = new Node('a');
		S.left = new Node('b');
		S.right = new Node('d');
		S.left.left = new Node('c');

		/**
		 * return false, 因为subtee的root是a,
		 * 那么按照subtree的定义，以a为root的根节点必须包含所有在大树里面以a为root的点
		 * 
		 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary
		 * -tree-set-2/
		 */
		if (tree.isSubtree(T, S)) {
			System.out.println("Yes , S is a subtree of T");
		} else {
			System.out.println("No, S is not a subtree of T");
		}
	}

}

class Passing {

	int i;
	int m = 0;
	int n = 0;
}

class Node {

	char data;
	Node left, right;

	Node(char item) {
		data = item;
		left = right = null;
	}
}
