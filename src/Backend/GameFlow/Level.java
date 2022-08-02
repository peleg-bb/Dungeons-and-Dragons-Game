package Backend.GameFlow;
import Backend.Interfaces.Observable;
import Backend.Interfaces.Observer;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Position;
import Backend.Tile.Tile;

import java.util.ArrayList;
import java.util.List;
import UI.UserInterface;


public class Level implements Observable {
    private List<Observer> observers;
    private Player player;
    private List<Enemy> enemies;
    private GameBoard board;
    public boolean gameOver;
    public boolean done;

    public Level(GameBoard board, UserInterface UI) {
        this.observers = new ArrayList<Observer>();
        addObserver(UI);
        this.board = board;
        this.player = board.player;
        this.enemies = board.getEnemies();
        gameOver = false;
        done = false;
    }

    public void GameTick(char choice) {
        player.onGameTick();
        move(choice);
        for (Enemy e : enemies) {
            move(e, e.onGameTick(player));
            if (player.isDead()) {
                notifyObservers(board.toString());
                gameOver = true;
                done = true;
                return;
            }
        }
        if (enemies.isEmpty()) {
            done = true;
            return;
        }

        notifyObservers(board.toString());
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

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)){
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers(char choice) {

    }

    @Override
    public void notifyObservers(int choice) {

    }

    @Override
    public void notifyObservers(List<List<String>> lines) {

    }

    @Override
    public void notifyObservers(String msg) {
        for (Observer o : observers){
            o.sendMessage(msg);
        }
    }
}

