import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Create an enum to define the different sizes of pizza
enum Size {
    // Define the basic pizza sizes
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String value;

    Size(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

// Create the base pizza class for additional attributes
public class Pizza {
    protected String type;
    protected Size size;
    protected List<String> toppings;

    protected Map<String, Double> sizePrices = Map.of(
        "Small", 10.0,
        "Medium", 15.0,
        "Large", 20.0
    );

    protected double toppingPrice = 1.5;

    public Pizza(String type, Size size, List<String> toppings) {
        this.type = type;
        this.size = size;
        this.toppings = toppings;
    }

    public double calculatePrice() {
        if (!sizePrices.containsKey(size.getValue())) {
            throw new IllegalArgumentException("Invalid size: " + size.getValue());
        }
        double basePrice = sizePrices.get(size.getValue());
        double toppingsTotal = toppingPrice * toppings.size();
        return basePrice + toppingsTotal;
    }

    public String getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }
}

// Define interfaces for Pizzas 
interface Bakeable {
    void prepare();
}

interface Grillable {
    void prepare();
}

// Create a bakeable Pizza - specifically Margerita
class MargheritaPizza extends Pizza implements Bakeable {
    // Initialize the pizza with size and list of toppings
    public MargheritaPizza(Size size, List<String> toppings) {
        super("margherita", size, toppings);
        sizePrices = Map.of(
            "Small", 12.0,
            "Medium", 16.0,
            "Large", 22.0
        );
        toppingPrice = 2.0;
    }

    // Define the prepare() method since we called the Bakeable interface
    @Override
    public void prepare() {
        System.out.println("Baking Margherita pizza...");
    }
}

// Create a grillable Pizza - specifically Pepperoni
class PepperoniPizza extends Pizza implements Grillable {
    // Initialize the pizza with size and list of toppings
    public PepperoniPizza(Size size, List<String> toppings) {
        super("pepperoni", size, toppings);
        sizePrices = Map.of(
            "Small", 14.0,
            "Medium", 18.0,
            "Large", 25.0
        );
        toppingPrice = 2.0;
    }

    // Define the prepare() method since we called the Grillable interface
    @Override
    public void prepare() {
        System.out.println("Grilling pepperoni pizza...");
    }
}

// Create the payment method interface which will process payments
interface PaymentMethod {
    void processPayment();
    String getType();
}

// Create a concrete credit card class which is a type of payment method
class CreditCard implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing payment with Credit Card...");
    }

    @Override
    public String getType() {
        return "credit";
    }
}

// Create a concrete paypal class which is a type of payment method
class PayPal implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing payment with PayPal...");
    }

    @Override
    public String getType() {
        return "paypal";
    }
}

// Create a concrete cash class which is a type of payment method
class Cash implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing cash payment...");
    }

    @Override
    public String getType() {
        return "cash";
    }
}

// Create a concrete order class which calculates pizza details
class Order {
    // Store the list of pizzas and the payment method used
    private final List<Pizza> items;
    private final PaymentMethod payment;

    // Constructor to initialize the pizzas and payment method
    public Order(List<Pizza> items, PaymentMethod payment) {
        this.items = new ArrayList<>(items);
        this.payment = payment;
    }

    // Add a pizza to our list of items
    public void addPizza(Pizza pizza) {
        items.add(pizza);
    }

    // Remove a pizza from our list of items
    public void removePizza(Pizza pizza) {
        items.remove(pizza);
    }

    // Create a method to calculate the total amount for order
    public double calculateTotal() {
        double total = 0;
        for (Pizza pizza : items) {
            total += pizza.calculatePrice();
        }
        return total;
    }

    // Create a method to pretty print the items
    public void displayOrderSummary() {
        System.out.println("Order Summary:");
        for (Pizza pizza : items) {
            System.out.printf("- %s (%s): $%.2f%n", 
                pizza.getType(), 
                pizza.getSize(), 
                pizza.calculatePrice()
            );
        }
        System.out.printf("Total: $%.2f%n", calculateTotal());
    }
}
