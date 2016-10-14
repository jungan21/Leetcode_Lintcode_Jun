package tree_divideConquer;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 *
 * One way to serialize a binary tree is to use pre-order traversal. When we
 * encounter a non-null node, we record the node's value. If it is a null node,
 * we record using a sentinel value such as #.
 * 
 * Example 1: "9,3,4,#,#,1,#,#,2,#,6,#,#" Return true
 * 
 * Example 2: "1,#" Return false
 * 
 * Example 3: "9,#,#,1" Return false
 * 
 */
public class VerifyPreorderSerialization {

	public static void main(String[] args) {
		String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		System.out.println(isValidSerialization(s));
	}

	/**
	 * 可以观察出如下两个规律：
	 * 
	 * 1. 数字的个数总是比#号少一个
	 * 
	 * 2. 最后一个一定是#号
	 * 
	 * http://www.cnblogs.com/grandyang/p/5174738.html
	 * 
	 * 
	 * 那么我们加入先不考虑最后一个#号，那么此时数字和#号的个数应该相同，如果我们初始化一个为0的计数器，遇到数字，计数器加1，遇到#号，计数器减1，
	 * 那么到最后计数器应该还是0。
	 * 
	 * 两个返回False的例子，"#,7,6,9,#,#,#"和"7,2,#,2,#,#,#,6,#"，那么通过这两个反例我们可以看出，
	 * 如果根节点为空的话
	 * ，后面不能再有节点，而且不能有三个连续的#号出现。所以我们再加减计数器的时候，如果遇到#号，且此时计数器已经为0了，再减就成负数了
	 * ，就直接返回False了，因为正确的序列里，任何一个位置i，在[0,
	 * i]范围内的#号数都不大于数字的个数的。当循环完成后，我们检测计数器是否为0的同时还要看看最后一个字符是不是#号。
	 * 
	 */
	public static boolean isValidSerialization(String preorder) {
		if (preorder == null || preorder.length() == 0) {
			return false;
		}
		String[] arr = preorder.split(",");
		int count = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].equals("#")) {
				count--;
				if (count < 0) {
					return false;
				}
			} else {
				count++;
			}
		}
		// 如果最后一位不考虑，"#"和"数字"个数应该相等，而且最后为必须是"#"
		return (count == 0 && arr[arr.length - 1].equals("#"));
	}

	/**
	 * jiuzhang
	 */
	public boolean isValidSerialization1(String preorder) {
		String s = preorder;
		boolean flag = true;
		while (s.length() > 1) {
			int index = s.indexOf(",#,#");
			if (index < 0) {
				flag = false;
				break;
			}
			int start = index;
			while (start > 0 && s.charAt(start - 1) != ',') {
				start--;
			}
			if (s.charAt(start) == '#') {
				flag = false;
				break;
			}
			s = s.substring(0, start) + s.substring(index + 3);
		}
		if (s.equals("#") && flag)
			return true;
		else
			return false;
	}

}
