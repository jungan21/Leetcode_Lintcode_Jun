package design.Elevator.State;

import java.util.Comparator;
import java.util.PriorityQueue;

public class T implements Runnable {
	private int floorSize = 10;
	private PriorityQueue<Integer> upCommand = new PriorityQueue<Integer>(
			floorSize, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
			});
	private PriorityQueue<Integer> downCommand = new PriorityQueue<Integer>(
			floorSize, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

	// 电梯的状态.分别为停止,上升,下降
	private State direction = new Stop(this, upCommand, downCommand);
	// 电梯的当前层
	private int currentFloor;

	public T() {
		this.currentFloor = 1;
		new Thread(this).start();
	}

	public void addCommand(Integer floor) {
		if (floor > getCurrentFloor() && !upCommand.contains(floor)) {
			upCommand.add(floor);
		} else if (floor < getCurrentFloor() && !downCommand.contains(floor)) {
			downCommand.add(floor);
		}
	}

	public void setDirection(State direction) {
		this.direction = direction;
	}

	public State getDirection() {
		return direction;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	// 电梯上升
	void up() {
		this.currentFloor = this.currentFloor + 1;
		System.out.println(" " + getCurrentFloor() + "层..");
	}

	// 电梯下降
	void Down() {
		this.currentFloor = this.currentFloor - 1;
		System.out.println(" " + getCurrentFloor() + "层..");

	}

	@Override
	public void run() {

		while (true) {
			this.direction.action();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		T t = new T();
		t.addCommand(3);

		t.addCommand(6);
		Thread.sleep(4000);
		t.addCommand(2);
	}

}
