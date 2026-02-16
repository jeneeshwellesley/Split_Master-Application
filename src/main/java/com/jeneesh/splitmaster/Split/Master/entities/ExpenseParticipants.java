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
    @JoinColumn(name = "group_id",nullable = false)
    private Groups groupId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User userId;

    @Column(name = "owned_amount")
    private double ownedAmount;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;



    //Constructors-------------------------------------------------------------------

    public ExpenseParticipants(){

    }

    public ExpenseParticipants(Expense expenseId,Groups groupId, User userId, double ownedAmount,
                               double paidAmount){
        this.expenseId = expenseId;
        this.groupId = groupId;
        this.userId = userId;
        this.ownedAmount = ownedAmount;
        this.paidAmount = paidAmount;
        this.createdAt = LocalDateTime.now();
    }

    //ToString method--------------------------------------------------------------------

    @Override
    public String toString() {
        return "ExpenseParticipants{" +
                "id=" + id +
                ", expenseId=" + expenseId +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", ownedAmount=" + ownedAmount +
                ", paidAmount=" + paidAmount +
                ", createdAt=" + createdAt +
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }
}
