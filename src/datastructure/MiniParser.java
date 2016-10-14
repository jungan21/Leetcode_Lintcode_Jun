package datastructure;

import java.util.Stack;

/**
 * Given s = "[123,[456,[789]]]",
 *
 */
public class MiniParser {

	public static void main(String[] args) {

	}

	// recursive
	public NestedInteger deserialize(String s) {
		NestedInteger result = new NestedInteger();
		if (s == null || s.length() == 0)
			return result;
		// s is just a number without any '[', ']'
		if (s.charAt(0) != '[') {
			result.setInteger(Integer.parseInt(s));
			return result;
		}

		/**
		 * Given s = "[123,[456,[789]]],
		 * 
		 * 对于s, 本算法for loop, 首先把 123处理了，然后把[456,[789]] 当做子问题递归调用处理
		 * 
		 * [1] s.length() >2, 因为 '[' ']' 加一个数字，最起码3个字符
		 */
		if (s.length() > 2) {
			int start = 1, count = 0;
			for (int i = 1; i < s.length(); i++) {
				char c = s.charAt(i);
				// 由于i从1开始， 第一个'[', 没有被考虑，第一次处理 ',' 123 处理了,
				if (count == 0 && (c == ',' || i == s.length() - 1)) {
					result.add(deserialize(s.substring(start, i)));
					start = i + 1;
				} else if (c == '[')
					count++;
				else if (c == ']')
					count--;
			}
		}
		return result;
	}

	public void clearNum(StringBuilder sb) {
		sb.delete(0, sb.length());
	}

	/**
	 * iterative
	 */

	public NestedInteger deserialize1(String s) {
		Stack<NestedInteger> stack = new Stack<NestedInteger>();
		String temp = "";
		// char 可以作为c
		for (char c : s.toCharArray()) {
			switch (c) {
			case '[':
				stack.push(new NestedInteger()); // start a new NI
				break;

			case ']':
				if (!temp.equals("")) {
					// add NI to parent
					stack.peek().add(new NestedInteger(Integer.parseInt(temp)));
					temp = "";
				}

				NestedInteger top = stack.pop();
				if (!stack.empty()) {
					stack.peek().add(top);
				} else {
					return top;
				}
				break;

			case ',':
				if (!temp.equals("")) {
					// add NI to parent
					stack.peek().add(new NestedInteger(Integer.parseInt(temp)));
					temp = "";
				}
				break;

			default:
				temp += c;
			}
		}
		// 对于输入时纯数字，如： 123，
		if (!temp.equals("")) {
			return new NestedInteger(Integer.parseInt(temp));
		}
		
		return null;
	}

}
