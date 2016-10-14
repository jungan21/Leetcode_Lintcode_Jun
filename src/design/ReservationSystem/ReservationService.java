package design.ReservationSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationService {

	List<Reservation> reservations;
	List<Table> tables;
	Set<Customer> customers;
	private static ReservationService instance;

	private ReservationService() {
		customers = new HashSet<>();
		init();
	}

	public static ReservationService getInstance() {
		if (instance == null) {
			instance = new ReservationService();
		}
		return instance;
	}

	private void init() {
		reservations = new ArrayList<>();
		tables = new ArrayList<>();
		// todo: add different types of tables to the table List
	}

	public void CreateNewProfile(int customerId, String fullName,
			String contactInfo) {
		// create profile for new customer
	}
	
	public boolean makeReservation(int customerId, LocalDate reservationTime,
			String otherRequirements) {
		Table table = null;
		for (Table t : tables) {
			if (t.isAvailable(reservationTime)) {
				table = t;
				break;
			}
		}
		if (table != null) {
			Reservation rsv = new Reservation(customerId, table.tableId,
					reservationTime, otherRequirements);
			return true;
		}
		return false;
	}
	
	public void cancelBooking(){
		
	}
}
