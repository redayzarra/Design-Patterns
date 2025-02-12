import java.util.List;

public abstract class BankAccount {
    private final String accountHolder;
    private final String accountType;
    private double balance; // Private attribute

    // Constructor
    public BankAccount(String accountHolder, String accountType, double balance) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        }
    }

    // Transfer method
    public void transfer(double amount, BankAccount targetAccount) {
        if (this.accountType.equals(targetAccount.accountType) && this.balance >= amount) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
        }
    }

    // Getter for balance
    public double getBalance() {
        return this.balance;
    }

    // Getter for account holder
    public String getAccountHolder() {
        return this.accountHolder;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    // Constructor
    public SavingsAccount(String accountHolder, double balance, double interestRate) {
        super(accountHolder, "savings", balance);
        this.interestRate = interestRate;
    }

    // Default constructor with interest rate of 5%
    public SavingsAccount(String accountHolder, double balance) {
        this(accountHolder, balance, 0.05);
    }

    // Apply interest
    public void applyInterest() {
        double newBalance = getBalance() + (getBalance() * this.interestRate);
        // Using deposit to update balance
        super.deposit(newBalance - getBalance());
    }
}

class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CheckingAccount(String accountHolder, double balance, double overdraftLimit) {
        super(accountHolder, "checking", balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Default constructor with overdraft limit of $500
    public CheckingAccount(String accountHolder, double balance) {
        this(accountHolder, balance, 500);
    }

    // Overriding withdraw method
    @Override
    public void withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) {
            super.deposit(-amount); // Using deposit with a negative amount
        }
    }
}

class BankSystem {
    public static void processAccounts(List<BankAccount> accounts, double depositAmount, double withdrawAmount) {
        for (BankAccount account : accounts) {
            account.deposit(depositAmount);
            account.withdraw(withdrawAmount);
            System.out.println(account.getAccountHolder() + "'s balance is: $" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        // Create accounts
        SavingsAccount account1 = new SavingsAccount("Alice", 50);
        CheckingAccount account2 = new CheckingAccount("Josh", 25);

        // List of accounts
        List<BankAccount> accounts = List.of(account1, account2);

        // Process accounts
        processAccounts(accounts, 20, 100);
    }
}