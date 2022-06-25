package Backend.Tile.Unit.Enemy;

import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Unit;

public abstract class Enemy extends Unit {

    protected int experience;
    public Enemy(char tile, String name, int healthA, int attackPoints, int defensePoint, int experience){
        super(tile, name, healthA, attackPoints, defensePoint);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }
    public abstract void onGameTick(Player p);

    public void accept(Player p){
        p.visit(this);
    }

    public void accept(Unit u){}
    public void visit(Player p){combat(p);}
    public void visit(Enemy e){ }

//    public void onKill(Player p){ //Not necessary
//        this.experience = this.experience + p.getExperience();
//    }
    public void onDeath(Unit u){
        u.onKill(this);
    }

    public void onKill(Unit u){}



}
