from typing import List


class BankAccount:
    def __init__(self, account_holder, account_type: str, balance=0):
        self.account_holder = account_holder
        self.account_type = account_type
        self._balance = balance  # Private attribute

    def deposit(self, amount):
        if amount > 0:
            self._balance += amount

    def withdraw(self, amount):
        if 0 < amount <= self._balance:
            self._balance -= amount

    def transfer(self, amount, target_account: "BankAccount"):
        balance = self.get_balance()
        if self.account_type == target_account.account_type and balance >= amount:
            self.withdraw(amount)
            target_account.deposit(amount)

    def get_balance(self):
        return self._balance

class SavingsAccount(BankAccount):
    def __init__(self, account_holder, balance: int = 0, interest_rate: int = 0.05):
        # Initialize the Bank account parent class
        super().__init__(account_holder, account_type="savings", balance=balance)
        self.interest_rate = interest_rate
        
    def apply_interest(self):
        # Adds interest to the bank account
        self._balance += (self._balance * self.interest_rate)
        
class CheckingAccount(BankAccount):
    def __init__(self, account_holder, balance: int = 0, overdraft_limit: int = 500):
        super().__init__(account_holder, account_type="checking", balance=balance)
        self.overdraft_limit = overdraft_limit
        
    def withdraw(self, amount: int) -> None:
        # Allows overdraft within the limit
        if (self._balance + self.overdraft_limit) >= amount:
            self._balance -= amount

def process_accounts(accounts: List["BankAccount"], deposit_amount: int = 20, withdraw_amount: int = 100) -> None:
    for account in accounts:
        account.deposit(deposit_amount)
        account.withdraw(withdraw_amount)
        print(f"{account.account_holder}'s balance is: ${account.get_balance()}")
        
# Create accounts
account1 = SavingsAccount("Alice", balance=50)
account2 = CheckingAccount("Josh", balance=25)

# List of accounts
accounts = [account1, account2]
process_accounts(accounts)
