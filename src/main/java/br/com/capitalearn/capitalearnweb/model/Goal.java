package br.com.capitalearn.capitalearnweb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Goal implements Serializable {
    private static final long serialVersionUID = 1L;

    private int goalId;
    private int userId;
    private String title;
    private String description;
    private Double goalAmount;
    private LocalDate dueDate;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String priority; //NORMAL, HARD, HARDCORE
    private Double goalAmountContain;

    public Goal() {}

    public Goal(int goalId, int userId){
        this.goalId = goalId;
        this.userId = userId;
    }

    public Goal(int goalId, int userId, String title, String description, Double goalAmount, LocalDate dueDate, String status, LocalDate createdAt, LocalDate updatedAt, String priority) {
        this.goalId = goalId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.goalAmount = goalAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.priority = priority;
    }

    public Goal(int userId, String title, String description, Double goalAmount, LocalDate dueDate, String status, String priority) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.goalAmount = goalAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Double getGoalAmountContain() {
        return goalAmountContain;
    }

    public void setGoalAmountContain(Double goalAmountContain) {
        this.goalAmountContain = goalAmountContain;
    }
}
