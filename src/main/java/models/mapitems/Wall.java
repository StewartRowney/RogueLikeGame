package models.mapitems;

import models.Location;

public class Wall implements IMapItem {

    //FIELDS
    private final char symbol = 'X';
    private Location location;

    //CONSTRUCTOR
    public Wall(Location location) {
        this.location = location;
    }

    //GETTERS
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
}
