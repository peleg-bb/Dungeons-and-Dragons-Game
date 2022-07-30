package Backend.Tile.Unit;

import Backend.Interfaces.MassageCallBack;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.StaticTiles.Wall;

public abstract class Unit extends Tile {
    protected String name;
    protected MassageCallBack massageCallBack; // TODO: Implement this
    protected int experience;

    public int getExperience() {
        return experience;
    }

    public Health health;
    public Position p;
    protected int attackPoints;
    protected int defensePoint;

    public Unit(char tile, Position position, String name,int healthA, int attackPoints,
                int defensePoint, int experience) {
        super(tile, position);
        this.name = name;
        this.health = new Health(healthA, healthA);
        this.attackPoints = attackPoints;
        this.defensePoint = defensePoint;
        this.experience = experience;
    }

    public void setMassageCallBack(MassageCallBack massageCallBack) {
        this.massageCallBack = massageCallBack;
    }
    public void initialize( Position p){
        this.position = p;
    }

    public void interact(Tile tile){
        tile.accept(this);
    }
    public void receiveDamage(int d){
        this.health.setHealthAmount(this.health.getHealthAmount()-d);
    }
    public int attack(){
        return (int) (Math.random()*(attackPoints-0)) + 0;
    }
    public int defend(){
        return (int) (Math.random() * (defensePoint));
    }

    public void combat(Unit u){ //u is defender
        massageCallBack.send(" ");
        massageCallBack.send( this.name + " engaged in combat with " + u.name);
        massageCallBack.send(description());
        massageCallBack.send(u.description());
       int attackP = attack();
       massageCallBack.send(this.name+ " rolled " + attackP + " attack points");
       int defenseP = u.defend();
         massageCallBack.send(u.name+ " rolled " + defenseP + " defense points");
       if(attackP - defenseP > 0){
           u.receiveDamage(attackP - defenseP);
           massageCallBack.send(this.name + " dealt " + (attackP - defenseP) + " damage points to " + u.name);
           massageCallBack.send(description());
           massageCallBack.send(u.description());
           if(u.isDead()){
               u.onDeath(this);
               this.onKill(u);
           }
       }
    }

    public abstract void onDeath(Unit unit);

    public abstract void onKill(Unit u);

    public boolean isDead(){
        return this.health.getHealthAmount() <= 0;
    }

    public abstract void accept(Unit unit);
    public abstract void visit(Enemy enemy);
    public abstract void visit(Player p);
    public void visit(Wall wall){};
    public void visit(Empty empty){ swapPositions(empty);};
    public void visit(Tile t){
        t.accept(this);
    }

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
            if (this.healthAmount < 0) {
                this.healthAmount = 0;
            }
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

    }


    public String getName(){
        return name;
    }
    public String description() {
        return name + "         Health: " + health.getHealthAmount() + "/" + health.getHealthPool() +
                "   Attack: " + attackPoints + "     Defense: " + defensePoint;
    }
}
