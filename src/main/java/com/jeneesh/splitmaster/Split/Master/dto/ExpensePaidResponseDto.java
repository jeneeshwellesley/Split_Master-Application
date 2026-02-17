package com.jeneesh.splitmaster.Split.Master.dto;

public class ExpensePaidResponseDto {
    private Long groupId;
    private String groupName;
    private double owedAmount;
    private double paidAmount;

    public ExpensePaidResponseDto(Long groupId, String groupName, double owedAmount, double paidAmount) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.owedAmount = owedAmount;
        this.paidAmount = paidAmount;
    }

    public ExpensePaidResponseDto() {
    }

    @Override
    public String toString() {
        return "ExpensePaidResponseDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", owedAmount=" + owedAmount +
                ", paidAmount=" + paidAmount +
                '}';
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getOwedAmount() {
        return owedAmount;
    }

    public void setOwedAmount(double owedAmount) {
        this.owedAmount = owedAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }
}
