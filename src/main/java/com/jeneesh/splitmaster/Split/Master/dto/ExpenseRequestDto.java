package com.jeneesh.splitmaster.Split.Master.dto;

import java.util.List;

public class ExpenseRequestDto {
    private Long groupId;
    private double totalAmount;
    private double paidByCreator;
    private String desc;
    private List<ExpenseParticipantsDto>phoneNumbers;
    private List<ContactRequestDto> contacts;
    public ExpenseRequestDto() {}

    public ExpenseRequestDto(Long groupId, double totalAmount, double paidByCreator, String desc, List<ExpenseParticipantsDto> phoneNumbers) {
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.paidByCreator = paidByCreator;
        this.desc = desc;
        this.phoneNumbers = phoneNumbers;
    }
    public ExpenseRequestDto(Long groupId, double totalAmount, List<ContactRequestDto> contacts,String  desc) {
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.desc = desc;
        this.contacts = contacts;
    }



    @Override
    public String toString() {
        return "ExpenseRequestDto{" +
                "groupId=" + groupId +
                ", totalAmount=" + totalAmount +
                ", paidByCreator=" + paidByCreator +
                ", desc='" + desc + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidByCreator() {
        return paidByCreator;
    }

    public void setPaidByCreator(double paidByCreator) {
        this.paidByCreator = paidByCreator;
    }

    public List<ExpenseParticipantsDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<ExpenseParticipantsDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ContactRequestDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactRequestDto> contacts) {
        this.contacts = contacts;
    }
}
