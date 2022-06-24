import java.util.List;

public abstract class Player extends Unit{
    protected int exprience;
    protected int level;
    public Player(String name, int healthA, int attackPoints, int defensePoint){
        super('@', name, healthA, attackPoints, defensePoint);
        this.exprience = 0;
        this.level =1;
    }

    public void setExprience() {
        this.exprience = this.exprience +1;
        if(this.exprience>= 50*this.level){
            levelUp();
        }
    }
    public int getExprience(){
        return this.exprience;
    }

    public void levelUp(){
        this.exprience = this.exprience - 50*this.level;
        this.level++; //ask
        health.setValues(this.health.getHealthPool(),this.health.getHealthPool()+10*level);
        this.attackPoints = this.attackPoints + 4*this.level;
        this.defensePoint = this.defensePoint + this.level;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
    public void visit(Player p){return;}
    public void visit(Enemy e){ combat(e);}
    public boolean visit(Empty e){return true;}
    public boolean visit(Wall w){return false;}


    public void onKill(Enemy e){
        this.exprience = this.exprience + e.getExpirience();
        e.onDeath();
    }
    public void onDeath(){
        this.tile = 'X';
        //send game is over
    }
    public abstract void onAbilityCast(List<Enemy> enemies);
    public abstract void onGameTick();
}
