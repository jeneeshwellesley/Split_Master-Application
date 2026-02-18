package com.jeneesh.splitmaster.Split.Master.dto;

public class SplitsRequestDto {
    private Long groupId;

    public SplitsRequestDto(Long groupId) {
        this.groupId = groupId;
    }

    public SplitsRequestDto() {
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
