package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;

public class TransactionGoal implements Serializable {
    private static final long serialVersionUID = 1L;

    private int transactionGoalId;
    private int transactionId;
    private int goal_id;

    public TransactionGoal() {}

    public TransactionGoal(int transactionGoalId, int transactionId, int goal_id) {
        this.transactionGoalId = transactionGoalId;
        this.transactionId = transactionId;
        this.goal_id = goal_id;
    }

    public TransactionGoal(int transactionId, int goal_id) {
        this.transactionId = transactionId;
        this.goal_id = goal_id;
    }

    public int getTransactionGoalId() {
        return transactionGoalId;
    }

    public void setTransactionGoalId(int transactionGoalId) {
        this.transactionGoalId = transactionGoalId;
    }

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
