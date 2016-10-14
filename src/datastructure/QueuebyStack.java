package datastructure;

import java.util.Stack;

public class QueuebyStack {
	// build queue by 2 stacks
	Stack<Integer> temp = new Stack<Integer>();
	Stack<Integer> stack = new Stack<Integer>();

	// Push element x to the back of queue.
	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(x);
		} else {
			while (!stack.isEmpty()) {
				temp.push(stack.pop());
			}
			stack.push(x);
			while (!temp.isEmpty()) {
				stack.push(temp.pop());
			}
		}
	}

	// Removes the element from in front of queue.
	public void pop() {
		stack.pop();
	}

	// Get the front element.
	public int peek() {
		return stack.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return stack.isEmpty();
	}

}
