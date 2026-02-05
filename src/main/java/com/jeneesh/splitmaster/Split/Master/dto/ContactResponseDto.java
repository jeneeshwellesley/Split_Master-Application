package com.jeneesh.splitmaster.Split.Master.dto;

import java.time.LocalDateTime;

public class ContactResponseDto {
    private String contactName;
    private String phoneNumber;
    private String friendOf;
    private String friendNum;
    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;

    public ContactResponseDto(){

    }

    public ContactResponseDto(String contactName, String phoneNumber,String  friendOf, String friendNum) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.friendOf = friendOf;
        this.friendNum = friendNum;
        this.addedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ContactResponseDto{" +
                "contactName='" + contactName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", friendOf='" + friendOf + '\'' +
                ", friendNum='" + friendNum + '\'' +
                ", addedAt=" + addedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(String friendOf) {
        this.friendOf = friendOf;
    }

    public String getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(String friendNum) {
        this.friendNum = friendNum;
    }
}
