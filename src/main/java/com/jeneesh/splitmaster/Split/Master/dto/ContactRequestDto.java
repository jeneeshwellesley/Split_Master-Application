package com.jeneesh.splitmaster.Split.Master.dto;

public class ContactRequestDto {
    private String phoneNumber;

    public ContactRequestDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ContactRequestDto() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
