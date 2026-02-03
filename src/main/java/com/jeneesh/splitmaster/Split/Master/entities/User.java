package com.jeneesh.splitmaster.Split.Master.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_table")
public class User {

    //Fields Init-------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Long userId;

    @Column(name ="user_Name",nullable = true)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phone_Number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "created_At",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_At",nullable = true)
    private LocalDateTime updatedAt;

    //Constructors-----------------------------------------------------------------

    public User() {}

    public User(String userName, String password,
                String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //ToString method init---------------------------------------------------------------

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    //Getters and Setters---------------------------------------------------------------------------


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
