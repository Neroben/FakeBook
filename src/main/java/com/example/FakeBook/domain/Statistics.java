package com.example.FakeBook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="statistics")
@Getter
@Setter
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

}

