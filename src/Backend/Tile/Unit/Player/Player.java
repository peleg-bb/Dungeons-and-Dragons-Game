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
    public Player(String name, Position position, int healthA , int attackPoints,
                  int defensePoint){
        super('@', position, name, healthA, attackPoints,
                defensePoint, 0); //experience is 0 because it is a player
        this.level = 1;
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
        health.setValues(this.health.getHealthPool(),this.health.getHealthPool()+10*level);
        this.attackPoints = this.attackPoints + 4*this.level;
        this.defensePoint = this.defensePoint + this.level;
    }

    public void accept(Unit u){
        u.visit(this);
    }
    public void visit(Player p){
        System.out.println("Player " + this.name + " is trying to visit Player " + p.name);
    }
    public void visit(Enemy e){
        super.combat(e);
        if (this.isDead()){
            this.onDeath(e);
        }
    }


    public void onKill(Unit u){
        this.experience = this.experience + u.getExperience();
        this.massageCallBack.send("You killed " + u.getName()); // Needs to be instantiated and I hate it
        this.setPosition(u.getPosition());
    }
    public void onDeath(Unit u){
        this.tile = 'X';
        this.massageCallBack.send("You died");
        u.onKill(this);
        // End game
    }
    public abstract void onAbilityCast(List<Enemy> enemies);

    public void onGameTick(){
        this.setExperience();
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



}
