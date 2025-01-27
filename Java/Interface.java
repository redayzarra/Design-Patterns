package Java;

// Create an interface which defines what an animal does - blueprint for concrete classes
interface Animal {
	void eat(); // These methods are public by default, you need to make them public in concrete classes as well
	void sleep();
	String sound();
}

// Create a concrete class called Dog that implements the Animal interface
class Dog implements Animal {
	@Override // Override annotation tells the compiler that we are overriding the interface function
	public void eat() {
		System.out.println("Dog is eating.");
	}

	@Override
	// Public means that the method below can be accessed from any other class/package
	public void sleep() {
		System.out.println("Dog is sleeping");
	}

	@Override 
	public String sound() {
		return "Bark";
	}
}

public class Interface {
	// The Java Virtual Machine (JVM) looks for this function below to run it
	public static void main(String [] args) { // Static means shared at the class-level
		// Define a new animal which is a dog
		Animal myDog = new Dog(); 
		
		// Make the dog do things
		myDog.eat();
		myDog.sleep();
		System.out.println("Dog says: " + myDog.sound());
	}
}
