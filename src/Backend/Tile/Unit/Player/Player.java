package Backend.Tile.Unit.Player;

import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Unit;

import java.util.List;

public abstract class Player extends Unit {
    protected int experience;
    protected int level;
    public Player(String name, int healthA, int attackPoints, int defensePoint){
        super('@', name, healthA, attackPoints, defensePoint);
        this.experience = 0;
        this.level =1;
    }

    public void setExperience() {
        this.experience = this.experience +1;
        if(this.experience >= 50*this.level){
            levelUp();
        }
    }
    public int getExperience(){
        return this.experience;
    }

    public void levelUp(){
        this.experience = this.experience - 50*this.level;
        this.level++; //ask
        health.setValues(this.health.getHealthPool(),this.health.getHealthPool()+10*level);
        this.attackPoints = this.attackPoints + 4*this.level;
        this.defensePoint = this.defensePoint + this.level;
    }

    public void accept(Unit u){
        u.visit(this);
    }
    public void visit(Player p){}
    public void visit(Enemy e){
        super.combat(e);
        if (this.isDead()){
            this.onDeath(e);
        }
    }

    public void onKill(Unit u){
        this.experience = this.experience + u.getExperience();

    }
    public void onDeath(Unit u){
        this.tile = 'X';
        u.onKill(this);
        // MessageCallBack
    }
    public abstract void onAbilityCast(List<Enemy> enemies);

    public void onGameTick(){
        this.setExperience();
    };


}
