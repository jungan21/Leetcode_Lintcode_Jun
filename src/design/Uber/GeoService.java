package design.Uber;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO service
 *
 */
public class GeoService {

	// key: driverID value: tripe associated with this driver (i.e. driver is on
	// the trip)
	Map<Integer, Trip> driver2Trip = null;
	// key: driverId, value: driver's location, 在这个map里的driver 都是available
	// 的driver
	Map<Integer, Location> driver2Location = null;

	// 系统启动时，初始化一些driver
	public GeoService(HashMap<Integer, Trip> driver2Trip,
			Map<Integer, Location> driver2Location) {
		this.driver2Trip = driver2Trip;
		this.driver2Location = driver2Location;
	}

	// process customer request
	public Trip request(Customer customer) {
		// Write your code here
		Trip trip = new Trip();
		trip.setCustomer(customer);

		Location customerLocation = customer.getLocation();

		double distance = -1;
		int driver_id = -1;
		for (Map.Entry<Integer, Location> entry : driver2Location.entrySet()) {
			Location driverLocation = entry.getValue();
			double dis = GeoUtil.getDistance(driverLocation, customerLocation);
			if (distance < 0 || distance > dis) {
				driver_id = entry.getKey();
				distance = dis;
			}
		}

		if (driver_id != -1)
			driver2Location.remove(driver_id);

		Driver driver = new Driver(driver_id);
		trip.setDriver(driver);
		// waiting for driver to confirm
		trip.setStatus(Trip.Status.WAITING);
		// set this driver to unavailable by removing this driver from
		// driver2Location
		driver2Location.remove(driver_id);
		driver2Trip.put(driver_id, trip);
		return trip;
	}

	public Trip report(Driver dirver) {
		int driver_id = dirver.getId();
		Trip trip = driver2Trip.get(driver_id);
		if (trip != null){
			
			return trip;
		} else {
			
			
		}
	}
}
