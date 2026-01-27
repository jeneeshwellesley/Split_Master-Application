package com.jeneesh.splitmaster.Split.Master.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "groups_table")
public class Groups {

    //Fields init---------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "group_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedDate;

    //Constructors--------------------------------------------------------------------

    public Groups(){

    }

    public Groups(long groupId, String name, User createdBy) {
        this.groupId = groupId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    //ToString method---------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy.getUserId() +
                ", createdAt=" + createdAt +
                ", updatedDate=" + updatedDate +
                '}';
    }


    //Getters and Setters--------------------------------------------------------------------------------------------


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreatedBy() {
        return createdBy.getUserId();
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
