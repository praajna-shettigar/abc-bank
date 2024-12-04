/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Carrington
 */
public class AccountTest {

    public AccountTest() {
    }
    
//    @Test
//    public void testTransfer(){
//        Account a1 = new Account(0);
//        Account a2 = new Account(0);
//        assertEquals(0,a1.sumTransactions(),0);
//        a1.deposit(10000);
//       // a1.Transfer(a2, 5000);
//        assertEquals(5000,a1.sumTransactions(),0);
//        assertEquals(5000,a2.sumTransactions(),0);
//    }


    @Test
    @DisplayName("Should successfully transfer amount between two accounts belonging to the same customer")
    public void testTransferMethod() {
        // Arrange: Create two accounts for the customer
        Account fromAccount = new Account(Account.CHECKING);
        Account toAccount = new Account(Account.SAVINGS);
        Customer customer = new Customer("John");
        customer.openAccount(fromAccount).openAccount(toAccount);

        // Deposit money into the 'fromAccount'
        fromAccount.deposit(1000.0);

        // Act: Perform the transfer
        fromAccount.transfer(toAccount, 500.0, customer);

        // Assert: Verify the balances after transfer
        assertEquals(500.0, fromAccount.sumTransactions(), 0.01);  // 'fromAccount' should have 500 left
        assertEquals(500.0, toAccount.sumTransactions(), 0.01);    // 'toAccount' should have 500

        // Optionally: Verify the account list size
        assertEquals(2, customer.getAccounts().size()); // Ensure the customer has 2 accounts
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Should throw IllegalArgumentException when transferring to a null account")
    public void testTransferToNullAccount() {
        // Setup
        Customer customer = new Customer("John");
        Account account1 = new Account(Account.CHECKING);
        customer.openAccount(account1);

        // Attempt to transfer to a null account
        account1.transfer(null, 100.0, customer);
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Should throw IllegalArgumentException when transferring to the same account")
    public void testTransferToSameAccount() {
        // Setup
        Customer customer = new Customer("John");
        Account account1 = new Account(Account.CHECKING);
        customer.openAccount(account1);

        // Attempt to transfer to the same account
        account1.transfer(account1, 100.0, customer);
    }

    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Should throw IllegalArgumentException when transferring between accounts of different customers")
    public void testTransferToAccountNotBelongingToCustomer() {
        // Setup
        Customer customer1 = new Customer("Praajna");
        Account account1 = new Account(Account.CHECKING);
        customer1.openAccount(account1);

        Customer customer2 = new Customer("Nithin");
        Account account2 = new Account(Account.SAVINGS);
        customer2.openAccount(account2);

        // Attempt to transfer between accounts belonging to different customers
        account1.transfer(account2, 100.0, customer1);
    }


    @Test(expected = IllegalArgumentException.class)
    @DisplayName("Should throw IllegalArgumentException when transferring a negative amount")
    public void testTransferNegativeAmount() {
        // Setup
        Customer customer = new Customer("John");
        Account account1 = new Account(Account.CHECKING);
        Account account2 = new Account(Account.SAVINGS);
        customer.openAccount(account1);
        customer.openAccount(account2);

        // Attempt to transfer a negative amount
        account1.transfer(account2, -100.0, customer);
    }




}
