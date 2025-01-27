from abc import ABC, abstractmethod


# Create a State Design pattern: Define an abstract class (interface)
class TrafficState(ABC):
    @abstractmethod
    def switch(self, traffic_light: "TrafficLight"):
        pass


# Define a concrete state class for green light
class GreenLight(TrafficState):
    def switch(self, traffic_light: "TrafficLight"):
        print("Switching from green to yellow light.")
        traffic_light.state = YellowLight()


# Define a concrete state class for yellow light
class YellowLight(TrafficState):
    def switch(self, traffic_light: "TrafficLight"):
        print("Switching from yellow light to red light.")
        traffic_light.state = RedLight()


# Define a concrete state class for red light
class RedLight(TrafficState):
    def switch(self, traffic_light: "TrafficLight"):
        print("Switching from red light to green light.")
        traffic_light.state = GreenLight()


# Create a concrete Traffic Light class to switch between states
class TrafficLight:
    def __init__(self):
        # Initialize the traffic light to red light
        self._state = RedLight()

    # Defining getter and setter for state
    @property
    def state(self):
        return self._state

    @state.setter
    def state(self, state: TrafficState):
        self._state = state

    def switch(self):
        self.state.switch(self)


# Test the system
traffic_light = TrafficLight()
for index in range(6):
    traffic_light.switch()
