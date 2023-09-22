package models.mapitems;

import models.Location;
import models.enums.Direction;

public class Character implements IMapItem {

    //FIELDS
    private final int speed;
    private char symbol;
    private Location location;

    //CONSTRUCTOR
    public Character(char symbol, int speed, Location location) {
        this.symbol = symbol;
        this.speed = speed;
        this.location = location;
    }

    //GETTERS
    public int getSpeed() {
        return speed;
    }
    @Override
    public char getSymbol() {
        return symbol;
    }
    @Override
    public Location getLocation() {
        return location;
    }

    //SETTERS
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    //METHODS
    public Location move(Direction direction) {
        Location newLocation = direction.getNextLocation(location, speed);
        location = newLocation;
        return location;
    }

}
