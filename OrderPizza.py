# Create enums for customizing the pizza
from abc import ABC, abstractmethod
from enum import Enum


class Size(Enum):
    SMALL = "small"
    MEDIUM = "medium"
    LARGE = "large"


class Crust(Enum):
    THIN = "thin"
    THICK = "thick"


class Sauce(Enum):
    TOMATO = "tomato"
    BBQ = "bbq"


class Topping(Enum):
    JALAPENO = "jalapeno"
    OLIVES = "olives"
    PINEAPPLE = "pineapple"
    MUSHROOMS = "mushrooms"


# Define a concrete class for pizza
class Pizza:
    def __init__(self):
        # Individual attributes
        self._size: Size = None
        self._crust: Crust = None
        self._sauce: Sauce = None
        self._toppings: list[Topping] = []

    def __str__(self):
        return (
            f"Pizza(size={self.size}, crust={self.crust}, "
            f"sauce={self.sauce}, toppings={self.toppings})"
        )

    # Create getter and setters for size
    @property
    def size(self):
        return self._size

    @size.setter
    def size(self, size: Size):
        self._size = size

    # Create getter and setter for crust
    @property
    def crust(self):
        return self._crust

    @crust.setter
    def crust(self, crust: Crust):
        self._crust = crust

    # Create getter and setter for sauce
    @property
    def sauce(self):
        return self._sauce

    @sauce.setter
    def sauce(self, sauce: Sauce):
        self._sauce = sauce

    # Create getter and setter for toppings
    @property
    def toppings(self):
        return self._toppings

    @toppings.setter
    def toppings(self, toppings: list[Topping]):
        self._toppings = toppings


# Create the Builder pattern for quickly building custom pizzas
class Builder(ABC):
    @abstractmethod
    def set_size(self, size: Size):
        pass

    @abstractmethod
    def set_crust(self, crust: Crust):
        pass

    @abstractmethod
    def set_sauce(self, sauce: Sauce):
        pass

    @abstractmethod
    def add_topping(self, topping: Topping):
        pass

    @abstractmethod
    def build(self):
        pass


# Define a concrete Pizza builder class
class PizzaBuilder(Builder):
    def __init__(self):
        self.pizza = Pizza()

    def set_size(self, size: Size):
        self.pizza.size = size
        return self

    def set_crust(self, crust: Crust):
        self.pizza.crust = crust
        return self

    def set_sauce(self, sauce: Sauce):
        self.pizza.sauce = sauce
        return self

    def add_topping(self, topping: Topping | list[Topping]):
        # If topping is a list, add the entire list
        if isinstance(topping, list):
            self.pizza.toppings.extend(topping)
        else:
            self.pizza.toppings.append(topping)
        return self

    def build(self):
        return self.pizza


# Test the system
pizza_builder = PizzaBuilder()

my_pizza = (PizzaBuilder()
            .set_size(Size.LARGE)
            .set_crust(Crust.THIN)
            .set_sauce(Sauce.TOMATO)
            .add_topping([Topping.JALAPENO, Topping.OLIVES])
            .build())

print(my_pizza)
