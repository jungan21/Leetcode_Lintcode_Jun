package design.Elevator.command;

public class Client {
	public static void main(String[] args) {
		ElevatorCar elevator = new ElevatorCar();
		CommandInvoker invoker = new CommandInvoker();

		Request r1 = new Request(1433930047, 3, "up");
		Request r2 = new Request(1433930108, 2, "down");
		Request r3 = new Request(1433930108, 5, "up");
		Request r4 = new Request(1433930108, 5, "up");
		Request r5 = new Request(1433930108, 2, "down");

		Command c1 = new CommandUpAndDown(elevator, r1);
		invoker.storeAndExecute(c1);
		Command c2 = new CommandUpAndDown(elevator, r2);
		invoker.storeAndExecute(c2);
		Command c3 = new CommandUpAndDown(elevator, r3);
		invoker.storeAndExecute(c3);
		Command c4 = new CommandUpAndDown(elevator, r4);
		invoker.storeAndExecute(c4);
		Command c5 = new CommandUpAndDown(elevator, r5);
		invoker.storeAndExecute(c5);

	}
}
