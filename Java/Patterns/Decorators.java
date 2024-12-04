// Create the tool interface to define all other tools
interface Tool {
    double damage();
    double durability();
    double speed();
    String description();
}

// Create concrete classes that are tools 
class Sword implements Tool {
    private static final double DAMAGE = 50.0;
    private static final double DURABILITY = 25.0;
    private static final double SPEED = 40.0;

    @Override
    public double damage() {
        return DAMAGE;
    }

    @Override
    public double durability() {
        return DURABILITY;
    }

    @Override
    public double speed() {
        return SPEED;
    }

    @Override
    public String description() {
        return "Sword";
    }
}

class Bow implements Tool {
    private static final double DAMAGE = 30.0;
    private static final double DURABILITY = 15.0;
    private static final double SPEED = 10.0;

    @Override
    public double damage() {
        return DAMAGE;
    }

    @Override
    public double durability() {
        return DURABILITY;
    }

    @Override
    public double speed() {
        return SPEED;
    }

    @Override
    public String description() {
        return "Bow";
    }
}

class Hammer implements Tool {
    private static final double DAMAGE = 40.0;
    private static final double DURABILITY = 50.0;
    private static final double SPEED = 10.0;

    @Override
    public double damage() {
        return DAMAGE;
    }

    @Override
    public double durability() {
        return DURABILITY;
    }

    @Override
    public double speed() {
        return SPEED;
    }

    @Override
    public String description() {
        return "Hammer";
    }
}

// Create an abstract class that accepts and is a tool
abstract class ToolDecorator implements Tool {
    protected Tool tool;

    public ToolDecorator(Tool tool) {
        this.tool = tool;
    }

    @Override
    public double damage() {
        return tool.damage();
    }

    @Override
    public double durability() {
        return tool.durability();
    }

    @Override
    public double speed() {
        return tool.speed();
    }

    @Override
    public String description() {
        return tool.description();
    }
}

// Create decorators to change the underlying behavior of tools
class SharpnessDecorator extends ToolDecorator {
    private final double sharpnessValue;

    public SharpnessDecorator(Tool tool, double sharpnessValue) {
        super(tool);
        this.sharpnessValue = sharpnessValue;
    }

    @Override
    public double damage() {
        return sharpnessValue + tool.damage(); // Adds extra damage
    }

    @Override
    public String description() {
        return tool.description() + " with Sharpness (+" + sharpnessValue + " damage)";
    }
}

class DurabilityDecorator extends ToolDecorator {
    private final double durabilityValue;

    public DurabilityDecorator(Tool tool, double durabilityValue) {
        super(tool);
        this.durabilityValue = durabilityValue;
    }

    @Override
    public double durability() {
        return durabilityValue + tool.durability(); // Adds extra durability
    }

    @Override
    public String description() {
        return tool.description() + " with Durability (+" + durabilityValue + ")";
    }
}

class SpeedBoostDecorator extends ToolDecorator {
    private final double speedValue;

    public SpeedBoostDecorator(Tool tool, double speedValue) {
        super(tool);
        this.speedValue = speedValue;
    }

    @Override
    public double speed() {
        return speedValue + tool.speed(); // Adds extra speed
    }

    @Override
    public String description() {
        return tool.description() + " with Speed Boost (+" + speedValue + " speed)";
    }
}

// Test the design patterns
public class Decorators {
    public static void main(String[] args) {
        // Create a basic sword
        Tool basicSword = new Sword();
        System.out.println("Basic Sword:");
        System.out.println("Description: " + basicSword.description());
        System.out.println("Damage: " + basicSword.damage());
        System.out.println("Durability: " + basicSword.durability());
        System.out.println("Speed: " + basicSword.speed());

        // Add Sharpness and Durability to the sword
        Tool enchantedSword = new DurabilityDecorator(
                new SharpnessDecorator(basicSword, 7.0), 12.0
        );
        System.out.println("\nEnchanted Sword:");
        System.out.println("Description: " + enchantedSword.description());
        System.out.println("Damage: " + enchantedSword.damage());
        System.out.println("Durability: " + enchantedSword.durability());
        System.out.println("Speed: " + enchantedSword.speed());

        // Add Speed Boost to the enchanted sword
        Tool fullyEnchantedSword = new SpeedBoostDecorator(enchantedSword, 15.0);
        System.out.println("\nFully Enchanted Sword:");
        System.out.println("Description: " + fullyEnchantedSword.description());
        System.out.println("Damage: " + fullyEnchantedSword.damage());
        System.out.println("Durability: " + fullyEnchantedSword.durability());
        System.out.println("Speed: " + fullyEnchantedSword.speed());
    }
}