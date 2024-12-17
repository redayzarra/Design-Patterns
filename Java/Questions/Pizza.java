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
    public MargheritaPizza(Size size, List<String> toppings) {
        super("margherita", size, toppings);
        sizePrices = Map.of(
            "Small", 12.0,
            "Medium", 16.0,
            "Large", 22.0
        );
        toppingPrice = 2.0;
    }

    @Override
    public void prepare() {
        System.out.println("Baking Margherita pizza...");
    }
}
