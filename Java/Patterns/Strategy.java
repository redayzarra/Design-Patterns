// Create a pricing strategy 
interface PricingStrategy {
    double calculateCost(double weight, int quantity);
}

// Create a concrete class for electronics pricing
class ElectronicShipping implements PricingStrategy {
    @Override
    public double calculateCost(double weight, int quantity) {
        return (weight * 20) * quantity + 10;
    }
}

// Create a concrete class for clothing pricing
class ClothingShipping implements PricingStrategy {
    @Override
    public double calculateCost(double weight, int quantity) {
        return weight * quantity + 10;
    }
}

// Create a concrete class for book pricing
class BookShipping implements PricingStrategy {
    @Override
    public double calculateCost(double weight, int quantity) {
        double baseCost = (weight * 10) * quantity + 10;
        if (quantity > 5) {
            baseCost *= 0.8; // Apply 20% discount
        }
        return baseCost;
    }
}

// Create a calculator class that will use calculator
class ShippingCalculator {
    // Create a variable for the strategy and it's parameters
    private PricingStrategy strategy;
    private double weight;
    private int quantity;

    // Constructor for initializing calculator and defining variables
    public ShippingCalculator(double weight, int quantity) {
        this.weight = weight;
        this.quantity = quantity;
    }

    // Method to change the current strategy
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    // Method to calculate the cost using current strategy
    public double calculateCost() {
        if (strategy == null) {
            throw new IllegalStateException("PricingStrategy not set. Use setStrategy() to define a strategy.");
        }
        return strategy.calculateCost(weight, quantity);
    }

    // Method to change the current weight (paramter in strategy)
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Method to change the current quantity (paramter in strategy)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Test the design pattern
public class Strategy {
    public static void main(String[] args) {
        // Initialize the calculator without a strategy
        ShippingCalculator shippingCalculator = new ShippingCalculator(5, 2);

        // Set the strategy and calculate
        shippingCalculator.setStrategy(new ElectronicShipping());
        System.out.println("Electronics Shipping Cost: " + shippingCalculator.calculateCost());

        // Change to a different strategy
        shippingCalculator.setStrategy(new ClothingShipping());
        System.out.println("Clothing Shipping Cost: " + shippingCalculator.calculateCost());

        // Switch to books with updated weight/quantity
        shippingCalculator.setStrategy(new BookShipping());
        shippingCalculator.setWeight(0.5);
        shippingCalculator.setQuantity(6);
        System.out.println("Books Shipping Cost: " + shippingCalculator.calculateCost());
    }
}