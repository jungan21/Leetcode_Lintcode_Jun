
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import Autopilot.main.Coordinate;
import Autopilot.main.INavigator;
import Autopilot.main.Navigator;

public class NavigatorTest {
	@Test
	public void favourite8() {
		Coordinate a = new Coordinate(-1, -2);
		Coordinate b = new Coordinate(1, 1);
		Coordinate c = new Coordinate(2, -1);
		Coordinate d = new Coordinate(3, -2);
		Coordinate e = new Coordinate(6, 1);
		Coordinate f = new Coordinate(3, 10);
		Coordinate g = new Coordinate(-1, 8);
		Coordinate h = new Coordinate(-2, 9);

		INavigator navigator = new Navigator();
		Coordinate[] route = navigator.route(Coordinate.CENTER,
				new Coordinate[] { d, h, b, g, a, c, f, e });

		assertArrayEquals(new Coordinate[] { a, b, c, d, e, f, g, h }, route);
	}
}
