package Backend.GameFlow;
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
    List<List<String>> levels;
    int currentLevelIndex = 1;


    public GameFlow(int playerChoice, List<List<String>> levels, UserInterface UI) { // Recieves a player from UI
        gameOver = false;
        this.observers = new ArrayList<Observer>();
        this.addObserver(UI);
        this.levels = levels;
        GameBoard gameBoard = new GameBoard();
        gameBoard.createPlayer(playerChoice, new Position(0, 0));
        int i = 0;
        while (i < levels.size()) {
            while (!gameOver) {
                String currentLevelName = "Current level: " + currentLevelIndex;
                currentLevelIndex++;
                notifyObservers(currentLevelName);
                // TODO: Check if bug is here
                gameBoard.setLevel(levels.get(i));
                Level currentLevel = new Level(gameBoard);
                notifyObservers(gameBoard.toString());
                while (!currentLevel.done) {
                    if (currentLevel.gameOver) {
                        gameOver = true;
                        break;
                    }
                    char userChoice = UserInterface.acceptInput();
                    currentLevel.GameTick(userChoice);
                    if (currentLevel.gameOver) {
                        gameOver = true;
                        break;
                    }
                }
                i++;
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

