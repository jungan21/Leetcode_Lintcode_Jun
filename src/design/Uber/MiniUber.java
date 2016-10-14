package design.Uber;

import java.util.HashMap;
import java.util.Map;

/**
 * from Jiuzhang http://www.jiuzhang.com/solutions/mini-uber/
 * 
 * http://www.lintcode.com/en/problem/mini-uber/
 *
 */
public class MiniUber {
	// key: driverID value: tripe associated with this driver (i.e. driver is on
	// the trip)
	Map<Integer, Trip> driver2Trip = null;
	// key: driverId, value: driver's location, 在这个map里的driver 都是available 的driver
	Map<Integer, Location> driver2Location = null;

	public MiniUber() {
		driver2Trip = new HashMap<Integer, Trip>();
		driver2Location = new HashMap<Integer, Location>();
	}

	// return matched trip information if there have matched rider or null
	public Trip report(int driver_id, double lat, double lng) {
		if (driver2Trip.containsKey(driver_id))
			return driver2Trip.get(driver_id);

		if (driver2Location.containsKey(driver_id)) {
			driver2Location.get(driver_id).lat = lat;
			driver2Location.get(driver_id).lng = lng;
		} else {
			driver2Location.put(driver_id, new Location(lat, lng));
		}
		return null;
	}

	// return a trip
	public Trip request(int rider_id, double lat, double lng) {
		// Write your code here
		Trip trip = new Trip(rider_id, lat, lng);
		double distance = -1;
		int driver_id = -1;
		for (Map.Entry<Integer, Location> entry : driver2Location.entrySet()) {
			Location location = entry.getValue();
			double dis = Helper.get_distance(location.lat, location.lng, lat,
					lng);
			if (distance < 0 || dis < distance) {
				driver_id = entry.getKey();
				distance = dis;
			}
		}

		if (driver_id != -1)
			driver2Location.remove(driver_id);
		trip.driver_id = driver_id;
		driver2Trip.put(driver_id, trip);
		return trip;
	}
}
