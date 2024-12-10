// Define the Prototype Interface
interface Prototype extends Cloneable {
    // Create an abstract method for cloning
    Prototype clone() throws CloneNotSupportedException;
}

// Create a concrete class that implements the Prototype interface
class Document implements Prototype {
    private String content;

    // Constructor to initialize content
    public Document(String content) {
        this.content = content;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Implement the clone method
    @Override
    public Document clone() throws CloneNotSupportedException {
        return (Document) super.clone(); // Call super.clone() to create a shallow copy
    }
}

// Test the Prototype Pattern
public class PrototypePatternDemo {
    public static void main(String[] args) {
        try {
            // Create an original document
            Document originalDocument = new Document("This is the first version of the document.");
            System.out.println("Original Document Content: " + originalDocument.getContent());

            // Clone the original document
            Document clonedDocument = originalDocument.clone();
            System.out.println("Cloned Document Content: " + clonedDocument.getContent());

            // Modify the content of the original document
            originalDocument.setContent("This is the second version of the document.");
            System.out.println("Modified Original Document Content: " + originalDocument.getContent());
            System.out.println("Cloned Document Content After Modification: " + clonedDocument.getContent());
        } catch (CloneNotSupportedException e) {
            System.out.println("Can't slone this object.");
        }
    }
}
