package com.jeneesh.splitmaster.Split.Master.dto;

import java.util.List;

public class ExpenseOverallSplitsResDto {
    private Long groupId;
    private String groupName;
    private List<ExpenseSplitView>splits;

    public ExpenseOverallSplitsResDto(Long groupId, String groupName, List<ExpenseSplitView> splits) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.splits = splits;
    }

    public ExpenseOverallSplitsResDto() {
    }

    @Override
    public String toString() {
        return "ExpenseOverallSplitsResDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", splits=" + splits +
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

    public List<ExpenseSplitView> getSplits() {
        return splits;
    }

    public void setSplits(List<ExpenseSplitView> splits) {
        this.splits = splits;
    }
}
