// Creating a concrete class to track inventory items
class InventorySystem {
    public boolean checkStock(int bookId) {
        System.out.println("Checking stock for book ID: " + bookId);
        return true;
    }

    public void reserveItem(int bookId) {
        System.out.println("Reserving book ID: " + bookId);
    }
}

// Creating a payment system (dummy)
class PaymentSystem {
    public boolean processPayment(int customerId, double amount) {
        System.out.println("Processing payment for customer ID: " + customerId + " with amount: $" + amount);
        return true;
    }
}

// Creating a shipping system (dummy)
class ShippingSystem {
    public void scheduleShipment(int bookId, int customerId) {
        System.out.println("Scheduling shipment for book ID: " + bookId + " to customer ID: " + customerId);
    }
}

// Create the facade design pattern
class BookstoreFacade {
    // Define variables to track the other subsystems
    private final InventorySystem inventory;
    private final PaymentSystem payment;
    private final ShippingSystem shipping;

    // Constructor to initialize the facade
    public BookstoreFacade() {
        this.inventory = new InventorySystem();
        this.payment = new PaymentSystem();
        this.shipping = new ShippingSystem();
    }

    // Method to place orders to interact with all systems
    public boolean placeOrder(int bookId, int customerId, double amount) {
        if (!inventory.checkStock(bookId)) {
            System.out.println("Book is out of stock!");
            return false;
        }

        inventory.reserveItem(bookId);

        if (!payment.processPayment(customerId, amount)) {
            System.out.println("Payment failed!");
            return false;
        }

        shipping.scheduleShipment(bookId, customerId);
        System.out.println("Order placed successfully!");
        return true;
    }
}

// Test the design pattern
public class Facade {
    public static void main(String[] args) {
        BookstoreFacade bookstore = new BookstoreFacade();
        bookstore.placeOrder(101, 501, 29.99);
    }
}