package Backend.GameFlow;

import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Position;
import Backend.Tile.Tile;


import java.util.List;

public class Level {
    private Player p;
    private List<Enemy> enemies;
    private List<Tile> tiles;
    private GameBoard board;
    public boolean gameOver;
    public boolean done;

    public Level(Player p, List<Enemy> t) {
        this.p = p;
        this.enemies = t;
        gameOver = false;
        done = false;
    }

    public void GameTick(char choice) {
        p.onGameTick();
        move(choice);
        if (p.isDead()) {
            p.setMassageCallBack((msg) -> gameOver = true);
        } else {
            for (Enemy e : enemies) {
                e.onGameTick(p);
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
        int x = p.getPosition().getX();
        int y = p.getPosition().getY();
        Position point = new Position(x, y);
        if (move == 'w') {
            p.visit(board.getTile(x, y + 1));
        }
        if (move == 's') {
            p.visit(board.getTile(x, y - 1));
        } else if (move == 'd') {
            p.visit(board.getTile(x + 1, y));
        } else if (move == 'a') {
            p.visit(board.getTile(x - 1, y));
        } else if (move == 'c') {
            p.onAbilityCast(enemies);
        }
    }
}

