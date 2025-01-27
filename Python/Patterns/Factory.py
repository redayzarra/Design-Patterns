from abc import ABC, abstractmethod


# Define an abstract class (interface)
class Attraction(ABC):
    @abstractmethod
    def get_requirements(self) -> dict[str, str]:
        pass


# Define concrete class for Roller Coaster (which is an attraction)
class RollerCoaster(Attraction):
    def get_requirements(self) -> dict[str, str]:
        return {
            "height": "Must be at least 48 inches tall",
            "health": "No heart conditions or recent surgeries",
        }


# Define concrete class for Haunted House (which is an attraction)
class HauntedHouse(Attraction):
    def get_requirements(self) -> dict[str, str]:
        return {
            "age": "Must be at least 12 years old",
            "warning": "Not recommended for individuals with heart conditions",
        }


# Define concrete class for Water Slide (which is an attraction)
class WaterSlide(Attraction):
    def get_requirements(self) -> dict[str, str]:
        return {
            "swimming": "Must be able to swim",
            "lockers": "Locker rental available for personal items",
        }


# Create the Factory pattern: Uses a type to return the correct class
class AttractionFactory:
    @staticmethod
    def create_attraction(type: str) -> Attraction:
        if type == "rollercoaster":
            return RollerCoaster()
        elif type == "hauntedhouse":
            return HauntedHouse()
        elif type == "waterslide":
            return WaterSlide()
        else:
            raise ValueError(f"Unknown type of attraction: {type}")


# Test the system
rollercoaster = AttractionFactory.create_attraction("rollercoaster")
hauntedhouse = AttractionFactory.create_attraction("hauntedhouse")
waterslide = AttractionFactory.create_attraction("waterslide")

attractions: list[Attraction] = [rollercoaster, hauntedhouse, waterslide]

for attraction in attractions:
    print(attraction.get_requirements())

