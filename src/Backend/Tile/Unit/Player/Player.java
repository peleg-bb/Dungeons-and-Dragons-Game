package Backend.Tile.Unit.Player;
import Backend.Interfaces.MassageCallBack;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Unit;

import java.util.List;

public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    protected int experienceRequired;
    public Player(String name, Position position, int healthA , int attackPoints,
                  int defensePoint){
        super('@', position, name, healthA, attackPoints,
                defensePoint, 0); //experience is 0 because it is a player
        this.level = 1;
        experienceRequired = 50*level;
    }

    public void setExperience() {
        this.experience = this.experience + 1;
        if(this.experience >= 50*this.level){
            levelUp();
        }
    }
    public int getExperience(){
        return this.experience;
    }

    public void levelUp(){
        this.experience = this.experience - 50*this.level;
        this.level++; // ask
        experienceRequired = 50*level;
        health.setValues(this.health.getHealthPool()+10*level,this.health.getHealthPool()+10*level);
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
        this.massageCallBack.send(this.name + " gained " + u.getExperience() +
                " experience points by killing " + u.getName());
    }
    public void onDeath(Unit u){
        this.tile = 'X';
        u.onKill(this);
        this.massageCallBack.send("You died. Game Over!");
        // End game
    }
    public abstract void onAbilityCast(List<Enemy> enemies);

    public void onGameTick(){
        this.setExperience();
        this.massageCallBack.send(description());
    }
//
//    public void move(char move, List<Enemy> enemies){ // Do not use this method
//        int x = this.getPosition().getX();
//        int y = this.getPosition().getY();
//        Position point = new Position(x,y);
//        if(move == 'w'){
//            this.position.moveUp();
//        }
//        if(move == 's'){
//            this.position.moveDown();
//        }
//        else if(move == 'd'){
//            this.position.moveRight();
//        }
//        else if(move == 'w'){
//            this.position.moveLeft();
//        }
//        else if(move == 'c'){
//            this.onAbilityCast(enemies);
//        }
//
//
//    }

    public String description() {
        return super.description() + "     Level: " + level + "     Experience: " + experience + "/" + experienceRequired;
    }



}
