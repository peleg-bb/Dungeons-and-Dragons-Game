package Backend.Tile.Unit;

import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.StaticTiles.Wall;

public abstract class Unit extends Tile {
    protected String name;
    protected int experience;

    public int getExperience() {
        return experience;
    }

    public Health health;
    public Position p;
    protected int attackPoints;
    protected int defensePoint;

    public Unit(char tile, String name,int healthA, int attackPoints, int defensePoint){
        super(tile);
        this.name = name;
        this.health = new Health(healthA, healthA);
        this.attackPoints = attackPoints;
        this.defensePoint = defensePoint;
    }
    public void initialize( Position p){
        this.position = p;
    }

    public void interact(Tile tile){
        tile.accept(this);
    }
    public void damage(int d){
        this.health.setHealthAmount(this.health.getHealthAmount()-d);

    }
    public int attack(){
        return (int) (Math.random()*(attackPoints-0)) + 0;
    }
    public int defend(){
        return (int) (Math.random()*(attackPoints-0)) + 0;
    }

    public void combat(Unit u){ //u is defender
       int attackP = attack();
       int defenseP = defend();
       if(attackP - defenseP >0){
           u.damage(attackP - defenseP);
           if(u.health.getHealthAmount() <=0){
               u.onDeath(this);
           }
       }
    }

    public abstract void onDeath(Unit unit);

    public abstract void onKill(Unit u);

    public boolean isDead(){
        return this.health.getHealthAmount() <= 0;};

    public abstract void accept(Unit unit);
    public abstract void visit(Enemy enemy);
    public abstract void visit(Player p);
    public void visit(Wall wall){};
    public void visit(Empty empty){
        swapPositions(empty);
    };

    public class Health {

        protected int healthPool;
        protected int healthAmount;
        public Health(int p, int a){
            this.healthPool = p;
            this.healthAmount = a;
        }
        public void setValues(int healthAmount, int healthPool){
            this.healthAmount = healthAmount;
            this.healthPool = healthPool;
        }

        public void setHealthAmount(int healthAmount) {
            this.healthAmount = healthAmount;
        }

        public void setHealthPool(int healthPool) {
            this.healthPool = healthPool;
        }

        public int getHealthAmount() {
            return healthAmount;
        }

        public int getHealthPool() {
            return healthPool;
        }

        //returns true if player is dead
        public boolean isDead(){
            return healthAmount <= 0;
        }
    }


}
