package tree_divideConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// in BST: binary search tree
public class KthSmallestElementInBST {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		TreeNode left = new TreeNode(6);
		TreeNode right = new TreeNode(10);
		TreeNode left_right = new TreeNode(7);
		TreeNode left_left = new TreeNode(5);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		System.out.println(kthSmallest(root, 3));
		System.out.println(inorderTraversalJiuzhang(root));
		System.out.println(kthSmallestJiuzhang(root, 3));
	}

	// since it's BST, do inorder traserver, it will sort the node
	public static int kthSmallest(TreeNode root, int k) {
		int result = 0;
		if (root == null || k <= 0) {
			return result;
		}
		int count = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode top = stack.peek();
			if (top.left != null) {
				stack.push(top.left);
				top.left = null;
			} else {
				count++;
				if (count == k) {
					result = top.val;
					break;
				}
				stack.pop();
				if (top.right != null) {
					stack.push(top.right);
				}
			}
		}
		return result;
	}
	
	
	
	

	public static int kthSmallestJiuzhang(TreeNode root, int k) {
		int result = 0;
		if (root == null || k <= 0) {
			return result;
		}
		int count = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			count++;
			if(count == k){
				result = cur.val;
				break;
			}
			cur = cur.right;
		}
		return result;
	}

	 public static ArrayList<Integer> inorderTraversalJiuzhang(TreeNode root) {
	       	ArrayList<Integer> result = new ArrayList<Integer>();
			if (root == null) {
			    return result;
			}
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode cur = root;
	        while (!stack.isEmpty() || cur != null){
	            while (cur != null){
	                stack.push(cur);
	                cur = cur.left;
	            }
	            cur = stack.pop();
	            result.add(cur.val);
	            cur = cur.right;
	        }        
			return result;
	    }
	
	// left, root, right
	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode top = stack.peek();
			if (top.left != null) {
				stack.push(top.left);
				top.left = null;
			} else {
				result.add(top.val);
				stack.pop();
				if (top.right != null) {
					stack.push(top.right);
					top.right = null;
				}
			}
		}

		return result;
	}
}
