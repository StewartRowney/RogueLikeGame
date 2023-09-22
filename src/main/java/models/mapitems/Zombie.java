package models.mapitems;


import models.Location;

public class Zombie extends Character {
    public Zombie( Location location) {
        super('Z', 1, location);
    }
}
