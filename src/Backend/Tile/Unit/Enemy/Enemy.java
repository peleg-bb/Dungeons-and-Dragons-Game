package Backend.Tile.Unit.Enemy;

import Backend.Interfaces.EnemyDeathCallBack;
import Backend.Tile.Position;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Unit;

public abstract class Enemy extends Unit {


    public EnemyDeathCallBack enemyDeathCallBack;

    public Enemy(char tile, Position position, String name, int healthA, int attackPoints,
                 int defensePoint, int experience){
        super(tile, position, name, healthA, attackPoints, defensePoint, experience);

    } // Can be deleted?

    public int getExperience() {
        return experience;
    }
    public abstract void onGameTick(Player p);

    public void accept(Player p){
        p.visit(this);
    }

    public void accept(Unit u){}
    public void visit(Player p){combat(p);}
    public void visit(Enemy e){}

//    public void onKill(Player p){ //Not necessary
//        this.experience = this.experience + p.getExperience();
//    }

    // set enemyDeathCallBack to a function that will be called when the enemy is dead
    public void setEnemyDeathCallBack(EnemyDeathCallBack enemyDeathCallBack){
        this.enemyDeathCallBack = enemyDeathCallBack;
    }

    public void onDeath(Unit u){
        enemyDeathCallBack.call();
        this.massageCallBack.send(this.getName() + " died.");
    }

    public void onKill(Unit u){
        // send message to player that enemy has killed player
        this.massageCallBack.send(this.getName() + " killed " + u.getName());
    }
}
