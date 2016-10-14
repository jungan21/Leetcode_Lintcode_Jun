package ImmutableQueue;

public class TestCase {

	public static void main(String[] args) {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		queue = queue.enQueue(5);
		
		int size1 = queue.size();
		
		System.out.println(size1);
		// ImmutableQueue<Integer> x = newQueue.dequeue();
		
		queue = queue.deQueue();
		
		int size2 = queue.size();
		
		System.out.println(size2);
		
	}

}
