# Subsystem classes
class InventorySystem:
    def check_stock(self, book_id):
        print(f"Checking stock for book ID: {book_id}")
        # Simulate stock availability
        return True
    
    def reserve_item(self, book_id):
        print(f"Reserving book ID: {book_id}")

class PaymentSystem:
    def process_payment(self, customer_id, amount):
        print(f"Processing payment for customer ID: {customer_id} with amount: ${amount}")
        # Simulate successful payment
        return True

class ShippingSystem:
    def schedule_shipment(self, book_id, customer_id):
        print(f"Scheduling shipment for book ID: {book_id} to customer ID: {customer_id}")

# Facade class
class BookstoreFacade:
    def __init__(self):
        self.inventory = InventorySystem()
        self.payment = PaymentSystem()
        self.shipping = ShippingSystem()

    def place_order(self, book_id, customer_id, amount):
        # Step 1: Check if the book is in stock
        if not self.inventory.check_stock(book_id):
            print("Book is out of stock!")
            return False
        
        # Step 2: Reserve the item in inventory
        self.inventory.reserve_item(book_id)
        
        # Step 3: Process the payment
        if not self.payment.process_payment(customer_id, amount):
            print("Payment failed!")
            return False
        
        # Step 4: Schedule the shipment
        self.shipping.schedule_shipment(book_id, customer_id)
        print("Order placed successfully!")
        return True

# Example usage
bookstore = BookstoreFacade()
bookstore.place_order(book_id=101, customer_id=501, amount=29.99)
