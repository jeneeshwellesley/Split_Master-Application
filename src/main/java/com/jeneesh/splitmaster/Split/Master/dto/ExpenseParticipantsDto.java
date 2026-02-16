package com.jeneesh.splitmaster.Split.Master.dto;

public class ExpenseParticipantsDto {
    private String phoneNumber;
    private double amount;

    public ExpenseParticipantsDto(String phoneNumber,double amount) {
        this.amount = amount;
        this.phoneNumber = phoneNumber;
    }

    public ExpenseParticipantsDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
