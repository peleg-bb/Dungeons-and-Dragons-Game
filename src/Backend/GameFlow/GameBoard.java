package Backend.GameFlow;

import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Position;
import Backend.Tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(new Position(x,y))){
                return t;
            }
        }
        throw new RuntimeException();
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty());
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return "";
    }
}