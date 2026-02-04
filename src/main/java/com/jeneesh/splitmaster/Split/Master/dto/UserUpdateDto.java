package com.jeneesh.splitmaster.Split.Master.dto;

public class UserUpdateDto {
    private String userName;
    private String phoneNumber;

    public UserUpdateDto() {

    }

    public UserUpdateDto(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
