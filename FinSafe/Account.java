import java.util.ArrayList;

public class Account {
    private String accountHolder;
    private double balance;
    private ArrayList<Double> transactionHistory;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        balance += amount;
        addTransaction(amount);
        System.out.println("Deposit successful.");
    }

    public void processTransaction(double amount) throws InSufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        }

        if (amount > balance) {
            throw new InSufficientFundsException("Insufficient balance. Transaction failed.");
        }

        balance -= amount;
        addTransaction(-amount);
        System.out.println("Withdrawal successful.");
    }

    private void addTransaction(double amount) {
        if (transactionHistory.size() == 5) {
            transactionHistory.remove(0);
        }
        transactionHistory.add(amount);
    }

    public void printMiniStatement() {
        System.out.println("\n===== MINI STATEMENT =====");
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Current Balance: ₹" + String.format("%.2f", balance));

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Last Successful Transactions:");
            for (int i = 0; i < transactionHistory.size(); i++) {
                double amount = transactionHistory.get(i);
                if (amount > 0) {
                    System.out.println((i + 1) + ". Deposited: ₹" + String.format("%.2f", amount));
                } else {
                    System.out.println((i + 1) + ". Withdrawn: ₹" + String.format("%.2f", Math.abs(amount)));
                }
            }
        }
        System.out.println("==========================\n");
    }
}