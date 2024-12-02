// Create Singleton Pattern: Only allows for one instance
public class Singleton {
	// Create a private variable for the current instance
	private static Singleton instance;
	private static final Object lock = new Object();

	private String weatherData;

	private Singleton() {
		this.weatherData = "Sunny, 25°C";
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	public String getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(String weatherData) {
		this.weatherData = weatherData;
	}
	
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
