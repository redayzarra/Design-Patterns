from abc import ABC, abstractmethod
from enum import Enum
import threading
import uuid


# Define Pizza enums
class Size(Enum):
    SMALL = "small"
    MEDIUM = "medium"
    LARGE = "large"


class Crust(Enum):
    THIN = "thin"
    THICK = "thick"


class Sauce(Enum):
    TOMATO = "tomato"
    MARINARA = "marinara"
    BBQ = "bbq"


class Topping(Enum):
    JALAPENO = "jalapeno"
    OLIVES = "olives"
    PINEAPPLE = "pineapple"
    MUSHROOMS = "mushrooms"
    SAUSAGE = "sausage"
    PEPPERONI = "pepperoni"


class Pizza:

    def __init__(self):
        self._size: Size = None
        self._crust: Crust = None
        self._sauce: Sauce = None
        self._toppings: set[Topping] = set()

    @property
    def size(self):
        return self._size

    @size.setter
    def size(self, size: Size):
        if not isinstance(size, Size):
            raise ValueError(f"Invalid size: {size}")
        self._size = size

    @property
    def crust(self):
        return self._crust

    @crust.setter
    def crust(self, crust: Crust):
        if not isinstance(crust, Crust):
            raise ValueError(f"Invalid crust: {crust}")
        self._crust = crust

    @property
    def sauce(self):
        return self._sauce

    @sauce.setter
    def sauce(self, sauce: Sauce):
        if not isinstance(sauce, Sauce):
            raise ValueError(f"Invalid sauce: {sauce}")
        self._sauce = sauce

    @property
    def toppings(self):
        return self._toppings

    @toppings.setter
    def toppings(self, toppings: set[Topping]):
        self._toppings = toppings


# Define an abstract builder for Pizza
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
    def add_topping(self, topping: Topping | tuple[Topping]):
        pass

    @abstractmethod
    def build(self) -> Pizza:
        pass


# Define a PizzaBuilder concrete class
class PizzaBuilder(Builder):
    def __init__(self):
        self.pizza: Pizza = Pizza()

    def set_size(self, size: Size):
        self.pizza.size = size
        return self

    def set_crust(self, crust: Crust):
        self.pizza.crust = crust
        return self

    def set_sauce(self, sauce: Sauce):
        self.pizza.sauce = sauce
        return self

    def add_topping(self, topping: Topping | tuple[Topping]):
        if isinstance(topping, Topping):
            self.pizza.toppings.add(topping)
        elif isinstance(topping, tuple):
            self.pizza.toppings.update(topping)
        return self  # Enable method chaining

    def build(self) -> Pizza:
        return self.pizza


# Create a Factory to produce predefined pizzas
class PizzaFactory:
    @staticmethod
    def create_pizza(
        type: str = None, size: Size = Size.MEDIUM, crust: Crust = Crust.THIN
    ) -> Pizza:
        builder = PizzaBuilder()

        if not type or not isinstance(type, str):
            raise ValueError(f"Invalid type for pizza: {type}")

        # Convert type to lower for uniformity
        type = type.lower()

        # Configure pizzas based on type
        if type == "margherita":
            return (
                builder.set_size(size)
                .set_crust(crust)
                .set_sauce(Sauce.TOMATO)
                .add_topping((Topping.MUSHROOMS, Topping.OLIVES))
                .build()
            )

        elif type == "pepperoni":
            return (
                builder.set_size(size)
                .set_crust(crust)
                .set_sauce(Sauce.MARINARA)
                .add_topping((Topping.PEPPERONI, Topping.SAUSAGE))
                .build()
            )

        elif type == "cheese":
            return (
                builder.set_size(size).set_crust(crust).set_sauce(Sauce.TOMATO).build()
            )

        else:
            raise ValueError(f"Unknown pizza type: {type}")


# Create a Strategy Pattern: Defines an abstract class (interface)
class PricingStrategy(ABC):
    # Define static price data
    SIZE_PRICES = {
        Size.SMALL: 5.00,
        Size.MEDIUM: 7.00,
        Size.LARGE: 10.00,
    }

    CRUST_PRICES = {
        Crust.THIN: 0.00,  # Default price
        Crust.THICK: 1.50,  # Extra charge for thick crust
    }

    TOPPING_PRICES = {
        Topping.JALAPENO: 0.75,
        Topping.OLIVES: 0.50,
        Topping.PINEAPPLE: 1.00,
        Topping.MUSHROOMS: 0.75,
        Topping.SAUSAGE: 1.25,
        Topping.PEPPERONI: 1.25,
    }

    @abstractmethod
    def calculate_price(self, pizza: Pizza) -> float:
        pass


# Define a DefaultPricing strategy concrete class
class DefaultPricing(PricingStrategy):
    def calculate_price(self, pizza: Pizza) -> float:
        # Get the base price for the size and crust
        base_price = self.SIZE_PRICES.get(pizza.size, 0.00)
        crust_price = self.CRUST_PRICES.get(pizza.crust, 0.00)

        # Calculate the total price for toppings
        topping_price = sum(
            self.TOPPING_PRICES.get(topping, 0.00) for topping in pizza.toppings
        )

        # Calculate the total
        return base_price + crust_price + topping_price


# Create Observer Pattern: Define an abstract class for observer
class Observer(ABC):
    @abstractmethod
    def receive(self, message: str):
        pass


# Create Observer Pattern: Define an abstract class for subjects
class Subject(ABC):
    @abstractmethod
    def add_observer(self, observer: Observer):
        pass

    @abstractmethod
    def remove_observer(self, observer: Observer):
        pass

    @abstractmethod
    def notify(self, message: str):
        pass


class NotificationSystem(Subject):

    def __init__(self):
        self.observers: set[Observer] = set()

    def add_observer(self, observer: Observer):
        self.observers.add(observer)

    def remove_observer(self, observer: Observer):
        self.observers.remove(observer)

    def notify(self, message: str):
        for observer in self.observers:
            observer.receive(message)


# Create a State pattern: Define an abstract class (interface) for states
class OrderState(ABC):
    @abstractmethod
    def update(self, order: "Order") -> str:
        pass


# Concrete States
class ReceivedState(OrderState):
    def update(self, order: "Order"):
        order.status = InPreparationState()
        return "Order is being prepared."


class InPreparationState(OrderState):
    def update(self, order: "Order"):
        order.status = ReadyState()
        return "Order is ready for pickup or delivery."


class ReadyState(OrderState):
    def update(self, order: "Order"):
        order.status = DeliveredState()
        return "Order is delivered."


class DeliveredState(OrderState):
    def update(self, order: "Order"):
        return "Order has already been delivered."


# Concrete Observer for customer notifications
class CustomerObserver(Observer):
    def receive(self, message: str):
        print(f"Notification to customer: {message}")


# Define the Order concrete class which will use strategy
# Order concrete class using strategy, state, and observer patterns
class Order:
    def __init__(self, order_id: int, pricing: PricingStrategy = DefaultPricing()):
        # Technical attributes
        self.order_id = order_id
        self.items: list[Pizza] = []
        self.pricing = pricing
        self.total = 0
        # Order status attributes
        self._status: OrderState = ReceivedState()
        self.status_history = [self.status.__class__.__name__]
        self.notification_system = NotificationSystem()

    @property
    def status(self):
        return self._status

    @status.setter
    def status(self, status: OrderState):
        self._status = status
        self.status_history.append(self._status.__class__.__name__)

    def add_item(self, item: Pizza):
        self.items.append(item)
        self.total += self.pricing.calculate_price(item)

    def update_status(self):
        status_message = self.status.update(self)
        message = f"Order {self.order_id}: {status_message}"
        self.notification_system.notify(message)


    def add_customer_observer(self, observer: Observer):
        self.notification_system.add_observer(observer)

    def remove_customer_observer(self, observer: Observer):
        self.notification_system.remove_observer(observer)


# Create a Singleton Pattern: to create a cashier to keep track of $$
class Cashier:
    _instance = None
    _instance_lock = threading.Lock()
    
    def __new__(cls):
        with cls._instance_lock:
            if cls._instance is None:
                cls._instance = super(Cashier, cls).__new__(cls)
                cls._instance._total = 0.0
                cls._instance.orders = []
        return cls._instance
    
    def __init__(self):
        if not hasattr(self, '_total'):
            self._total = 0.0
            self.orders = []  # Use list instead of set to avoid hash issues
    
    def get_total(self) -> float:
        return self._total
    
    def create_order(self, pricing: PricingStrategy = DefaultPricing()):
        order_id = len(self.orders) + 1
        new_order = Order(order_id=order_id, pricing=pricing)
        self.orders.append(new_order)
        return new_order
    
    def complete_order(self, order: Order):
        if order.status.__class__ is DeliveredState:
            self._total += order.total  # Update total only if the order is delivered
        else:
            print("Order is not yet completed.")
    
    def get_order_count(self) -> int:
        return len(self.orders)
