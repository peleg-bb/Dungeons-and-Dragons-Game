package UI;
import Backend.GameFlow.GameBoard;
import Backend.GameFlow.Level;
import Backend.Interfaces.Observable;
import Backend.Interfaces.Observer;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Mage;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;
import Backend.Tile.Unit.Player.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class GameFlow implements Observable {
    private List<Observer> observers;
    public boolean gameOver;
    List<List<String>> levels = new ArrayList<List<String>>();
    int currentLevelIndex = 0;
    private GameBoard gameBoard;
    private boolean hasNextLevel; // Temporary, will read from levels file
    Scanner reader = new Scanner(System.in);

    public GameFlow(int playerChoice) { // Recieves a player from UI
        gameOver = false;
        while (!gameOver && !levels.isEmpty()) {
            String currentLevelName = "Current level: " + currentLevelIndex; // print?
            gameBoard.createPlayer(playerChoice, new Position(0, 0));
            for (List<String> level : levels) {
                gameBoard.setLevel(level);
                Level currentLevel = new Level(gameBoard.player, gameBoard.getEnemies());
                while (!currentLevel.gameOver) {
                    char userChoice = UserInterface.acceptInput();
                    currentLevel.GameTick(userChoice);
                }
                gameOver = true;
            }
        }
    }


    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)){
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers(char choice) {
        for (Observer o : observers){
            o.update(choice);
        }
    }

    @Override
    public void notifyObservers(int choice) {
        for (Observer o : observers){
            o.update(choice);
        }
    }

    @Override
    public void notifyObservers(List<List<String>> lines) {
        for (Observer o : observers){
            o.update(lines);
        }
    }
}

