class InventorySystem {
    public boolean checkStock(int bookId) {
        System.out.println("Checking stock for book ID: " + bookId);
        return true;
    }

    public void reserveItem(int bookId) {
        System.out.println("Reserving book ID: " + bookId);
    }
}

class PaymentSystem {
    public boolean processPayment(int customerId, double amount) {
        System.out.println("Processing payment for customer ID: " + customerId + " with amount: $" + amount);
        return true;
    }
}

class ShippingSystem {
    public void scheduleShipment(int bookId, int customerId) {
        System.out.println("Scheduling shipment for book ID: " + bookId + " to customer ID: " + customerId);
    }
}

// Facade class
class BookstoreFacade {
    private final InventorySystem inventory;
    private final PaymentSystem payment;
    private final ShippingSystem shipping;

    public BookstoreFacade() {
        this.inventory = new InventorySystem();
        this.payment = new PaymentSystem();
        this.shipping = new ShippingSystem();
    }

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

// Example usage
public class Facade {
    public static void main(String[] args) {
        BookstoreFacade bookstore = new BookstoreFacade();
        bookstore.placeOrder(101, 501, 29.99);
    }
}