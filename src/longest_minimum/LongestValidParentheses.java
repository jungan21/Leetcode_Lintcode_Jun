package longest_minimum;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is
 * "()()", which has length = 4.
 * 
 *
 */
public class LongestValidParentheses {

	public static void main(String[] args) {
		// String s = "(()()";
		String s = "(())";
		System.out.println(longestValidParentheses(s));
	}

	/**
	 * https://wenyuanjiao.gitbooks.io/algorithm_practice/content/
	 * longest_valid_parentheses.html
	 * 
	 * https://discuss.leetcode.com/topic/2289/my-o-n-solution-using-a-stack/5
	 * 
	 * Initialize a stack of Integer to put index
	 * 
	 * append 'x' to string
	 * (必须append一个x到s后面，如果不append，碰到"(()"的情况，最后一个')'加进去的时候不update max)
	 * 
	 * Scan each char in String s
	 * 
	 * if char==')' && !stack.isEmpty() && char at stack.peek() is '(' {stack
	 * pop}
	 * 
	 * else
	 * 
	 * if (stack.isEmpty()) {max: i}
	 * 
	 * else {max: i-stack.peek()-1} push(i)
	 * 
	 * return max
	 * 
	 */

	public static int longestValidParentheses(String s) {
		int max = Integer.MIN_VALUE;
		// Note:
		s += "x";
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')' && !stack.isEmpty()
					&& s.charAt(stack.peek()) == '(') {
				stack.pop();
			} else {
				if (stack.isEmpty()) {
					max = Math.max(max, i);
				} else {
					max = Math.max(max, i - stack.peek() - 1);
				}
				stack.push(i);
			}
		}
		return max;
	}

	// dp dp[i], 前i个元素有多少valid结果
	// http://bangbingsyb.blogspot.ca/2014/11/leetcode-longest-valid-parentheses.html
	/**
	 * s.charAt(i-1) = '(' => dp[i] = 0;
	 * 
	 * s.charAt(i-1) = ')' 找i前一个字符的最长括号串DP[i]的前一个字符j = i-2-DP[i-1]
	 */
	public static int longestValidParenthesesDP(String s) {
		int max = Integer.MIN_VALUE;
		int len = s.length();
		int[] dp = new int[len + 1];
		dp[0] = 0;

		for (int i = 1; i <= len; i++) {
			// 找i前一个字符的最长括号串DP[i]的前一个字符j = i-2-DP[i-1]
			int j = i - 2 - dp[i - 1];
			if (s.charAt(i - 1) == '(' || j < 0 || s.charAt(j) == ')') {
				dp[i] = 0;
			} else {
				// s.charAt(i - 1) == ')' s.charAt(j) == '(' && j >=0
				dp[i] = dp[i - 1] + 2 + dp[j];
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}
}
