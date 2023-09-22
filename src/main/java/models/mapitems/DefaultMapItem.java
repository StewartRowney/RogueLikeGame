package models.mapitems;

import models.Location;

public class DefaultMapItem implements IMapItem {

    //FIELDS
    private final char symbol = '-';
    private Location location;

    //CONSTRUCTOR
    public DefaultMapItem(Location location) {
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
