package company.walmart.design.RestaurantReservationSystem;

import java.time.LocalDate;
import java.util.List;

class Seat {

}

public class Table {
	int tableId;
	int numberOfSeats;
	TableType type;
	List<Seat> seatList;
	// allow for how many people
	int size; 

	public Table(int id, int number, TableType type) {
		this.tableId = id;
		this.numberOfSeats = number;
		this.type = type;
	}

	public boolean isAvailable(LocalDate reservationTime) {
		// check if this table is available begin from reserve time, the default
		// duration is 1 hour.
		return true;
	}

	enum TableType {
		Booth, HighTable;
	}

}
