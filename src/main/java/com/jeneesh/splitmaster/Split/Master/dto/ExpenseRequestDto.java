package com.jeneesh.splitmaster.Split.Master.dto;

import java.util.List;

public class ExpenseRequestDto {
    private Long groupId;
    private String desc;
    private double totalAmount;
    private double amountPaidByCreator;
    private List<ExpenseParticipantsDto>phoneNumbers;

    public ExpenseRequestDto(Long groupId,
                             double totalAmount,double amountPaidByCreator, String desc, List<ExpenseParticipantsDto> phoneNumbers) {
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.amountPaidByCreator = amountPaidByCreator;
        this.desc = desc;
        this.phoneNumbers = phoneNumbers;
    }

    public ExpenseRequestDto() {
    }

    @Override
    public String toString() {
        return "ExpenseRequestDto{" +
                "groupId=" + groupId +
                ", desc='" + desc + '\'' +
                ", totalAmount=" + totalAmount +
                ", amountPaidByCreator=" + amountPaidByCreator +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ExpenseParticipantsDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<ExpenseParticipantsDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public double getAmountPaidByCreator() {
        return amountPaidByCreator;
    }

    public void setAmountPaidByCreator(double amountPaidByCreator) {
        this.amountPaidByCreator = amountPaidByCreator;
    }
}
