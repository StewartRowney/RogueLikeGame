package models;

import models.mapitems.DefaultMapItem;
import models.mapitems.IMapItem;
import models.mapitems.Wall;

import java.util.Random;

public class Map {
    //FIELDS
    private final int size;
    private final IMapItem[][] map;

    //CONSTRUCTOR
    public Map(int size) {
        this.size = size;
        this.map = new IMapItem[size][size];
    }

    //GETTER
    public int getSize() {
        return size;
    }

    //METHODS
    public void changeLocation(IMapItem mapItem, Location newLocation) {
        Location previousLocation = mapItem.getLocation();
        mapItem.setLocation(newLocation);
        map[newLocation.x()][newLocation.y()] = mapItem;
        map[previousLocation.x()][previousLocation.y()] = new DefaultMapItem(previousLocation);
    }

    public void setUpLocation(IMapItem mapItem) {
        Location location = mapItem.getLocation();
        map[location.x()][location.y()] = mapItem;
    }

    public IMapItem getMapItem(Location location) {
        return map[location.x()][location.y()];
    }

    public void printMapToConsole() {
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%s  ", map[j][i].getSymbol());
            }
            System.out.println("");
        }
        System.out.println();
    }

    public void initialiseMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || j == 0 || i == map.length - 1 || j == map.length - 1) {
                    setUpLocation(new Wall(new Location(i,j)));
                }
                else {
                    setUpLocation(new DefaultMapItem((new Location(i,j))));
                }
            }
        }
    }

    public Location selectRandomFreeLocation() {
        boolean success = false;
        Location location = new Location(0, 0);
        Random random = new Random();

        while (!success) {
            int x = random.nextInt(size - 2) + 1;
            int y = random.nextInt(size - 2) + 1;

            if (map[x][y].getClass() == DefaultMapItem.class) {
                location = new Location( x, y);
                success = true;
            }
        }
        return location;
    }

}
