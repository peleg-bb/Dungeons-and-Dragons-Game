package Backend.Interfaces;

import Backend.Tile.Tile;

// Observer pattern interface
public interface PositionObserver {
    void update(Tile tile, Tile tile2);
}
