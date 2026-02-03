package com.jeneesh.splitmaster.Split.Master.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expense_table")
public class Expense {

    //Fields------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Groups groupId;

    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User createdBy;

    @Column(name = "description")
    private String description;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //Constructors-----------------------------------------------------------------

    public Expense(){

    }

    public Expense(Groups groupId, User createdBy,
                   String description, double totalAmount){
        this.groupId = groupId;
        this.createdBy = createdBy;
        this.description = description;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();

    }

    //ToString method---------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", groupId=" + groupId.getGroupId() +
                ", createdBy=" + createdBy.getUserId() +
                ", description='" + description + '\'' +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }

    //Getters and Setters---------------------------------------------------------


    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public long getGroupId() {
        return groupId.getGroupId();
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    public long getCreatedBy() {
        return createdBy.getUserId();
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
