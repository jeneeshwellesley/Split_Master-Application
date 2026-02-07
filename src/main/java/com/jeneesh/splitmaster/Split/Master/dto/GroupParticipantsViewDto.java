package com.jeneesh.splitmaster.Split.Master.dto;

public class GroupParticipantsViewDto {
    private String groupName;
    private String role;

    public GroupParticipantsViewDto() {
    }
    public GroupParticipantsViewDto(String groupName, String role) {
        this.groupName = groupName;
        this.role = role;
    }

    @Override
    public String toString() {
        return "GroupParticipantsViewDto{" +
                "groupName='" + groupName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
