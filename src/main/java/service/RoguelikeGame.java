package service;

import models.Location;
import models.Map;
import models.enums.Difficulty;
import models.enums.Direction;
import models.mapitems.DefaultMapItem;
import models.mapitems.FinishPoint;
import models.mapitems.Player;
import models.mapitems.Zombie;

import java.util.ArrayList;

import java.util.Scanner;

public class RoguelikeGame {

    private final Player player;
    private final Difficulty difficulty;
    private final Map map;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Zombie> zombies = new ArrayList<>();
    private boolean gameFinished = false;

    public RoguelikeGame(Player player, Difficulty difficulty, int mapSize) {
        this.player = player;
        this.difficulty = difficulty;
        this.map = new Map(mapSize);
    }

    public void run() {
        gameSetUp();
        while (!gameFinished) {
            playerMove();
            map.printMapToConsole();
        }
    }

    private void gameSetUp() {
        gameFinished = false;
        map.initialiseMap();
        setUpCharacters();
        map.printMapToConsole();
    }

    public void setUpCharacters() {
        setUpPlayer();
        createFinishPoint();
        createZombies();
    }
    public void setUpPlayer() {
        player.setLocation(map.selectRandomFreeLocation());
        map.setUpLocation(player);
    }
    public void createFinishPoint() {
        FinishPoint finishPoint = new FinishPoint(map.selectRandomFreeLocation());
        map.setUpLocation(finishPoint);
    }
    public void createZombies() {
        int number = (difficulty.ordinal() + 1) * 3;
        for (int i = 0; i < number; i++) {
            Zombie zombie = new Zombie(map.selectRandomFreeLocation());
            zombies.add(zombie);
            map.setUpLocation(zombie);
        }
    }

    private void playerMove() {
        Direction playerDirection = validatePlayerMove();
        Location newLocation = playerDirection.getNextLocation(player.getLocation(), player.getSpeed());
        map.changeLocation(player, newLocation);
    }

    private Direction validatePlayerMove() {
        Direction playerDirection = promptPlayerForMove();
        Location targetLocation = playerDirection.getNextLocation(player.getLocation(), player.getSpeed());

        System.out.println(player.getLocation());
        System.out.println(playerDirection.getNextLocation(player.getLocation(), 1));

        if (map.getMapItem(targetLocation).getClass() == FinishPoint.class) {
            gameFinished = true;
            System.out.println("You win.");
            return playerDirection;
        }
        else if (map.getMapItem(targetLocation).getClass() != DefaultMapItem.class) {
            System.out.println("Space is already taken, try again");
            return validatePlayerMove();
        }
        return playerDirection;
    }

    private Direction promptPlayerForMove() {
        System.out.print("""
                Make a move:
                N - North
                NE - North-East
                E - East
                SE - South-East
                S - South
                SW - South-West
                W - West
                NW - North-West
                """);
        String input = scanner.nextLine();

        return switch(input.trim().toLowerCase()) {
            case "n": yield Direction.NORTH;
            case "ne": yield Direction.NORTH_EAST;
            case "e": yield Direction.EAST;
            case "se": yield Direction.SOUTH_EAST;
            case "s": yield Direction.SOUTH;
            case "sw": yield Direction.SOUTH_WEST;
            case "w": yield Direction.WEST;
            case "nw": yield Direction.NORTH_WEST;
            default: {
                System.out.println("Enter a valid move");
                yield promptPlayerForMove();}
        };
    }
}
