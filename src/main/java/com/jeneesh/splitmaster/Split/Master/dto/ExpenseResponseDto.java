package com.jeneesh.splitmaster.Split.Master.dto;

import java.util.List;

public class ExpenseResponseDto {
    private String groupName;
    private Long groupId;
    private Long createdBy;
    private String desc;
    private List<ExpenseParticipantsDto>numbers;

    public ExpenseResponseDto(String groupName, Long groupId, Long createdBy, String desc, List<ExpenseParticipantsDto> numbers) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.createdBy = createdBy;
        this.desc = desc;
        this.numbers = numbers;
    }

    public ExpenseResponseDto() {

    }

    @Override
    public String toString() {
        return "ExpenseResponseDto{" +
                "groupName='" + groupName + '\'' +
                ", groupId=" + groupId +
                ", createdBy=" + createdBy +
                ", desc='" + desc + '\'' +
                ", numbers=" + numbers +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public List<ExpenseParticipantsDto> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<ExpenseParticipantsDto> numbers) {
        this.numbers = numbers;
    }
}
