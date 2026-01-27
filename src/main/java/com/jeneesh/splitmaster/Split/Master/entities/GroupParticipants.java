package com.jeneesh.splitmaster.Split.Master.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_participants_table")
public class GroupParticipants {

    //Fields------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Groups groupId;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private User membersId;

    @Column(name = "role")
    private String role;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    //Constructors------------------------------------------------------------

    public GroupParticipants(){

    }

    public GroupParticipants(long id, Groups groupId,User membersId, String role){
        this.id = id;
        this.groupId = groupId;
        this.membersId = membersId;
        this.role = role;
        this.joinedAt = LocalDateTime.now();
    }

    //ToString method------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "GroupParticipants{" +
                "id=" + id +
                ", groupId=" + groupId.getGroupId() +
                ", membersId=" + membersId.getUserId() +
                ", role='" + role + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }

    //Getters and Setters---------------------------------------------------------------------------------


    public long getGroupId() {
        return groupId.getGroupId();
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMembersId() {
        return membersId.getUserId();
    }

    public void setMembersId(User membersId) {
        this.membersId = membersId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
