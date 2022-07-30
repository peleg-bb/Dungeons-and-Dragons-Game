package Backend.Tile.Unit.Enemy;

import Backend.Tile.Position;
import Backend.Tile.Unit.Player.Player;



public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, Position position, String name, int healthA, int attackPoints,
                   int defensePoint, int visionRange, int expirience){
        super(tile,position, name,healthA, attackPoints, defensePoint, expirience);
        this.visionRange = visionRange;
    }
    public char onGameTick(Player p){
        if(inRange(p)){

            int dx = this.position.getX()-p.position.getX();
            int dy = this.position.getY()-p.position.getY();
            if(dy==0) {
                if(dx>0){
                    return 'w';
                }
                else{
                    return 's';
                }
            }
            else if(dx==0){
                if(dy<0){
                    return 'd';
                }
                else{
                    return 'a';
                }
            }
            else if(Math.abs(dx)<Math.abs(dy)){ // BUG: This is not correct
                if(dx>0){
                    return 'w';
                }
                else{
                    return 's';
                }
            }
            else{
                if(dy<0){
                    return 'd';
                }
                else{
                    return 'a';
                }
            }
        }
        else{
            int move = (int) (Math.random() * 5);
            // generate random integer between 0 and 4
            switch(move){
                case 0: break;
                case 1: return 'w';
                case 2 : return 's';
                case 3 : return 'd';
                case 4 : return 'a';
            }
        }
        return ' ';
    }

    public boolean inRange(Player p){
        return this.position.range(p.position)<visionRange;
    }

    public String description(){
        return super.description() + "     Experience Value: " + this.experience + "     Vision Range: " + this.visionRange;
    }

}
