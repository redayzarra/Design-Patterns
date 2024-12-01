import java.util.ArrayList;
import java.util.List;

// Define an abstract class for Vehicle
abstract class Vehicle {
    protected String licensePlate;
    protected String vehicleType;

    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}

// Define a subclass for cars
class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, "car");
    }
}

// Define a subclass for motorcycles
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, "motorcycle");
    }
}

// Define a subclass for trucks
class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, "truck");
    }
}

// Factory Pattern: VehicleFactory class creates vehicles based on type
class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType, String licensePlate) {
        switch (vehicleType) {
            case "car":
                return new Car(licensePlate);
            case "motorcycle":
                return new Motorcycle(licensePlate);
            case "truck":
                return new Truck(licensePlate);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}

// Singleton Pattern: ParkingLotManager class to manage parking spots
class ParkingLotManager {
    private static ParkingLotManager instance;
    private int freeSpots;

    private ParkingLotManager(int freeSpots) {
        this.freeSpots = freeSpots;
    }

    public static synchronized ParkingLotManager getInstance(int freeSpots) {
        if (instance == null) {
            instance = new ParkingLotManager(freeSpots);
        }
        return instance;
    }

    public int getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(int freeSpots) {
        this.freeSpots = freeSpots;
    }
}

// Enum for TicketType
enum TicketType {
    STANDARD,
    VIP
}

// Builder Pattern: Ticket class with a builder for customization
class Ticket {
    private String licensePlate;
    private String entryTime;
    private int spotLocation;
    private TicketType ticketType;

    private Ticket() {
    }

    public static class Builder {
        private Ticket ticket;

        public Builder() {
            ticket = new Ticket();
        }

        public Builder setLicensePlate(String licensePlate) {
            ticket.licensePlate = licensePlate;
            return this;
        }

        public Builder setEntryTime(String entryTime) {
            ticket.entryTime = entryTime;
            return this;
        }

        public Builder setSpotLocation(int spotLocation) {
            ticket.spotLocation = spotLocation;
            return this;
        }

        public Builder setTicketType(TicketType ticketType) {
            ticket.ticketType = ticketType;
            return this;
        }

        public Ticket build() {
            return ticket;
        }
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public int getSpotLocation() {
        return spotLocation;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}

// Prototype Pattern: ParkingSpot class for cloning spots
class ParkingSpot implements Cloneable {
    private int spotNumber;
    private String spotSize;
    private boolean isReserved;

    public ParkingSpot(int spotNumber, String spotSize, boolean isReserved) {
        this.spotNumber = spotNumber;
        this.spotSize = spotSize;
        this.isReserved = isReserved;
    }

    @Override
    public ParkingSpot clone() {
        try {
            return (ParkingSpot) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}

// Adapter Pattern: PaymentProcessor and adapter for legacy payment
interface PaymentProcessor {
    void processPayment(double amount, String licensePlate);
}

class LegacyPaymentProcessor {
    public void pay(String accountId, double paymentAmount) {
        System.out.println("Processing payment of $" + paymentAmount + " for account " + accountId + " using legacy processor.");
    }
}

class PaymentProcessorAdapter implements PaymentProcessor {
    private LegacyPaymentProcessor legacyProcessor;

    public PaymentProcessorAdapter(LegacyPaymentProcessor legacyProcessor) {
        this.legacyProcessor = legacyProcessor;
    }

    @Override
    public void processPayment(double amount, String licensePlate) {
        String accountId = "ACC-" + licensePlate;
        legacyProcessor.pay(accountId, amount);
    }
}

// Decorator Pattern: Adding services dynamically
interface Service {
    double cost();

    String description();
}

class BasicParking implements Service {
    @Override
    public double cost() {
        return 10.0;
    }

    @Override
    public String description() {
        return "Basic Parking";
    }
}

abstract class ServiceDecorator implements Service {
    protected Service service;

    public ServiceDecorator(Service service) {
        this.service = service;
    }

    @Override
    public double cost() {
        return service.cost();
    }

    @Override
    public String description() {
        return service.description();
    }
}

class WashDecorator extends ServiceDecorator {
    public WashDecorator(Service service) {
        super(service);
    }

    @Override
    public double cost() {
        return service.cost() + 5.0;
    }

    @Override
    public String description() {
        return service.description() + ", Wash";
    }
}

class VacuumDecorator extends ServiceDecorator {
    public VacuumDecorator(Service service) {
        super(service);
    }

    @Override
    public double cost() {
        return service.cost() + 3.0;
    }

    @Override
    public String description() {
        return service.description() + ", Vacuum";
    }
}

class DetailingDecorator extends ServiceDecorator {
    public DetailingDecorator(Service service) {
        super(service);
    }

    @Override
    public double cost() {
        return service.cost() + 15.0;
    }

    @Override
    public String description() {
        return service.description() + ", Detailing";
    }
}

// Strategy Pattern: Payment strategies for flexibility
interface PaymentStrategy {
    void pay(double amount, String licensePlate);
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount, String licensePlate) {
        System.out.println("Processing cash payment of $" + amount + " for vehicle " + licensePlate);
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount, String licensePlate) {
        System.out.println("Processing credit card payment of $" + amount + " for vehicle " + licensePlate);
    }
}

class MobilePayment implements PaymentStrategy {
    @Override
    public void pay(double amount, String licensePlate) {
        System.out.println("Processing mobile payment of $" + amount + " for vehicle " + licensePlate);
    }
}

// Observer interface
interface Observer {
    void announce(String message);
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Concrete Subject: Parking Lot Notifications
class ParkingLotSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.announce(message);
        }
    }
}

// Concrete Observers
class SecuritySystem implements Observer {
    @Override
    public void announce(String message) {
        System.out.println("Security System Alert: " + message);
    }
}

class BillingSystem implements Observer {
    @Override
    public void announce(String message) {
        System.out.println("Billing System Notification: " + message);
    }
}

// State interface for the Gate
interface GateState {
    void handleRequest(ParkingGate gate);
}

// Concrete States for the Gate
class GateOpenState implements GateState {
    @Override
    public void handleRequest(ParkingGate gate) {
        System.out.println("Gate is already open. Allowing vehicle entry or exit.");
        gate.setState(new GateClosingState());
    }
}

class GateClosedState implements GateState {
    @Override
    public void handleRequest(ParkingGate gate) {
        System.out.println("Gate is closed. Initiating opening process.");
        gate.setState(new GateOpeningState());
    }
}

class GateOpeningState implements GateState {
    @Override
    public void handleRequest(ParkingGate gate) {
        System.out.println("Gate is opening... now fully open.");
        gate.setState(new GateOpenState());
    }
}

class GateClosingState implements GateState {
    @Override
    public void handleRequest(ParkingGate gate) {
        System.out.println("Gate is closing... now fully closed.");
        gate.setState(new GateClosedState());
    }
}

// Parking Gate class to manage state transitions
class ParkingGate {
    private GateState state;

    public ParkingGate() {
        state = new GateClosedState(); // Initialize gate as closed
    }

    public void setState(GateState state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest(this);
    }
}

// Facade Pattern: ParkingLotFacade simplifies operations
class ParkingLotFacade extends ParkingLotSubject {
    private PaymentStrategy paymentStrategy;
    private ParkingLotManager manager;
    private ParkingGate gate;

    public ParkingLotFacade(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.manager = ParkingLotManager.getInstance(50); // Start with 50 free spots
        this.gate = new ParkingGate();
    }

    public Ticket enterParkingLot(String vehicleType, String licensePlate) {
        System.out.println("Opening gate for vehicle entry.");
        gate.request(); // Transition to opening, then open

        if (manager.getFreeSpots() > 0) {
            Ticket ticket = new Ticket.Builder()
                .setLicensePlate(licensePlate)
                .setEntryTime("Current date and time") // Replace with real time
                .setSpotLocation(manager.getFreeSpots())
                .setTicketType(TicketType.STANDARD) // Default to standard ticket
                .build();
            
            manager.setFreeSpots(manager.getFreeSpots() - 1);
            notifyObservers(vehicleType + " with license plate " + licensePlate + " entered. Spot assigned: " + ticket.getSpotLocation());
            
            System.out.println("Closing gate after vehicle entry.");
            gate.request(); // Transition to closing, then closed

            return ticket;
        } else {
            notifyObservers("No available spots. Entry denied.");
            System.out.println("Closing gate due to lack of availability.");
            gate.request(); // Transition to closing, then closed
            return null;
        }
    }

    public void exitParkingLot(Ticket ticket, double amount) {
        System.out.println("Opening gate for vehicle exit.");
        gate.request(); // Transition to opening, then open

        paymentStrategy.pay(amount, ticket.getLicensePlate());
        manager.setFreeSpots(manager.getFreeSpots() + 1);
        notifyObservers("Vehicle with license plate " + ticket.getLicensePlate() + " exited. Spot " + ticket.getSpotLocation() + " is now available.");

        System.out.println("Closing gate after vehicle exit.");
        gate.request(); // Transition to closing, then closed
    }
}

// Main Application
public class ParkingLotApp {
    public static void main(String[] args) {
        // Initialize ParkingLotFacade with a Cash Payment Strategy
        ParkingLotFacade system = new ParkingLotFacade(new CashPayment());

        // Create and register observers
        SecuritySystem security = new SecuritySystem();
        BillingSystem billing = new BillingSystem();

        system.registerObserver(security);
        system.registerObserver(billing);

        // Simulate a vehicle entering the parking lot
        Ticket ticket = system.enterParkingLot("car", "ABC123");

        // Simulate exiting the parking lot
        if (ticket != null) {
            system.exitParkingLot(ticket, 10.0);
        }

        // Remove an observer and simulate another vehicle exit
        system.removeObserver(billing);
        System.out.println("\nSimulating another vehicle exit with billing observer removed.");
        Ticket anotherTicket = system.enterParkingLot("truck", "XYZ789");
        if (anotherTicket != null) {
            system.exitParkingLot(anotherTicket, 20.0);
        }
    }
}
