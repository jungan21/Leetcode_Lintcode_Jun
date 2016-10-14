package datastructure;

import java.util.Stack;

public class ReveserStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		reverseStack(stack);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

		// for (int i = 0, n = stack.size(); i < n; i++) {
		// System.out.println(stack.elementAt(i));
		// }

	}

	public static <T> void reverseStack(Stack<T> stack) {
		if (stack.isEmpty()) {
			return;
		}
		// Remove bottom element from stack
		T bottom = popBottom(stack);

		// Reverse everything else in stack
		reverseStack(stack);

		// Add original bottom element to top of stack
		stack.push(bottom);
	}

	private static <T> T popBottom(Stack<T> stack) {
		T top = stack.pop();
		if (stack.isEmpty()) {
			// If we removed the last element, return it
			return top;
		} else {
			// We didn't remove the last element, so remove the last element
			// from what remains
			T bottom = popBottom(stack);
			// Since the element we removed in this function call isn't the
			// bottom element, add it back onto the top of the stack where it
			// came from
			stack.push(top);
			return bottom;
		}
	}

}
