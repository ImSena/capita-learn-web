package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Investment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int investmentId;
    private int userId;
    private String investmentType; // LCA, CDB, LCI, TESOURO, ACTION, CRIPTO
    private Double amount;
    private String isRecurring; //Y, N
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Investment() {}

    public Investment(int investmentId, int userId, String investmentType, Double amount, String isRecurring, LocalDate createdAt, LocalDate updatedAt) {
        this.investmentId = investmentId;
        this.userId = userId;
        this.investmentType = investmentType;
        this.amount = amount;
        this.isRecurring = isRecurring;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Investment( int userId, String investmentType, Double amount, String isRecurring) {
        this.userId = userId;
        this.investmentType = investmentType;
        this.amount = amount;
        this.isRecurring = isRecurring;
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(String isRecurring) {
        this.isRecurring = isRecurring;
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
}
