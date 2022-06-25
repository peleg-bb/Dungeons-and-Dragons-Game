package Backend.Tile.Unit.Player;

import Backend.Tile.Unit.Enemy.Enemy;

import java.util.List;

public class Rogue extends Player{

    private final String spacialAbility = "Fan of Knives";
    private int cost;
    private int currentEnergy;
    private static final int maxCost = 100;
    public Rogue(String name, int healthA, int attackPoints, int defensePoint, int cost){
        super(name, healthA, attackPoints, defensePoint);
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
            throw new IllegalArgumentException("can not cast ability");
        }
        this.currentEnergy = this.currentEnergy - this.cost;
        for(Enemy e : enemies){
            if(this.position.range(e.position)<2){
                e.health.setHealthPool(e.health.getHealthPool() - attackPoints);
            }
        }
    }


}
