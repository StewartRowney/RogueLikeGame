package service;

import models.Location;
import models.Map;
import models.enums.Difficulty;
import models.enums.Direction;
import models.mapitems.DefaultMapItem;
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

    public RoguelikeGame(Player player, Difficulty difficulty, int mapSize) {
        this.player = player;
        this.difficulty = difficulty;
        this.map = new Map(mapSize);
    }

    public void run() {
        gameSetUp();
        boolean gameOver = false;

        while (!gameOver) {
            gameOver = gameTurn();
        }
    }

    private boolean gameTurn() {
        promptPlayerForMove();
        return true;
    }

    private void gameSetUp() {
        map.initialiseMap();
        player.setLocation(map.selectRandomFreeLocation());
        map.changeMapItem(player);
        createZombies();
        map.printMapToConsole();
    }

    public void createZombies() {
        int number = (difficulty.ordinal() + 1) * 3;
        for (int i = 0; i < number; i++) {
            Zombie zombie = new Zombie(map.selectRandomFreeLocation());
            zombies.add(zombie);
            map.changeMapItem(zombie);
        }
    }

    private void playerMove() {
        Direction playerDirection = validatePlayerMove();

    }

    private Direction validatePlayerMove() {
        Direction playerDirection = promptPlayerForMove();
        Location targetLocation = playerDirection.getNextLocation(player.getLocation(), player.getSpeed());

        if (map.getMapItem(targetLocation).getClass() != DefaultMapItem.class) {

        }
        return null;
    }

    private Direction promptPlayerForMove() {
        System.out.print("""
                Make a move:
                N - North
                E - East
                S - South
                W - West
                """);
        String input = scanner.nextLine();

        return switch(input.trim().toLowerCase()) {
            case "n": yield Direction.NORTH;
            case "e": yield Direction.EAST;
            case "s": yield Direction.SOUTH;
            case "w": yield Direction.WEST;
            default: {
                System.out.println("Enter a valid move");
                yield promptPlayerForMove();}
        };
    }
}
