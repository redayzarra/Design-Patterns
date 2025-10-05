// Create an interface called Pizza which implements the following methods
interface Pizza {
    prepare(): void;
    bake(): void;
    cut(): void;
    box(): void;
}

// We then create a class that uses the Pizza interface
class MargheritaPizza implements Pizza {

    // We now have to implement all the functions from the Pizza interface
    prepare(): void {
        console.log("Preparing a pizza")
    }

    bake(): void {
        console.log("Baking the pizza")
    }

    cut(): void {
        console.log("Cutting the pizza")
    }
     
    box(): void {
        console.log("Boxing the pizza")
    }
}

// This allows us to call the Margherita Pizza class and use it
const pizza = new MargheritaPizza();
pizza.prepare()