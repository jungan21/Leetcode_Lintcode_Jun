package company.walmart.design.RestaurantReservationSystem;

public class Customer {

	private int customerId;
	private String fullName;
	private String telephoneNumber;

	public Customer(int id, String name, String telePhoneNumber) {
		this.customerId = id;
		this.fullName = name;
		this.telephoneNumber = telePhoneNumber;
	}

	public int getId() {
		return this.customerId;
	}

	public String getName() {
		return this.fullName;
	}

	public String getTeleNumber() {
		return this.telephoneNumber;
	}

}
