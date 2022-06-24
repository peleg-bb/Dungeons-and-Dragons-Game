public abstract class Unit extends Tile implements Visitor,Visited{
    protected String name;
    protected Health health;
    protected Position p;
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
    public void demage(int d){
        this.health.setHealthAmount(this.health.getHealthAmount()-d);

    }
    public int attack(){
        return (int) (Math.random()*(attackPoints-0)) + 0;
    }
    public int defence(){
        return (int) (Math.random()*(attackPoints-0)) + 0;
    }
    public void combat(Unit u ){ //u is defender
       int attackP = attack();
       int defenseP = defence();
       if(attackP - defenseP >0){
           u.demage(attackP - defenseP);
           if(u.health.getHealthAmount() <=0){
               this.onKill();

           }
       }
    }

    public abstract void onKill();

    public abstract void OnDeath();

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
    }
}
