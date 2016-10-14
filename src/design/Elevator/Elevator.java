package design.Elevator;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
	enum Status {
		OCCUPIED, IDLE, MAINANANCE;
	}

	enum Direction {
		UP, DOWN, HOLD;
	}

	// stand, maintenance, up , down
	private Status status;
	private int currentFloor;
	private Queue<Integer> destinationFloors;

	public Elevator(Integer currentFloor) {
		this.currentFloor = currentFloor;
		this.destinationFloors = new LinkedList<Integer>();
	}

	public int nextDestionation() {
		return this.destinationFloors.peek();
	}

	public void popDestination() {
		this.destinationFloors.poll();
	}

	public int currentFloor() {
		return this.currentFloor;
	}

	public void addNewDestinatoin(Integer destination) {
		this.destinationFloors.add(destination);
	}

	public void moveUp() {
		currentFloor++;
	}

	public void moveDown() {
		currentFloor--;
	}

	public Elevator.Direction direction() {
		if (destinationFloors.size() > 0) {
			if (currentFloor < destinationFloors.peek()) {
				return Elevator.Direction.UP;
			} else if (currentFloor > destinationFloors.peek()) {
				return Elevator.Direction.DOWN;
			}
		}
		return Elevator.Direction.HOLD;
	}

	public Elevator.Status status() {
		return (destinationFloors.size() > 0) ? Elevator.Status.OCCUPIED
				: Elevator.Status.IDLE;
	}

}
