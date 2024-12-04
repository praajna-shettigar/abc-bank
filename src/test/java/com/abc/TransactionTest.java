package com.abc;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    @DisplayName("Verify that a Transaction object is successfully created")
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }

    @Test
    @DisplayName("Validate the transaction amount matches the initialized value")
    public void testTransactionAmount(){
        double amount = 100.0;
        Transaction transaction = new Transaction(amount);
        assertEquals(amount,transaction.amount,0.0);
    }

    @Test
    @DisplayName("Verify the transaction date is initialized correctly")
    public void testTransactionDate(){
        Transaction transaction = new Transaction(100.0);
        Date now = DateProvider.getInstance().now();

        //small time difference between instance creation and test run
        assertTrue(transaction.transactionDate.getTime()<= now.getTime());

    }
}
