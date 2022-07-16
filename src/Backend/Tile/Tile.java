package Backend.Tile;

import Backend.Interfaces.PositionObservable;

import Backend.Interfaces.PositionObserver;
import Backend.Tile.Unit.Unit;

import java.util.ArrayList;

public abstract class Tile implements Comparable<Tile>, PositionObservable {
    protected char tile;
    public Position position;
    protected ArrayList<PositionObserver> observers;

    public Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
        observers = new ArrayList<PositionObserver>();
    }
//    public void initialize(int x, int y){
//        position = new Position(x,y);
//    }
    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }


    public abstract void accept(Unit unit);

    // swaps positions of two tiles
    public void swapPositions(Tile tile){
        Position temp = this.position;
        this.position = tile.getPosition();
        tile.setPosition(temp);
        // notify observers of position change
        notifyObservers(this, tile);

    }

    public void notifyObservers(Tile tile, Tile tile2){
        for (PositionObserver observer : observers) {
            observer.update(tile, tile2);
        }
    }

    public void addObserver(PositionObserver o){
        observers.add(o);
    }
    public void removeObserver(PositionObserver o){
        // do nothing
    }




}

