package design.parking;

public class Level {
	private int floor;
	// 每一楼层的spots
	private ParkingSpot[] spots;
	// 每一楼层的 availalbe spots
	private int availableSpots = 0; // number of free spots
	private int SPOTS_PER_ROW;

	public Level(int flr, int num_rows, int spots_per_row) {
		this.floor = flr;
		int SPOTS_PER_ROW = spots_per_row;
		int numberSpots = 0;
		this.spots = new ParkingSpot[num_rows * spots_per_row];

		// init size for each spot in array spots
		// 每个spot的大小实际是一样的，只是提前把某个或某一个区域预留给指定的车型
		for (int row = 0; row < num_rows; ++row) {
			for (int spot = 0; spot < spots_per_row / 4; ++spot) {
				VehicleSize sz = VehicleSize.Motorcycle;
				spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
				numberSpots++;
			}
			for (int spot = spots_per_row / 4; spot < spots_per_row / 4 * 3; ++spot) {
				VehicleSize sz = VehicleSize.Compact;
				spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
				numberSpots++;
			}
			for (int spot = spots_per_row / 4 * 3; spot < spots_per_row; ++spot) {
				VehicleSize sz = VehicleSize.Large;
				spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
				numberSpots++;
			}
		}
		availableSpots = numberSpots;
	}

	/* Try to find a place to park this vehicle. Return false if failed. */
	// on level basis, i.e. 以每一层为单位，如果在某一层能park就返回true
	public boolean parkVehicle(Vehicle vehicle) {
		if (availableSpots() < vehicle.getSpotsNeeded()) {
			return false; // no enough spots
		}
		int spotNumber = findAvailableSpots(vehicle);
		if (spotNumber < 0) {
			return false;
		}
		return parkStartingAtSpot(spotNumber, vehicle);
	}

	/* find a spot to park this vehicle. Return index of spot, or -1 on failure. */
	private int findAvailableSpots(Vehicle vehicle) {
		int spotsNeeded = vehicle.getSpotsNeeded();
		// 保证找到的spots是连在一起的
		int lastRow = -1;
		int spotsFound = 0;

		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			// 几个连续的spot必须在一行才符合要求
			if (lastRow != spot.getRow()) {
				spotsFound = 0;
				lastRow = spot.getRow();
			}
			// 每个spot的大小不一样, 根据车型，预留了
			// ，只是提前把某个或某一个区域预留给指定的车型
			// 譬如停Bus, 要找到连续5个size为large的spots才可以
			if (spot.canFitVehicle(vehicle)) {
				spotsFound++;
			} else {
				spotsFound = 0;
			}
			if (spotsFound == spotsNeeded) {
				return i - (spotsNeeded - 1); // index of spot
			}
		}
		return -1;
	}

	/*
	 * Park a vehicle starting at the spot spotNumber, and continuing until
	 * vehicle.spotsNeeded.
	 */
	private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
		vehicle.clearSpots();

		boolean success = true;

		for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
			// boolean要 保证 全部为true
			success &= spots[i].park(vehicle);
			// success = (success && spots[i].park(vehicle));
		}

		availableSpots -= vehicle.spotsNeeded;
		return success;
	}

	/* When a car was removed from the spot, increment availableSpots */
	public void spotFreed() {
		availableSpots++;
	}

	public int availableSpots() {
		return availableSpots;
	}

	public void print() {
		int lastRow = -1;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (spot.getRow() != lastRow) {
				/**
				 * Level0:   mmmccccccllllll  mmmccccccllllll
				 * 
				 * 两行之间打印一个空格
				 */
				System.out.print("  ");
				lastRow = spot.getRow();
			}
			spot.print();
		}
	}
}
