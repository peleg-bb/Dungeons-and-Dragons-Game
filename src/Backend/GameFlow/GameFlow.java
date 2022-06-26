package Backend.GameFlow;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Mage;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;

import java.util.List;

public class GameFlow {
    private Level currentLevel;
    private Player player;
    private boolean gameOver;
    private List<Enemy> levelEnemies;
    private List<String> levelNames;
    private int currentLevelIndex = 1;
    private GameBoard gameBoard;
    private boolean hasNextLevel; // Temporary, will read from levels file



    public GameFlow(Player p){ // Recieves a player from UI
        this.gameBoard = new GameBoard(new Tile[4][4]);
        while (!gameOver){
            for (int i = 0; i < levelNames.size(); i++) {
                String currentLevelName = "Current level: " + currentLevelIndex;

                Level currentLevel = new Level(p, levelEnemies);
                while (!currentLevel.gameOver){
                while (!currentLevel.done) {
                    char userChoice = 'w'; // Temp, should read from UI
                    currentLevel.GameTick(userChoice);
                    }
                }
                gameOver = true;
            }
        }


        }
    }
}
