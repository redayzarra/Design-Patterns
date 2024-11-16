from abc import ABC, abstractmethod
import copy
from enum import Enum
import threading
from typing import List


# Define a vehicle base class
class Vehicle(ABC):
    def __init__(self, license_plate: str, vehicle_type: str):
        self.license_plate = license_plate
        self.vehicle_type = vehicle_type


# Define a subclass for cars
class Car(Vehicle):
    def __init__(self, license_plate: str):
        super().__init__(license_plate=license_plate, vehicle_type="car")


# Define a subclass for motorcycles
class Motorcycle(Vehicle):
    def __init__(self, license_plate: str):
        super().__init__(license_plate=license_plate, vehicle_type="motorcycle")


# Define a subclass for trucks
class Truck(Vehicle):
    def __init__(self, license_plate: str):
        super().__init__(license_plate=license_plate, vehicle_type="truck")


# Create a Factory Pattern: creates car's based on the type
class VehicleFactory:
    # Create a static method - (function that lives in class) that creates vehicles
    @staticmethod
    def create_vehicle(vehicle_type: str, license_plate: str) -> Vehicle:
        if vehicle_type == "car":
            return Car(license_plate)
        elif vehicle_type == "motorcycle":
            return Motorcycle(license_plate)
        elif vehicle_type == "truck":
            return Truck(license_plate)
        else:
            raise ValueError(f"Unknown vehicle type: {vehicle_type}")


# Create a car
car = VehicleFactory.create_vehicle("car", "ABC123")
print(f"Created a {car.vehicle_type} with license plate {car.license_plate}")

# Create a motorcycle
motorcycle = VehicleFactory.create_vehicle("motorcycle", "XYZ789")
print(
    f"Created a {motorcycle.vehicle_type} with license plate {motorcycle.license_plate}"
)

# Create a truck
truck = VehicleFactory.create_vehicle("truck", "LMN456")
print(f"Created a {truck.vehicle_type} with license plate {truck.license_plate}")


# Create a Singleton Pattern: parking lot manager that tracks spots
# Create a Singleton Pattern: parking lot manager that tracks spots
class ParkingLotManager:
    _instance = None
    _instance_lock = threading.Lock()

    def __new__(cls, free_spots: int):
        with cls._instance_lock:
            if cls._instance is None:
                cls._instance = super(ParkingLotManager, cls).__new__(cls)
                cls._instance._free_spots = free_spots
        return cls._instance

    @property
    def free_spots(self) -> int:
        return self._free_spots  # Access the internal attribute

    @free_spots.setter
    def free_spots(self, free_spots: int):
        self._free_spots = free_spots


# Create a parking lot manager with an initial number of available spots
manager = ParkingLotManager(free_spots=50)
print(f"Initial available spots: {manager.free_spots}")

# Try to create a second instance with a different number of available spots
another_parking_lot_manager = ParkingLotManager(free_spots=100)
print(
    f"Available spots after second instantiation attempt: {another_parking_lot_manager.free_spots}"
)

# Update the number of available spots
manager.free_spots += 1
print(f"Updated available spots: {manager.free_spots}")


# Define an enum for the type of ticket
class TicketType(Enum):
    STANDARD = 1
    VIP = 2


# Define a Ticket class that contains information about the ticket
class Ticket:
    def __init__(self):
        self.license_plate = None
        self.entry_time = None
        self.spot_location = None
        self.ticket_type = None

    @property
    def license_plate(self):
        return self._license_plate

    @license_plate.setter
    def license_plate(self, license_plate: str):
        self._license_plate = license_plate

    @property
    def entry_time(self):
        return self._entry_time

    @entry_time.setter
    def entry_time(self, entry_time):
        self._entry_time = entry_time

    @property
    def spot_location(self):
        return self._spot_location

    @spot_location.setter
    def spot_location(self, spot_location: int):
        self._spot_location = spot_location

    @property
    def ticket_type(self):
        return self._ticket_type

    @ticket_type.setter
    def ticket_type(self, ticket_type: TicketType):
        self._ticket_type = ticket_type


# Create a Builder Pattern: Allows us to create different types of tickets
class Builder(ABC):
    @abstractmethod
    def set_license_plate(self, license_plate: str):
        pass

    @abstractmethod
    def set_entry_time(self, entry_time):
        pass

    @abstractmethod
    def set_spot_location(self, spot_location: int):
        pass

    @abstractmethod
    def set_ticket_type(self, ticket_type: TicketType):
        pass

    def build(self):
        return self.ticket


# Define a concrete builder for Standard Tickets
class StandardTicketBuilder(Builder):
    def __init__(self):
        # Initializes an empty Ticket object
        self.ticket = Ticket()

    def set_license_plate(self, license_plate: str):
        self.ticket.license_plate = license_plate
        return self

    def set_entry_time(self, entry_time):
        self.ticket.entry_time = entry_time
        return self

    def set_spot_location(self, spot_location: int):
        self.ticket.spot_location = spot_location
        return self

    def set_ticket_type(self, ticket_type: TicketType = TicketType.STANDARD):
        self.ticket.ticket_type = ticket_type
        return self

    def build(self):
        return self.ticket


# Concrete concrete builder for VIP Tickets
class VIPTicketBuilder(Builder):
    def __init__(self):
        self.ticket = Ticket()  # Initializes an empty Ticket object

    def set_license_plate(self, license_plate: str):
        self.ticket.license_plate = license_plate
        return self

    def set_entry_time(self, entry_time):
        self.ticket.entry_time = entry_time
        return self

    def set_spot_location(self, spot_location: int):
        self.ticket.spot_location = spot_location
        return self

    def set_ticket_type(self, ticket_type: TicketType = TicketType.VIP):
        self.ticket.ticket_type = ticket_type
        return self

    def build(self):
        return self.ticket


# Initialize the builder for a standard ticket
standard_ticket_builder = StandardTicketBuilder()

# Set properties for the ticket
standard_ticket = (
    standard_ticket_builder.set_license_plate("ABC123")
    .set_entry_time("2024-11-10 10:00")
    .set_spot_location(42)
    .set_ticket_type(TicketType.STANDARD)
    .build()
)

# Display the ticket's details
print("Standard Ticket:")
print(f"License Plate: {standard_ticket.license_plate}")
print(f"Entry Time: {standard_ticket.entry_time}")
print(f"Spot Location: {standard_ticket.spot_location}")
print(f"Ticket Type: {standard_ticket.ticket_type.name}")


# Create a Prototype Pattern: Allows things to create copies of themeselves
class Prototype(ABC):
    @abstractmethod
    def clone(self):
        pass


# Define a parking spot class that stores information about parking spots
class ParkingSpot(Prototype):
    def __init__(self, spot_number: int, spot_size: int, is_reserved: bool = False):
        self.spot_number = spot_number
        self.spot_size = spot_size
        self.is_reserved = is_reserved

    def clone(self):
        return copy.copy(self)


# Create an initial parking spot prototype
original_spot = ParkingSpot(spot_number=1, spot_size="medium", is_reserved=True)
print("Original Spot:")
print(f"Spot Number: {original_spot.spot_number}")
print(f"Spot Size: {original_spot.spot_size}")
print(f"Is Reserved: {original_spot.is_reserved}")

# Clone the original spot to create similar spots
cloned_spot1 = original_spot.clone()
cloned_spot1.spot_number = 2  # Change the spot number
cloned_spot1.is_reserved = False  # This spot is not reserved

cloned_spot2 = original_spot.clone()
cloned_spot2.spot_number = 3  # Another unique spot number


# Define an abstract class that acts as an interface for payments
class PaymentProcessor(ABC):
    @abstractmethod
    def process_payment(self, amount: float, license_plate: str):
        pass


# Define a legacy payment processor class
class LegacyPaymentProcessor:
    def pay(self, account_id: str, payment_amount: float):
        print(
            f"Processing payment of ${payment_amount} for account {account_id} using legacy processor."
        )


# Create an Adapter Pattern:
class PaymentProcessorAdapter(PaymentProcessor):
    def __init__(self, legacy_processor: LegacyPaymentProcessor):
        self.legacy_processor = legacy_processor

    def process_payment(self, amount: float, license_plate: str):
        # Adapt the license_plate to an account_id and call pay()
        account_id = self.convert_to_account(license_plate)
        self.legacy_processor.pay(account_id, amount)

    def convert_to_account(self, license_plate: str) -> str:
        return f"ACC-{license_plate}"


# Create an instance of the legacy payment processor
legacy_processor = LegacyPaymentProcessor()

# Wrap the legacy processor with the adapter
adapter = PaymentProcessorAdapter(legacy_processor)

# Use the adapter to process a payment
adapter.process_payment(amount=25.0, license_plate="XYZ123")


# Define an abstract class for services that we provide
class Service(ABC):
    # Each service has to have a cost
    @abstractmethod
    def cost(self) -> float:
        pass

    # Each service has to have a description
    @abstractmethod
    def description(self) -> float:
        pass


# Define a concrete class that accepts a service
class BasicParking(Service):
    # Mandatory cost for being a basic service
    def cost(self) -> float:
        return 10.0

    # Mandatory description for being a service
    def description(self):
        return "Basic Parking"


# Create a Decorator Pattern: This is a Service
class ServiceDecorator(Service):
    # Accepts other Services
    def __init__(self, service: Service):
        self.service = service

    # Provides access to the given Service's cost
    def cost(self) -> float:
        return self.service.cost()

    # Provides access to the given Service's description
    def description(self) -> str:
        return self.service.description()


# Create a decorator to add washing service: Type of Service
class WashDecorator(ServiceDecorator):
    def cost(self) -> float:
        return 5.0 + self.service.cost()

    def description(self) -> str:
        return self.service.description() + ", Wash"


# Create a decorator to add vacuum service: Type of Service
class VacuumDecorator(ServiceDecorator):
    def cost(self) -> float:
        return 3.0 + self.service.cost()

    def description(self) -> str:
        return self.service.description() + ", Vacuum"


# Create a decorator to add detailing service: Type of Service
class DetailingDecorator(ServiceDecorator):
    def cost(self) -> float:
        return 15.0 + self.service.cost()

    def description(self) -> str:
        return self.service.description() + ", Detailing"


# Create a basic parking service
basic_parking = BasicParking()
print("Description:", basic_parking.description())
print("Cost:", basic_parking.cost())

# Add a vacuum and detailing service on top of basic parking
full_service = DetailingDecorator(VacuumDecorator(basic_parking))
print("Description:", full_service.description())
print("Cost:", full_service.cost())


# Create a Strategy Pattern
class PaymentStrategy(ABC):
    @abstractmethod
    def pay(self, amount: float, license_plate: str):
        pass


# Define a concrete class for cash payments
class CashPayment(PaymentStrategy):
    def pay(self, amount: float, license_plate: str):
        print(f"Processing cash payment of ${amount} for vehicle {license_plate}")


# Implement a concrete strategy for credit card payment
class CreditCardPayment(PaymentStrategy):
    def pay(self, amount: float, license_plate: str):
        print(
            f"Processing credit card payment of ${amount} for vehicle {license_plate}"
        )


# Implement a concrete strategy for mobile payment
class MobilePayment(PaymentStrategy):
    def pay(self, amount: float, license_plate: str):
        print(f"Processing mobile payment of ${amount} for vehicle {license_plate}")


# Create the Observer Pattern: define the observer interface
class Observer(ABC):
    @abstractmethod
    def announce(self, message: str):
        pass


# Create the Observer Pattern: define the subject interface
class Subject(ABC):
    @abstractmethod
    def register_observer(self, observer: Observer):
        pass

    @abstractmethod
    def remove_observer(self, observer: Observer):
        pass

    @abstractmethod
    def notify_observers(self, message: str):
        pass


# Define a concrete subject class
class ParkingLotSubject(Subject):
    def __init__(self):
        self._observers: List[Observer] = []

    def register_observer(self, observer: Observer):
        self._observers.append(observer)

    def remove_observer(self, observer: Observer):
        self._observers.remove(observer)

    def notify_observers(self, message: str):
        for observer in self._observers:
            observer.announce(message)


# Define a concrete observer class
class SecuritySystem(Observer):
    def announce(self, message: str):
        print(f"Security System Alert: {message}")


# Define a concrete observer class
class BillingSystem(Observer):
    def announce(self, message: str):
        print(f"Billing System Notification: {message}")


# Create a State Pattern: Keeps track of the state of a gate
class GateState(ABC):
    @abstractmethod
    def handle_request(self, gate):
        pass


# Define some concrete gate state classes
class GateOpenState(GateState):
    def handle_request(self, gate):
        print("Gate is already open. Allowing vehicle entry or exit.")
        gate.set_state(GateClosingState())  # Transition to closing after action


class GateClosedState(GateState):
    def handle_request(self, gate):
        print("Gate is closed. Initiating opening process.")
        gate.set_state(GateOpeningState())  # Transition to opening when requested


class GateOpeningState(GateState):
    def handle_request(self, gate):
        print("Gate is opening... now fully open.")
        gate.set_state(GateOpenState())  # Transition to open after fully opened


class GateClosingState(GateState):
    def handle_request(self, gate):
        print("Gate is closing... now fully closed.")
        gate.set_state(GateClosedState())  # Transition to closed after fully closed


# Define a concrete parking gate class
class ParkingGate:
    def __init__(self):
        # Initialize with the gate closed
        self.state = GateClosedState()
        self.prev_state = None

    def set_state(self, state):
        self.prev_state = self.state
        self.state = state

    def get_prev_state(self):
        return self.prev_state

    def request(self):
        # Delegate the request to the current state
        self.state.handle_request(self)


# Create a Facade Pattern: Simplify the complex logic (hide it)
class ParkingLotFacade(ParkingLotSubject):
    def __init__(self, payment_strategy):
        super().__init__()
        self.payment_strategy = payment_strategy
        self.manager = ParkingLotManager(free_spots=50)
        self.gate = ParkingGate()  # Add ParkingGate instance

    def enter_parking_lot(self, vehicle_type: str, license_plate: str):
        # Open the gate to allow entry
        print("Opening gate for vehicle entry.")
        self.gate.request()  # Should transition to opening, then open

        vehicle = VehicleFactory.create_vehicle(vehicle_type, license_plate)

        if self.manager.free_spots > 0:
            ticket_builder = (
                StandardTicketBuilder()
                .set_license_plate(license_plate)
                .set_entry_time("Current date")
            )
            ticket = ticket_builder.set_spot_location(self.manager.free_spots).build()
            self.manager.free_spots -= 1

            self.notify_observers(
                f"{vehicle.vehicle_type.capitalize()} with license plate {license_plate} entered. Spot assigned: {ticket.spot_location}."
            )

            # Close the gate after entry
            print("Closing gate after vehicle entry.")
            self.gate.request()  # Should transition to closing, then closed

            return ticket
        else:
            self.notify_observers("No available spots. Entry denied.")
            # Close the gate if no spots are available
            print("Closing gate due to lack of availability.")
            self.gate.request()  # Should transition to closing, then closed
            return None

    def exit_parking_lot(self, ticket: Ticket, amount: float):
        # Open the gate for vehicle exit
        print("Opening gate for vehicle exit.")
        self.gate.request()  # Should transition to opening, then open

        # Process payment
        self.payment_strategy.pay(amount, ticket.license_plate)
        self.manager.free_spots += 1
        self.notify_observers(
            f"Vehicle with license plate {ticket.license_plate} exited. Spot {ticket.spot_location} is now available."
        )

        # Close the gate after exit
        print("Closing gate after vehicle exit.")
        self.gate.request()  # Should transition to closing, then closed


# Initialize the ParkingLotFacade with a payment strategy
system = ParkingLotFacade(CashPayment())

# Create observers
security = SecuritySystem()
billing = BillingSystem()

# Register observers to the system
system.register_observer(security)
system.register_observer(billing)

# Simulate a vehicle entering
ticket = system.enter_parking_lot("car", "ABC123")

# Simulate exiting the parking lot and processing payment
if ticket:
    system.exit_parking_lot(ticket, amount=10.0)

# Remove an observer and simulate another vehicle exit to test removal
system.remove_observer(billing)
print("\nSimulating another vehicle exit with billing observer removed.")
ticket = system.enter_parking_lot("truck", "XYZ789")
if ticket:
    system.exit_parking_lot(ticket, amount=20.0)
