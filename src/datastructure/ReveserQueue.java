package datastructure;

import java.util.LinkedList;

public class ReveserQueue {

	public static void main(String[] args) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		reverseQueue(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}

	}

	public static <T> void reverseQueue(LinkedList<T> queue) {
		if (queue.isEmpty()) {
			return;
		}
		// 与revese stack 相反，我们需要popTop from queue
		T top = queue.poll();

		// Reverse everything else in queue
		reverseQueue(queue);

		// Add original top element to the end of the queue
		queue.offer(top);
	}

}
