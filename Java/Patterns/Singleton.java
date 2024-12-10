public class Singleton {

    // Private constructor to prevent instantiation
    private Singleton() {}

    // Static nested class - Singleton instance is created only when accessed
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }

    // Example methods to demonstrate Singleton behavior
    private String weatherData = "Sunny, 25°C";

    public String getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(String weatherData) {
        this.weatherData = weatherData;
    }

    // Test the Singleton
    public static void main(String[] args) {
        Singleton weatherSystem = Singleton.getInstance();
        System.out.println(weatherSystem.getWeatherData());

        weatherSystem.setWeatherData("Cloudy, 50°C");
        System.out.println(weatherSystem.getWeatherData());

        Singleton newSystem = Singleton.getInstance();
        System.out.println(newSystem.getWeatherData()); // Should print "Cloudy, 50°C"
    }
}

