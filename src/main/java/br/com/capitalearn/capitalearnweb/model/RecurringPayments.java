package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class RecurringPayments implements Serializable {
    private static final long serialVersionUID = 1L;

    private int recurringPaymentId;
    private Double amount;
    private String description;
    private String cycle; //MONTH, WEEK, DAY
    private LocalDate nextPaymentDate;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int userId;

    public RecurringPayments() {}

    public RecurringPayments(int recurringPaymentId, Double amount, String description, String cycle,LocalDate nextPaymentDate, String status, LocalDate createdAt, LocalDate updatedAt, int userId) {
        this.recurringPaymentId = recurringPaymentId;
        this.amount = amount;
        this.description = description;
        this.cycle = cycle;
        this.nextPaymentDate = nextPaymentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public RecurringPayments(Double amount, String description, String cycle,LocalDate nextPaymentDate, String status, int userId) {
        this.amount = amount;
        this.description = description;
        this.cycle = cycle;
        this.nextPaymentDate = nextPaymentDate;
        this.status = status;
        this.userId = userId;
    }

    public int getRecurringPaymentId() {
        return recurringPaymentId;
    }

    public void setRecurringPaymentId(int recurringPaymentId) {
        this.recurringPaymentId = recurringPaymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public LocalDate getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(LocalDate nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
