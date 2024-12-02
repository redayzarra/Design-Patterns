// Create Singleton Pattern: Only allows for one instance
public class Singleton {
	// Create a private variable for the current instance
	private static Singleton instance;

	// Create a private constant for thread safety lock
	private static final Object lock = new Object();

	// Create a private variable to store data
	private String weatherData;
	private Singleton() {
		this.weatherData = "Sunny, 25°C";
	}

	// Create method to get the current instance or create first new one
	public static Singleton getInstance() {
		// Make sure the instance doesn't already exist, to create new one
		if (instance == null) {
			synchronized (lock) { // Use lock for thread safety
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	// Create method to get the current weather data
	public String getWeatherData() {
		return weatherData;
	}

	// Create method to set the current weather data
	public void setWeatherData(String weatherData) {
		this.weatherData = weatherData;
	}
	
	// Test the design pattern
	public static void main(String[] args) {
		// Create the first instance of the singleton
		Singleton weatherSystem = Singleton.getInstance();
		System.out.println(weatherSystem.getWeatherData());
	
		// Update weather data
        weatherSystem.setWeatherData("Cloudy, 50°C");
        System.out.println(weatherSystem.getWeatherData()); // Expected: "Cloudy, 50°C"

        // Get another instance and verify that it points to the same object
        Singleton newSystem = Singleton.getInstance();
        System.out.println(newSystem.getWeatherData()); // Expected: "Cloudy, 50°C"
	}
}
