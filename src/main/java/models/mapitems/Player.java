package models.mapitems;

import models.Location;

public class Player extends Character {

    //FIELDS
    private String name;

    //CONSTRUCTOR
    public Player (String name, char symbol, Location location) {
        super(symbol, 1, location);
        this.name = name;
    }

    //GETTER
    public String getName() {
        return name;
    }

    //SETTER
    public void setName(String name) {
        this.name = name;
    }
}
