package Backend.GameFlow;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.StaticTiles.Wall;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Position;
import Backend.Tile.Tile;
import Backend.Tile.Unit.Enemy.Monster;
import Backend.Tile.Unit.Enemy.Trap;
import Backend.Tile.Unit.Player.Mage;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;
import Backend.Tile.Unit.Player.Warrior;
import Backend.Tile.Unit.Unit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private Tile[][] board;
    private Player player;
    private List<Tile> tiles;
    private List<Tile> enemies;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile getTile(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(new Position(x,y))){
                return t;
            }
        }
        throw new RuntimeException("Attempting to access a tile which doesn't exist.");
    }

    public void setLevel(List<String> lines){

        board = new Tile[lines.size()][lines.get(0).length()]; // The Board
        for (int x = 0 ; x < lines.size(); x++) {
            for (int y = 0; y < lines.get(0).length(); y++) {
                char tileType = lines.get(x).charAt(y);
                Tile newTile;
                switch (tileType) {
                    case '.' -> newTile = new Empty(new Position(x, y));
                    case '#' -> newTile = new Wall(new Position(x, y));
                    case '@' -> player.setPosition(new Position(x, y));
                    case 's' -> {
                        newTile = new Monster('s', new Position(x, y), "Lannister Solider", 80, 8, 3, 25, 3);
                        enemies.add(newTile);
                    }
                    case 'k' -> {
                        newTile = new Monster('k', new Position(x, y), "Lannister Knight", 200, 14, 8, 50, 4);
                        enemies.add(newTile);
                    }
                    case 'q' -> {
                        newTile = new Monster('q', new Position(x, y), "Queen's Guard", 400, 20, 15, 5, 100);
                        enemies.add(newTile);
                    }
                    case 'z' -> {
                        newTile = new Monster('z', new Position(x, y), "Wright", 600, 30, 15, 3, 100);
                        enemies.add(newTile);
                    }
                    case 'b' -> {
                        newTile = new Monster('b', new Position(x, y), "Bear-Wright", 1000, 75, 30, 4, 250);
                        enemies.add(newTile);
                    }
                    case 'g' -> {
                        newTile = new Monster('g', new Position(x, y), "Giant-Wright", 1500, 100, 40, 5, 500);
                        enemies.add(newTile);
                    }
                    case 'w' -> {
                        newTile = new Monster('w', new Position(x, y), "White Walker", 2000, 150, 50, 6, 1000);
                        enemies.add(newTile);
                    }
                    case 'M' -> {
                        newTile = new Monster('M', new Position(x, y), "The Mountain", 1000, 60, 25, 6, 500);
                        enemies.add(newTile);
                    }
                    case 'C' -> {
                        newTile = new Monster('C', new Position(x, y), "Queen Cersei", 100, 10, 10, 1, 1000);
                        enemies.add(newTile);
                    }
                    case 'K' -> {
                        newTile = new Monster('K', new Position(x, y), "Knight's King", 5000, 300, 150, 8, 5000);
                        enemies.add(newTile);
                    }
                    case 'B' -> {
                        newTile = new Trap('B', new Position(x, y), "Bonus Trap", 1, 1, 1, 250, 1, 5);
                        enemies.add(newTile);
                    }
                    case 'Q' -> {
                        newTile = new Trap('Q', new Position(x, y), "Queen's Trap", 250, 50, 10, 100, 3, 7);
                        enemies.add(newTile);
                    }
                    case 'D' -> {
                        newTile = new Trap('D', new Position(x, y), "Death Trap", 500, 100, 20, 250, 1, 10);
                        enemies.add(newTile);
                    }
                }
            }
        }
    }


    public void createPlayer(int playerChoice, Position playerPosition) {

        switch (playerChoice) {
            case 1:
                this.player = new Warrior("Jon Snow", playerPosition, 300, 30, 4, 3);
            case 2:
                this.player = new Warrior("The Hound", playerPosition, 400, 20, 6, 5);
            case 3:
                this.player = new Mage("Melisandre", playerPosition, 100, 5, 1, 15, 300, 30, 5,6);
            case 4:
                this.player = new Mage("Thoros of Myr", playerPosition, 250, 25, 4, 20, 150, 20, 3, 4);
            case 5:
                this.player = new Rogue("Arya Stark", playerPosition, 150, 40, 2, 20);
            case 6:
                this.player = new Rogue("Bronn", playerPosition, 250, 35, 3, 50);
//            case 7:
//                this.player = new Hunter(playerPosition, "Ygritte", new Health(220), 30, 2, 6);
        }

    }
    public void remove(Enemy e) {
        enemies.remove(e);
        tiles.remove(e);
        Position p = e.getPosition();
        board[e.getPosition().getX()][e.getPosition().getY()] = new Empty(e.getPosition());
    }

    @Override
    public String toString() {
        String sBoard="";
        for (int i=0;i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                sBoard+=board[i][j].toString();
            }
            sBoard+="\n";
        }
        return sBoard;
    }



}