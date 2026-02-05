package com.jeneesh.splitmaster.Split.Master.dto;

import java.time.LocalDateTime;

public class ContactViewResponseDto {

    private String name;
    private String phoneNumber;

    public ContactViewResponseDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public ContactViewResponseDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
