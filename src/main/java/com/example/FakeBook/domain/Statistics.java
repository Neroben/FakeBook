package com.example.FakeBook.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private boolean active;
    private Timestamp timestamp;

    public Statistics(){

    }

    public Statistics(User user, boolean active, Timestamp timestamp){
        this.user = user;
        this.active = active;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
