package design.Elevator;

public class ElevatorFactory {
	private static Elevator elevator;

	public static Elevator getElevator() {
		if(elevator == null){
			return new Elevator();
		} else {
			return elevator;
		}
	}

}
