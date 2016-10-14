package design.Elevator.State;

import java.util.PriorityQueue;

/**
 * 电梯停止状态
 */
public class Stop extends AbstractState implements State {

	// Constructor
	public Stop(T t, PriorityQueue<Integer> upCommandList,
			PriorityQueue<Integer> downCommandList) {
		super(t, upCommandList, downCommandList);
	}

	@Override
	public void action() {
		if (upCommandList.size() > downCommandList.size()) {
			t.setDirection(new Up(t, upCommandList, downCommandList));
		} else if (upCommandList.size() < downCommandList.size()) {
			t.setDirection(new Down(t, upCommandList, downCommandList));
		}
		t.getDirection().display();
	}

	@Override
	public void display() {
	}

}
