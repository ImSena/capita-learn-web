package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;

public class TransactionRecurring implements Serializable {
    private static final long serialVersionUID = 1L;

    private int transactionRecurringId;
    private int transactionId;
    private int recurringId;

    public TransactionRecurring() {}

    public TransactionRecurring(int transactionRecurringId, int transactionId, int recurringId) {
        this.transactionRecurringId = transactionRecurringId;
        this.transactionId = transactionId;
        this.recurringId = recurringId;
    }

    public TransactionRecurring(int transactionId, int recurringId) {
        this.transactionId = transactionId;
        this.recurringId = recurringId;
    }

    public int getTransactionRecurringId() {
        return transactionRecurringId;
    }

    public void setTransactionRecurringId(int transactionRecurringId) {
        this.transactionRecurringId = transactionRecurringId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(int recurringId) {
        this.recurringId = recurringId;
    }
}
