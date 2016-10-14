package design.Uber;

import java.util.List;

/**
 * 
 * 1. driver send location to this service every 4 seconds, then this serivie
 * re-send to GeoService.java, at the same time, this service will get the
 * nearby customsers list and send it back to driver
 * 
 * 2. customer send Uber request to DispatchService.java, and then this service
 * get the drivers nearby from GeoService and send it back to customer
 *
 */
public class DispatchService {
	private GeoService geoService;

	public DispatchService(GeoService geoService) {
		this.geoService = geoService;
	}

	public Trip requestDriver(Customer customer) {
		return geoService.request(customer);
	}

	public Trip reportLocation(Driver driver) {
		return geoService.report(driver);
	}

	public List<Customer> getMatchedCustomers(Driver driver) {
		return null;
	}
}
