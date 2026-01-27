package com.jeneesh.splitmaster.Split.Master.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Contacts")
public class Contacts {

    //Fields Init-----------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private User contact;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedAt;

    //Constructors------------------------------------------------------------------

    public Contacts() {

    }

    public Contacts(User user, User contact) {
        this.user = user;
        this.contact = contact;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //ToString init------------------------------------------------------------------


    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", userId = " + user.getUserId() +
                ", contactId=" + contact.getUserId() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    //Getters and Setters----------------------------------------------------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getContactId() {
        return contact.getUserId();
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
