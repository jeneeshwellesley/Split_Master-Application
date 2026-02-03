package com.jeneesh.splitmaster.Split.Master.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "expense_participants")
public class ExpenseParticipants {

    //Fields---------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id",nullable = false)
    private Expense expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User userId;

    @Column(name = "owned_amount")
    private double ownedAmount;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "remaining_amount")
    private double remainingAmount;

    @Column(name = "installment_count")
    private int installmentCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    //Constructors-------------------------------------------------------------------

    public ExpenseParticipants(){

    }

    public ExpenseParticipants(Expense expenseId, User userId, double ownedAmount,
                               double paidAmount,double remainingAmount,int installmentCount){
        this.expenseId = expenseId;
        this.userId = userId;
        this.ownedAmount = ownedAmount;
        this.paidAmount = paidAmount;
        this.remainingAmount = remainingAmount;
        this.installmentCount = installmentCount;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //ToString method--------------------------------------------------------------------


    @Override
    public String toString() {
        return "ExpenseParticipants{" +
                "id=" + id +
                ", expenseId=" + expenseId.getExpenseId() +
                ", userId=" + userId.getUserId() +
                ", ownedAmount=" + ownedAmount +
                ", paidAmount=" + paidAmount +
                ", remainingAmount=" + remainingAmount +
                ", installmentCount=" + installmentCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    //Getters and Setters-----------------------------------------------------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getExpenseId() {
        return expenseId.getExpenseId();
    }

    public void setExpenseId(Expense expenseId) {
        this.expenseId = expenseId;
    }

    public long getUserId() {
        return userId.getUserId();
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public double getOwnedAmount() {
        return ownedAmount;
    }

    public void setOwnedAmount(double ownedAmount) {
        this.ownedAmount = ownedAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
