package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Bank {
    private List<Customer> customers;

    private static final Logger LOGGER = Logger.getLogger(Bank.class.getName());

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        if (customers == null || customers.isEmpty()) {
            LOGGER.warning("Customer list is either null or empty");
            throw new IllegalStateException("No customers available");
        }
        return customers.get(0).getName();
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
