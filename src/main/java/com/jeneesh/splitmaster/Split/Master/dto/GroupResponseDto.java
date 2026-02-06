package com.jeneesh.splitmaster.Split.Master.dto;

public class GroupResponseDto {
    private String groupName;
    private Long createdBy;

    public GroupResponseDto(String groupName, Long createdBy) {
        this.groupName = groupName;
        this.createdBy = createdBy;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
