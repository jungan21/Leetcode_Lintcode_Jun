package String;

import java.util.Stack;

// Time complexity: O(n) where n is length of the string
// Space complexity: O(n/2) where n is length of string

public class ValidParentheses {

	static boolean isCouple(char l, char r) {
		return (l == '[' && r == ']') || (l == '(' && r == ')')
				|| (l == '{' && r == '}');
	}

	// http://www.jiuzhang.com/solutions/valid-parentheses/
	public static boolean isBalanced(String str) {
		// odd number would always result in false
		if ((str.length() % 2) != 0) {
			return false;
		}

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// if ("({[".contains(String.valueOf(c))) {
			// "({[".indexOf(ch) != -1
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					if (isCouple(stack.peek(), ch)) {
						stack.pop();
					} else {
						return false;
					}
				}
			}

		}
		return stack.isEmpty();
	}

	/**
	 * Pagerduty interview question
	 * 
	 * Only contains ( ), no space can be used here
	 * 
	 */
	public static boolean isValidPagerDuty(String inputStr) {

		int length = inputStr.length();
		if (length == 0) {
			return true;
		}

		if (length % 2 != 0) {
			return false;
		}

		char pre = ' ';
		int countOpen = 0;
		int countClose = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			char ch = inputStr.charAt(i);
			if (ch == '(') {
				countOpen++;
				pre = ch;
			} else {
				countClose++;
				if (pre == ' ') {
					return false;
				} else {
					if (isCouple(pre, ch)) {
						countOpen--;
						countClose--;
						if (countOpen == 0) {
							pre = ' ';
						}
					} else if (!isCouple(pre, ch)) {
						return false;
					}
				}

			}
		}
		return pre == ' ';
	}

	/**
	 * recursive - pagerduty
	 * 
	 * @param args
	 */
	public static boolean isValidPagerDuty1(String inputStr) {
		int index = 0;
		int openCount = 0;
		int closeCount = 0;
		return helper(inputStr, index, openCount, closeCount);
	}

	public static boolean helper(String inputStr, int index, int openCount,
			int closeCount) {

		if (index == inputStr.length()) {
			return openCount == closeCount;
		}
		// 不能省略，否则"))(("返回true
		if (openCount < closeCount) {
			return false;
		}
		if (inputStr.charAt(index) == '(') {
			return helper(inputStr, index + 1, openCount + 1, closeCount);
		} else if (inputStr.charAt(index) == ')') {
			return helper(inputStr, index + 1, openCount, closeCount + 1);
		}
		return helper(inputStr, index + 1, openCount, closeCount);
	}

	public static void main(String[] args) {
		System.out.println(isValidPagerDuty("))(("));
		// System.out.println(isBalanced("([]{)"));
		// System.out.println(isBalanced("()[]{}[][]"));
	}

}
