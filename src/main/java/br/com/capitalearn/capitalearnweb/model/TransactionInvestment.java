package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;

public class TransactionInvestment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int transactionInvestmentId;
    private int transactionId;
    private int investmentId;

    public TransactionInvestment() {}

    public TransactionInvestment(int transactionInvestmentId, int transactionId, int investmentId) {
        this.transactionInvestmentId = transactionInvestmentId;
        this.transactionId = transactionId;
        this.investmentId = investmentId;
    }

    public TransactionInvestment(int transactionInvestmentId, int transactionId) {
        this.transactionInvestmentId = transactionInvestmentId;
        this.transactionId = transactionId;
    }

    public int getTransactionInvestmentId() {
        return transactionInvestmentId;
    }

    public void setTransactionInvestmentId(int transactionInvestmentId) {
        this.transactionInvestmentId = transactionInvestmentId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }
}
