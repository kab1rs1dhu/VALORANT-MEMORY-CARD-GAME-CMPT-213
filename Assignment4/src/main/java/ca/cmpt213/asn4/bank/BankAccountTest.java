/**
 * {@code BankAccountTest} class is responsible for testing the {@code BankAccount} class.
 * It uses a series of tests that test the functionality of {@code BankAccount} class and {@code SavingsAccount} class.
 *
 * There are 17 tests in total thar test whether the methods of the {@code SavingsAccount} class work correctly.
 *
 * The @Before method is used to set up the {@code SavingsAccount} object before each test.
 * The tests are ->
 *
 *    1. testInitialBalance() -> Tests if the initial balance is set correctly.
 *
 *    2. testInitialIsActive() -> Tests if the account is active initially.
 *
 *    3. testDeposit() -> Tests if the deposit method works correctly.
 *
 *    4. testDepositNegativeAmount() -> Tests if the deposit method throws an exception when a negative amount is deposited.
 *
 *    5. testWithdraw() -> Tests if the withdraw method works correctly.
 *
 *    6. testWithdrawInsufficientFunds() -> Tests if the withdraw method throws an exception when there are insufficient funds.
 *
 *    7. testWithdrawDeactivatesAccount() -> Tests if the withdraw method deactivates the account when the balance is less than the minimum balance.
 *
 *    8. testWithdrawWhenInactive() -> Tests if the withdraw method throws an exception when the account is inactive.
 *
 *    9. testDepositActivatesAccount() -> Tests if the deposit method activates the account when the balance is greater than or equal to the minimum balance.
 *
 *    10. testCalcInterest() -> Tests if the calcInterest method calculates the interest correctly.
 *
 *    11. testCalcInterestMultiplePeriods() -> Tests if the calcInterest method calculates the interest correctly for multiple periods.
 *
 *    12. testMonthlyProcess() -> Tests if the monthlyProcess method processes the monthly service charges correctly.
 *
 *    13. testMonthlyProcessWithServiceCharges() -> Tests if the monthlyProcess method processes the monthly service charges correctly when there are service charges.
 *
 *    14. testMonthlyProcessDeactivatesAccount() -> Tests if the monthlyProcess method deactivates the account when the balance is less than the minimum balance.
 *
 *    15. testMonthlyProcessActivatesAccount() -> Tests if the monthlyProcess method activates the account when the balance is greater than or equal to the minimum balance.
 *
 *    16. testToString() -> Tests if the toString method returns the correct string representation of the account.
 *
 *    17. testSetNegativeMonthlyServiceCharges() -> Tests if the setMonthlyServiceCharges method throws an exception when negative service charges are set.
 */

package ca.cmpt213.asn4.bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    private SavingsAccount account;

    @Before
    public void setUp() {
        account = new SavingsAccount(100.0, 0.05);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(100.0, account.getBalance(), 0.01);
    }

    @Test
    public void testInitialIsActive() {
        assertTrue(account.isActive());
    }

    @Test
    public void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        account.deposit(-50.0);
    }

    @Test
    public void testWithdraw() {
        account.withdraw(30.0);
        assertEquals(70.0, account.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInsufficientFunds() {
        account.withdraw(150.0);
    }

    @Test
    public void testWithdrawDeactivatesAccount() {
        account.withdraw(80.0);
        assertEquals(20.0, account.getBalance(), 0.01);
        assertFalse(account.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawWhenInactive() {
        account.withdraw(80.0); // Deactivates the account
        account.withdraw(10.0); // Should throw exception
    }

    @Test
    public void testDepositActivatesAccount() {
        account.withdraw(80.0); // Deactivates the account
        assertFalse(account.isActive());
        account.deposit(10.0);
        assertEquals(30.0, account.getBalance(), 0.01);
        assertTrue(account.isActive());
    }

    @Test
    public void testCalcInterest() {
        account.calcInterest();
        assertEquals(100.42, account.getBalance(), 0.01);
    }

    @Test
    public void testCalcInterestMultiplePeriods() {
        account.calcInterest();
        account.calcInterest();
        assertEquals(100.84, account.getBalance(), 0.01);
    }

    @Test
    public void testMonthlyProcess() {
        account.deposit(50.0);
        account.withdraw(30.0);
        account.setMonthlyServiceCharges(10.0);
        account.monthlyProcess();
        assertEquals(110.45, account.getBalance(), 0.01);
    }

    @Test
    public void testMonthlyProcessWithServiceCharges() {
        account.setMonthlyServiceCharges(10.0);
        account.monthlyProcess();
        assertEquals(90.375, account.getBalance(), 0.01);
    }

    @Test
    public void testMonthlyProcessDeactivatesAccount() {
        account.withdraw(80.0); // Deactivates the account
        account.monthlyProcess();
        assertFalse(account.isActive());
    }

    @Test
    public void testMonthlyProcessActivatesAccount() {
        account.withdraw(80.0); // Deactivates the account
        assertFalse(account.isActive());
        account.deposit(10.0); // Should activate the account
        account.monthlyProcess();
        assertTrue(account.isActive());
    }

    @Test
    public void testToString() {
        String expected = "Savings Account: Balance: 100.0, Number of Deposits: 0, Number of Withdrawals: 0, Annual Interest Rate: 0.05, Monthly Service Charges: 0.0";
        assertEquals(expected, account.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeMonthlyServiceCharges() {
        account.setMonthlyServiceCharges(-10.0);
    }
}
