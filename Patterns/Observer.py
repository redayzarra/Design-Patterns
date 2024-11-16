from abc import ABC, abstractmethod

# Create the observer pattern: Define an abstract class for observer
class Observer(ABC):
    def announce(self, message: str):
        pass

# Create the observer pattern: Define an abstract class for subject
class Subject(ABC):
    def add_observer(self, observer: Observer):
        pass

    def remove_observer(self, observer: Observer):
        pass

    def notify_observers(self, message: str):
        pass
    
# Define a Notification System concrete class which is a subject
class NotificationSystem(Subject):
    def __init__(self):
        self._observers: set[Observer] = set()
        
    def add_observer(self, observer: Observer | tuple[Observer]):
        if isinstance(observer, Observer):
            self._observers.add(observer)
        else:
            self._observers.update(observer)
        
    def remove_observer(self, observer: Observer):
        self._observers.remove(observer)
        
    def notify_observers(self, message: str):
        for observer in self._observers:
            observer.announce(message)
            

# Define a concrete class for EmailObservers
class EmailObserver(Observer):
    def announce(self, message: str):
        print(f"Email Observer: {message}")
    
# Define a concrete class for SMSObservers
class SMSObserver(Observer):
    def announce(self, message: str):
        print(f"SMSObserver: {message}")
        
# Define a concrete class for SMSObservers
class AppObserver(Observer):
    def announce(self, message: str):
        print(f"AppObserver: {message}")
        
    
# Test the system
system = NotificationSystem()

# Create observers
Josh = EmailObserver()
Alina = SMSObserver()
Jacob = AppObserver()

system.add_observer((Josh, Alina, Jacob))

system.notify_observers("There is a car approaching.")
system.notify_observers("There is a person at the front door.")
system.remove_observer(Josh)

system.notify_observers("The front window is broken.")
