package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    //Adding new account type
    public static final int SUPER_SAVINGS = 3;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            case SUPER_SAVINGS: //new account type
                return calculateMaxiSavingsInterest(amount) + (amount *0.02);
            default:
                return amount * 0.001;
        }
    }

    //private method for maxi savings interest calculation
    private double calculateMaxiSavingsInterest(double amount){
        if(amount <= 1000)
            return amount * 0.02;
        if(amount <=2000)
            return 20+(amount-1000)*0.05;
        return 70 + (amount -2000) * 0.1;

    }

    //Transfer funds between accounts
//    public void transfer(Account toAccount, double amount){
//        if(this==toAccount){
//            throw new IllegalArgumentException("Cannot transfer to the same account");
//
//        }
//        this.withdraw(amount);
//        toAccount.deposit(amount);
//    }

    public void transfer(Account toAccount, double amount, Customer customer) {
        // Null checks
        if (toAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null.");
        }

        // Same account check
        if (this == toAccount) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }

        // Ownership check - Ensure both accounts belong to the same customer
        if (!customer.getAccounts().contains(this) || !customer.getAccounts().contains(toAccount)) {
            throw new IllegalArgumentException("Both accounts must belong to the same customer.");
        }

        // Transaction execution with rollback handling

        this.withdraw(amount);
        toAccount.deposit(amount);

    }


    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
