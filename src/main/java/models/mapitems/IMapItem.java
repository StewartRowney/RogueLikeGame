package models.mapitems;

import models.Location;

public interface IMapItem {

    char getSymbol();
    Location getLocation();
    void setLocation(Location location);
}
