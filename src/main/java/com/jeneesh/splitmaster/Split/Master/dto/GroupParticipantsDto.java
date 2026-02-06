package com.jeneesh.splitmaster.Split.Master.dto;

public class GroupParticipantsDto {
    private Long groupId;
    private String groupName;
    private Long memberId;
    private String memberName;
    private String role;

    public GroupParticipantsDto(Long groupId, String groupName, Long memberId, String memberName, String role) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.memberId = memberId;
        this.memberName = memberName;
        this.role = role;
    }

    public GroupParticipantsDto() {
    }

    @Override
    public String toString() {
        return "GroupParticipantsDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
