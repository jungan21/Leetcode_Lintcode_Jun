package design.ReservationSystem;

import java.time.LocalDate;

public class Reservation {
	int reservationId;
	int customerId;
	int tableId;
	LocalDate creationTime;
	LocalDate reservationTime;
	String otherRequirements;
	int numberOfPeople;

	public Reservation(int customerId, int tableId, LocalDate reservationTime,
			String otherRequirements) {
		//this.reservationId = Auto generated ID 
		this.tableId = tableId;
		this.customerId = customerId;
		this.reservationTime = reservationTime;
		this.otherRequirements = otherRequirements;
	}

	public void udpateTable() {
		// to change the table for customer
	}

	public void updateTime() {
		// update the reservation time
	}

	public void updateRequirements() {
		// update the requirements
	}
}
