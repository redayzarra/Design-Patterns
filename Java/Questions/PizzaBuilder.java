
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Creating enums for pizza attributes
enum Size {
    SMALL, MEDIUM, LARGE
}

enum Crust {
    THIN, THICK
}

enum Sauce {
    TOMATO, BBQ
}

enum Topping {
    JALAPENO, OLIVES, PINEAPPLE, MUSHROOMS
}

// Creating a concrete pizza class
class Pizza {
    // Variables to store the pizza attributes
    private Size size;
    private Crust crust;
    private Sauce sauce;
    private List<Topping> toppings;

    // Constructor to initialize the pizza 
    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    // Getter and setter for the pizza's size
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    // Getter and setter for the pizza's crust
    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    // Getter and setter for the pizza's sauce
    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    // Getter and setter for the pizza's toppings
    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    // Method to pretty print the pizza
    @Override
    public String toString() {
        return "Pizza(size=" + size +
                ", crust=" + crust +
                ", sauce=" + sauce +
                ", toppings=" + toppings + ")";
    }
}

// Create the PizzaBuilder concrete class
public class PizzaBuilder {
    // Creating private pizza variable
    private final Pizza pizza;

    // Constructor to initialize PizzaBuilder
    public PizzaBuilder() {
        this.pizza = new Pizza();
    }

    // Method with method-chaining to set size
    public PizzaBuilder setSize(Size size) {
        pizza.setSize(size);
        return this;
    }

    // Method with method-chaining to set crust
    public PizzaBuilder setCrust(Crust crust) {
        pizza.setCrust(crust);
        return this;
    }

    // Method with method-chaining to set sauce
    public PizzaBuilder setSauce(Sauce sauce) {
        pizza.setSauce(sauce);
        return this;
    }

    // Method with method-chaining to add a topping
    public PizzaBuilder addTopping(Topping topping) {
        pizza.getToppings().add(topping);
        return this;
    }

    // Method with method-chaining to add many toppings
    public PizzaBuilder addToppings(List<Topping> toppings) {
        pizza.getToppings().addAll(toppings);
        return this;
    }

    // Build method to return the final pizza
    public Pizza build() {
        return pizza;
    }
}

// Test the pizza builder
class Test {
    public static void main(String[] args) {
        PizzaBuilder pizzaBuilder = new PizzaBuilder();

        Pizza myPizza = pizzaBuilder
                .setSize(Size.LARGE)
                .setCrust(Crust.THIN)
                .setSauce(Sauce.TOMATO)
                .addToppings(Arrays.asList(Topping.JALAPENO, Topping.OLIVES))
                .build();

        System.out.println(myPizza);
    }
}