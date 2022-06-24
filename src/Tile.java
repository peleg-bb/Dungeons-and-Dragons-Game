public abstract class Tile implements Comparable<Tile>{
    protected char tile;
    protected Position position;

    public Tile(char tile){
        this.tile = tile;
    }
    public void initialize(int x, int y){
        position = new Position(x,y);
    }
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
}

