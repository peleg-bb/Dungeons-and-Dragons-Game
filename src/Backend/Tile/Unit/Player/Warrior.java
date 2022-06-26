package Backend.Tile.Unit.Player;

import Backend.Tile.Position;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Unit;

import java.util.List;
public class Warrior extends Player{
    private final String spacial_ability = "Avenger's Shield";
    protected int abilityCoolDown;
    protected int remainingCoolDown;
    public Warrior(String name, Position position, int healthA, int attackPoints, int defensePoint, int coolDown){
        super(name, position, healthA, attackPoints, defensePoint);
        this.abilityCoolDown = coolDown;
        this.remainingCoolDown =0;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.remainingCoolDown = 0;
        health.setValues(1,2);//change
        this.attackPoints = this.attackPoints +2*this.level;
        this.attackPoints = this.defensePoint +level;
    }
    public void onGameTick(){
        this.remainingCoolDown = this.remainingCoolDown-1;
    }
    public void onAbilityCast(List<Enemy> enemies){
        if(this.remainingCoolDown>0){
            //trows the error
        }
        else{
            this.remainingCoolDown = this.abilityCoolDown;
            health.setHealthAmount(Math.min(health.getHealthAmount() +10*defensePoint,health.getHealthPool()));
        }
        for(Enemy e : enemies ){///
            if(this.position.range(e.position)<3){
                interact(e);
                break;
            }
        }
    }
}
