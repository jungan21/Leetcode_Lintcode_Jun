package company.walmart.design.parking;

public class Motorcycle extends Vehicle {

	public Motorcycle() {
		spotsNeeded = 1;
		size = VehicleSize.Motorcycle;
	}

	public boolean canFitInSpot(ParkingSpot spot) {
		return true;
	}

	@Override
	public void print() {
		System.out.print("M");

	}

}
