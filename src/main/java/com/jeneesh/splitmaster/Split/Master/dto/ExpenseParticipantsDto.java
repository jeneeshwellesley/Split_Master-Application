package com.jeneesh.splitmaster.Split.Master.dto;

public class ExpenseParticipantsDto {
    private String phoneNumber;

    public ExpenseParticipantsDto(String phoneNumber) {
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
}
