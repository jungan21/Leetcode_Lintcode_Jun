package tree_divideConquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * http://www.lintcode.com/en/problem/binary-tree-serialization/
 * 
 * Design an algorithm and write code to serialize and deserialize a binary
 * tree. Writing the tree to a file is called 'serialization' and reading back
 * from the file to reconstruct the exact same binary tree is 'deserialization'.
 * 
 * There is no limit of how you deserialize or serialize a binary tree, you only
 * need to make sure you can serialize a binary tree to a string and deserialize
 * this string to the original structure.
 */
public class BinaryTreeSerialization {

	/**
	 * pre-order recursive: 3 9 # # 20 15 # # 7 # #
	 * 
	 * http://massivealgorithms.blogspot.ca/2015/10/serialize-and-deserialize-
	 * binary-tree.html
	 */
	public static String serializeRecursive(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		// 也可以不用传递StringBuilder, 而是定义一个String或者StringBuilder类型的公共变量公共变量
		serializeHelper(root, sb);
		return sb.toString();
	}

	private static void serializeHelper(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("# ");
			return;
		}
		sb.append(node.val + " ");
		serializeHelper(node.left, sb);
		serializeHelper(node.right, sb);
	}

	public static TreeNode deserializeRecursive(String s) {
		if (s == null || s.length() == 0)
			return null;
		// StringTokenizer(String str, String delim)
		StringTokenizer st = new StringTokenizer(s, " ");
		return deserializeHelper(st);
	}

	private static TreeNode deserializeHelper(StringTokenizer st) {
		if (!st.hasMoreTokens()) {
			return null;
		}
		String val = st.nextToken();
		if (val.equals("#")) {
			return null;
		}
		// 由于是pre order 遍历，这里顺序不能改变
		TreeNode root = new TreeNode(Integer.valueOf(val));
		root.left = deserializeHelper(st);
		root.right = deserializeHelper(st);
		return root;
	}

	// 如果不用Tokenizer，也可以写成下面这种数组，index必须定义在外面，只能一直增加，不能随着递归的调用而改变
	public static TreeNode deserializeRecursive1(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		String[] strArr = data.split(" ");
		return deserializeHelper1(strArr);
	}

	static int index = 0;

	public static TreeNode deserializeHelper1(String[] st) {
		if (index >= st.length) {
			return null;
		}
		String val = st[index++];
		if (val.equals("#")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.valueOf(val));
		root.left = deserializeHelper1(st);
		root.right = deserializeHelper1(st);
		return root;
	}

	// =====================jiuzhang================
	/**
	 * Recommened! level order traversal: {3,9,20,#,#,15,7}
	 * 
	 * Queue: {(3), (9, 20), (null, null, 15, 7), (null, null, null, null)}
	 * 
	 */
	public static String serializeJun(TreeNode root) {
		if (root == null) {
			return "{}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node == null) {
					sb.append("#,");
					continue;
				} else {
					sb.append(node.val + ",");
				}
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}
		while (sb.length() > 1
				&& (sb.charAt(sb.length() - 1) == '#' || sb
						.charAt(sb.length() - 1) == ',')) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * This method will be invoked second, the argument data is what exactly you
	 * serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public static TreeNode deserialize(String data) {
		if (data.equals("{}")) {
			return null;
		}
		// remove first { and last } char, then convert to String array
		String[] vals = data.substring(1, data.length() - 1).split(",");
		ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
		queue.add(root);
		int index = 0;
		boolean isLeftChild = true;
		for (int i = 1; i < vals.length; i++) {
			if (!vals[i].equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
				if (isLeftChild) {
					queue.get(index).left = node;
				} else {
					queue.get(index).right = node;
				}
				queue.add(node);
			}
			if (!isLeftChild) {
				index++;
			}
			isLeftChild = !isLeftChild;
		}
		return root;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);

		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20);

		// TreeNode left_left = new TreeNode(4);
		// TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(15);
		TreeNode right_right = new TreeNode(7);

		root.left = left;
		root.right = right;
		root.right.left = right_left;
		root.right.right = right_right;

		System.out.println(serializeJun(root));

		// System.out.println(deserialize("{3,9,20,#,#,15,7}"));
		System.out.println(deserialize("1 # 2 # #"));
	}
}
