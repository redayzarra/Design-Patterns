import java.util.HashMap;
import java.util.Map;

interface Attraction {
	Map<String, String> getRequirements();
}

class RollerCoaster implements Attraction {
	@Override
	public Map<String, String> getRequirements() {
		Map<String, String> requirements = new HashMap<>();
		requirements.put("height", "Must be at least 48 inches tall");
        requirements.put("health", "No heart conditions or recent surgeries");
        return requirements;
	}
}

class HauntedHouse implements Attraction {
    @Override
    public Map<String, String> getRequirements() {
        Map<String, String> requirements = new HashMap<>();
        requirements.put("age", "Must be at least 12 years old");
        requirements.put("warning", "Not recommended for individuals with heart conditions");
        return requirements;
    }
}

class WaterSlide implements Attraction {
    @Override
    public Map<String, String> getRequirements() {
        Map<String, String> requirements = new HashMap<>();
        requirements.put("swimming", "Must be able to swim");
        requirements.put("lockers", "Locker rental available for personal items");
        return requirements;
    }
}

// Create the Factory Pattern for attractions
class AttractionFactory {
	public static Attraction createAttraction(String type) {
		switch (type.toLowerCase()) {
			case "rollercoaster":
				return new RollerCoaster();
			case "hauntedhouse":
				return new HauntedHouse();
			case "waterslide":
				return new WaterSlide();
			default:
				throw new IllegalArgumentException("Unknown type of attraction: " + type);
		}
	}
}

// Test the factory
public class Main {
	public static void main(String[] args) {
		// Create attractions using the factory
		Attraction rollercoaster = AttractionFactory.createAttraction("rollercoaster");
		Attraction hauntedhouse = AttractionFactory.createAttraction("hauntedhouse");
		Attraction waterslide = AttractionFactory.createAttraction("waterslide");

		// Store the attractions in an array
		Attraction[] attractions = {rollercoaster, hauntedhouse, waterslide};

		// Test each attraction
		for (Attraction attraction : attractions) {
			System.out.println(attraction.getRequirements());
		}
	}
}