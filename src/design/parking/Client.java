package design.parking;

import java.util.Random;

public class Client {

	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot(1, 2, 15);
		Random random = new Random();
		Vehicle v = null;
		while (v == null || lot.parkVehicle(v)) {
			// 第一次执行， 由于没有停任何车，所有就打印出空停车场
			// lot里的具体的print方法是以level为单位打印的
			lot.print();
			int r = random.nextInt(6);
			if (r < 1) {
				v = new Bus();
			} else if (r < 5) {
				v = new Motorcycle();
			} else {
				v = new Car();
			}
			System.out.print("\nParking a ");
			v.print();
			System.out.println("");
		}
		System.out.println("Parking Failed. Final state: ");
		lot.print();

	}

}
