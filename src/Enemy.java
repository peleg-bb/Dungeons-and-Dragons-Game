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

    public void acceptAttack(Visitor v){
        v.visitAttack(this);
    }
    public void visitAttack(Enemy enemy){
        return;
    }
    public void visitAttack(Player p){
        int attacker = (int) (Math.random()*(attackPoints-0)) + 0;
        int defender = (int) (Math.random()*(defensePoint-0)) + 0;
        combat(attacker,defender,p);
        if(p.health.getHealthAmount()<0){
            this.position = p. position;//swap
            onKill(p);
        }
    }
    public void onKill(Player p){
        this.expirience = this.expirience + p.getExprience();
        p.onDeath();
    }
    public void onDeath(){
        //delete from board
    }
}
