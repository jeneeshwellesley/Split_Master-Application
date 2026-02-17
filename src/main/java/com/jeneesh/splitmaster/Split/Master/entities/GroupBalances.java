package com.jeneesh.splitmaster.Split.Master.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "group_balances")
public class GroupBalances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groupId;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payerId;

    @ManyToOne
    @JoinColumn(name ="receiver_id")
    private User receiverId;

    @Column(name ="amount")
    private double amount;

    public GroupBalances() {
    }

    public GroupBalances (Groups groupId, User payerId, User receiverId, double amount) {
        this.groupId = groupId;
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GroupBalances{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", payerId=" + payerId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    public User getPayerId() {
        return payerId;
    }

    public void setPayerId(User payerId) {
        this.payerId = payerId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
