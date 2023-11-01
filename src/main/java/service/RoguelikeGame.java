package service;

import models.GameStatus;
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
    private final GameStatus gameFinished = new GameStatus();

    public RoguelikeGame(Player player, Difficulty difficulty, int mapSize) {
        this.player = player;
        this.difficulty = difficulty;
        this.map = new Map(mapSize);
    }

    public void run() {
        gameSetUp();
        while (!gameFinished.getGameFinished()) {
            playerMove();
            moveZombies();
            map.printMapToConsole();
        }
        System.out.println(gameFinished.getMessage());
    }

    private void gameSetUp() {
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
        player.setLocation(new Location(2,2));
        map.setUpLocation(player);
    }
    public void createFinishPoint() {
        FinishPoint finishPoint = new FinishPoint(new Location(map.getSize() - 3, map.getSize() - 3));
        map.setUpLocation(finishPoint);
    }
    public void createZombies() {
        int number = (difficulty.ordinal() + 1) * (map.getSize() - 4)/2;
        for (int i = 0; i < number; i++) {
            Zombie zombie = new Zombie(map.selectRandomFreeLocation());
            zombies.add(zombie);
            map.setUpLocation(zombie);
        }
    }

    public void moveZombies() {
        for (Zombie zombie : zombies) {
            tryMoveZombie(zombie);
        }
    }

    public void tryMoveZombie(Zombie zombie) {
        Direction direction = Direction.selectRandomDirection();
        Location targetLocation = direction.getNextLocation(zombie.getLocation(), zombie.getSpeed());
        if (map.getMapItem(targetLocation).getClass() == Player.class) {
            gameFinished.setMessage("****YOU LOSE****\nYou were eaten by a zombie\nBetter luck next time " + player.getName());
            gameFinished.finishGame();
            map.changeLocation(zombie, targetLocation);
        }
        else if (map.getMapItem(targetLocation).getClass() != DefaultMapItem.class) {
            tryMoveZombie(zombie);
        }
        else {
            map.changeLocation(zombie, targetLocation);
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

        if (map.getMapItem(targetLocation) instanceof FinishPoint) {
            gameFinished.finishGame();
            gameFinished.setMessage("****YOU WIN****\nCongratulations " + player.getName());
            return playerDirection;
        }
        else if (map.getMapItem(targetLocation) instanceof Zombie zombie) {
            zombies.remove(zombie);
            System.out.println("You have slain a zombie");
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
