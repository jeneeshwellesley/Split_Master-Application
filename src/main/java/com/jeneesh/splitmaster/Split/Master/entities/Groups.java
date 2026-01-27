package com.jeneesh.splitmaster.Split.Master.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Groups")
public class Groups {

    //Fields init---------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

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

    public Groups(int groupId, String name, User createdBy, LocalDateTime createdAt, LocalDateTime updatedDate) {
        this.groupId = groupId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedDate = updatedDate;
    }




}
