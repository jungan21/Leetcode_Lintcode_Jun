package datastructure;

import java.util.Stack;

public class MinStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(3);
		minStack.push(2);
		minStack.push(1);
		minStack.push(2);
		minStack.push(1);
		System.out.println(minStack.getMin());
		String a = "B";
		
	}

	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}

	public void push(int x) {
		stack.push(x);
		if (minStack.isEmpty()) {
			minStack.push(x);
		} else {
			minStack.push(Math.min(x, minStack.peek()));
		}
	}

	public void pop() {
		minStack.pop();
		stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return minStack.peek();
	}
}
