package design.Uber;

import java.sql.Date;
import java.util.List;

public class Driver {

	public int id;
	public Location location;
	// driver send location back to server every 4 seconds
	private Date lastUpdated;
	private List<Customer> matchedRiderList;

	/* constructor */
	public Driver(int id, Location location) {
		this.id = id;
		this.location = location;
	}

	public Driver(int id) {
		this.id = id;
	}

	/* get/set */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/* behavior */
	public Trip reportLocation(Driver driver, Location location) {

		// at the same time, get the matched customers
		return null;
	}

	public void accept(Request request) {

	}

	public void decline() {

	}
}
