package Backend.GameFlow;

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
        System.out.println(board.toString()); // print board via message call back and not system.out.println
        player.onGameTick();
        move(choice);
        if (player.isDead()) {
            player.setMassageCallBack((msg) -> gameOver = true);
        } else {
            for (Enemy e : enemies) {
                e.onGameTick(player);
                if (e.isDead()) {
                    e.setEnemyDeathCallBack(() -> enemies.remove(e));
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
        Position point = new Position(x, y);
        if (move == 'w') {
            player.visit(board.getTile(x, y + 1));
        }
        if (move == 's') {
            player.visit(board.getTile(x, y - 1));
        } else if (move == 'd') {
            player.visit(board.getTile(x + 1, y));
        } else if (move == 'a') {
            player.visit(board.getTile(x - 1, y));
        } else if (move == 'c') {
            player.onAbilityCast(enemies);
        }
    }
}

