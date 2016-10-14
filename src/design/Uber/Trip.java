package design.Uber;

import java.util.Date;

public class Trip {

	enum Status {
		NEW, WAITING, PICK_UP, STARTED, ENDED;
	}

	private int id;
	private Driver driver;
	private Customer customer;
	// new request, waiting for driver, on the way to pick up, in trip,
	// cancelled, ended;
	private Status status;
	// when trip started
	private Date createdAt;
	private Location destination;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
