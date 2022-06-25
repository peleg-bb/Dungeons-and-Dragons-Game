public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;
    public Trap(char tile, String name,  int healthA, int attackPoints, int defensePoint, int experience, int visibilityTime, int invisibilityTime){
        super(tile, name, healthA, attackPoints, defensePoint, experience);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this. tickCount = 0;
        this.visible = true;
    }

    @Override
    public void onGameTick(Player p) {
        visible = (tickCount<visibilityTime);
        if(tickCount == (visibilityTime+invisibilityTime)){
            tickCount = 0;
        }
        else{
            tickCount = tickCount + 1;
        }
        if(this.position.range(p.position)<2){
            interact(p);
        }
    }

}
