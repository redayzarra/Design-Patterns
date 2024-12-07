import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Create the observer interface that will get the notifications
interface ObserverObject {
    void announce(String message);
}

// Create the subject interface that will send the notifications
interface Subject {
    void addObserver(ObserverObject observer);
    void removeObserver(ObserverObject observer);
    void notifyObservers(String message);
}

// Create the notification system that is a subject
class NotificationSystem implements Subject {
    // Store the observers in a set for O(1) add, search, and remove
    private final Set<ObserverObject> observers = new HashSet<>();

    // Methods to add an observer to notify
    @Override
    public void addObserver(ObserverObject observer) {
        observers.add(observer);
    }

    public void addObservers(ObserverObject... observersList) {
        observers.addAll(Arrays.asList(observersList));
    }

    // Method to remove observer 
    @Override
    public void removeObserver(ObserverObject observer) {
        observers.remove(observer);
    }

    // Method to notify all observers using a message
    @Override
    public void notifyObservers(String message) {
        for (ObserverObject observer : observers) {
            observer.announce(message);
        }
    }
}

// Create concrete classes that are observers
class EmailObserver implements ObserverObject {
    @Override
    public void announce(String message) {
        System.out.println("EmailObserver: " + message);
    }
}

class SMSObserver implements ObserverObject {
    @Override
    public void announce(String message) {
        System.out.println("SMSObserver: " + message);
    }
}

class AppObserver implements ObserverObject {
    @Override
    public void announce(String message) {
        System.out.println("AppObserver: " + message);
    }
}

// Test the system
public class Observer {
    public static void main(String[] args) {
        NotificationSystem system = new NotificationSystem();

        // Create observers
        ObserverObject Josh = new EmailObserver();
        ObserverObject Alina = new SMSObserver();
        ObserverObject Jacob = new AppObserver();

        // Add multiple observers
        system.addObservers(Josh, Alina, Jacob);

        // Notify observers
        system.notifyObservers("There is a car approaching.");
        system.notifyObservers("There is a person at the front door.");
        
        // Remove one observer
        system.removeObserver(Josh);

        // Notify remaining observers
        system.notifyObservers("The front window is broken.");
    }
}