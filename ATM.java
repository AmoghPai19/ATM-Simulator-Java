import java.util.*;

class IncorrectInputException extends Exception{
    IncorrectInputException(String msg){
        super(msg);
    }

}

class Transaction {
    String type;
    double amount;

    Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String toString() {
        return type + ": " + amount;
    }
}

class BankAccount {
    String accountNumber;
    String pin;
    double balance;
    List<Transaction> transactions = new ArrayList<>();

    BankAccount(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. Your new balance is: " + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. Your new balance is: " + balance);
        }
    }

    public void changePin(String newPin) {
        pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            transactions.add(new Transaction("Transfer to " + recipient.accountNumber, amount));
            recipient.deposit(amount);
            System.out.println("Transfer successful. Your new balance is: " + balance);
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATM {
    public static void main(String[] args) throws IncorrectInputException {
        BankAccount account = new BankAccount("123456789", "1234");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        boolean authenticated = false;
        while (!authenticated) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            authenticated = account.authenticate(enteredPin);
            if (!authenticated) {
                System.out.println("Incorrect PIN. Try again.");
            }
        }
        try{
            int choice;
            do {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Change PIN");
                System.out.println("5. Transfer");
                System.out.println("6. Transaction History");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if(choice>7 || choice<1){
                throw new IncorrectInputException("Invalid Choice! Try Again");
            }

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.next();
                        account.changePin(newPin);
                        break;
                    case 5:
                        System.out.print("Enter recipient account number (dummy): ");
                        scanner.next(); // just simulate
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        BankAccount recipient = new BankAccount("987654321", "0000"); // Dummy recipient
                        account.transfer(recipient, transferAmount);
                        break;
                    case 6:
                        account.showTransactionHistory();
                        break;
                    case 7:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 7);
        } 
        catch(IncorrectInputException e){
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}