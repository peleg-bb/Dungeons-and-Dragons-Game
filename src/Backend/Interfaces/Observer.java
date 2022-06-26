package Backend.Interfaces;

import java.util.List;

// Observer pattern interface
public interface Observer {
    void update(int choice);
    void update(char choice);
    void update(List<List<String>> lines);
    void sendMessage(String msg);
}
