package datastructure;

import java.util.LinkedList;
import java.util.Stack;

public class PrintStackQueueInReversedOrder {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		printStack(stack);

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		printQueue(queue);
	}

	// print stack element in reversed way
	public static void printStack(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int temp = stack.pop();
		printStack(stack);
		System.out.println(temp);
	}

	// print Queue element in reversed way
	public static void printQueue(LinkedList<Integer> queue) {
		if (queue.isEmpty()) {
			return;
		}
		int temp = queue.poll();
		printQueue(queue);
		System.out.println(temp);
	}
}
