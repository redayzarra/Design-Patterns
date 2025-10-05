// Create a singleton
class WeatherSingleton {
    // Private: means this variable is not available anywhere but in this class
    // Static: means that this is accesssible by simply doing WeatherSingleton.instance
    private static instance; WeatherSingleton; // Holds the single instance
    private weatherData: string;

    // Constructor() is a special built-in class that gets called when trying to create a new Class()
    private constructor() {
        this.weatherData = "Sunny, 25C"
    }

    public static getInstance(): WeatherSingleton {
        // If we don't have a weather singleton, let's make one
        if (!WeatherSingleton.instance) {
            WeatherSingleton.instance = new WeatherSingleton();
        }

        // Otherwise, if we already have one... let's return it
        return WeatherSingleton.instance;
    }

    // Getter: this gets the current weather data, allows us to safely control HOW we return the data
    public getWeather(): string {
        return this.weatherData;
    }

    // Setter: this sets the current weather data, allows us to safely control HOW that happens
    public setWeather(newWeather: string): void {
        this.weatherData = newWeather;
    }
}

const system1 = WeatherSingleton.getInstance();
console.log(system1.getWeather());  // "Sunny, 25°C"

system1.setWeather("Cloudy, 18°C");

const system2 = WeatherSingleton.getInstance();
console.log(system2.getWeather());  // "Cloudy, 18°C" (same instance!)
