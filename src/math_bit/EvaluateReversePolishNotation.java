package math_bit;

import java.util.Stack;

public class EvaluateReversePolishNotation {

	public static void main(String[] args) {
		String[] tokens = new String[] { "2", "1", "+", "3", "*" };
		System.out.println(evalRPN(tokens));
	}

	// method 1:
	// http://www.jiuzhang.com/solutions/evaluate-reverse-polish-notation/
	public static int evalRPN(String[] tokens) {
		String operator = "+-*/";
		Stack<Integer> stack = new Stack<Integer>();

		for (String token : tokens) {
			if (!operator.contains(token)) {
				stack.push(Integer.valueOf(token));
			} else {
				int a = stack.pop();
				int b = stack.pop();
				//注意b, a的顺序 不能乱
				if (token.equals("+")) {
					stack.push(b + a);
				} else if (token.equals("-")) {
					stack.push(b - a);
				} else if (token.equals("*")) {
					stack.push(b * a);
				} else if (token.equals("/")) {
					stack.push(b / a);
				}
			}
		}

		return stack.pop();
	}

	// method 2
	// http://www.programcreek.com/2012/12/leetcode-evaluate-reverse-polish-notation/
	public static int evalRPN1(String[] tokens) {
		int returnValue = 0;
		String operators = "+-*/";

		Stack<String> stack = new Stack<String>();

		for (String t : tokens) {
			if (!operators.contains(t)) { // push to stack if it is a number
				stack.push(t);
			} else {// pop numbers from stack if it is an operator
				int a = Integer.parseInt(stack.pop());
				int b = Integer.valueOf(stack.pop());
				switch (t) {
				case "+":
					stack.push(String.valueOf(a + b));
					break;
				case "-":
					stack.push(String.valueOf(b - a));
					break;
				case "*":
					stack.push(String.valueOf(a * b));
					break;
				case "/":
					stack.push(String.valueOf(b / a));
					break;
				}
			}
		}

		returnValue = Integer.valueOf(stack.pop());

		return returnValue;
	}
}
