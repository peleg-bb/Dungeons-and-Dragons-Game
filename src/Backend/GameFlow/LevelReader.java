package Backend.GameFlow;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LevelReader {
    // A function which reads a .txt file and returns a list with each line as a string
    public static List<String> readFile(String fileName)
    {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {
            // do something
            e.printStackTrace();
        }
        return lines;
    }


}


