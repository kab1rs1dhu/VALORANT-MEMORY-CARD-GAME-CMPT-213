/**
 * {@code SavingsAccount} extends  {@code BankAccount} class. It represents a savings account.
 *
 * {@code SavingsAccount} class has the following attributes:
 *    -> {@code MINIMUM_BALANCE} is a double that stores the minimum balance required to keep the account active.
 *
 *    -> {@code isActive} is a boolean that stores the status of the account. If the balance is greater than or equal to the minimum balance, the account is active.
 *
 * {@code SavingsAccount} class has the following methods:
 *
 *    -> {@code SavingsAccount(double balance, double annualInterestRate)} is a constructor that initializes the balance and annual interest rate of the account.
 *       It does this by calling the constructor of the superclass. It also initializes the status of the account based upon the initial balance.
 *       If the balance is greater than or equal to the minimum balance, the account is active. Otherwise, it is inactive.
 *
 *    -> {@code isActive()} returns the status of the account. If the account is active, it returns true. Otherwise, it returns false.
 *
 *    -> {@code withdraw(double amount)} withdraws the given amount from the account by calling the superclass method.
 *        If the account is inactive, it throws an IllegalArgumentException. If the balance is less than the minimum balance, it deactivates the account.
 *
 *     -> {@code deposit(double amount)} deposits the given amount to the account by calling the superclass method. If after depositing the amount, the balance is greater than or equal to the minimum balance,
 *         it activates the account.
 *
 *      -> {@code monthlyProcess()} processes the monthly service charges, calculates the monthly interest, and resets the number of deposits, number of withdrawals, and monthly service charges.
 *          If the balance is less than the minimum balance, it deactivates the account.
 *
 *      -> {@code toString()} returns the string representation of the account displaying the balance, number of deposits, number of withdrawals, annual interest rate, and monthly service charges.
 */
package ca.cmpt213.asn4.bank;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 25;
    private boolean isActive;

    public SavingsAccount(double balance, double annualInterestRate) {
        super(balance, annualInterestRate);
        this.isActive = (balance >= MINIMUM_BALANCE);
    }

    public boolean isActive() {
        return isActive;


    }

    @Override
    public void withdraw(double amount) {
        if (isActive) {
            super.withdraw(amount);
            if (getBalance() < MINIMUM_BALANCE) {
                isActive = false;
            }
        } else {
            throw new IllegalArgumentException("Account is inactive");
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (getBalance() >= MINIMUM_BALANCE) {
            isActive = true;
        }
    }

    @Override
    public void monthlyProcess() {
        int additionalCharges = 0;
        if (getNumOfWithdrawals() > 4) {
            additionalCharges = getNumOfWithdrawals() - 4;
        }
        setMonthlyServiceCharges(getMonthlyServiceCharges() + additionalCharges);
        super.monthlyProcess();

        if (getBalance() < MINIMUM_BALANCE) {
            isActive = false;
        } else {
            isActive = true;
        }
    }


    @Override
    public String toString() {
        return "Savings Account: " + super.toString();
    }
}

