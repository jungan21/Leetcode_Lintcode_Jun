package design.Elevator.State;

import java.util.PriorityQueue;
/**
 * 电梯上升状态
 */
public class Up extends AbstractState implements State {

	public Up(T t, PriorityQueue<Integer> upCommandList,
			PriorityQueue<Integer> downCommandList) {
		super(t, upCommandList, downCommandList);
	}

	@Override
	public void action() {
		if (upCommandList.peek() != null) {
			if (upCommandList.peek() > t.getCurrentFloor()) {
				t.up();
				if (t.getCurrentFloor() == upCommandList.peek()) {
					upCommandList.poll();
					System.out.println(" " + t.getCurrentFloor() + "层到了");
				}
			}
		} else if (downCommandList.peek() != null) {
			t.setDirection(new Down(t, upCommandList, downCommandList));
			t.getDirection().display();

		} else {
			t.setDirection(new Stop(t, upCommandList, downCommandList));
		}
	}

	@Override
	public void display() {
		System.out.println("电梯上行");
	}

}
