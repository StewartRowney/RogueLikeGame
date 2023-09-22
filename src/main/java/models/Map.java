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
    public void changeMapItem(IMapItem mapItem) {
        map[mapItem.getLocation().x()][mapItem.getLocation().y()] = mapItem;
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
            System.out.println("\n");
        }
    }

    public void initialiseMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || j == 0 || i == map.length - 1 || j == map.length - 1) {
                    changeMapItem(new Wall(new Location(i,j)));
                }
                else {
                    changeMapItem(new DefaultMapItem((new Location(i,j))));
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
