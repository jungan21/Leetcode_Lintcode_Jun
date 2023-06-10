package company.walmart.design.parking;

public class ParkingLot {
	private Level[] levels;
	// num_rows: each level/floor has how many row;
	// spots_per_row. each row has how many spots 
	public ParkingLot(int level, int num_rows, int spots_per_row) {
		// Write your code here
		levels = new Level[level];
		for (int i = 0; i < level; i++) {
			// i means which floor, i.e. level
			levels[i] = new Level(i, num_rows, spots_per_row);
		}
	}

	// Park the vehicle in a spot (or multiple spots)
	// Return false if failed
	public boolean parkVehicle(Vehicle vehicle) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].parkVehicle(vehicle)) {
				return true;
			}
		}
		return false;
	}

	// unPark the vehicle
	public void unParkVehicle(Vehicle vehicle) {
		vehicle.clearSpots();
	}

	public void print() {
		for (int i = 0; i < levels.length; i++) {
			System.out.print("Level" + i + ": ");
			levels[i].print();
			System.out.println("");
		}
		System.out.println("");
	}

}
