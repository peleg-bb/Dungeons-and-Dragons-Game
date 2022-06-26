package UI;

import Backend.GameFlow.Level;
import Backend.GameFlow.TileFactory;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;

import java.util.Scanner;

public class UserInterface {
    TileFactory tileFactory;

    public UserInterface(){
        this.tileFactory = new TileFactory();
    }

    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        System.out.println("Welcome to Dungeons and dragons!");
        System.out.println("Available players are: ");
        // Choose player
        System.out.println("Choose a player: ");
        Player p = UI.choosePlayer();
        GameFlow game = new GameFlow(p);
    }

    // Let user choose a player
    public Player choosePlayer() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose a player: ");
        return tileFactory.getPlayer();
    }

    public static char acceptInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter your move: ");
        reader.useDelimiter("");
        return reader.next().charAt(0);
    }
}
