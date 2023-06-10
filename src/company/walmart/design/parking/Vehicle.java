package company.walmart.design.parking;

import java.util.ArrayList;

public abstract class Vehicle {
	protected int spotsNeeded;
	protected VehicleSize size;
	protected String licensePlate;

	//之所以要list, 因为bus大车要占用5个连续的停车位
	protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();

	public int getSpotsNeeded() {
		return spotsNeeded;
	}

	public VehicleSize getSize() {
		return size;
	}

	/* Park vehicle in this spot (among others, potentially) */
	public void parkInSpot(ParkingSpot spot) {
		parkingSpots.add(spot);
	}

	/* Remove car from spot, and notify spot that it's gone */
	public void clearSpots() {
		for (int i = 0; i < parkingSpots.size(); i++) {
			parkingSpots.get(i).removeVehicle();
		}
		// parkingSpots 是一个list, 保留了之前该车所占用的所有spots,现在需要清除
		parkingSpots.clear();
	}

	// this need to be implement in subclass
	public abstract boolean canFitInSpot(ParkingSpot spot);

	public abstract void print();
}
