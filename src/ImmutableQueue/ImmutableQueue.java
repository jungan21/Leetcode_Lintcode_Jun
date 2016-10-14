package ImmutableQueue;

import java.util.NoSuchElementException;

public class ImmutableQueue<T> {

	// Immutable Stack Class (will use two Immutable Stack to implement
	// immutable queue)
	private static class ImmutableStack<T> {

		private T head;
		// tail is an ImmutableStack object
		private ImmutableStack<T> tail;
		private int size;

		// default constructor
		private ImmutableStack() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}

		// Constructor Overloading
		private ImmutableStack(T obj, ImmutableStack<T> tail) {
			this.head = obj;
			this.tail = tail;
			this.size = tail.size + 1;
		}

		// Emptying the stack
		public static ImmutableStack emptyStack() {
			return new ImmutableStack();
		}

		// Checking if stack is empty
		public boolean isEmpty() {
			return this.size == 0;
		}

		// Push into the stack
		public ImmutableStack<T> push(T obj) {
			return new ImmutableStack(obj, this);
		}

		/**
		 * Take stack object and push the ( head of the tail stack) to the new
		 * stack do this until the stack is empty
		 * */

		public ImmutableStack<T> toReverseStack() {
			ImmutableStack<T> stack = new ImmutableStack<T>();
			ImmutableStack<T> tail = this;
			while (!tail.isEmpty()) {
				stack = stack.push(tail.head);
				tail = tail.tail;
			}
			return stack;
		}
	}

	// order for enqueue, reverse for dequeue
	private ImmutableStack<T> order;
	private ImmutableStack<T> reverse;

	// Default constructor ImmutableQueue
	public ImmutableQueue() {
		this.order = ImmutableStack.emptyStack();
		this.reverse = ImmutableStack.emptyStack();
	}

	// Constructor overloading
	public ImmutableQueue(ImmutableStack<T> order, ImmutableStack<T> reverse) {
		this.order = order;
		this.reverse = reverse;
	}

	/**
	 * reverse the order stack and assign it to reverse stack and make order
	 * stack empty
	 * */

	private void balanceQueue() {
		this.reverse = this.order.toReverseStack();
		this.order = ImmutableStack.emptyStack();
	}

	// Enqueue Object
	public ImmutableQueue<T> enQueue(T object) {
		if (object == null)
			throw new IllegalArgumentException();
		return new ImmutableQueue<T>(this.order.push(object), this.reverse);
	}

	/**
	 * Dequeue from the queue if Queue is empty then throw
	 * NoSuchElementException
	 * 
	 * if Reverse Stack is not empty then return Immutable queue with reverse
	 * stack's tail object
	 * 
	 * else reverse the Order ImmutableStack and take the tail of this and clean
	 * the order ImmutableStack
	 * 
	 * */

	public ImmutableQueue<T> deQueue() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (!this.reverse.isEmpty()) {
			return new ImmutableQueue<T>(this.order, this.reverse.tail);
		} else {
			return new ImmutableQueue<T>(ImmutableStack.emptyStack(),
					this.order.toReverseStack().tail);
		}
	}

	public T head() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.reverse.isEmpty())
			balanceQueue();
		return this.reverse.head;
	}

	public boolean isEmpty() {
		return (this.order.size + this.reverse.size) == 0;
	}

	public int size() {
		return (this.order.size + this.reverse.size);
	}

}