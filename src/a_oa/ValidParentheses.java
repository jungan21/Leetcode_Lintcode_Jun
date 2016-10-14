package a_oa;

import java.util.Stack;

// Time complexity: O(n) where n is length of the string
// Space complexity: O(n/2) where n is length of string

/**
 * 给你一个str,里面只有 '('和‘)’,让你数valid pairs一共有多少,如果不是valid就返回-1.
 * (判断是不是valid的parenthesis string，不是的话返回-1，是的话返回valid pair个数，即String.length() /
 * 2 )
 */

public class ValidParentheses {

	static boolean isCouple(char l, char r) {
		return l == '(' && r == ')';
	}

	// http://www.jiuzhang.com/solutions/valid-parentheses/
	public static int isBalanced(String str) {

		if (str == null || str.length() == 0) {
			return 0;
		}
		// odd number would always result in false
		if ((str.length() % 2) != 0) {
			return -1;
		}
		int count = 0;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// if ("({[".contains(String.valueOf(c))) {
			// "({[".indexOf(ch) != -1
			if (ch == '(') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					return -1;
				} else {
					if (isCouple(stack.peek(), ch)) {
						stack.pop();
						count++;
					} else {
						return -1;
					}
				}
			}

		}
		return stack.isEmpty() ? count : -1;
	}

	public static void main(String[] args) {
		System.out.println(isBalanced("(())()"));
		// System.out.println(isBalanced("([]{)"));
		// System.out.println(isBalanced("()[]{}[][]"));
	}

}
