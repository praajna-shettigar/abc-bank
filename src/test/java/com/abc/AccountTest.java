/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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




}
