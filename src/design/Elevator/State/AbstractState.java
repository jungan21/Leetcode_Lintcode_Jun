package design.Elevator.State;

import java.util.PriorityQueue;

public abstract class AbstractState {
	protected PriorityQueue<Integer> upCommandList;
	protected PriorityQueue<Integer> downCommandList;
	protected T t;

	public AbstractState(T t, PriorityQueue<Integer> upCommandList,
			PriorityQueue<Integer> downCommandList) {
		this.t = t;
		this.upCommandList = upCommandList;
		this.downCommandList = downCommandList;
	}
}
