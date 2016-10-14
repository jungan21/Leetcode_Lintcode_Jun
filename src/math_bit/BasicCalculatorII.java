package math_bit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward
 * zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples: "3+2*2" = 7 " 3/2 " = 1 " 3+5 / 2 " = 5 Note: Do not use the
 * eval built-in library function.
 * 
 */
public class BasicCalculatorII {

	public static void main(String[] args) {
		System.out.println(calculate("3*(2+2)"));
	}

	/**
	 * https://discuss.leetcode.com/topic/15761/accepted-java-infix-to-postfix-
	 * based-solution-with-explaination-600ms
	 * 
	 * http://codechen.blogspot.ca/2015/06/leetcode-basic-calculator-ii.html
	 */
	public static int calculate(String s) {
		return evaluatePostfix(infix2Postfix2(s));
	}

	public static int evaluatePostfix(List<Object> postfix) {
		Stack<Integer> operands = new Stack<Integer>();
		int a = 0, b = 0;
		for (Object s : postfix) {
			if (s instanceof Character) {
				char c = (Character) s;
				b = operands.pop();
				a = operands.pop();
				switch (c) {
				case '+':
					operands.push(a + b);
					break;
				case '-':
					operands.push(a - b);
					break;
				case '*':
					operands.push(a * b);
					break;
				default:
					operands.push(a / b);
				}
			} else { // instanceof Integer
				operands.push((Integer) s);
			}
		}
		return operands.pop();
	}

	/**
	 * 带(, ) 括号的情况，
	 * 
	 * transform to post fix 逆波兰试(postfix notation)
	 * EvaluateReversePolishNotation.java
	 * http://codechen.blogspot.ca/2015/06/leetcode-basic-calculator-ii.html
	 */
	public static List<Object> infix2Postfix2(String s) {
		Stack<Character> operators = new Stack<Character>();
		List<Object> postfix = new LinkedList<Object>();
		int numberBuffer = 0;
		boolean bufferingOperand = false;
		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9') {
				numberBuffer = numberBuffer * 10 + (c - '0');
				bufferingOperand = true;
			} else {
				if (bufferingOperand) {
					postfix.add(numberBuffer);
				}
				numberBuffer = 0;
				bufferingOperand = false;

				if (c == ' ' || c == '\t') {
					continue;
				}

				if (c == '(') {
					operators.push('(');
				} else if (c == ')') {
					while (operators.peek() != '(') {
						postfix.add(operators.pop());
					}
					operators.pop(); // popping "("
				} else { // operator
					while (!operators.isEmpty()
							&& rank(c) <= rank(operators.peek()))
						postfix.add(operators.pop());
					operators.push(c);
				}
			}

		}
		/**
		 * 如果输入只有"0", 那么上面的else里面的往postfix里添加的语句执行不到
		 */
		if (bufferingOperand)
			postfix.add(numberBuffer);

		while (!operators.isEmpty())
			postfix.add(operators.pop());
		System.out.println(postfix);
		return postfix;
	}

	/**
	 * 不带 (, ), 括号的情况
	 */
	public List<Object> infix2Postfix1(String s) {
		List<Object> postfix = new ArrayList<Object>();
		Stack<Character> operator = new Stack<>();
		char[] charArr = s.toCharArray();

		int numberBuffer = 0;
		boolean buffer = false;
		for (char c : charArr) {
			int num = c - '0';
			if (num >= 0 && num <= 9) {
				numberBuffer = numberBuffer * 10 + (c - '0');
				buffer = true;
			} else {
				if (buffer) {
					postfix.add(numberBuffer);
				}
				buffer = false;
				numberBuffer = 0;
				if (c == ' ') {
					continue;
				}
				while (!operator.isEmpty() && rank(c) <= rank(operator.peek())) {
					postfix.add(operator.pop());
				}
				operator.push(c);
			}
		}

		if (buffer) {
			postfix.add(numberBuffer);
		}
		while (!operator.isEmpty()) {
			postfix.add(operator.pop());
		}

		return postfix;
	}

	public static int rank(char op) {
		// the bigger the number, the higher the rank
		switch (op) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return 0; // '('
		}
	}
}
