package Backend.GameFlow;
import Backend.GameFlow.GameBoard;
import Backend.GameFlow.Level;
import Backend.Interfaces.Observable;
import Backend.Interfaces.Observer;
import Backend.Tile.Position;
import UI.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameFlow implements Observable {
    private List<Observer> observers;
    public boolean gameOver;
    List<List<String>> levels = new ArrayList<List<String>>();
    int currentLevelIndex = 0;
    private GameBoard gameBoard;
    private boolean hasNextLevel; // Temporary, will read from levels file
    Scanner reader = new Scanner(System.in);

    public GameFlow(int playerChoice, List<List<String>> levels, UserInterface UI) { // Recieves a player from UI
        gameOver = false;
        this.observers = new ArrayList<Observer>();
        this.addObserver(UI);
        this.levels = levels;
        this.gameBoard = new GameBoard();
        gameBoard.createPlayer(playerChoice, new Position(0, 0));
        while (!gameOver && !levels.isEmpty()) {
            for (List<String> level : levels) {
                String currentLevelName = "Current level: " + currentLevelIndex;
                notifyObservers(currentLevelName);// print?
                gameBoard.setLevel(level);
                Level currentLevel = new Level(gameBoard);
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

    @Override
    public void notifyObservers(String msg) {
        for (Observer o : observers){
            o.sendMessage(msg);
        }
    }


}

