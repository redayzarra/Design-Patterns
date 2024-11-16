# Create a base concrete class for weapons
from abc import ABC, abstractmethod


# Create an abstract class for Tool interface
class Tool(ABC):
    @abstractmethod
    def damage(self) -> float:
        pass

    @abstractmethod
    def durability(self) -> float:
        pass

    @abstractmethod
    def speed(self) -> float:
        pass

    @abstractmethod
    def description(self) -> str:
        pass


# Concrete Tool Classes with Constants
class Sword(Tool):
    DAMAGE = 50.0
    DURABILITY = 25.0
    SPEED = 40.0

    def damage(self) -> float:
        return self.DAMAGE

    def durability(self) -> float:
        return self.DURABILITY

    def speed(self) -> float:
        return self.SPEED

    def description(self) -> str:
        return "Sword"


class Bow(Tool):
    DAMAGE = 30.0
    DURABILITY = 15.0
    SPEED = 10.0

    def damage(self) -> float:
        return self.DAMAGE

    def durability(self) -> float:
        return self.DURABILITY

    def speed(self) -> float:
        return self.SPEED

    def description(self) -> str:
        return "Bow"


class Hammer(Tool):
    DAMAGE = 40.0
    DURABILITY = 50.0
    SPEED = 10.0

    def damage(self) -> float:
        return self.DAMAGE

    def durability(self) -> float:
        return self.DURABILITY

    def speed(self) -> float:
        return self.SPEED

    def description(self) -> str:
        return "Hammer"


# Decorator Pattern: Able to add enchantments/upgrades
class ToolDecorator(Tool):
    def __init__(self, tool: Tool):
        self.tool = tool

    def damage(self) -> float:
        return self.tool.damage()

    def durability(self) -> float:
        return self.tool.durability()

    def speed(self) -> float:
        return self.tool.speed()

    def description(self) -> str:
        return self.tool.description()


# Define a decorator for Sharpness enchantment
class SharpnessDecorator(ToolDecorator):
    def __init__(self, tool: Tool, sharpness_value: float = 5.0):
        super().__init__(tool)
        self.sharpness_value = sharpness_value

    def damage(self) -> float:
        return (
            self.sharpness_value + self.tool.damage()
        )  # Adds extra damage for sharpness

    def durability(self) -> float:
        return self.tool.durability()  # No change to durability

    def speed(self) -> float:
        return self.tool.speed()  # No change to speed

    def description(self) -> str:
        return (
            self.tool.description()
            + f" with Sharpness (+{self.sharpness_value} damage)"
        )


# Define a decorator for Durability enchantment
class DurabilityDecorator(ToolDecorator):
    def __init__(self, tool: Tool, durability_value: float = 10.0):
        super().__init__(tool)
        self.durability_value = durability_value

    def damage(self) -> float:
        return self.tool.damage()  # No change to damage

    def durability(self) -> float:
        return self.durability_value + self.tool.durability()  # Adds extra durability

    def speed(self) -> float:
        return self.tool.speed()  # No change to speed

    def description(self) -> str:
        return self.tool.description() + f" with Durability (+{self.durability_value})"


# Define a decorator for Speed Boost enchantment
class SpeedBoostDecorator(ToolDecorator):
    def __init__(self, tool: Tool, speed_value: float = 10.0):
        super().__init__(tool)
        self.speed_value = speed_value

    def damage(self) -> float:
        return self.tool.damage()  # No change to damage

    def durability(self) -> float:
        return self.tool.durability()  # No change to durability

    def speed(self) -> float:
        return self.speed_value + self.tool.speed()  # Adds extra speed

    def description(self) -> str:
        return (
            self.tool.description() + f" with Speed Boost (+{self.speed_value} speed)"
        )


# Create a basic sword
basic_sword = Sword()
print("Basic Sword:")
print("Description:", basic_sword.description())
print("Damage:", basic_sword.damage())
print("Durability:", basic_sword.durability())
print("Speed:", basic_sword.speed())

# Add Sharpness and Durability to the sword with parameterized values
enchanted_sword = DurabilityDecorator(
    SharpnessDecorator(basic_sword, sharpness_value=7.0), durability_value=12.0
)
print("\nEnchanted Sword:")
print("Description:", enchanted_sword.description())
print("Damage:", enchanted_sword.damage())
print("Durability:", enchanted_sword.durability())
print("Speed:", enchanted_sword.speed())

# Add another layer of Speed Boost to the enchanted sword
fully_enchanted_sword = SpeedBoostDecorator(enchanted_sword, speed_value=15.0)
print("\nFully Enchanted Sword:")
print("Description:", fully_enchanted_sword.description())
print("Damage:", fully_enchanted_sword.damage())
print("Durability:", fully_enchanted_sword.durability())
print("Speed:", fully_enchanted_sword.speed())
