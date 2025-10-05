// Create an interface called Pizza which implements the following methods
interface Pizza {
    prepare(): void;
    bake(): void;
    cut(): void;
    box(): void;
}

// Create a concrete class that actually implements the Pizza interface
class MargheritaPizza implements Pizza {
    // Implement all the functions from the Pizza interface
    prepare(): void {
        console.log("Preparing margherita pizza");
    }

    bake(): void {
        console.log("Baking margherita pizza");
    }

    cut(): void {
        console.log("Cutting margherita pizza");
    }

    box(): void {
        console.log("Boxing margherita pizza");
    }
}

// This allows us to call the Margherita Pizza concrete class and use it
const margheritaPizza = new MargheritaPizza();
margheritaPizza.prepare();

// Also create another concrete class like Pepperoni
class PepperoniPizza implements Pizza {
    prepare(): void {
        console.log("Preparing pepperoni pizza");
    }

    bake(): void {
        console.log("Baking pepperoni pizza");
    }

    cut(): void {
        console.log("Cutting pepperoni pizza");
    }

    box(): void {
        console.log("Boxing pepperoni pizza");
    }
}

// Concrete classes allow us to instantiate the class
const pepperoniPizza = new PepperoniPizza();
pepperoniPizza.bake();

// Create an abstract class for ordering pizzas
abstract class PizzaFactory {
    // Abstract: means the subclasses that extend this class will implement this function
    // Protected: means this is an internal method only to be used by this class and subclasses
    protected abstract build(type: string): Pizza;

    public orderPizza(type: string): Pizza {
        // Uses the current instance's build() function
        const pizza = this.build(type);

        // Call the pizza's functions
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        // Return the pizza which is what the function expects as output
        return pizza;
    }
}

class NewYorkPizzaFactory extends PizzaFactory {
    // Subclasses must implement the abstract methods from PizzaFactory
    protected build(type: string): Pizza {
        switch (type) {
            case "margherita":
                return new MargheritaPizza();
            case "pepperoni":
                return new PepperoniPizza();
            default:
                throw new Error("Sorry, we don't have this kind of pizza.");
        }
    }
}

/////////////////////////////////////////////

// Define a new interface which defines what every attraction does
interface Attraction {
    getRequirements(): number;
}

// Create concrete classes to implement the attraction interface
class RollerCoaster implements Attraction {
    getRequirements(): number {
        return 20;
    }
}

class HauntedHouse implements Attraction {
    getRequirements(): number {
        return 50;
    }
}

class WaterSlide implements Attraction {
    getRequirements(): number {
        return 75;
    }
}

// Creating an enum to define the types
enum AttractionType {
    ROLLERCOASTER = "rollercoaster",
    HAUNTEDHOUSE = "hauntedhouse",
    WATERSLIDE = "waterslide"
}

// Create a factory which accepts the different types to return the correct attraction 
class AttractionFactory {
    // Static: method can be used directly from the class like this Calculator.add(2, 3) since we 
    // don't need to track any internal state... we can just create and return objects.
    static create(type: AttractionType): Attraction {
        switch (type) {
            case AttractionType.ROLLERCOASTER: return new RollerCoaster();
            case AttractionType.HAUNTEDHOUSE: return new HauntedHouse();
            case AttractionType.WATERSLIDE: return new WaterSlide();
        }
    }
}

const rides: Attraction[] = [
    AttractionFactory.create(AttractionType.ROLLERCOASTER),
    AttractionFactory.create(AttractionType.HAUNTEDHOUSE),
    AttractionFactory.create(AttractionType.WATERSLIDE)
]

for (const ride of rides) {
    console.log(ride.getRequirements())
}