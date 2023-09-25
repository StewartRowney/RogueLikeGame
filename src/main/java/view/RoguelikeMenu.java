package view;

import models.Location;
import models.enums.Difficulty;
import models.mapitems.Player;
import service.RoguelikeGame;

import java.util.Scanner;

public class RoguelikeMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        welcomeMessage();
        boolean running = true;

        while(running) {
            running = mainMenu();
        }

        System.out.println("Thanks for playing the Roguelike Game");
    }

    public boolean mainMenu() {
        printMenu();
        return menuLogic();
    }
    public boolean menuLogic() {
        int input = validateIntInput();
        switch(input) {
            case 1 -> {playGame(); return true;}
            case 2 -> {return false;}
            default -> {
                System.out.println("Enter an option from the menu");
                return menuLogic();
            }
        }
    }
    public void playGame() {
        createHeading("Roguelike Game");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Character Symbol (Cannot be X,Z,* or -: ");
        char symbol = validateCharInput();
        System.out.print("Enter Difficulty (0 - easy, 1 - medium, 2 - hard, 3 - legendary): ");
        int difficulty = validateDifficulty();
        System.out.print("Enter map size (min 8, max 20): ");
        int mapSize = validateMapSize();

        Player player = new Player(name, symbol, new Location(0,0));
        RoguelikeGame game = new RoguelikeGame(player, Difficulty.values()[difficulty], mapSize);
        game.run();
    }

    public void welcomeMessage() {
        String welcomeMessage = "Welcome to the Roguelike Game";
        System.out.printf("""
            
            %s
            %s
            Press [Enter] to start
            """, welcomeMessage, "*".repeat(welcomeMessage.length()));

        scanner.nextLine();
    }
    private void createHeading(String heading) {
        System.out.printf("""
                        
                ************ %s *************
                """, heading);
    }
    public void printMenu() {
        createHeading("Roguelike Menu");
        System.out.print("""
                1. Play game
                2. Exit
                """);
    }
    private int validateIntInput() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException nFE) {
            System.out.print("Input a valid option from the menu\nTry again: ");
            return validateIntInput();
        }
    }
    private int validateDifficulty() {
        int number = validateIntInput();
        if (number < 0 || number > 3) {
            System.out.print("Input a valid number\nTry again: ");
            return validateDifficulty();
        }
        else {
            return number;
        }
    }
    private int validateMapSize() {
        int number = validateIntInput();
        if (number < 8 || number > 20) {
            System.out.print("Input a valid number\nTry again: ");
            return validateDifficulty();
        }
        else {
            return number;
        }
    }
    private char validateCharInput() {
        String input = scanner.nextLine();

        if (input.length() == 1) {
            char inputChar = input.charAt(0);
            if (inputChar == 'X' || inputChar == 'Z' || inputChar == '-' || inputChar == '*') {
                System.out.print("Input a valid character\nTry again: ");
                return validateCharInput();
            }
            else {
                return inputChar;
            }
        }
        else {
            System.out.println("Input a single character\nTry again: ");
            return validateCharInput();
        }
    }
}
