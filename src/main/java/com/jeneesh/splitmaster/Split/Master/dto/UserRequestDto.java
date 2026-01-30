package com.jeneesh.splitmaster.Split.Master.dto;

public class UserRequestDto {
    private String name;
    private String phoneNumber;
    private String password;

    UserRequestDto(String name, String phoneNumber,  String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
    }

    public UserRequestDto() {

    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
