package Backend.Interfaces;

import Backend.Tile.Tile;

public interface PositionObservable {
    public void addObserver(PositionObserver o);
    public void removeObserver(PositionObserver o);
    public void notifyObservers(Tile tile, Tile tile2);

}

