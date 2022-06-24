public abstract class Enemy extends Unit{
    protected int expirience;
    public Enemy(char tile, String name, int healthA, int attackPoints, int defensePoint, int expirience){
        super(tile, name, healthA, attackPoints, defensePoint);
        this.expirience = expirience;
    }

    public int getExpirience() {
        return expirience;
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
        this.expirience = this.expirience + p.getExprience();
        p.onDeath();
    }
    public void onDeath(){
        //delete from board
    }
}
