package com.jeneesh.splitmaster.Split.Master.dto;

public class UserLoginDto {
    private String phoneNumber;
    private String password;

    UserLoginDto(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    UserLoginDto() {

    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
