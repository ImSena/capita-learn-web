package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private int transactionId;
    private double amount;
    private String category; // GOAL, INVESTMENT, RECURRING, NORMAL
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int userId;
    private String transactionType; //ADD, REMOVE
    private String title;

    public Transaction() {}

    public Transaction(double amount, String category, String description, int userId, String transactionType, String title) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.userId = userId;
        this.transactionType = transactionType;
        this.title = title;
    }

    public Transaction(int transactionId, double amount, String category, String description, LocalDate createdAt, LocalDate updatedAt, int userId, String transactionType, String title) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.transactionType = transactionType;
        this.title = title;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
