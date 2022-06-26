package UI;

import Backend.GameFlow.GameFlow;
import Backend.GameFlow.TileFactory;
import Backend.Interfaces.Observer;

import java.io.*;
import java.util.ArrayList;
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
        // read levels from file
        List<List<String>> levels = UI.readFiles(args);
        new GameFlow(choice, levels);
        System.out.println("Game over, Bye bye!");
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
                    System.out.println("You must choose a number between 1-7. Select player: ");
                    choice = reader.nextInt();
                }
                return choice;
            } catch (Exception e) {
                System.out.println("You must choose a number between 1-7. Select player: ");
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

    // Reads file and returns a list of lines
    private List<List<String>> readFiles(String[] args) {
        File[] files = new File(args[0]).listFiles(); // read from levels path
        List<List<String>> levels = new ArrayList<List<String>>();
        for (File file : files) {
            if (file.isFile() && file.getName().indexOf("level")!=-1) { //read each file
                List<String> levelFiles = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String next;
                    while ((next = reader.readLine()) != null) {
                        levelFiles.add(next);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage() + "\n" +
                            ex.getStackTrace());
                }
                levels.add(levelFiles);

            }
        }
        return levels;
    }
}
