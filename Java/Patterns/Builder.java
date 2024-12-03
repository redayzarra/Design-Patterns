import java.util.ArrayList;
import java.util.List;

// Define enums for different meal components
enum MainItem {
    BURGER, SANDWICH, SALAD
}

enum SideItem {
    FRIES, ONION_RINGS, SIDE_SALAD
}

enum Drink {
    SODA, JUICE, WATER
}

enum Dessert {
    ICE_CREAM, PIE, COOKIES
}

// Define the Meal class
class Meal {
    private MainItem mainItem;
    private final List<SideItem> sides;
    private Drink drink;
    private Dessert dessert;

    // Constructor
    public Meal() {
        this.sides = new ArrayList<>();
    }

    // Getters and Setters
    public MainItem getMainItem() {
        return mainItem;
    }

    public void setMainItem(MainItem mainItem) {
        this.mainItem = mainItem;
    }

    public List<SideItem> getSides() {
        return sides;
    }

    public void addSide(SideItem side) {
        this.sides.add(side);
    }

    public void addSides(List<SideItem> sides) {
        this.sides.addAll(sides);
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}

// Create the Builder pattern: Define a concrete class to build meals
class MealBuilder {
    // Create the meal that we will build and return
    private Meal meal;

    // Constructor to initilize a Meal object
    public MealBuilder() {
        this.meal = new Meal();
    }

    // Returning "this" to allow for method chaining
    public MealBuilder setMainItem(MainItem mainItem) {
        meal.setMainItem(mainItem);
        return this;
    }

    public MealBuilder addSide(SideItem side) {
        meal.addSide(side);
        return this;
    }

    public MealBuilder addSides(List<SideItem> sides) {
        meal.addSides(sides);
        return this;
    }

    public MealBuilder setDrink(Drink drink) {
        meal.setDrink(drink);
        return this;
    }

    public MealBuilder setDessert(Dessert dessert) {
        meal.setDessert(dessert);
        return this;
    }

    public Meal build() {
        return meal;
    }
}

// Test the design pattern
public class Builder {
    public static void main(String[] args) {
        // Use the builder to create a custom meal
        MealBuilder mealBuilder = new MealBuilder();
        Meal customMeal = mealBuilder
                .setMainItem(MainItem.SANDWICH)
                .addSides(List.of(SideItem.ONION_RINGS, SideItem.SIDE_SALAD))
                .setDrink(Drink.JUICE)
                .setDessert(Dessert.COOKIES)
                .build();

        // Display the meal's details
        System.out.println("Custom Meal:");
        System.out.println("Main Item: " + customMeal.getMainItem());
        System.out.println("Sides: " + customMeal.getSides());
        System.out.println("Drink: " + customMeal.getDrink());
        System.out.println("Dessert: " + (customMeal.getDessert() != null ? customMeal.getDessert() : "No Dessert"));
    }
}
