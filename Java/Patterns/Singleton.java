public class Singleton {
    // Private: Ensures the variable is accessible only within this class.
    // Static: Belongs to the class, so it's shared across all instances.
    // Volatile: Guarantees visibility and prevents thread caching or reordering issues.
    private static volatile Singleton barista;

    // Private constructor: Prevents external instantiation using the "new" keyword.
    private Singleton() {
        System.out.println("New barista hired!");
    }

    // Public static method to provide access to the Singleton instance.
    public static Singleton getInstance() {
        // First null check: Skips locking if the Singleton is already created
        if (barista == null) {
            synchronized (Singleton.class) { // Locks this block to ensure thread safety.
                // Second null check: Ensures only one instance is created, even with multiple threads.
                if (barista == null) {
                    barista = new Singleton();
                }
            }
        }
        // Return the Singleton instance (only one exists).
        return barista;
    }

    // Methods for the barista to perform actions.
    public void makeCoffee() {
        System.out.println("Barista is making coffee.");
    }

    public void serveCoffee() {
        System.out.println("Barista is serving coffee.");
    }
}
