package com.jeneesh.splitmaster.Split.Master.dto;

public class ExpensePayRequestDto {
    private Long groupId;
    private Long expenseId;
    private double amount;

    public ExpensePayRequestDto(Long groupId, Long expenseId, double amount) {
        this.groupId = groupId;
        this.expenseId = expenseId;
        this.amount = amount;
    }

    public ExpensePayRequestDto() {
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
