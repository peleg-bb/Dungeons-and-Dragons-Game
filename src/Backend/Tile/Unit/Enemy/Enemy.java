package Backend.Tile.Unit.Enemy;

import Backend.Interfaces.EnemyDeathCallBack;
import Backend.Tile.Position;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Unit;

public abstract class Enemy extends Unit {


    public EnemyDeathCallBack enemyDeathCallBack;

    public Enemy(char tile, Position position, String name, int healthA, int attackPoints,
                 int defensePoint, int experience){
        super(tile, position, name, healthA, attackPoints, defensePoint, experience);
        // In hindsight, I should have used builder pattern, but I wasn't familiar with it while writing this code.
    } // Can be deleted?

    public int getExperience() {
        return experience;
    }
    public abstract char onGameTick(Player p);

//    public void accept(Player p){
//        p.visit(this);
//    }

    public void accept(Unit u){u.visit(this);}

    public void visit(Player p){combat(p);}
    public void visit(Enemy e){}

    // set enemyDeathCallBack to a function that will be called when the enemy is dead
    public void setEnemyDeathCallBack(EnemyDeathCallBack enemyDeathCallBack){
        this.enemyDeathCallBack = enemyDeathCallBack;
    }

    public void onDeath(Unit u){
        this.massageCallBack.send(this.getName() + " died.");
    }

    public void onKill(Unit u){
        // send message to player that enemy has killed player
        this.massageCallBack.send(this.getName() + " killed " + u.getName());
    }
}
