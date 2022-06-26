package UI;
import Backend.GameFlow.GameBoard;
import Backend.GameFlow.Level;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Mage;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;

import java.util.List;
import java.util.Scanner;

public class GameFlow {
    private Level currentLevel;
    private Player player;
    public boolean gameOver;
    private List<Enemy> levelEnemies;
    List<String> levelNames;
    int currentLevelIndex = 1;
    private GameBoard gameBoard;
    private boolean hasNextLevel; // Temporary, will read from levels file


    public GameFlow(Player p){ // Recieves a player from UI
        this.gameBoard = new GameBoard(new Tile[][]{});
        while (!gameOver){
            for (int i = 0; i < levelNames.size(); i++) {
                String currentLevelName = "Current level: " + currentLevelIndex;
                Level currentLevel = new Level(p, levelEnemies);
                while (!currentLevel.gameOver){
                    char userChoice = UserInterface.acceptInput();
                    currentLevel.GameTick(userChoice);
                }
                gameOver = true;
            }
        }
    }
}

