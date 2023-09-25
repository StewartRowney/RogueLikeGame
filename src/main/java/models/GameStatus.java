package models;

public class GameStatus {
    private boolean gameFinished = false;
    private String message = "problem with game loop";

    public String getMessage() {
        return message;
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public void finishGame() {
        gameFinished = true;
    }

    public void setMessage(String string) {
        message = string;
    }
}
