package Backend.Tile.Unit.Player;

import Backend.Tile.Position;
import Backend.Tile.Unit.Enemy.Enemy;

import java.util.List;

public class Rogue extends Player{

    private final String spacialAbility = "Fan of Knives";
    private int cost;
    private int currentEnergy;
    private static final int maxCost = 100;
    public Rogue(String name, Position position, int healthA, int attackPoints, int defensePoint, int cost){
        super(name, position, healthA, attackPoints, defensePoint);
        this.currentEnergy = 100;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.currentEnergy = 100;
        this.attackPoints = this.attackPoints+3*level;
    }

    @Override
    public void onGameTick() {
        this.currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public void onAbilityCast(List<Enemy> enemies) {
        if(currentEnergy<cost){
            massageCallBack.send("Cannot cast ability, not enough energy");
        }
        this.currentEnergy = this.currentEnergy - this.cost;
        for(Enemy e : enemies){
            if(this.position.range(e.position)<2){
                e.health.setHealthPool(e.health.getHealthPool() - attackPoints);
                massageCallBack.send("You attacked " + e.getName() + " for " + attackPoints +
                        " damage using special ability: " + spacialAbility);
            }
        }
    }

    public String description(){
        return super.description() + "      cost: " + this.cost;
    }


}
