package datastructure;
import java.util.LinkedList;
import java.util.Queue;

public class StackByQueue {

	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();

	public static void main(String[] args) {
		System.out.println( (true & true));
	}

	// Push element x onto stack.
	public void push(int x) {
		if (empty()) {
			q1.add(x);
		} else {
			if (q1.size() > 0) {
				q2.add(x);
				int size = q1.size();
				while (size > 0) {
					q2.add(q1.poll());
					size--;
				}
			} else if (q2.size() > 0) {
				q1.add(x);
				int size = q2.size();
				while (size > 0) {
					q1.add(q2.poll());
					size--;
				}
			}
		}
	}

	// Removes the element on top of the stack.
	public void pop() {
		if (q1.size() > 0) {
			q1.poll();
		} else if (q2.size() > 0) {
			q2.poll();
		}
	}

	// Get the top element.
	public int top() {
		if (q1.size() > 0) {
			return q1.peek();
		} else if (q2.size() > 0) {
			return q2.peek();
		}
		return 0;
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return q1.isEmpty() & q2.isEmpty();
	}

	
	
	/**
	 * Jun
	 */
}
