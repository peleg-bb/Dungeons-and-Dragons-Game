package UI;

import Backend.GameFlow.Level;
import Backend.GameFlow.TileFactory;
import Backend.Interfaces.Observer;
import Backend.Tile.Unit.Player.Player;
import Backend.Tile.Unit.Player.Rogue;

import java.util.List;
import java.util.Scanner;

public class UserInterface implements Observer {
    TileFactory tileFactory;
    Scanner reader = new Scanner(System.in);

    public UserInterface(){
        this.tileFactory = new TileFactory();
    }

    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        System.out.println("Welcome to Dungeons and dragons!");
        System.out.println("Available players are: ");
        int choice = UI.choosePlayer();
        GameFlow game = new GameFlow(choice);
    }

    // Let user choose a player
    public int choosePlayer() {
        System.out.println("1. Jon Snow1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1");
        System.out.println("        Experience: 0/50                Cooldown: 0/3");
        System.out.println("2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1");
        System.out.println("        Experience: 0/50                Cooldown: 0/5");
        System.out.println("3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1");
        System.out.println("        Experience: 0/50                Mana: 75/300            Spell Power: 15");
        System.out.println("4. Thoros of Myr                Health: 250/250         Attack: 25              Defense: 4              Level: 1");
        System.out.println("                Experience: 0/50                Mana: 37/150            Spell Power: 20");
        System.out.println("5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1");
        System.out.println("        Experience: 0/50                Energy: 100/100");
        System.out.println("6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1");
        System.out.println("        Experience: 0/50                Energy: 100/100");
        System.out.println("7. Ygritte              Health: 220/220         Attack: 30              Defense: 2              Level: 1");
        System.out.println("        Experience: 0/50                Arrows: 10              Range: 6");
        System.out.println("Select player:");
        while (true) {
            try {
                int choice = reader.nextInt();
                while (choice < 1 || choice > 7) {
                    System.out.println("You didn't choose a player. Select player: ");
                    choice = reader.nextInt();
                    return choice;
                }
            } catch (Exception e) {
                System.out.println("You didn't choose a player. Select player: ");
                reader.nextLine();
            }
        }

    }

    public static char acceptInput() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter your move: ");
        reader.useDelimiter("");
        return reader.next().charAt(0);
    }

    @Override
    public void update(int choice) {
    }

    @Override
    public void update(char choice) {
    }

    @Override
    public void update(List<List<String>> lines) {

    }

    @Override
    public void sendMessage(String msg) {

    }
}
