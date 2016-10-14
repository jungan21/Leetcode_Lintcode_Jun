package ImmutableQueue;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestImmutableQueue extends TestCase {
	ImmutableQueue<Integer> queue;

	@Before
	public void setUp() throws Exception {
		queue = new ImmutableQueue<Integer>();
		queue = queue.enQueue(1);
		queue = queue.enQueue(2);
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testEnQueue() {
		queue = queue.enQueue(3);
		assertTrue(queue.size() == 3);
	}

	@Test
	public void testDeQueue() {
		queue = queue.deQueue();
		assertTrue(queue.head() == 2);
	}

	@Test
	public void testHead() {
		assertTrue(queue.head()==1);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue.isEmpty());
	}

}
