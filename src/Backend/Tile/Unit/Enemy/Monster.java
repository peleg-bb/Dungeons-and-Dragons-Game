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
    public char onGameTick(Player p){ // TODO: Make it visit the tile around rather than just change position!
        if(this.position.range(p.position)<visionRange){
            int dx = this.position.getX()-p.position.getX();
            int dy = this.position.getY()-p.position.getY();
            if(Math.abs(dx)>Math.abs(dy)){
                if(dx>0){
                    return 'w'; // TODO: Check if it works
                }
                else{
                    return 's';
                }
            }

            else{
                if(dy>0){
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

}
