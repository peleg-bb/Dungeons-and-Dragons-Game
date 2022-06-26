package Backend.Tile.StaticTiles;

import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Unit;

public class Empty extends Tile {

    public Empty(Position position){
        super('.', position);
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
