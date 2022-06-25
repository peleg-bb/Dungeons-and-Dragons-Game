package Backend.Interfaces;

import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.StaticTiles.Wall;

public interface Visitor {
    public void visit(Enemy enemy);
    public void visit(Player player);
    public boolean visit(Empty e);
    public boolean visit(Wall w);

}
