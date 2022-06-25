package Backend.Tile.StaticTiles;

import Backend.Tile.Tile;
import Backend.Tile.Unit.Unit;

public class Wall extends Tile {
    public Wall(){
        super('#');
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
