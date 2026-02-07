package com.jeneesh.splitmaster.Split.Master.dto;

public class GroupParticipantsDto {
    private Long groupId;
    private String groupName;
    private Long contactId;
    private String contactName;
    private String name;

    public GroupParticipantsDto(Long groupId, String groupName, Long contactId, String contactName, String name) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.contactId = contactId;
        this.contactName = contactName;
        this.name = name;
    }
    public GroupParticipantsDto() {
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

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
