import java.util.Scanner;

// BankAccount class represents the user's bank account
class BankAccount {
    private double accountBalance; // Variable to store the account balance

    // Constructor to initialize the account balance
    public BankAccount(double initialBalance) {
        this.accountBalance = initialBalance;
    }

    // Method to retrieve the current account balance
    public double getAccountBalance() {
        return accountBalance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit of $" + amount + " successful.");
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }
}

// ATM class contains the main method for the ATM interface
public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to take user input
        BankAccount userAccount = new BankAccount(1000); // Create an instance of BankAccount with an initial balance of $1000

        System.out.println("Welcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");

        boolean continueTransactions = true; // Variable to control the continuation of transactions

        // Loop to handle user transactions until they choose to exit
        while (continueTransactions) {
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); // Read user's choice

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + userAccount.getAccountBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble(); // Read the deposit amount
                    userAccount.deposit(depositAmount); // Deposit the amount into the account
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble(); // Read the withdrawal amount
                    userAccount.withdraw(withdrawAmount); // Withdraw the amount from the account
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    continueTransactions = false; // Exit the loop and end transactions
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }

        scanner.close(); // Close the Scanner object to release resources
    }
}
