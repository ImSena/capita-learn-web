package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Balance implements Serializable {
    private static final long serialVersionUID = 1L;

    private int balanceId;
    private Double balanceAmount;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int userId;

    public Balance(){

    }

    public Balance(int balanceId, Double balanceAmount, LocalDate createdAt, LocalDate updatedAt, int userId){
        this.balanceId = balanceId;
        this.balanceAmount = balanceAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public Balance(Double balanceAmount, int userId){
        this.balanceAmount = balanceAmount;
        this.userId = userId;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
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
}
