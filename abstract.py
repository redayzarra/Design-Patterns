from abc import ABC, abstractmethod


class BankAccount(ABC):
    def __init__(self, account_holder: str, account_type: str, balance: int = 0):
        self.account_holder = account_holder
        self.account_type = account_type
        self._balance = balance
        
    def deposit(self, amount: int) -> None:
        if amount > 0:
            self._balance += amount
        
    @abstractmethod
    def withdraw(self, amount: int) -> None:
        pass

    def get_balance(self):
        return self._balance
    
class SavingsAccount(BankAccount):
    def __init__(self, account_holder: str, balance: int, interest_rate: int = 0.05):
        super().__init__(account_holder, "savings", balance)
        self.interest_rate = interest_rate
    
    def withdraw(self, amount: int) -> None:
        if self._balance >= amount:
            self._balance -= amount
        
    def apply_interest(self) -> None:
        self._balance += (self._balance * self.interest_rate)

class CheckingAccount(BankAccount):
    def __init__(self, account_holder: str, balance: int, overdraft_limit: int = 500):
        super().__init__(account_holder, "checking", balance)
        self.overdraft_limit = overdraft_limit
    
    def withdraw(self, amount: int) -> None:
        if (self._balance + self.overdraft_limit) >= amount:
            self._balance -= amount
    