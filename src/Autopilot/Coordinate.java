package Autopilot;

public class Coordinate {
	public final int x;
	public final int y;

	public final static Coordinate CENTER = new Coordinate(0, 0);

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d)", x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		Coordinate c = (Coordinate) obj;
		return x == c.x && y == c.y;
	}

	@Override
	public int hashCode() {
		return (x * 397) ^ y;
	}
}
