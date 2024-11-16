# Create the Singleton pattern
import threading


class WeatherSingleton:
    # Create private class attributes
    _instance = None
    _instance_lock = threading.Lock()
    
    def __new__(cls):
        # Using the threading, lock the instance creation process
        with cls._instance_lock:
            # Make sure the instance doesn't already exist
            if cls._instance is None:
                # Create a new and ONLY instance
                cls._instance = super(WeatherSingleton, cls).__new__(cls)
                # Setup attributes now - we won't initialize again
                cls._instance._weather_data = "Sunny, 25°C" 
        return cls._instance
    
    @property
    def weather_data(self):
        return self._weather_data
    
    @weather_data.setter
    def weather_data(self, weather_data: str):
        self._weather_data = weather_data
        
# Create an original weather system
weather_system = WeatherSingleton()
print(weather_system.weather_data) # Expected output: "Cloudy, 50°C"

# Update the weather system and print new data
weather_system.weather_data = "Cloudy, 50°C"
print(weather_system.weather_data)  # Expected output: "Cloudy, 50°C"


new_system = WeatherSingleton()
print(new_system.weather_data)  # Expected output: "Cloudy, 50°C"

