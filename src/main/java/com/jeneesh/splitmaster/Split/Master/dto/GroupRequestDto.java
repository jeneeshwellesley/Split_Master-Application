package com.jeneesh.splitmaster.Split.Master.dto;

public class GroupRequestDto {
    private String groupName;

    public GroupRequestDto(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
