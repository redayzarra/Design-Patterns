abstract class BankAccount {
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
