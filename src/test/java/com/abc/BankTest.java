package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    //first customer success check
    @Test
    public void testGetFirstCustomerSuccess(){
        Bank bank = new Bank();
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        assertEquals("Alice", bank.getFirstCustomer());
    }

    @Test
    public void testGetFirstCustomerNoCustomer(){
        Bank bank = new Bank();
        //As there is no customer should return "Error"
        assertEquals("Error", bank.getFirstCustomer());
    }

    @Test
    public void testGetFirstCustomerNullCustomers(){
        Bank bank = new Bank();

        //manually setting customers to null
        bank.customers = null;

        assertEquals("Error", bank.getFirstCustomer());

    }

}
