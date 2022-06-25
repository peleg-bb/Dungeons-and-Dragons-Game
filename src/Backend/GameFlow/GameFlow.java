package Backend.GameFlow;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import java.util.List;

public class GameFlow {
    private Level currentLevel;
    private Player player;
    private boolean gameOver;
    private List<Enemy> enemieslevelEnemies;
    private List<List<String>> levels;
    private int currentLevelIndex;
    private GameBoard gameBoard;


    public GameFlow(){
        // TODO: Implement me
        // this.gameBoard = new GameBoard();
    }
}
