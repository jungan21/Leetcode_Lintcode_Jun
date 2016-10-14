package design.Elevator.State;

import java.util.PriorityQueue;

/**
 * 电梯下降状态
 */
public class Down extends AbstractState implements State {
	public Down(T t, PriorityQueue<Integer> upCommandList,
			PriorityQueue<Integer> downCommandList) {
		super(t, upCommandList, downCommandList);
	}

	@Override
	public void action() {
		if (downCommandList.peek() != null) {
			if (downCommandList.peek() < t.getCurrentFloor()) {
				t.Down();
				if (t.getCurrentFloor() == downCommandList.peek()) {
					downCommandList.poll();
					System.out.println(" " + t.getCurrentFloor() + "层到了");
				}
			}
		} else if (downCommandList.peek() != null) {
			t.setDirection(new Up(t, upCommandList, downCommandList));
			t.getDirection().display();

		} else {
			t.setDirection(new Stop(t, upCommandList, downCommandList));
		}
	}

	@Override
	public void display() {
		System.out.println("电梯下行");
	}

}
