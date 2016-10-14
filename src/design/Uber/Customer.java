package design.Uber;

public class Customer {
	private int id;
	private Location location;
	private DispatchService dispatcher;
	private Trip trip;

	// constructor
	public Customer(int id, Location location, DispatchService dispatcher) {
		this.id = id;
		this.location = location;
		this.dispatcher = dispatcher;
	}

	// set/get
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

	public DispatchService getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(DispatchService dispatcher) {
		this.dispatcher = dispatcher;
	}

	// behavior
	public void requestDriver(Customer customer) {
		dispatcher.requestDriver(customer);
	}
}
