package models.enums;


import models.Location;

import java.util.Random;

public enum Direction {
    NORTH {public Location getNextLocation(Location location, int speed) {return new Location(location.x(), location.y() - speed);}},
    NORTH_EAST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() + speed, location.y() - speed);}},
    EAST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() + speed, location.y());}},
    SOUTH_EAST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() + speed, location.y() + speed);}},
    SOUTH {public Location getNextLocation(Location location, int speed) {return new Location(location.x(), location.y() + speed);}},
    SOUTH_WEST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() - speed, location.y() + speed);}},
    WEST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() - speed, location.y());}},
    NORTH_WEST {public Location getNextLocation(Location location, int speed) {return new Location(location.x() - speed, location.y() - speed);}};

    public abstract Location getNextLocation(Location location, int speed);

    public static Direction selectRandomDirection() {
        Random random = new Random();
        int randomNumber = random.nextInt(8);
        return Direction.values()[randomNumber];
    }
}

