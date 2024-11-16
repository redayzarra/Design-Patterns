from abc import ABC, abstractmethod


# Create a Strategy pattern: Define an abstract class for the strategy
class Strategy:
    @abstractmethod
    def calculate_cost(self, weight: float, quantity: int) -> float:
        pass


# Define concrete classes that use the strategy
class ElectronicShipping(Strategy):
    def calculate_cost(self, weight: float, quantity: int) -> float:
        return (weight * 20) * quantity + 10


# Define concrete classes that use the strategy
class ClothingShipping(Strategy):
    def calculate_cost(self, weight: float, quantity: int) -> float:
        return (weight) * quantity + 10


# Define concrete classes that use the strategy
class BookShipping(Strategy):
    def calculate_cost(self, weight: float, quantity: int) -> float:
        base_cost = (weight * 10) * quantity + 10
        if quantity > 5:
            base_cost *= 0.8  # Apply 20% discount
        return base_cost


# Define a ShippingCalculator concrete class to switch strategies
class ShippingCalculator:
    def __init__(self, weight: float, quantity: int):
        self._strategy = None
        self._weight = weight
        self._quantity = quantity

    def set_strategy(self, strategy: Strategy):
        self._strategy = strategy

    def calculate_cost(self) -> float:
        if not self._strategy:
            raise ValueError(
                "Strategy not set. Use set_strategy() to define a strategy."
            )
        return self._strategy.calculate_cost(self._weight, self._quantity)


# Test the system with different strategies
# Initialize the calculator without a strategy
shipping_calculator = ShippingCalculator(weight=5, quantity=2)

# Set the strategy and calculate
shipping_calculator.set_strategy(ElectronicShipping())
print("Electronics Shipping Cost:", shipping_calculator.calculate_cost())

# Change to a different strategy
shipping_calculator.set_strategy(ClothingShipping())
print("Clothing Shipping Cost:", shipping_calculator.calculate_cost())

# Switch to books with updated weight/quantity
shipping_calculator.set_strategy(BookShipping())
shipping_calculator._weight = 0.5
shipping_calculator._quantity = 6
print("Books Shipping Cost:", shipping_calculator.calculate_cost())
