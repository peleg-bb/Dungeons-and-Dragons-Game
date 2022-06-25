package Backend.Tile.Unit.Player;

import Backend.Tile.Unit.Enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Player{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;
    private final String spacial_ability = "Blizzard";

    //there are fields which I am not sure are supposed to be in the Constructor
    private Mage(char tile, String name, int healthA, int attackPoints, int defensePoint,int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(name, healthA, attackPoints, defensePoint);
        this.manaPool = manaPool;
        this.currentMana = this.manaPool/4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.abilityRange = abilityRange;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.manaPool = this.manaPool + 25*level;
        this.currentMana = Math.min(this.currentMana+this.manaPool/4, this.manaPool);
        this.spellPower = this.spellPower + 10*level;
    }
    @Override
    public void onGameTick(){
        super.onGameTick();
        this.currentMana = Math.min(this.manaPool, this.currentMana + level);
    }

    public void onAbilityCast(List<Enemy> enemies){
        if(currentMana<manaCost){
            throw new IllegalArgumentException("can not cast ability");
        }
        this.currentMana = this.currentMana - this.manaCost;
        int hits = 0 ;
        List<Enemy> enemy  = new ArrayList<>();
        for(Enemy e : enemies){
            if(position.range(e.position) < abilityRange){
                enemy.add(e);
            }
        }
        while(hits<hitsCount & enemy.isEmpty()){
            int index = (int) (Math.random()*(enemies.size()-0)) + 0;
            Enemy toAttack = enemy.get(index);
            toAttack.health.setHealthPool(toAttack.health.getHealthPool() - spellPower);

            hits = hits + 1;
        }
    }


    }

