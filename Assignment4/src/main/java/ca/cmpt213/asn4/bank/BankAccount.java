/**
 * {@code BankAccount } class is an abstract class that represents a bank account.
 *
 * {@code BankAccount}class has the following attributes:
 *
 *     -> {@code balance} is a double that stores the balance of the account.
 *     -> {@code numOfDeposits} is an integer that stores the number of deposits made.
 *     -> {@code numOfWithdrawals} is an integer that stores the number of withdrawals made.
 *     -> {@code annualInterestRate} is a double that stores the annual interest rate of the account.
 *     -> {@code monthlyServiceCharges} is a double that stores the monthly service charges of the account.
 *
 *  {@code BankAccount} class has the following methods:
 *
 *    -> {@code BankAccount(double balance, double annualInterestRate)} is a constructor that initializes the balance and annual interest rate of the account.
 *        if the balance or the annual interest rate is less than 0, it throws an IllegalArgumentException. It also initializes the number of deposits, number of withdrawals, and monthly service charges to 0.
 *
 *    -> {@code deposit(double amount)} deposits the given amount to the account. If the amount is less than 0, it throws an IllegalArgumentException. It also increments the number of deposits.
 *
 *    -> {@code withdraw(double amount)} withdraws the given amount from the account. If the amount is less than 0, it throws an IllegalArgumentException. If the balance is less than the amount,
 *        it throws an IllegalArgumentException. It also increments the number of withdrawals.
 *
 *     -> {@code calcInterest()} calculates the monthly interest of the account.
 *
 *     -> {@code monthlyProcess()} processes the monthly service charges, calculates the monthly interest, and resets the number of deposits, number of withdrawals, and monthly service charges.
 *
 *     -> {@code getBalance()} returns the balance of the account.
 *
 *     -> {@code getNumOfDeposits()} returns the number of deposits made.
 *
 *     -> {@code getNumOfWithdrawals()} returns the number of withdrawals made.
 *
 *     -> {@code getMonthlyServiceCharges()} returns the monthly service charges of the account.
 *
 *     -> {@code setMonthlyServiceCharges(double monthlyServiceCharges)} sets the monthly service charges of the account.
 *         If the monthly service charges are less than 0, it throws an IllegalArgumentException.
 *
 *     -> {@code toString()} returns the string representation of the account displaying the balance, number of deposits, number of withdrawals, annual interest rate, and monthly service charges.
 */

package ca.cmpt213.asn4.bank;

public abstract class BankAccount {

    private double balance;
    private int numOfDeposits;
    private int numOfWithdrawals;
    private double annualInterestRate;
    private double monthlyServiceCharges;

    public BankAccount(double balance, double annualInterestRate) {
        if (balance < 0 || annualInterestRate < 0) {
            throw new IllegalArgumentException("Balance and annual interest rate must be greater than 0");
        }
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.numOfDeposits = 0;
        this.numOfWithdrawals = 0;
        this.monthlyServiceCharges = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        } else {
            this.balance += amount;
            numOfDeposits++;
        }
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        } else if (balance > amount) {
            balance -= amount;
            numOfWithdrawals++;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
    }

    public void monthlyProcess() {
        balance -= monthlyServiceCharges;
        calcInterest();
        numOfDeposits = 0;
        numOfWithdrawals = 0;
        monthlyServiceCharges = 0;
    }

    public double getBalance() {
        return balance;
    }


    public int getNumOfWithdrawals() {
        return numOfWithdrawals;
    }

    public double getMonthlyServiceCharges() {
        return monthlyServiceCharges;
    }

    public void setMonthlyServiceCharges(double monthlyServiceCharges) {
        if (monthlyServiceCharges < 0) {
            throw new IllegalArgumentException("Monthly service charges must be non-negative");
        }
        this.monthlyServiceCharges = monthlyServiceCharges;
    }

    @Override
    public String toString() {
        return "Balance: " + balance + ", Number of Deposits: " + numOfDeposits + ", Number of Withdrawals: " + numOfWithdrawals +
                ", Annual Interest Rate: " + annualInterestRate + ", Monthly Service Charges: " + monthlyServiceCharges;
    }
}

