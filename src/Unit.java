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


    public void attack(Unit unit){
         unit.acceptAttack(this);
    }

    public void combat(int attaker, int defender, Unit u ){ //u is defender
        if(attaker  - defender > 0){
            u.health.setHealthPool(health.getHealthPool() - (attaker  - defender));
        }
        return;
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
