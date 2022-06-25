package Backend.Tile.StaticTiles;

import Backend.Tile.Tile;
import Backend.Tile.Unit.Unit;

public class Empty extends Tile {

    public Empty(){
        super('.');
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
