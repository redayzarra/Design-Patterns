from abc import ABC, abstractmethod
from enum import Enum


# Define enums for different meal components
class MainItem(Enum):
    BURGER = "Burger"
    SANDWICH = "Sandwich"
    SALAD = "Salad"


class SideItem(Enum):
    FRIES = "Fries"
    ONION_RINGS = "Onion Rings"
    SIDE_SALAD = "Side Salad"


class Drink(Enum):
    SODA = "Soda"
    JUICE = "Juice"
    WATER = "Water"


class Dessert(Enum):
    ICE_CREAM = "Ice Cream"
    PIE = "Pie"
    COOKIES = "Cookies"


# Define the Meal class
class Meal:
    def __init__(self):
        self._main_item = None
        self._sides = []
        self._drink = None
        self._dessert = None

    # Getter and setter for main_item
    @property
    def main_item(self):
        return self._main_item

    @main_item.setter
    def main_item(self, main_item: MainItem):
        self._main_item = main_item

    # Getter and setter for sides
    @property
    def sides(self):
        return self._sides

    @sides.setter
    def sides(self, sides: list[SideItem]):
        self._sides = sides

    # Getter and setter for drink
    @property
    def drink(self):
        return self._drink

    @drink.setter
    def drink(self, drink: Drink):
        self._drink = drink

    # Getter and setter for dessert
    @property
    def dessert(self):
        return self._dessert

    @dessert.setter
    def dessert(self, dessert: Dessert):
        self._dessert = dessert


# Create the Builder Pattern
class Builder(ABC):
    @abstractmethod
    def set_main_item(self, main_item: MainItem):
        pass

    @abstractmethod
    def add_side(self, side: SideItem | list[SideItem]):
        pass

    @abstractmethod
    def set_drink(self, drink: Drink):
        pass

    @abstractmethod
    def set_dessert(self, dessert: Dessert):
        pass

    # Build method returns the final product
    @abstractmethod
    def build(self):
        pass


# Define a MealBuilder concrete class for creating meals
class MealBuilder(Builder):
    def __init__(self):
        self.meal = Meal()

    def set_main_item(self, main_item: MainItem):
        self.meal.main_item = main_item
        return self

    def add_side(self, side: SideItem | list[SideItem]):
        if isinstance(side, list):
            self.meal.sides.extend(side)
        else:
            self.meal.sides.append(side)
        return self

    def set_drink(self, drink: Drink):
        self.meal.drink = drink
        return self

    def set_dessert(self, dessert: Dessert):
        self.meal.dessert = dessert
        return self

    def build(self):
        return self.meal


# Test the system
meal_builder = MealBuilder()
custom_meal = (
    meal_builder.set_main_item(MainItem.SANDWICH)
    .add_side([SideItem.ONION_RINGS, SideItem.SIDE_SALAD])
    .set_drink(Drink.JUICE)
    .set_dessert(Dessert.COOKIES)
    .build()
)

# Display the meal's details
print("Custom Meal:")
print(f"Main Item: {custom_meal.main_item.value}")
print(f"Sides: {[side.value for side in custom_meal.sides]}")
print(f"Drink: {custom_meal.drink.value}")
print(f"Dessert: {custom_meal.dessert.value if custom_meal.dessert else 'No Dessert'}")
