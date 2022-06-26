package Backend.Interfaces;

import java.util.List;

// Observer pattern interface
public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(char choice);
    void notifyObservers(int choice);
    void notifyObservers(List<List<String>> lines);
}
