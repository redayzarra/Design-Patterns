from abc import ABC, abstractmethod
import enum
from typing import List


# Creating an enum to store the only way to define sizes
class Size(enum.Enum):
    SMALL = "Small"
    MEDIUM = "Medium"
    LARGE = "Large"
    
class Pizza:
    def __init__(self, type: str, size: Size, toppings: List[str]):
        # Core pizza attributes
        self.type = type
        self.size = size
        self.toppings = toppings
        
        # Default pricing details
        self.size_prices = {"Small": 10, "Medium": 15, "Large": 20}
        self.topping_price = 1.5


    def calculate_price(self) -> float:
        # Check if the size is valid using the size's value
        if self.size.value not in self.size_prices:
            raise ValueError(f"Invalid size: {self.size.value}")
        
        # Calculate the base price and topping price
        base_price = self.size_prices[self.size.value]
        toppings_total = self.topping_price * len(self.toppings)
        return base_price + toppings_total
    

# Define a baking interface which only applies to some pizza types
class Bakeable(ABC):
    @abstractmethod
    def prepare(self):
        pass

# Define a baking interface which only applies to some pizza types
class Grillable(ABC):
    @abstractmethod
    def prepare(self):
        pass

# Define a pizza subclass that also requires a prepare method for baking
class MargheritaPizza(Pizza, Bakeable):
    def __init__(self, size: Size, toppings: List[str]):
        super().__init__(type="margherita", size=size, toppings=toppings)
        self.size_prices = {"Small": 12, "Medium": 16, "Large": 22}
        self.topping_price = 2
        
    def prepare(self):
        print("Baking Margherita pizza...")
        
# Define a pizza subclass that also requires a prepare method for baking
class PepperoniPizza(Pizza, Grillable):
    def __init__(self, size: Size, toppings: List[str]):
        super().__init__(type="pepperoni", size=size, toppings=toppings)
        self.size_prices = {"Small": 14, "Medium": 18, "Large": 25}
        self.topping_price = 2
        
    def prepare(self):
        print("Grilling pepperoni pizza...")
        
# Define a pizza subclass that also requires a prepare method for baking
class GlutenFreePizza(Pizza):
    def __init__(self, size: Size, toppings: List[str]):
        super().__init__(type="gluten-free", size=size, toppings=toppings)
        self.size_prices = {"Small": 14, "Medium": 18, "Large": 25}
        self.topping_price = 0
    
# Create an interface that creates many different payment types
class PaymentMethod(ABC):
    def __init__(self, type: str):
        self.type = type
        
    @abstractmethod
    def process_payment(self):
        pass
        
# Define a credit card payment type
class CreditCard(PaymentMethod):
    def __init__(self):
        super().__init__(type="credit")
    
    def process_payment(self):
        print("Processing payment with Credit Card...")

# Define a paypal payment type
class PayPal(PaymentMethod):
    def __init__(self):
        super().__init__(type="paypal")
    
    def process_payment(self):
        print("Processing payment with PayPal...")
        
# Define a cash payment type
class Cash(PaymentMethod):
    def __init__(self):
        super().__init__(type="cash")
    
    def process_payment(self):
        print("Processing cash payment...")
        
# Create an orde class that only accepts a payment method
class Order:
    def __init__(self, items: List[Pizza], payment: PaymentMethod):
        self.items = items
        self.payment = payment
        
    def add_pizza(self, pizza: Pizza) -> None:
        self.items.append(pizza)
        
    def remove_pizza(self, pizza: Pizza) -> None:
        self.items.remove(pizza)
        
    def calculate_total(self) -> float:
        total = 0
        for pizza in self.items:
            total += pizza.calculate_price()
        return total
    
    def display_order_summary(self) -> None:
        print("Order Summary:")
        for pizza in self.items:
            print(f"- {pizza.type} ({pizza.size}): ${pizza.calculate_price():.2f}")
        print(f"Total: ${self.calculate_total():.2f}")
    