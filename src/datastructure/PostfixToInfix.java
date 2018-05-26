package datastructure;
import java.util.Scanner;
import java.util.Stack;

/**
 * Input : abc++ Output : (a + (b + c))
 * 
 * Input : ab*c+ Output : ((a*b)+c)
 * 
 * @author ganjun
 *
 */
public class PostfixToInfix {

	/**
	 * Checks if the input is operator or not
	 * 
	 * @param c
	 *            input to be checked
	 * @return true if operator
	 */
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
			return true;
		return false;
	}

	/**
	 * Converts any postfix to infix
	 * 
	 * @param postfix
	 *            String expression to be converted
	 * @return String infix expression produced
	 */
	public String convert(String postfix) {
		Stack<String> s = new Stack<>();

		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			if (isOperator(c)) {
				String b = s.pop();
				String a = s.pop();
				// NOTE
				s.push("(" + a + c + b + ")");
			} else
				// "" just for convert the char c to String
				s.push("" + c);

		}

		return s.pop();
	}

	public static void main(String[] args) {
		PostfixToInfix obj = new PostfixToInfix();
		Scanner sc = new Scanner(System.in);
		System.out.print("Postfix : ");
		String postfix = sc.next();
		System.out.println("Infix : " + obj.convert(postfix));
	}
}