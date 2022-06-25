public abstract class Enemy extends Unit{

    protected int experience;
    public Enemy(char tile, String name, int healthA, int attackPoints, int defensePoint, int experience){
        super(tile, name, healthA, attackPoints, defensePoint);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }
    public abstract void onEnemyTurn(Player p);

    public void accept(Visitor v){
        v.visit(this);
    }
    public void visit(Player p){combat(p);}
    public void visit(Enemy e){return; }
    public boolean visit(Empty e){return true;}
    public boolean visit(Wall w){return false;}


    public void onKill(Player p){
        this.experience = this.experience + p.getExperience();

    }
    public void onDeath(Unit u){
        u.onKill(this);
    }


}
