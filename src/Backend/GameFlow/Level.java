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
    private boolean gameOver;
    private boolean done;

    public Level(Player p, List<Enemy> t ){
        this.p = p;
        this.enemies = t;
        gameOver = false;
        done = false;
    }
    public void GameTick(){
        p.onGameTick();
        // move()
        if (p.isDead()){
            gameOver = true;
        }
        else{
            for (Enemy e : enemies) {
                e.onGameTick(p);
                if (e.isDead()){
                    e.setEnemyDeathCallBack(()->enemies.remove(e));
                }
            }
        }

        if(enemies.isEmpty()){
            done = true;
        }
    }


    public void move(char move){
        int x = p.getPosition().getX();
        int y = p.getPosition().getY();
        Position point = new Position(x,y);
        if(move == 'w'){
            p.position.setY(y+1);
        }
        if(move == 's'){
            p.position.setY(y-1);
        }
        else if(move == 'd'){
            p.position.setX(x+1);
        }
        else if(move == 'w'){
            p.position.setX(x-1);
        }
        else if(move == 'c'){
            p.onAbilityCast(enemies);
        }
        if(checkPoint()){
            p.position.setPosition(point);
        }
        else{
            FightEnemy();
        }
    }

    private void FightEnemy() {
        for(Enemy e : enemies){
            if(e.position.equals(p.position)){
                p.interact(e);
                if(e.isDead()){
                    //remove from board:)
                }

            }
        }
    }

    public boolean checkPoint(){
        for(Tile t : tiles){
            //I want to know if it's a wall or not
            if(t.position.equals(p.position)){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }
}
