package Backend.GameFlow;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import java.util.List;


public class Level {
    private Player player;
    private List<Enemy> enemies;
    private GameBoard board;
    public boolean gameOver;
    public boolean done;

    public Level(GameBoard board) {
        this.board = board;
        this.player = board.player;
        this.enemies = board.getEnemies();
        gameOver = false;
        done = false;
    }

    public void GameTick(char choice) {
        player.onGameTick();
        move(choice);
        System.out.println(board.toString()); // print board via message call back and not system.out.println
        if (player.isDead()){
            gameOver = true;
            done = true;
            return;
        } else {
            for (Enemy e : enemies) {
                move(e, e.onGameTick(player));
                if (e.isDead()) {
//                    e.setEnemyDeathCallBack(() -> {
//                        enemies.remove(e);
//                        board.remove(e);
//                        System.out.println("Enemy died"); // give name of enemy
//                    });
                    System.out.println("Enemy died, reached e.isDead in Level!"); // give name of enemy
                }
            }
            if (enemies.isEmpty()) {
                done = true;
            }
        }
    }
    public void move(char move) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        if (move == 'd') {
            player.visit(board.getTile(x, y + 1));
        }
        if (move == 'a') {
            player.visit(board.getTile(x, y - 1));
        } else if (move == 's') {
            player.visit(board.getTile(x + 1, y));
        } else if (move == 'w') {
            player.visit(board.getTile(x - 1, y));
        } else if (move == 'c') {
            player.onAbilityCast(enemies);
        }
    }

    public void move(Enemy enemy, char move){
        int x = enemy.getPosition().getX();
        int y = enemy.getPosition().getY();
        if (move == 'd') {
            enemy.visit(board.getTile(x, y + 1));
        }
        if (move == 'a') {
            enemy.visit(board.getTile(x, y - 1));
        } else if (move == 's') {
            enemy.visit(board.getTile(x + 1, y));
        } else if (move == 'w') {
            enemy.visit(board.getTile(x - 1, y));
        }
    }
}

