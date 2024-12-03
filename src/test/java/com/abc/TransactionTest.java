package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }

    @Test
    public void testTransactionAmount(){
        double amount = 100.0;
        Transcation transaction = new Transaction(amount);
        assertEquals(amount,transaction.amount,0.0);
    }

    @Test
    public void testTransactionDate(){
        Transaction transaction = new Transaction(100.0);
        Date now = DateProvider.getInstance().now();

        //samll time difference between instance creation and test run
        assertTrue(transaction.transactionDate.getTime()<= now.getTime());

    }
}
