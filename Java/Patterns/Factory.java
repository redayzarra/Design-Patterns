// Define an interface for the shape
interface Shape {
	void draw(); // This is the public method that every concrete class must implement
}

// Create a concrete class that implements the Shape interface
class Circle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a ciricle");
	}
}

// Create a concrete class that implements the Shape interface
class Triangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a triangle");
	}
}

// Create a concrete class that implements the Shape interface
class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a rectangle");
	}
}
// Create a concrete class that implements the Shape interface
class Square implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing a square");
	}
}

// Create the Factory design pattern 
class ShapeFactory {
	// Create a static method to return the right shape
    public static Shape getShape(String shapeType) {
        // Base case: If the shape type doesn't exist, return null
        if (shapeType == null) {
            return null;
        }

        switch (shapeType.toLowerCase()) {
            case "circle":
                return new Circle();
            case "square":
                return new Square();
            case "rectangle":
                return new Rectangle();
            default:
                throw new IllegalArgumentException("Unknown shape type provided: " + shapeType);
        }
    }
}

public class Factory {
    public static void main(String[] args) {
        // Use the factory to create new shapes
        Shape circle = ShapeFactory.getShape("circle");
        Shape square = ShapeFactory.getShape("square");
        Shape rectangle = ShapeFactory.getShape("rectangle");

        // Draw the individual shapes
        circle.draw();
        square.draw();
        rectangle.draw();
    }
}