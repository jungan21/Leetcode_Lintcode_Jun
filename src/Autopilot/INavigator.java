package Autopilot;

public interface INavigator {
    Coordinate[] route(Coordinate spaceshipPosition, Coordinate[] destinations);
}
