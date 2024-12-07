import java.util.HashSet;
import java.util.Set;

interface ObserverObject {
    void announce(String message);
}

interface Subject {
    void addObserver(ObserverObject observer);
    void removeObserver(ObserverObject observer);
    void notifyObservers(String message);
}

class NotificationSystem implements Subject {
    private Set<ObserverObject> observers = new HashSet<>();

    @Override
    public void addObserver(ObserverObject observer) {
        observers.add(observer);
    }

    public void addObservers(ObserverObject... observersList) {
        for (ObserverObject observer : observersList) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ObserverObject observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (ObserverObject observer : observers) {
            observer.announce(message);
        }
    }
}

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